package tp.ms.base.rest.refinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tp.ms.base.rest.refinfo.vo.BaseRefInfoVO;
import tp.ms.base.rest.refinfo.vo.MyBaseRefInfo;
import tp.ms.base.rest.refinfo.vo.MyBaseRefInfoExample;
import tp.ms.base.rest.refinfo.vo.RefInfoQueryVO;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

public interface MsBaseRefInfoMapper extends DaoMapper<MyBaseRefInfo, MyBaseRefInfoExample> {

	@Select("SELECT ${code} code, ${name} name, ${pk} pkid FROM ${table} WHERE ${condition}; ")
	List<BaseRefInfoVO> selectByTranslate(@Param("code") String code, @Param("name")String name, @Param("pk")String pk, @Param("table")String table, @Param("condition")String condition);

	@Select("SELECT ${code} code, ${name} name, ${pk} id FROM ${table} WHERE ${condition}; ")
	List<BaseRefInfoVO> executeByTranslateQuery(RefInfoQueryVO queryVO);
	
}