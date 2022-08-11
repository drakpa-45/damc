<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<body class="">
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">
                        DAMC
                    </h1>
                </div>

                <div class="card" id="registrtaionFormCard">
                    <div class="card-body">
                        <div class="row">
                            <div class="tab-content border p-3 col-lg-12">
                                <form action="" method="post" id="contractorPaymentForm">
                                    <div class="">
                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-header bg-indigo-light">
                                                <h3 class="card-title text-white">Farmer Pending details</h3>
                                            </div>
                                            <div class="card-body">
                                                <div class="col-lg-12">
                                                    <table class="table table-bordered table-center table-responsive-lg"
                                                           id="farmerPendingDtls">
                                                        <thead>
                                                        <tr>
                                                            <th>CID</th>
                                                            <th>Name</th>
                                                            <th>Dzongkhag</th>
                                                            <th>Gewog</th>
                                                            <th>Role</th>
                                                            <th>Email</th>
                                                            <th colspan="4">Action</th>

                                                        </tr>
                                                        </thead>
                                                        <tbody class="pending-farmer">

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Box Close -->
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form id="modalForm">
        <%--HR add model--%>
        <div aria-hidden="true" aria-labelledby="hrModalLabel" role="dialog" class="modal fade in" id="addHRModal">
            <div class="modal-dialog modal-lg" id="hrModal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 id="hrModalLabel" class="modal-title">Add Human Resource</h4>
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
                    </div>
                    <div class="modal-body form-horizontal">
                        <div class="modal-div">
                            <div class="form-group">
                                <input type="hidden" id="hrId" name="contractorHRs[0].id">
                                <label class="col-lg-2">CID No.
                                    <span class="text-danger">*</span>:</label>
                                <div class="col-lg-4">
                                    <div class="input-icon">
                                        <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                        <input type="text" name="contractorHRs[0].name" id="hr1" class="form-control name" disabled placeholder="">
                                    </div>
                                </div>
                                <label class="col-md-2 col-lg-2">Name: <span class="text-danger">*</span>:</label>
                                <div class="col-lg-4">
                                    <div class="input-icon">
                                        <span class="input-icon-addon"><i class="fa fa-address-card-o"></i></span>
                                        <input type="text" name="contractorHRs[0].cidNo" class="form-control hr-cid" id="hr2" disabled placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2">Dzongkhag<span class="text-danger">*</span>:</label>
                                <div class="col-lg-4">
                                    <select name="dzongkhag_Serial_No" class="form-control bg-light border-secondary" id="hr3" required data-msg-required="Country is required.">
                                        <option value="">--Select Dzongkhag--</option>
                                        <c:forEach var="country_id" items="${dzongkhag}" varStatus="counter">
                                            <option value="${country_id.value}">${country_id.text}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="col-lg-2">Gewog<span class="text-danger">*</span>:</label>
                                <div class="col-lg-4">
                                    <div class="input-icon">
                                        <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                        <input type="text" name="contractorHRs[0].name" id="hr3" class="form-control name" required="" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2">Role<span class="text-danger">*</span>:</label>
                                <div class="col-lg-4">
                                    <input type="text" name="contractorHRs[0].name" id="hr4" class="form-control name" required="" placeholder="">
                                </div>
                                <label class="col-lg-2">Email
                                    <span class="text-danger">*</span>:</label>
                                <div class="col-lg-4">
                                    <input type="text" name="contractorHRs[0].name" id="hr5" class="form-control name" required="" placeholder="">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" onclick="getModalData('hrDtlsTable','hr',10)" type="button">OK</button>
                        <button data-dismiss="modal" class="btn btn-warning" <%--onclick="refreshModel('hrModal','addHRModal')"--%> type="button">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%--confirmation model--%>
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal in" id="confirmationModel">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <span><b>Confirmation!</b></span>
                    </div>
                    <div class="modal-body form-horizontal">
                        <div class="alert alert-info">
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                    <span id="messages"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="submitApplication()">Yes</button>
                        <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')">
                            <span class="fa fa-times"></span> No
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script src="<c:url value="/resources/js/damc/pending.js"/>"></script>
</div>
</body>
</html>