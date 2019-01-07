package tp.ms.base.rest.ots.staff.entity;

import java.util.ArrayList;
import java.util.List;

public class MyAdreamStaffRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MyAdreamStaffRelationExample() {
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

        public Criteria andPkUserIsNull() {
            addCriterion("pk_user is null");
            return (Criteria) this;
        }

        public Criteria andPkUserIsNotNull() {
            addCriterion("pk_user is not null");
            return (Criteria) this;
        }

        public Criteria andPkUserEqualTo(String value) {
            addCriterion("pk_user =", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserNotEqualTo(String value) {
            addCriterion("pk_user <>", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserGreaterThan(String value) {
            addCriterion("pk_user >", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserGreaterThanOrEqualTo(String value) {
            addCriterion("pk_user >=", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserLessThan(String value) {
            addCriterion("pk_user <", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserLessThanOrEqualTo(String value) {
            addCriterion("pk_user <=", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserLike(String value) {
            addCriterion("pk_user like", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserNotLike(String value) {
            addCriterion("pk_user not like", value, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserIn(List<String> values) {
            addCriterion("pk_user in", values, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserNotIn(List<String> values) {
            addCriterion("pk_user not in", values, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserBetween(String value1, String value2) {
            addCriterion("pk_user between", value1, value2, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkUserNotBetween(String value1, String value2) {
            addCriterion("pk_user not between", value1, value2, "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkPostIsNull() {
            addCriterion("pk_post is null");
            return (Criteria) this;
        }

        public Criteria andPkPostIsNotNull() {
            addCriterion("pk_post is not null");
            return (Criteria) this;
        }

        public Criteria andPkPostEqualTo(String value) {
            addCriterion("pk_post =", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostNotEqualTo(String value) {
            addCriterion("pk_post <>", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostGreaterThan(String value) {
            addCriterion("pk_post >", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostGreaterThanOrEqualTo(String value) {
            addCriterion("pk_post >=", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostLessThan(String value) {
            addCriterion("pk_post <", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostLessThanOrEqualTo(String value) {
            addCriterion("pk_post <=", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostLike(String value) {
            addCriterion("pk_post like", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostNotLike(String value) {
            addCriterion("pk_post not like", value, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostIn(List<String> values) {
            addCriterion("pk_post in", values, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostNotIn(List<String> values) {
            addCriterion("pk_post not in", values, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostBetween(String value1, String value2) {
            addCriterion("pk_post between", value1, value2, "pkPost");
            return (Criteria) this;
        }

        public Criteria andPkPostNotBetween(String value1, String value2) {
            addCriterion("pk_post not between", value1, value2, "pkPost");
            return (Criteria) this;
        }

        public Criteria andIsMainIsNull() {
            addCriterion("is_main is null");
            return (Criteria) this;
        }

        public Criteria andIsMainIsNotNull() {
            addCriterion("is_main is not null");
            return (Criteria) this;
        }

        public Criteria andIsMainEqualTo(Short value) {
            addCriterion("is_main =", value, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainNotEqualTo(Short value) {
            addCriterion("is_main <>", value, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainGreaterThan(Short value) {
            addCriterion("is_main >", value, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainGreaterThanOrEqualTo(Short value) {
            addCriterion("is_main >=", value, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainLessThan(Short value) {
            addCriterion("is_main <", value, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainLessThanOrEqualTo(Short value) {
            addCriterion("is_main <=", value, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainIn(List<Short> values) {
            addCriterion("is_main in", values, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainNotIn(List<Short> values) {
            addCriterion("is_main not in", values, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainBetween(Short value1, Short value2) {
            addCriterion("is_main between", value1, value2, "isMain");
            return (Criteria) this;
        }

        public Criteria andIsMainNotBetween(Short value1, Short value2) {
            addCriterion("is_main not between", value1, value2, "isMain");
            return (Criteria) this;
        }

        public Criteria andHigherUpIsNull() {
            addCriterion("higher_up is null");
            return (Criteria) this;
        }

        public Criteria andHigherUpIsNotNull() {
            addCriterion("higher_up is not null");
            return (Criteria) this;
        }

        public Criteria andHigherUpEqualTo(String value) {
            addCriterion("higher_up =", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpNotEqualTo(String value) {
            addCriterion("higher_up <>", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpGreaterThan(String value) {
            addCriterion("higher_up >", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpGreaterThanOrEqualTo(String value) {
            addCriterion("higher_up >=", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpLessThan(String value) {
            addCriterion("higher_up <", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpLessThanOrEqualTo(String value) {
            addCriterion("higher_up <=", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpLike(String value) {
            addCriterion("higher_up like", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpNotLike(String value) {
            addCriterion("higher_up not like", value, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpIn(List<String> values) {
            addCriterion("higher_up in", values, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpNotIn(List<String> values) {
            addCriterion("higher_up not in", values, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpBetween(String value1, String value2) {
            addCriterion("higher_up between", value1, value2, "higherUp");
            return (Criteria) this;
        }

        public Criteria andHigherUpNotBetween(String value1, String value2) {
            addCriterion("higher_up not between", value1, value2, "higherUp");
            return (Criteria) this;
        }

        public Criteria andPkUserLikeInsensitive(String value) {
            addCriterion("upper(pk_user) like", value.toUpperCase(), "pkUser");
            return (Criteria) this;
        }

        public Criteria andPkPostLikeInsensitive(String value) {
            addCriterion("upper(pk_post) like", value.toUpperCase(), "pkPost");
            return (Criteria) this;
        }

        public Criteria andHigherUpLikeInsensitive(String value) {
            addCriterion("upper(higher_up) like", value.toUpperCase(), "higherUp");
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