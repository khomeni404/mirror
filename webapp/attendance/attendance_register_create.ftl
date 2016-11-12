<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="/mirror/home.erp">Dashboard</a></li>
        <li><a href="#">Attendance Register Home</a></li>
        <li>Attendance Register List</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="attendanceRegisterForm" id="attendanceRegisterForm" action="saveAttendanceRegister.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="jobDate">
                            Job Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="jobDate" id="jobDate" placeholder="Job Date"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="jobTitle">
                            Remark:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="remark" id="remark" placeholder="Remark"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="jobTitle">
                            In Time:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="inTime" id="inTime" placeholder="In Time"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="jobTitle">
                            Out Time:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="outTime" id="outTime" placeholder="Out Time"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="jobTitle">
                            Attendant:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <select id="leave" name="leave">
                            <#list employees as employee>
                                <option value="${employee.authorizeId}">${employee.employeeProfile.name.firstName}</option>
                            </#list>
                        </select>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="jobTitle">
                            Actual Work Hours:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="actualWorkHours" id="actualWorkHours" placeholder="Actual Work Hours"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="jobTitle">
                            Late Minute:
                            <span class="required-indicator"></span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="lateMinute" id="lateMinute" placeholder="Late Minute"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />


                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.attendanceRegisterForm.reset()">Clear</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.attendanceRegisterForm.submit();
            alert("Record Saved ! !");
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
