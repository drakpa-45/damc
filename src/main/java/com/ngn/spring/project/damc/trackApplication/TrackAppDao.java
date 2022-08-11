package com.ngn.spring.project.damc.trackApplication;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.commonDto.TasklistDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18-Dec-20.
 */
@Repository
public class TrackAppDao extends BaseDao{

    @Transactional
    public List<TasklistDto> populateTrackApp(String applicationNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN a.RemarksByRejector \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN a.RemarksByVerifier \n" +
                    "WHEN a.SysApproverUserId != '' THEN a.RemarksByApprover ELSE 'No remarks'\n" +
                    "END AS remarks,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN DATE_FORMAT(a.RejectedDate,'%d-%m-%Y')\n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN DATE_FORMAT(a.VerifiedDate,'%d-%m-%Y')  \n" +
                    "WHEN a.SysApproverUserId != '' THEN DATE_FORMAT(a.RegistrationApprovedDate,'%d-%m-%Y') ELSE 'No date'\n" +
                    "END AS commonDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.FullName \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.FullName \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.FullName ELSE 'No name'\n" +
                    "END AS fullName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.ContactNo \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.ContactNo \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.ContactNo ELSE 'No contacts'\n" +
                    "END AS contactNo, t.TotalAmount totalAmount\n" +
                    "FROM crpsurvey a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpsurveyappliedservice s\n" +
                    "ON s.CrpSurveyId = a.CrpSurveyId LEFT JOIN crpsurveyservicepayment t ON t.CrpSurveyId=s.CrpSurveyId\n" +
                    "LEFT JOIN sysuser u ON u.Id = a.SysRejectorUserId LEFT JOIN sysuser v ON v.Id=a.SysVerifierUserId LEFT JOIN sysuser ap ON ap.Id=a.SysApproverUserId \n" +
                    "WHERE a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN a.RemarksByRejector \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN a.RemarksByVerifier \n" +
                    "WHEN a.SysApproverUserId != '' THEN a.RemarksByApprover ELSE 'No remarks'\n" +
                    "END AS remarks,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN DATE_FORMAT(a.RejectedDate,'%d-%m-%Y')\n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN DATE_FORMAT(a.VerifiedDate,'%d-%m-%Y')  \n" +
                    "WHEN a.SysApproverUserId != '' THEN DATE_FORMAT(a.RegistrationApprovedDate,'%d-%m-%Y') ELSE 'No date'\n" +
                    "END AS commonDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.FullName \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.FullName \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.FullName ELSE 'No name'\n" +
                    "END AS fullName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.ContactNo \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.ContactNo \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.ContactNo ELSE 'No contacts'\n" +
                    "END AS contactNo, t.TotalAmount totalAmount\n" +
                    "FROM crpspecializedtrade a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpspecializedtradeappliedservice s\n" +
                    "ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId LEFT JOIN crpspecializedtradeservicepayment t ON t.CrpSpecializedTradeId=s.CrpSpecializedTradeId\n" +
                    "LEFT JOIN sysuser u ON u.Id = a.SysRejectorUserId LEFT JOIN sysuser v ON v.Id=a.SysVerifierUserId LEFT JOIN sysuser ap ON ap.Id=a.SysApproverUserId \n" +
                    "WHERE a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN a.RemarksByRejector \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN a.RemarksByVerifier \n" +
                    "WHEN a.SysApproverUserId != '' THEN a.RemarksByApprover ELSE 'No remarks'\n" +
                    "END AS remarks,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN DATE_FORMAT(a.RejectedDate,'%d-%m-%Y')\n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN DATE_FORMAT(a.VerifiedDate,'%d-%m-%Y')  \n" +
                    "WHEN a.SysApproverUserId != '' THEN DATE_FORMAT(a.RegistrationApprovedDate,'%d-%m-%Y') ELSE 'No date'\n" +
                    "END AS commonDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.FullName \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.FullName \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.FullName ELSE 'No name'\n" +
                    "END AS fullName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.ContactNo \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.ContactNo \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.ContactNo ELSE 'No contacts'\n" +
                    "END AS contactNo, t.TotalAmount totalAmount\n" +
                    "FROM crparchitect a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crparchitectappliedservice s\n" +
                    "ON s.CrpArchitectId = a.CrpArchitectId LEFT JOIN crparchitectservicepayment t ON t.CrpArchitectId=s.CrpArchitectId\n" +
                    "LEFT JOIN sysuser u ON u.Id = a.SysRejectorUserId LEFT JOIN sysuser v ON v.Id=a.SysVerifierUserId LEFT JOIN sysuser ap ON ap.Id=a.SysApproverUserId \n" +
                    "WHERE a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();

            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN a.RemarksByRejector \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN a.RemarksByVerifier \n" +
                    "WHEN a.SysApproverUserId != '' THEN a.RemarksByApprover ELSE 'No remarks'\n" +
                    "END AS remarks,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN DATE_FORMAT(a.RejectedDate,'%d-%m-%Y')\n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN DATE_FORMAT(a.VerifiedDate,'%d-%m-%Y')  \n" +
                    "WHEN a.SysApproverUserId != '' THEN DATE_FORMAT(a.RegistrationApprovedDate,'%d-%m-%Y') ELSE 'No date'\n" +
                    "END AS commonDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.FullName \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.FullName \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.FullName ELSE 'No name'\n" +
                    "END AS fullName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejectorUserId != '' THEN u.ContactNo \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.ContactNo \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.ContactNo ELSE 'No contacts'\n" +
                    "END AS contactNo, t.TotalAmount totalAmount\n" +
                    "FROM crpengineer a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpengineerappliedservice s\n" +
                    "ON s.CrpEngineerId = a.CrpEngineerId LEFT JOIN crpengineerservicepayment t ON t.CrpEngineerId=s.CrpEngineerId\n" +
                    "LEFT JOIN sysuser u ON u.Id = a.SysRejectorUserId LEFT JOIN sysuser v ON v.Id=a.SysVerifierUserId LEFT JOIN sysuser ap ON ap.Id=a.SysApproverUserId \n" +
                    "WHERE a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,b.Name AS appStatus,a.ApplicationDate appDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN a.RemarksByRejector \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN a.RemarksByVerifier \n" +
                    "WHEN a.SysApproverUserId != '' THEN a.RemarksByApprover ELSE 'No remarks'\n" +
                    "END AS remarks,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN DATE_FORMAT(a.RejectedDate,'%d-%m-%Y')\n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN DATE_FORMAT(a.VerifiedDate,'%d-%m-%Y')  \n" +
                    "WHEN a.SysApproverUserId != '' THEN DATE_FORMAT(a.RegistrationApprovedDate,'%d-%m-%Y') ELSE 'No date'\n" +
                    "END AS commonDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN s.FullName \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.FullName \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.FullName ELSE 'No name'\n" +
                    "END AS fullName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN s.ContactNo \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.ContactNo \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.ContactNo ELSE 'No contacts'\n" +
                    "END AS contactNo\n" +
                    "FROM crpconsultant a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpConsultantId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpconsultantappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpConsultantId) e ON e.CrpConsultantId = a.CrpConsultantId \n" +
                    "LEFT JOIN sysuser s ON s.Id=a.SysRejecterUserId LEFT JOIN sysuser v ON v.Id=a.SysVerifierUserId LEFT JOIN sysuser ap ON ap.Id=a.SysApproverUserId WHERE a.ReferenceNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo,e.serviceName,b.Name AS appStatus,a.ApplicationDate appDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN a.RemarksByRejector \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN a.RemarksByVerifier \n" +
                    "WHEN a.SysApproverUserId != '' THEN a.RemarksByApprover ELSE 'No remarks'\n" +
                    "END AS remarks,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN DATE_FORMAT(a.RejectedDate,'%d-%m-%Y')\n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN DATE_FORMAT(a.RegistrationVerifiedDate,'%d-%m-%Y')  \n" +
                    "WHEN a.SysApproverUserId != '' THEN DATE_FORMAT(a.RegistrationApprovedDate,'%d-%m-%Y') ELSE 'No date'\n" +
                    "END AS commonDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN s.FullName \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.FullName \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.FullName ELSE 'No name'\n" +
                    "END AS fullName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN s.ContactNo \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.ContactNo \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.ContactNo ELSE 'No contacts'\n" +
                    "END AS contactNo\n" +
                    "FROM crpcontractor a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpContractorId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceNo ASC)serviceName  \n" +
                    "FROM crpcontractorappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpContractorId) e ON e.CrpContractorId = a.CrpContractorId \n" +
                    "LEFT JOIN sysuser s ON s.Id=a.SysRejecterUserId  LEFT JOIN sysuser v ON v.Id=a.SysVerifierUserId LEFT JOIN sysuser ap ON ap.Id=a.SysApproverUserId\n" +
                    "WHERE a.ReferenceNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }

            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,b.Name AS appStatus,a.ApplicationDate appDate, \n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN a.RemarksByRejector \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN a.RemarksByVerifier \n" +
                    "WHEN a.SysApproverUserId != '' THEN a.RemarksByApprover ELSE 'No remarks'\n" +
                    "END AS remarks,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN DATE_FORMAT(a.RejectedDate,'%d-%m-%Y')\n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN DATE_FORMAT(a.VerifiedDate,'%d-%m-%Y')  \n" +
                    "WHEN a.SysApproverUserId != '' THEN DATE_FORMAT(a.RegistrationApprovedDate,'%d-%m-%Y') ELSE 'No date'\n" +
                    "END AS commonDate,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN s.FullName \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.FullName \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.FullName ELSE 'No name'\n" +
                    "END AS fullName,\n" +
                    "CASE \n" +
                    "WHEN a.SysRejecterUserId != '' THEN s.ContactNo \n" +
                    "WHEN a.SysVerifierUserId != '' AND a.SysApproverUserId = '' THEN v.ContactNo \n" +
                    "WHEN a.SysApproverUserId != '' THEN ap.ContactNo ELSE 'No contacts'\n" +
                    "END AS contactNo\n" +
                    "FROM crpspecializedfirm a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpSpecializedTradeId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name SEPARATOR ', ')serviceName  \n" +
                    "FROM crpspecializedfirmappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpSpecializedTradeId) e ON e.CrpSpecializedTradeId = a.CrpSpecializedTradeId \n" +
                    "LEFT JOIN sysuser s ON s.Id=a.SysRejecterUserId LEFT JOIN sysuser v ON v.Id=a.SysVerifierUserId LEFT JOIN sysuser ap ON ap.Id=a.SysApproverUserId WHERE a.ReferenceNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
        } catch (Exception e) {
            System.out.print("Exception in TrackAppDao # populateTrackApp: " + e);
            e.printStackTrace();
        }
        return dto;
    }
}
