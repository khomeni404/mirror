<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="/mirror/home.erp">Dashboard</a></li>

        <li><a href="#">Leave Home</a></li>
        <li>Leave Entitlement</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="leaveRegisterForm" id="leaveRegisterForm" action="saveLeaveRegister.erp" method="POST">

            <fieldset class="form">
                <div class="container_16">
                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            From Date:     <span class="required-indicator">*</span></label></div><div class="grid_4 omega reset-input">
                            <input type="text" name="fromDate" id="fromDate" placeholder="" value="2014-07-21"/></div>

                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            To Date:     <span class="required-indicator">*</span></label></div><div class="grid_4 omega reset-input">
                            <input type="text" name="toDate" id="toDate" placeholder=""  value="2014-07-25"/></div>

                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            Leave Days:     <span class="required-indicator">*</span></label></div><div class="grid_4 omega reset-input">
                            <input type="text" name="leaveDays" id="leaveDays" placeholder="" value="4"/></div>

                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            Leave Purpose:     <span class="required-indicator">*</span></label></div><div class="grid_4 omega reset-input">
                            <input type="text" name="leavePurpose" id="leavePurpose" placeholder="" value="Family Issue"/></div>

                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            Remark:     <span class="required-indicator">*</span></label></div><div class="grid_4 omega reset-input">
                            <input type="text" name="remark" id="remark" placeholder="" value="No remark"/></div>


                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            Leave Type:     <span class="required-indicator">*</span></label></div>
                    <div class="grid_4 omega reset-input">
                        <select id="leave" name="leave" style="width: 170px">
                            <#list leaves as leave>
                                <option value="${leave.id}">${leave.leaveType}</option>
                            </#list>
                        </select>
                    </div>

                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            Leave Status:     <span class="required-indicator">*</span></label></div>
                    <div class="grid_4 omega reset-input">
                        <select id="leaveStatus" name="leaveStatus"  style="width: 270px">
                            <option value="1">Disapproved</option>
                            <option value="2">Approved</option>
                            <option value="3">Processing</option>
                        </select>
                    </div>

                    <br class="clear" /><br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leave">
                            Approved Disapproved Reason:     <span class="required-indicator">*</span></label></div><div class="grid_4 omega reset-input">
                            <input type="text" name="approvedDisapprovedReason" value="No Reason" id="approvedDisapprovedReason" placeholder=""/></div>

                    <br class="clear" /><br class="clear" />
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.leaveRegisterForm.reset()">Clear</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.leaveRegisterForm.submit();
            alert("Record Saved ! !");
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
