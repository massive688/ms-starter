package tp.ms.base.rest.process.vo;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MsWorkableFlowProcessInformationExample  extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MsWorkableFlowProcessInformationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPkProcessInformationIsNull() {
            addCriterion("pk_process_information is null");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationIsNotNull() {
            addCriterion("pk_process_information is not null");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationEqualTo(String value) {
            addCriterion("pk_process_information =", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationNotEqualTo(String value) {
            addCriterion("pk_process_information <>", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationGreaterThan(String value) {
            addCriterion("pk_process_information >", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationGreaterThanOrEqualTo(String value) {
            addCriterion("pk_process_information >=", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationLessThan(String value) {
            addCriterion("pk_process_information <", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationLessThanOrEqualTo(String value) {
            addCriterion("pk_process_information <=", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationLike(String value) {
            addCriterion("pk_process_information like", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationNotLike(String value) {
            addCriterion("pk_process_information not like", value, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationIn(List<String> values) {
            addCriterion("pk_process_information in", values, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationNotIn(List<String> values) {
            addCriterion("pk_process_information not in", values, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationBetween(String value1, String value2) {
            addCriterion("pk_process_information between", value1, value2, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationNotBetween(String value1, String value2) {
            addCriterion("pk_process_information not between", value1, value2, "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andApproveTimeIsNull() {
            addCriterion("approve_time is null");
            return (Criteria) this;
        }

        public Criteria andApproveTimeIsNotNull() {
            addCriterion("approve_time is not null");
            return (Criteria) this;
        }

        public Criteria andApproveTimeEqualTo(String value) {
            addCriterion("approve_time =", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeNotEqualTo(String value) {
            addCriterion("approve_time <>", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeGreaterThan(String value) {
            addCriterion("approve_time >", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("approve_time >=", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeLessThan(String value) {
            addCriterion("approve_time <", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeLessThanOrEqualTo(String value) {
            addCriterion("approve_time <=", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeLike(String value) {
            addCriterion("approve_time like", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeNotLike(String value) {
            addCriterion("approve_time not like", value, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeIn(List<String> values) {
            addCriterion("approve_time in", values, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeNotIn(List<String> values) {
            addCriterion("approve_time not in", values, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeBetween(String value1, String value2) {
            addCriterion("approve_time between", value1, value2, "approveTime");
            return (Criteria) this;
        }

        public Criteria andApproveTimeNotBetween(String value1, String value2) {
            addCriterion("approve_time not between", value1, value2, "approveTime");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNull() {
            addCriterion("node_name is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("node_name is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("node_name =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("node_name <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("node_name >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("node_name >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("node_name <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("node_name <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("node_name like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("node_name not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("node_name in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("node_name not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("node_name between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("node_name not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperationIsNull() {
            addCriterion("operation is null");
            return (Criteria) this;
        }

        public Criteria andOperationIsNotNull() {
            addCriterion("operation is not null");
            return (Criteria) this;
        }

        public Criteria andOperationEqualTo(String value) {
            addCriterion("operation =", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotEqualTo(String value) {
            addCriterion("operation <>", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThan(String value) {
            addCriterion("operation >", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThanOrEqualTo(String value) {
            addCriterion("operation >=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThan(String value) {
            addCriterion("operation <", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThanOrEqualTo(String value) {
            addCriterion("operation <=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLike(String value) {
            addCriterion("operation like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotLike(String value) {
            addCriterion("operation not like", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationIn(List<String> values) {
            addCriterion("operation in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotIn(List<String> values) {
            addCriterion("operation not in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationBetween(String value1, String value2) {
            addCriterion("operation between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotBetween(String value1, String value2) {
            addCriterion("operation not between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsIsNull() {
            addCriterion("handling_opinions is null");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsIsNotNull() {
            addCriterion("handling_opinions is not null");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsEqualTo(String value) {
            addCriterion("handling_opinions =", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsNotEqualTo(String value) {
            addCriterion("handling_opinions <>", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsGreaterThan(String value) {
            addCriterion("handling_opinions >", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsGreaterThanOrEqualTo(String value) {
            addCriterion("handling_opinions >=", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsLessThan(String value) {
            addCriterion("handling_opinions <", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsLessThanOrEqualTo(String value) {
            addCriterion("handling_opinions <=", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsLike(String value) {
            addCriterion("handling_opinions like", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsNotLike(String value) {
            addCriterion("handling_opinions not like", value, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsIn(List<String> values) {
            addCriterion("handling_opinions in", values, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsNotIn(List<String> values) {
            addCriterion("handling_opinions not in", values, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsBetween(String value1, String value2) {
            addCriterion("handling_opinions between", value1, value2, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsNotBetween(String value1, String value2) {
            addCriterion("handling_opinions not between", value1, value2, "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNull() {
            addCriterion("process_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNotNull() {
            addCriterion("process_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessIdEqualTo(String value) {
            addCriterion("process_id =", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotEqualTo(String value) {
            addCriterion("process_id <>", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThan(String value) {
            addCriterion("process_id >", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_id >=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThan(String value) {
            addCriterion("process_id <", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThanOrEqualTo(String value) {
            addCriterion("process_id <=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLike(String value) {
            addCriterion("process_id like", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotLike(String value) {
            addCriterion("process_id not like", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdIn(List<String> values) {
            addCriterion("process_id in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotIn(List<String> values) {
            addCriterion("process_id not in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdBetween(String value1, String value2) {
            addCriterion("process_id between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotBetween(String value1, String value2) {
            addCriterion("process_id not between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeIsNull() {
            addCriterion("is_current_node is null");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeIsNotNull() {
            addCriterion("is_current_node is not null");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeEqualTo(String value) {
            addCriterion("is_current_node =", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeNotEqualTo(String value) {
            addCriterion("is_current_node <>", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeGreaterThan(String value) {
            addCriterion("is_current_node >", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeGreaterThanOrEqualTo(String value) {
            addCriterion("is_current_node >=", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeLessThan(String value) {
            addCriterion("is_current_node <", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeLessThanOrEqualTo(String value) {
            addCriterion("is_current_node <=", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeLike(String value) {
            addCriterion("is_current_node like", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeNotLike(String value) {
            addCriterion("is_current_node not like", value, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeIn(List<String> values) {
            addCriterion("is_current_node in", values, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeNotIn(List<String> values) {
            addCriterion("is_current_node not in", values, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeBetween(String value1, String value2) {
            addCriterion("is_current_node between", value1, value2, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeNotBetween(String value1, String value2) {
            addCriterion("is_current_node not between", value1, value2, "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andOperatorIDIsNull() {
            addCriterion("operator_i_d is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIDIsNotNull() {
            addCriterion("operator_i_d is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIDEqualTo(String value) {
            addCriterion("operator_i_d =", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDNotEqualTo(String value) {
            addCriterion("operator_i_d <>", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDGreaterThan(String value) {
            addCriterion("operator_i_d >", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDGreaterThanOrEqualTo(String value) {
            addCriterion("operator_i_d >=", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDLessThan(String value) {
            addCriterion("operator_i_d <", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDLessThanOrEqualTo(String value) {
            addCriterion("operator_i_d <=", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDLike(String value) {
            addCriterion("operator_i_d like", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDNotLike(String value) {
            addCriterion("operator_i_d not like", value, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDIn(List<String> values) {
            addCriterion("operator_i_d in", values, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDNotIn(List<String> values) {
            addCriterion("operator_i_d not in", values, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDBetween(String value1, String value2) {
            addCriterion("operator_i_d between", value1, value2, "operatorID");
            return (Criteria) this;
        }

        public Criteria andOperatorIDNotBetween(String value1, String value2) {
            addCriterion("operator_i_d not between", value1, value2, "operatorID");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("ts is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("ts is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(String value) {
            addCriterion("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(String value) {
            addCriterion("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(String value) {
            addCriterion("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(String value) {
            addCriterion("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(String value) {
            addCriterion("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(String value) {
            addCriterion("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLike(String value) {
            addCriterion("ts like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotLike(String value) {
            addCriterion("ts not like", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<String> values) {
            addCriterion("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<String> values) {
            addCriterion("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(String value1, String value2) {
            addCriterion("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(String value1, String value2) {
            addCriterion("ts not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andDrIsNull() {
            addCriterion("dr is null");
            return (Criteria) this;
        }

        public Criteria andDrIsNotNull() {
            addCriterion("dr is not null");
            return (Criteria) this;
        }

        public Criteria andDrEqualTo(Integer value) {
            addCriterion("dr =", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotEqualTo(Integer value) {
            addCriterion("dr <>", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThan(Integer value) {
            addCriterion("dr >", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThanOrEqualTo(Integer value) {
            addCriterion("dr >=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThan(Integer value) {
            addCriterion("dr <", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThanOrEqualTo(Integer value) {
            addCriterion("dr <=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrIn(List<Integer> values) {
            addCriterion("dr in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotIn(List<Integer> values) {
            addCriterion("dr not in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrBetween(Integer value1, Integer value2) {
            addCriterion("dr between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotBetween(Integer value1, Integer value2) {
            addCriterion("dr not between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andPkProcessInformationLikeInsensitive(String value) {
            addCriterion("upper(pk_process_information) like", value.toUpperCase(), "pkProcessInformation");
            return (Criteria) this;
        }

        public Criteria andApproveTimeLikeInsensitive(String value) {
            addCriterion("upper(approve_time) like", value.toUpperCase(), "approveTime");
            return (Criteria) this;
        }

        public Criteria andNodeNameLikeInsensitive(String value) {
            addCriterion("upper(node_name) like", value.toUpperCase(), "nodeName");
            return (Criteria) this;
        }

        public Criteria andOperatorLikeInsensitive(String value) {
            addCriterion("upper(operator) like", value.toUpperCase(), "operator");
            return (Criteria) this;
        }

        public Criteria andOperationLikeInsensitive(String value) {
            addCriterion("upper(operation) like", value.toUpperCase(), "operation");
            return (Criteria) this;
        }

        public Criteria andHandlingOpinionsLikeInsensitive(String value) {
            addCriterion("upper(handling_opinions) like", value.toUpperCase(), "handlingOpinions");
            return (Criteria) this;
        }

        public Criteria andProcessIdLikeInsensitive(String value) {
            addCriterion("upper(process_id) like", value.toUpperCase(), "processId");
            return (Criteria) this;
        }

        public Criteria andIsCurrentNodeLikeInsensitive(String value) {
            addCriterion("upper(is_current_node) like", value.toUpperCase(), "isCurrentNode");
            return (Criteria) this;
        }

        public Criteria andTaskIdLikeInsensitive(String value) {
            addCriterion("upper(task_id) like", value.toUpperCase(), "taskId");
            return (Criteria) this;
        }

        public Criteria andOperatorIDLikeInsensitive(String value) {
            addCriterion("upper(operator_i_d) like", value.toUpperCase(), "operatorID");
            return (Criteria) this;
        }

        public Criteria andTsLikeInsensitive(String value) {
            addCriterion("upper(ts) like", value.toUpperCase(), "ts");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
