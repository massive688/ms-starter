package tp.ms.base.rest.ots.staff.entity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelation;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;

@TargetSqlSession(SqlSessionKey.CS6304)
public interface MyAdreamStaffRelationMapper {
	
    long countByExample(MyAdreamStaffRelationExample example);

    int deleteByExample(MyAdreamStaffRelationExample example);

    int insert(MyAdreamStaffRelation record);

    int insertSelective(MyAdreamStaffRelation record);

    List<MyAdreamStaffRelation> selectByExample(MyAdreamStaffRelationExample example);

    int updateByExampleSelective(@Param("record") MyAdreamStaffRelation record, @Param("example") MyAdreamStaffRelationExample example);

    int updateByExample(@Param("record") MyAdreamStaffRelation record, @Param("example") MyAdreamStaffRelationExample example);
}