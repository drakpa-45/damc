package com.ngn.spring.project.damc.common.dto;

/**
 * Created by USER on 27-Jan-21.
 */
public class CdbDTO {
    private String consultantFirmname;
    private String consultantCDBNo;
    private String contractorFirmname;
    private String contractorCDBNo;
    private String spFirmname;
    private String spCDBNo;

    public String getConsultantFirmname() {
        return consultantFirmname;
    }

    public void setConsultantFirmname(String consultantFirmname) {
        this.consultantFirmname = consultantFirmname;
    }

    public String getConsultantCDBNo() {
        return consultantCDBNo;
    }

    public void setConsultantCDBNo(String consultantCDBNo) {
        this.consultantCDBNo = consultantCDBNo;
    }

    public String getContractorFirmname() {
        return contractorFirmname;
    }

    public void setContractorFirmname(String contractorFirmname) {
        this.contractorFirmname = contractorFirmname;
    }

    public String getContractorCDBNo() {
        return contractorCDBNo;
    }

    public void setContractorCDBNo(String contractorCDBNo) {
        this.contractorCDBNo = contractorCDBNo;
    }

    public String getSpFirmname() {
        return spFirmname;
    }

    public void setSpFirmname(String spFirmname) {
        this.spFirmname = spFirmname;
    }

    public String getSpCDBNo() {
        return spCDBNo;
    }

    public void setSpCDBNo(String spCDBNo) {
        this.spCDBNo = spCDBNo;
    }
}
