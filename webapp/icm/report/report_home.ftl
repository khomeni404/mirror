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
                    <img src="../resources/images/csd/report.png"><span>Item List</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/viewItemSummary.erp?category=all" original-title="Payment Statement">
                    <img src="../resources/images/csd/statement.png"><span>Item Summary</span>
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
            <li>
                <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                    <img src="../resources/images/csd/statement.png"><span>Statement</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewPaymentReport.erp?cid=1" original-title="Find Other Payment">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Payment Report</span>
                </a>
            </li>
        </ul>
    </div>
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">Collection Report</label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/csd/viewPaymentSummaryAll.erp?range=all" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Summary</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                    <img src="../resources/images/csd/statement.png"><span>Statement</span>
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
            <li>
                <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                    <img src="../resources/images/csd/statement.png"><span>Statement</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewPaymentReport.erp?cid=1" original-title="Find Other Payment">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Payment Report</span>
                </a>
            </li>
        </ul>
    </div>
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">
            Marketing Report
        </label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/csd/viewPaymentSummaryAll.erp?range=all" original-title="Payment Report">
                    <img src="../resources/images/csd/report.png"><span>Sales by MID</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                    <img src="../resources/images/csd/statement.png"><span>Sales by Cat.</span>
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
            <li>
                <a href="/mirror/csd/viewPaymentStatement.erp?cid=1" original-title="Payment Statement">
                    <img src="../resources/images/csd/statement.png"><span>Statement</span>
                </a>
            </li>
            <li>
                <a href="/mirror/csd/viewPaymentReport.erp?cid=1" original-title="Find Other Payment">
                    <img src="../resources/images/csd/paymentStatement.png"><span>Payment Report</span>
                </a>
            </li>
        </ul>
    </div>


</div>
<#--End widget div-->

</@layout.gen_app_layout>

