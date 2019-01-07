package tp.ms.base.rest.formula.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tp.ms.base.rest.formula.vo.FormulaObject;

@Mapper
//@CacheNamespace(implementation=MsMybatisRedisCache.class)
public interface FormulaValueDaoMapper {

	@Select("SELECT ${fieldCode} FROM ${table} WHERE ${where}")
	String queryByFormula(FormulaObject param);
}
