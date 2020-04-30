package com.lcyanxi.model;


import java.io.Serializable;
import java.util.Date;


public class UserLesson implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 班级课程ID
     */
    private Integer classCourseId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 父班级ID
     */
    private Integer parentClassId;

    /**
     * 课次ID（对应pe_class_lesson的主键ID）
     */
    private Integer lessonId;

    /**
     * 购买时间
     */
    private Date buyTime;

    /**
     * 购买状态(0:未购买,1:已购买)
     */
    private Boolean buyStatus;

    /**
     * 状态 ( 1: 生效， 0 失效 ) 后期有可能会删除学习路径节点
     */
    private int status = 1;
    private Date createTime;
    /**
     * 创建人账号
     */
    private String createUid;
    private String createUsername;
    private String updateUsername;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人账号
     */
    private String updateUid;

    /**
     * 删除状态 1:已删除；0：未删除
     */
    private Boolean isDeleted = false;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassCourseId() {
        return classCourseId;
    }

    public void setClassCourseId(Integer classCourseId) {
        this.classCourseId = classCourseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getParentClassId() {
        return parentClassId;
    }

    public void setParentClassId(Integer parentClassId) {
        this.parentClassId = parentClassId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Boolean getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(Boolean buyStatus) {
        this.buyStatus = buyStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
