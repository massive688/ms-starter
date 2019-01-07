package com.ms.workable.flow.modeler.test.core;

import org.apache.catalina.Wrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Entity 瀵硅薄灏佽鎿嶄綔绫伙紝瀹氫箟T-SQL璇硶
 * </p>
 *
 * @author hubin , yanghu , Dyang , Caratacus
 * @Date 2016-11-7
 */
@SuppressWarnings("serial")
public class EntityWrapper<T> {

    /**
     * 鏁版嵁搴撹〃鏄犲皠瀹炰綋绫�
     */
    protected T entity = null;
	private String sqlSelect;

    public EntityWrapper() {
        /* 娉ㄦ剰锛屼紶鍏ユ煡璇㈠弬鏁� */
    }

    public EntityWrapper(T entity) {
        this.entity = entity;
    }

    public EntityWrapper(T entity, String sqlSelect) {
        this.entity = entity;
        this.sqlSelect = sqlSelect;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    /**
     * SQL 鐗囨
     */
    public String getSqlSegment() {
        /*
         * 鏃犳潯浠�
		 */
        String sqlWhere = null;//sql.toString();
        if (StringUtils.isEmpty(sqlWhere)) {
            return null;
        }

        /*
         * 鏍规嵁褰撳墠瀹炰綋鍒ゆ柇鏄惁闇�瑕佸皢WHERE鏇挎崲鎴� AND 澧炲姞瀹炰綋涓嶄负绌轰絾鎵�鏈夊睘鎬т负绌虹殑鎯呭喌
		 */
        return null;//isWhere != null ? (isWhere ? sqlWhere : sqlWhere.replaceFirst("WHERE", AND_OR)) : sqlWhere.replaceFirst("WHERE", AND_OR);
    }

	public void eq(String string, Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Wrapper eq(String string, String processInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
