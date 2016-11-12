<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<script src="customer_list.js"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="#">Customer</a></li>
        <li><a  href="#">List</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>Customer list</span>
    </div>

    <div class="content">
        <div id="customer_list_div" class="content scaffold-list" role="main">

             <!-- Here the data will be showed from java script file according to div id -->

        </div>
    </div>

</div>

</@layout.gen_app_layout>