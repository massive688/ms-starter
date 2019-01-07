package tp.ms.base.rest.typecoded.vo;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MyBillcodereturnExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MyBillcodereturnExample() {
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

        public Criteria andPkBillcoderulebaseIsNull() {
            addCriterion("pk_billcoderulebase is null");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseIsNotNull() {
            addCriterion("pk_billcoderulebase is not null");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseEqualTo(String value) {
            addCriterion("pk_billcoderulebase =", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseNotEqualTo(String value) {
            addCriterion("pk_billcoderulebase <>", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseGreaterThan(String value) {
            addCriterion("pk_billcoderulebase >", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseGreaterThanOrEqualTo(String value) {
            addCriterion("pk_billcoderulebase >=", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseLessThan(String value) {
            addCriterion("pk_billcoderulebase <", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseLessThanOrEqualTo(String value) {
            addCriterion("pk_billcoderulebase <=", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseLike(String value) {
            addCriterion("pk_billcoderulebase like", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseNotLike(String value) {
            addCriterion("pk_billcoderulebase not like", value, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseIn(List<String> values) {
            addCriterion("pk_billcoderulebase in", values, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseNotIn(List<String> values) {
            addCriterion("pk_billcoderulebase not in", values, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseBetween(String value1, String value2) {
            addCriterion("pk_billcoderulebase between", value1, value2, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcoderulebaseNotBetween(String value1, String value2) {
            addCriterion("pk_billcoderulebase not between", value1, value2, "pkBillcoderulebase");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnIsNull() {
            addCriterion("pk_billcodereturn is null");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnIsNotNull() {
            addCriterion("pk_billcodereturn is not null");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnEqualTo(String value) {
            addCriterion("pk_billcodereturn =", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnNotEqualTo(String value) {
            addCriterion("pk_billcodereturn <>", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnGreaterThan(String value) {
            addCriterion("pk_billcodereturn >", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnGreaterThanOrEqualTo(String value) {
            addCriterion("pk_billcodereturn >=", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnLessThan(String value) {
            addCriterion("pk_billcodereturn <", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnLessThanOrEqualTo(String value) {
            addCriterion("pk_billcodereturn <=", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnLike(String value) {
            addCriterion("pk_billcodereturn like", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnNotLike(String value) {
            addCriterion("pk_billcodereturn not like", value, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnIn(List<String> values) {
            addCriterion("pk_billcodereturn in", values, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnNotIn(List<String> values) {
            addCriterion("pk_billcodereturn not in", values, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnBetween(String value1, String value2) {
            addCriterion("pk_billcodereturn between", value1, value2, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andPkBillcodereturnNotBetween(String value1, String value2) {
            addCriterion("pk_billcodereturn not between", value1, value2, "pkBillcodereturn");
            return (Criteria) this;
        }

        public Criteria andMarkstrIsNull() {
            addCriterion("markstr is null");
            return (Criteria) this;
        }

        public Criteria andMarkstrIsNotNull() {
            addCriterion("markstr is not null");
            return (Criteria) this;
        }

        public Criteria andMarkstrEqualTo(String value) {
            addCriterion("markstr =", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrNotEqualTo(String value) {
            addCriterion("markstr <>", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrGreaterThan(String value) {
            addCriterion("markstr >", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrGreaterThanOrEqualTo(String value) {
            addCriterion("markstr >=", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrLessThan(String value) {
            addCriterion("markstr <", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrLessThanOrEqualTo(String value) {
            addCriterion("markstr <=", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrLike(String value) {
            addCriterion("markstr like", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrNotLike(String value) {
            addCriterion("markstr not like", value, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrIn(List<String> values) {
            addCriterion("markstr in", values, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrNotIn(List<String> values) {
            addCriterion("markstr not in", values, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrBetween(String value1, String value2) {
            addCriterion("markstr between", value1, value2, "markstr");
            return (Criteria) this;
        }

        public Criteria andMarkstrNotBetween(String value1, String value2) {
            addCriterion("markstr not between", value1, value2, "markstr");
            return (Criteria) this;
        }

        public Criteria andRtsnsIsNull() {
            addCriterion("rtsns is null");
            return (Criteria) this;
        }

        public Criteria andRtsnsIsNotNull() {
            addCriterion("rtsns is not null");
            return (Criteria) this;
        }

        public Criteria andRtsnsEqualTo(String value) {
            addCriterion("rtsns =", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsNotEqualTo(String value) {
            addCriterion("rtsns <>", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsGreaterThan(String value) {
            addCriterion("rtsns >", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsGreaterThanOrEqualTo(String value) {
            addCriterion("rtsns >=", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsLessThan(String value) {
            addCriterion("rtsns <", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsLessThanOrEqualTo(String value) {
            addCriterion("rtsns <=", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsLike(String value) {
            addCriterion("rtsns like", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsNotLike(String value) {
            addCriterion("rtsns not like", value, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsIn(List<String> values) {
            addCriterion("rtsns in", values, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsNotIn(List<String> values) {
            addCriterion("rtsns not in", values, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsBetween(String value1, String value2) {
            addCriterion("rtsns between", value1, value2, "rtsns");
            return (Criteria) this;
        }

        public Criteria andRtsnsNotBetween(String value1, String value2) {
            addCriterion("rtsns not between", value1, value2, "rtsns");
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