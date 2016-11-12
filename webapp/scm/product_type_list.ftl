<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="../scm/product_type_list.js"></script>
<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li>Product Type List</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>User</span>
    </div>
    <div class="content">
        <div id="product_type_list" class="content scaffold-list" role="main"></div>
    </div>
</div>
</@layout.gen_app_layout>