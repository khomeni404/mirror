<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a  href="timeHome.erp">Time Home</a></li>
        <li><a  href="#">Holiday Create</a></li>
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

        <form name="dataForm" id="dataForm" action="saveHolidayEntitlement.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="dateFrom">
                            Date (From):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="dateFrom" class="myElement a1" id="dateFrom" placeholder="YYYY-MM-DD" value=""/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="holidayType">
                            Holiday Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="holidayType" id="holidayType">
                            <option value="Public">Public Holiday</option>
                            <option value="General">General Holiday</option>
                        </select>
                    </div>



                    <br class="clear" />   <br class="clear" />
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
                        <label for="note">
                            Issue:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="note" class="myElement a3" id="note" value="" placeholder="Holiday title"/>
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
            $(".a1").focus();
        });


        function submitForm(){
            document.dataForm.submit();
            //alert("Record Saved ! !");
        }

    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
