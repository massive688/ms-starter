package tp.ms.base.rest.ots.orgs.entity;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MyAdreamOrgelementsExample extends BaseExample {

    protected List<Criteria> oredCriteria;

    public MyAdreamOrgelementsExample() {
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

        public Criteria andPkOrgelementsIsNull() {
            addCriterion("pk_orgelements is null");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsIsNotNull() {
            addCriterion("pk_orgelements is not null");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsEqualTo(String value) {
            addCriterion("pk_orgelements =", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsNotEqualTo(String value) {
            addCriterion("pk_orgelements <>", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsGreaterThan(String value) {
            addCriterion("pk_orgelements >", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsGreaterThanOrEqualTo(String value) {
            addCriterion("pk_orgelements >=", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsLessThan(String value) {
            addCriterion("pk_orgelements <", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsLessThanOrEqualTo(String value) {
            addCriterion("pk_orgelements <=", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsLike(String value) {
            addCriterion("pk_orgelements like", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsNotLike(String value) {
            addCriterion("pk_orgelements not like", value, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsIn(List<String> values) {
            addCriterion("pk_orgelements in", values, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsNotIn(List<String> values) {
            addCriterion("pk_orgelements not in", values, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsBetween(String value1, String value2) {
            addCriterion("pk_orgelements between", value1, value2, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andPkOrgelementsNotBetween(String value1, String value2) {
            addCriterion("pk_orgelements not between", value1, value2, "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPkCorpIsNull() {
            addCriterion("pk_corp is null");
            return (Criteria) this;
        }

        public Criteria andPkCorpIsNotNull() {
            addCriterion("pk_corp is not null");
            return (Criteria) this;
        }

        public Criteria andPkCorpEqualTo(String value) {
            addCriterion("pk_corp =", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpNotEqualTo(String value) {
            addCriterion("pk_corp <>", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpGreaterThan(String value) {
            addCriterion("pk_corp >", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpGreaterThanOrEqualTo(String value) {
            addCriterion("pk_corp >=", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpLessThan(String value) {
            addCriterion("pk_corp <", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpLessThanOrEqualTo(String value) {
            addCriterion("pk_corp <=", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpLike(String value) {
            addCriterion("pk_corp like", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpNotLike(String value) {
            addCriterion("pk_corp not like", value, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpIn(List<String> values) {
            addCriterion("pk_corp in", values, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpNotIn(List<String> values) {
            addCriterion("pk_corp not in", values, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpBetween(String value1, String value2) {
            addCriterion("pk_corp between", value1, value2, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkCorpNotBetween(String value1, String value2) {
            addCriterion("pk_corp not between", value1, value2, "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkGroupIsNull() {
            addCriterion("pk_group is null");
            return (Criteria) this;
        }

        public Criteria andPkGroupIsNotNull() {
            addCriterion("pk_group is not null");
            return (Criteria) this;
        }

        public Criteria andPkGroupEqualTo(String value) {
            addCriterion("pk_group =", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupNotEqualTo(String value) {
            addCriterion("pk_group <>", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupGreaterThan(String value) {
            addCriterion("pk_group >", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupGreaterThanOrEqualTo(String value) {
            addCriterion("pk_group >=", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupLessThan(String value) {
            addCriterion("pk_group <", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupLessThanOrEqualTo(String value) {
            addCriterion("pk_group <=", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupLike(String value) {
            addCriterion("pk_group like", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupNotLike(String value) {
            addCriterion("pk_group not like", value, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupIn(List<String> values) {
            addCriterion("pk_group in", values, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupNotIn(List<String> values) {
            addCriterion("pk_group not in", values, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupBetween(String value1, String value2) {
            addCriterion("pk_group between", value1, value2, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andPkGroupNotBetween(String value1, String value2) {
            addCriterion("pk_group not between", value1, value2, "pkGroup");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreationtimeIsNull() {
            addCriterion("creationtime is null");
            return (Criteria) this;
        }

        public Criteria andCreationtimeIsNotNull() {
            addCriterion("creationtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreationtimeEqualTo(String value) {
            addCriterion("creationtime =", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeNotEqualTo(String value) {
            addCriterion("creationtime <>", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeGreaterThan(String value) {
            addCriterion("creationtime >", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeGreaterThanOrEqualTo(String value) {
            addCriterion("creationtime >=", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeLessThan(String value) {
            addCriterion("creationtime <", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeLessThanOrEqualTo(String value) {
            addCriterion("creationtime <=", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeLike(String value) {
            addCriterion("creationtime like", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeNotLike(String value) {
            addCriterion("creationtime not like", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeIn(List<String> values) {
            addCriterion("creationtime in", values, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeNotIn(List<String> values) {
            addCriterion("creationtime not in", values, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeBetween(String value1, String value2) {
            addCriterion("creationtime between", value1, value2, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeNotBetween(String value1, String value2) {
            addCriterion("creationtime not between", value1, value2, "creationtime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeIsNull() {
            addCriterion("modifiedtime is null");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeIsNotNull() {
            addCriterion("modifiedtime is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeEqualTo(String value) {
            addCriterion("modifiedtime =", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeNotEqualTo(String value) {
            addCriterion("modifiedtime <>", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeGreaterThan(String value) {
            addCriterion("modifiedtime >", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeGreaterThanOrEqualTo(String value) {
            addCriterion("modifiedtime >=", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeLessThan(String value) {
            addCriterion("modifiedtime <", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeLessThanOrEqualTo(String value) {
            addCriterion("modifiedtime <=", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeLike(String value) {
            addCriterion("modifiedtime like", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeNotLike(String value) {
            addCriterion("modifiedtime not like", value, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeIn(List<String> values) {
            addCriterion("modifiedtime in", values, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeNotIn(List<String> values) {
            addCriterion("modifiedtime not in", values, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeBetween(String value1, String value2) {
            addCriterion("modifiedtime between", value1, value2, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeNotBetween(String value1, String value2) {
            addCriterion("modifiedtime not between", value1, value2, "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNull() {
            addCriterion("billtype is null");
            return (Criteria) this;
        }

        public Criteria andBilltypeIsNotNull() {
            addCriterion("billtype is not null");
            return (Criteria) this;
        }

        public Criteria andBilltypeEqualTo(String value) {
            addCriterion("billtype =", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotEqualTo(String value) {
            addCriterion("billtype <>", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThan(String value) {
            addCriterion("billtype >", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeGreaterThanOrEqualTo(String value) {
            addCriterion("billtype >=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThan(String value) {
            addCriterion("billtype <", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLessThanOrEqualTo(String value) {
            addCriterion("billtype <=", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeLike(String value) {
            addCriterion("billtype like", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotLike(String value) {
            addCriterion("billtype not like", value, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeIn(List<String> values) {
            addCriterion("billtype in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotIn(List<String> values) {
            addCriterion("billtype not in", values, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeBetween(String value1, String value2) {
            addCriterion("billtype between", value1, value2, "billtype");
            return (Criteria) this;
        }

        public Criteria andBilltypeNotBetween(String value1, String value2) {
            addCriterion("billtype not between", value1, value2, "billtype");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Short value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Short value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Short value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Short value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Short value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Short value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Short> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Short> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Short value1, Short value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Short value1, Short value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
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

        public Criteria andPkOrgelementsLikeInsensitive(String value) {
            addCriterion("upper(pk_orgelements) like", value.toUpperCase(), "pkOrgelements");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(code) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andParentIdLikeInsensitive(String value) {
            addCriterion("upper(parent_id) like", value.toUpperCase(), "parentId");
            return (Criteria) this;
        }

        public Criteria andPkCorpLikeInsensitive(String value) {
            addCriterion("upper(pk_corp) like", value.toUpperCase(), "pkCorp");
            return (Criteria) this;
        }

        public Criteria andPkGroupLikeInsensitive(String value) {
            addCriterion("upper(pk_group) like", value.toUpperCase(), "pkGroup");
            return (Criteria) this;
        }

        public Criteria andCreatorLikeInsensitive(String value) {
            addCriterion("upper(creator) like", value.toUpperCase(), "creator");
            return (Criteria) this;
        }

        public Criteria andCreationtimeLikeInsensitive(String value) {
            addCriterion("upper(creationtime) like", value.toUpperCase(), "creationtime");
            return (Criteria) this;
        }

        public Criteria andModifierLikeInsensitive(String value) {
            addCriterion("upper(modifier) like", value.toUpperCase(), "modifier");
            return (Criteria) this;
        }

        public Criteria andModifiedtimeLikeInsensitive(String value) {
            addCriterion("upper(modifiedtime) like", value.toUpperCase(), "modifiedtime");
            return (Criteria) this;
        }

        public Criteria andBilltypeLikeInsensitive(String value) {
            addCriterion("upper(billtype) like", value.toUpperCase(), "billtype");
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