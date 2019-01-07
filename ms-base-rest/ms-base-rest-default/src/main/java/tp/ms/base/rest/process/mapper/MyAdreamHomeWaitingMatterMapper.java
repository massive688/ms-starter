package tp.ms.base.rest.process.mapper;

import java.util.List;

import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatter;
import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatterExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@TargetSqlSession(SqlSessionKey.CS6304)
public interface MyAdreamHomeWaitingMatterMapper extends DaoMapper<MyAdreamHomeWaitingMatter, MyAdreamHomeWaitingMatterExample> {


	List<MyAdreamHomeWaitingMatter> selectProcessMatter(String currentPkUser);

}