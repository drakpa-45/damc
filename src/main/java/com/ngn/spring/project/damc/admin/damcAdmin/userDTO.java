package com.ngn.spring.project.damc.admin.damcAdmin;

/**
 * ==================================================================================
 * Created by Pema Drakpa on 20/11/2021.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class userDTO {
    private String id;
    private String cidNo;
    private String name;
    private Character sex;
    private String dzongkhagName;
    private String gewogName;
    private String village;
    private String email;
    private String designationId;
    private String moblieNo;
    private String agency;
    private String role;
    private String region;
    private String designationName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public String getDzongkhagName() {
        return dzongkhagName;
    }

    public void setDzongkhagName(String dzongkhagName) {
        this.dzongkhagName = dzongkhagName;
    }

    public String getGewogName() {
        return gewogName;
    }

    public void setGewogName(String gewog) {
        this.gewogName = gewogName;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoblieNo() {
        return moblieNo;
    }

    public void setMoblieNo(String moblieNo) {
        this.moblieNo = moblieNo;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
