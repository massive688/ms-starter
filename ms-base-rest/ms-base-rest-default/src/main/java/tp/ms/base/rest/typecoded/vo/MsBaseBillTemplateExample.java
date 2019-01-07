package tp.ms.base.rest.typecoded.vo;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MsBaseBillTemplateExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MsBaseBillTemplateExample() {
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

        public Criteria andPkBaseBillTemplateIsNull() {
            addCriterion("pk_base_bill_template is null");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateIsNotNull() {
            addCriterion("pk_base_bill_template is not null");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateEqualTo(String value) {
            addCriterion("pk_base_bill_template =", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateNotEqualTo(String value) {
            addCriterion("pk_base_bill_template <>", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateGreaterThan(String value) {
            addCriterion("pk_base_bill_template >", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("pk_base_bill_template >=", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateLessThan(String value) {
            addCriterion("pk_base_bill_template <", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateLessThanOrEqualTo(String value) {
            addCriterion("pk_base_bill_template <=", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateLike(String value) {
            addCriterion("pk_base_bill_template like", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateNotLike(String value) {
            addCriterion("pk_base_bill_template not like", value, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateIn(List<String> values) {
            addCriterion("pk_base_bill_template in", values, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateNotIn(List<String> values) {
            addCriterion("pk_base_bill_template not in", values, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateBetween(String value1, String value2) {
            addCriterion("pk_base_bill_template between", value1, value2, "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillTemplateNotBetween(String value1, String value2) {
            addCriterion("pk_base_bill_template not between", value1, value2, "pkBaseBillTemplate");
            return (Criteria) this;
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

        public Criteria andColumnModuleNameIsNull() {
            addCriterion("column_module_name is null");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameIsNotNull() {
            addCriterion("column_module_name is not null");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameEqualTo(String value) {
            addCriterion("column_module_name =", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameNotEqualTo(String value) {
            addCriterion("column_module_name <>", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameGreaterThan(String value) {
            addCriterion("column_module_name >", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("column_module_name >=", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameLessThan(String value) {
            addCriterion("column_module_name <", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameLessThanOrEqualTo(String value) {
            addCriterion("column_module_name <=", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameLike(String value) {
            addCriterion("column_module_name like", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameNotLike(String value) {
            addCriterion("column_module_name not like", value, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameIn(List<String> values) {
            addCriterion("column_module_name in", values, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameNotIn(List<String> values) {
            addCriterion("column_module_name not in", values, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameBetween(String value1, String value2) {
            addCriterion("column_module_name between", value1, value2, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameNotBetween(String value1, String value2) {
            addCriterion("column_module_name not between", value1, value2, "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameIsNull() {
            addCriterion("column_class_qualified_name is null");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameIsNotNull() {
            addCriterion("column_class_qualified_name is not null");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameEqualTo(String value) {
            addCriterion("column_class_qualified_name =", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameNotEqualTo(String value) {
            addCriterion("column_class_qualified_name <>", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameGreaterThan(String value) {
            addCriterion("column_class_qualified_name >", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameGreaterThanOrEqualTo(String value) {
            addCriterion("column_class_qualified_name >=", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameLessThan(String value) {
            addCriterion("column_class_qualified_name <", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameLessThanOrEqualTo(String value) {
            addCriterion("column_class_qualified_name <=", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameLike(String value) {
            addCriterion("column_class_qualified_name like", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameNotLike(String value) {
            addCriterion("column_class_qualified_name not like", value, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameIn(List<String> values) {
            addCriterion("column_class_qualified_name in", values, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameNotIn(List<String> values) {
            addCriterion("column_class_qualified_name not in", values, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameBetween(String value1, String value2) {
            addCriterion("column_class_qualified_name between", value1, value2, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameNotBetween(String value1, String value2) {
            addCriterion("column_class_qualified_name not between", value1, value2, "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationIsNull() {
            addCriterion("column_class_name_abbreviation is null");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationIsNotNull() {
            addCriterion("column_class_name_abbreviation is not null");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationEqualTo(String value) {
            addCriterion("column_class_name_abbreviation =", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationNotEqualTo(String value) {
            addCriterion("column_class_name_abbreviation <>", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationGreaterThan(String value) {
            addCriterion("column_class_name_abbreviation >", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationGreaterThanOrEqualTo(String value) {
            addCriterion("column_class_name_abbreviation >=", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationLessThan(String value) {
            addCriterion("column_class_name_abbreviation <", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationLessThanOrEqualTo(String value) {
            addCriterion("column_class_name_abbreviation <=", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationLike(String value) {
            addCriterion("column_class_name_abbreviation like", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationNotLike(String value) {
            addCriterion("column_class_name_abbreviation not like", value, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationIn(List<String> values) {
            addCriterion("column_class_name_abbreviation in", values, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationNotIn(List<String> values) {
            addCriterion("column_class_name_abbreviation not in", values, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationBetween(String value1, String value2) {
            addCriterion("column_class_name_abbreviation between", value1, value2, "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationNotBetween(String value1, String value2) {
            addCriterion("column_class_name_abbreviation not between", value1, value2, "columnClassNameAbbreviation");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNull() {
            addCriterion("describe is null");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNotNull() {
            addCriterion("describe is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeEqualTo(String value) {
            addCriterion("describe =", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotEqualTo(String value) {
            addCriterion("describe <>", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThan(String value) {
            addCriterion("describe >", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("describe >=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThan(String value) {
            addCriterion("describe <", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThanOrEqualTo(String value) {
            addCriterion("describe <=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLike(String value) {
            addCriterion("describe like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotLike(String value) {
            addCriterion("describe not like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeIn(List<String> values) {
            addCriterion("describe in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotIn(List<String> values) {
            addCriterion("describe not in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeBetween(String value1, String value2) {
            addCriterion("describe between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotBetween(String value1, String value2) {
            addCriterion("describe not between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andPlaceholderIsNull() {
            addCriterion("placeholder is null");
            return (Criteria) this;
        }

        public Criteria andPlaceholderIsNotNull() {
            addCriterion("placeholder is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceholderEqualTo(String value) {
            addCriterion("placeholder =", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotEqualTo(String value) {
            addCriterion("placeholder <>", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderGreaterThan(String value) {
            addCriterion("placeholder >", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderGreaterThanOrEqualTo(String value) {
            addCriterion("placeholder >=", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderLessThan(String value) {
            addCriterion("placeholder <", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderLessThanOrEqualTo(String value) {
            addCriterion("placeholder <=", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderLike(String value) {
            addCriterion("placeholder like", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotLike(String value) {
            addCriterion("placeholder not like", value, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderIn(List<String> values) {
            addCriterion("placeholder in", values, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotIn(List<String> values) {
            addCriterion("placeholder not in", values, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderBetween(String value1, String value2) {
            addCriterion("placeholder between", value1, value2, "placeholder");
            return (Criteria) this;
        }

        public Criteria andPlaceholderNotBetween(String value1, String value2) {
            addCriterion("placeholder not between", value1, value2, "placeholder");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNull() {
            addCriterion("required is null");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNotNull() {
            addCriterion("required is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredEqualTo(String value) {
            addCriterion("required =", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotEqualTo(String value) {
            addCriterion("required <>", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThan(String value) {
            addCriterion("required >", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThanOrEqualTo(String value) {
            addCriterion("required >=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThan(String value) {
            addCriterion("required <", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThanOrEqualTo(String value) {
            addCriterion("required <=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLike(String value) {
            addCriterion("required like", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotLike(String value) {
            addCriterion("required not like", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredIn(List<String> values) {
            addCriterion("required in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotIn(List<String> values) {
            addCriterion("required not in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredBetween(String value1, String value2) {
            addCriterion("required between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotBetween(String value1, String value2) {
            addCriterion("required not between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andEmptyHintIsNull() {
            addCriterion("empty_hint is null");
            return (Criteria) this;
        }

        public Criteria andEmptyHintIsNotNull() {
            addCriterion("empty_hint is not null");
            return (Criteria) this;
        }

        public Criteria andEmptyHintEqualTo(String value) {
            addCriterion("empty_hint =", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintNotEqualTo(String value) {
            addCriterion("empty_hint <>", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintGreaterThan(String value) {
            addCriterion("empty_hint >", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintGreaterThanOrEqualTo(String value) {
            addCriterion("empty_hint >=", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintLessThan(String value) {
            addCriterion("empty_hint <", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintLessThanOrEqualTo(String value) {
            addCriterion("empty_hint <=", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintLike(String value) {
            addCriterion("empty_hint like", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintNotLike(String value) {
            addCriterion("empty_hint not like", value, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintIn(List<String> values) {
            addCriterion("empty_hint in", values, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintNotIn(List<String> values) {
            addCriterion("empty_hint not in", values, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintBetween(String value1, String value2) {
            addCriterion("empty_hint between", value1, value2, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andEmptyHintNotBetween(String value1, String value2) {
            addCriterion("empty_hint not between", value1, value2, "emptyHint");
            return (Criteria) this;
        }

        public Criteria andReadonlyIsNull() {
            addCriterion("readonly is null");
            return (Criteria) this;
        }

        public Criteria andReadonlyIsNotNull() {
            addCriterion("readonly is not null");
            return (Criteria) this;
        }

        public Criteria andReadonlyEqualTo(String value) {
            addCriterion("readonly =", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyNotEqualTo(String value) {
            addCriterion("readonly <>", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyGreaterThan(String value) {
            addCriterion("readonly >", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyGreaterThanOrEqualTo(String value) {
            addCriterion("readonly >=", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyLessThan(String value) {
            addCriterion("readonly <", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyLessThanOrEqualTo(String value) {
            addCriterion("readonly <=", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyLike(String value) {
            addCriterion("readonly like", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyNotLike(String value) {
            addCriterion("readonly not like", value, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyIn(List<String> values) {
            addCriterion("readonly in", values, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyNotIn(List<String> values) {
            addCriterion("readonly not in", values, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyBetween(String value1, String value2) {
            addCriterion("readonly between", value1, value2, "readonly");
            return (Criteria) this;
        }

        public Criteria andReadonlyNotBetween(String value1, String value2) {
            addCriterion("readonly not between", value1, value2, "readonly");
            return (Criteria) this;
        }

        public Criteria andMinLengthIsNull() {
            addCriterion("min_length is null");
            return (Criteria) this;
        }

        public Criteria andMinLengthIsNotNull() {
            addCriterion("min_length is not null");
            return (Criteria) this;
        }

        public Criteria andMinLengthEqualTo(Integer value) {
            addCriterion("min_length =", value, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthNotEqualTo(Integer value) {
            addCriterion("min_length <>", value, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthGreaterThan(Integer value) {
            addCriterion("min_length >", value, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_length >=", value, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthLessThan(Integer value) {
            addCriterion("min_length <", value, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthLessThanOrEqualTo(Integer value) {
            addCriterion("min_length <=", value, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthIn(List<Integer> values) {
            addCriterion("min_length in", values, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthNotIn(List<Integer> values) {
            addCriterion("min_length not in", values, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthBetween(Integer value1, Integer value2) {
            addCriterion("min_length between", value1, value2, "minLength");
            return (Criteria) this;
        }

        public Criteria andMinLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("min_length not between", value1, value2, "minLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthIsNull() {
            addCriterion("max_length is null");
            return (Criteria) this;
        }

        public Criteria andMaxLengthIsNotNull() {
            addCriterion("max_length is not null");
            return (Criteria) this;
        }

        public Criteria andMaxLengthEqualTo(Integer value) {
            addCriterion("max_length =", value, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthNotEqualTo(Integer value) {
            addCriterion("max_length <>", value, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthGreaterThan(Integer value) {
            addCriterion("max_length >", value, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_length >=", value, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthLessThan(Integer value) {
            addCriterion("max_length <", value, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthLessThanOrEqualTo(Integer value) {
            addCriterion("max_length <=", value, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthIn(List<Integer> values) {
            addCriterion("max_length in", values, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthNotIn(List<Integer> values) {
            addCriterion("max_length not in", values, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthBetween(Integer value1, Integer value2) {
            addCriterion("max_length between", value1, value2, "maxLength");
            return (Criteria) this;
        }

        public Criteria andMaxLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("max_length not between", value1, value2, "maxLength");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNull() {
            addCriterion("field_type is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNotNull() {
            addCriterion("field_type is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeEqualTo(Integer value) {
            addCriterion("field_type =", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotEqualTo(Integer value) {
            addCriterion("field_type <>", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThan(Integer value) {
            addCriterion("field_type >", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_type >=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThan(Integer value) {
            addCriterion("field_type <", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThanOrEqualTo(Integer value) {
            addCriterion("field_type <=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIn(List<Integer> values) {
            addCriterion("field_type in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotIn(List<Integer> values) {
            addCriterion("field_type not in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeBetween(Integer value1, Integer value2) {
            addCriterion("field_type between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("field_type not between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andRefTranslateIsNull() {
            addCriterion("ref_translate is null");
            return (Criteria) this;
        }

        public Criteria andRefTranslateIsNotNull() {
            addCriterion("ref_translate is not null");
            return (Criteria) this;
        }

        public Criteria andRefTranslateEqualTo(String value) {
            addCriterion("ref_translate =", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateNotEqualTo(String value) {
            addCriterion("ref_translate <>", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateGreaterThan(String value) {
            addCriterion("ref_translate >", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateGreaterThanOrEqualTo(String value) {
            addCriterion("ref_translate >=", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateLessThan(String value) {
            addCriterion("ref_translate <", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateLessThanOrEqualTo(String value) {
            addCriterion("ref_translate <=", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateLike(String value) {
            addCriterion("ref_translate like", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateNotLike(String value) {
            addCriterion("ref_translate not like", value, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateIn(List<String> values) {
            addCriterion("ref_translate in", values, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateNotIn(List<String> values) {
            addCriterion("ref_translate not in", values, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateBetween(String value1, String value2) {
            addCriterion("ref_translate between", value1, value2, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefTranslateNotBetween(String value1, String value2) {
            addCriterion("ref_translate not between", value1, value2, "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefShowIsNull() {
            addCriterion("ref_show is null");
            return (Criteria) this;
        }

        public Criteria andRefShowIsNotNull() {
            addCriterion("ref_show is not null");
            return (Criteria) this;
        }

        public Criteria andRefShowEqualTo(Integer value) {
            addCriterion("ref_show =", value, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowNotEqualTo(Integer value) {
            addCriterion("ref_show <>", value, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowGreaterThan(Integer value) {
            addCriterion("ref_show >", value, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("ref_show >=", value, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowLessThan(Integer value) {
            addCriterion("ref_show <", value, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowLessThanOrEqualTo(Integer value) {
            addCriterion("ref_show <=", value, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowIn(List<Integer> values) {
            addCriterion("ref_show in", values, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowNotIn(List<Integer> values) {
            addCriterion("ref_show not in", values, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowBetween(Integer value1, Integer value2) {
            addCriterion("ref_show between", value1, value2, "refShow");
            return (Criteria) this;
        }

        public Criteria andRefShowNotBetween(Integer value1, Integer value2) {
            addCriterion("ref_show not between", value1, value2, "refShow");
            return (Criteria) this;
        }

        public Criteria andReorderIsNull() {
            addCriterion("reorder is null");
            return (Criteria) this;
        }

        public Criteria andReorderIsNotNull() {
            addCriterion("reorder is not null");
            return (Criteria) this;
        }

        public Criteria andReorderEqualTo(Integer value) {
            addCriterion("reorder =", value, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderNotEqualTo(Integer value) {
            addCriterion("reorder <>", value, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderGreaterThan(Integer value) {
            addCriterion("reorder >", value, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("reorder >=", value, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderLessThan(Integer value) {
            addCriterion("reorder <", value, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderLessThanOrEqualTo(Integer value) {
            addCriterion("reorder <=", value, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderIn(List<Integer> values) {
            addCriterion("reorder in", values, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderNotIn(List<Integer> values) {
            addCriterion("reorder not in", values, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderBetween(Integer value1, Integer value2) {
            addCriterion("reorder between", value1, value2, "reorder");
            return (Criteria) this;
        }

        public Criteria andReorderNotBetween(Integer value1, Integer value2) {
            addCriterion("reorder not between", value1, value2, "reorder");
            return (Criteria) this;
        }

        public Criteria andRefFormulaIsNull() {
            addCriterion("ref_formula is null");
            return (Criteria) this;
        }

        public Criteria andRefFormulaIsNotNull() {
            addCriterion("ref_formula is not null");
            return (Criteria) this;
        }

        public Criteria andRefFormulaEqualTo(String value) {
            addCriterion("ref_formula =", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaNotEqualTo(String value) {
            addCriterion("ref_formula <>", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaGreaterThan(String value) {
            addCriterion("ref_formula >", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaGreaterThanOrEqualTo(String value) {
            addCriterion("ref_formula >=", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaLessThan(String value) {
            addCriterion("ref_formula <", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaLessThanOrEqualTo(String value) {
            addCriterion("ref_formula <=", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaLike(String value) {
            addCriterion("ref_formula like", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaNotLike(String value) {
            addCriterion("ref_formula not like", value, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaIn(List<String> values) {
            addCriterion("ref_formula in", values, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaNotIn(List<String> values) {
            addCriterion("ref_formula not in", values, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaBetween(String value1, String value2) {
            addCriterion("ref_formula between", value1, value2, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefFormulaNotBetween(String value1, String value2) {
            addCriterion("ref_formula not between", value1, value2, "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefUrlIsNull() {
            addCriterion("ref_url is null");
            return (Criteria) this;
        }

        public Criteria andRefUrlIsNotNull() {
            addCriterion("ref_url is not null");
            return (Criteria) this;
        }

        public Criteria andRefUrlEqualTo(String value) {
            addCriterion("ref_url =", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlNotEqualTo(String value) {
            addCriterion("ref_url <>", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlGreaterThan(String value) {
            addCriterion("ref_url >", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ref_url >=", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlLessThan(String value) {
            addCriterion("ref_url <", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlLessThanOrEqualTo(String value) {
            addCriterion("ref_url <=", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlLike(String value) {
            addCriterion("ref_url like", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlNotLike(String value) {
            addCriterion("ref_url not like", value, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlIn(List<String> values) {
            addCriterion("ref_url in", values, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlNotIn(List<String> values) {
            addCriterion("ref_url not in", values, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlBetween(String value1, String value2) {
            addCriterion("ref_url between", value1, value2, "refUrl");
            return (Criteria) this;
        }

        public Criteria andRefUrlNotBetween(String value1, String value2) {
            addCriterion("ref_url not between", value1, value2, "refUrl");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameIsNull() {
            addCriterion("data_translate_name is null");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameIsNotNull() {
            addCriterion("data_translate_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameEqualTo(String value) {
            addCriterion("data_translate_name =", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameNotEqualTo(String value) {
            addCriterion("data_translate_name <>", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameGreaterThan(String value) {
            addCriterion("data_translate_name >", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_translate_name >=", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameLessThan(String value) {
            addCriterion("data_translate_name <", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameLessThanOrEqualTo(String value) {
            addCriterion("data_translate_name <=", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameLike(String value) {
            addCriterion("data_translate_name like", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameNotLike(String value) {
            addCriterion("data_translate_name not like", value, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameIn(List<String> values) {
            addCriterion("data_translate_name in", values, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameNotIn(List<String> values) {
            addCriterion("data_translate_name not in", values, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameBetween(String value1, String value2) {
            addCriterion("data_translate_name between", value1, value2, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameNotBetween(String value1, String value2) {
            addCriterion("data_translate_name not between", value1, value2, "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameIsNull() {
            addCriterion("prev_generator_name is null");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameIsNotNull() {
            addCriterion("prev_generator_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameEqualTo(String value) {
            addCriterion("prev_generator_name =", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameNotEqualTo(String value) {
            addCriterion("prev_generator_name <>", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameGreaterThan(String value) {
            addCriterion("prev_generator_name >", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameGreaterThanOrEqualTo(String value) {
            addCriterion("prev_generator_name >=", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameLessThan(String value) {
            addCriterion("prev_generator_name <", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameLessThanOrEqualTo(String value) {
            addCriterion("prev_generator_name <=", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameLike(String value) {
            addCriterion("prev_generator_name like", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameNotLike(String value) {
            addCriterion("prev_generator_name not like", value, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameIn(List<String> values) {
            addCriterion("prev_generator_name in", values, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameNotIn(List<String> values) {
            addCriterion("prev_generator_name not in", values, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameBetween(String value1, String value2) {
            addCriterion("prev_generator_name between", value1, value2, "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameNotBetween(String value1, String value2) {
            addCriterion("prev_generator_name not between", value1, value2, "prevGeneratorName");
            return (Criteria) this;
        }
        


        public Criteria andRefQueryConditionIsNull() {
            addCriterion("ref_query_condition is null");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionIsNotNull() {
            addCriterion("ref_query_condition is not null");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionEqualTo(String value) {
            addCriterion("ref_query_condition =", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionNotEqualTo(String value) {
            addCriterion("ref_query_condition <>", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionGreaterThan(String value) {
            addCriterion("ref_query_condition >", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionGreaterThanOrEqualTo(String value) {
            addCriterion("ref_query_condition >=", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionLessThan(String value) {
            addCriterion("ref_query_condition <", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionLessThanOrEqualTo(String value) {
            addCriterion("ref_query_condition <=", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionLike(String value) {
            addCriterion("ref_query_condition like", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionNotLike(String value) {
            addCriterion("ref_query_condition not like", value, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionIn(List<String> values) {
            addCriterion("ref_query_condition in", values, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionNotIn(List<String> values) {
            addCriterion("ref_query_condition not in", values, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionBetween(String value1, String value2) {
            addCriterion("ref_query_condition between", value1, value2, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionNotBetween(String value1, String value2) {
            addCriterion("ref_query_condition not between", value1, value2, "refQueryCondition");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
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

        public Criteria andPkBaseBillTemplateLikeInsensitive(String value) {
            addCriterion("upper(pk_base_bill_template) like", value.toUpperCase(), "pkBaseBillTemplate");
            return (Criteria) this;
        }

        public Criteria andPkBilltypeLikeInsensitive(String value) {
            addCriterion("upper(pk_billtype) like", value.toUpperCase(), "pkBilltype");
            return (Criteria) this;
        }

        public Criteria andColumnModuleNameLikeInsensitive(String value) {
            addCriterion("upper(column_module_name) like", value.toUpperCase(), "columnModuleName");
            return (Criteria) this;
        }

        public Criteria andColumnClassQualifiedNameLikeInsensitive(String value) {
            addCriterion("upper(column_class_qualified_name) like", value.toUpperCase(), "columnClassQualifiedName");
            return (Criteria) this;
        }

        public Criteria andColumnClassNameAbbreviationLikeInsensitive(String value) {
            addCriterion("upper(column_class_name_abbreviation) like", value.toUpperCase(), "columnClassNameAbbreviation");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(type) like", value.toUpperCase(), "type");
            return (Criteria) this;
        }

        public Criteria andDescribeLikeInsensitive(String value) {
            addCriterion("upper(describe) like", value.toUpperCase(), "describe");
            return (Criteria) this;
        }

        public Criteria andPlaceholderLikeInsensitive(String value) {
            addCriterion("upper(placeholder) like", value.toUpperCase(), "placeholder");
            return (Criteria) this;
        }

        public Criteria andRequiredLikeInsensitive(String value) {
            addCriterion("upper(required) like", value.toUpperCase(), "required");
            return (Criteria) this;
        }

        public Criteria andEmptyHintLikeInsensitive(String value) {
            addCriterion("upper(empty_hint) like", value.toUpperCase(), "emptyHint");
            return (Criteria) this;
        }

        public Criteria andReadonlyLikeInsensitive(String value) {
            addCriterion("upper(readonly) like", value.toUpperCase(), "readonly");
            return (Criteria) this;
        }

        public Criteria andRefTranslateLikeInsensitive(String value) {
            addCriterion("upper(ref_translate) like", value.toUpperCase(), "refTranslate");
            return (Criteria) this;
        }

        public Criteria andRefFormulaLikeInsensitive(String value) {
            addCriterion("upper(ref_formula) like", value.toUpperCase(), "refFormula");
            return (Criteria) this;
        }

        public Criteria andRefUrlLikeInsensitive(String value) {
            addCriterion("upper(ref_url) like", value.toUpperCase(), "refUrl");
            return (Criteria) this;
        }

        public Criteria andDataTranslateNameLikeInsensitive(String value) {
            addCriterion("upper(data_translate_name) like", value.toUpperCase(), "dataTranslateName");
            return (Criteria) this;
        }

        public Criteria andPrevGeneratorNameLikeInsensitive(String value) {
            addCriterion("upper(prev_generator_name) like", value.toUpperCase(), "prevGeneratorName");
            return (Criteria) this;
        }

        public Criteria andRefQueryConditionLikeInsensitive(String value) {
            addCriterion("upper(ref_query_condition) like", value.toUpperCase(), "refQueryCondition");
            return (Criteria) this;
        }
        
        public Criteria andTableNameLikeInsensitive(String value) {
            addCriterion("upper(table_name) like", value.toUpperCase(), "tableName");
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