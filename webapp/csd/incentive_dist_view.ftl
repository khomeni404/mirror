<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="/mirror/csd/csdConfigurationHome.erp">CSD Home</a></li>
        <li><a href="/mirror/csd/createIncentiveDist.erp">Create Incentive</a></li>
        <li><a href="#">View</a></li>
    </ul>
</div>
<br class="clear"/>

<#--<div class="widget" style="width: 500px">-->
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <#if customer??>
                    <div class="row">
                        <div class="col-lg-12 text-center"
                             style="border-bottom: double; font-weight: bolder; font-size: larger; background-color: #a4eea7">
                            <br/> Incentive Distribution of ${customer.CID} <br/>
                            Customer Name : ${customer.name}    <br/> &nbsp;
                        </div>
                    </div>
                    <hr/>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                            <tr>
                                <th>Distribution For</th>
                                <th>Application Date</th>
                                <th>Self Amount</th>
                                <th>Staff Amount</th>
                                <th>Checked By</th>
                                <th>Distributed On</th>
                                <th>Vr. / Action</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#assign totalS = 0>
                                <#assign totalP = 0>
                                <#list distributionList as dist>
                                <tr <#if dist.voucherNo??>style="background-color: #bbffd8"</#if>>
                                    <td>${dist.salesMan.name}</td>
                                    <td>${dist.applicationDate}</td>

                                    <#if dist.voucherNo??>
                                        <td class="text-right" >
                                            <b>${dist.salesManAmount}</b>
                                        </td>
                                        <td class="text-right">
                                            <b>${dist.personnelAmount}</b>
                                        </td>
                                    <#else >
                                        <td class="text-right">
                                        ${dist.salesManAmount}
                                        </td>
                                        <td class="text-right">
                                        ${dist.personnelAmount}
                                        </td>
                                    </#if>


                                    <#if dist.checkedBy??>
                                        <td>${dist.checkedBy.token.name}</td>
                                    <#else >
                                        <td class="text-danger">Not checked</td>
                                    </#if>


                                    <#if dist.voucherNo??>
                                        <td class="text-success"><b>${dist.distributionDate}</b></td>
                                        <#assign totalS = totalS + dist.salesManAmount>
                                        <#assign totalP = totalP + dist.personnelAmount>
                                    <#else >
                                        <td class="text-danger">Not Distributed</td>
                                    </#if>
                                    <#if dist.checkedBy??>
                                        <#if dist.voucherNo??>
                                            <td class="text-success"><b>${dist.voucherNo}</b></td>
                                        <#else >
                                            <td>
                                                <button type="button"
                                                        class="fa fa-money text-success createDistributionVoucher"
                                                        distId="${dist.id}"> Voucher
                                                </button>
                                            </td>
                                        </#if>
                                    <#else >
                                        <td>
                                            <button type="button" class="fa fa-check text-success checkDistribution"
                                                    distId="${dist.id}"> Check
                                            </button>
                                            <button type="button" class="fa fa-times button-danger deleteDistribution"
                                                    distId="${dist.id}">
                                            </button>
                                        </td>
                                    </#if>

                                </tr>
                                </#list>
                            <tr style="border-top: double">
                                <td colspan="2" class="text-right "><b>Total Distributed Amount = </b></td>
                                <td class="text-right"><b>${totalS}/-</b></td>
                                <td class="text-right"><b>${totalP}/-</b></td>
                                <td class="text-center" colspan="3">G R A N D T O T A L = <b>${totalS+totalP}/-</b></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </#if>

            </div>
        </div>
    </div>
</div>

<script>
    var uId;
    $('.createDistributionVoucher').click(function () {
        uId = $(this).attr('distId');
        $('#IncentiveDistVoucherModal').modal('show');
    });

    function saveVoucher() {
        var vrF = $("#voucherNo");
        var vr = vrF.val();
        if (vr == "") {
            $('#errMsgVr').html("Please Insert Voucher No. Here");
            return false;
        }

        var dateF = $("#distributionDate");
        var date = dateF.val();
        if (date == "") {
            $('#errMsgDt').html("Please Insert Date Here");
            return false;
        }
        window.location = '/mirror/csd/distributeIncentiveDist.erp?id=' + uId + '&status=3&voucherNo=' + vr + '&distributionDate=' + date;

    }

    $(function () {
        $("#distributionDate").datepicker({
            format: 'yyyy/mm/dd',
            autoclose:true
        });
    });

    $('.checkDistribution').click(function () {
        var id = $(this).attr('distId');
        window.location = '/mirror/csd/checkIncentiveDist.erp?id=' + id + '&status=1&voucherNo=&distributionDate=2015/10/10';
    });

    $('.deleteDistribution').click(function () {
        var id = $(this).attr('distId');
        window.location = '/mirror/csd/deleteIncentiveDist.erp?id=' + id;
    });

    function clearErrMsg() {
        $('#errMsg').html("");
    }


</script>

<style>
    th {
        background-color: #d2ccdd;
        font-weight: bolder;
    }
</style>


<form name="updateVrForm" id="updateVrForm" action="updateIncentiveDist.erp" method="GET">
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

                            <div class="col-lg-12 text-center">

                                <div class="col-lg-5 text-left">
                                    Distribution Vr. No.:
                                </div>
                                <div class="col-lg-7 text-center">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                        <#--<input type="hidden" id="id" name="id" value=""/>
                                        <input type="hidden" id="status" name="status" value="3"/>-->
                                            <input type="text" placeholder="Voucher No" name="voucherNo"
                                                   id="voucherNo" class="form-control" onclick="clearErrMsg()">
                                            <span for="errMsgVr" id="errMsgVr" class="errMsg text-danger"></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-5 text-left">
                                    Distribution Date:
                                </div>
                                <div class="col-lg-7 text-center">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            <input type="text" placeholder="YYYY/MM/DD" readonly
                                                   name="distributionDate" id="distributionDate" class="form-control"
                                                   onclick="clearErrMsg()">

                                            <span for="errMsgDt" id="errMsgDt" class="errMsg text-danger"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel
                        <button type="button" class="btn btn-success"
                                onclick="saveVoucher();" <#-- data-dismiss="modal"-->>Save Vr
                        </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</form>

</@layout.gen_app_layout>