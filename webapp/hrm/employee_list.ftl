<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<script src="leave_list.js"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">Attendance Home</a></li>
        <li><a  href="#">Leave</a></li>
        <li>Leave list</li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>Leave list</span>
    </div>

    <div class="content">
        <div id="leave_lists" class="content scaffold-list" role="main">



        </div>
    </div>

</div>

</@layout.gen_app_layout>