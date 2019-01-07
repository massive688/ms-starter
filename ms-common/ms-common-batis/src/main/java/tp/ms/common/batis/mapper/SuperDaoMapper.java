package tp.ms.common.batis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;


@Repository
public interface SuperDaoMapper<T extends BaseVO, E extends BaseExample> {
	/**
	 * 下面是生成的部分
	 * @param example
	 * @return
	 */
	long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(String key);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
	
}
