<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a  href="leaveApplicationList.erp?status=0">Application List</a></li>
        <li><a  href="#">App. Create</a></li>
    </ul>
</div>
<br class="clear"/>


<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>
    <div  style="width: 500px;">
        <span><span style="color: red; font-weight: bold"></span></span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="dataForm" id="dataForm" action="saveLeaveApplication.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="days">
                            Number of days:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="days" class="myElement a0" id="days" placeholder="days" value=""/>

                    </div>


                    <br class="clear" /> <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="fromDate">
                            From (Date):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="fromDate" class="myElement a1" id="fromDate" placeholder="YYYY-MM-DD" value=""/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="issue">
                            Issue:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="issue" class="myElement a3" id="issue" value="" placeholder="Issue"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="leaveType">
                            Leave Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="leaveType" id="leaveType">
                            <option value="0">Casual Leave</option>
                            <option value="1">Sick Leave</option>
                            <option value="2">Emergency Leave</option>
                            <option value="3">Public Holiday</option>
                        </select>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="location">
                            Located (On Leave):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="location" class="myElement a3" id="location" value="" placeholder="Location"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="phone">
                            Phone (On Leave):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="phone" class="myElement a3" id="phone" value="" placeholder="10 Digit"/>
                    </div>

                    <br class="clear" /> <br class="clear"/>
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve myElement a7"  onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.dataForm.reset()">Clear</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->
    <script type="text/javascript">
        $(document).ready(function () {
            $(".a0").focus();
        });
        var counter = 1;
        $(".myElement").keyup(function (event) {
            if (event.keyCode == 13) {
                if(counter < 4){
                    $(".a"+counter).focus();
                    $(".a"+counter).select();
                    counter++;
                }else{
                    submitForm();
                }

            }
        });

        function submitForm(){
            document.dataForm.submit();
            //alert("Record Saved ! !");
        }

        function setCashDate() {
            var receiptDate = document.getElementById("receiptDate");
            var cashDate = document.getElementById("cashDate");
            cashDate.value = receiptDate.value;
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
