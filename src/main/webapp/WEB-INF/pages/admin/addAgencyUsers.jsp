<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<body class="card">
<div class="card-body" id="ttargetId">
    <form class="form-horizontal" id="applicationForm"  method="post">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-12">
                <div class="col-sm-6">
                    <h1>User Registration</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <!-- left column -->
                <div class="col-md-12">
                    <!-- general form elements -->
                    <div class="card card-info">
                        <div class="card-header" style="background-color:#faebcc">
                            <h3 class="card-title, text-black-50">User Profile</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                            <div class="card-body">
                                <div class="form-group row">
                                    <label  class="col-sm-2 col-form-label required"> <b style="color: #000000">Employee ID:</b></label>
                                    <div class="col-sm-4">
                                        <input type="text" id="employeeId" class="form-control number" name="office_Contact_No" placeholder="employee ID ..." aria-describedby="sizing-addon1">
                                        <span id="ocontactError" class="text-danger"></span>
                                    </div>
                                    <label class="col-sm-2 col-form-label required"><b style="color: #000000">Name:</b></label>
                                    <div class="col-sm-4">
                                        <input type="text" id="employeeName" class="form-control" name="office_Contact_No" placeholder="text.." aria-describedby="sizing-addon1">
                                        <span id="TypeError" class="text-danger"></span>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-sm-2 col-form-label required"><b style="color: #000000">Designation:</b></label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="designationId">
                                            <option value="">--Select designation--</option>
                                            <c:forEach var="designation" items="${designation}" varStatus="counter">
                                                <option value="${designation.value}">${designation.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Email:</b></label>
                                    <div class="col-sm-4">
                                        <input type="email" id ="email" name="email" class="form-control" placeholder="email ..">
                                        <span id="emailError" class="text-danger"></span>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-sm-2 col-form-label required"><b style="color: #000000">Phone Number:</b></label>
                                    <div class="col-sm-4">
                                        <input type="text" id="" class="form-control" name="office_Contact_No" placeholder="Contact Number ..." aria-describedby="sizing-addon1">
                                        <span id="FarmerTypeError" class="text-danger"></span>
                                    </div>
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Agency:</b></label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="agencyId" onchange="chooseAgency(this.value)" name="agencyId">
                                            <option value="">--Select Agency--</option>
                                            <c:forEach var="agency_id" items="${agencies}" varStatus="counter">
                                                <option value="${agency_id.value}">${agency_id.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row stakeHolder hidden">
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Role:</b></label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="roleIdStakeholder" name="roleIdStakeholder">
                                            <option value="16">STAKE HODLER</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row damcOrCdd hidden">
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Role:</b></label>
                                    <div class="col-sm-4">
                                        <select multiple="" class="form-control" id="roleIdDamc">
                                            <option value="">--Select Role--</option>
                                            <c:forEach var="roles" items="${roles}" varStatus="counter">
                                                <option value="${roles.value}">${roles.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row ramco hidden">
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Region:</b></label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="regionId">
                                            <option value="">--Select Region--</option>
                                            <c:forEach var="region" items="${region}" varStatus="counter">
                                                <option value="${region.value}">${region.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Role:</b></label>
                                    <div class="col-sm-4">
                                        <select multiple="" class="form-control" id="roleIdRamco">
                                            <option value="">--Select Role--</option>
                                            <c:forEach var="roles" items="${roles}" varStatus="counter">
                                                <option value="${roles.value}">${roles.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row dzongkhag hidden">
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Dzongkhag:</b></label>
                                    <div class="col-sm-4">
                                        <select name="dzongkhag_Serial_No" class="form-control bg-light border-secondary" id="dzongkhagId" required data-msg-required="Country is required.">
                                            <option value="">--Select Dzongkhag--</option>
                                            <c:forEach var="country_id" items="${dzongkhag}"
                                                       varStatus="counter">
                                                <option value="${country_id.value}">${country_id.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Sector:</b></label>
                                    <div class="col-sm-4">
                                        <select  class="form-control" id="sectorId">
                                            <option value="">--Select sector--</option>
                                            <c:forEach var="sector" items="${sector}" varStatus="counter">
                                                <option value="${sector.value}">${sector.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Role:</b></label>
                                    <div class="col-sm-4">
                                        <select multiple="" class="form-control" id="roleIdDzongkhag">
                                            <option value="">--Select Role--</option>
                                            <c:forEach var="roles" items="${roles}" varStatus="counter">
                                                <option value="${roles.value}">${roles.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row gewog hidden">
                                    <label class="col-sm-2 col-form-label"><b style="color: #000000">Gewog:</b></label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="gewogUsersId">
                                            <option value="">--Select--</option>
                                            <c:forEach var="gewogUsers" items="${gewogUsers}" varStatus="counter">
                                                <option value="${gewogUsers.value}">${gewogUsers.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <button class="btn btn-success" id="btn_submit" type="button"><b class="fa fa-save lg"></b>&nbsp;<b style="font-weight: bold"> Add New User</b> </button> &nbsp;&nbsp;
                                <button class="btn btn-danger" id="btn_cancel" onclick="cancelData()" type="button"><b class="fa fa-close lg"></b>&nbsp;<b style="font-weight: bold">Cancel</b> </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>
</div>
<script src="<c:url value="/resources/js/damc/pending.js"/>"></script>
<script>
    function cancelData(){
        document.getElementById("applicationForm").reset();
    }
function chooseAgency(val){
    if(val==1){
        $('.damcOrCdd').removeClass('hidden');
        $('.ramco').addClass('hidden',true);
        $('.dzongkhag').addClass('hidden',true);
        $('.gewog').addClass('hidden',true);
        $('.stakeHolder').addClass('hidden',true);
    }else if(val == 2){
        $('.ramco').removeClass('hidden',true);
        $('.dzongkhag').addClass('hidden',true);
        $('.gewog').addClass('hidden',true);
        $('.stakeHolder').addClass('hidden',true);
        $('.damcOrCdd').addClass('hidden',true);
    }else if(val==3){
        $('.dzongkhag').removeClass('hidden',true);
        $('.ramco').addClass('hidden',true);
        $('.gewog').addClass('hidden',true);
        $('.stakeHolder').addClass('hidden',true);
        $('.damcOrCdd').addClass('hidden',true);
    }else if(val==4){
        $('.gewog').removeClass('hidden',true);
        $('.dzongkhag').addClass('hidden',true);
        $('.ramco').addClass('hidden',true);
        $('.stakeHolder').addClass('hidden',true);
        $('.damcOrCdd').addClass('hidden',true);
    }else if(val==5){
        $('.stakeHolder').removeClass('hidden',true);
        $('.gewog').addClass('hidden',true);
        $('.dzongkhag').addClass('hidden',true);
        $('.ramco').addClass('hidden',true);
        $('.damcOrCdd').addClass('hidden',true);
    }
}

</script>
<%-- <script src="<c:url value="/resources/js/damc/registration.js"/>"></script>--%>
</body>
</html>
