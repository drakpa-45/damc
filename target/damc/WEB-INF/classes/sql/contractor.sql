/*Contractor Dao*/
ContractorDao.gFeeStructure = SELECT cc.`Id` AS id,cc.`Name` AS name,cc.`Code` AS code,cc.`RegistrationFee` AS registrationFee,cc.`RenewalFee` AS renewalFee FROM `cmncontractorclassification` cc where (:category is null or cc.Id =:category) ORDER BY referenceNo ASC
ContractorDao.gContractCategory = SELECT wc.Id AS id, wc.`Name` AS name,wc.`Code` AS code FROM `cmncontractorworkcategory` wc ORDER BY referenceNo ASC
ContractorDao.gClassification = SELECT a.Id AS value, CONCAT(a.Code,':',a.Name) text FROM `cmncontractorclassification` a ORDER BY a.code ASC
ContractorDao.gEquipment = SELECT a.`Id` AS value,a.`Name` AS text,a.IsRegistered as obj1  FROM `cmnequipment` a ORDER BY a.Name ASC
/*ContractorDao.isEmailUnique = SELECT a.Email FROM `crpcontractor` a WHERE a.Email =:email*/
ContractorDao.isEmailUnique = SELECT a.Email FROM `sysuser` a WHERE a.Email =:email
ContractorDao.isFirmNameUnique = SELECT a.NameOfFirm FROM `crpcontractorfinal` a WHERE a.NameOfFirm =:firmName
ContractorDao.getTrainingDtl = SELECT a.`CmnTrainingTypeId` tTypeId,a.`CmnTrainingModuleId` tModuleId,tt.`Name` tType,tm.`Name` tModule,a.`TrainingFromDate` fromDate,a.`TrainingToDate` toDate,b.`CIDNo` cidNo,b.`Participant` participant FROM `crpcontractortraining` a LEFT JOIN `crpcontractortrainingdetail` b ON a.`Id` = b.`CrpContractorTrainingId` LEFT JOIN `cmnlistitem` tt ON tt.Id = a.`CmnTrainingTypeId` LEFT JOIN `cmnlistitem` tm ON tm.Id = a.`CmnTrainingModuleId` WHERE b.`CIDNo` =:cidNo
ContractorDao.getRefresherCourseDtl =SELECT a.CmnTrainingTypeId tTypeId,a.CmnTrainingModuleId tModuleId,tt.Name tType,tm.Name tModule,a.TrainingFromDate fromDate,a.TrainingToDate toDate,b.CIDNo cidNo,b.Participant participant FROM crpcontractortraining a LEFT JOIN crpcontractortrainingdetail b ON a.Id = b.CrpContractorTrainingId LEFT JOIN cmnlistitem tt ON tt.Id = a.CmnTrainingTypeId LEFT JOIN cmnlistitem tm ON tm.Id = a.CmnTrainingModuleId WHERE b.CrpContractorFinalId =:contractorFinalId

ContractorDao.getContractorOngoingApp = SELECT aa.`CrpContractorId` contractorId,aa.`ReferenceNo` AS referenceNo, aa.ApplicationDate AS applicationDate, aa.CDBNo AS cdbNo,aa.Id AS appStatusId,bb.Name AS appStatusName FROM `crpcontractor` aa LEFT JOIN cmnlistitem bb ON aa.`CmnApplicationRegistrationStatusId`=bb.Id WHERE  bb.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND aa.CDBNo =:cdbNo
ContractorDao.getHRAttachmentFinal = SELECT Id id, CrpContractorHumanResourceFinalId contractorHrId,DocumentName documentName,DocumentPath documentPath, FileType fileType,CreatedBy createdBy ,CreatedOn createdOn FROM crpcontractorhumanresourceattachmentfinal WHERE Id=:hraId
ContractorDao.getEQAttachmentFinal = SELECT Id id,CrpContractorEquipmentFinalId equipmentId,DocumentName documentName,DocumentPath documentPath,FileType fileType,CreatedBy createdBy,CreatedOn createdOn FROM crpcontractorequipmentattachmentfinal WHERE Id = :eqaId
ContractorDao.getAttachmentFinal = SELECT Id id,CrpContractorFinalId contractorId,DocumentName documentName,DocumentPath documentPath,AttachmentFor attachmentFor,FileType fileType,CreatedBy createdBy,CreatedOn createdOn FROM crpcontractorattachmentfinal WHERE Id = :aId
/** ContractorActionDao */
ContractorActionDao.gTaskList = CALL ProCrpContractorTaskList (:userId,:status,:service);

ContractorActionDao.getContractorHRs = SELECT hr.`Id` AS id,hr.`CrpContractorId` contractorID,hr.`CIDNo`cidNo,hr.`Name` AS name, hr.`Sex` sex,hr.`ShowInCertificate` AS siCertificate, hr.Verified as verified,hr.Approved \
,hr.`IsPartnerOrOwner` isPartnerOrOwner,hr.DeleteRequest AS deleteRequest, DATE_FORMAT(hr.`JoiningDate`,'%d-%m-%Y') joiningDate,sa.`Name` salutationName,co.`Name` countryName,qu.`Name` qualificationName,st.`Name` serviceTypeName,td.`Name` tradeName,de.`Name` designationName \
FROM `crpcontractorhumanresource` hr LEFT JOIN `cmnlistitem` sa ON sa.`Id` = hr.`CmnSalutationId` LEFT JOIN `cmncountry` co ON co.`Id` = hr.`CmnCountryId` LEFT JOIN `cmnlistitem` qu ON qu.`Id` = hr.`CmnQualificationId` \
LEFT JOIN `cmnlistitem` st ON st.`Id` = hr.`CmnServiceTypeId` LEFT JOIN `cmnlistitem` td ON td.`Id` = hr.`CmnTradeId` LEFT JOIN `cmnlistitem` de ON de.`Id` = hr.`CmnDesignationId` WHERE hr.`CrpContractorId` =:contractorId and (:ownerOrPartner is null or hr.`IsPartnerOrOwner` =:ownerOrPartner)

ContractorActionDao.getFeeCategoryClass = SELECT cc.`Id` id,cc.`CmnCategoryId` categoryId,CONCAT(wc.`Code`,'-',wc.`Name`) categoryName, a.`Name` aClassName,cc.`AppliedAmount` AS aAmount,v.`Name` vClassName,cc.`VerifiedAmount` AS vAmount,ap.`Name` apClassName,cc.ApprovedAmount apAmount \
FROM `crpcontractorregistrationpayment` cc LEFT JOIN `cmncontractorworkcategory` wc ON wc.`Id` = cc.`CmnCategoryId` LEFT JOIN `cmncontractorclassification` a ON a.`Id` = cc.`CmnAppliedClassificationId` LEFT JOIN `cmncontractorclassification` v ON v.`Id` = cc.`CmnVerifiedClassificationId` \
LEFT JOIN `cmncontractorclassification` ap ON ap.`Id` = cc.`CmnApprovedClassificationId`  WHERE cc.`CrpContractorFinalId` =:contractorId

ContractorActionDao.getEquipment = SELECT ce.`Id` id, ce.`CrpContractorId` contractorId, eq.`Name` equipmentName,ce.`RegistrationNo` registrationNo,ce.`SerialNo` serialNo,ce.`Quantity` quantity,ce.`ModelNo` modelNo \
,ce.Verified as verified, ce.Approved as approved,ce.CmnEquipmentId equipmentId, CASE WHEN eq.IsRegistered = '1' THEN 'Registered' ELSE 'Not Registered' END AS equipmentType \
FROM `crpcontractorequipment` ce LEFT JOIN `cmnequipment`  eq ON ce.`CmnEquipmentId` = eq.`Id`WHERE ce.`CrpContractorId` = :contractorId

ContractorActionDao.verify = CALL ProCrpContractorApplicationVerify(:contractorId,:vUserId,:vRemarks)
ContractorActionDao.approve = CALL ProCrpContractorApplicationApprove(:contractorId,:aUserId,:aRemarks)
ContractorActionDao.reject = UPDATE crpcontractor c SET c.SysRejecterUserId =:userId, \
		                          c.RejectedDate = CURDATE(), \
		                          c.RemarksByRejector = :remarks, \
                              c.RegistrationStatus = 3, \
		                          c.CmnApplicationRegistrationStatusId =:applicationStatusId \
	                          WHERE c.CrpContractorId=:contractorId

ContractorActionDao.sendBack=UPDATE crpcontractor c SET c.EditedBy =:userId, \
		                          c.EditedOn = CURDATE(), c.SysLockedByUserId = null, \
                              c.RegistrationStatus = 7, \
		                          c.CmnApplicationRegistrationStatusId =:applicationStatusId \
	                          WHERE c.CrpContractorId=:contractorId

ContractorActionDao.paymentUpdate = CALL ProCrpContractorNewRegistrationFinalData(:contractorId,:userId,:appStatusId,:createdBy,:hashed_password)

ContractorActionDao.getAppHistoryDtl = SELECT c.* , case v.`FullName` when NULL then '(Citizen)' else v.`FullName` end userName FROM ( SELECT 'Submitted' appStatus,c.`CrpContractorId` contractorId,c.`CreatedBy` userId,c.`CreatedOn` actionDate,''remarks FROM `crpcontractor` c UNION ALL SELECT 'Verified',c.`CrpContractorId`,c.`SysVerifierUserId`,c.`RegistrationVerifiedDate`,c.`RemarksByVerifier`FROM `crpcontractor` c UNION ALL SELECT 'Approved for Payment',c.`CrpContractorId`,c.`SysApproverUserId`,c.`RegistrationApprovedDate`,c.`RemarksByApprover` FROM `crpcontractor` c UNION ALL SELECT 'Rejected',c.`CrpContractorId`,c.`SysRejecterUserId`,c.`RejectedDate`,c.`RemarksByRejector` FROM `crpcontractor` c ) c LEFT JOIN `sysuser` v ON c.`userId` = v.`Id` WHERE c.`contractorId` =:contractorId AND c.actionDate IS NOT NULL
ContractorActionDao.send2MyOrGroupTask = UPDATE crpcontractor a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo
ContractorActionDao.getNextCDBNo = SELECT DISTINCT a.`CDBNo`+1 AS cdbNo  FROM crpcontractorfinal a ORDER BY CONVERT(a.`CDBNo`, DECIMAL) DESC  LIMIT 1
ContractorActionDao.getHRAttachments = SELECT a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorhumanresourceattachment` a WHERE a.`CrpContractorHumanResourceId` = :hrId
ContractorActionDao.getEQAttachments = SELECT a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorequipmentattachment` a WHERE a.`CrpContractorEquipmentId`  = :eqId
ContractorActionDao.getIncAttachment = SELECT DocumentName AS documentName, DocumentPath documentPath,FileType AS fileType,AttachmentFor AS attachmentFor FROM crpcontractorattachment WHERE CrpContractorId =:contractorId
ContractorActionDao.getCDBNoFromAppNo = SELECT b.cdbno FROM  crpcontractor a LEFT JOIN crpcontractorfinal b ON a.email = b.email WHERE a.referenceNo =:appNo
/* Contractor renewal dao*/
ContractorRCDao.getContractorStatus =select bb.Id value,bb.Name text,bb.ReferenceNo obj1 from `crpcontractorfinal` aa left join cmnlistitem bb on aa.`CmnApplicationRegistrationStatusId`=bb.Id where cdbNo = :cdbNo
ContractorRCDao.getServicesFee = SELECT Id,ReferenceNo, Name AS serviceName, ContractorAmount AS feeAmount  FROM `crpservice` WHERE ((:refNo is null and ReferenceNo IN (4,6,7,8,9,10,12)) or ReferenceNo = :refNo)

ContractorRCDao.getContractorHRsFinal = SELECT hr.`Id` AS id,hr.`CrpContractorFinalId` contractorID,hr.`CIDNo`cidNo,hr.`Name` AS name, hr.`Sex` sex,hr.`ShowInCertificate` AS siCertificate, hr.DeleteRequest AS deleteRequest \
,hr.`IsPartnerOrOwner` isPartnerOrOwner,DATE_FORMAT(hr.`JoiningDate`,'%d-%m-%Y') joiningDate,sa.`Name` salutationName,co.`Name` countryName,qu.`Name` qualificationName,st.`Name` serviceTypeName,td.`Name` tradeName,de.`Name` designationName,hr.CmnCountryId countryId, hr.CmnDesignationId designationId, hr.CmnSalutationId salutationId \
FROM `crpcontractorhumanresourcefinal` hr LEFT JOIN `cmnlistitem` sa ON sa.`Id` = hr.`CmnSalutationId` LEFT JOIN `cmncountry` co ON co.`Id` = hr.`CmnCountryId` LEFT JOIN `cmnlistitem` qu ON qu.`Id` = hr.`CmnQualificationId` \
LEFT JOIN `cmnlistitem` st ON st.`Id` = hr.`CmnServiceTypeId` LEFT JOIN `cmnlistitem` td ON td.`Id` = hr.`CmnTradeId` LEFT JOIN `cmnlistitem` de ON de.`Id` = hr.`CmnDesignationId` WHERE hr.`CrpContractorFinalId` =:contractorId and (:ownerOrPartner is null or hr.`IsPartnerOrOwner` =:ownerOrPartner)

ContractorRCDao.getEquipmentFinal = SELECT ce.`Id` id, eq.`Name` equipmentName,ce.`RegistrationNo` registrationNo,ce.`SerialNo` serialNo,ce.`Quantity` quantity,ce.`ModelNo` modelNo ,ce.DeleteRequest AS deleteRequest,ce.EditCheck editCheck,ce.CmnEquipmentId equipmentId, CASE WHEN eq.IsRegistered = '1' THEN 'Registered' ELSE 'Not Registered' END AS equipmentType FROM `crpcontractorequipmentfinal` ce LEFT JOIN `cmnequipment`  eq ON ce.`CmnEquipmentId` = eq.`Id` WHERE ce.`CrpContractorFinalId` =:contractorId
ContractorRCDao.getHRAttachmentsFinal = SELECT a.Id as id,a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorhumanresourceattachmentfinal` a WHERE a.`CrpContractorHumanResourceFinalId` = :hrId
ContractorRCDao.getEQAttachmentsFinal = SELECT a.Id as id, a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorequipmentattachmentfinal` a WHERE a.`CrpContractorEquipmentFinalId`  = :eqId
ContractorRCDao.getCategoryClassFinal = SELECT 	`Id` AS id, `CrpContractorFinalId` AS contractorId,`CmnProjectCategoryId` AS categoryId,`CmnAppliedClassificationId` AS aClassId, `CmnVerifiedClassificationId` AS vClassId, `CmnApprovedClassificationId` AS apClassId FROM `crpcontractorworkclassificationfinal` WHERE CrpContractorFinalId =:contractorId ORDER BY CmnProjectCategoryId
ContractorRCDao.saveDeleteHrRequest = Update crpcontractorhumanresourcefinal set DeleteRequest = 1 where Id =:hrId
ContractorRCDao.saveDeleteEqRequest = Update crpcontractorequipmentfinal set DeleteRequest = 1 where Id =:eqId
ContractorRCDao.saveEditEqRequest = Update crpcontractorequipmentfinal set EditCheck = 1 where Id =:eqId

ContractorRCDao.saveHrShowIncert = Update crpcontractorhumanresourcefinal set ShowInCertificate = 0 where Id =:hrId

/*ContractorRCDao.auditMemo = SELECT CONCAT('You have following audit memo:<br>',AIN,' : ',`AuditObservation`) AS auditObservation FROM `crpcontractorauditclearance` WHERE `CrpContractorConsultantId` =:contractorFinalId AND  `Dropped` = '0'*/
ContractorRCDao.auditMemo = SELECT AIN messages,AuditObservation remarks,Agency serviceName FROM crpcontractorauditclearance WHERE CrpContractorConsultantId = :contractorFinalId AND Dropped ='0' ORDER BY AIN
ContractorRCDao.getIncAttachmentFinal = SELECT Id id, DocumentName AS documentName, DocumentPath documentPath,FileType AS fileType,AttachmentFor AS attachmentFor FROM crpcontractorattachmentfinal WHERE CrpContractorFinalId =:contractorFinalId

/* get list of print certificate*/
ContractorActionDao.getPrintList =SELECT f.CDBNo cdbNo,f.ApplicationDate applicationDate,f.NameOfFirm fullname,DATE_FORMAT(f.RegistrationApprovedDate, '%d-%m-%Y') paymentReceiptNo FROM crpcontractorfinal f  WHERE f.CmnApplicationRegistrationStatusId =? GROUP BY f.Email ;

/*Contractor Renewal Action Dao*/
ContractorRCActionDao.getAppliedServices = SELECT a.ReferenceNo applicationNo,c.CmnServiceTypeId serviceId,d.referenceNo serviceRefNo, d.Name serviceName, e.PaymentAmount ,a.NameOfFirm firmName,b.Name AS appStatus,a.ApplicationDate applicationDate FROM crpcontractor a LEFT JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId LEFT JOIN crpcontractorappliedservice c ON c.CrpContractorId = a.CrpContractorId LEFT JOIN crpservice d ON d.Id = c.CmnServiceTypeId LEFT JOIN crpcontractorservicepayment  e ON e.CrpContractorId = c.CrpContractorId AND e.CmnServiceTypeId = c.CmnServiceTypeId WHERE a.ReferenceNo=:applicationNo

/*ContractorRCActionDao.getAppliedServices = SELECT CAST(a.`ReferenceNo` as char ) applicationNo,c.CmnServiceTypeId serviceId,CAST(d.referenceNo as char) serviceRefNo, d.Name serviceName, e.PaymentAmount ,a.`NameOfFirm` firmName,b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpcontractor` a  LEFT JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId` LEFT JOIN `crpcontractorappliedservice` c ON c.`CrpContractorId` = a.`CrpContractorId` LEFT JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` LEFT JOIN `crpcontractorservicepayment`  e ON e.`CrpContractorId` = c.CrpContractorId AND e.`CmnServiceTypeId` = c.CmnServiceTypeId WHERE a.`ReferenceNo` = :applicationNo */
ContractorRCActionDao.getDeleteHrRequest = SELECT hr.`Id` AS id,hr.`CrpContractorFinalId` contractorID,hr.`CIDNo`cidNo,hr.`Name` AS name, hr.`Sex` sex,hr.`ShowInCertificate` AS siCertificate, hr.DeleteRequest AS deleteRequest \
,hr.`IsPartnerOrOwner` isPartnerOrOwner,hr.`JoiningDate` joiningDate,sa.`Name` salutationName,co.`Name` countryName,qu.`Name` qualificationName,st.`Name` serviceTypeName,td.`Name` tradeName,de.`Name` designationName \
FROM `crpcontractorhumanresourcefinal` hr LEFT JOIN `cmnlistitem` sa ON sa.`Id` = hr.`CmnSalutationId` LEFT JOIN `cmncountry` co ON co.`Id` = hr.`CmnCountryId` LEFT JOIN `cmnlistitem` qu ON qu.`Id` = hr.`CmnQualificationId` \
LEFT JOIN `cmnlistitem` st ON st.`Id` = hr.`CmnServiceTypeId` LEFT JOIN `cmnlistitem` td ON td.`Id` = hr.`CmnTradeId` LEFT JOIN `cmnlistitem` de ON de.`Id` = hr.`CmnDesignationId` WHERE hr.`CrpContractorFinalId` =:contractorId and (:ownerOrPartner is null or hr.`IsPartnerOrOwner` =:ownerOrPartner)
/*
ContractorRCActionDao.getProposedCategories = SELECT s.`CrpContractorId` contractorId,ss.Name serviceName,ss.ReferenceNo serviceRefNo,ca.Code categoryName,ex.Name exClassName,ap.Name aClassName ,d.Amount aAmount, d.`CmnCategoryId` categoryId,d.`CmnExistingClassificationId` exClassId,d.`CmnAppliedClassificationId` aClassId \
FROM `crpcontractorservicepayment` s LEFT JOIN `crpcontractorservicepaymentdetail` d ON s.`Id` = `CrpContractorServicePaymentId` LEFT JOIN `crpcontractor` c ON s.`CrpContractorId` = c.Id LEFT JOIN `crpservice` ss ON ss.Id = s.`CmnServiceTypeId` \
LEFT JOIN `cmncontractorclassification` ap ON ap.Id = d.`CmnAppliedClassificationId` LEFT JOIN `cmncontractorclassification` ex ON ex.Id = d.`CmnExistingClassificationId` \
LEFT JOIN `cmncontractorworkcategory` ca ON ca.Id = d.`CmnCategoryId` WHERE c.`ReferenceNo` =:appNo ORDER BY ca.ReferenceNo ASC*/

ContractorRCActionDao.getProposedCategories = SELECT s.`CrpContractorId` contractorId,ss.Name serviceName,ss.ReferenceNo serviceRefNo,ca.Code categoryName,ex.Name exClassName,ap.Name aClassName ,d.Amount aAmount, d.`CmnCategoryId` categoryId,d.`CmnExistingClassificationId` exClassId,d.`CmnAppliedClassificationId` aClassId \
FROM `crpcontractorservicepayment` s INNER JOIN `crpcontractorservicepaymentdetail` d ON s.`Id` = `CrpContractorServicePaymentId` INNER JOIN `crpcontractor` c ON s.`CrpContractorId` = c.Id INNER JOIN `crpservice` ss ON ss.Id = s.`CmnServiceTypeId` \
LEFT JOIN `cmncontractorclassification` ap ON ap.Id = d.`CmnAppliedClassificationId` LEFT JOIN `cmncontractorclassification` ex ON ex.Id = d.`CmnExistingClassificationId` \
LEFT JOIN `cmncontractorworkcategory` ca ON ca.Id = d.`CmnCategoryId` WHERE c.`ReferenceNo` =:appNo ORDER BY ca.ReferenceNo ASC

ContractorRCActionDao.getPreviousCategories =

ContractorRCActionDao.paymentUpdate = CALL ProCrpContractorRenewalPaymentApproval(:contractorId,:userId,:appStatusId,:createdBy)
ContractorRCActionDao.hrEqUpdate = CALL ProCrpContractorHrEqApproval(:contractorId,:userId,:appStatusId,:createdBy)

/* cancellation of CDB certificate queries*/
ContractorActionDao.approveCancellation = CALL ProCrpContractorApplicationCancellationApprove(:contractorId,:aUserId,:aRemarks)

/* comments/adv/monitoring*/

ContractorRCDao.getCommentsRecords =SELECT s.Type ReferenceNo, s.Remarks Remarks, s.Date ActualEndDate  FROM crpcontractorcommentsadverserecord s WHERE s.CrpContractorFinalId =(SELECT Id FROM crpcontractorfinal c WHERE c.CDBNo=?) AND Type=1
ContractorRCDao.getAdvRecords =SELECT s.Type years, s.Remarks Remarks, s.Date completeDate  FROM crpcontractorcommentsadverserecord s WHERE s.CrpContractorFinalId =(SELECT Id FROM crpcontractorfinal c WHERE c.CDBNo=?) AND Type=2

ContractorActionDao.getFeeForRenewOS =SELECT SUM(c.Amount) FROM crpcontractorservicepaymentdetail c LEFT JOIN crpcontractorservicepayment s ON s.Id = c.CrpContractorServicePaymentId WHERE s.CrpContractorId =?