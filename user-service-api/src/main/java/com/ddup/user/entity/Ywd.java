package com.ddup.user.entity;

/**
 * @desc: 云网点
 * @author: wty
 * @create: 2021-02-06 16:37
 */

public class Ywd extends BaseEntity<Ywd> {
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 云网点名称
     */
    private String ywdName;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 01：银行员工；02：商户；03 个人用户
     */
    private String ywdUserType;
    /**
     * 邀请人云网点code（上线云网点code）
     */
    private String inviteYwdCode;
    /**
     * 云网点层级（银行员工为根云网点，默认为层级：0）
     */
    private Integer ywdLevel;
    /**
     * 云网点 id 层级路径
     */
    private String devPath;
    /**
     * 根银行员工对应的ag_bank_employee的code
     */
    private String empCode;
    /**
     * 根银行员工empNo，管户经理员工代码
     */
    private String empNo;

    /**
     * 根银行员工的部门编号，管户机构代码
     */
    private String deptCode;
    /**
     * 云网点用户手机号
     */
    private String telephone;
    /**
     * 状态 01：正常，02：关闭，03：暂停
     */
    private String ywdStatus;
    /**
     * 社区编号
     */
    private String cmtyCode;
    /**
     * 备注
     */
    private String remark;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getYwdName() {
        return ywdName;
    }

    public void setYwdName(String ywdName) {
        this.ywdName = ywdName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getYwdUserType() {
        return ywdUserType;
    }

    public void setYwdUserType(String ywdUserType) {
        this.ywdUserType = ywdUserType;
    }

    public String getInviteYwdCode() {
        return inviteYwdCode;
    }

    public void setInviteYwdCode(String inviteYwdCode) {
        this.inviteYwdCode = inviteYwdCode;
    }

    public Integer getYwdLevel() {
        return ywdLevel;
    }

    public void setYwdLevel(Integer ywdLevel) {
        this.ywdLevel = ywdLevel;
    }

    public String getDevPath() {
        return devPath;
    }

    public void setDevPath(String devPath) {
        this.devPath = devPath;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getYwdStatus() {
        return ywdStatus;
    }

    public void setYwdStatus(String ywdStatus) {
        this.ywdStatus = ywdStatus;
    }

    public String getCmtyCode() {
        return cmtyCode;
    }

    public void setCmtyCode(String cmtyCode) {
        this.cmtyCode = cmtyCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
