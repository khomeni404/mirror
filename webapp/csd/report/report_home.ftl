<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 900px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li>${PageTitle}</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 900px;">
    <style>
        .content{height: 125px;}
        .widgetlist { list-style: none; }
        .widgetlist li { display: inline-block; float: left; width: 130px; margin: 0 10px 10px 0; }
        .widgetlist li a { display: block; padding: 15px; border: 1px solid #ccc; color: #333; text-align: center;  }
        .widgetlist li a { -moz-border-radius: 3px; -webkit-border-radius: 3px; border-radius: 3px; -moz-box-shadow: 1px 1px 0 #fff; color: #069; }
        .widgetlist li a span { font-size: 12px; display: block; margin-top: 10px; }
        .widgetlist li a:hover { -moz-box-shadow: 0 0 4px #ddd; text-decoration: none; }
    </style>
    <div class="header" style="width: 900px;">
        <span><span class="ico gray reportHome"></span>&nbsp;&nbsp;${PageTitle}</span>
    </div>

    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">Customer Report</label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/csd/viewPaymentSummaryAll.erp?range=all" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Summary</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/salesReport4.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Due Summary</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                    <img src="../resources/images/csd/report.png"><span>Statement</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewPaymentReport.erp?cid=1" original-title="Find Other Payment">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Report</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewOverdueReport.erp" original-title="Find Other Payment">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Dues List</span>
                </a>
            </li>

        </ul>
    </div>
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">Status Report</label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/csd/customerListByStatus.erp?status=Approved" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Approved Sales</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/customerListByStatus.erp?status=Cancelled" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Cancelled Sales</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/customerListByStatus.erp?status=Processing" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Processing Sales</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/customerListByStatus.erp?status=Refunded" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Refunded Sales</span>
                </a>
            </li>
        </ul>
    </div>
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">
            Sales & Collection Report
        </label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/csd/salesReport1.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 1</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/salesReport2.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 2</span>
                </a>
            </li>

            <li>
                <a href="/mirror/csd/salesReport3.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 3</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/salesReport6.erp" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales Report 4</span>
                </a>
            </li>

            <li>
                <a href="/mirror/csd/salesReport5.erp?from=thisMonth&to=thisMonth" original-title="Payment Report">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Collection 1</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getCollectionDataAllYear.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Collection 2</span>
                </a>
            </li>
        </ul>
    </div>


    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 350px">
        <label style="color: green; font-size: 15px;">
            Annual General Meeting (AGM) Report
        </label><hr/>
        <ul class="widgetlist">


            <li>
                <a href="/mirror/report/getSalesByYear.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales By Year</span>
                </a>
            </li>

            <li>
                <a href="/mirror/report/getSalesByMonth.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales By Month</span>
                </a>
            </li>

            <li>
                <a href="/mirror/report/getSalesBySalesPersonType.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales By Sales Person Type</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getSalesBySalesPerson.erp?type=ALL&limit=-1" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales By Sales Person</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getSalesBySalesPersonExcel.erp?type=ALL&limit=-1" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>By Sales Person</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getSalesBySalesPerson.erp?type=Director&limit=-1" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Director Sales</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getSalesBySalesPerson.erp?type=Employee&limit=-1" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Marketing Sales</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getSalesBySalesPerson.erp?type=Others&limit=-1" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>FMT Sales</span>
                </a>
            </li>

            <li>
                <a href="/mirror/report/getSalesBySalesPerson.erp?type=Share Holder&limit=-1" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Share Holder Sales</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getSalesByBOD.erp?from=24/10/2014" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales By BOD</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getReferredCustomer.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>All Referred</span>
                </a>
            </li>
         <#--
         <li>
                <a href="/mirror/report/adminReportHome.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Admin Report</span>
                </a>
            </li>
            -->

        </ul>
    </div>

    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">
            Marketing Report  & Admin Report
        </label><hr/>
        <ul class="widgetlist">

            <li>
                <a href="/mirror/report/getSalesDataSalesPersonWise.erp?type=Employee&from=01/01/2010&to=" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales Summary (MKT)</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/getSalesDataDetailsSalesPersonWise.erp?type=Employee&from=01/01/2010&to=" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Sales Details (MKT)</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewCustomerByMid.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Customer by MID</span>
                </a>
            </li>
            <li>
                <a href="/mirror/report/adminReportHome.erp" original-title="View Customer by MID">
                    <img src="../resources/images/csd/report.png"><span>Admin Report</span>
                </a>
            </li>
            <!--    <li>
                   <a href="/mirror/csd/viewPaymentReport.erp?cid=1" original-title="Find Other Payment">
                       <img src="../resources/images/csd/paymentStatement.png"><span>Payment Report</span>
                   </a>
               </li>
               <li>
                   <a href="/mirror/csd/viewPaymentSummaryAll.erp?range=all" original-title="Payment Report">
                       <img src="../resources/images/csd/report.png"><span>Sales by MID</span>
                   </a>
               </li>
               <li>
                   <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                       <img src="../resources/images/csd/report.png"><span>Sales by Cat.</span>
                   </a>
               </li>
               <li>
                   <a href="/mirror/csd/viewPaymentReport.erp?cid=1" original-title="Find Other Payment">
                       <img src="../resources/images/csd/paymentStatement.png"><span>Report</span>
                   </a>
               </li>
               <li>
                   <a href="/mirror/csd/viewPaymentSummaryAll.erp?range=all" original-title="Payment Report">
                       <img src="../resources/images/csd/report.png"><span>XYZ</span>
                   </a>
               </li>
              -->
        </ul>
    </div>


</div>
<#--End widget div-->

</@layout.gen_app_layout>

