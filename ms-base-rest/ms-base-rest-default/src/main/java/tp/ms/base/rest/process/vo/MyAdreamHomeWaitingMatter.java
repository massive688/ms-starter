package tp.ms.base.rest.process.vo;

import java.io.Serializable;
import tp.ms.base.rest.resource.vo.ChildBaseVO;

public class MyAdreamHomeWaitingMatter extends ChildBaseVO implements Serializable {
    private String pkHomeWaitingMatter;

    private String pkHomeRoot;

    private String proposer;

    private String proposerPost;

    private String theme;

    private String processId;

    private String processUrl;

    private String billtype;

    private String formContentId;

    private String taskId;

    private String nodeName;

    private static final long serialVersionUID = 1L;

    public String getPkHomeWaitingMatter() {
        return pkHomeWaitingMatter;
    }

    public void setPkHomeWaitingMatter(String pkHomeWaitingMatter) {
        this.pkHomeWaitingMatter = pkHomeWaitingMatter == null ? null : pkHomeWaitingMatter.trim();
    }

    public String getPkHomeRoot() {
        return pkHomeRoot;
    }

    public void setPkHomeRoot(String pkHomeRoot) {
        this.pkHomeRoot = pkHomeRoot == null ? null : pkHomeRoot.trim();
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer == null ? null : proposer.trim();
    }

    public String getProposerPost() {
        return proposerPost;
    }

    public void setProposerPost(String proposerPost) {
        this.proposerPost = proposerPost == null ? null : proposerPost.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId == null ? null : processId.trim();
    }

    public String getProcessUrl() {
        return processUrl;
    }

    public void setProcessUrl(String processUrl) {
        this.processUrl = processUrl == null ? null : processUrl.trim();
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public String getFormContentId() {
        return formContentId;
    }

    public void setFormContentId(String formContentId) {
        this.formContentId = formContentId == null ? null : formContentId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }
	@Override
	public String getParentKey() {
		return this.pkHomeRoot;
	}

	@Override
	public void setParentKey(String parentKey) {
		this.pkHomeRoot = parentKey;
	}

	@Override
	public String getTable() {
		return "my_adream_home_waiting_matters";
	}

	@Override
	public void setPrimaryKey(String key) {
		this.pkHomeWaitingMatter = key;
	}

	@Override
	public String getPrimaryKey() {
		return this.pkHomeWaitingMatter;
	}
}
