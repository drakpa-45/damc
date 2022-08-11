package com.ngn.spring.project.home;

import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.damc.common.CommonService;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value ="", method = RequestMethod.GET)
    public String index(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @PreAuthorize("isAuthenticated()")
	@RequestMapping(value ={"/admin"}, method = RequestMethod.GET)
	public String index_admin(ModelMap model,HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setUserID(loginDTO.getUserId());
        loggedInUser.setUserName(loginDTO.getUsername());
        loggedInUser.setFullName(loginDTO.getFullName());
        loggedInUser.setProfileId(loginDTO.getProfileId());
        loggedInUser.setServerDate(new Date());
        loggedInUser.setRole(auth.getAuthorities().toString());
        String registrationtype="";

        if(loggedInUser.getUserName().equalsIgnoreCase("DAMC")) {
           // registrationtype= ApplicationStatus.VERIFIED.getCode();
            model.addAttribute("userRole","DAMC");
        }

       request.getSession().setAttribute("loggedInUser", loggedInUser);
       if(loggedInUser.getUserName().equalsIgnoreCase("DAMC")){
            return "index_admin";
        }else {
            return "redirect:/acknowledgement";
        }
    }

    @PreAuthorize("isAuthenticated()")
	@RequestMapping(value ={"/public_access"}, method = RequestMethod.GET)
	public String index_public(ModelMap model,HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String cdbdet = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        String getCdbNoForSp = commonService.getCdbNoForSp(loginDTO);
        String getCdbNoForSpFirm = commonService.getCdbNoForSpFirm(loginDTO);
        String getCdbNoForSurvey = commonService.getCdbNoForSurvey(loginDTO);
        String getCdbNoForArchitect = commonService.getCdbNoForArchitect(loginDTO);
        String getCdbNoForEngineer = commonService.getCdbNoForEngineer(loginDTO);
        String getConsultantCdbNo = commonService.getConsultantCdbNo(loginDTO);
        String getCdbNoForContractor = commonService.getCdbNoForContractor(loginDTO);

        model.addAttribute("newsAndNotifications", commonService.getdashboardDetails(cdbdet.split("999")[0]));
        // model.addAttribute("rejectedApplications", commonService.populaterejectedApplications(cdbdet,getCdbNoForSp,getCdbNoForSurvey));
        session.setAttribute("loginDetails", loginDTO);

        /** get count for hr deleted replacement for contractor, consultant and specialized firm*/
        request.setAttribute("contractor_Ncount", commonService.populateNotificationCount("crpcontractor"));
        request.setAttribute("consultant_Ncount", commonService.populateNotificationCount("crpconsultant"));
        request.setAttribute("specializedFirm_Ncount", commonService.populateNotificationCount("specializedFirm"));

        if (getCdbNoForContractor != null) {
            cdbdet = commonService.getCdbNo(loginDTO);
            String contractorId = (String) commonService.getValue("crpcontractorfinal", "Id", "CDBNo", cdbdet.split("9999")[1]);
          //  List auditMemo = cRenewalService.auditMemo(contractorId);
            List seekClearance = commonService.seekClearance(cdbdet.split("9999")[1]);
            model.addAttribute("seekClearance", seekClearance);
           /* if (auditMemo != null && !auditMemo.isEmpty()) {
                // responseMessage.setStatus(0);
                // responseMessage.setText(auditMemo);
                model.addAttribute("auditMemo", auditMemo);
                // return "index_public_contractor";
            }*/
            session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
            model.addAttribute("applicationHistory", commonService.populateapplicationHistoryContractor(cdbdet.split("9999")[1]));
            model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationContractor(cdbdet.split("9999")[1]));
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("CDBNo", cdbdet);
            return "index_public_contractor";
        }else{
            cdbdet = commonService.getCdbNo(loginDTO);
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("CDBNo", cdbdet);
            session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
            //model.addAttribute("registrationDetails", commonService.populateApplicantDetails(cdbdet.split("999")[1]));
            return "index_public";
        }
	}

    @RequestMapping(value = "/resubmissionIndex")
    public String redirectToPage( ModelMap model,HttpServletRequest request,TasklistDto dto) {
        return "resubmissionIndex";
    }

    @RequestMapping(value = "/acknowledgement")
    public String acknowledgement( ModelMap model,HttpServletRequest request,@ModelAttribute("acknowledgement_message")String acknowledgement_message) {
        model.addAttribute("ackMessage", acknowledgement_message);
        return "acknowledgement";
    }

    @ResponseBody
    @RequestMapping(value ="/public_access/isUsenameExist4gotPwd", method = RequestMethod.GET)
    public Boolean isUsenameExist4gotPwd(String username){
        return  commonService.isUsenameExist4gotPwd(username);
    }

    @ResponseBody
    @RequestMapping(value = "/public_access/generatePassword")
    public ResponseMessage generatePassword( ModelMap model,String username) {
        return commonService.generatePassword(username);
    }

    @RequestMapping(value ="/admin/replacedContractorHr", method = RequestMethod.GET)
    public Object replacedContractorHr(HttpServletRequest request,ModelMap model,String cid,RedirectAttributes redirectAttributes){
         commonService.replacedContractorHr(cid);
        model.addAttribute("acknowledgement_message", "Success");
        return "acknowledgement";
    }

    @RequestMapping(value ="/admin/replacedConsultantHr", method = RequestMethod.GET)
    public Object replacedConsultantHr(HttpServletRequest request,ModelMap model,String cid,RedirectAttributes redirectAttributes){
        commonService.replacedConsultantHr(cid);
        model.addAttribute("acknowledgement_message", "Success");
        return "acknowledgement";
    }

    @RequestMapping(value ="/admin/replacedSFHr", method = RequestMethod.GET)
    public Object replacedSFHr(HttpServletRequest request,ModelMap model,String cid,RedirectAttributes redirectAttributes){
        commonService.replacedSFHr(cid);
        model.addAttribute("acknowledgement_message", "Success");
        return "acknowledgement";
    }
}