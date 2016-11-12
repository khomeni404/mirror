<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<script src="money_receipt_list.js"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a  href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="cidForm" id="cidForm" action="findMoneyReceipt.erp" method="GET">
            <input type="text" name="mr" id="mr" placeholder="MR number (Press Enter)"/>
            <label id="errorMsg" name="errorMsg" style="color: #ff0507;"></label>
        </form>
        <div id="mr_list_div" class="content scaffold-list" role="main">
             <!-- Here the data will be showed from java script file according to div id -->

        </div>
    </div>

</div>

</@layout.gen_app_layout>