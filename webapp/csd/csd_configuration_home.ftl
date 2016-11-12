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
        .content {
            height: 125px;
        }

        .widgetlist {
            list-style: none;
        }

        .widgetlist li {
            display: inline-block;
            float: left;
            width: 130px;
            margin: 0 10px 10px 0;
        }

        .widgetlist li a {
            display: block;
            padding: 15px;
            border: 1px solid #ccc;
            color: #333;
            text-align: center;
        }

        .widgetlist li a {
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            -moz-box-shadow: 1px 1px 0 #fff;
            color: #069;
        }

        .widgetlist li a span {
            font-size: 12px;
            display: block;
            margin-top: 10px;
        }

        .widgetlist li a:hover {
            -moz-box-shadow: 0 0 4px #ddd;
            text-decoration: none;
        }
    </style>
    <div class="header" style="width: 849px;">
        <span><span class="ico gray recruit"></span>&nbsp;&nbsp;${PageTitle}</span>
    </div>

    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 799px;height: 200px">
        <ul class="widgetlist">


            <li>
                <a href="../csd/createCustomer.erp" original-title="Job Title">
                    <img src="../resources/images/hrm/jobTitle.png"><span>Add Customer</span>
                </a>
            </li>
            <li>
                <a href="../csd/createEmpCsd.erp" original-title="Employee">
                    <img src="../resources/images/hrm/jobRole.png"><span>Employee</span>
                </a>
            </li>
            <li>
                <a href="../csd/createDirector.erp" original-title="Job Spec">
                    <img src="../resources/images/hrm/jobSpec.png"><span>Director</span>
                </a>
            </li>
            <li>
                <a href="../csd/mIDList.erp" original-title="Job Status">
                    <img src="../resources/images/hrm/jobCategory.png"><span>M I D</span>
                </a>
            </li>
            <li>
                <a href="../csd/buildingList.erp" original-title="Job Appreciation">
                    <img src="../resources/images/hrm/ico1.png"><span>Building</span>
                </a>
            </li>
            <li>
                <a href="../csd/viewBuilding.erp?id=13" original-title="Job Appreciation">
                    <img src="../resources/images/hrm/ico1.png"><span>Booking List</span>
                </a>
            </li>
            <li>
                <a href="../csd/projectList.erp" original-title="Job Status">
                    <img src="../resources/images/hrm/degree.png"><span>Project</span>
                </a>
            </li>
            <li>
                <a href="../csd/offerList.erp" original-title="Job Status">
                    <img src="../resources/images/hrm/ico2.png"><span>Offer</span>
                </a>
            </li>
            <li>
                <a href="../admin/createBoardMeeting.erp" original-title="Job Status">
                    <img src="../resources/images/admin/meeting.png" height="50px" width="50px" /><span>BOD Meeting</span>
                </a>
            </li>
            <li>
                <a href="../csd/createCancellationApp.erp" original-title="Job Status">
                    <img src="../resources/images/hrm/ico2.png" height="50px" width="50px" /><span>Cancellation App</span>
                </a>
            </li>

        </ul>

    </div>


</div>
<#--End widget div-->

</@layout.gen_app_layout>