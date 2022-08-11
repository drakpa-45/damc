/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function _baseURL() {
    return cdbGlobal.baseURL() + "/trackApp";
}

//endregion

var trackAPP = (function () {
    function populateTrackApp(){
        $.ajax({
            url: _baseURL() + '/public_access/populateTrackApp',
            type: 'GET',
            success: function (res) {
                var tr='',workID='';
                var remarks = '',name='',contact='',vapDate='';
                var entity = res.dto;
                debugger;
                for (var i in entity) {
                    if(entity[i].remarks != ''){
                        remarks = entity[i].remarks;
                        name = entity[i].fullName;
                        contact = entity[i].contactNo;
                        vapDate = new Date(entity[i].rejectedDate);
                    }else if(entity[i].vRemarks !=''){
                        remarks = entity[i].vRemarks;
                        name = entity[i].vFullName;
                        contact = entity[i].vContactNo;
                        vapDate = new Date(entity[i].vDate);
                    }else if(entity[i].appRemarks !=''){
                        remarks = entity[i].appRemarks;
                        name = entity[i].aFullName;
                        contact = entity[i].aContactNo;
                        vapDate = new Date(entity[i].approverDate);
                    }
                    tr = tr +
                    "<tr><td>"+(parseInt(i)+1)+"</td>" +
                    "<td>"+ entity[i].applicationNo + "</td>" +
                    "<td>"+ entity[i].serviceName + "</td>" +
                    "<td>"+ entity[i].appStatus + "</td>" +
                    "<td>"+remarks+ "</td>" +
                    "<td>"+ name + "</td>" +
                    "<td>"+ contact + "</td>"+
                    "<td>"+ vapDate +"</td>" +
                    "</tr>";
                }
                $('#trackApp').find('tbody').html(tr);
            }
        });
    }
    function init(){
        populateTrackApp();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        trackAPP.init();
    }
);