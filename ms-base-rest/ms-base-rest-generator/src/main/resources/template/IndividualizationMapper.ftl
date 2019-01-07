package com.${packageName}.mapper.individualization;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;

@Mapper
@TargetSqlSession(SqlSessionKey.CS6304)
public interface ${pkSuffix}IndividualizationMapper {
	
	@Select("")
	List<Object> getListInfos();

}
