package com.ngn.spring.project.damc.admin.damcAdmin;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.damc.common.CommonService;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description: Contractor New Registration Action controller. Verify, Approve and Payment Approval
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/admin/farmerPendingList")
public class UserManagementController extends BaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    CommonService commonService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "admin/farmerPending";
    }

    @ResponseBody
    @RequestMapping(value = "/getPendingList", method = RequestMethod.GET)
    public ResponseMessage getContractorInfo(HttpServletRequest request, String appNo,Character flag) {
        return adminService.getPendingList();
    }

    /**
     * Index method for addAgencyUsers
     *
     * @param request  --> HttpServletRequest
     * @param response --> HttpServletResponse
     * @param model    --> Model
     * @return :: String PAGE
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/addAgencyUsers",method = RequestMethod.GET)
    public String addAdminUsers(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        model.addAttribute("dzongkhag", commonService.gDzongkhagList());
        model.addAttribute("agencies", commonService.gAgencyList());
        model.addAttribute("roles", commonService.gRolesList());
        model.addAttribute("region", commonService.gRegionList());
        model.addAttribute("designation", commonService.gDesignationList());
        model.addAttribute("sector", commonService.gSectorList());
        model.addAttribute("gewogUsers", commonService.gGewogUsersList());
        return "admin/addAgencyUsers";
    }

    @ResponseBody
    @RequestMapping(value ="/isEmployeeIdUnique", method = RequestMethod.GET)
    public Boolean isEmployeeIdUnique(String employeeId){
        return adminService.isEmployeeIdUnique(employeeId);
    }

    @ResponseBody
    @RequestMapping(value ="/isUsenameExist4gotPwd", method = RequestMethod.GET)
    public Boolean isUsenameExist4gotPwd(String username){
        return  commonService.isUsenameExist4gotPwd(username);
    }

    @ResponseBody
    @RequestMapping(value = "/generatePassword")
    public ResponseMessage generatePassword( ModelMap model,String username) {
        return commonService.generatePassword(username);
    }

    @ResponseBody
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ResponseMessage approve(HttpServletRequest request,String cidNo,String email) throws Exception{
        loggedInUser = gLoggedInUser(request);
        return adminService.approve(cidNo,email,loggedInUser);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(HttpServletRequest request) throws Exception{
        loggedInUser = gLoggedInUser(request);

        String agencyId=request.getParameter("agencyId");
        String employeeId=request.getParameter("employeeId");
        String employeeName=request.getParameter("employeeName");
        String designationId=request.getParameter("designationId");
        String email=request.getParameter("email");
        String roleIdStakeholder=request.getParameter("roleIdStakeholder");
        String roleIdDamc=request.getParameter("roleIdDamc");
        String roleIdRamco=request.getParameter("roleIdRamco");
        String regionId=request.getParameter("regionId");
        String roleIdRegion=request.getParameter("roleIdRegion");
        String dzongkhagId=request.getParameter("dzongkhagId");
        String sectorId=request.getParameter("sectorId");
        String roleIdDzongkhag=request.getParameter("roleIdDzongkhag");
        String gewogUsersId=request.getParameter("gewogUsersId");

        return adminService.save(agencyId, employeeId, employeeName, designationId, email, roleIdStakeholder, roleIdDamc, regionId, roleIdRegion, dzongkhagId, sectorId, roleIdDzongkhag, gewogUsersId,roleIdRamco, loggedInUser);
    }

    @RequestMapping(value = "/deleteThisFM", method = RequestMethod.GET)
    public void deleteThisHR(HttpServletRequest request, HttpServletResponse response,String cidNo, String email) throws Exception{
        adminService.deleteThisFM(cidNo,email);
    }
}
