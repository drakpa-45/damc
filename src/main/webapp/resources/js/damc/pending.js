/**
 * Created by user on 2/14/2020.
 */
var $check = null;
function checkBtn(checkBoxId) {
    $check.prop('checked',true);
      if (checkBoxId == "owner") {
     $('#nextGIBtn').prop('disabled', false);
          //$("#hrModal").modal('show').refresh();
     } else if(checkBoxId == 'equipment'){
     $('#btnValEqNext').prop('disabled', false);
     }else{
     $('#nextHRBtn').prop('disabled', false);
         // $("#hrModal").modal('show').refresh();
     }
}

function openModal(modalId) {
    $("#" + modalId).modal({backdrop: 'static', keyboard: false});
}

var farmerRegistrationAction = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/farmerPendingList";
    }

    function getPendingList() {
        $.ajax({
            url:'/admin/farmerPendingList/getPendingList/',
            type: 'GET',
            success: function (res) {
                var existingHrs = res.dto;
                addHR("pending-farmer",existingHrs);
            }
        })
    }

    function addHR(tBodyClass, farmers){
        var hrTr = "";
        for (var i in farmers) {
            var deleteHr = "";
            var edit = "";
            var reset = "";
            var save = "";
            var verifiedApproved = '';
            if( (tBodyClass == 'pending-farmer')){
                /* verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                 verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' name='contractorHRs[0].hrChecked' class='check' value='1'   required=''></td>";
                 */
                edit = edit + "<td><input type='hidden' class='contractorHRid' id='contractorHRid' name='contractorHRs[0].id' value='" + farmers[i].cidNo + "'/><a class='p-2 edit_row'><i class='fa fa-pencil text-primary'></i></a></td>";
                reset = reset + "<td><input type='hidden' class='contractorHRid' id='contractorHRid' name='contractorHRs[0].id' value='" + farmers[i].cidNo + "'/><a class='p-2 refresh_row'><i class='fa fa-refresh text-warning'></i></a></td>";
                save = save + "<td><input type='hidden' class='cidNumber' id='cidNo'  value='" + farmers[i].cidNo + "'/> " +
                "<input type='hidden' class='emailID' id='email'  value='" + farmers[i].email + "'/><a class='p-2 approve_row'><i class='fa fa-save text-success'></i></a>" +
                "</td>";
                deleteHr = deleteHr + "<td><input type='hidden' class='contractorHRid' id='contractorHRid' name='contractorHRs[0].id' value='" + farmers[i].cidNo + "'/>" +
                "<input type='hidden' class='emailID' id='email'  value='" + farmers[i].email + "'/><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>";
            }

            hrTr = hrTr + "<tr class=''>" +
            "<td class='cidNo'>" + farmers[i].cidNo + "</td>" +
            "<td class='name'>" + farmers[i].name + "</td>" +
            "<td class='dzongkhag'>" + farmers[i].dzongkhagName + "</td>" +
            "<td class='gewog'>" + farmers[i].gewogName + "</td>" +
            "<td class='role'>" + farmers[i].role + "</td>" +
            "<td class='email'>" + farmers[i].email + "</td>" +
            edit  + reset +  save + deleteHr +
            "</tr>";
        }
        $('#farmerPendingDtls').find('.'+tBodyClass).append(hrTr);
    }

    function approveFarmerRow(){
        $('body').on('click','.approve_row',function(e){
            var cidNo = $(this).closest('tr').find('.cidNumber').val();
            var email = $(this).closest('tr').find('.emailID').val();
            $.ajax({
                url: _baseURL() + '/approve',
                type: 'POST',
                data: {cidNo:cidNo,email:email},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                    $(this).closest('tr').remove();
                }
            });
        });
    }

    function deleteThisFM(){
        $('body').on('click','.del_row',function(e){
            var cidNo = $(this).closest('tr').find('.cidNumber').val();
            var email = $(this).closest('tr').find('.emailID').val();
            $.ajax({
                url:  _baseURL() +'/deleteThisFM',
                type: 'GET',
                data: {cidNo:cidNo,email:email}
            });
            $(this).closest('tr').remove();
        });
    }

    function editInModal(){
        $('#farmerPendingDtls').on('click','.edit_row',function(e){
            alert();
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addHRModal');
            hrModal.find('#hrId').val(row.find('.contractorHRid').val())//for Edit
            hrModal.find('#hr1').val(hrModal.find('#hr1 option:contains("'+row.find('td:nth-child(1)').text()+'")').val());
            hrModal.find('#hr2').val(row.find('td:nth-child(2)').text()).prop('disabled',true);
            hrModal.find('#hr3').val(row.find('td:nth-child(3)').text()).prop('disabled',true);
            hrModal.find('#hr4').val(row.find('td:nth-child(4)').text()).prop('disabled',true);
            hrModal.find('#hr5').val(hrModal.find('#hr5 option:contains("'+row.find('td:nth-child(5)').text()+'")').val()).prop('disabled',true).prop('disabled',true);
            hrModal.find('#hr6').val(hrModal.find('#hr6 option:contains("'+row.find('td:nth-child(6)').text()+'")').val());
            hrModal.find('#hr7').val(hrModal.find('#hr7 option:contains("'+row.find('td:nth-child(7)').text()+'")').val());
            hrModal.find('#hr8').val(hrModal.find('#hr8 option:contains("'+row.find('td:nth-child(8)').text()+'")').val());
            hrModal.find('#hr9').val(hrModal.find('#hr9 option:contains("'+row.find('td:nth-child(9)').text()+'")').val());
            hrModal.find('#hr10').val(row.find('td:nth-child(10)').val());
            var hraTr = "";
            row.find('.hra').each(function(){
                var name = $(this).find('a').text();
                var hra = $(this).find('a').parent().html();
                hraTr = hraTr+"<tr><td><input type='hidden' class='hraId' value='"+$(this).find('.hraId').val()+"'>" +
                "<input type='text' required class='form-control docName' name='contractorHRs[0].contractorHRAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"'></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                "<td><button class='changeHr' type='button' onclick='_changeHr()'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            hrModal.find('#hrUploadTbl tbody').empty().html(hraTr);
            row.addClass('tbd');
            openModal('addHRModal');
        });

        $('body').on('click','.edit-hr',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addHRModal');
            hrModal.find('#hr5').val(row.find('.hr5').val());
            hrModal.find('#hr3').val(row.find('.hr3').val());
            hrModal.find('#hr1').val(row.find('.hr1').val());
            hrModal.find('#hr2').val(row.find('.hr2').val());
            hrModal.find('#hr4').val(row.find('.hr4').val());
            hrModal.find('#hr6').val(row.find('.hr6').val());
            hrModal.find('#hr7').val(row.find('.hr7').val());
            hrModal.find('#hr8').val(row.find('.hr8').val());
            hrModal.find('#hr9').val(row.find('.hr9').val());
            hrModal.find('#hr10').val(row.find('.hr10').val());
            row.addClass('tbd'); //add class to be deleted
            openModal('addHRModal');
        });
    }

    function isEmployeeIdUnique(){
        $('#employeeId').on('change',function(){
            var $this = $('#employeeId');
            $.ajax({
                url: _baseURL() + '/isEmployeeIdUnique',
                type: 'GET',
                data: {employeeId: $this.val()},
                success: function (res) {
                    if(res == true){
                        //$this.val()
                    }else{
                        $this.val('').focus();
                        warningMsg("This user ID has already been registered.");
                        $this.val('').focus();
                    }
                }
            });
        });
    }

    function saveData() {
        $('body').on('click', '#btn_submit', function (e) {
            alert();
            var agencyId = $('#agencyId').val();
            var employeeId = $('#employeeId').val();
            var employeeName = $('#employeeName').val();
            var designationId = $('#designationId').val();
            var email = $('#email').val();
            var roleIdStakeholder = $('#roleIdStakeholder').val();
            var roleIdDamc = $('#roleIdDamc').val();
            var roleIdRamco = $('#roleIdRamco').val();
            var regionId = $('#regionId').val();
            var roleIdRegion = $('#roleIdRegion').val();
            var dzongkhagId = $('#dzongkhagId').val();
            var sectorId = $('#sectorId').val();
            var roleIdDzongkhag = $('#roleIdDzongkhag').val();
            var gewogUsersId = $('#gewogUsersId').val();

            $.ajax({
                url: _baseURL() + '/save?agencyId=' + agencyId + '&employeeId=' + employeeId + '&employeeName=' + employeeName + '&roleIdRamco=' + roleIdRamco +
                '&designationId=' + designationId + '&email=' + email + '&roleIdStakeholder=' + roleIdStakeholder + '&roleIdDamc=' + roleIdDamc + '&regionId=' + regionId
                + '&roleIdRegion=' + roleIdRegion + '&dzongkhagId=' + dzongkhagId + '&sectorId=' + sectorId + '&roleIdDzongkhag=' + roleIdDzongkhag + '&gewogUsersId=' + gewogUsersId,
                type: 'POST',
                contentType: false,
                processData: false,
                success: function (res) {
                    if (res.status === 1) {
                        successMsg(res.text, '/admin/farmerPendingList/addAgencyUsers');
                    } else if (res.status == 5) {
                        warningMsg(res.text);
                    } else {
                        errorMsg(res.text)
                    }
                }
            });
        });
    }

    function nullif(val){
        if(val == null || val == 'null'){
            val = ''
        }
        return val;
    }

    function init(){
        getPendingList();
        editInModal();
        isEmployeeIdUnique();
        saveData();
        approveFarmerRow();
        deleteThisFM();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        farmerRegistrationAction.init();
    }
);