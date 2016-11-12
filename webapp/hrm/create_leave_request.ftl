<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="#">Dashboard</a></li>

        <li><a href="#">Dashboard</a></li>
        <li>Create Leave Request</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="advance_waiver_crate" class="content" role="main" style="width: 450px;">

        <form name="advanceWaiverForm" id="advanceWaiverForm" action="saveLeaveRequest" method="POST">

            <fieldset class="form">


                <div class="container_16">

                    <div class="grid_3 alpha">
                        <label for="employeeId">
                            Employee Id:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="employeeId" id="employeeId"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_8 label">
                        <div class="grid_3 alpha msg_text">

                            Leave Type:
                        </div>
                         <div class="grid_4 omega">
                    <select>
                        <#list leaves as leave>
                            <option value="${leave.id}">${leave.leaveType}</option>
                        </#list>
                    </select>
                         </div>
                    </div>
                    </div>

                    <#--<div class="grid_3 alpha">-->
                        <#--<label for="leaveType">-->
                            <#--Leave Type:-->
                            <#--<span class="required-indicator">*</span>-->
                        <#--</label>-->
                    <#--</div>-->

                    <#--<div class="grid_4 omega reset-input">-->
                        <#--<input type="text" name="leaveType" id="leaveType"/>-->
                    <#--</div>-->

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="leaveFrom">
                            Leave From:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="leaveFrom" id="leaveFrom"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="to">
                            To:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="to" id="to"/>
                    </div>


                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="purpose">
                            Purpose:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="purpose" id="purpose"/>
                    </div>




                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="remark">
                            Remark:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="remark" id="remark"/>
                    </div>


                    <br class="clear" />
                    <br class="clear" />


                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="remarkCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.remarkForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.remarkForm.submit();
            alert(1)
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>