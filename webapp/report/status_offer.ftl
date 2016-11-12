<#--
<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<!--Resources For Bootstrap&ndash;&gt;
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

&lt;#&ndash;<div class="widget" style="width: 500px">&ndash;&gt;
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
                                        <button type="button" class="fa fa-check text-success checkDistribution"
                                                distId="${dist.id}"> Check
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
        /*alert($('#id').val());
        return false;*/
        $('#IncentiveDistVoucherModal').modal('show');

        //window.location = '/mirror/csd/updateIncentiveDist.erp?id='+id+'&status=1&voucherNo=';

    });
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
    }


</script>

&lt;#&ndash;</div>&ndash;&gt;
&lt;#&ndash;End widget div&ndash;&gt;


<form name="dataForm" id="dataForm" action="updateIncentiveDist.erp" method="GET">
    <div class="modal fade" id="IncentiveDistVoucherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content panel-success">
                <div class="modal-header panel-heading">

                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title" id="alert-success-title">Attention</h3>
                </div>
                <div class="modal-body" id="alert-success-body" style="font-size: 18px">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h3 class="text-left text-success">Please Insert Voucher No</h3>

                            <form name="dataForm" id="dataForm" action="saveIncentiveDist.erp" method="POST">
                                <div class="col-lg-12 text-center">

                                    <div class="col-lg-5 text-left">
                                        Distribution Vr. No.:
                                    </div>
                                    <div class="col-lg-7 text-center">
                                        <div class="form-group">
                                            <div class="form-group has-success">
                                            &lt;#&ndash;<input type="hidden" id="id" name="id" value=""/>
                                            <input type="hidden" id="status" name="status" value="3"/>&ndash;&gt;
                                                <input type="text" placeholder="Voucher No" name="voucherNo"
                                                       id="voucherNo" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-success" id="saveVoucher" data-dismiss="modal">Save</button>
                </div>
            </div>
            <!-- /.modal-content &ndash;&gt;
        </div>
        <!-- /.modal-dialog &ndash;&gt;
    </div><!-- /.modal &ndash;&gt;

    <script type="text/javascript">
        function makModalAlert(type, head, body) {
            $('#alertModal-' + type).modal('show');
            $('#alert-' + type + '-title').html(head);
            $('#alert-' + type + '-body').html(body);
        }

        function hideMakModalAlert(type) {
            $('#alertModal-' + type).modal('hide');
        }


    </script>

</@layout.gen_app_layout>



-->
