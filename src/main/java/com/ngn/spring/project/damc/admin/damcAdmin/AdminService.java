package com.ngn.spring.project.damc.admin.damcAdmin;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ====================================================================
 * Created by Pema Drakpa on 21/11/2021.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Service
public class AdminService extends BaseService {

    @Autowired
    private UserAdminDao userAdminDao;

    @Transactional(readOnly =  true)
    public ResponseMessage getPendingList(){
        List<userDTO> userList = userAdminDao.getPendingList();
        responseMessage.setDto(userList);
        return responseMessage;
    }

    @Transactional
    public ResponseMessage approve(String cidNo, String email, LoggedInUser loggedInUser) throws Exception{
        int len = 4;
        String ngpwd = generateRandomPassword(len);
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(ngpwd, salt);

        userAdminDao.approve(cidNo,email,loggedInUser,hashed_password);
        String mailContent = "Dear User,<br>" +
                "Your requisition has been approved on <b>"+new Date()+"</b> by Online Registration and Information Management of Farmers Group and Co-operatives."+
                "And your password is: " +ngpwd;
        MailSender.sendMail(email, "damc@gov.bt", null, mailContent, "Application approved");
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Farmer CID number :" + cidNo + " approved successfully.");
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
    public void deleteThisFM(String cidNo, String email) {
         userAdminDao.deleteThisFM(cidNo,email);
    }

    @Transactional
    public ResponseMessage save(String agencyId, String employeeId, String employeeName, String designationId, String email, String roleIdStakeholder, String roleIdDamc, String regionId, String roleIdRegion, String dzongkhagId, String sectorId, String roleIdDzongkhag, String gewogUsersId, String roleIdRamco, LoggedInUser loggedInUser) {
        String aRoles[] = new String[0];
        if(agencyId.equalsIgnoreCase("1")){
            aRoles=roleIdDamc.split(",");
        } else if(agencyId.equalsIgnoreCase("2")){
            aRoles=roleIdRamco.split(",");
        } else if(agencyId.equalsIgnoreCase("3")){
            aRoles=roleIdDzongkhag.split(",");
        } else if(agencyId.equalsIgnoreCase("4")){
            aRoles=gewogUsersId.split(",");
        } else if(agencyId.equalsIgnoreCase("5")){
            aRoles=roleIdStakeholder.split(",");
        }
        List<String> aRoleList = Arrays.asList(aRoles);
        aRoleList.stream().filter(s -> !s.isEmpty()).forEach(s -> {
            UserRoleMapping userRoleMap = new UserRoleMapping();
            userRoleMap.setUser_Id(employeeId);
            userRoleMap.setRole_Id(Integer.valueOf(s));
            userRoleMap.setAgency_Id(agencyId);
            userAdminDao.save(userRoleMap);
        });

        int len = 4;
        String ngpwd = generateRandomPassword(len);
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(ngpwd, salt);

        UserMaster userMaster = new UserMaster();
        userMaster.setLogin_Id(employeeId);
        userMaster.setFirst_Name(employeeName);
        userMaster.setEmail_Id(email);
        userMaster.setPassword(hashed_password);
        userMaster.setDesignationId(designationId);
        userMaster.setRegionId(regionId);
        userMaster.setSectorId(sectorId);
        userMaster.setIs_Approved("1");
        userMaster.setUser_Status("1");
        userAdminDao.save(userMaster);

        UserLocationMapping userLocationMapping = new UserLocationMapping();
        userLocationMapping.setUser_Id(employeeId);
        if(!dzongkhagId.equalsIgnoreCase("")){
            userLocationMapping.setDzongkhag_Id(dzongkhagId);
        }else{
            userLocationMapping.setDzongkhag_Id(null);
        }
        userAdminDao.save(userLocationMapping);
        String mailContent = "Dear User,<br>" +
                "User created successfully on <b>"+new Date()+"</b> by Online Registration and Information Management of Farmers Group and Co-operatives."+
                "Your userId is : " +employeeId +"and your password is: " +ngpwd;
        try {
            MailSender.sendMail(email, "damc@gov.bt", null, mailContent, "Application approved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Agency with userID:" + employeeId + " created successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public Boolean isEmployeeIdUnique(String employeeId) {
        return userAdminDao.isEmployeeIdUnique(employeeId);
    }
}
