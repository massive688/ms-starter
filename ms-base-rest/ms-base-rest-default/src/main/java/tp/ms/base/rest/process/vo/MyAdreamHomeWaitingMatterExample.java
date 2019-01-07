package tp.ms.base.rest.process.vo;

import java.util.ArrayList;
import java.util.List;

import tp.ms.common.bean.vo.BaseExample;

public class MyAdreamHomeWaitingMatterExample  extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MyAdreamHomeWaitingMatterExample() {
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

        public Criteria andPkHomeWaitingMatterIsNull() {
            addCriterion("pk_home_waiting_matter is null");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterIsNotNull() {
            addCriterion("pk_home_waiting_matter is not null");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterEqualTo(String value) {
            addCriterion("pk_home_waiting_matter =", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterNotEqualTo(String value) {
            addCriterion("pk_home_waiting_matter <>", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterGreaterThan(String value) {
            addCriterion("pk_home_waiting_matter >", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterGreaterThanOrEqualTo(String value) {
            addCriterion("pk_home_waiting_matter >=", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterLessThan(String value) {
            addCriterion("pk_home_waiting_matter <", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterLessThanOrEqualTo(String value) {
            addCriterion("pk_home_waiting_matter <=", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterLike(String value) {
            addCriterion("pk_home_waiting_matter like", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterNotLike(String value) {
            addCriterion("pk_home_waiting_matter not like", value, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterIn(List<String> values) {
            addCriterion("pk_home_waiting_matter in", values, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterNotIn(List<String> values) {
            addCriterion("pk_home_waiting_matter not in", values, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterBetween(String value1, String value2) {
            addCriterion("pk_home_waiting_matter between", value1, value2, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeWaitingMatterNotBetween(String value1, String value2) {
            addCriterion("pk_home_waiting_matter not between", value1, value2, "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootIsNull() {
            addCriterion("pk_home_root is null");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootIsNotNull() {
            addCriterion("pk_home_root is not null");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootEqualTo(String value) {
            addCriterion("pk_home_root =", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootNotEqualTo(String value) {
            addCriterion("pk_home_root <>", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootGreaterThan(String value) {
            addCriterion("pk_home_root >", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootGreaterThanOrEqualTo(String value) {
            addCriterion("pk_home_root >=", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootLessThan(String value) {
            addCriterion("pk_home_root <", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootLessThanOrEqualTo(String value) {
            addCriterion("pk_home_root <=", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootLike(String value) {
            addCriterion("pk_home_root like", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootNotLike(String value) {
            addCriterion("pk_home_root not like", value, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootIn(List<String> values) {
            addCriterion("pk_home_root in", values, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootNotIn(List<String> values) {
            addCriterion("pk_home_root not in", values, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootBetween(String value1, String value2) {
            addCriterion("pk_home_root between", value1, value2, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootNotBetween(String value1, String value2) {
            addCriterion("pk_home_root not between", value1, value2, "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andProposerIsNull() {
            addCriterion("proposer is null");
            return (Criteria) this;
        }

        public Criteria andProposerIsNotNull() {
            addCriterion("proposer is not null");
            return (Criteria) this;
        }

        public Criteria andProposerEqualTo(String value) {
            addCriterion("proposer =", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotEqualTo(String value) {
            addCriterion("proposer <>", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerGreaterThan(String value) {
            addCriterion("proposer >", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerGreaterThanOrEqualTo(String value) {
            addCriterion("proposer >=", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerLessThan(String value) {
            addCriterion("proposer <", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerLessThanOrEqualTo(String value) {
            addCriterion("proposer <=", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerLike(String value) {
            addCriterion("proposer like", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotLike(String value) {
            addCriterion("proposer not like", value, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerIn(List<String> values) {
            addCriterion("proposer in", values, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotIn(List<String> values) {
            addCriterion("proposer not in", values, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerBetween(String value1, String value2) {
            addCriterion("proposer between", value1, value2, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerNotBetween(String value1, String value2) {
            addCriterion("proposer not between", value1, value2, "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerPostIsNull() {
            addCriterion("proposer_post is null");
            return (Criteria) this;
        }

        public Criteria andProposerPostIsNotNull() {
            addCriterion("proposer_post is not null");
            return (Criteria) this;
        }

        public Criteria andProposerPostEqualTo(String value) {
            addCriterion("proposer_post =", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostNotEqualTo(String value) {
            addCriterion("proposer_post <>", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostGreaterThan(String value) {
            addCriterion("proposer_post >", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostGreaterThanOrEqualTo(String value) {
            addCriterion("proposer_post >=", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostLessThan(String value) {
            addCriterion("proposer_post <", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostLessThanOrEqualTo(String value) {
            addCriterion("proposer_post <=", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostLike(String value) {
            addCriterion("proposer_post like", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostNotLike(String value) {
            addCriterion("proposer_post not like", value, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostIn(List<String> values) {
            addCriterion("proposer_post in", values, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostNotIn(List<String> values) {
            addCriterion("proposer_post not in", values, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostBetween(String value1, String value2) {
            addCriterion("proposer_post between", value1, value2, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andProposerPostNotBetween(String value1, String value2) {
            addCriterion("proposer_post not between", value1, value2, "proposerPost");
            return (Criteria) this;
        }

        public Criteria andThemeIsNull() {
            addCriterion("theme is null");
            return (Criteria) this;
        }

        public Criteria andThemeIsNotNull() {
            addCriterion("theme is not null");
            return (Criteria) this;
        }

        public Criteria andThemeEqualTo(String value) {
            addCriterion("theme =", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotEqualTo(String value) {
            addCriterion("theme <>", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeGreaterThan(String value) {
            addCriterion("theme >", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeGreaterThanOrEqualTo(String value) {
            addCriterion("theme >=", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLessThan(String value) {
            addCriterion("theme <", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLessThanOrEqualTo(String value) {
            addCriterion("theme <=", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeLike(String value) {
            addCriterion("theme like", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotLike(String value) {
            addCriterion("theme not like", value, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeIn(List<String> values) {
            addCriterion("theme in", values, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotIn(List<String> values) {
            addCriterion("theme not in", values, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeBetween(String value1, String value2) {
            addCriterion("theme between", value1, value2, "theme");
            return (Criteria) this;
        }

        public Criteria andThemeNotBetween(String value1, String value2) {
            addCriterion("theme not between", value1, value2, "theme");
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

        public Criteria andProcessUrlIsNull() {
            addCriterion("process_url is null");
            return (Criteria) this;
        }

        public Criteria andProcessUrlIsNotNull() {
            addCriterion("process_url is not null");
            return (Criteria) this;
        }

        public Criteria andProcessUrlEqualTo(String value) {
            addCriterion("process_url =", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlNotEqualTo(String value) {
            addCriterion("process_url <>", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlGreaterThan(String value) {
            addCriterion("process_url >", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlGreaterThanOrEqualTo(String value) {
            addCriterion("process_url >=", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlLessThan(String value) {
            addCriterion("process_url <", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlLessThanOrEqualTo(String value) {
            addCriterion("process_url <=", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlLike(String value) {
            addCriterion("process_url like", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlNotLike(String value) {
            addCriterion("process_url not like", value, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlIn(List<String> values) {
            addCriterion("process_url in", values, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlNotIn(List<String> values) {
            addCriterion("process_url not in", values, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlBetween(String value1, String value2) {
            addCriterion("process_url between", value1, value2, "processUrl");
            return (Criteria) this;
        }

        public Criteria andProcessUrlNotBetween(String value1, String value2) {
            addCriterion("process_url not between", value1, value2, "processUrl");
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

        public Criteria andFormContentIdIsNull() {
            addCriterion("form_content_id is null");
            return (Criteria) this;
        }

        public Criteria andFormContentIdIsNotNull() {
            addCriterion("form_content_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormContentIdEqualTo(String value) {
            addCriterion("form_content_id =", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdNotEqualTo(String value) {
            addCriterion("form_content_id <>", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdGreaterThan(String value) {
            addCriterion("form_content_id >", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdGreaterThanOrEqualTo(String value) {
            addCriterion("form_content_id >=", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdLessThan(String value) {
            addCriterion("form_content_id <", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdLessThanOrEqualTo(String value) {
            addCriterion("form_content_id <=", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdLike(String value) {
            addCriterion("form_content_id like", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdNotLike(String value) {
            addCriterion("form_content_id not like", value, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdIn(List<String> values) {
            addCriterion("form_content_id in", values, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdNotIn(List<String> values) {
            addCriterion("form_content_id not in", values, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdBetween(String value1, String value2) {
            addCriterion("form_content_id between", value1, value2, "formContentId");
            return (Criteria) this;
        }

        public Criteria andFormContentIdNotBetween(String value1, String value2) {
            addCriterion("form_content_id not between", value1, value2, "formContentId");
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

        public Criteria andPkHomeWaitingMatterLikeInsensitive(String value) {
            addCriterion("upper(pk_home_waiting_matter) like", value.toUpperCase(), "pkHomeWaitingMatter");
            return (Criteria) this;
        }

        public Criteria andPkHomeRootLikeInsensitive(String value) {
            addCriterion("upper(pk_home_root) like", value.toUpperCase(), "pkHomeRoot");
            return (Criteria) this;
        }

        public Criteria andProposerLikeInsensitive(String value) {
            addCriterion("upper(proposer) like", value.toUpperCase(), "proposer");
            return (Criteria) this;
        }

        public Criteria andProposerPostLikeInsensitive(String value) {
            addCriterion("upper(proposer_post) like", value.toUpperCase(), "proposerPost");
            return (Criteria) this;
        }

        public Criteria andThemeLikeInsensitive(String value) {
            addCriterion("upper(theme) like", value.toUpperCase(), "theme");
            return (Criteria) this;
        }

        public Criteria andProcessIdLikeInsensitive(String value) {
            addCriterion("upper(process_id) like", value.toUpperCase(), "processId");
            return (Criteria) this;
        }

        public Criteria andProcessUrlLikeInsensitive(String value) {
            addCriterion("upper(process_url) like", value.toUpperCase(), "processUrl");
            return (Criteria) this;
        }

        public Criteria andBilltypeLikeInsensitive(String value) {
            addCriterion("upper(billtype) like", value.toUpperCase(), "billtype");
            return (Criteria) this;
        }

        public Criteria andFormContentIdLikeInsensitive(String value) {
            addCriterion("upper(form_content_id) like", value.toUpperCase(), "formContentId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLikeInsensitive(String value) {
            addCriterion("upper(task_id) like", value.toUpperCase(), "taskId");
            return (Criteria) this;
        }

        public Criteria andNodeNameLikeInsensitive(String value) {
            addCriterion("upper(node_name) like", value.toUpperCase(), "nodeName");
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
