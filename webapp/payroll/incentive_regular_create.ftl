<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="/mirror/home.erp">Dashboard</a></li>

        <li>Add Regular Incentive</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="incentiveForm" id="incentiveForm" action="saveIncentive.erp" method="POST">
            <input id="incentiveType" name="incentiveType" value="1" type="hidden">
            <fieldset class="form">


                <div class="container_16">

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="incentiveName">
                            Incentive Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="incentiveName" id="incentiveName"/>
                    </div>
                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="incentiveAmount">
                            Incentive Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="incentiveAmount" id="incentiveAmount"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.incentiveForm.reset()">Clear</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.incentiveForm.submit();
            alert("Job Spec Saved");
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
