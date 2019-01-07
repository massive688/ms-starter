package tp.ms.base.rest.ots.staff.entity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;

@TargetSqlSession(SqlSessionKey.CS6304)
public interface MyAdreamStaffMapper {
	
    long countByExample(MyAdreamStaffExample example);

    int deleteByExample(MyAdreamStaffExample example);

    int deleteByPrimaryKey(String pkUser);

    int insert(MyAdreamStaff record);

    int insertSelective(MyAdreamStaff record);

    List<MyAdreamStaff> selectByExample(MyAdreamStaffExample example);

    MyAdreamStaff selectByPrimaryKey(String pkUser);

    int updateByExampleSelective(@Param("record") MyAdreamStaff record, @Param("example") MyAdreamStaffExample example);

    int updateByExample(@Param("record") MyAdreamStaff record, @Param("example") MyAdreamStaffExample example);

    int updateByPrimaryKeySelective(MyAdreamStaff record);

    int updateByPrimaryKey(MyAdreamStaff record);
}