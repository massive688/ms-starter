package tp.ms.base.rest.typecoded.vo;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MsBaseBilltypeExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MsBaseBilltypeExample() {
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

        public Criteria andPkBilltypeIsNull() {
            addCriterion("pk_billtype is null");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeIsNotNull() {
            addCriterion("pk_billtype is not null");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeEqualTo(String value) {
            addCriterion("pk_billtype =", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeNotEqualTo(String value) {
            addCriterion("pk_billtype <>", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeGreaterThan(String value) {
            addCriterion("pk_billtype >", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeGreaterThanOrEqualTo(String value) {
            addCriterion("pk_billtype >=", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeLessThan(String value) {
            addCriterion("pk_billtype <", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeLessThanOrEqualTo(String value) {
            addCriterion("pk_billtype <=", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeLike(String value) {
            addCriterion("pk_billtype like", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeNotLike(String value) {
            addCriterion("pk_billtype not like", value, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeIn(List<String> values) {
            addCriterion("pk_billtype in", values, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeNotIn(List<String> values) {
            addCriterion("pk_billtype not in", values, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeBetween(String value1, String value2) {
            addCriterion("pk_billtype between", value1, value2, "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeNotBetween(String value1, String value2) {
            addCriterion("pk_billtype not between", value1, value2, "pkBilltype");
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

        public Criteria andModuleNameIsNull() {
            addCriterion("module_name is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("module_name is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("module_name =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("module_name <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("module_name >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("module_name >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("module_name <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("module_name <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("module_name like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("module_name not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("module_name in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("module_name not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("module_name between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("module_name not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andPackageNameIsNull() {
            addCriterion("package_name is null");
            return (Criteria) this;
        }

        public Criteria andPackageNameIsNotNull() {
            addCriterion("package_name is not null");
            return (Criteria) this;
        }

        public Criteria andPackageNameEqualTo(String value) {
            addCriterion("package_name =", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotEqualTo(String value) {
            addCriterion("package_name <>", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameGreaterThan(String value) {
            addCriterion("package_name >", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameGreaterThanOrEqualTo(String value) {
            addCriterion("package_name >=", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLessThan(String value) {
            addCriterion("package_name <", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLessThanOrEqualTo(String value) {
            addCriterion("package_name <=", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLike(String value) {
            addCriterion("package_name like", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotLike(String value) {
            addCriterion("package_name not like", value, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameIn(List<String> values) {
            addCriterion("package_name in", values, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotIn(List<String> values) {
            addCriterion("package_name not in", values, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameBetween(String value1, String value2) {
            addCriterion("package_name between", value1, value2, "packageName");
            return (Criteria) this;
        }

        public Criteria andPackageNameNotBetween(String value1, String value2) {
            addCriterion("package_name not between", value1, value2, "packageName");
            return (Criteria) this;
        }

        public Criteria andComponentIsNull() {
            addCriterion("component is null");
            return (Criteria) this;
        }

        public Criteria andComponentIsNotNull() {
            addCriterion("component is not null");
            return (Criteria) this;
        }

        public Criteria andComponentEqualTo(String value) {
            addCriterion("component =", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentNotEqualTo(String value) {
            addCriterion("component <>", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentGreaterThan(String value) {
            addCriterion("component >", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentGreaterThanOrEqualTo(String value) {
            addCriterion("component >=", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentLessThan(String value) {
            addCriterion("component <", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentLessThanOrEqualTo(String value) {
            addCriterion("component <=", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentLike(String value) {
            addCriterion("component like", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentNotLike(String value) {
            addCriterion("component not like", value, "component");
            return (Criteria) this;
        }

        public Criteria andComponentIn(List<String> values) {
            addCriterion("component in", values, "component");
            return (Criteria) this;
        }

        public Criteria andComponentNotIn(List<String> values) {
            addCriterion("component not in", values, "component");
            return (Criteria) this;
        }

        public Criteria andComponentBetween(String value1, String value2) {
            addCriterion("component between", value1, value2, "component");
            return (Criteria) this;
        }

        public Criteria andComponentNotBetween(String value1, String value2) {
            addCriterion("component not between", value1, value2, "component");
            return (Criteria) this;
        }

        public Criteria andComponentTypeIsNull() {
            addCriterion("component_type is null");
            return (Criteria) this;
        }

        public Criteria andComponentTypeIsNotNull() {
            addCriterion("component_type is not null");
            return (Criteria) this;
        }

        public Criteria andComponentTypeEqualTo(Integer value) {
            addCriterion("component_type =", value, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeNotEqualTo(Integer value) {
            addCriterion("component_type <>", value, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeGreaterThan(Integer value) {
            addCriterion("component_type >", value, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("component_type >=", value, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeLessThan(Integer value) {
            addCriterion("component_type <", value, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("component_type <=", value, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeIn(List<Integer> values) {
            addCriterion("component_type in", values, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeNotIn(List<Integer> values) {
            addCriterion("component_type not in", values, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeBetween(Integer value1, Integer value2) {
            addCriterion("component_type between", value1, value2, "componentType");
            return (Criteria) this;
        }

        public Criteria andComponentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("component_type not between", value1, value2, "componentType");
            return (Criteria) this;
        }

        public Criteria andUiPathIsNull() {
            addCriterion("ui_path is null");
            return (Criteria) this;
        }

        public Criteria andUiPathIsNotNull() {
            addCriterion("ui_path is not null");
            return (Criteria) this;
        }

        public Criteria andUiPathEqualTo(String value) {
            addCriterion("ui_path =", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathNotEqualTo(String value) {
            addCriterion("ui_path <>", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathGreaterThan(String value) {
            addCriterion("ui_path >", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathGreaterThanOrEqualTo(String value) {
            addCriterion("ui_path >=", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathLessThan(String value) {
            addCriterion("ui_path <", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathLessThanOrEqualTo(String value) {
            addCriterion("ui_path <=", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathLike(String value) {
            addCriterion("ui_path like", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathNotLike(String value) {
            addCriterion("ui_path not like", value, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathIn(List<String> values) {
            addCriterion("ui_path in", values, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathNotIn(List<String> values) {
            addCriterion("ui_path not in", values, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathBetween(String value1, String value2) {
            addCriterion("ui_path between", value1, value2, "uiPath");
            return (Criteria) this;
        }

        public Criteria andUiPathNotBetween(String value1, String value2) {
            addCriterion("ui_path not between", value1, value2, "uiPath");
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

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Integer value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Integer value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Integer value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Integer value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Integer value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Integer value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Integer> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Integer> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Integer value1, Integer value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Integer value1, Integer value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNull() {
            addCriterion("approve_status is null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNotNull() {
            addCriterion("approve_status is not null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusEqualTo(Integer value) {
            addCriterion("approve_status =", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotEqualTo(Integer value) {
            addCriterion("approve_status <>", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThan(Integer value) {
            addCriterion("approve_status >", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("approve_status >=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThan(Integer value) {
            addCriterion("approve_status <", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThanOrEqualTo(Integer value) {
            addCriterion("approve_status <=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIn(List<Integer> values) {
            addCriterion("approve_status in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotIn(List<Integer> values) {
            addCriterion("approve_status not in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusBetween(Integer value1, Integer value2) {
            addCriterion("approve_status between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("approve_status not between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusIsNull() {
            addCriterion("bill_status is null");
            return (Criteria) this;
        }

        public Criteria andBillStatusIsNotNull() {
            addCriterion("bill_status is not null");
            return (Criteria) this;
        }

        public Criteria andBillStatusEqualTo(Integer value) {
            addCriterion("bill_status =", value, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusNotEqualTo(Integer value) {
            addCriterion("bill_status <>", value, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusGreaterThan(Integer value) {
            addCriterion("bill_status >", value, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_status >=", value, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusLessThan(Integer value) {
            addCriterion("bill_status <", value, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusLessThanOrEqualTo(Integer value) {
            addCriterion("bill_status <=", value, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusIn(List<Integer> values) {
            addCriterion("bill_status in", values, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusNotIn(List<Integer> values) {
            addCriterion("bill_status not in", values, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusBetween(Integer value1, Integer value2) {
            addCriterion("bill_status between", value1, value2, "billStatus");
            return (Criteria) this;
        }

        public Criteria andBillStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_status not between", value1, value2, "billStatus");
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

        public Criteria andPkBilltypeLikeInsensitive(String value) {
            addCriterion("upper(pk_billtype) like", value.toUpperCase(), "pkBilltype");
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

        public Criteria andModuleNameLikeInsensitive(String value) {
            addCriterion("upper(module_name) like", value.toUpperCase(), "moduleName");
            return (Criteria) this;
        }

        public Criteria andPackageNameLikeInsensitive(String value) {
            addCriterion("upper(package_name) like", value.toUpperCase(), "packageName");
            return (Criteria) this;
        }

        public Criteria andComponentLikeInsensitive(String value) {
            addCriterion("upper(component) like", value.toUpperCase(), "component");
            return (Criteria) this;
        }

        public Criteria andUiPathLikeInsensitive(String value) {
            addCriterion("upper(ui_path) like", value.toUpperCase(), "uiPath");
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

        public Criteria andProcessIdLikeInsensitive(String value) {
            addCriterion("upper(process_id) like", value.toUpperCase(), "processId");
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