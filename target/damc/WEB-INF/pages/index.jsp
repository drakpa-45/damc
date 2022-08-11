<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<div class="card">
    <div class="card-header col-sm-12 col-md-12 col-lg-12">
        <div class="col-sm-12 col-md-5 col-lg-5"><h3 class="card-title font-weight-bold">DAMC Services </h3></div>
    </div>
    <div class="card-body">
            <div class="col-md-4 col-lg-4 col-sm-12">
            </div>
            <div class="col-lg-4 col-md-4 border border-primary pt-3" style="background-color:lightgrey;">
                <form class="border-2 card" action="<c:url value="/auth"/>" method="post">
                    <div class="card-body">
                        <div class="card-title bold text-primary"><u><b>Login to your account</b></u></div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-user-circle"></i></span>
                            </div>
                            <input type="text" class="form-control numeric" name="username" placeholder="Enter your email">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-lock"></i></span>
                            </div>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Enter your Password">
                            <span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                        </div>
                        <div>
                            <label>
                                <div class="form-group">
                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                        <span><input type="checkbox" id="submitcheckbox" onclick="" name="tnc" class="required"  style="width:15px;height:15px;"></span>
                                        <span class="bold text-info">Remember me</span>
                                    </div>
                                </div>
                            </label>
                            <label>
                                <div class="form-group">
                                    <div class="col-lg-12 col-lg-12 col-md-12 col-sm-12">
                                        <a href="#" data-toggle="modal" data-target="#changePwdModal" class="nav-link">
                                            <span class="bold text-danger"><u><i>Forgot Password?</i></u></span>
                                        </a>
                                    </div>
                                </div>
                            </label>
                        </div>
                        <div class="form-footer col-md-3">
                            <button type="submit" class="btn btn-indigo align-content-center">Sign in</button>
                           <%-- <button type="button" onclick="res()" class="btn btn-warning btn-block">Resubmit</button>--%>
                        </div>
                        <div class="form-group" style="color:red">
                            <c:if test="${not empty error}">
                                <label class="form-control error">${error}</label>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
<div aria-hidden="true" aria-labelledby="hrModalLabel" role="dialog" class="modal fade in"
     id="changePwdModal">
    <div class="modal-dialog modal-lg" id="ownerModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="ownerModalLabel" class="modal-title">Generate New Password</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group">
                        <label class="col-lg-4">User Name
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-8">
                            <input type="text" id="usename" class="form-control name" required="" placeholder="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" id="generatePassword" onclick="generatePassword()"  type="button">Generate</button>
                <button data-dismiss="modal" class="btn btn-danger" target="#changePwdModal" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<style>
    .field-icon {
        float: right;
        margin-left: -25px;
        margin-top: 9px;
        position: relative;
        z-index: 2;
    }
</style>
<script>$('[data-toggle="tooltip"]').tooltip();
    function checkAppStatus(applicationNo){
        window.open('/damc/trackApp?applicationNo=' + $('#appNo').val());
    }
</script>
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

    function generatePassword(){
        var $this = $('#usename').val();
        var usernameTrue = false;
        if ($this == "") {
            $('#usename').focus();
            warningMsg('Please enter username');
        } else{
            alert();
            $.ajax({
                url:'/admin/farmerPendingList/isUsenameExist4gotPwd?username='+$this,
                success: function (res) {
                    if(res == true){
                        warningMsg("This username is not registered with DAMC. Please enter your valid username");
                        $('#usename').val('').focus();
                    }else{
                        if (usernameTrue == false) {
                            $.ajax({
                                url: '/admin/farmerPendingList/generatePassword',
                                type: 'GET',
                                data: {username: $this},
                                success: function (res) {
                                    if (res.status == 1) {
                                        successMsg("Your newly generated password is send to you via email.");
                                        $('#usename').val('');
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    function confirmPassword(confirmPw){
        if(!confirmPw){
            return;
        }
        if(confirmPw != $('#npwd').val()){
            // $('#npwd').focus().val('');
            warningMsg("Confirmation email does not match.");
            $('#cpwd').focus().val('');
        }
    }

</script>
</body>
</html>
