<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
    <#-- <li><a href="/mirror/csd/csdConfigurationHome.erp">CSD Home</a></li>
     -->
        <li><a href="#">Director List</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="row">
    <div class="col-lg-2 text-center">
        Select an Option :
    </div>
    <div class="col-lg-3 text-center">
        <div class="panel panel-default">
            <select name="queryText" id="query-select" class="form-control"><option value="-1">--Select Option--</option>
                <option value="Director">Director</option>
                <option value="Share Holder">Share Holder</option>
                <option value="All">All</option>
            </select>
        </div>
    </div>

    <script>
            $('#query-select').change(function() {
                var  queryText  = $('#query-select').val();
                window.location = '/mirror/admin/directorList.erp?type='+queryText;
            });
    </script>

    <div class="col-lg-3 text-center pull-right">
        <a onclick="jQuery('#print-div').print()" style="/*position: absolute; left: 88%;*/"><img
                alt="Print" src="../resources/images/csd/printer2.png"/></a>
    </div>
</div>
<div class="row" id="print-div">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-left text-success">${PageTitle}</h3>

                <div class="table-responsive" >
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Sales Man</th>
                            <th>Customer Name</th>
                            <th>Self Amount</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list directorList as director>
                            <tr>
                                <td>${director.mid}</td>
                                <td>${director.name}</td>
                                <td>${director.type}</td>
                                <td>${director.id}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>

<script>
    $('.deleteDistribution').click(function () {
        var id = $(this).attr('distId');
        var thisTr = $(this).parent().parent();
        thisTr.remove();
        window.location = '/mirror/csd/deleteIncentiveDist.erp?id=' + id;

    });
    var uId;
    $('.saveDistributionVoucher').click(function () {
        var id = $(this).attr('distId');
        uId = id;
        //*alert($('#id').val());
        return false;
        //*
        $('#IncentiveDistVoucherModal').modal('show');

        //window.location = '/mirror/csd/updateIncentiveDist.erp?id='+id+'&status=1&voucherNo=';

    });
    $('.viewDistribution').click(function () {
        var id = $(this).attr('distId');
        window.location = '/mirror/csd/viewIncentiveDist.erp?id=' + id;

    });
    /*
     $('#saveVoucher').click(function () {
         var voucherNo = $('#voucherNo').val();
         if(voucherNo == "") {
             makModalAlert("warning", "Attention", "Please put Voucher No")
             return false;
         }
         //$('#IncentiveDistVoucherModal').modal('show');
         window.location = '/mirror/csd/updateIncentiveDist.erp?id='+uId+'&status=3&voucherNo='+voucherNo;
     });


     $('.checkDistribution').click(function () {
         var id = $(this).attr('distId');
         window.location = '/mirror/csd/updateIncentiveDist.erp?id='+id+'&status=1&voucherNo=';
     });


     function loadDistData() {
         var cid = $('#salesMan');
         alert(cid.val());
         //alert($('#cid').val());
         //return false;
         $.ajax({
             url: '/mirror/csd/getIncentiveDistData.erp',
             data: {
                 cid: cid.val()
             },
             dataType: 'json',
             success: function (data) {
                 if (data.msg == "No") {
                     alert('Sorry');
                 } else {
                     alert("got")
                 }
             }
         })
     }


     $(function () {
         $("#applicationDate").datepicker({format: 'yyyy/mm/dd'});
     });

     function saveIncentiveDist() {
         //loadDistData();
         //return false;
         document.dataForm.submit();
     }*/


</script>

<#--</div>-->
<#--End widget div-->

<script type="text/javascript">
    function makModalAlert(type, head, body) {
        $('#alertModal-' + type).modal('show');
        $('#alert-' + type + '-title').html(head);
        $('#alert-' + type + '-body').html(body);
    }

    function hideMakModalAlert(type) {
        $('#alertModal-' + type).modal('hide');
    }

    $(function () {
        $("#distributionDate").datepicker({
            format: 'yyyy/mm/dd'
        });
    });

    $('#saveVoucher').click(function () {
        var voucherNo = $('#voucherNo').val();
        var distributionDate = $('#distributionDate').val();
        if (voucherNo == "" || distributionDate == "") {
            alert("Please Insert VR no and Date Please.");
            return false;
        }
        //document.updateVrForm.submit();
        window.location = '/mirror/csd/updateIncentiveDist.erp?id=' + uId + '&status=3&voucherNo=' + voucherNo + "&distributionDate=" + distributionDate;
    });

</script>

</@layout.gen_app_layout>



