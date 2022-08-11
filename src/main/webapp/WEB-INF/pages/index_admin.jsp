<%@ page import="com.ngn.spring.project.global.enu.UserRole" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/21/2020
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="page-header">
            <div class="col-sm-12 col-md-8 col-lg-8 pull-right">
                <h1 class="page-title font-weight-bold">DAMC</h1>
            </div>
        </div>
       <%-- <div class="card">
            <div class="card-header">
                <h3 class="card-title font-weight-bold">Dashboard</h3>
            </div>
            <div id="filterTasklistId">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">

                    </div>
                </div>
            </div>
        </div>--%>
    </div>
    <div id="content_main_div"></div>
</div>
<script>
    function _baseURL() {
        return cdbGlobal.baseURL() + "/damc";
    }
    function loadapplication(type, service) {
        var url = _baseURL() + '/admin/architect';
        // alert(url);
        $('#content_main_div').load();
    }
    function checkAppStatus(applicationNo) {
        window.open('/damc/trackApp?applicationNo=' + applicationNo);
    }
</script>
</body>
</html>
