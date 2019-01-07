package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ObjectUtilms;

public class HandleHelper {
	
	public static void example(String qualifiedName) throws ADBusinessException {
		String target = HandleHelper.class.getClassLoader().getResource("").getPath().replaceAll("target/classes",
				"src/main/java");
		qualifiedName = qualifiedName.replace(".", File.separator);
		target += File.separator + qualifiedName+ "Example.java";
		BufferedReader orignFile = null;
		FileWriter fw = null;
		try {
			orignFile = new BufferedReader(new FileReader(target));
			StringBuffer newFile = new StringBuffer();
	        String tempString = null;
	        
	        // 一次读入一行，直到读入null为文件结束

            while ((tempString = orignFile.readLine()) != null) {
            	if(ObjectUtilms.isNotEmpty(tempString) && tempString.startsWith("public class ")) {
            		newFile.append("import tp.ms.common.bean.vo.BaseExample;");
            		newFile.append("\r\n");
            		newFile.append("\r\n");
            		if(tempString.indexOf("{") > -1) {
            			tempString = tempString.substring(0, tempString.indexOf("{")) + " extends BaseExample {";
            		}else {
            			tempString += " extends BaseExample ";
            		}
            	}
                newFile.append(tempString);
        		newFile.append("\r\n");
            }

            fw = new FileWriter(new File(target));
            fw.write(newFile.toString());

        }catch (Exception e) {
			throw new ADBusinessException(e);
		}finally {
			try {
				if(orignFile != null) 
					orignFile.close();
			} catch (IOException e) {
				throw new ADBusinessException(e);
			}
			if(fw != null) {
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					throw new ADBusinessException(e);
				}
			}
		}
		
		
	}

	public static void daoMap(String qualifiedName) throws ADBusinessException {

		String target = HandleHelper.class.getClassLoader().getResource("").getPath().replaceAll("target/classes",
				"src/main/java");
		String className = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
		String voPackage = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
		qualifiedName = voPackage.replace(".vo",  ".mapper.") + className;
		qualifiedName = qualifiedName.replace(".", File.separator);
		target += File.separator + qualifiedName+ "Mapper.java";
		BufferedReader orignFile = null;
		FileWriter fw = null;
		try {
			orignFile = new BufferedReader(new FileReader(target));
			StringBuffer newFile = new StringBuffer();
	        String tempString = null;
	        
	        // 一次读入一行，直到读入null为文件结束
	        List<String> ignore = Arrays.asList(new String[] {"import java.util.List;","import org.apache.ibatis.annotations.Param;"});
	        
            while ((tempString = orignFile.readLine()) != null) {
            	if(ignore.contains(tempString))
            		continue;
            	if(ObjectUtilms.isNotEmpty(tempString) && tempString.startsWith("public interface ")) {
                    newFile.append("import tp.ms.common.data.mybatis.annotation.SqlSessionKey;");
            		newFile.append("\r\n");
                    newFile.append("import tp.ms.common.data.mybatis.annotation.TargetSqlSession;");
            		newFile.append("\r\n");
            		newFile.append("\r\n");
                    newFile.append("@TargetSqlSession(SqlSessionKey.CS6304)");
            		newFile.append("\r\n");
            		tempString = tempString.replaceAll("DaoMapper", "DaoMapper<"+className+", "+className+"Example>");
                    newFile.append(tempString);
            		newFile.append("\r\n");
            		newFile.append("\r\n");
            		newFile.append("}");
            		break;
            	}
                newFile.append(tempString);
        		newFile.append("\r\n");
            }

            fw = new FileWriter(new File(target));
            fw.write(newFile.toString());

        }catch (Exception e) {
			throw new ADBusinessException(e);
		}finally {
			try {
				if(orignFile != null) 
					orignFile.close();
			} catch (IOException e) {
				throw new ADBusinessException(e);
			}
			if(fw != null) {
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					throw new ADBusinessException(e);
				}
			}
		}
	}
	

}
