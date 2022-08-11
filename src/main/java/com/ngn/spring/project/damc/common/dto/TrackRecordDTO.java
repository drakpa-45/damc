package com.ngn.spring.project.damc.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ====================================================================
 * Created by Nima Yoezer on 10/29/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class TrackRecordDTO {

    private Integer years;
    private String cdbNo;
    private String firmName;
    private String pAgency;
    private String wClassification;
    //private BigDecimal bidAmount;
    private BigDecimal evalAmount;
    //private String dzongkhag;
    private String status;
    private String nWorks;
    private Date startDate;
    private Date completeDate;
    private String rating;
    private BigDecimal scoring;
    private String recordId;
    private String workId;
    private BigDecimal evaluationOnTime;
    private BigDecimal evaluationQty;

    private String RowId;
    private String WorkOrderNo;
    private String Type;
    private String Remarks;
    private String NameOfWork;
    private String DescriptionOfWork;

    private String ContractPeriod;
    private String WorkStartDate;
    private String WorkCompletionDate;
    private String CompletionDateFinal;
    private String ActualEndDate;
    private String FinalAmount;

    private String ContractPriceFinal;
    private String AwardedAmount;
    private String CrpContractorFinalId;
    private String OntimeCompletionScore;
    private String ReferenceNo;
    private String BidAmount;

    private String EvaluatedAmount;
    private String ProcuringAgencyId;
    private String ProcuringAgencyCode;
    private String ProcuringAgency;
    private String ProjectCategory;
    private String ParentProcuringAgency;

    private String classification;
    private String CDBNo;
    private String Dzongkhag;
    private String Contractor;
    private String QualityOfExecutionScore;
    private String WorkStatus;
    private String RecordId;

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getpAgency() {
        return pAgency;
    }

    public void setpAgency(String pAgency) {
        this.pAgency = pAgency;
    }

    public String getwClassification() {
        return wClassification;
    }

    public void setwClassification(String wClassification) {
        this.wClassification = wClassification;
    }

    public BigDecimal getEvalAmount() {
        return evalAmount;
    }

    public void setEvalAmount(BigDecimal evalAmount) {
        this.evalAmount = evalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getnWorks() {
        return nWorks;
    }

    public void setnWorks(String nWorks) {
        this.nWorks = nWorks;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public BigDecimal getScoring() {
        return scoring;
    }

    public void setScoring(BigDecimal scoring) {
        this.scoring = scoring;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public BigDecimal getEvaluationOnTime() {
        return evaluationOnTime;
    }

    public void setEvaluationOnTime(BigDecimal evaluationOnTime) {
        this.evaluationOnTime = evaluationOnTime;
    }

    public BigDecimal getEvaluationQty() {
        return evaluationQty;
    }

    public void setEvaluationQty(BigDecimal evaluationQty) {
        this.evaluationQty = evaluationQty;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getRowId() {
        return RowId;
    }

    public void setRowId(String rowId) {
        RowId = rowId;
    }

    public String getWorkOrderNo() {
        return WorkOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        WorkOrderNo = workOrderNo;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNameOfWork() {
        return NameOfWork;
    }

    public void setNameOfWork(String nameOfWork) {
        NameOfWork = nameOfWork;
    }

    public String getDescriptionOfWork() {
        return DescriptionOfWork;
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        DescriptionOfWork = descriptionOfWork;
    }

    public String getContractPeriod() {
        return ContractPeriod;
    }

    public void setContractPeriod(String contractPeriod) {
        ContractPeriod = contractPeriod;
    }

    public String getWorkStartDate() {
        return WorkStartDate;
    }

    public void setWorkStartDate(String workStartDate) {
        WorkStartDate = workStartDate;
    }

    public String getWorkCompletionDate() {
        return WorkCompletionDate;
    }

    public void setWorkCompletionDate(String workCompletionDate) {
        WorkCompletionDate = workCompletionDate;
    }

    public String getCompletionDateFinal() {
        return CompletionDateFinal;
    }

    public void setCompletionDateFinal(String completionDateFinal) {
        CompletionDateFinal = completionDateFinal;
    }

    public String getActualEndDate() {
        return ActualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        ActualEndDate = actualEndDate;
    }

    public String getFinalAmount() {
        return FinalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        FinalAmount = finalAmount;
    }

    public String getContractPriceFinal() {
        return ContractPriceFinal;
    }

    public void setContractPriceFinal(String contractPriceFinal) {
        ContractPriceFinal = contractPriceFinal;
    }

    public String getAwardedAmount() {
        return AwardedAmount;
    }

    public void setAwardedAmount(String awardedAmount) {
        AwardedAmount = awardedAmount;
    }

    public String getCrpContractorFinalId() {
        return CrpContractorFinalId;
    }

    public void setCrpContractorFinalId(String crpContractorFinalId) {
        CrpContractorFinalId = crpContractorFinalId;
    }

    public String getOntimeCompletionScore() {
        return OntimeCompletionScore;
    }

    public void setOntimeCompletionScore(String ontimeCompletionScore) {
        OntimeCompletionScore = ontimeCompletionScore;
    }

    public String getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        ReferenceNo = referenceNo;
    }

    public String getEvaluatedAmount() {
        return EvaluatedAmount;
    }

    public void setEvaluatedAmount(String evaluatedAmount) {
        EvaluatedAmount = evaluatedAmount;
    }

    public String getProcuringAgencyId() {
        return ProcuringAgencyId;
    }

    public void setProcuringAgencyId(String procuringAgencyId) {
        ProcuringAgencyId = procuringAgencyId;
    }

    public String getProcuringAgencyCode() {
        return ProcuringAgencyCode;
    }

    public void setProcuringAgencyCode(String procuringAgencyCode) {
        ProcuringAgencyCode = procuringAgencyCode;
    }

    public String getProcuringAgency() {
        return ProcuringAgency;
    }

    public void setProcuringAgency(String procuringAgency) {
        ProcuringAgency = procuringAgency;
    }

    public String getProjectCategory() {
        return ProjectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        ProjectCategory = projectCategory;
    }

    public String getParentProcuringAgency() {
        return ParentProcuringAgency;
    }

    public void setParentProcuringAgency(String parentProcuringAgency) {
        ParentProcuringAgency = parentProcuringAgency;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCDBNo() {
        return CDBNo;
    }

    public void setCDBNo(String CDBNo) {
        this.CDBNo = CDBNo;
    }

    public String getContractor() {
        return Contractor;
    }

    public void setContractor(String contractor) {
        Contractor = contractor;
    }

    public String getQualityOfExecutionScore() {
        return QualityOfExecutionScore;
    }

    public void setQualityOfExecutionScore(String qualityOfExecutionScore) {
        QualityOfExecutionScore = qualityOfExecutionScore;
    }

    public String getWorkStatus() {
        return WorkStatus;
    }

    public void setWorkStatus(String workStatus) {
        WorkStatus = workStatus;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getBidAmount() {
        return BidAmount;
    }

    public void setBidAmount(String bidAmount) {
        BidAmount = bidAmount;
    }

    public String getDzongkhag() {
        return Dzongkhag;
    }

    public void setDzongkhag(String dzongkhag) {
        Dzongkhag = dzongkhag;
    }
}
