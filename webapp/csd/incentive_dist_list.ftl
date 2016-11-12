<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="/mirror/csd/csdConfigurationHome.erp">CSD Home</a></li>
        <li><a href="/mirror/csd/createIncentiveDist.erp">Create Incentive</a></li>
        <li><a href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>

<#--<div class="widget" style="width: 500px">-->
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-left text-success">List  ${PageTitle}</h3>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Sales Man</th>
                            <th>Customer Name</th>
                            <th>Self Amount</th>
                            <th>Staff/Personnel Amount</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list distributionList as dist>
                            <tr>
                                <td>${dist.salesMan.name}</td>
                                <td>${dist.customer.name}</td>
                                <td>${dist.salesManAmount}</td>
                                <td>${dist.personnelAmount}</td>


                                <#if dist.status == 0>
                                    <td class="text-center">
                                        <button type="button" class="fa fa-times text-danger deleteDistribution"
                                                distId="${dist.id}"> Delete
                                        </button>
                                        <button type="button" class="fa fa-times text-success viewDistribution"
                                                distId="${dist.customer.id}"> View Details
                                        </button>

                                    </td>
                                </#if>

                                <#if dist.status == 1>
                                    <td class="text-center">
                                        <button type="button" class="fa fa-money text-success saveDistributionVoucher"
                                                distId="${dist.id}"> Voucher
                                        </button>
                                    </td>
                                </#if>

                                <#if dist.status == 2>
                                    <td class="text-center" style="background-color: #d4d4d4">


                                    </td>
                                </#if>

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



