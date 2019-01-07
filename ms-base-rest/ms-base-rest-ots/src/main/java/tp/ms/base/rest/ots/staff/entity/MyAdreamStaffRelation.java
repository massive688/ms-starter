package tp.ms.base.rest.ots.staff.entity;

import java.io.Serializable;

public class MyAdreamStaffRelation implements Serializable {
    private String pkUser;

    private String pkPost;

    private Integer isMain;

    private String higherUp;

    private static final long serialVersionUID = 1L;

    public String getPkUser() {
        return pkUser;
    }

    public void setPkUser(String pkUser) {
        this.pkUser = pkUser == null ? null : pkUser.trim();
    }

    public String getPkPost() {
        return pkPost;
    }

    public void setPkPost(String pkPost) {
        this.pkPost = pkPost == null ? null : pkPost.trim();
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public String getHigherUp() {
        return higherUp;
    }

    public void setHigherUp(String higherUp) {
        this.higherUp = higherUp == null ? null : higherUp.trim();
    }
}