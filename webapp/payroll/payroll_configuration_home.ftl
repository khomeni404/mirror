<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 849px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li>${PageTitle}</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 849px;">
    <style>
        .content{height: 125px;}
        .widgetlist { list-style: none; }
        .widgetlist li { display: inline-block; float: left; width: 130px; margin: 0 10px 10px 0; }
        .widgetlist li a { display: block; padding: 15px; border: 1px solid #ccc; color: #333; text-align: center;  }
        .widgetlist li a { -moz-border-radius: 3px; -webkit-border-radius: 3px; border-radius: 3px; -moz-box-shadow: 1px 1px 0 #fff; color: #069; }
        .widgetlist li a span { font-size: 12px; display: block; margin-top: 10px; }
        .widgetlist li a:hover { -moz-box-shadow: 0 0 4px #ddd; text-decoration: none; }
    </style>
    <div class="header" style="width: 849px;">
        <span><span class="ico gray recruit"></span>&nbsp;&nbsp;${PageTitle}</span>
    </div>

    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 799px;height: 600px">
        <ul class="widgetlist">

            <li>
                <a href="../payroll/allowanceTypeList.erp" original-title="Allowance Type">
                    <img src="../resources/images/hrm/common.png"><span>Allowance Type</span>
                </a>
            </li>
            <li>
                <a href="../payroll/incentiveList.erp" original-title="Incentive">
                    <img src="../resources/images/hrm/jobTitle.png"><span>Incentive</span>
                </a>
            </li>
            <li>
                <a href="../payroll/deductTypeList.erp" original-title="Deduct Type">
                    <img src="../resources/images/hrm/jobRole.png"><span>Deduct Type</span>
                </a>
            </li>
            <li>
                <a href="../payroll/overtimeRateList.erp" original-title="Overtime Rate">
                    <img src="../resources/images/hrm/jobCategory.png"><span>Overtime Rate</span>
                </a>
            </li>
            <li>
                <a href="../payroll/paymentMethodList.erp" original-title="Payment Method">
                    <img src="../resources/images/hrm/ico1.png"><span>Payment Method</span>
                </a>
            </li>
            <li>
                <a href="../payroll/paymentRequisitionList.erp" original-title="Payment Requisition">
                    <img src="../resources/images/hrm/degree.png"><span>Payment Requi.</span>
                </a>
            </li>
            <li>
                <a href="../payroll/payRegisterList.erp" original-title="Pay Register">
                    <img src="../resources/images/hrm/ico2.png"><span>Pay Register</span>
                </a>
            </li>

            <li>
                <a href="../payroll/providentFundList.erp" original-title="Provident Fund">
                    <img src="../resources/images/hrm/jobSpec.png"><span>Provident Fund</span>
                </a>
            </li>


            <li>
                <a href="../payroll/othersList.erp" original-title="Distributor Home">
                    <img src="../resources/images/hrm/common.png"><span>Others</span>
                </a>
            </li>
        </ul>
    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>