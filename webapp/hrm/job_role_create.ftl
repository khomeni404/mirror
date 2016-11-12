<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="/mirror/home.erp">Dashboard</a></li>

        <li><a href="/hrm/jobHome.erp">Job Home</a></li>
        <li>Create Job Role</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="jobRole_crate" class="content" role="main" style="width: 450px;">

        <form name="jobRoleForm" id="jobRoleForm" action="saveJobRole.erp" method="POST">

            <fieldset class="form">


                <div class="container_16">

                    <div class="grid_3 alpha">
                        <label for="jobRoleName">
                            Job Role Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="jobRoleName" id="jobRoleName"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="jobRoleDescription">
                            Job Role Description:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="jobRoleDescription" id="jobRoleDescription"/>
                    </div>



                    <br class="clear" />
                    <br class="clear" />



                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="jobRoleDescriptionCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.jobRoleForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.jobRoleForm.submit();
            alert("Role Saved")
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>