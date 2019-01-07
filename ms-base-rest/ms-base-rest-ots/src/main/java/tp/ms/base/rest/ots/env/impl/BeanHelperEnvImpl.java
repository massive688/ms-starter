//package tp.ms.base.rest.ots.env.impl;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//import lombok.extern.slf4j.Slf4j;
//import tp.ms.base.rest.ots.orgs.api.OrgsService;
//import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelements;
//import tp.ms.base.rest.typecoded.api.BillCodeService;
//import tp.ms.base.rest.typecoded.id.OidGenerator;
//import tp.ms.common.bean.exception.ADBusinessException;
//import tp.ms.common.bean.support.context.BeanHelperEnv;
//import tp.ms.common.bean.support.context.MsEnvContextHolder;
//
//@Slf4j
//@Component
//public class BeanHelperEnvImpl implements BeanHelperEnv {
//
//	public final  String INITCODE_FIX = "#A$A#";
//	
//
//	public void test() throws ADBusinessException {
//		log.info("param: {}","100012AINECK00000000135MC");
//		String[]  codes = generateBatchCodes("100012AINECK00000000135MC",10);
//
//		log.debug(codes[1]);
//		if(Boolean.valueOf("true")) return ;
//		String rootPath = System.getProperty("catalina.base")==null? System.getProperty("user.dir"):System.getProperty("catalina.base") ;
//		String dirPath = rootPath + File.separator + "bin" + File.separator ;
//		String filePath = dirPath + "idstorage.ca";
//		try {
//			FileInputStream is = new FileInputStream(filePath);
//			byte[] buff = new byte[is.available()];
//			is.read(buff, 0, is.available());
//			System.out.println(new String(buff));
//			System.out.println(is);
//			System.out.println(new String(buff));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for(int i=0; i < 10; i++) {
////			System.out.println( generateid());
//			
//		}
//	}
//
//	public  String generateid() {
//		return generateid(1)[0];
//	}
//	
//	@Autowired
//	OrgsService orgsService;
//	
//	public  String generateid(String pkCorpCode) {
//		MyAdreamOrgelements element = null;
//		try {
//			element = orgsService.queryByKey(pkCorpCode);
//		} catch (ADBusinessException e) {
//		}
//		return generateid(element.getCode(), 1)[0];
//	}
//	
//	public  String[] generateid(int num) {
//		return generateid("000001", num);
//	}
//	
//	public  String[] generateid(String pkCorpCode, int num){
//		checkOrg(pkCorpCode);
//		/**
//		 * 生成唯一性key
//		 * @param num	传入需要生成的个数
//		 * @return 
//		 */
//		String ids[] = new String[num]; 
//		for(int i=0; i < ids.length; i++){
//			ids[i] = OidGenerator.instance().nextGidBase(pkCorpCode);
//		}
//		return ids;
//	}
//
//
//	private void checkOrg(String pkCorpCode) {
//		Assert.notNull(pkCorpCode, " 组织IDCODE不可以空" );
//		if(pkCorpCode.length() != 6) 
//			throw new IllegalArgumentException(" 组织IDCODE相应的格式错误");
//	}
//
//	/****************主键生成部分结束*************************************************************/	
//	
//	@Autowired
//	OrgsService service;
//	
//	public  String generateBatchCodes(String pkBilltype) throws ADBusinessException {
//		pkBilltype = pkBilltype == null ? "100012AINECK00000000135MC" : pkBilltype;
//		return generateBatchCodes(pkBilltype, 1)[0];
//	}
//	
//	public  String[] generateBatchCodes(String pkBilltype, int num) throws ADBusinessException {
//		MyAdreamOrgelements orgVO = service.queryByKey(MsEnvContextHolder.getContext().user().getPkOrg());
//		return generateBatchCodes(pkBilltype, orgVO.getCode(), num);
//	}
//	
//	@Autowired
//	BillCodeService billCodeService;
//	
//	public  String[] generateBatchCodes(String pkBilltype, String pkCorp, int num) throws ADBusinessException {
//		return billCodeService.generateBatchCodes(pkBilltype, pkCorp, num);
//	}
//	
//	
//}
