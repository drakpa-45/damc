<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="auth" property="principal"/>
<%String cdbNo=(String) session.getAttribute("App_Details"); %>

<html>
<head>
    <link href ="css/cdb.css" type ="text/css/html" rel ="stylesheet"/>
</head>
<body>
<div class="header py-4" style="background: #120f65;">
    <div class="container ">
        <div class="d-flex">
            <a class="header-brand" href="<c:url value="/index"/>">
                <img src="<c:url value="/resources/img/logo.png"/>" class="mt-2 header-brand-img" alt="tabler logo" style="height:60px;width:60px">
            </a>
            <h3 class="text-white"><br/>Online Registration and Information Management
                of Farmers Group and Co-operatives</h3>
            <div class="d-flex order-lg-2 ml-auto mt-5">
                <div class="dropdown">
                    <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                        <span class="avatar" ></span>
                    <span class="ml-2 d-none d-lg-block">
                      <span class="text-muted">${auth.fullName}</span>
                        <%--<small class="text-muted d-block mt-1">User</small>--%>
                      <small class="text-muted d-block mt-1">${loggedInUser.role}</small>
                    </span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item" href="<c:url value="/logout"/>">
                            <i class="dropdown-icon fe fe-log-out"></i> Sign out
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  d-lg-flex header p-0 shadow-sm" id="headerMenuCollapse">
    <div class="container">
        <div class="row pull-left">
            <div class="navbar-header">
                <button class="navbar-toggle" type="button" data-toggle="collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse nav-tabs-collapse d-flex " id="navbarSupportedContent">
                <strong>
                    <%if (cdbNo.startsWith("SpecializedTrade")){%>
                    <ul class="nav nav-tabs border-0">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link" style="color:black"><i class="fa fa-Dashboard"></i> Dashboard</a>
                        </li>
                       <%-- <li class="nav-item">
                            <a href="#" class="notification">
                                <i class="notification fa fa-bell-o mt-3" style="zoom: 1.4"></i><span class="badge">0</span>
                            </a>
                        </li>--%>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancelspTrade?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="#"  data-toggle="modal" data-target="#phonedModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="#"  data-toggle="modal" data-target="#changePwdModal" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}else if (cdbNo.startsWith("Architect")){%>
                    <ul class="nav nav-tabs border-0">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard"></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancel?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit "></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="#"  data-toggle="modal" data-target="#phonedModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="#"  data-toggle="modal" data-target="#changePwdModal" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}else if (cdbNo.startsWith("Survey")){%>
                    <ul class="nav nav-tabs border-0">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard "></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancel?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="#"  data-toggle="modal" data-target="#phonedModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="#"  data-toggle="modal" data-target="#changePwdModal" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%} else if (cdbNo.startsWith("Engineer")){%>
                    <ul class="nav nav-tabs border-0">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard "></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancel?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="#"  data-toggle="modal" data-target="#phonedModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="#"  data-toggle="modal" data-target="#changePwdModal" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}else if (cdbNo.startsWith("Consultant")){%>
                    <ul class="nav nav-tabs border-0">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard "></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile"/>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/consultantRC"/>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/consultantOS"/>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Other Services</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/consultantCC"/>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="#"  data-toggle="modal" data-target="#phonedModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                <a href="#"  data-toggle="modal" data-target="#tpnModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update trade/TPN Number</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="#"  data-toggle="modal" data-target="#changePwdModal" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}else if(cdbNo.startsWith("Contractor")){%>
                        <ul class="nav nav-tabs border-0" id='navList'>
                            <li class="nav-item">
                                <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard"></i>Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/public_access/profile"/>" class="nav-link" style="color:black;"><i class="fa fa-user"></i> Profile</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/public_access/contractorRC"/>" class="nav-link hcolor" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Certificate</a>
                            </li>
                            <li class="nav-item" >
                                <a href="<c:url value="/public_access/contractorOS"/>" class="nav-link hcolor" style="color:black"><i class="fa fa-edit"></i> Other Services</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/public_access/contractorCC"/>" class="nav-link hcolor" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                            </li>
                            <li class="nav-item" style="color:black">
                                <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                                <div class="dropdown-menu dropdown-menu-arrow">
                                    <a href="#"  data-toggle="modal" data-target="#phonedModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                    <a href="#"  data-toggle="modal" data-target="#tpnModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update trade/TPN Number</a>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a href="#"  data-toggle="modal" data-target="#changePwdModal" class="nav-link" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                            </li>
                            <li class="nav-item">
                                <a href="#"  data-toggle="modal" data-target="#seekClearificationModal" class="nav-link" style="color:black"><i class="fa fa-question-circle "></i> Seek Clearification</a>
                            </li>
                        </ul>
                    <%}else if (cdbNo.startsWith("SpecializedFirm")){%>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard"></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile"/>" class="nav-link" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/specializedFirmRC"/>" class="nav-link" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Certificate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/specializedFirmOS"/>" class="nav-link" style="color:black"><i class="fa fa-edit"></i> Other Services</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/specializedFirmCC"/>" class="nav-link" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="#"  data-toggle="modal" data-target="#phonedModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                <a href="#"  data-toggle="modal" data-target="#tpnModal" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update trade/TPN Number</a>
                            </div>
                            <div class="dropdown-menu dropdown-menu-arrow">
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="#" data-toggle="modal" data-target="#changePwdModal" class="nav-link" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}%>
                </strong>
            </div>
        </div>
    </div>
</div>
<div aria-hidden="true" aria-labelledby="hrModalLabel" role="dialog" class="modal fade in"
     id="changePwdModal">
    <div class="modal-dialog modal-lg" id="pwdModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="ownerModalLabel" class="modal-title">Change/Update Password</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group">
                        <label class="col-lg-4">Old Password
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-8">
                            <input type="password" id="usename" onchange="existUsename(this.value)" class="form-control name" required="" placeholder="Enter old password">
                            <span toggle="#usename" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4">New Password
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-8 passwordClass">
                            <input type="password" name="contractorHRs[0].name" id="npwd" class="form-control name" required="" placeholder="">
                            <span toggle="#npwd" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4">Confirm Password<span class="text-danger">*</span>:</label>
                        <div class="col-lg-8">
                            <input type="password" name="contractorHRs[0].name" onchange="confirmPassword(this.value)" id="cpwd" class="form-control name" required="" placeholder="">
                            <span toggle="#cpwd" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" onclick="updatePassword()" type="button">Update</button>
                <button data-dismiss="modal" class="btn btn-danger" target="#changePwdModal" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<div aria-hidden="true" aria-labelledby="seekClearificationModal" role="dialog" class="modal fade in"
     id="seekClearificationModal">
    <div class="modal-dialog modal-lg" id="scodal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="scModalLabel" class="modal-title">Enquiry Here</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group">
                        <label class="col-lg-2">Tender Id<span class="text-danger">*</span>:</label>
                        <div class="col-lg-10 mb-5">
                            <input type="text" id="tenderId" class="form-control number" required="" placeholder="">
                        </div>
                        <label class="col-md-2 mt-3" for="enquiry">Your enquiry:<span class="text-danger">*</span></label>
                        <div class="col-md-10">
                            <textarea name="enquiry" id="enquiry" class="form-control"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" id="btnEnquiry" onclick="seekClearification()" type="button">Ask</button>
                <button data-dismiss="modal" class="btn btn-danger" target="#seekClearificationModal" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<div aria-hidden="true" aria-labelledby="hrModalLabel" role="dialog" class="modal fade in"
     id="phonedModal">
    <div class="modal-dialog modal-lg" id="pModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="pModalLabel" class="modal-title">Update Phone Number</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group">
                        <label class="col-lg-4">Phone Number
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-8">
                            <input type="number" id="phoneNumber" class="form-control number" maxlength="8" required="" placeholder="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" onclick="updatePhoneNumber()" type="button">Update</button>
                <button data-dismiss="modal" class="btn btn-danger" target="#phonedModal" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="username" class="form-control number" value="${auth.username}" required="" placeholder="">
<div aria-hidden="true" aria-labelledby="hrModalLabel" role="dialog" class="modal fade in"
     id="tpnModal">
    <div class="modal-dialog modal-lg" id="tModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="tModalLabel" class="modal-title">Update trade/TPN Number</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group">
                        <label class="col-lg-4">Trade Number
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-8">
                            <input type="text" id="tNumber" class="form-control" required="" placeholder="">
                        </div>
                        <br />
                        <label class="col-lg-4">TPN Number
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-8">
                            <input type="text" id="tpnNumber" class="form-control" required="" placeholder="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" onclick="updateTTNumber()" type="button">Update</button>
                <button data-dismiss="modal" class="btn btn-danger" target="#tpnModal" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<style>
    /* unvisited link */
    .hcolor:link {
        color: black;
    }
    /* selected link */
    .hcolor:active {
        color: blue;
    }
    a.active {
        background-color: cornflowerblue;
    }
    .field-icon {
        float: right;
        margin-left: -15px;
        margin-top: -25px;
        position: relative;
        z-index: 2;
    }

</style>
<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>

<script type="text/javascript" >

    $(".toggle-password").click(function() {
        $(this).toggleClass("fa-eye fa-eye-slash");
        var input = $($(this).attr("toggle"));
        if (input.attr("type") == "password") {
            input.attr("type", "text");
        } else {
            input.attr("type", "password");
        }
    });
    function myFunction(e) {
        if (document.querySelector('#navList a.active') !== null) {
            document.querySelector('#navList a.active').classList.remove('active');
        }
        e.target.className = "active";
    }

    function existUsename(pwd){
        var $this = $('#username').val();
            $.ajax({
                url:'/damc/public_access/isUsenameExist?username='+$this + '&pwd='+pwd,
                success: function (res) {
                    if(res == false){
                        //warningMsg("This username is registered in CDB. Please enter your valid username");
                    } else{
                        $('#usename').val('').focus();
                        warningMsg("Please enter your valid old password.");
                    }
                }
            });
    }

    function updatePassword(){
        var $password = $('#cpwd').val();
        var $this = $('#username').val();
        if($('#usename').val() != ''){
        $.ajax({
            url:'/damc/public_access/updatePassword',
            type: 'GET',
            data: {username: $this, newPwd: $password},
            success: function (res) {
                if(res.status == 1){
                    successMsg('Your password is successfully updated.')
                }
            }
        });
             $('#usename').val('');
             $('#npwd').val('');
             $('#cpwd').val('');
        }else{
            warningMsg('Please enter your old password');
            $('#usename').focus();
        }
    }

    function updatePhoneNumber(){
        var $this = $('#phoneNumber').val();
        var $username = $('#username').val();
        $.ajax({
            url:'/damc/public_access/updatePhoneNumber',
            type: 'GET',
            data: {phoneNumber: $this,username:$username},
            success: function (res) {
                if(res.status == 1){
                    successMsg(res.text)
                }
            }
        });
    }

    function seekClearification(){
        var $this = $('#enquiry').val();
        var $username = $('#username').val();
        var $tenderId = $('#tenderId').val();
        if($this !='' && $tenderId !=''){
            $.ajax({
                url:'/damc/public_access/seekClearification',
                type: 'GET',
                data: {enquiry: $this,username:$username,tenderId:$tenderId},
                success: function (res) {
                    if(res.status == 1){
                        successMsg(res.text)
                    }
                }
            });
        }else{
            warningMsg('Both the fields are required');
            $('#enquiry').focus();
            $('#tenderId').focus();
        }
    }


    function updateTTNumber(){
        var $trade = $('#tNumber').val();
        var $tpn = $('#tpnNumber').val();
        var $username = $('#username').val();
        $.ajax({
            url:'/damc/public_access/updateTTNumber',
            type: 'GET',
            data: {tradeNumber: $trade,tpn: $tpn,username:$username},
            success: function (res) {
                if(res.status == 1){
                    successMsg(res.text)
                }
            }
        });
    }

    function confirmPassword(confirmPw){
            if(!confirmPw){
                return;
            }
            if(confirmPw != $('#npwd').val()){
               // $('#npwd').focus().val('');
                warningMsg("Confirmation password does not match.");
                $('#cpwd').focus().val('');
            }
    }
</script>
</body>
</html>
