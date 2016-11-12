<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="#">Building</a></li>
        <li><a  href="#">Edit</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="editForm" id="editForm" action="updateBuilding.erp" method="POST">
            <input type="hidden" name="id" id="id" value="${id}"/>

            <fieldset class="form">
                <div class="container_16">

                    <div class="grid_3 alpha">
                        <label for="leaveType">
                            Building Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="buildingName" id="buildingName" value="${buildingName}"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="totalFloorSize">
                            Floor Size:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="totalFloorSize" id="totalFloorSize" value="${totalFloorSize}"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="numberOfFloor">
                            Number of Floor:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="numberOfFloor" id="numberOfFloor" value="${numberOfFloor}"/>
                    </div>
                    <br class="clear" /><br class="clear" />

                    <div class="grid_3 alpha">
                    <label for="numberOfUnit">
                        Number of Unit:
                        <span class="required-indicator">*</span>
                    </label>
                </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="numberOfUnit" id="numberOfUnit" value="${numberOfUnit}"/>
                    </div>
                    <br class="clear" /><br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="landArea">
                            Land Area:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="landArea" id="landArea" value="${landArea}"/>
                    </div>
                    <br class="clear" /><br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="projectName">
                            Project Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="projectName" id="projectName" value="${projectName}"/>
                    </div>
                    <br class="clear" /><br class="clear" />



                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Update</a>
                        <a id="ff" class="button danger icon remove" onclick="document.editForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.editForm.submit();
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>