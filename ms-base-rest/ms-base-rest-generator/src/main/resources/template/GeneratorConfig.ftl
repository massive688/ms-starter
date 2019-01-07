<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">  
<generatorConfiguration>  
	<!-- https://www.jianshu.com/p/e09d2370b796 Mybatis Generator最完整配置详解 -->
    <context id="testTables1" targetRuntime="MyBatis3">  <!-- MyBatis3Simple -->
    	
    	<plugin type="org.mybatis.generator.plugins.CaseInsensitiveLikePlugin"></plugin>  
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"></plugin>  
        <!-- Pagination  
        <plugin type="com.my.web.generator.dao.plugin.SqlserverPaginationPlugin"></plugin> 
        --> 
        <commentGenerator>  
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->  
            <property name="suppressAllComments" value="true" />  
        </commentGenerator>  
        <!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->  
        <!--   
        <jdbcConnection driverClass="oracle.jdbc.driver.OracleDriver"  
            connectionURL="jdbc:oracle:thin:@150.16.17.22:1521/wsbs" userId="hr"  
            password="hr123">  
        </jdbcConnection>-->         
        <jdbcConnection driverClass="${driverClass}"  
            connectionURL="${connUrl}"   
            userId="${user}"                                        
            password="${password}">  
        </jdbcConnection>   
  
        <!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL 和   
            NUMERIC 类型解析为java.math.BigDecimal -->  
        <javaTypeResolver>  
            <property name="forceBigDecimals" value="false" />  
        </javaTypeResolver>  
  
        <!-- targetProject:生成PO类的位置 -->     
        <javaModelGenerator targetPackage="com.${packageName}.vo"  
            targetProject="src/main/java">  
            <!-- enableSubPackages:是否让schema作为包的后缀 -->  
            <property name="enableSubPackages" value="false" />
            <!-- 设置一个根对象，  
		            如果设置了这个根对象，那么生成的keyClass或者recordClass会继承这个类；在Table的rootClass属性中可以覆盖该选项  
		            注意：如果在key class或者record class中有root class相同的属性，MBG就不会重新生成这些属性了，包括：  
		                1，属性名相同，类型相同，有相同的getter/setter方法；  
		         -->  									
		    <property name="rootClass" value="${extendClass}"/>	
            <!-- 从数据库返回的值被清理前后的空格 -->  
            <property name="trimStrings" value="true" />  
        </javaModelGenerator>  
        <!-- targetProject:mapper映射文件生成的位置 -->  
        <sqlMapGenerator targetPackage="com.${packageName}.mapper"   
         targetProject="src/main/java"> 
             <!-- enableSubPackages:是否让schema作为包的后缀 -->  
            <property name="enableSubPackages" value="false" />  
            
        </sqlMapGenerator>  
        <!-- targetPackage：mapper接口生成的位置 -->  
        <javaClientGenerator type="XMLMAPPER"  
            targetPackage="com.${packageName}.mapper"     
            targetProject="src/main/java">  
            <!-- enableSubPackages:是否让schema作为包的后缀 -->  
            <property name="enableSubPackages" value="false" />
            <!-- 可以为所有生成的接口添加一个父接口，但是MBG只负责生成，不负责检查  
	        <property name="rootInterface" value=""/>  
	         -->  
            
            <property name="rootInterface" value="${mapperBase}"/>
        </javaClientGenerator>  
        <!-- 指定数据库表 -->  
        <!--<table tableName="items"></table> -->   
        <table tableName="${tableName}" domainObjectName="${domainObjectName}">
	        <!-- 用来修改表中某个列的属性，MBG会使用修改后的列来生成domain的属性；  
	             column:要重新设置的列名；  
	             注意,一个table元素中可以有多个columnOverride元素哈~  
	          -->
	        <columnOverride column="approve_status"  property="approveStatus" javaType="java.lang.Integer" jdbcType="SMALLINT">  
	             <!-- 使用property属性来指定列要生成的属性名称 -->  
	             <property name="property" value="approveStatus"/>  
	  
	             <!-- javaType用于指定生成的domain的属性类型，使用类型的全限定名  --> 
	             <property name="javaType" value="com.ms.core.service.rest.common.vo.status.ApproveStatus"/>  
	               
	             <!-- jdbcType用于指定该列的JDBC类型  -->
	             <property name="jdbcType" value="INTEGER"/>  	               	 	         
	  
	             <!-- 参考table元素的delimitAllColumns配置，默认为false  
	             <property name="delimitedColumnName" value=""/>  
	              -->  
	         </columnOverride>
	         
	        <columnOverride column="bill_status" property="billStatus" javaType="java.lang.Integer" jdbcType="SMALLINT">  
	             <property name="property" value="billStatus"/>  
	             <property name="javaType" value="tp.ms.base.rest.resource.vo.BillStatus"/>	  
	             <property name="jdbcType" value="INTEGER"/>  
	         </columnOverride>
	        <columnOverride column="enabled" property="enabled" javaType="java.lang.Integer" jdbcType="SMALLINT" >  
	             <property name="property" value="enabled"/>  
	             <property name="javaType" value="java.lang.Integer"/>  
	             <property name="jdbcType" value="INTEGER"/>  
	        </columnOverride>
	        <columnOverride column="dr" property="dr" javaType="java.lang.Integer" jdbcType="SMALLINT">  
	             <property name="property" value="dr"/>  
	             <property name="javaType" value="java.lang.Integer"/>	  
	             <property name="jdbcType" value="INTEGER"/>  
	         </columnOverride>

        </table>  
    </context>  
</generatorConfiguration>
