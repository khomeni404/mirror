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
                <a href="../hrm/employmentStatusList.erp" original-title="Employment Status">
                    <img src="../resources/images/hrm/common.png"><span>Emp. Status</span>
                </a>
            </li>
            <li>
                <a href="../hrm/jobTitleList.erp" original-title="Job Title">
                    <img src="../resources/images/hrm/jobTitle.png"><span>Job Title</span>
                </a>
            </li>
            <li>
                <a href="../hrm/jobRoleList.erp" original-title="Job Role">
                    <img src="../resources/images/hrm/jobRole.png"><span>Job Role</span>
                </a>
            </li>
            <li>
                <a href="../hrm/jobSpecList.erp" original-title="Job Spec">
                    <img src="../resources/images/hrm/jobSpec.png"><span>Job Spec</span>
                </a>
            </li>
            <li>
                <a href="../hrm/jobCategoryList.erp" original-title="Job Status">
                    <img src="../resources/images/hrm/jobCategory.png"><span>Job Category</span>
                </a>
            </li>
            <li>
                <a href="../hrm/jobAppreciationList.erp" original-title="Job Appreciation">
                    <img src="../resources/images/hrm/ico1.png"><span>Job Appreciation</span>
                </a>
            </li>
            <li>
                <a href="../hrm/degreeList.erp" original-title="Job Status">
                    <img src="../resources/images/hrm/degree.png"><span>Degree</span>
                </a>
            </li>
            <li>
                <a href="../hrm/degreeList.erp" original-title="Job Status">
                    <img src="../resources/images/hrm/ico2.png"><span>Employee Prof.</span>
                </a>
            </li>

            <li>
                <a href="../category/home.erp" original-title="Category Home">
                    <img src="../resources/images/hrm/common.png"><span>Category Home</span>
                </a>
            </li>

            <li>
                <a href="../vendor/home.erp" original-title="Vendor Home">
                    <img src="../resources/images/hrm/common.png"><span>Vendor Home</span>
                </a>
            </li>

            <li>
                <a href="../distributor/home.erp" original-title="Distributor Home">
                    <img src="../resources/images/hrm/common.png"><span>Distributor Home</span>
                </a>
            </li>
        </ul>
    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>