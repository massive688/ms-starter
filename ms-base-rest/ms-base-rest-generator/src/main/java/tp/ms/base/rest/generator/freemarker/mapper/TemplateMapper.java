package tp.ms.base.rest.generator.freemarker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;

@Repository
@TargetSqlSession(SqlSessionKey.CS6304)
public interface TemplateMapper {
	/**
	 * 找到要插入的列
	 * 
	 * @param list
	 * @return
	 */
	Integer findTable(@Param("table") String table);


	List<String> columns(String tableName);
	
	int updateTableField(@Param("uSql") String uSql);
}
