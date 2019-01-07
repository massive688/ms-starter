package tp.ms.base.rest.resource.vo;

public interface IAuditInfo {


	  // 创建人
	  public static final String CREATOR = "creator";
	  
	  // 创建时间
	  public static final String CREATIONTIME = "creationtime";
	  
	  // 最后修改时间
	  public static final String MODIFIEDTIME = "modifiedtime";

	  // 最后修改人
	  public static final String MODIFIER = "modifier";

	  // 制单人
	//  public static final String OPERATOR = "operator";
	  
	  // 制单时间
	//  public static final String BILLMAKERTIME = "billmakertime";
	  
	  /*
	   * 制单时间
	   */
	//  UFDateTime getBillMakerTime();

	  /*
	   * 创建时间
	   */
	  String getCreationtime();

	  /*
	   * 创建人
	   */
	  String getCreator();

	  /*
	   * 最后修改时间
	   */
	  String getModifiedtime();

	  /*
	   * 最后修改人
	   */
	  String getModifier();

	  /*
	   * 制单人
	   */
	//  String getOperator();
}
