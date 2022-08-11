package com.ngn.spring.project.damc.common.dto;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 3/7/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class PersonalInfoDTO {
    private String cidNo;
    private String fullName;
    private String sex;
    private String dzongkhagId;
    private String gowegId;
    private String villageId;
    private String dzongkhagNmae;
    private String gowegName;
    private String villageName;
    private String dob;

    private String regEmail;
    private String RegMobileNo;
    private String fullname;
    private String id;

    private String cdbNo;
    private String remarks;

    private List<EmployeeDetailsDTO> employeeDetailsDTOs;

    private List<GovCopDTO> govCopDTOs;

    private List<CdbDTO> cdbDTOs;
    private List<CdbDTO> cdbDTOs1;
    private List<CdbDTO> cdbDTOs2;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDzongkhagId() {
        return dzongkhagId;
    }

    public void setDzongkhagId(String dzongkhagId) {
        this.dzongkhagId = dzongkhagId;
    }

    public String getGowegId() {
        return gowegId;
    }

    public void setGowegId(String gowegId) {
        this.gowegId = gowegId;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getDzongkhagNmae() {
        return dzongkhagNmae;
    }

    public void setDzongkhagNmae(String dzongkhagNmae) {
        this.dzongkhagNmae = dzongkhagNmae;
    }

    public String getGowegName() {
        return gowegName;
    }

    public void setGowegName(String gowegName) {
        this.gowegName = gowegName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegMobileNo() {
        return RegMobileNo;
    }

    public void setRegMobileNo(String regMobileNo) {
        RegMobileNo = regMobileNo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        remarks = remarks;
    }

    public List<EmployeeDetailsDTO> getEmployeeDetailsDTOs() {
        return employeeDetailsDTOs;
    }

    public void setEmployeeDetailsDTOs(List<EmployeeDetailsDTO> employeeDetailsDTOs) {
        this.employeeDetailsDTOs = employeeDetailsDTOs;
    }

    public List<GovCopDTO> getGovCopDTOs() {
        return govCopDTOs;
    }

    public void setGovCopDTOs(List<GovCopDTO> govCopDTOs) {
        this.govCopDTOs = govCopDTOs;
    }

    public List<CdbDTO> getCdbDTOs() {
        return cdbDTOs;
    }

    public void setCdbDTOs(List<CdbDTO> cdbDTOs) {
        this.cdbDTOs = cdbDTOs;
    }

    public List<CdbDTO> getCdbDTOs1() {
        return cdbDTOs1;
    }

    public void setCdbDTOs1(List<CdbDTO> cdbDTOs1) {
        this.cdbDTOs1 = cdbDTOs1;
    }

    public List<CdbDTO> getCdbDTOs2() {
        return cdbDTOs2;
    }

    public void setCdbDTOs2(List<CdbDTO> cdbDTOs2) {
        this.cdbDTOs2 = cdbDTOs2;
    }
}
