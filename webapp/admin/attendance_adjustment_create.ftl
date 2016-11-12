<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="#">Dashboard</a></li>

        <li><a href="#">Dashboard</a></li>
        <li>Create Attendance Adjustment</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="attendanceAdjustment_crate" class="content" role="main" style="width: 450px;">

        <form name="attendanceAdjustmentForm" id="attendanceAdjustmentForm" action="saveUser.erp" method="POST">

            <fieldset class="form">

                <div class="container_16">

                    <div class="grid_3 alpha">
                        <label for="adjustmentType">
                            Adjustment Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="adjustmentType" id="adjustmentType"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="adjustmentThreshold">
                            Adjustment Threshold:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="adjustmentThreshold" id="adjustmentThreshold"/>
                    </div>



                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="lateThresholdCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.attendanceAdjustmentForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.attendanceAdjustmentForm.submit();
            alert(1)
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>