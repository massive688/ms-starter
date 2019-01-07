package tp.ms.base.rest.refinfo.vo;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MyBaseRefInfoExample extends BaseExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MyBaseRefInfoExample() {
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

        public Criteria andPkBaseRefInfoIsNull() {
            addCriterion("pk_base_ref_info is null");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoIsNotNull() {
            addCriterion("pk_base_ref_info is not null");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoEqualTo(String value) {
            addCriterion("pk_base_ref_info =", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoNotEqualTo(String value) {
            addCriterion("pk_base_ref_info <>", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoGreaterThan(String value) {
            addCriterion("pk_base_ref_info >", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoGreaterThanOrEqualTo(String value) {
            addCriterion("pk_base_ref_info >=", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoLessThan(String value) {
            addCriterion("pk_base_ref_info <", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoLessThanOrEqualTo(String value) {
            addCriterion("pk_base_ref_info <=", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoLike(String value) {
            addCriterion("pk_base_ref_info like", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoNotLike(String value) {
            addCriterion("pk_base_ref_info not like", value, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoIn(List<String> values) {
            addCriterion("pk_base_ref_info in", values, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoNotIn(List<String> values) {
            addCriterion("pk_base_ref_info not in", values, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoBetween(String value1, String value2) {
            addCriterion("pk_base_ref_info between", value1, value2, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoNotBetween(String value1, String value2) {
            addCriterion("pk_base_ref_info not between", value1, value2, "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andRefCodeIsNull() {
            addCriterion("ref_code is null");
            return (Criteria) this;
        }

        public Criteria andRefCodeIsNotNull() {
            addCriterion("ref_code is not null");
            return (Criteria) this;
        }

        public Criteria andRefCodeEqualTo(String value) {
            addCriterion("ref_code =", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotEqualTo(String value) {
            addCriterion("ref_code <>", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeGreaterThan(String value) {
            addCriterion("ref_code >", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ref_code >=", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeLessThan(String value) {
            addCriterion("ref_code <", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeLessThanOrEqualTo(String value) {
            addCriterion("ref_code <=", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeLike(String value) {
            addCriterion("ref_code like", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotLike(String value) {
            addCriterion("ref_code not like", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeIn(List<String> values) {
            addCriterion("ref_code in", values, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotIn(List<String> values) {
            addCriterion("ref_code not in", values, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeBetween(String value1, String value2) {
            addCriterion("ref_code between", value1, value2, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotBetween(String value1, String value2) {
            addCriterion("ref_code not between", value1, value2, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefNameIsNull() {
            addCriterion("ref_name is null");
            return (Criteria) this;
        }

        public Criteria andRefNameIsNotNull() {
            addCriterion("ref_name is not null");
            return (Criteria) this;
        }

        public Criteria andRefNameEqualTo(String value) {
            addCriterion("ref_name =", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotEqualTo(String value) {
            addCriterion("ref_name <>", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameGreaterThan(String value) {
            addCriterion("ref_name >", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameGreaterThanOrEqualTo(String value) {
            addCriterion("ref_name >=", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLessThan(String value) {
            addCriterion("ref_name <", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLessThanOrEqualTo(String value) {
            addCriterion("ref_name <=", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLike(String value) {
            addCriterion("ref_name like", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotLike(String value) {
            addCriterion("ref_name not like", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameIn(List<String> values) {
            addCriterion("ref_name in", values, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotIn(List<String> values) {
            addCriterion("ref_name not in", values, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameBetween(String value1, String value2) {
            addCriterion("ref_name between", value1, value2, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotBetween(String value1, String value2) {
            addCriterion("ref_name not between", value1, value2, "refName");
            return (Criteria) this;
        }

        public Criteria andRefClassIsNull() {
            addCriterion("ref_class is null");
            return (Criteria) this;
        }

        public Criteria andRefClassIsNotNull() {
            addCriterion("ref_class is not null");
            return (Criteria) this;
        }

        public Criteria andRefClassEqualTo(String value) {
            addCriterion("ref_class =", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassNotEqualTo(String value) {
            addCriterion("ref_class <>", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassGreaterThan(String value) {
            addCriterion("ref_class >", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassGreaterThanOrEqualTo(String value) {
            addCriterion("ref_class >=", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassLessThan(String value) {
            addCriterion("ref_class <", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassLessThanOrEqualTo(String value) {
            addCriterion("ref_class <=", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassLike(String value) {
            addCriterion("ref_class like", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassNotLike(String value) {
            addCriterion("ref_class not like", value, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassIn(List<String> values) {
            addCriterion("ref_class in", values, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassNotIn(List<String> values) {
            addCriterion("ref_class not in", values, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassBetween(String value1, String value2) {
            addCriterion("ref_class between", value1, value2, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefClassNotBetween(String value1, String value2) {
            addCriterion("ref_class not between", value1, value2, "refClass");
            return (Criteria) this;
        }

        public Criteria andRefTypeIsNull() {
            addCriterion("ref_type is null");
            return (Criteria) this;
        }

        public Criteria andRefTypeIsNotNull() {
            addCriterion("ref_type is not null");
            return (Criteria) this;
        }

        public Criteria andRefTypeEqualTo(Integer value) {
            addCriterion("ref_type =", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeNotEqualTo(Integer value) {
            addCriterion("ref_type <>", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeGreaterThan(Integer value) {
            addCriterion("ref_type >", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ref_type >=", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeLessThan(Integer value) {
            addCriterion("ref_type <", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ref_type <=", value, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeIn(List<Integer> values) {
            addCriterion("ref_type in", values, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeNotIn(List<Integer> values) {
            addCriterion("ref_type not in", values, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeBetween(Integer value1, Integer value2) {
            addCriterion("ref_type between", value1, value2, "refType");
            return (Criteria) this;
        }

        public Criteria andRefTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ref_type not between", value1, value2, "refType");
            return (Criteria) this;
        }

        public Criteria andModulenameIsNull() {
            addCriterion("modulename is null");
            return (Criteria) this;
        }

        public Criteria andModulenameIsNotNull() {
            addCriterion("modulename is not null");
            return (Criteria) this;
        }

        public Criteria andModulenameEqualTo(String value) {
            addCriterion("modulename =", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameNotEqualTo(String value) {
            addCriterion("modulename <>", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameGreaterThan(String value) {
            addCriterion("modulename >", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameGreaterThanOrEqualTo(String value) {
            addCriterion("modulename >=", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameLessThan(String value) {
            addCriterion("modulename <", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameLessThanOrEqualTo(String value) {
            addCriterion("modulename <=", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameLike(String value) {
            addCriterion("modulename like", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameNotLike(String value) {
            addCriterion("modulename not like", value, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameIn(List<String> values) {
            addCriterion("modulename in", values, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameNotIn(List<String> values) {
            addCriterion("modulename not in", values, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameBetween(String value1, String value2) {
            addCriterion("modulename between", value1, value2, "modulename");
            return (Criteria) this;
        }

        public Criteria andModulenameNotBetween(String value1, String value2) {
            addCriterion("modulename not between", value1, value2, "modulename");
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

        public Criteria andDrEqualTo(Short value) {
            addCriterion("dr =", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotEqualTo(Short value) {
            addCriterion("dr <>", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThan(Short value) {
            addCriterion("dr >", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrGreaterThanOrEqualTo(Short value) {
            addCriterion("dr >=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThan(Short value) {
            addCriterion("dr <", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrLessThanOrEqualTo(Short value) {
            addCriterion("dr <=", value, "dr");
            return (Criteria) this;
        }

        public Criteria andDrIn(List<Short> values) {
            addCriterion("dr in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotIn(List<Short> values) {
            addCriterion("dr not in", values, "dr");
            return (Criteria) this;
        }

        public Criteria andDrBetween(Short value1, Short value2) {
            addCriterion("dr between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andDrNotBetween(Short value1, Short value2) {
            addCriterion("dr not between", value1, value2, "dr");
            return (Criteria) this;
        }

        public Criteria andPkBaseRefInfoLikeInsensitive(String value) {
            addCriterion("upper(pk_base_ref_info) like", value.toUpperCase(), "pkBaseRefInfo");
            return (Criteria) this;
        }

        public Criteria andRefCodeLikeInsensitive(String value) {
            addCriterion("upper(ref_code) like", value.toUpperCase(), "refCode");
            return (Criteria) this;
        }

        public Criteria andRefNameLikeInsensitive(String value) {
            addCriterion("upper(ref_name) like", value.toUpperCase(), "refName");
            return (Criteria) this;
        }

        public Criteria andRefClassLikeInsensitive(String value) {
            addCriterion("upper(ref_class) like", value.toUpperCase(), "refClass");
            return (Criteria) this;
        }

        public Criteria andModulenameLikeInsensitive(String value) {
            addCriterion("upper(modulename) like", value.toUpperCase(), "modulename");
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