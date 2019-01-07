package tp.ms.base.rest.generator.generationcode.bean;

import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

public class FreemarkerConstant {
	public final static String USERNAME = "sa" ;
	public final static String PASSWORD = "e9wkYRHE" ;
	public final static String JDBCURL = "jdbc:jtds:sqlserver://101.226.249.46:1433;DatabaseName=CS6305" ;
	public final static String DRTVERCLASS = "net.sourceforge.jtds.jdbc.Driver" ;
	//public final static String PACKAGENAME ="reg.gs.ge";//包名
	//public final static String MODULENAME ="test";//模块名
	/**
	 * 生成实体类和Mapper的位置
	 */
	public final static String VOPACKAGENAME ="com.vo";
	public final static String MAPPERXMLPACKAGENAME ="com.mapper";
	public final static String MAPPERPACKAGENAME ="com.mapper";
	/**
	 * 继承的主子表与基mapper
	 */
	public final static String MAJORBASE = MajorBaseVO.class.getName();
	public final static String CHILDBASE = ChildBaseVO.class.getName();
	public final static String AUDITBASE = MajorAuditBaseVO.class.getName();
	public final static String MAPPERBASE = DaoMapper.class.getName();
	/**
	 * 主子与继承表的包名							
	 */
	public final static String MAJORPACKAGENAME="com.test.reg.gs.ge";
	public final static String CHIlDPACKAGENAME="com.test.reg.gs.ge";
	public final static String AUDITPACKAGENAME="com.test.reg.gs.ge";
	/**
	 * controlle、service、serviceimpl生成的路径
	 */
	public final static String POLYCONTROLLERPATH = ".\\src\\main\\java\\com\\rest\\poly\\";
	public final static String POLYSERVICEPATH = ".\\src\\main\\java\\com\\service\\poly\\";
	public final static String POLYSERVICEIMPLPATH = ".\\src\\main\\java\\com\\service\\poly\\impl\\";
	public final static String POLYVOPATH = ".\\src\\main\\java\\com\\vo\\";
	/**
	 * controlle、service、serviceimpl生成的路径
	 */
	public final static String MAJORCONTROLLERPATH = ".\\src\\main\\java\\com\\rest\\major\\";
	public final static String MAJORSERVICEPATH = ".\\src\\main\\java\\com\\service\\major\\";
	public final static String MAJORSERVICEIMPLPATH = ".\\src\\main\\java\\com\\service\\major\\impl\\";
	/**
	 * controlle、service、serviceimpl生成的路径
	 */
	public final static String CHILDCONTROLLERPATH = ".\\src\\main\\java\\com\\rest\\child\\";
	public final static String CHILDSERVICEPATH = ".\\src\\main\\java\\com\\service\\child\\";
	public final static String CHILDSERVICEIMPLPATH = ".\\src\\main\\java\\com\\service\\child\\impl\\";
	
	public final static String[]  majorlist = {"pk_corp","pk_group","creator","creationtime","modifier","modifiedtime","billtype","enabled"};
			
			
}		
