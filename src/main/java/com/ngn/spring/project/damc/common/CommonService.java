package com.ngn.spring.project.damc.common;

import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.auth.LoginService;
import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.damc.admin.dto.EquipmentDTO;
import com.ngn.spring.project.damc.common.dto.*;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.ResponseMessage;
import com.ngn.spring.project.token.APIService;
import com.ngn.spring.project.token.Token;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizenDetailsResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizendetailsObj;
import org.wso2.client.model.RCSC_ZestEmployeeDetailServiceAPI.GetEmployeeDetailByCidOBJ;
import org.wso2.client.model.RCSC_ZestEmployeeDetailServiceAPI.GetEmployeeDetailByCidResponse;
import org.wso2.client.model.RSTA_LicenseAndVehicleInformationAPI.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class CommonService extends BaseService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private LoginService loginService;

    @Autowired
    private APIService apiService;
    /**
     * To get the country list
     * @return List
     */

    @Transactional(readOnly = true)
    public List gCountryList(){
        return commonDao.gCountryList();
    }

    /**
     * To get the dzongkhag list
     * @return List
     */

    @Transactional(readOnly = true)
    public List gDzongkhagList(){
        return commonDao.gDzongkhagList();
    }

    /**
     * To get the dzongkhag list
     * @return List
     */

    @Transactional(readOnly = true)
    public List gCmnListItem(String cmnListId){
        return commonDao.gCmnListItem(cmnListId);
    }

    @Transactional(readOnly = true)
    public List gCmnListItemOw(String cmnListId){
        return commonDao.gCmnListItemOw(cmnListId);
    }

    @Transactional(readOnly = true)
    public List gCmnListItemHr(String cmnListId){
        return commonDao.gCmnListItemHr(cmnListId);
    }

    @Transactional(readOnly = true)
    public Object getNextID(String tblName, String colName){
        return commonDao.getNextID(tblName, colName);
    }

    /**
     * To get the a column value from certain table with filter
     * @param tblName -- table name to fetch data from
     * @param colName -- column name
     * @param filterCol -- filtering column
     * @param filterVal -- filtering value
     * @return data
     */
    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String filterCol, String filterVal ){
        return commonDao.getValue(tblName, colName,filterCol,filterVal);
    }
    /**
     * To get the a column value from certain table with multiple filter condition
     * @param tblName -- table name to fetch data from
     * @param colName -- column name
     * @param condition -- filtering condition, can include multiple condition with and or operator
     * @return data
     */
    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String condition ){
        return commonDao.getValue(tblName, colName,condition);
    }

    @Transactional(readOnly = true)
    public ResponseMessage getGewogList(String dzongkhagId){
        List gewogList = commonDao.getGewogList(dzongkhagId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(gewogList);
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public ResponseMessage getVillageList(String gewogId){
        List villageList = commonDao.getVillageList(gewogId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(villageList);
        return responseMessage;
    }

    public String getRandomGeneratedId(){
        return UUID.randomUUID().toString();
    }

    public String uploadDocument(MultipartFile attachment,String loc, String fileName) throws Exception{
        String rootPath = null;
        if (attachment != null && !attachment.isEmpty()) {
            //get file upload location from properties file
            Resource resource = new ClassPathResource("/properties/fileUpload.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            rootPath= props.getProperty("fileUpload.loc");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
            String urlAppender = "/" + calendar.get(Calendar.YEAR) + "/" + dateFormat.format(calendar.getTime()) + "/" + calendar.get(Calendar.DATE) + "/";
            rootPath = rootPath + loc + urlAppender + fileName;

            byte[] bytes = attachment.getBytes();
            Path path = Paths.get(rootPath);

            Path parentDir = path.getParent();
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);
            Files.write(path, bytes);
        }
        return rootPath;
    }

    @Transactional(readOnly = true)
    public void viewDownloadFile(String tableName,String filterCol,String filterVal,HttpServletResponse response) throws Exception{
        FileDetailDTO fileDetailDTO = commonDao.getAttachmentDetail(tableName,filterCol,filterVal);
        if(fileDetailDTO != null) {

            File file = new File(fileDetailDTO.getFilePath());
            byte[] bFile = Files.readAllBytes(file.toPath());
            if(file.exists())
                try {
                    downloadFile(bFile,fileDetailDTO.getFileName(),response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void viewDownloadFile(String documentPath,HttpServletResponse response) throws Exception{
        if(documentPath != null) {
            //documentPath = documentPath.replaceAll("///","\\");
            File file = new File(documentPath);
            byte[] bFile = Files.readAllBytes(file.toPath());
            if(file.exists())
                try {
                    downloadFile(bFile,file.getName(),response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
             }
         }

    public ResponseMessage downloadFile(byte[] file, String fileName, HttpServletResponse response) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();
        if (fileName == null || fileName.isEmpty()) {
            responseMessage.setStatus(0);
            responseMessage.setText("File Name is empty");
            return responseMessage;
        }
        if (file == null) {
            responseMessage.setStatus(0);
            responseMessage.setText("No file to download");
            return responseMessage;
        }

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (fileExt) {
            case "xlsx":
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "xls":
                response.setContentType("application/vnd.ms-excel");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "docx":
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "doc":
                response.setContentType("application/msword");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "pdf":
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "inline:filename=" + fileName);
                break;
            case "png":
                response.setContentType("image/png");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpg":
                response.setContentType("image/jpeg");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpeg":
                response.setContentType("image/pjpeg");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "txt":
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "csv":
                response.setContentType("text/csv");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
        }
        response.setContentLength(file.length);
        FileCopyUtils.copy(file, response.getOutputStream());
        responseMessage.setStatus(1);
        responseMessage.setText("File downloaded successfully.");
        return responseMessage;
    }

    public String getFileEXT(MultipartFile attachment){
        String originalFN = attachment.getOriginalFilename();
        return originalFN.substring(originalFN.lastIndexOf("."));
    }

    //TODO-- fetch from API DCRC
    public ResponseMessage getPersonalInfo(String cid, String type){
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String dcrcCitizenEndPointUrl = resourceBundle1.getString("getCitizenDetailsDCRC.endPointURL");
        //String dcrcCitizenaccessToken =resourceBundle1.getString("getCitizenDetails.accessToken");
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();

        if(type.equalsIgnoreCase("fetch") || type.equalsIgnoreCase("check")) {
            String cdbNo = commonDao.getCDBNo(cid);
            if (!cdbNo.isEmpty()) {
                personalInfoDTO.setCdbNo(cdbNo);
                personalInfoDTO.setRemarks("This CID is already registered in one of the services.");
            }else {
                personalInfoDTO.setCdbNo("Not Registered");
            }
        }
        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(dcrcCitizenEndPointUrl);

            Token token = apiService.getApplicationToken();
            String tokenApi = token.getAccess_token();
            apiClient.setAccessToken(tokenApi);
          //  apiClient.setAccessToken(dcrcCitizenaccessToken);

            org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi api = new DefaultApi(apiClient);
            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(cid);

            CitizendetailsObj citizendetailsObj = citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);
            String dzongkhagIdDCRC = citizendetailsObj.getDzongkhagId();
            if(dzongkhagIdDCRC.length() == 1){
                dzongkhagIdDCRC = '0'+dzongkhagIdDCRC;
            }
            String dzonghagId=commonDao.getValue("cmndzongkhag","Id","Code",dzongkhagIdDCRC).toString();

            personalInfoDTO.setCidNo(cid);
            personalInfoDTO.setFullName(citizendetailsObj.getFirstName() + " " + citizendetailsObj.getMiddleName() + " " + citizendetailsObj.getLastName());
            personalInfoDTO.setFullName(personalInfoDTO.getFullName().replaceAll("null", ""));
            personalInfoDTO.setSex(citizendetailsObj.getGender());
            personalInfoDTO.setDzongkhagId(dzonghagId);
            personalInfoDTO.setDzongkhagNmae(citizendetailsObj.getDzongkhagName());
            personalInfoDTO.setGowegId(citizendetailsObj.getGewogId());
            personalInfoDTO.setGowegName(citizendetailsObj.getGewogName());
            personalInfoDTO.setVillageId(citizendetailsObj.getVillageSerialNo());
            personalInfoDTO.setVillageName(citizendetailsObj.getVillageName());
            personalInfoDTO.setDob(citizendetailsObj.getDob());
            personalInfoDTO.setCidNo(citizendetailsObj.getCid());

            GovDTO rcscDetails = getDetailsFromRCSC(cid);

            if(type.equalsIgnoreCase("check")){
                personalInfoDTO.setEmployeeDetailsDTOs(commonDao.validateWorkEngagementCidNo(cid));
                personalInfoDTO.setCdbDTOs(commonDao.validatePartnerCidNoContractorfinal(cid));
                personalInfoDTO.setCdbDTOs1(commonDao.validatePartnerCidNoConsultantfinal(cid));
                personalInfoDTO.setCdbDTOs2(commonDao.validatePartnerCidNoSpecializedFirmfinal(cid));
                personalInfoDTO.setGovCopDTOs(commonDao.validateCorporateCidNo(cid));
            }
            responseMessage = new ResponseMessage();
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setDto(personalInfoDTO);
            responseMessage.setDto1(rcscDetails);
            return responseMessage;
        }catch(Exception e){
            System.out.print("Exception in CommonDaoImpl # getPersonalDetails: "+e);
            personalInfoDTO.setFullName(" ");
            responseMessage = new ResponseMessage();
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Could not connect to DCRC API. Please wait for the connection OR enter the information correctly.");

            GovDTO rcscDetails = getDetailsFromRCSC(cid);
            if(type.equalsIgnoreCase("check")){
                personalInfoDTO.setEmployeeDetailsDTOs(commonDao.validateWorkEngagementCidNo(cid));
                personalInfoDTO.setCdbDTOs(commonDao.validatePartnerCidNoContractorfinal(cid));
                personalInfoDTO.setCdbDTOs1(commonDao.validatePartnerCidNoConsultantfinal(cid));
                personalInfoDTO.setCdbDTOs2(commonDao.validatePartnerCidNoSpecializedFirmfinal(cid));
                personalInfoDTO.setGovCopDTOs(commonDao.validateCorporateCidNo(cid));
            }
            responseMessage = new ResponseMessage();
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setDto(personalInfoDTO);
            responseMessage.setDto1(rcscDetails);
            e.printStackTrace();
            return responseMessage;
           /* personalInfoDTO.setFullName("Drakpa");
            personalInfoDTO.setSex("M");
            personalInfoDTO.setCidNo("11214002875");
            personalInfoDTO.setDzongkhagNmae("Thimphu");
            personalInfoDTO.setGowegId("3004");
            personalInfoDTO.setGowegName("Thimthrom");
            personalInfoDTO.setVillageId("4003");
            personalInfoDTO.setVillageName("Thimthrom");
            String dzong1=commonDao.getValue("cmndzongkhag","Id","NameEn","Thimphu").toString();
            personalInfoDTO.setDzongkhagId(dzong1);
            if (type.equalsIgnoreCase("check")) {
                personalInfoDTO.setEmployeeDetailsDTOs(commonDao.validateWorkEngagementCidNo(cid));
                personalInfoDTO.setCdbDTOs(commonDao.validatePartnerCidNoContractorfinal(cid));
                personalInfoDTO.setCdbDTOs1(commonDao.validatePartnerCidNoConsultantfinal(cid));
                personalInfoDTO.setCdbDTOs2(commonDao.validatePartnerCidNoSpecializedFirmfinal(cid));
                personalInfoDTO.setGovCopDTOs(commonDao.validateCorporateCidNo(cid));
            }
             e.printStackTrace();
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setDto(personalInfoDTO);
            return responseMessage;*/
        }
    }

    private GovDTO getDetailsFromRCSC(String cid) {
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String rcscEndPointUrl = resourceBundle1.getString("getRCSCDetails.endPointURL");
        GovDTO dto = new GovDTO();
        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);

            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(rcscEndPointUrl);

            Token token = apiService.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());

            org.wso2.client.api.RCSC_ZestEmployeeDetailServiceAPI.DefaultApi rcscApi = new org.wso2.client.api.RCSC_ZestEmployeeDetailServiceAPI.DefaultApi(apiClient);
            GetEmployeeDetailByCidResponse employeeDetailsResponse = rcscApi.employeedetailbycidCidGet(cid);
            GetEmployeeDetailByCidOBJ employeeDetailsOBJ=null;
            if (employeeDetailsResponse.getEmployeedetails().getEmployeedetail() != null && !employeeDetailsResponse.getEmployeedetails().getEmployeedetail().isEmpty()) {
                employeeDetailsOBJ = employeeDetailsResponse.getEmployeedetails().getEmployeedetail().get(0);
                dto.setFullName(employeeDetailsOBJ.getFirstName() + " " + employeeDetailsOBJ.getMiddleName() + " " + employeeDetailsOBJ.getLastName());
                dto.setId(employeeDetailsOBJ.getEmployeeNumber());
                dto.setDesignation(employeeDetailsOBJ.getPositionTitle());
                dto.setGradeId(employeeDetailsOBJ.getPositionLevel());
                dto.setMinistryName(employeeDetailsOBJ.getParentAgency());
            }
        } catch (Exception e) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Could not connect to RCSC API. Please wait for the connection OR enter the information correctly. " + e);
            // TODO: un-comment when API is using.
        }
        return dto;
}

    public TasklistDto populateCount(String type,String registrationtype) {
        return commonDao.populateCount(type,registrationtype);
    }

    public TasklistDto populateNotificationCount(String type) {
        Toolkit.getDefaultToolkit().beep();
        return commonDao.populateNotificationCount(type);
    }

    public String getCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getAppType(loginDTO);
        return app_type;
    }

    public List<TasklistDto> getdashboardDetails(String type) {
        List<TasklistDto> dashboards=commonDao.getdashboardDetails(type);
        return dashboards;
    }

    public List<TasklistDto> populaterejectedApplications(String getCdbNoForSurvey, String getCdbNoForSp, String cdbdet) {
        List<TasklistDto> rejectedapplist=commonDao.populaterejectedApplications(cdbdet,getCdbNoForSurvey,getCdbNoForSp);
        return rejectedapplist;
    }

    public String getSpCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getSpAppType(loginDTO);
        return app_type;
    }

    public String getCdbNoForSp(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForSp(loginDTO);
        return app_type;
    }

    public String getCdbNoForSpFirm(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForSpFirm(loginDTO);
        return app_type;
    }

    public String getCdbNoForSurvey(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForSurvey(loginDTO);
        return app_type;
    }

    public String getSurveyCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getSurveyCdbNo(loginDTO);
        return app_type;
    }

    @Transactional
    public Boolean isEmailUnique(String email) {
       return commonDao.isEmailUnique(email);
    }

    @Transactional
    public Boolean getFirmName(HttpServletRequest request) {
        return commonDao.getFirmName(request);
    }
    @Transactional
    public Boolean getFirmNameConsultant(HttpServletRequest request) {
        return commonDao.getFirmNameConsultant(request);
    }
    public String getOwnershipType(String appNo) {
        return commonDao.getOwnershipType(appNo);
    }

    @Transactional
    public String insertuserDetails(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        return commonDao.insertuserDetails(dto1,userID,request);
    }

    public BigInteger getCdbNoForConsultant(LoginDTO loginDTO) {
        BigInteger app_type=commonDao.getCdbNoForConsultant(loginDTO);
        return app_type;
    }

    public String getConsultantCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getConsultantCdbNo(loginDTO);
        return app_type;
    }

    @Transactional
    public Boolean checkIfMailExists(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        return commonDao.checkIfMailExists(dto1);
    }

    @Transactional
    public List<DropdownDTO> getDropdownDetails(String sl_no, String type) {
        return commonDao.getDropdownDetails(sl_no, type);
    }

    @Transactional
    public String updatesysuser(PersonalInfoDTO dto1, String userID, String getfullname, HttpServletRequest request) {
        return commonDao.updatesysuser(dto1, userID, getfullname, request);
    }

    @Transactional
    public String getPreviousMail(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        return commonDao.getPreviousMail(dto1,userID,request);
    }

    @Transactional(readOnly = true)
    public ResponseMessage getTrackRecord(String cdbNo) {
        List<TrackRecord> entity = new ArrayList<TrackRecord>();
         entity=commonDao.getTrackRecord(cdbNo);
        responseMessage.setDto(entity);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public boolean isExpiredApplication(String cdbNo){
        return cdbNo == null?false:commonDao.isExpiredApplication(cdbNo);
    }

    public ResponseMessage checkEquipment(String regNo, String serviceName) {
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String rstaendpointURL =resourceBundle1.getString("getEquipmentDetailsFromRSTA.endPointURL");
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        String vechileType = "";
        List<VehicleDetails> vehicleDetailses = new ArrayList<>();
        if(!regNo.isEmpty() || regNo != ""){
             vechileType = commonDao.getVechileTypeFromVechileNumber(regNo);
        }
        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(rstaendpointURL);

            Token token = apiService.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());
            org.wso2.client.api.RSTA_LicenseAndVehicleInformationAPI.DefaultApi api =
                    new org.wso2.client.api.RSTA_LicenseAndVehicleInformationAPI.DefaultApi(apiClient);
          VehicleownerdetailsResponse vehicleownerdetailsResponse = api.ownerdetailsbyvehicletypeVehicleNoVehicleTypeGet(regNo,vechileType);
            VehicleownerdetailObj vehicledetailObj   = vehicleownerdetailsResponse.getVehicleOwnerDetails().getVehicleOwnerDetail().get(0);

            equipmentDTO.setRegistrationNo(regNo);
            equipmentDTO.setOwnerCid(vehicledetailObj.getOwnerCid());
            equipmentDTO.setOwnerName(vehicledetailObj.getOwnerName());
            equipmentDTO.setEquipmentType(vechileType);

            equipmentDTO.setCdbDTOs(commonDao.fetchEqDtlsFromCDB(regNo,serviceName));
            responseMessage = new ResponseMessage();
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setDto(equipmentDTO);
            return responseMessage;
        }catch(Exception e){
           /* equipmentDTO.setFullName(" ");
            responseMessage = new ResponseMessage();
            responseMessage.setStatus(SUCCESSFUL_STATUS);*/
            /*responseMessage.setText("Could not connect to RSTA API. Please wait for the connection OR enter the information correctly.");
            responseMessage.setDto(personalInfoDTO);
            return responseMessage;*/
            /*vehicleDetailses.size().setRegistrationNo("BP-1-A1234");
            vehicleDetailses.get(0).setRegisteredRegion("Thimphu");
            vehicleDetailses.get(0).setVehicleType("Medium");
            vehicleDetailses.get(0).setOwnerName("Drakpa");*/

            e.printStackTrace();
            equipmentDTO.setCdbDTOs(commonDao.fetchEqDtlsFromCDB(regNo,serviceName));
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setDto(equipmentDTO);
            return responseMessage;
        }
    }

    public String getCdbNoForContractor(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForContractor(loginDTO);
        return app_type;
    }

    public List<TasklistDto> populateapplicationHistoryConsultant(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        dto= commonDao.populateapplicationHistoryConsultant(cdbNo);
        return dto;
    }

    public List<TasklistDto> populateapplicationHistoryContractor(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        dto= commonDao.populateapplicationHistoryContractor(cdbNo);
        return dto;
    }

    public List<TasklistDto> populaterejectedApplicationConsultant(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        dto= commonDao.populaterejectedApplicationConsultant(cdbNo);
        return dto;
    }

    public List<TasklistDto> populaterejectedApplicationContractor(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        dto= commonDao.populaterejectedApplicationContractor(cdbNo);
        return dto;
    }

    public String getCdbNoForArchitect(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForArchitect(loginDTO);
        return app_type;
    }

    public String getArchitectCDBNo(LoginDTO loginDTO) {
        String app_type=commonDao.getArchitectCDBNo(loginDTO);
        return app_type;
    }

    public String getCdbNoForEngineer(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForEngineer(loginDTO);
        return app_type;
    }

    public String getEngineerCDBNo(LoginDTO loginDTO) {
        String app_type=commonDao.getEngineerCDBNo(loginDTO);
        return app_type;
    }

    public Boolean isUsenameExist(String username, String pwd) {
        LoginDTO userLogin = loginService.login(username);
        String replace=userLogin.getPassword().replaceAll("2y", "2a");
        String newPwd = "";
        if(userLogin.getPassword().length()>40 && BCrypt.checkpw(pwd, replace)){
            newPwd = replace;
        }
        return commonDao.isUsenameExist(username,newPwd);
    }

    public Boolean isUsenameExist4gotPwd(String username) {
        return commonDao.isUsenameExist4gotPwd(username);
    }

    @Transactional
    public ResponseMessage updatePhoneNumber(LoginDTO loginDTO, String phoneNumber) {
        String returnMsg = commonDao.updatePhoneNumber(loginDTO, phoneNumber);
        if(returnMsg.equalsIgnoreCase("Success")){
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Phone number updated successfully!");
        }else {
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Unable to update Phone number!");
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage seekClearification(LoginDTO loginDTO, String enquiry, String tenderId) {
        String returnMsg = commonDao.seekClearification(loginDTO, enquiry,tenderId);
        if(returnMsg.equalsIgnoreCase("Success")){
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("enquiry success!");
        }else {
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("enquiry failed!");
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage updateTTNumber(LoginDTO loginDTO,String tradeNumber, String tpn) {
        String returnMsg = commonDao.updateTTNumber(loginDTO, tradeNumber, tpn);
        if(returnMsg.equalsIgnoreCase("Success")){
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Trade number/ TPN number updated successfully!");
        }else {
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Unable to update Trade number/ TPN number!");
        }
        return responseMessage;
    }
    @Transactional
    public ResponseMessage updatePassword(LoginDTO loginDTO, String username, String newPwd) {
         commonDao.updatePassword(loginDTO,username,newPwd);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your password is successfully updated .");
        return responseMessage;
    }

    public ResponseMessage generatePassword(String username) {
        int len = 4;
        String ngpwd = generateRandomPassword(len);
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(ngpwd, salt);

        commonDao.updGeneratedPassword(username,hashed_password);
        String mobileNumber = (String) getValue("t_user_master", "Email_Id", "Login_Id", username);
        String mailContent =
                "<html> <body>"+"Dear User,<br>" +
                "Your new password is generated successfully. And your newly generated password is: <span style='color:blue'>" + ngpwd + "</span><br /><br />"+
                "<span class='text-danger' style='color:red'> NOTE: This is an auto-generated email – please do not reply to it.</span><br/>" +
                "Email Date:" + new Date() + "<br/><br />"+
                "Thank you.<br>"+
                "Sincerely,<br>"+
                "DAMC<br><br>"+
                "</body></html>";
        try {
            MailSender.sendMail(username, "damc@gov.bt", null, mailContent, "New Password Generated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your newly generated password is send to you via SMS and email.");
        return responseMessage;
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
    public ResponseMessage replacedContractorHr(String cid) {
       commonDao.replacedContractorHr(cid);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Success");
        return null;
    }

    @Transactional
    public ResponseMessage replacedConsultantHr(String cid) {
        commonDao.replacedConsultantHr(cid);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Success");
        return null;
    }

    @Transactional
    public ResponseMessage replacedSFHr(String cid) {
        commonDao.replacedSFHr(cid);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Success");
        return null;
    }

    @Transactional(readOnly = true)
    public List seekClearance(String cdbNo){
        return commonDao.seekClearance(cdbNo);
    }

    @Transactional
    public List<TrackRecord> checkEqEngagedByWorkID(String workId, String cdbNo) {
        List<TrackRecord> dto=new ArrayList<TrackRecord>();
        dto= commonDao.checkEqEngagedByWorkID(workId,cdbNo);
        return dto;
    }

    @Transactional
    public List<TrackRecord> checkHrEngagedByWorkID(String workId, String cdbNo) {
        List<TrackRecord> dto=new ArrayList<TrackRecord>();
        dto= commonDao.checkHrEngagedByWorkID(workId,cdbNo);
        return dto;
    }

    @Transactional
    public List gAgencyList() {
        return commonDao.gAgencyList();
    }

    @Transactional
    public List gRolesList() {
        return commonDao.gRolesList();
    }

    @Transactional
    public List gRegionList() {
        return commonDao.gRegionList();
    }

    @Transactional
    public List gDesignationList() {
        return commonDao.gDesignationList();
    }

    @Transactional
    public List gSectorList() {
        return commonDao.gSectorList();
    }

    @Transactional
    public List gGewogUsersList() {
        return commonDao.gGewogUsersList();
    }
}
