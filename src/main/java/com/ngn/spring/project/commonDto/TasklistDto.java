package com.ngn.spring.project.commonDto;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by USER on 3/29/2020.
 */
public class TasklistDto {
    private String Application_Number;
    private int Status_Id;
    private int Service_Id;
    private String Action_Date;
    private String Assigned_User_Id;
    private int Assigned_Priv_Id;
    private BigInteger groupTaskCount;
    private BigInteger mytaskCount;
    private BigInteger registration;
    private BigInteger renewal;
    private BigInteger otherService;
    private BigInteger cancellation;
    private BigInteger registrationConsultant;
    private BigInteger renewalConsultant;
    private BigInteger otherServiceConsultant;
    private BigInteger cancellationConsultant;
    private BigInteger registrationSPFirm;
    private BigInteger renewalSPFirm;
    private BigInteger otherServiceSPFirm;
    private BigInteger cancellationSPFirm;
    private BigInteger registrationSPtrade;
    private BigInteger renewalSPtrade;
    private BigInteger cancellationSPtrade;
    private BigInteger registrationArchitect;
    private BigInteger renewalArchitect;
    private BigInteger cancellationArchitect;
    private BigInteger registrationSurvey;
    private BigInteger renewalSurvey;
    private BigInteger cancellationSurvey;
    private BigInteger registrationEngineer;
    private BigInteger renewalEngineer;
    private BigInteger cancellationEngineer;
    private String messages;
    private Date appDate;
    private String cdbNo;
    private Timestamp rejectedDate;
    private String remarks;
    private String serviceName;
    private String appStatus;
    private String fullName;
    private String contactNo;
    private String commonDate;
    private BigInteger applicationNo;
    private BigInteger notificationCount;
    private BigDecimal totalAmount;
    private BigDecimal appliedAmount;


    public String getApplication_Number() {
        return Application_Number;
    }

    public void setApplication_Number(String application_Number) {
        Application_Number = application_Number;
    }

    public int getStatus_Id() {
        return Status_Id;
    }

    public void setStatus_Id(int status_Id) {
        Status_Id = status_Id;
    }

    public int getService_Id() {
        return Service_Id;
    }

    public void setService_Id(int service_Id) {
        Service_Id = service_Id;
    }

    public String getAction_Date() {
        return Action_Date;
    }

    public void setAction_Date(String action_Date) {
        Action_Date = action_Date;
    }

    public String getAssigned_User_Id() {
        return Assigned_User_Id;
    }

    public void setAssigned_User_Id(String assigned_User_Id) {
        Assigned_User_Id = assigned_User_Id;
    }

    public int getAssigned_Priv_Id() {
        return Assigned_Priv_Id;
    }

    public void setAssigned_Priv_Id(int assigned_Priv_Id) {
        Assigned_Priv_Id = assigned_Priv_Id;
    }

    public BigInteger getGroupTaskCount() {
        return groupTaskCount;
    }

    public void setGroupTaskCount(BigInteger groupTaskCount) {
        this.groupTaskCount = groupTaskCount;
    }

    public BigInteger getMytaskCount() {
        return mytaskCount;
    }

    public void setMytaskCount(BigInteger mytaskCount) {
        this.mytaskCount = mytaskCount;
    }

    public BigInteger getRegistration() {
        return registration;
    }

    public void setRegistration(BigInteger registration) {
        this.registration = registration;
    }

    public BigInteger getRenewal() {
        return renewal;
    }

    public void setRenewal(BigInteger renewal) {
        this.renewal = renewal;
    }

    public BigInteger getCancellation() {
        return cancellation;
    }

    public void setCancellation(BigInteger cancellation) {
        this.cancellation = cancellation;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public Timestamp getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Timestamp rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public BigInteger getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(BigInteger applicationNo) {
        this.applicationNo = applicationNo;
    }

    public BigInteger getOtherService() {
        return otherService;
    }

    public void setOtherService(BigInteger otherService) {
        this.otherService = otherService;
    }

    public BigInteger getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(BigInteger notificationCount) {
        this.notificationCount = notificationCount;
    }

    public BigInteger getRegistrationConsultant() {
        return registrationConsultant;
    }

    public void setRegistrationConsultant(BigInteger registrationConsultant) {
        this.registrationConsultant = registrationConsultant;
    }

    public BigInteger getRenewalConsultant() {
        return renewalConsultant;
    }

    public void setRenewalConsultant(BigInteger renewalConsultant) {
        this.renewalConsultant = renewalConsultant;
    }

    public BigInteger getOtherServiceConsultant() {
        return otherServiceConsultant;
    }

    public void setOtherServiceConsultant(BigInteger otherServiceConsultant) {
        this.otherServiceConsultant = otherServiceConsultant;
    }

    public BigInteger getCancellationConsultant() {
        return cancellationConsultant;
    }

    public void setCancellationConsultant(BigInteger cancellationConsultant) {
        this.cancellationConsultant = cancellationConsultant;
    }

    public BigInteger getRegistrationSPFirm() {
        return registrationSPFirm;
    }

    public void setRegistrationSPFirm(BigInteger registrationSPFirm) {
        this.registrationSPFirm = registrationSPFirm;
    }

    public BigInteger getRenewalSPFirm() {
        return renewalSPFirm;
    }

    public void setRenewalSPFirm(BigInteger renewalSPFirm) {
        this.renewalSPFirm = renewalSPFirm;
    }

    public BigInteger getOtherServiceSPFirm() {
        return otherServiceSPFirm;
    }

    public void setOtherServiceSPFirm(BigInteger otherServiceSPFirm) {
        this.otherServiceSPFirm = otherServiceSPFirm;
    }

    public BigInteger getCancellationSPFirm() {
        return cancellationSPFirm;
    }

    public void setCancellationSPFirm(BigInteger cancellationSPFirm) {
        this.cancellationSPFirm = cancellationSPFirm;
    }

    public BigInteger getRegistrationSPtrade() {
        return registrationSPtrade;
    }

    public void setRegistrationSPtrade(BigInteger registrationSPtrade) {
        this.registrationSPtrade = registrationSPtrade;
    }

    public BigInteger getRenewalSPtrade() {
        return renewalSPtrade;
    }

    public void setRenewalSPtrade(BigInteger renewalSPtrade) {
        this.renewalSPtrade = renewalSPtrade;
    }

    public BigInteger getCancellationSPtrade() {
        return cancellationSPtrade;
    }

    public void setCancellationSPtrade(BigInteger cancellationSPtrade) {
        this.cancellationSPtrade = cancellationSPtrade;
    }

    public BigInteger getRegistrationArchitect() {
        return registrationArchitect;
    }

    public void setRegistrationArchitect(BigInteger registrationArchitect) {
        this.registrationArchitect = registrationArchitect;
    }

    public BigInteger getRenewalArchitect() {
        return renewalArchitect;
    }

    public void setRenewalArchitect(BigInteger renewalArchitect) {
        this.renewalArchitect = renewalArchitect;
    }

    public BigInteger getCancellationArchitect() {
        return cancellationArchitect;
    }

    public void setCancellationArchitect(BigInteger cancellationArchitect) {
        this.cancellationArchitect = cancellationArchitect;
    }

    public BigInteger getRegistrationSurvey() {
        return registrationSurvey;
    }

    public void setRegistrationSurvey(BigInteger registrationSurvey) {
        this.registrationSurvey = registrationSurvey;
    }

    public BigInteger getRenewalSurvey() {
        return renewalSurvey;
    }

    public void setRenewalSurvey(BigInteger renewalSurvey) {
        this.renewalSurvey = renewalSurvey;
    }

    public BigInteger getCancellationSurvey() {
        return cancellationSurvey;
    }

    public void setCancellationSurvey(BigInteger cancellationSurvey) {
        this.cancellationSurvey = cancellationSurvey;
    }

    public BigInteger getRegistrationEngineer() {
        return registrationEngineer;
    }

    public void setRegistrationEngineer(BigInteger registrationEngineer) {
        this.registrationEngineer = registrationEngineer;
    }

    public BigInteger getRenewalEngineer() {
        return renewalEngineer;
    }

    public void setRenewalEngineer(BigInteger renewalEngineer) {
        this.renewalEngineer = renewalEngineer;
    }

    public BigInteger getCancellationEngineer() {
        return cancellationEngineer;
    }

    public void setCancellationEngineer(BigInteger cancellationEngineer) {
        this.cancellationEngineer = cancellationEngineer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCommonDate() {
        return commonDate;
    }

    public void setCommonDate(String commonDate) {
        this.commonDate = commonDate;
    }
}
