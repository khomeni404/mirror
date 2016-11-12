<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="/mirror/csd/buildingList.erp">Building List</a></li>
        <li><a  href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="dataForm" id="dataForm" action="saveBuilding.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="buildingName">
                            Building Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="buildingName" id="buildingName" placeholder=""/>
                    </div>


                    <br class="clear"/>
                    <br class="clear"/>
                    <div class="grid_3 alpha">
                        <label for="nameAlias">
                            Name Alias:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="nameAlias" id="nameAlias" placeholder="FIL | MIM | BUD | N/A"/>
                    </div>

                    <br class="clear"/>
                    <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="floorSizes">
                            Floor Sizes:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="floorSizes" id="floorSizes" placeholder="1700 | 1300 | 2450" "/>
                    </div>


                    <br class="clear" /> <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="totalFloorSize">
                            Total Floor Size:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="totalFloorSize" id="totalFloorSize" placeholder="totalFloorSize"/>
                    </div>




                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="numberOfFloor">
                            Number of floor:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="numberOfFloor" id="numberOfFloor" placeholder="Number Of Floor"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="numberOfUnit">
                            Total Number of unit:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="numberOfUnit" id="numberOfUnit" placeholder="numberOfUnit"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="aptIds">
                            Apt. IDs:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <textarea name="aptIds" id="aptIds" cols="30" rows="10" placeholder="101,102,103,201,202"></textarea>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="landArea">
                            Land Area:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="landArea" id="landArea" placeholder="Land Area"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="handOver">
                            Hand-Over:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="handOver" id="handOver" placeholder="2021 | December 2021"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="projectName">
                            Project:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                          <select name="pid" id="pid">
                              <#list projects as project>
                                  <option value="${project.id}">${project.projectName}</option>
                              </#list>
                          </select>
                    </div>



                    <br class="clear" /> <br class="clear" />
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.dataForm.reset()">Clear</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.dataForm.submit();
            alert("Record Saved ! !");
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
