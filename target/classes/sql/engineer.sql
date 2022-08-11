/** EngineerDao **/

EngineerDao.gFeeStructure=SELECT CAST(IF(f.`NewRegistrationFee` IS NULL,'0',f.`NewRegistrationFee`) AS UNSIGNED) registrationFee,f.`FirstRenewalFee` renewalFee,f.`Name` name,f.`RegistrationValidity` validaty FROM `crpservicefeestructure` f WHERE f.`Name` LIKE '%Engineer%'

EngineerDao.getMaxId=SELECT a.ReferenceNo FROM crpengineer a ORDER BY a.ReferenceNo DESC LIMIT 1

EngineerDao.getTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate,a.CIDNo serviceNo,cm.Name serviceSectorType,b.Name AS appStatus,a.CDBNo cdbNo,s.CmnServiceTypeId cmnServiceTypeId FROM crpengineer a LEFT JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId LEFT JOIN crpengineerappliedservice s ON s.CrpEngineerId=a.CrpEngineerId LEFT JOIN cmnlistitem cm ON cm.Id = a.CmnServiceSectorTypeId WHERE a.SysLockedByUserId IS NULL AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId=? ORDER BY a.ReferenceNo DESC;
EngineerDao.getMyTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate,a.CIDNo serviceNo,cm.Name serviceSectorType,b.Name AS appStatus,a.CDBNo cdbNo,s.CmnServiceTypeId cmnServiceTypeId FROM crpengineer a LEFT JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId LEFT JOIN crpengineerappliedservice s ON s.CrpEngineerId=a.CrpEngineerId LEFT JOIN cmnlistitem cm ON cm.Id = a.CmnServiceSectorTypeId WHERE a.SysLockedByUserId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId=? ORDER BY a.ReferenceNo DESC;

EngineerDao.send2MyOrGroupTask = UPDATE crpengineer a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo

EngineerDao.getEngineerDetails=SELECT a.CmnApplicationRegistrationStatusId updateStatus, t.Name trade,a.CmnTradeId cmnTradeId,a.CrpEngineerId CrpEngineerId,a.ReferenceNo,a.CIDNo cidNo,a.Name fullname,i.Name salutation,d.NameEn dzongkhagId,a.Gewog gewog,a.Village village,c.Name countryId,ser.Name serviceSectorType,a.CmnServiceSectorTypeId serviceSectorTypeId,a.Email email,a.MobileNo mobileNo,a.EmployerName employeeName,a.EmployerAddress employeeAddress,q.Name qualificationId,a.GraduationYear graduationyr,a.NameOfUniversity universityName,uc.Name universityCountry,a.VerifiedDate verifcationdate,a.RemarksByVerifier verifierremarks,a.RegistrationApprovedDate approvaldate, a.RemarksByApprover approiverremarks,ss.Name serviceTypeId,a.CDBNo cdbNo,a.RegistrationExpiryDate regExpDate,a.ApplicationDate applicationDate,a.CIDNo createdBy,o.FullName verifierUser,z.FullName approverUser,a.CancellationReason cancellationRemarks,IF(css.NoOfDaysLate IS NULL, 0,  css.NoOfDaysLate) noOfDaysLate,IF(css.noOfDaysAfterGracePeriod IS NULL, 0,  css.noOfDaysAfterGracePeriod ) noOfDaysAfterGracePeriod, css.PaymentAmount paymentAmt,css.PenaltyPerDay penaltyPerDay,css.TotalAmount totalAmt FROM crpengineer a LEFT JOIN sysuser o ON o.Id=a.SysVerifierUserId LEFT JOIN sysuser z ON z.Id = a.SysApproverUserId LEFT JOIN cmnlistitem i ON i.Id=a.CmnSalutationId LEFT JOIN cmndzongkhag d ON d.Id=a.CmnDzongkhagId LEFT JOIN cmncountry c ON c.Id=a.CmnCountryId LEFT JOIN cmnlistitem ser ON ser.Id=a.CmnServiceSectorTypeId LEFT JOIN cmnlistitem q ON q.Id=a.CmnQualificationId LEFT JOIN cmncountry uc ON uc.Id=a.CmnUniversityCountryId LEFT JOIN crpengineerappliedservice s ON s.CrpEngineerId=a.Id LEFT JOIN crpservice ss ON ss.Id=s.CmnServiceTypeId LEFT JOIN cmnlistitem t ON t.Id = a.CmnTradeId LEFT JOIN  crpengineerservicepayment css ON css.CrpEngineerId = a.CrpEngineerId WHERE a.ReferenceNo=?
EngineerDao.getEngineerDoc=SELECT d.Id id,d.DocumentName,d.DocumentPath,d.CreatedOn,d.FileType FROM crpengineerattachment d WHERE d.CrpEngineerId=?

EngineerDao.getengineerregDetails=SELECT ApplicationDate applicationDate,CmnApplicationRegistrationStatusId updateStatus, CrpEngineerId,ReferenceNo,CIDNo cidNo,a.Name fullname,CmnSalutationId salutation,CmnDzongkhagId dzongkhagId, Gewog gewog,Village village,c.Name cmnCountryId,CmnCountryId countryId,CmnServiceSectorTypeId serviceSectorType,Email email,MobileNo mobileNo,EmployerName employeeName,EmployerAddress employeeAddress,CmnQualificationId qualificationId,GraduationYear graduationyr,NameOfUniversity universityName,CmnUniversityCountryId universityCountry,RegistrationApprovedDate approvaldate,RemarksByApprover approiverremarks,RegistrationExpiryDate regExpDate FROM crpengineer a LEFT JOIN cmncountry c ON c.Id =  a.CmnCountryId WHERE ReferenceNo=?

EngineerDao.getEngineerOngoingApp = SELECT f.CDBNo cdbNo,i.Name updateStatus,f.ApplicationDate applicationDate,f.ReferenceNo ReferenceNo FROM crpengineer f LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId WHERE i.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.CDBNo=:cdbNo

EngineerDao.getPrintList = SELECT f.`CDBNo` cdbNo,f.`ApplicationDate` applicationDate,f.`CIDNo` cidNo,f.`Email` email,f.`Name` fullname,DATE_FORMAT(f.`RegistrationApprovedDate`, '%d-%m-%Y') paymentReceiptNo,f.`MobileNo` mobileNo FROM crpengineerfinal f WHERE f.`CmnApplicationRegistrationStatusId`=? GROUP BY f.Email

EngineerDao.getEngineerPrintDetails = SELECT a.ReferenceNo referenceNo,a.CIDNo ownerCID,a.Name ownerName,d.NameEn dzongkhagName,c.Name countryName,ser.Name serviceSectorType,a.CmnServiceSectorTypeId serviceSectorTypeId,a.CDBNo cdbNo,DATE_FORMAT(a.RegistrationExpiryDate, '%d-%m-%Y') regExpiryDate,DATE_FORMAT(a.InitialDate, '%d-%m-%Y') initialRegistrationDate,DATE_FORMAT(a.ApplicationDate, '%d-%m-%Y') revalidationDate,cm.Name firmName FROM crpengineerfinal a  LEFT JOIN cmndzongkhag d ON d.Id = a.CmnDzongkhagId LEFT JOIN cmncountry c ON c.Id = a.CmnCountryId LEFT JOIN cmnlistitem ser ON ser.Id = a.CmnServiceSectorTypeId LEFT JOIN cmnlistitem cm ON cm.Id = a.CmnTradeId WHERE a.CDBNo =?