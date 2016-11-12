<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

    <div class="bread_crumbs_ui_div" style="width: 500px">
        <ul id="crumbs_ui_custom">
            <li><a href="${DashboardLink}">Dashboard</a></li>
            <li><a href="attendanceList.erp">Attendance List</a></li>
            <li>Attendance Employee</li>
        </ul>
    </div>
    <br class="clear"/>

    <div class="widget" style="width: 500px">

        <div class="header" style="width: 500px;">
            <span><span class="ico gray user"></span>${PageTitle}</span>
        </div>

        <div id="user_crate" class="content" role="main" style="width: 450px;">

            <form name="saveEmployeeAttendanceForm" id="saveEmployeeAttendanceForm" action="saveEmployeeAttendance.erp" method="POST">

                <fieldset class="form">
                    <div class="fieldcontain  required">
                        <label for="employeeEntryName">
                            <div id="employeeEntryName">Employee Name</div>
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="text" name="userCode" required=""  value="" />
                    </div>

                    <div class="fieldcontain required">
                        <label for="employeePassword">
                            <div id="employeePassword">Employee Password</div>
                            <span class="required-indicator">*</span>
                        </label>
                        <input type="password" name="password" required=""  value="" />
                    </div>

                    <div class="grid_4 alpha">&nbsp;&nbsp;</div>
                    <div class="grid_4 omega" style="padding-left: 184px;padding-top: 15px">

                        <a id="employeeEntryForm" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.employeeEntryForm.reset()">Clear</a>

                    </div>

                </fieldset>
                <br class="clear"/>


            </form>
        </div>
        <!--end user create div-->

        <script type="text/javascript">
            function submitForm(){
                document.saveEmployeeAttendanceForm.submit();
            }
        </script>
    </div>
    <#--End widget div-->

</@layout.gen_app_layout>