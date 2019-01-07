package tp.ms.base.rest.typecoded.vo;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MsBaseBillCodeRuleExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MsBaseBillCodeRuleExample() {
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

        public Criteria andPkBaseBillCodeRuleIsNull() {
            addCriterion("pk_base_bill_code_rule is null");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleIsNotNull() {
            addCriterion("pk_base_bill_code_rule is not null");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleEqualTo(String value) {
            addCriterion("pk_base_bill_code_rule =", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleNotEqualTo(String value) {
            addCriterion("pk_base_bill_code_rule <>", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleGreaterThan(String value) {
            addCriterion("pk_base_bill_code_rule >", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleGreaterThanOrEqualTo(String value) {
            addCriterion("pk_base_bill_code_rule >=", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleLessThan(String value) {
            addCriterion("pk_base_bill_code_rule <", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleLessThanOrEqualTo(String value) {
            addCriterion("pk_base_bill_code_rule <=", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleLike(String value) {
            addCriterion("pk_base_bill_code_rule like", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleNotLike(String value) {
            addCriterion("pk_base_bill_code_rule not like", value, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleIn(List<String> values) {
            addCriterion("pk_base_bill_code_rule in", values, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleNotIn(List<String> values) {
            addCriterion("pk_base_bill_code_rule not in", values, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleBetween(String value1, String value2) {
            addCriterion("pk_base_bill_code_rule between", value1, value2, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andPkBaseBillCodeRuleNotBetween(String value1, String value2) {
            addCriterion("pk_base_bill_code_rule not between", value1, value2, "pkBaseBillCodeRule");
            return (Criteria) this;
        }

        public Criteria andCodelengthIsNull() {
            addCriterion("codelength is null");
            return (Criteria) this;
        }

        public Criteria andCodelengthIsNotNull() {
            addCriterion("codelength is not null");
            return (Criteria) this;
        }

        public Criteria andCodelengthEqualTo(Integer value) {
            addCriterion("codelength =", value, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthNotEqualTo(Integer value) {
            addCriterion("codelength <>", value, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthGreaterThan(Integer value) {
            addCriterion("codelength >", value, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("codelength >=", value, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthLessThan(Integer value) {
            addCriterion("codelength <", value, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthLessThanOrEqualTo(Integer value) {
            addCriterion("codelength <=", value, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthIn(List<Integer> values) {
            addCriterion("codelength in", values, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthNotIn(List<Integer> values) {
            addCriterion("codelength not in", values, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthBetween(Integer value1, Integer value2) {
            addCriterion("codelength between", value1, value2, "codelength");
            return (Criteria) this;
        }

        public Criteria andCodelengthNotBetween(Integer value1, Integer value2) {
            addCriterion("codelength not between", value1, value2, "codelength");
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

        public Criteria andElemtypeIsNull() {
            addCriterion("elemtype is null");
            return (Criteria) this;
        }

        public Criteria andElemtypeIsNotNull() {
            addCriterion("elemtype is not null");
            return (Criteria) this;
        }

        public Criteria andElemtypeEqualTo(Short value) {
            addCriterion("elemtype =", value, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeNotEqualTo(Short value) {
            addCriterion("elemtype <>", value, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeGreaterThan(Short value) {
            addCriterion("elemtype >", value, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("elemtype >=", value, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeLessThan(Short value) {
            addCriterion("elemtype <", value, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeLessThanOrEqualTo(Short value) {
            addCriterion("elemtype <=", value, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeIn(List<Short> values) {
            addCriterion("elemtype in", values, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeNotIn(List<Short> values) {
            addCriterion("elemtype not in", values, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeBetween(Short value1, Short value2) {
            addCriterion("elemtype between", value1, value2, "elemtype");
            return (Criteria) this;
        }

        public Criteria andElemtypeNotBetween(Short value1, Short value2) {
            addCriterion("elemtype not between", value1, value2, "elemtype");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberIsNull() {
            addCriterion("lastserialnumber is null");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberIsNotNull() {
            addCriterion("lastserialnumber is not null");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberEqualTo(String value) {
            addCriterion("lastserialnumber =", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberNotEqualTo(String value) {
            addCriterion("lastserialnumber <>", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberGreaterThan(String value) {
            addCriterion("lastserialnumber >", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberGreaterThanOrEqualTo(String value) {
            addCriterion("lastserialnumber >=", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberLessThan(String value) {
            addCriterion("lastserialnumber <", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberLessThanOrEqualTo(String value) {
            addCriterion("lastserialnumber <=", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberLike(String value) {
            addCriterion("lastserialnumber like", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberNotLike(String value) {
            addCriterion("lastserialnumber not like", value, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberIn(List<String> values) {
            addCriterion("lastserialnumber in", values, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberNotIn(List<String> values) {
            addCriterion("lastserialnumber not in", values, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberBetween(String value1, String value2) {
            addCriterion("lastserialnumber between", value1, value2, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andLastserialnumberNotBetween(String value1, String value2) {
            addCriterion("lastserialnumber not between", value1, value2, "lastserialnumber");
            return (Criteria) this;
        }

        public Criteria andIsreferIsNull() {
            addCriterion("isrefer is null");
            return (Criteria) this;
        }

        public Criteria andIsreferIsNotNull() {
            addCriterion("isrefer is not null");
            return (Criteria) this;
        }

        public Criteria andIsreferEqualTo(Short value) {
            addCriterion("isrefer =", value, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferNotEqualTo(Short value) {
            addCriterion("isrefer <>", value, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferGreaterThan(Short value) {
            addCriterion("isrefer >", value, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferGreaterThanOrEqualTo(Short value) {
            addCriterion("isrefer >=", value, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferLessThan(Short value) {
            addCriterion("isrefer <", value, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferLessThanOrEqualTo(Short value) {
            addCriterion("isrefer <=", value, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferIn(List<Short> values) {
            addCriterion("isrefer in", values, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferNotIn(List<Short> values) {
            addCriterion("isrefer not in", values, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferBetween(Short value1, Short value2) {
            addCriterion("isrefer between", value1, value2, "isrefer");
            return (Criteria) this;
        }

        public Criteria andIsreferNotBetween(Short value1, Short value2) {
            addCriterion("isrefer not between", value1, value2, "isrefer");
            return (Criteria) this;
        }

        public Criteria andCodeprefixIsNull() {
            addCriterion("codePrefix is null");
            return (Criteria) this;
        }

        public Criteria andCodeprefixIsNotNull() {
            addCriterion("codePrefix is not null");
            return (Criteria) this;
        }

        public Criteria andCodeprefixEqualTo(String value) {
            addCriterion("codePrefix =", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixNotEqualTo(String value) {
            addCriterion("codePrefix <>", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixGreaterThan(String value) {
            addCriterion("codePrefix >", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixGreaterThanOrEqualTo(String value) {
            addCriterion("codePrefix >=", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixLessThan(String value) {
            addCriterion("codePrefix <", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixLessThanOrEqualTo(String value) {
            addCriterion("codePrefix <=", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixLike(String value) {
            addCriterion("codePrefix like", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixNotLike(String value) {
            addCriterion("codePrefix not like", value, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixIn(List<String> values) {
            addCriterion("codePrefix in", values, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixNotIn(List<String> values) {
            addCriterion("codePrefix not in", values, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixBetween(String value1, String value2) {
            addCriterion("codePrefix between", value1, value2, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andCodeprefixNotBetween(String value1, String value2) {
            addCriterion("codePrefix not between", value1, value2, "codeprefix");
            return (Criteria) this;
        }

        public Criteria andConstantfixIsNull() {
            addCriterion("constantfix is null");
            return (Criteria) this;
        }

        public Criteria andConstantfixIsNotNull() {
            addCriterion("constantfix is not null");
            return (Criteria) this;
        }

        public Criteria andConstantfixEqualTo(String value) {
            addCriterion("constantfix =", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixNotEqualTo(String value) {
            addCriterion("constantfix <>", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixGreaterThan(String value) {
            addCriterion("constantfix >", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixGreaterThanOrEqualTo(String value) {
            addCriterion("constantfix >=", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixLessThan(String value) {
            addCriterion("constantfix <", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixLessThanOrEqualTo(String value) {
            addCriterion("constantfix <=", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixLike(String value) {
            addCriterion("constantfix like", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixNotLike(String value) {
            addCriterion("constantfix not like", value, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixIn(List<String> values) {
            addCriterion("constantfix in", values, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixNotIn(List<String> values) {
            addCriterion("constantfix not in", values, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixBetween(String value1, String value2) {
            addCriterion("constantfix between", value1, value2, "constantfix");
            return (Criteria) this;
        }

        public Criteria andConstantfixNotBetween(String value1, String value2) {
            addCriterion("constantfix not between", value1, value2, "constantfix");
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

        public Criteria andTimeformatIsNull() {
            addCriterion("timeformat is null");
            return (Criteria) this;
        }

        public Criteria andTimeformatIsNotNull() {
            addCriterion("timeformat is not null");
            return (Criteria) this;
        }

        public Criteria andTimeformatEqualTo(String value) {
            addCriterion("timeformat =", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatNotEqualTo(String value) {
            addCriterion("timeformat <>", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatGreaterThan(String value) {
            addCriterion("timeformat >", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatGreaterThanOrEqualTo(String value) {
            addCriterion("timeformat >=", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatLessThan(String value) {
            addCriterion("timeformat <", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatLessThanOrEqualTo(String value) {
            addCriterion("timeformat <=", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatLike(String value) {
            addCriterion("timeformat like", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatNotLike(String value) {
            addCriterion("timeformat not like", value, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatIn(List<String> values) {
            addCriterion("timeformat in", values, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatNotIn(List<String> values) {
            addCriterion("timeformat not in", values, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatBetween(String value1, String value2) {
            addCriterion("timeformat between", value1, value2, "timeformat");
            return (Criteria) this;
        }

        public Criteria andTimeformatNotBetween(String value1, String value2) {
            addCriterion("timeformat not between", value1, value2, "timeformat");
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

        public Criteria andLasttimevalueIsNull() {
            addCriterion("lasttimevalue is null");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueIsNotNull() {
            addCriterion("lasttimevalue is not null");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueEqualTo(String value) {
            addCriterion("lasttimevalue =", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueNotEqualTo(String value) {
            addCriterion("lasttimevalue <>", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueGreaterThan(String value) {
            addCriterion("lasttimevalue >", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueGreaterThanOrEqualTo(String value) {
            addCriterion("lasttimevalue >=", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueLessThan(String value) {
            addCriterion("lasttimevalue <", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueLessThanOrEqualTo(String value) {
            addCriterion("lasttimevalue <=", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueLike(String value) {
            addCriterion("lasttimevalue like", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueNotLike(String value) {
            addCriterion("lasttimevalue not like", value, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueIn(List<String> values) {
            addCriterion("lasttimevalue in", values, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueNotIn(List<String> values) {
            addCriterion("lasttimevalue not in", values, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueBetween(String value1, String value2) {
            addCriterion("lasttimevalue between", value1, value2, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andLasttimevalueNotBetween(String value1, String value2) {
            addCriterion("lasttimevalue not between", value1, value2, "lasttimevalue");
            return (Criteria) this;
        }

        public Criteria andIsautofillIsNull() {
            addCriterion("isautofill is null");
            return (Criteria) this;
        }

        public Criteria andIsautofillIsNotNull() {
            addCriterion("isautofill is not null");
            return (Criteria) this;
        }

        public Criteria andIsautofillEqualTo(String value) {
            addCriterion("isautofill =", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillNotEqualTo(String value) {
            addCriterion("isautofill <>", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillGreaterThan(String value) {
            addCriterion("isautofill >", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillGreaterThanOrEqualTo(String value) {
            addCriterion("isautofill >=", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillLessThan(String value) {
            addCriterion("isautofill <", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillLessThanOrEqualTo(String value) {
            addCriterion("isautofill <=", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillLike(String value) {
            addCriterion("isautofill like", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillNotLike(String value) {
            addCriterion("isautofill not like", value, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillIn(List<String> values) {
            addCriterion("isautofill in", values, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillNotIn(List<String> values) {
            addCriterion("isautofill not in", values, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillBetween(String value1, String value2) {
            addCriterion("isautofill between", value1, value2, "isautofill");
            return (Criteria) this;
        }

        public Criteria andIsautofillNotBetween(String value1, String value2) {
            addCriterion("isautofill not between", value1, value2, "isautofill");
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