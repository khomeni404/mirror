<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboardpl</a></li>

        <li><a href="userList.erp">Leave List</a></li>
        <li>Update Leave</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="leaveUpdateForm" id="leaveUpdateForm" action="updateLeave.erp" method="POST">
            <input type="hidden" name="id" id="id" value="${id}"/>

            <fieldset class="form">
                <div class="container_16">

                    <div class="grid_3 alpha">
                        <label for="leaveType">
                            Leave Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="leaveType" id="leaveType" value="${leaveType}"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="leaveDuration">
                            Leave Duration:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="leaveDuration" id="leaveDuration" value="${leaveDuration}"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="confirmPassword">
                            Leave Notes:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="leaveNotes" id="leaveNotes" value="${leaveNotes}"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />



                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Update</a>
                        <a id="ff" class="button danger icon remove" onclick="document.leaveUpdateForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.leaveUpdateForm.submit();
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>