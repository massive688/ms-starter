package tp.ms.common.data.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tp.ms.common.bean.vo.RefBaseVO;

public interface RefMapper<T extends RefBaseVO> {

	List<T> selectByReferenceCondition(@Param("condition") String condition);
}
