package tp.ms.base.rest.typecoded.id;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class OidGenerator {
	
	static final Logger log = LoggerFactory.getLogger(OidGenerator.class);
	
	static final int gid_amount = 1000;
	/*****默认情况下的初始值******/
	static final String initIdCode = "00000000000000000000010000";
	Object lock;
	private static OidGenerator stance;
	static {
		stance = new OidGenerator();
	}
	private OidGenerator(){
		lock = new Object();
	}
	public static OidGenerator instance() {
		return stance;
	}
	Map<String, OidCount> oidMap = new HashMap<String, OidCount>();
	Map<String, Lock> locks = new HashMap<String, Lock>();
	public String nextGidBase(String relationKey){
		OidCount oidCount;
		Lock l = getLock(relationKey);
		l.lock();
		try {
			String id = null;
			oidCount = oidMap.get(relationKey);
			if(oidCount == null) {
				oidCount = new OidCount();
				oidMap.put(relationKey, oidCount);
			}
			//从文件中读取上次最后一个生成的key值
			if(oidCount.amount%gid_amount==0 || 0==oidCount.amount) {
				id = readIdbyFile(relationKey, oidCount);
			}else {
				id = oidCount.oidBase;
			}
			oidCount.oidBase = OidAlgorithm.instance(id).nextGitCode();
			++oidCount.amount;
		} finally {
			l.unlock();
		}
		return oidCount.oidBase;
	}
	
	/*****默认情况下最后生成的key存放的文件路径******/
	static String rootPath = System.getProperty("catalina.base")==null? System.getProperty("user.dir"):System.getProperty("catalina.base") ;
	static String dirPath = rootPath + File.separator + "bin" + File.separator ;
	static String filePath = dirPath + "idstorage.ca";
	private String readIdbyFile(String relationKey, OidCount oidCount) {
		String fixKey = relationKey+"=";
		String oid = null;
		File file = new File(filePath);
		if(file.exists()){
	        BufferedReader reader = null;
	        try {
//	            System.out.println("以行为单位读取文件内容，一次读一整行：");
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
//	                System.out.println("line " + line + ": " + tempString);
	            	if(!StringUtils.isEmpty(tempString) && tempString.startsWith(fixKey)) {
	            		tempString = tempString.replaceAll(fixKey, "");
		                break;
	            	}
	            }
	            reader.close();
	            if(StringUtils.isEmpty(tempString) || tempString.trim().length() != OidAlgorithm.TATOL_LENGTH){
	            	oid = initIdCode(relationKey);
	            }else{
	            	oid = tempString.trim();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		}else{
			oid = initIdCode(relationKey);
		}
		OidAlgorithm oidAlgorithm = OidAlgorithm.instance(oid);
		for (int i = 0; i < gid_amount; i++) {
			oidAlgorithm.nextGitCode();
		}
		writeIdInFile(fixKey, oidAlgorithm.nextGitCode());
		return oid;
	}
	String initIdCode(String relationKey) {
		String inid, fix = relationKey + generateRandom();
		if(initIdCode.length() >= OidAlgorithm.CODE_LENGTH){
			inid = initIdCode.substring(initIdCode.length()-OidAlgorithm.CODE_LENGTH);
		}else{
			inid = cloneString(OidAlgorithm.CODE_LENGTH - initIdCode.length()) + initIdCode;
		}
		
		int inidleng = fix.length() + inid.length();
		if(inidleng < OidAlgorithm.TATOL_LENGTH) {
			inid = cloneString(OidAlgorithm.TATOL_LENGTH - inidleng) + inid;
		}else if(inidleng > OidAlgorithm.TATOL_LENGTH) {
			inid = inid.substring(inidleng-OidAlgorithm.TATOL_LENGTH);
		}
		return fix+inid;
	}
	
	String cloneString(int length) {
		byte[] bs = new byte[length]; 
		for(int i=0; i<length; i++){
			bs[i] = 48;
		}
		return new String(bs);
	}
	static final char[] ap = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	//生成一个随机唯一性的标识
	String generateRandom() {
		int leng = ap.length;
		int gm;
		long y = (long) (System.currentTimeMillis()*(Math.random())+UUID.randomUUID().toString().hashCode());
		String result = "";
		do {
			gm = (int) (y%leng);
			y = y/leng;
			result = ap[gm] + result;
		} while (y>0);
		return result;
	}
	Lock getLock(String gidMark) {
		String lockKey = gidMark;
		Lock l = null;
		synchronized (lock) {
			l = locks.get(lockKey);
			if(l==null) {
				l = new ReentrantLock();
				locks.put(gidMark, l);
			}
		}
		return l;
	}
	
	
	/**
	 * 将参数写入文件
	 * @param lastId
	 */
	
	void writeIdInFile(String fixKey, String nextGidAmountCode) {
		File file = new File(filePath);
		try {
			FileWriter fileWritter;
            boolean fixKeyExists = false;
            boolean fileExists = file.exists();
            int line = 0;
			if(fileExists){
				File fileTemp = new File(filePath+".temp");
				FileWriter fileWritterTemp = new FileWriter(fileTemp, true);
		        BufferedWriter bufferWritterTemp = new BufferedWriter(fileWritterTemp);
//	            System.out.println("以行为单位读取文件内容，一次读一整行：");
				BufferedReader reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	            	if(line != 0)
	            		bufferWritterTemp.newLine();
	            	if(!StringUtils.isEmpty(tempString) && tempString.startsWith(fixKey)) {
	            		if(fixKeyExists)
	            			break;
	            		fixKeyExists = true;
	            		bufferWritterTemp.write(fixKey+nextGidAmountCode);
	            	}else {
	            		bufferWritterTemp.write(tempString);
	            	}
	            	line++;
	            }
	            if(!fixKeyExists) {
            		bufferWritterTemp.newLine();
            		bufferWritterTemp.write(fixKey+nextGidAmountCode);
	            }
	            bufferWritterTemp.flush();
	            bufferWritterTemp.close();
	            reader.close();
	            file.delete();
	            fileTemp.renameTo(file);
			}else {
				File dir = new File(dirPath);
				if(!dir.exists())
					dir.mkdirs();
				file.createNewFile();
				fileWritter = new FileWriter(file);
		        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		        bufferWritter.write(fixKey+nextGidAmountCode);
		        bufferWritter.flush();
		        bufferWritter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class OidCount{
		String oidBase;
		int amount;
		public OidCount() {
			this.oidBase = initIdCode;
			this.amount = 0;
		}
	}
}

class OidAlgorithm {
	/** 设置生成的oid长度*/
	final static int CODE_LENGTH = 13;
	/** 设置生成的oid长度*/
	final static int TATOL_LENGTH = 30;
	/** 最大编码 90 */
	final byte MAX_CODE = 90;
	/** 最小编码 48 */
	final byte MIN_CODE = 48;
	/** 14 位 OidBase 码 */
	private byte[] gidBaseCodes = new byte[TATOL_LENGTH];

	private static OidAlgorithm stance= null;
	static OidAlgorithm instance(String currid){
		if(stance == null)
			stance = new OidAlgorithm();
		stance.setGidBaseCodes(currid.getBytes());
		return stance;
	}

	void setGidBaseCodes(byte[] gidBase) {	
		if (gidBase.length != TATOL_LENGTH)
			return;
		System.arraycopy(gidBase, 0, gidBaseCodes, 0, TATOL_LENGTH);
	}
	
	String nextGitCode() {
		for (int i = gidBaseCodes.length - 1; i >= 0; --i) {
			byte code = (byte) (gidBaseCodes[i] + 1);
			boolean carryUp = false;
			byte newCode = code;
			if (code > MAX_CODE) {
				newCode = MIN_CODE;
				carryUp = true;
			}
			// 跳过数字与大写字母之间的其他字符：
			if (newCode == 58) {
				newCode = 65;
			}
				gidBaseCodes[i] = newCode;
			
			if (!carryUp)
				break;
		}
		return new String(gidBaseCodes);
	}
}