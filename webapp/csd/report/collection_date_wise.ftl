<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<!--Resources For Bootstrap-->
    <#include "../../gen_template/resources_of_bootstrap.ftl">


<div class="row">
    <div class="col-lg-6 pull-left">
        <h1>Collection Tracker</h1> <b class="text-success">${reportOf}</b>
        <br class="clear"/>
    </div>
    <div class="col-lg-6 pull-right">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-3 text-left">
                        Date From:
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <input type="text" placeholder="YYYY-MM-DD" readonly name="applicationDate"
                                   id="ct-date-from" class="form-control" onclick="clearErrMsg()">
                                    <span for="errMsgDateFrom" id="errMsgDateFrom"
                                          class="errMsg text-left text-danger"></span>
                        </div>
                    </div>
                    <div class="col-lg-4 text-left">
                        <button type="button" id="ct-viewCollectionToday"
                                class="btn btn-block btn-info">
                            Today Report
                        </button>
                    </div>

                    <div class="col-lg-1">
                        <a href="#"><i class="fa fa-print fa-2x text-danger"
                                       onclick="jQuery('#print-div').print()"></i></a>
                    </div>


                </div>
                <div class="row">
                    <div class="col-lg-3 text-left">
                        Date To:
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <input type="text" placeholder="YYYY-MM-DD" readonly name="applicationDate"
                                   id="ct-date-to" class="form-control" onclick="clearErrMsg()">
                                    <span for="errMsgDateTo" id="errMsgDateTo"
                                          class="errMsg text-left text-danger"></span>
                        </div>
                    </div>
                    <div class="col-lg-4 text-left">
                        <button type="button" id="ct-viewCollection"
                                class="btn  btn-block btn-success">
                            View Report
                        </button>
                    </div>
                    <div class="col-lg-1">
                        <a href="#"><i class="fa fa-file-pdf-o fa-2x text-info"
                                       onclick="jQuery('#print-div').print()"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row" id="print-div">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-heading"><label style="font-weight: bolder; font-size: x-large; font-family: 'Arial Black'">${reportOf}</label></div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr style="font-weight: bold; background-color: #c0ad99">
                            <th>SL</th>
                            <th class="text-center">CID</th>
                            <th>Name</th>
                           <#-- <th>Opening</th>-->
                            <th>Deposit</th>
                            <th>Adjustment</th>
                            <th>Withdraw</th>
                            <th>Closing</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#assign sl = 0>
                            <#assign totalDepo = 0>
                            <#assign totalAdjustment = 0>
                            <#assign totalWithdraw = 0>
                            <#if salesList??>
                                <#list salesList as list>

                                    <#if list[3]??>
                                        <#assign deposit = list[3]>
                                        <#assign totalDepo = totalDepo+deposit>
                                    <#else >
                                        <#assign deposit = 0>
                                    </#if>
                                    <#if list[4]??>
                                        <#assign adjustment = list[4]>
                                        <#assign totalAdjustment = totalAdjustment+ adjustment>
                                    <#else >
                                        <#assign adjustment = 0>
                                    </#if>
                                    <#if list[5]??>
                                        <#assign withdraw = list[5]>
                                        <#assign totalWithdraw = totalWithdraw +withdraw>
                                    <#else >
                                        <#assign withdraw = 0>
                                    </#if>
                                    <#if deposit == 0 && adjustment == 0 && withdraw == 0>
                                    <#else >
                                        <#assign sl = sl + 1>
                                    <tr>
                                        <td class="text-center col-md-push-1">${sl}</td>
                                        <td class="text-center col-lg-2"><b>${list[0]}</b></td>
                                        <td class="text-left col-md-push-1">${list[1]}</td>
                                        <#--<td class="text-right col-md-push-1">---</td>-->
                                        <td class="text-right col-md-push-1">${deposit}</td>
                                        <td class="text-right col-md-push-1">
                                            <#if adjustment < 0 >
                                                (${-adjustment})
                                            <#else >
                                            ${adjustment}
                                            </#if>
                                        </td>
                                        <td class="text-right col-md-push-1">${-withdraw}</td>
                                        <td class="text-right col-md-push-1">
                                            <#if deposit+adjustment+withdraw < 0 >
                                                (${-(deposit+adjustment+withdraw)})
                                            <#else >
                                            ${deposit+adjustment+withdraw}
                                            </#if>
                                        </td>
                                    </tr>
                                    </#if>
                                </#list>
                            <tr style="font-weight: bolder; background-color: #778b72">
                                <td class="text-right col-lg-2" colspan="2">T O T A L =</td>
                                <td class="text-right col-md-push-1">---</td>
                                <td class="text-right col-md-push-1">${totalDepo}</td>
                                <td class="text-right col-md-push-1">${totalAdjustment}</td>
                                <td class="text-right col-md-push-1">${-totalWithdraw}</td>
                                <td class="text-right col-md-push-1">${totalDepo+totalAdjustment+totalWithdraw}</td>
                            </tr>
                            </#if>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>


<script>
    $(function () {
        $("#ct-date-from").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
        $("#ct-date-to").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    });

    $('#ct-viewCollection').click(function () {
        var from = $('#ct-date-from').val();
        var to = $('#ct-date-to').val();
        if (from + to == "")
            return false;
        window.location = '/mirror/csd/salesReport5.erp?from=' + from + '&to=' + to;
    });
    $('#ct-viewCollectionToday').click(function () {
        window.location = '/mirror/csd/salesReport5.erp?from=today&to=today';
    });

</script>

</@layout.gen_app_layout>



