<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >
    <#assign ctx = rc.getContextPath()/>
<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">
<#--<link rel="stylesheet" href="../resources/js/extjs/resources/css/ext-all.css" type="text/css">-->


<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <label style="font-weight: bolder; font-family: 'Arial Black'">Payment Statement</label>
                <br/> <label style="color: red" id="message-ps"></label>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-3 text-left">
                        Date From:
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <input type="text" placeholder="DD-MM-YYYY" name="applicationDate"
                                   id="date-from" class="form-control" value="${from!}">
                                    <span for="errMsgDateFrom" id="errMsgDateFrom"
                                          class="errMsg text-left text-danger"></span>
                        </div>
                    </div>
                    <div class="col-lg-4 text-left">
                        <input type="text" placeholder="CID" name="customer-id"
                               id="customer-id" class="form-control">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 text-left">
                        Date To:
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <input type="text" placeholder="DD-MM-YYYY" name="applicationDate"
                                   id="date-to" class="form-control" value="${to!}">
                                    <span for="errMsgDateTo" id="errMsgDateTo"
                                          class="errMsg text-left text-danger"></span>
                        </div>
                    </div>

                    <div class="col-lg-4 text-left">
                        <button type="button" id="viewCollection"
                                class="btn  btn-block btn-success">
                            View Report
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <label style="font-weight: bolder; font-family: 'Arial Black'">Collection : Mode Wise</label>
                <br/> <label style="color: red" id=""></label>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-3 text-left">
                        Date From:
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <input type="text" placeholder="DD-MM-YYYY"
                                   id="date-from-16" class="form-control" value="${from!}">
                                    <span for="errMsgDateFrom" id="errMsgDateFrom"
                                          class="errMsg text-left text-danger"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 text-left">
                        Date To:
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <input type="text" placeholder="DD-MM-YYYY"
                                   id="date-to-16" class="form-control" value="${to!}">
                                    <span for="errMsgDateTo" id="errMsgDateTo"
                                          class="errMsg text-left text-danger"></span>
                        </div>
                    </div>

                    <div class="col-lg-4 text-left">
                        <button type="button" id="id-16"
                                class="btn  btn-block btn-success">
                            View Report
                        </button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <label style="font-weight: bolder; font-family: 'Arial Black'">Customer List : Last Payment</label>
                <br/> <label style="color: red" id="message-ps"></label>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <select name="" id="vcpm-type" class="form-control">
                                <option value="BEFORE">Before</option>
                                <option value="AFTER">Within</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <select name="" id="vcpm-month" class="form-control">
                                <#list 1..24 as month>
                                    <option value="${month}">${month} Month</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="col-lg-4 text-left">
                        <button type="button" id="view-customer-payment-month"
                                class="btn  btn-block btn-success">
                            View Report
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

<#--Offer Wise-->
    <div class="col-lg-6">
        <form action="getCustomerOfferWise.erp" method="GET">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label style="font-weight: bolder; font-family: 'Arial Black'">Customer List : Offer
                        Wise</label>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-8 text-center">
                            <div class="form-group  text-left">
                                <select name="offerId" class="form-control">
                                    <#list offerList as offer>
                                        <option value="${offer.id!}">${offer.offerName!}
                                            | ${(offer.deadLine)!'---'}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="col-lg-4 text-left">
                            <button type="submit" id="view-customer-payment-month"
                                    class="btn  btn-block btn-success">
                                View Report
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="row">
<#--Handover Wise-->
    <div class="col-lg-6">
        <form action="getCustomerListCustomized.erp" method="POST">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label style="font-weight: bolder; font-family: 'Arial Black'">Customer List :
                        Customised</label>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-2 text-left">
                            <b>Handover:</b>
                        </div>
                        <div class="col-lg-4 text-center">
                            <select name="handoverYear" class="form-control">
                                <option value="">All Year</option>
                                <#list handoverYearList as year>
                                    <option value="${year}">${year}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="col-lg-2 text-left">
                            <b>Size</b>
                        </div>
                        <div class="col-lg-4 text-center">
                            <select name="floorSize" class="form-control">
                                <option value="">All Sizes</option>
                                <option value="1100">1100 SFT</option>
                                <option value="1300">1300 SFT</option>
                                <option value="1700">1700 SFT</option>
                                <option value="2100">2100 SFT</option>
                            </select>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-lg-6 text-left">
                            <b>Building Handover Year :</b>
                        </div>
                        <div class="col-lg-6 text-center">
                            <select name="buildingHandover" class="form-control">
                                <option value="">All Year</option>
                                <#list buildingHandoverYearList as year>
                                    <option value="${year}">${year}</option>
                                </#list>
                            </select>
                        </div>

                    </div>
                    <br/>

                    <div class="row">
                        <div class="col-lg-2 text-left">
                            <b>Mode:</b>
                        </div>
                        <div class="col-lg-4 text-center">
                            <select name="payMode" class="form-control">
                                <option value="">All Mode</option>
                                <option value="Installment">Installment</option>
                                <option value="One Time">One-Time</option>
                            </select>
                        </div>
                        <div class="col-lg-2 text-left">
                            <b>Offer</b>
                        </div>
                        <div class="col-lg-4 text-center">
                            <select name="offerId" class="form-control">
                                <option value="">All Offer</option>
                                <#list offerList as offer>
                                    <option value="${offer.id!}">${offer.offerName!}
                                        | ${(offer.deadLine)!'---'}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <br/>
                    <div class="row text-right">
                        <div class="col-lg-2 text-left">
                            <b>Status</b>
                        </div>
                        <div class="col-lg-4 text-center">
                            <select name="bookingStatus" class="form-control">
                                <option value="">All Status</option>
                                <option value="Approved">Approved</option>
                                <option value="Cancelled">Cancelled</option>
                                <option value="Processing">Processing</option>
                                <option value="Refunded">Refunded</option>
                            </select>
                        </div>
                        <div class="col-lg-2 text-left">
                            <b>Badge</b>
                        </div>
                        <div class="col-lg-4 text-center">
                            <select name="badgeId" class="form-control">
                                <option value="">All Badge</option>
                                <#list  badgeList as badge>
                                    <option value="${badge.id!}">${badge.name!}</option>
                                </#list>
                            </select>
                        </div>

                    </div>
                    <hr/>
                    <div class="row text-right" style="background-color: #f1f6ff; padding: 3px 3px 3px 3px ">
                        <div class="col-lg-2 text-left">

                        </div>
                        <div class="col-lg-2 text-center">

                        </div>


                        <input type="hidden" name="reportFileName" id="report-file-name"/>
                        <div class="col-lg-4">
                            <button type="submit" id="view-customer-payment-month"
                                    onclick="$('#report-file-name').val('report_27');"
                                    class="btn btn-block btn-info">Old Format
                            </button>
                        </div>
                        <div class="col-lg-4">
                            <button type="submit" id="view-customer-payment-month"
                                    onclick="$('#report-file-name').val('report_27_2');"
                                    class="btn  btn-block btn-success">New Format
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </form>
    </div>

    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <label style="font-weight: bolder; font-family: 'Arial Black'">Collection Tracker</label>
                <br/> <label style="color: red" id="message-ps"></label>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-3 text-left">
                        Date From:
                    </div>
                    <div class="col-lg-4 text-center">
                        <div class="form-group  text-left">
                            <input type="text" placeholder="YYYY-MM-DD" name="applicationDate"
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
                            <input type="text" placeholder="YYYY-MM-DD"  name="applicationDate"
                                   id="ct-date-to" class="form-control" value="">
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
<div class="row">
    <div class="col-lg-6">
        <form action="customerListByLocation.erp" method="GET">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label style="font-weight: bolder; font-family: 'Arial Black'">Customer List : Location
                        Wise</label>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-8 text-center">
                            <div class="form-group  text-left">
                                <input list="locationList" class="form-control"
                                       name="location" placeholder="Type Location"/>
                                <datalist id="locationList" autocomplete="off">
                                    <#list locationList as location>
                                    <option value="${location.id} : ${(location.toString())!}">
                                    </#list>
                                </datalist>
                            </div>
                        </div>

                        <div class="col-lg-4 text-left">
                            <button type="submit" id="view-customer-payment-month"
                                    class="btn  btn-block btn-success">
                                View Report
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label style="color: green; font-size: 15px;">Customer Report</label>
        </div>
        <div class="panel-body">
            <div class="col-lg-2">

                <a href="/mirror/csd/viewPaymentSummaryAll.erp?range=all" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Summary</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/clearReport.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Clear Summary</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/salesReport4.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Due Summary</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                    <img src="../resources/images/csd/report.png"><span>Statement</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/viewPaymentReport.erp?cid=1" original-title="Find Other Payment">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Report</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/viewOverdueReport.erp" original-title="Find Other Payment">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Dues List</span>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label style="color: green; font-size: 15px;">Customer List By Status</label>
        </div>
        <div class="panel-body">
            <div class="col-lg-3"><a href="/mirror/csd/customerListByStatus.erp?status=Approved"
                                     original-title="Payment Report">
                <img src="../resources/images/csd/report.png"><span>Approved Sales</span>
            </a></div>
            <div class="col-lg-3"><a href="/mirror/csd/customerListByStatus.erp?status=Cancelled"
                                     original-title="Payment Report">
                <img src="../resources/images/csd/report.png"><span>Cancelled Sales</span>
            </a></div>
            <div class="col-lg-3"><a href="/mirror/csd/customerListByStatus.erp?status=Processing"
                                     original-title="Payment Report">
                <img src="../resources/images/csd/report.png"><span>Processing Sales</span>
            </a></div>
            <div class="col-lg-3"><a href="/mirror/csd/customerListByStatus.erp?status=Refunded"
                                     original-title="Payment Report">
                <img src="../resources/images/csd/report.png"><span>Refunded Sales</span>
            </a></div>
        </div>
    </div>
</div>


<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label style="color: green; font-size: 15px;">Board Meeting Report</label>
        </div>
        <div class="panel-body">
            <div class="col-lg-2">
                <a href="/mirror/csd/salesReport1.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 1</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/salesReport2.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 2</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/salesReport3.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 3</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/salesReport6.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 4</span>
                </a>
            </div>
        <#--<div class="col-lg-2">
            <a href="/mirror/csd/salesReport5.erp?from=thisMonth&to=thisMonth" original-title="Payment Report">
                <img src="../resources/images/csd/paymentStatement.png"><span>Collection 1</span>
            </a>
        </div>
        <div class="col-lg-2">
            <a href="/mirror/report/getCollectionDataAllYear.erp" original-title="View Customer by MID">
                <img src="../resources/images/csd/paymentStatement.png"><span>Collection 2</span>
            </a>
        </div>-->
        </div>
    </div>
</div>

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label style="color: green; font-size: 15px;"> Annual General Meeting (AGM) Report</label>
        </div>
        <div class="panel-body">
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-2">
                        <a href="/mirror/report/getSalesByYear.erp" original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Sales By Year</span>
                        </a>
                    </div>

                    <div class="col-lg-2">
                        <a href="/mirror/report/getSalesByMonth.erp" original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Sales By Month</span>
                        </a>
                    </div>

                    <div class="col-lg-3">
                        <a href="/mirror/report/getSalesBySalesPersonType.erp"
                           original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Sales By Sales Person Type</span>
                        </a>
                    </div>
                    <div class="col-lg-3">
                        <a href="/mirror/report/getSalesBySalesPerson.erp?type=ALL&limit=-1"
                           original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Sales By Sales Person</span>
                        </a>
                    </div>
                    <div class="col-lg-2">
                        <a href="/mirror/report/getSalesBySalesPersonExcel.erp?type=ALL&limit=-1"
                           original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>By Sales Person</span>
                        </a>
                    </div>
                </div>
                <div class="row" style="padding-top: 22px">

                    <div class="col-lg-2">
                        <a href="/mirror/report/getSalesBySalesPerson.erp?type=Director&limit=-1"
                           original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Director Sales</span>
                        </a>
                    </div>
                    <div class="col-lg-2">
                        <a href="/mirror/report/getSalesBySalesPerson.erp?type=Employee&limit=-1"
                           original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Marketing Sales</span>
                        </a>
                    </div>
                    <div class="col-lg-3">
                        <a href="/mirror/report/getSalesBySalesPerson.erp?type=Others&limit=-1"
                           original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>FMT Sales</span>
                        </a>
                    </div>

                    <div class="col-lg-3">
                        <a href="/mirror/report/getSalesBySalesPerson.erp?type=Share Holder&limit=-1"
                           original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Share Holder Sales</span>
                        </a>
                    </div>
                    <div class="col-lg-2">
                        <a href="#<#--/mirror/report/getSalesByBOD.erp?from=24/10/2014-->"
                           onclick="dateSelectionWindow('','');" original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>Sales By BOD</span>
                        </a>
                    </div>
                </div>
                <div class="row" style="padding-top: 22px">
                    <div class="col-lg-2">
                        <a href="/mirror/report/getReferredCustomer.erp" original-title="View Customer by MID">
                            <img src="../resources/images/csd/report.png"><span>All Referred</span>
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label style="color: green; font-size: 15px;">Marketing Report & Admin Report</label>
        </div>
        <div class="panel-body">
            <div class="col-lg-3">
                <a href="/mirror/report/getSalesDataSalesPersonWise.erp?type=Employee&from=01/01/2010&to="
                   original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales Summary (MKT)</span>
                </a>
            </div>
            <div class="col-lg-3">
                <a href="/mirror/report/getSalesDataDetailsSalesPersonWise.erp?type=Employee&from=01/01/2010&to="
                   original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales Details (MKT)</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/csd/viewCustomerByMid.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Customer by MID</span>
                </a>
            </div>
            <div class="col-lg-2">
                <a href="/mirror/report/adminReportHome.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Admin Report</span>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label style="color: green; font-size: 15px;">Customer Report</label>
        </div>
        <div class="panel-body">

        </div>
    </div>
</div>

<script src="../resources/js/extjs/ext-all.js"></script>

<script>
    $(function () {
        $("#date-from").datepicker({format: 'dd-mm-yyyy', autoclose: true});
        $("#date-to").datepicker({format: 'dd-mm-yyyy', autoclose: true});
        $("#date-from-16").datepicker({format: 'dd-mm-yyyy', autoclose: true});
        $("#date-to-16").datepicker({format: 'dd-mm-yyyy', autoclose: true});
    });

    $('#viewCollection').click(function () {
        var from = $('#date-from').val();
        var to = $('#date-to').val();
        var cid = $('#customer-id').val();
        if (from == "" || to == "" || cid == "") {
            $('#message-ps').html('Fill the field first');
            return false;
        }
        window.location = '/mirror/mis/showPaymentStatement.erp?cid=' + cid + '&from=' + from + '&to=' + to;
    });

    $('#view-customer-payment-month').click(function () {
        var month = $('#vcpm-month').val();
        var type = $('#vcpm-type').val();
        console.log(month);
        console.log(type);
        //return;
        postMe('/mirror/mis/report_13.erp', 'month=' + month + '&type=' + type);
//        window.location = '/mirror/mis/showPaymentStatement.erp?cid=' + cid + '&from=' + from + '&to=' + to;
    });

    $('#id-16').click(function () {
        var from = $('#date-from-16').val();
        var to = $('#date-to-16').val();
        if (from == "" || to == "") {
            //$('#message-16').html('Fill the field first');
            return false;
        }
        window.location = '/mirror/mis/showCollectionReport_16.erp?from=' + from + '&to=' + to;
    });

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

<script>
    function dateSelectionWindow(action, extraParam) {
        //postMe(action, 'courseId=' + 2+'&batchNo=' + 2);
        var form = Ext.create('Ext.form.Panel', {
            title: '',
            bodyPadding: 5,
            width: 350,
            //url: action,
            layout: 'anchor',
            defaults: {
                anchor: '100%'
            },
            items: [
                {
                    xtype: 'datefield',
                    fieldLabel: 'Date From',
                    value: Ext.Date.format(new Date(), 'd/m/Y'),
                    name: 'date',
                    format: 'd/m/Y',
                    allowBlank: false
                }],

            buttons: [{
                text: 'Reset',
                handler: function () {
                    this.up('form').getForm().reset();
                }
            }, {
                text: 'View',
                formBind: true,
                disabled: true,
                handler: function () {
                    var form = this.up('form').getForm();
                    var date = toDate(form.findField('date').getValue(), 'DD/MM/YYYY');
                    if (form.isValid()) {
                        postMe(action, 'date=' + date + extraParam);
                        //alert(action+'?from=' + from + '&to=' + to + extraParam);
                    }
                }
            }]
        });
        Ext.create('Ext.window.Window', {
            title: 'Select Date',
            items: [form]
        }).show();
    }
</script>

