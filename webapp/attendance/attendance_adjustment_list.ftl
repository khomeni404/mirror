<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<script src="attendance_adjustment_list.js"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">Attendance Home</a></li>
        <li><a  href="#">Attendance Adjustment</a></li>
        <li>Attendance Adjustment  List</li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>Attendance Adjustment List</span>
    </div>

    <div class="content">
        <div id="attendance_adjustment_list" class="content scaffold-list" role="main">



        </div>
    </div>

</div>

</@layout.gen_app_layout>