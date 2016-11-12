<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 849px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li>${PageTitle}</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 849px; min-height: 550px">
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
        <span><span class="ico gray approve"></span>&nbsp;&nbsp;${PageTitle}</span>
    </div>

   <#-- <div id="recruit_home" class="content scaffold-list" role="main" style="width: 799px; height: 100px">
        <b style="color: green">Director, Accounts & Finance:</b>
        <ul class="widgetlist">
            <li>
                <a href="../csd/voucherListNa.erp" original-title="Payment Schedule">
                    <img src="../resources/images/csd/waiting.png"><span>Not approved voucher List</span>
                </a>
            </li>
            <li>
                <a href="../csd/voucherListAp.erp" original-title="Payment Schedule">
                    <img src="../resources/images/csd/approved.png"><span>Approved voucher List</span>
                </a>
            </li>
            <li>
                <a href="../csd/voucherListRe.erp" original-title="Payment Schedule">
                    <img src="../resources/images/csd/problem.png"><span>Rejected voucher List</span>
                </a>
            </li>
        </ul>
    </div>
    <br/>  <br/>
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 799px; height: 100px">
        <b style="color: green">Managing Director:</b>
        <ul class="widgetlist">

            <li>
                <a href="../csd/customerListNa.erp" original-title="Payment Schedule">
                    <img src="../resources/images/csd/waiting.png"><span>NOT approved CUSTOMER List</span>
                </a>
            </li>
            <li>
                <a href="../csd/customerListAp.erp" original-title="Payment Schedule">
                    <img src="../resources/images/csd/approved.png"><span>Approved CUSTOMER List</span>
                </a>
            </li>
            <li>
                <a href="../csd/customerListCa.erp" original-title="Payment Schedule">
                    <img src="../resources/images/csd/problem.png"><span>Rejected CUSTOMER List</span>
                </a>
            </li>

        </ul>
    </div>
    <br/><br/>-->
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 799px;height: 100px">
        <b style="color: green">Accounts Executive: <b/>
        <ul class="widgetlist">

            <li>
                <a href="../csd/chequeListNotPassed.erp?status=N" original-title="NOT passed cheque">
                    <img src="../resources/images/csd/waiting.png"><span>NOT passed cheque list</span>
                </a>
            </li>

            <li>
                <a href="../csd/chequeListNotPassed.erp?status=Y" original-title="NOT passed cheque">
                    <img src="../resources/images/csd/approved.png"><span>Passed cheque list</span>
                </a>
            </li>

            <li>
                <a href="../csd/chequeListNotPassed.erp?status=X" original-title="NOT passed cheque">
                    <img src="../resources/images/csd/problem.png"><span>Rejected cheque list</span>
                </a>
            </li>


        </ul>
    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>