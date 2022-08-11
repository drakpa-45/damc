/*Common Dao*/
CommonDao.gCountryList = SELECT a.`Id`AS value, a.`Name` AS text FROM `cmncountry` a ORDER BY a.`Code` DESC, a.`Name` ASC
CommonDao.gAgencyList = SELECT a.Id AS value, a.Agency_Name_Desc AS text FROM cmnagencies a ORDER BY a.Id  ASC
CommonDao.gRolesList =  SELECT a.Role_Id AS value, a.Role_Name AS text FROM cmnrolemaster a ORDER BY a.Role_Id  ASC
CommonDao.gRegionList=SELECT a.Id AS value, a.Region_Name AS text FROM cmnregion a ORDER BY a.Id  ASC
CommonDao.gDesignationList=SELECT a.Id AS value, a.Designation AS text FROM cmndesignation a ORDER BY a.Id  ASC
CommonDao.gSectorList=SELECT a.Id AS value, a.SectorName AS text FROM cmnsector a ORDER BY a.Id  ASC
CommonDao.gGewogUsersList=SELECT a.Id AS value, a.GewogUseRoles AS text FROM cmngewogusers a ORDER BY a.Id  ASC

CommonDao.gDzongkhagList = SELECT a.`Dzongkhag_Serial_No` AS value,a.`Dzongkhag_Name` AS text FROM `t_dzongkhag_lookup` a

CommonDao.isUsenameExist = SELECT a.username FROM sysuser a WHERE a.username =:username AND a.password=:pwd
CommonDao.isUsenameExist4gotPwd = SELECT a.username FROM sysuser a WHERE a.username =:username
