package com.ngn.spring.project.damc.common.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by USER on 12-Mar-21.
 */
@Entity
@Table(name = "viewcontractorstrackrecords")
public class TrackRecord {

    @Id
    private String RowId;
    private String WorkOrderNo;
    private String Type;
    private String Remarks;
    private String WorkId;
    private String NameOfWork;
    private String year;
    private String DescriptionOfWork;
    private BigDecimal ContractPeriod;
    private Date WorkStartDate;
    private Date WorkCompletionDate;
    private Date CompletionDateFinal;
    private Date ActualEndDate;
    private BigDecimal FinalAmount;

    private BigDecimal ContractPriceFinal;
    private BigDecimal AwardedAmount;
    private String CrpContractorFinalId;
    private BigDecimal OntimeCompletionScore;
    private Integer ReferenceNo;
    private BigDecimal BidAmount;
    private BigDecimal EvaluatedAmount;
    private String ProcuringAgencyId;
    private String ProcuringAgencyCode;
    private String ProcuringAgency;
    private String ProjectCategory;
    private String ParentProcuringAgency;
    private String classification;
    private String CDBNo;
    private String Dzongkhag;
    private String Contractor;
    private BigDecimal QualityOfExecutionScore;
    private String WorkStatus;
    private String RecordId;
    private Integer LDImposed;
    private Integer LDNoOfDays;
    private String LDAmount;
    private Integer Hindrance;
    private Integer HindranceNoOfDays;
    private String APSFormPath;

    @Transient
    private String cidNo;
    @Transient
    private String hrName;
    @Transient
    private String name;



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

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
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

    public BigDecimal getContractPeriod() {
        return ContractPeriod;
    }

    public void setContractPeriod(BigDecimal contractPeriod) {
        ContractPeriod = contractPeriod;
    }

    public Date getWorkStartDate() {
        return WorkStartDate;
    }

    public void setWorkStartDate(Date workStartDate) {
        WorkStartDate = workStartDate;
    }

    public Date getWorkCompletionDate() {
        return WorkCompletionDate;
    }

    public void setWorkCompletionDate(Date workCompletionDate) {
        WorkCompletionDate = workCompletionDate;
    }

    public Date getCompletionDateFinal() {
        return CompletionDateFinal;
    }

    public void setCompletionDateFinal(Date completionDateFinal) {
        CompletionDateFinal = completionDateFinal;
    }

    public Date getActualEndDate() {
        return ActualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        ActualEndDate = actualEndDate;
    }

    public BigDecimal getFinalAmount() {
        return FinalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        FinalAmount = finalAmount;
    }

    public BigDecimal getContractPriceFinal() {
        return ContractPriceFinal;
    }

    public void setContractPriceFinal(BigDecimal contractPriceFinal) {
        ContractPriceFinal = contractPriceFinal;
    }

    public BigDecimal getAwardedAmount() {
        return AwardedAmount;
    }

    public void setAwardedAmount(BigDecimal awardedAmount) {
        AwardedAmount = awardedAmount;
    }

    public String getCrpContractorFinalId() {
        return CrpContractorFinalId;
    }

    public void setCrpContractorFinalId(String crpContractorFinalId) {
        CrpContractorFinalId = crpContractorFinalId;
    }

    public BigDecimal getOntimeCompletionScore() {
        return OntimeCompletionScore;
    }

    public void setOntimeCompletionScore(BigDecimal ontimeCompletionScore) {
        OntimeCompletionScore = ontimeCompletionScore;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(Integer referenceNo) {
        ReferenceNo = referenceNo;
    }

    public BigDecimal getBidAmount() {
        return BidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        BidAmount = bidAmount;
    }

    public BigDecimal getEvaluatedAmount() {
        return EvaluatedAmount;
    }

    public void setEvaluatedAmount(BigDecimal evaluatedAmount) {
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

    public String getDzongkhag() {
        return Dzongkhag;
    }

    public void setDzongkhag(String dzongkhag) {
        Dzongkhag = dzongkhag;
    }

    public String getContractor() {
        return Contractor;
    }

    public void setContractor(String contractor) {
        Contractor = contractor;
    }

    public BigDecimal getQualityOfExecutionScore() {
        return QualityOfExecutionScore;
    }

    public void setQualityOfExecutionScore(BigDecimal qualityOfExecutionScore) {
        QualityOfExecutionScore = qualityOfExecutionScore;
    }

    public String getWorkStatus() {
        return WorkStatus;
    }

    public void setWorkStatus(String workStatus) {
        WorkStatus = workStatus;
    }

    public String getRecordId() {
        return RecordId;
    }

    public void setRecordId(String recordId) {
        RecordId = recordId;
    }

    public String getWorkId() {
        return WorkId;
    }

    public void setWorkId(String workId) {
        WorkId = workId;
    }

    public Integer getLDImposed() {
        return LDImposed;
    }

    public void setLDImposed(Integer LDImposed) {
        this.LDImposed = LDImposed;
    }

    public Integer getLDNoOfDays() {
        return LDNoOfDays;
    }

    public void setLDNoOfDays(Integer LDNoOfDays) {
        this.LDNoOfDays = LDNoOfDays;
    }

    public String getLDAmount() {
        return LDAmount;
    }

    public void setLDAmount(String LDAmount) {
        this.LDAmount = LDAmount;
    }

    public Integer getHindrance() {
        return Hindrance;
    }

    public void setHindrance(Integer hindrance) {
        Hindrance = hindrance;
    }

    public Integer getHindranceNoOfDays() {
        return HindranceNoOfDays;
    }

    public void setHindranceNoOfDays(Integer hindranceNoOfDays) {
        HindranceNoOfDays = hindranceNoOfDays;
    }

    public String getAPSFormPath() {
        return APSFormPath;
    }

    public void setAPSFormPath(String APSFormPath) {
        this.APSFormPath = APSFormPath;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
