package finance.web.controller.noauth.book.vo;


import java.io.Serializable;

/**
 * @program: finance-server
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-09-14 09:16
 **/
public class BookCommentVO implements Serializable {
    private static final long serialVersionUID = -3260283054866923560L;

    private String commentId;
    private String headLink;
    private String secPhone;
    private String score;
    private String likeNum;
    private String comment;
    private Boolean isLike;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getHeadLink() {
        return headLink;
    }

    public void setHeadLink(String headLink) {
        this.headLink = headLink;
    }

    public String getSecPhone() {
        return secPhone;
    }

    public void setSecPhone(String secPhone) {
        this.secPhone = secPhone;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}
