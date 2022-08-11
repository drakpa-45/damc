package com.ngn.spring.project.damc.common;

import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.damc.common.dto.*;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.ResponseMessage;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Repository
public class CommonDao extends BaseDao {
    @Transactional(readOnly = true)
    public List gCountryList() {
        sqlQuery = properties.getProperty("CommonDao.gCountryList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gDzongkhagList() {
        sqlQuery = properties.getProperty("CommonDao.gDzongkhagList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gCmnListItem(String cmnListId) {
        sqlQuery = properties.getProperty("CommonDao.gCmnListItem");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("cmnListId", cmnListId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gCmnListItemOw(String cmnListId) {
        sqlQuery = properties.getProperty("CommonDao.gCmnListItemOw");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("cmnListId", cmnListId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gCmnListItemHr(String cmnListId) {
        sqlQuery = properties.getProperty("CommonDao.gCmnListItemHr");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("cmnListId", cmnListId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List getGewogList(String dzongkhagId) {
        sqlQuery = properties.getProperty("CommonDao.getGewogList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("dzongkhagId",dzongkhagId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List getVillageList(String gewogId) {
        sqlQuery = properties.getProperty("CommonDao.getVillageList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("gewogId", gewogId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public Object getNextID(String tblName, String colName) {
        sqlQuery = "select ("+colName+")+1 from "+tblName+" order by "+colName + " desc";
        hQuery = hibernateQuery(sqlQuery);
        return hQuery.list() == null?1:hQuery.list().get(0);
    }

    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String filterCol, String filterVal) {
        sqlQuery = "select "+colName+" from "+tblName+" where "+filterCol+ "='"+filterVal+"'";
        hQuery = hibernateQuery(sqlQuery);List obj = hQuery.list();
        return obj.isEmpty()?null:obj.get(0);
    }

    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String condition) {
        sqlQuery = "select "+colName+" from "+tblName+" where "+condition;
        hQuery = hibernateQuery(sqlQuery);List obj = hQuery.list();
        return obj.isEmpty()?null:obj.get(0);
    }

    @Transactional(readOnly = true)
    public FileDetailDTO getAttachmentDetail(String tableName,String filterCol,String filterVal) {
        //sqlQuery = properties.getProperty("CommonDao.getAttachmentDetail");
        sqlQuery = "SELECT a.DocumentName fileName,a.DocumentPath filePath,a.FileType fileType FROM "+tableName+" a WHERE "+filterCol+"=:filterVal";
        hQuery = hibernateQuery(sqlQuery,FileDetailDTO.class)
                //.setParameter("tableName",tableName)
                .setParameter("filterVal", filterVal);
        List obj = hQuery.list();
        return obj.isEmpty()?null:(FileDetailDTO)obj.get(0);
    }

    @Transactional(readOnly = true)
    public TasklistDto populateCount(String type,String registrationtype) {
        TasklistDto dto = new TasklistDto();
        if(type.equalsIgnoreCase("crparchitect")){
            String resubmitquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
                Query architectGroup = sqlQuery(resubmitquery).setParameter(1, registrationtype);
                dto.setGroupTaskCount((BigInteger) architectGroup.list().get(0));

            String architectRegistration=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
                Query registrationArchitect = sqlQuery(architectRegistration).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
                dto.setRegistrationArchitect((BigInteger) registrationArchitect.list().get(0));

            String architectRenewal=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
                Query renewalArchitect = sqlQuery(architectRenewal).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
                dto.setRenewalArchitect((BigInteger) renewalArchitect.list().get(0));

            String architectCancellaiton=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
                Query cancellaitonArchitect = sqlQuery(architectCancellaiton).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
                dto.setCancellationArchitect((BigInteger) cancellaitonArchitect.list().get(0));
        }else if(type.equalsIgnoreCase("crpengineer")){
            String resubmitquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crpengineer a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
                Query engineerGroupCount = sqlQuery(resubmitquery).setParameter(1, registrationtype);
                dto.setGroupTaskCount((BigInteger) engineerGroupCount.list().get(0));

            String regEngineer=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crpengineer a LEFT JOIN crpengineerappliedservice s ON s.`CrpEngineerId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
                Query engineerRegistration = sqlQuery(regEngineer).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
                dto.setRegistrationEngineer((BigInteger) engineerRegistration.list().get(0));

            String renwalEngineer=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crpengineer a LEFT JOIN crpengineerappliedservice s ON s.`CrpEngineerId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
                Query engineerRenewal = sqlQuery(renwalEngineer).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
                dto.setRenewalEngineer((BigInteger) engineerRenewal.list().get(0));

            String cancellationEngineer=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crpengineer a LEFT JOIN crpengineerappliedservice s ON s.`CrpEngineerId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
                Query engineerCancellation = sqlQuery(cancellationEngineer).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
                dto.setCancellationEngineer((BigInteger) engineerCancellation.list().get(0));
        }else if(type.equalsIgnoreCase("crpspecializedTrade")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpspecializedtrade a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
                Query sptradeGroupCount = sqlQuery(resubmitquery).setParameter(1, registrationtype);
                dto.setGroupTaskCount((BigInteger) sptradeGroupCount.list().get(0));

            String registrationSptrade="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedtrade a LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.Name IS NOT NULL";
                Query sptradeRegistration = sqlQuery(registrationSptrade).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
                dto.setRegistrationSPtrade((BigInteger) sptradeRegistration.list().get(0));

            String renewalSptrade="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedtrade a LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.Name IS NOT NULL";
                Query sptradeRenewal = sqlQuery(renewalSptrade).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
                dto.setRenewalSPtrade((BigInteger) sptradeRenewal.list().get(0));

            String cancellationSptrade="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedtrade a LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.Name IS NOT NULL";
                Query sptradeCancellation = sqlQuery(cancellationSptrade).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
                dto.setCancellationSPtrade((BigInteger) sptradeCancellation.list().get(0));
        }else if(type.equalsIgnoreCase("crpspecializedFirm")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpspecializedfirm a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
                Query spFirmGroupCount = sqlQuery(resubmitquery).setParameter(1, registrationtype);
                dto.setGroupTaskCount((BigInteger) spFirmGroupCount.list().get(0));

            String registrationSPFirm="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedfirm a LEFT JOIN crpspecializedfirmappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL";
                Query spFirmRegistration = sqlQuery(registrationSPFirm).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
                dto.setRegistrationSPFirm((BigInteger) spFirmRegistration.list().get(0));

            String renewalSpfirm="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedfirm a LEFT JOIN crpspecializedfirmappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?  AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL";
                Query spfirmRenewal = sqlQuery(renewalSpfirm).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
                dto.setRenewalSPFirm((BigInteger) spfirmRenewal.list().get(0));

            String osSpfirm="SELECT COUNT(a.ReferenceNo) appcount FROM crpspecializedfirm a LEFT JOIN crpspecializedfirmappliedservice s ON s.CrpSpecializedTradeId = a.Id WHERE a.CmnApplicationRegistrationStatusId = ? AND s.CmnServiceTypeId <> '55a922e1-cbbf-11e4-83fb-080027dcfac6' AND s.CmnServiceTypeId <> '45bc628b-cbbe-11e4-83fb-080027dcfac6' AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL ";
                Query spfirmOS = sqlQuery(osSpfirm).setParameter(1, registrationtype);
                dto.setOtherServiceSPFirm((BigInteger) spfirmOS.list().get(0));

            String cancelSPFIRM="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedfirm a LEFT JOIN crpspecializedfirmappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL ";
                Query spfirmCancle = sqlQuery(cancelSPFIRM).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
                dto.setCancellationSPFirm((BigInteger) spfirmCancle.list().get(0));
        }else if(type.equalsIgnoreCase("crpconsultant")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpconsultant a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
                Query consultantGroupCount = sqlQuery(resubmitquery).setParameter(1, registrationtype);
                dto.setGroupTaskCount((BigInteger) consultantGroupCount.list().get(0));

            String consultantRegistration="SELECT COUNT(a.ReferenceNo)appcount FROM crpconsultant a LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId=a.CrpConsultantId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL ";
                Query registrationConsultant = sqlQuery(consultantRegistration).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
                dto.setRegistrationConsultant((BigInteger) registrationConsultant.list().get(0));

            String renewalConsultant="SELECT COUNT(a.ReferenceNo)appcount FROM crpconsultant a LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId=a.CrpConsultantId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL ";
                Query consultantRenewal = sqlQuery(renewalConsultant).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
                dto.setRenewalConsultant((BigInteger) consultantRenewal.list().get(0));

            String osConsultant="SELECT COUNT(a.ReferenceNo) appcount FROM crpconsultant a LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId = a.CrpConsultantId WHERE a.CmnApplicationRegistrationStatusId = ? AND s.CmnServiceTypeId <> '55a922e1-cbbf-11e4-83fb-080027dcfac6' AND s.CmnServiceTypeId <> '45bc628b-cbbe-11e4-83fb-080027dcfac6' AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL ";
                Query consultantOS = sqlQuery(osConsultant).setParameter(1, registrationtype);
                dto.setOtherServiceConsultant((BigInteger) consultantOS.list().get(0));
            String consultantCancel="SELECT COUNT(a.ReferenceNo)appcount FROM crpconsultant a LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId=a.CrpConsultantId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL ";
                Query cancelConsultant = sqlQuery(consultantCancel).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
                dto.setCancellationConsultant((BigInteger) cancelConsultant.list().get(0));
        }else if(type.equalsIgnoreCase("crpsurvey")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpsurvey a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
                Query surveyGroupCount = sqlQuery(resubmitquery).setParameter(1, registrationtype);
                dto.setGroupTaskCount((BigInteger) surveyGroupCount.list().get(0));

            String surveyRegistration="SELECT COUNT(a.ReferenceNo)appcount FROM crpsurvey a LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.CrpSurveyId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
                Query registrationSurvey = sqlQuery(surveyRegistration).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
                dto.setRegistrationSurvey((BigInteger) registrationSurvey.list().get(0));

            String surveyRenewal="SELECT COUNT(a.ReferenceNo)appcount FROM crpsurvey a LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.CrpSurveyId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
                Query renewalSurvey = sqlQuery(surveyRenewal).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
                dto.setRenewalSurvey((BigInteger) renewalSurvey.list().get(0));

            String cancelSurvey="SELECT COUNT(a.ReferenceNo)appcount FROM crpsurvey a LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.CrpSurveyId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
                Query surveyCancel = sqlQuery(cancelSurvey).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
                dto.setCancellationSurvey((BigInteger) surveyCancel.list().get(0));
        }else if(type.equalsIgnoreCase("crpcontractor")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpcontractor a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
                Query contractorGroupCount = sqlQuery(resubmitquery).setParameter(1, registrationtype);
                dto.setGroupTaskCount((BigInteger) contractorGroupCount.list().get(0));

            String registrationContractor="SELECT COUNT(a.ReferenceNo)appcount FROM crpcontractor a LEFT JOIN crpcontractorappliedservice s ON s.CrpContractorId=a.CrpContractorId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL";
                Query registrationqueryes = sqlQuery(registrationContractor).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
                dto.setRegistration((BigInteger) registrationqueryes.list().get(0));

            String renwalquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpcontractor a LEFT JOIN crpcontractorappliedservice s ON s.CrpContractorId=a.CrpContractorId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6'  AND a.SysLockedByUserId IS NULL";
                Query renwalquerye = sqlQuery(renwalquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
                dto.setRenewal((BigInteger) renwalquerye.list().get(0));

            String osquery="SELECT COUNT(a.ReferenceNo) appcount FROM crpcontractor a INNER JOIN crpcontractorappliedservice s \n" +
                    "ON s.CrpContractorId = a.CrpContractorId WHERE a.CmnApplicationRegistrationStatusId =? AND\n" +
                    "s.CmnServiceTypeId <> '55a922e1-cbbf-11e4-83fb-080027dcfac6' AND s.CmnServiceTypeId <> '45bc628b-cbbe-11e4-83fb-080027dcfac6' AND s.CmnServiceTypeId <> 'a98f434b-d8ee-11e4-afa1-9c2a70cc8e06' AND\n" +
                    "a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.SysLockedByUserId IS NULL";
                Query osquerye = sqlQuery(osquery).setParameter(1, registrationtype);
                dto.setOtherService((BigInteger) osquerye.list().get(0));

            String cancellaitonquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpcontractor a LEFT JOIN crpcontractorappliedservice s ON s.CrpContractorId=a.CrpContractorId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId <> 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.SysLockedByUserId IS NULL";
                Query cancellaitonquerye = sqlQuery(cancellaitonquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
                dto.setCancellation((BigInteger) cancellaitonquerye.list().get(0));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public TasklistDto populateNotificationCount(String type) {
        TasklistDto dto = new TasklistDto();
        if(type.equalsIgnoreCase("crpconsultant")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpconsultantfinal a LEFT JOIN crpconsultanthumanresourcefinal h ON a.Id = h.CrpConsultantFinalId WHERE a.RegistrationApprovedDate + 30 AND HasNotification =1 AND h.DeleteRequest=1";
           Query renwalquerye = sqlQuery(resubmitquery);
            dto.setNotificationCount((BigInteger) renwalquerye.list().get(0));
        }else if(type.equalsIgnoreCase("crpcontractor")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpcontractorfinal a LEFT JOIN crpcontractorhumanresourcefinal h ON a.Id = h.CrpContractorFinalId WHERE a.RegistrationApprovedDate + 30 AND HasNotification =1 AND h.DeleteRequest=1";
            Query renwalquerye = sqlQuery(resubmitquery);
            dto.setNotificationCount((BigInteger) renwalquerye.list().get(0));
        }else if(type.equalsIgnoreCase("specializedFirm")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpspecializedfirmfinal a LEFT JOIN crpspecializedtradehumanresourcefinal h ON a.Id = h.CrpSpecializedTradeFinalId WHERE a.RegistrationApprovedDate + 30 AND HasNotification =1 AND h.DeleteRequest=1";
            Query renwalquerye = sqlQuery(resubmitquery);
            dto.setNotificationCount((BigInteger) renwalquerye.list().get(0));
        }
        return dto;
    }

    @Transactional
    public String getAppType(LoginDTO loginDTO) {
        String cdbNo="";
        String query="SELECT COALESCE(CONCAT('Architect999', f.ARNo),CONCAT('Contractor9999', c.CDBNo),CONCAT('Consultant999', cons.CDBNo),CONCAT('SpecializedFirm999', sp.SPNo),CONCAT('SpecializedFirm999', sf.SPNo),CONCAT('Engineer999',ef.CDBNo)) cdbNo FROM sysuser s LEFT JOIN crparchitectfinal f ON f.SysUserId = s.Id LEFT JOIN crpcontractorfinal c ON c.SysUserId = s.Id LEFT JOIN crpconsultantfinal cons ON cons.SysUserId = s.Id LEFT JOIN crpspecializedtradefinal sp ON sp.SysUserId = s.Id LEFT JOIN crpspecializedfirmfinal sf ON sf.SysUserId = s.Id LEFT JOIN crpengineerfinal ef ON ef.SysUserId=s.Id  WHERE s.username =?";
        cdbNo = (String)sqlQuery(query).setParameter(1, loginDTO.getUsername()).uniqueResult();
        return cdbNo;
    }

    @Transactional
    public List<TasklistDto> getdashboardDetails(String type) {
        /*MessageFor field
				 1=CRPS,2=Etool,3=CiNet,4=Contractors,5=Consultant,6=Architects,7=Engineers,8=Specilazed Trade Users*/
        if(type.equalsIgnoreCase("Architect")){
            type="6";
        }
        List<TasklistDto> retval=new ArrayList<TasklistDto>();
        try {
            sqlQuery="SELECT Message messages FROM `sysnewsandnotification` WHERE MessageFor=? ";
            Query queryre = sqlQuery(sqlQuery, TasklistDto.class).setParameter(1, type);
            if(queryre.list().size()>0){
                retval=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getdashboardDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplications(String getCdbNoForSp, String getCdbNoForSurvey, String cdbdet) {
        List<TasklistDto> retval=new ArrayList<TasklistDto>();
        try {
            if(cdbdet.split("999")[0].equalsIgnoreCase("Architect")){
                sqlQuery="SELECT a.`ReferenceNo` Application_Number,a.`ApplicationDate` appDate,a.`ARNo` cdbNo,a.`RejectedDate` rejectedDate,a.`RemarksByRejector` remarks FROM crparchitect a WHERE a.`CmnApplicationRegistrationStatusId`='de662a61-b049-11e4-89f3-080027dcfac6' AND a.`ARNo`=? ";
            }
            if(cdbdet.split("999")[0].equalsIgnoreCase("Consultant")){
                sqlQuery="SELECT a.ReferenceNo Application_Number,a.ApplicationDate appDate,a.CDBNo cdbNo,a.RejectedDate rejectedDate,a.RemarksByRejector remarks FROM crpconsultant a WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.CDBNo =?";
            }
            Query queryre = sqlQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbdet.split("999")[1]);
            if(queryre.list().size()>0){
                retval=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getdashboardDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String getSpAppType(LoginDTO loginDTO) {
        String cdbNo="";
        String specializedquery=" SELECT f.SPNo cdbNo FROM sysuser s LEFT JOIN crpspecializedtradefinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query spNo = sqlQuery(specializedquery).setParameter(1, loginDTO.getUsername());
        if(spNo.list().size()>0)
            cdbNo="SpecializedTrade999"+ spNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public String getCdbNoForSp(LoginDTO loginDTO) {
        String cdbNo="";
        String specializedquery="SELECT f.SPNo cdbNo FROM sysuser s LEFT JOIN crpspecializedtradefinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(specializedquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getCdbNoForSpFirm(LoginDTO loginDTO) {
        String cdbNo="";
        String specializedquery="SELECT f.SPNo cdbNo FROM sysuser s LEFT JOIN crpspecializedfirmfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(specializedquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getCdbNoForSurvey(LoginDTO loginDTO) {
        String cdbNo="";
        String surveyquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crpsurveyfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(surveyquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getSurveyCdbNo(LoginDTO loginDTO) {
        String cdbNo="";
        String architectquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crpsurveyfinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query arNo = sqlQuery(architectquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Survey999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    public Boolean isEmailUnique(String email) {
        sqlQuery = properties.getProperty("SurveyDao.isEmailUnique");
        return hibernateQuery(sqlQuery).setParameter("email",email).list().isEmpty();
    }

    public Boolean getFirmName(HttpServletRequest request) {
        sqlQuery = properties.getProperty("SpecializedDao.getFirmName");
        return hibernateQuery(sqlQuery).setParameter(1,request.getParameter("firmName")).list().isEmpty();
    }

    public Boolean getFirmNameConsultant(HttpServletRequest request) {
        sqlQuery = properties.getProperty("ConsultantDao.getFirmName");
        return hibernateQuery(sqlQuery).setParameter(1,request.getParameter("firmName")).list().isEmpty();
    }

    @Transactional
    public String getOwnershipType(String appNo) {
        String surveyquery="SELECT s.CmnOwnershipTypeId FROM crpspecializedtrade s WHERE s.ReferenceNo=?";
        Query ownertype = sqlQuery(surveyquery).setParameter(1, appNo);
        return (String) ownertype.list().get(0);
    }

    public String insertuserDetails(PersonalInfoDTO dto, String userID, HttpServletRequest request) {
        String return_value="Insert_Fail",actorId="";
        try {
            String generateID = UUID.randomUUID().toString();
            int len = 4;
            String ngpwd = generateRandomPassword(len);

            Query query1 = sqlQuery("INSERT INTO sysuser (ContactNo,CreatedBy,CreatedOn,Email,FullName,Id,PASSWORD,STATUS,username) VALUES(?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?) ");
            query1.setParameter(1, dto.getRegMobileNo()).setParameter(2, userID).setParameter(3, dto.getRegEmail()).setParameter(4, dto.getFullname()).setParameter(5, generateID).setParameter(6, ngpwd).setParameter(7, "1").setParameter(8, dto.getRegEmail());
            int save = query1.executeUpdate();
            if (save > 0) {
                System.out.print("Password: "+ngpwd+" is generated against user:"+dto.getRegEmail());
                return_value= ngpwd;
            }

        } catch (Exception e) {
            System.out.print("Exception in commonDao # insertuserDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String generateRandomPassword(int len) {
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of loop choose a character randomly from the given ASCII range
        // and append it to StringBuilder instance
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    @Transactional
    public BigInteger getCdbNoForConsultant(LoginDTO loginDTO) {
        sqlQuery = properties.getProperty("ConsultantDao.getCDBNo");
        List resultList = hibernateQuery(sqlQuery).setParameter(1, loginDTO.getUsername()).list();
        return (BigInteger) (resultList.isEmpty() ? 1 : resultList.get(0));
    }

    @Transactional
    public String getConsultantCdbNo(LoginDTO loginDTO) {
        String cdbNo=null;
        String consquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpconsultantfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query arNo = sqlQuery(consquery).setParameter(1, loginDTO.getUsername());
        return (String) arNo.list().get(0);

    }

    public Boolean checkIfMailExists(PersonalInfoDTO dto1) {
        sqlQuery = properties.getProperty("ConsultantDao.checkIfMailExists");
        return hibernateQuery(sqlQuery).setParameter(1,dto1.getRegEmail()).list().isEmpty();
    }

    public List<DropdownDTO> getDropdownDetails(String sl_no, String type) {
        List<DropdownDTO> dropDownList = new ArrayList<DropdownDTO>();
        try {
            if (type.equalsIgnoreCase("gewog_list")) {
                String sqlQuery = "SELECT g.Gewog_Serial_No AS value, CONCAT(g.Gewog_Name,'/',g.Gewog_Name_Bh) AS text FROM cmngewog g WHERE Dzongkhag_Serial_No=:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("Type_Id", sl_no);
                dropDownList = hQuery.list();
            } else if (type.equalsIgnoreCase("village_list")) {
                String sqlQuery = "SELECT v.Village_Serial_No AS value, CONCAT(v.Village_Name,'/',v.Village_Name_Bh)AS text FROM cmnvillage v WHERE Gewog_Serial_No=:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("Type_Id", sl_no);
                dropDownList = hQuery.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dropDownList;
    }

    public String updatesysuser(PersonalInfoDTO dto1, String userID, String getfullname, HttpServletRequest request) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("UPDATE sysuser s SET s.EditedOn=CURRENT_DATE,s.EditedBy=?,s.Email=?,s.FullName=?,s.username=? WHERE s.username = ?");
            query1.setParameter(1,userID).setParameter(2,dto1.getRegEmail()).setParameter(3,getfullname).setParameter(4,dto1.getRegEmail()).setParameter(5,dto1.getId());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }else {
                return_value = "failed";
            }
        }catch (Exception e) {
            System.out.print("Exception in ConsultantDao # updatesysuser: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String getPreviousMail(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        String previousMail="";
        try {
            sqlQuery = "SELECT s.FullName FROM sysuser s WHERE s.Email=?";
            previousMail = (String) hibernateQuery(sqlQuery).setParameter(1, dto1.getId()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ConsultantDao # getPreviousMail:" + e);
            e.printStackTrace();
            return previousMail="Failed";
        }
        return previousMail;
    }

    @Transactional
    public String getCDBNo(String cid) {
        String cdbNo="", consquery="",existingCDBNo="";
try {
    consquery = "SELECT ARNo FROM crparchitectfinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
    }
    consquery = "SELECT CDBNo FROM crpengineerfinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
    }
    consquery = "SELECT SPNo FROM crpspecializedtradefinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
    }
    consquery = "SELECT ARNo FROM crpsurveyfinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
        }
    }catch (Exception e){
    e.printStackTrace();
     }
        return cdbNo;
    }

    public List<TrackRecord> getTrackRecord(String cdbNo) {
        List<TrackRecord> entity = new ArrayList<TrackRecord>();
        sqlQuery = properties.getProperty("CommonDao.getTrackRecord");
        entity = (List<TrackRecord>) hibernateQuery(sqlQuery, TrackRecord.class).setParameter(1,cdbNo).list();
        return entity;
    }

    @Transactional(readOnly = true)
    public boolean isExpiredApplication(String cdbdet) {
        String type =""; String cdbNo="";
        if(cdbdet.startsWith("Contractor")){
            type=cdbdet.split("9999")[0];
            cdbNo = cdbdet.split("9999")[1];
        }else{
            type=cdbdet.split("999")[0];
            cdbNo = cdbdet.split("999")[1];
        }
        if(type.equalsIgnoreCase("Contractor")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredContractor");
        }else if(type.equalsIgnoreCase("Consultant")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredConsultant");
        }else if(type.equalsIgnoreCase("SpecializedFirm")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredSpecializedFirm");
        }else if(type.equalsIgnoreCase("Engineer")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredEngineer");
        } else if(type.equalsIgnoreCase("Survey")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredSurveyor");
        }else if(type.equalsIgnoreCase("Architect")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredArchitect");
        }else if(type.equalsIgnoreCase("SpecializedTrade")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredSpecializedTrade");
        }
        //BigInteger bigIntValue = (BigInteger)hibernateQuery(sqlQuery).setParameter("cdbNo", cdbNo).list().get(0);
        Integer bigIntValue = (Integer) hibernateQuery(sqlQuery).setParameter("cdbNo", cdbNo).list().get(0);
        return (bigIntValue.intValue() == 1);
    }

    @Transactional
    public List<EmployeeDetailsDTO> validateWorkEngagementCidNo(String cidNo) {
       // sqlQuery = properties.getProperty("ConsultantRCActionDao.getEmployeeDetailsFromCDB");
        List<EmployeeDetailsDTO> employeeDetailsDTOs = new ArrayList<>();
        sqlQuery = properties.getProperty("CommonDao.validateWorkEngagementCidNo");
         employeeDetailsDTOs =(List<EmployeeDetailsDTO>) hibernateQuery(sqlQuery, EmployeeDetailsDTO.class).setParameter("cidNo", cidNo).list();
        return employeeDetailsDTOs;
    }

    @Transactional
    public String getCdbNoForContractor(LoginDTO loginDTO) {
        String cdbNo=null;
        String consquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpcontractorfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query arNo = sqlQuery(consquery).setParameter(1, loginDTO.getUsername());
        return (String) arNo.list().get(0);
    }

    @Transactional
    public List<TasklistDto> populateapplicationHistoryConsultant(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpconsultant a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpConsultantId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpconsultantappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpConsultantId\n" +
                    ") e ON e.CrpConsultantId = a.CrpConsultantId \n" +
                    "WHERE a.CDBNo=? ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        }catch (Exception e) {
            System.out.print("Exception in CommonDao # populateapplicationHistoryConsultant: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populateapplicationHistoryContractor(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpcontractor a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpContractorId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpcontractorappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpContractorId\n" +
                    ") e ON e.CrpContractorId = a.CrpContractorId \n" +
                    "WHERE a.CDBNo=? ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        }catch (Exception e) {
            System.out.print("Exception in CommonDao # populateapplicationHistoryContractor: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationConsultant(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,a.RemarksByRejector remarks,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpconsultant a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpConsultantId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpconsultantappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpConsultantId\n" +
                    ") e ON e.CrpConsultantId = a.CrpConsultantId \n" +
                    "WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.CDBNo=? AND a.RejectedDate > DATE_ADD(NOW(),INTERVAL -10 DAY) ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populaterejectedApplicationConsultant: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationContractor(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,a.RemarksByRejector remarks,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpcontractor a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpContractorId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpcontractorappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpContractorId\n" +
                    ") e ON e.CrpContractorId = a.CrpContractorId \n" +
                    "WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.CDBNo=? AND a.RejectedDate > DATE_ADD(NOW(),INTERVAL -10 DAY) ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populaterejectedApplicationContractor: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String getCdbNoForArchitect(LoginDTO loginDTO) {
        String cdbNo="";
        String surveyquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crparchitectfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(surveyquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getArchitectCDBNo(LoginDTO loginDTO) {
        String cdbNo="";
        String architectquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crparchitectfinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query arNo = sqlQuery(architectquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Architect999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public String getCdbNoForEngineer(LoginDTO loginDTO) {
        String cdbNo="";
        String surveyquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpengineerfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(surveyquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getEngineerCDBNo(LoginDTO loginDTO) {
        String cdbNo="";
        String architectquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpengineerfinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query arNo = sqlQuery(architectquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Engineer999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public List<GovCopDTO> validateCorporateCidNo(String cidNo) {
        List<GovCopDTO> dto=new ArrayList<GovCopDTO>();
        sqlQuery = properties.getProperty("CommonDao.validateCorporateCidNo");
        dto =(List<GovCopDTO>) hibernateQuery(sqlQuery, GovCopDTO.class).setParameter("cidNo", cidNo).list();
        return dto;
    }

    @Transactional
    public ArrayList<CdbDTO> validatePartnerCidNoContractorfinal(String cidNo) {
            sqlQuery = properties.getProperty("CommonDao.getContractorEngagement");
            return (ArrayList<CdbDTO>) hibernateQuery(sqlQuery, CdbDTO.class).setParameter(1,cidNo).list();
    }

    @Transactional
    public ArrayList<CdbDTO> validatePartnerCidNoConsultantfinal(String cidNo) {
        sqlQuery = properties.getProperty("CommonDao.getConsultantEngagement");
        return (ArrayList<CdbDTO>) hibernateQuery(sqlQuery, CdbDTO.class).setParameter(1,cidNo).list();
    }

    @Transactional
    public ArrayList<CdbDTO> validatePartnerCidNoSpecializedFirmfinal(String cidNo) {
        sqlQuery = properties.getProperty("CommonDao.getSpecializedFirmEngagement");
        return (ArrayList<CdbDTO>) hibernateQuery(sqlQuery, CdbDTO.class).setParameter(1,cidNo).list();
    }

    @Transactional(readOnly = true)
    public Boolean isUsenameExist(String username, String pwd) {
        sqlQuery = properties.getProperty("CommonDao.isUsenameExist");
        return hibernateQuery(sqlQuery).setParameter("username", username).setParameter("pwd",pwd).list().isEmpty();
    }

    @Transactional(readOnly = true)
    public Boolean isUsenameExist4gotPwd(String username) {
        sqlQuery = properties.getProperty("userAdmin.isEmployeeIdUnique");
        return hibernateQuery(sqlQuery).setParameter("employeeId", username).list().isEmpty();
    }

    public String updatePhoneNumber(LoginDTO loginDTO, String phoneNumber) {
        ResponseMessage responseMessage = null;
        String getCdbNoForSp = getCdbNoForSp(loginDTO);
        String getCdbNoForSpFirm = getCdbNoForSpFirm(loginDTO);
        String getCdbNoForSurvey = getCdbNoForSurvey(loginDTO);
        String getCdbNoForArchitect = getCdbNoForArchitect(loginDTO);
        String getCdbNoForEngineer= getCdbNoForEngineer(loginDTO);
        String getConsultantCdbNo = getConsultantCdbNo(loginDTO);
        String getCdbNoForContractor = getCdbNoForContractor(loginDTO);
        String cdbdet="";int save=0;
        if(getCdbNoForSp != null){
           // if(getCdbNoForSp.contains("SP-") || getCdbNoForSp.contains("SF-")) {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtradefinal,sysuser SET crpspecializedtradefinal.MobileNo = ?,sysuser.ContactNo = ? WHERE crpspecializedtradefinal.SysUserId=sysuser.Id AND sysuser.Email=?");
            query1.setParameter(1, phoneNumber).setParameter(2,phoneNumber).setParameter(3, loginDTO.getUsername());
            save = query1.executeUpdate();
           // }
        }else if(getCdbNoForSpFirm !=null){
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedfirmfinal,sysuser SET crpspecializedfirmfinal.MobileNo = ?,sysuser.ContactNo = ? WHERE crpspecializedfirmfinal.SysUserId=sysuser.Id AND sysuser.Email=?");
            query1.setParameter(1, phoneNumber).setParameter(2,phoneNumber).setParameter(3, loginDTO.getUsername());
            save = query1.executeUpdate();
        }else if(getCdbNoForSurvey != null) {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpsurveyfinal,sysuser SET crpsurveyfinal.MobileNo = ?,sysuser.ContactNo = ? WHERE crpsurveyfinal.SysUserId=sysuser.Id AND sysuser.Email=?");
            query1.setParameter(1, phoneNumber).setParameter(2,phoneNumber).setParameter(3, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getCdbNoForEngineer != null) {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpengineerfinal,sysuser SET crpengineerfinal.MobileNo = ?,sysuser.ContactNo = ? WHERE crpengineerfinal.SysUserId=sysuser.Id AND sysuser.Email=?");
            query1.setParameter(1, phoneNumber).setParameter(2,phoneNumber).setParameter(3, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getCdbNoForArchitect != null) {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitectfinal,sysuser SET crparchitectfinal.MobileNo = ?,sysuser.ContactNo = ? WHERE crparchitectfinal.SysUserId=sysuser.Id AND sysuser.Email=?");
            query1.setParameter(1, phoneNumber).setParameter(2,phoneNumber).setParameter(3, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getConsultantCdbNo!=null){
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpconsultantfinal,sysuser SET crpconsultantfinal.MobileNo = ?,sysuser.ContactNo = ? WHERE crpconsultantfinal.SysUserId=sysuser.Id AND sysuser.Email=?");
            query1.setParameter(1, phoneNumber).setParameter(2,phoneNumber).setParameter(3, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getCdbNoForContractor!=null){
            //org.hibernate.query.Query query1 = sqlQuery("UPDATE crpcontractorfinal SET MobileNo = ? WHERE Email = ?");
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpcontractorfinal,sysuser SET crpcontractorfinal.MobileNo = ?,sysuser.ContactNo = ? WHERE crpcontractorfinal.SysUserId=sysuser.Id AND sysuser.Email=?");
            query1.setParameter(1, phoneNumber).setParameter(2,phoneNumber).setParameter(3, loginDTO.getUsername());
             save = query1.executeUpdate();
        }
        if (save > 0) {
            return "Success";
        }else {
            return "Failed";
        }
    }

    public String seekClearification(LoginDTO loginDTO, String enquiry, String tenderId) {
        ResponseMessage responseMessage = null;
        String getCdbNoForContractor = getCdbNoForContractor(loginDTO);
        String cdbNo = (String) getValue("crpcontractorfinal", "CDBNo", "Email", loginDTO.getUsername());
        String cdbdet="";int save=0;
        if(getCdbNoForContractor!=null){
            //org.hibernate.query.Query query1 = sqlQuery("UPDATE crpcontractorfinal SET MobileNo = ? WHERE Email = ?");
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO etlseekclarification(Enquiry,CDB_No,Tender_Id) VALUES (?,?,?)");
            query1.setParameter(1,enquiry).setParameter(2,cdbNo).setParameter(3,tenderId);
            save = query1.executeUpdate();
        }
        if (save > 0) {
            return "Success";
        }else {
            return "Failed";
        }
    }


    public String updateTTNumber(LoginDTO loginDTO, String tradeNumber, String tpn) {
        ResponseMessage responseMessage = null;
        String getCdbNoForSpFirm = getCdbNoForSpFirm(loginDTO);
        String getConsultantCdbNo = getConsultantCdbNo(loginDTO);
        String getCdbNoForContractor = getCdbNoForContractor(loginDTO);
        String cdbdet="";int save=0;
        if(getCdbNoForSpFirm != null){
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedfirmfinal SET TPN=?, TradeLicenseNo=? WHERE Email = ?");
            query1.setParameter(1, tpn).setParameter(2,tradeNumber).setParameter(3, loginDTO.getUsername());
            save = query1.executeUpdate();
        }else if(getConsultantCdbNo!=null){
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpconsultantfinal SET TPN=?, TradeLicenseNo=? WHERE Email = ?");
            query1.setParameter(1, tpn).setParameter(2,tradeNumber).setParameter(3, loginDTO.getUsername());
            save = query1.executeUpdate();
        }else if(getCdbNoForContractor!=null){
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpcontractorfinal SET TPN=?, TradeLicenseNo=? WHERE Email = ?");
            query1.setParameter(1, tpn).setParameter(2,tradeNumber).setParameter(3, loginDTO.getUsername());
            save = query1.executeUpdate();
        }
        if (save > 0) {
            return "Success";
        }else {
            return "Failed";
        }
    }

    public ResponseMessage updatePassword(LoginDTO loginDTO, String username, String newPwd) {
      ResponseMessage responseMessage=null;
        /*String digit=newPwd;
        StringBuilder salt=new StringBuilder();
        Random rnd=new Random();
        while (salt.length()<2){
            int index=(int) (rnd.nextFloat() * digit.length());
            salt.append(digit.charAt(index));
        }
        String saltString=salt.toString();
        String pw_hash= BCrypt.hashpw(saltString, BCrypt.gensalt());
*/
        String generateID = UUID.randomUUID().toString();
        String salt = BCrypt.gensalt(12);
        String pw_hash = BCrypt.hashpw(newPwd, salt);

        try {
            Query query1 = sqlQuery("UPDATE sysuser  SET password=? WHERE username = ?");
            query1.setParameter(1, pw_hash).setParameter(2,username);
             query1.executeUpdate();
        }catch (Exception e) {
            System.out.print("Exception in ommonDao # updatesysuser: " + e);
            e.printStackTrace();
        }
        return responseMessage;
    }

    @Transactional
    public List<CdbDTO> fetchEqDtlsFromCDB(String regNo, String serviceName) {
        List<CdbDTO> cdbDTOs = new ArrayList<>();
        if(serviceName.equalsIgnoreCase("consultant")){
            sqlQuery = properties.getProperty("CommonDao.consultantFetchEqDtlsFromCDB");
        }
        if(serviceName.equalsIgnoreCase("contractor")){
            sqlQuery = properties.getProperty("CommonDao.contractorFetchEqDtlsFromCDB");
        }
        if(serviceName.equalsIgnoreCase("specializedFirm")){
            sqlQuery = properties.getProperty("CommonDao.specializedFirmFetchEqDtlsFromCDB");
        }
        cdbDTOs =(List<CdbDTO>) hibernateQuery(sqlQuery, CdbDTO.class).setParameter("regNo", regNo).list();
        return cdbDTOs;
    }

    @Transactional(readOnly = true)
    public String getVechileTypeFromVechileNumber(String regNo) {
        String vechileType="";
        try {
            sqlQuery = "SELECT t.Description FROM t_vehicle_type_master t LEFT JOIN cmnequipment ce ON ce.VehicleType = t.Vehicle_Type_Id LEFT JOIN crpcontractorequipment cc ON cc.CmnEquipmentId = ce.Id WHERE cc.RegistrationNo=?";
            vechileType = (String) hibernateQuery(sqlQuery).setParameter(1, regNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in commonDao # getVechileTypeFromVechileNumber:" + e);
            e.printStackTrace();
        }
        return vechileType;
    }

    @Transactional
    public void updGeneratedPassword(String username, String ngpwd) {
        try {
            Query query1 = sqlQuery("UPDATE sysuser s SET s.password=? WHERE s.username = ?");
            query1.setParameter(1, ngpwd).setParameter(2,username);
            query1.executeUpdate();
        }catch (Exception e) {
            System.out.print("Exception in CommonDao # updGeneratedPassword: " + e);
            e.printStackTrace();
        }
    }

    public void replacedContractorHr(String cid) {
        try {
            Query query1 = sqlQuery("DELETE FROM crpcontractorhumanresourcefinal WHERE CIDNo=?");
            query1.setParameter(1, cid);
            query1.executeUpdate();
        }catch (Exception e) {
            System.out.print("Exception in CommonDao # replacedContractorHr: " + e);
            e.printStackTrace();
        }
    }
    public void replacedConsultantHr(String cid) {
        try {
            Query query1 = sqlQuery("DELETE FROM crpconsultanthumanresourcefinal WHERE CIDNo=?");
            query1.setParameter(1, cid);
            query1.executeUpdate();
        }catch (Exception e) {
            System.out.print("Exception in CommonDao # replacedConsultantHr: " + e);
            e.printStackTrace();
        }
    }
    public void replacedSFHr(String cid) {
        try {
            Query query1 = sqlQuery("DELETE FROM crpspecializedtradehumanresourcefinal WHERE CIDNo=?");
            query1.setParameter(1, cid);
            query1.executeUpdate();
        }catch (Exception e) {
            System.out.print("Exception in CommonDao # replacedSFHr: " + e);
            e.printStackTrace();
        }
    }

    public List seekClearance(String cdbNo) {
        sqlQuery = properties.getProperty("CommonDao.seekClearance");
        Query hQuery = hibernateQuery(sqlQuery, TasklistDto.class).setParameter("cdbNo", cdbNo);
        return hQuery.list().isEmpty()?null:hQuery.list();
    }

    public List<TrackRecord> checkHrEngagedByWorkID(String workId, String cdbNo) {
        List<TrackRecord> entity = new ArrayList<TrackRecord>();
        sqlQuery = properties.getProperty("CommonDao.checkHrEngagedByWorkID");
        entity = (List<TrackRecord>) hibernateQuery(sqlQuery, TrackRecord.class).setParameter("workId", workId).setParameter("cdbNo",cdbNo).list();
        return entity;
    }

    public List<TrackRecord> checkEqEngagedByWorkID(String workId, String cdbNo) {
        List<TrackRecord> entity = new ArrayList<TrackRecord>();
        sqlQuery = properties.getProperty("CommonDao.checkEqEngagedByWorkID");
        entity = (List<TrackRecord>) hibernateQuery(sqlQuery, TrackRecord.class).setParameter("workId", workId).setParameter("cdbNo",cdbNo).list();
        return entity;
    }

    public List gAgencyList() {
        sqlQuery = properties.getProperty("CommonDao.gAgencyList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    public List gRolesList() {
        sqlQuery = properties.getProperty("CommonDao.gRolesList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    public List gRegionList() {
        sqlQuery = properties.getProperty("CommonDao.gRegionList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    public List gDesignationList() {
        sqlQuery = properties.getProperty("CommonDao.gDesignationList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    public List gSectorList() {
        sqlQuery = properties.getProperty("CommonDao.gSectorList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    public List gGewogUsersList() {
        sqlQuery = properties.getProperty("CommonDao.gGewogUsersList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }
}

