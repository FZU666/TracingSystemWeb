package com.psf.core.model;

import java.io.Serializable;

public class Target implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.tid
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    private Integer tid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.uid
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.targetName
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    private String targetname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.imgUrl
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    private String imgurl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.eigenvalue
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    private String eigenvalue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.tid
     *
     * @return the value of target.tid
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.tid
     *
     * @param tid the value for target.tid
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.uid
     *
     * @return the value of target.uid
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.uid
     *
     * @param uid the value for target.uid
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.targetName
     *
     * @return the value of target.targetName
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public String getTargetname() {
        return targetname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.targetName
     *
     * @param targetname the value for target.targetName
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.imgUrl
     *
     * @return the value of target.imgUrl
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.imgUrl
     *
     * @param imgurl the value for target.imgUrl
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.eigenvalue
     *
     * @return the value of target.eigenvalue
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public String getEigenvalue() {
        return eigenvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.eigenvalue
     *
     * @param eigenvalue the value for target.eigenvalue
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    public void setEigenvalue(String eigenvalue) {
        this.eigenvalue = eigenvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", uid=").append(uid);
        sb.append(", targetname=").append(targetname);
        sb.append(", imgurl=").append(imgurl);
        sb.append(", eigenvalue=").append(eigenvalue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}