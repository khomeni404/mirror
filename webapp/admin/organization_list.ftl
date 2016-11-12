<#import "../gen_template/gen_app_layout.ftl" as layout>

<@layout.gen_app_layout "${PageTitle}" >

<script src="organization_list.js"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="#">Dashboard</a></li>

        <li><a href="#">Dashboard</a></li>
        <li>Organization List</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 700px">

    <div class="header" style="width: 700px;">
        <span><span class="ico gray authority"></span>${PageTitle}</span>
    </div>

    <div id="organization_list" class="content" role="main" style="width: 650px;">
    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>