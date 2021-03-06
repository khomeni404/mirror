<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 849px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li>Recruit Home</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 849px">

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
        <span><span class="ico gray recruit"></span>&nbsp;&nbsp;Recruitment Home</span>
    </div>

    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 799px;">
        <ul class="widgetlist">

            <li><a href="createJobPosting.erp"><img src="../resources/images/recruit/post_jobs.png"><span>Job Posting</span></a></li>
            <li><a href="responsibilityList.erp"><img src="../resources/images/recruit/responsibility_48.png"><span>Responsibility</span></a></li>
            <li></li>
        </ul>
    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>