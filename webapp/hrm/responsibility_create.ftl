<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="/mirror/home.erp">Dashboard</a></li>
        <li><a href="recruitHome.erp">Recruitment Home</a></li>
        <li>Create Responsibility</li>
    </ul>
</div>
<br class="clear"/>
<#if Session.saveValue?exists>
    <div class="uiBoxYellow">${Session.saveValue}</div>
</#if>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="jobTitle_crate" class="content" style="width: 450px;">

        <form name="responsibilityForm" id="responsibilityForm" action="saveResponsibility.erp" method="POST">

            <fieldset class="form">


                <div class="container_16">

                    <div class="grid_3 alpha">
                        <label for="responsibilityCode">
                            Responsibility Code:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="responsibilityCode" id="responsibilityCode"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                    <div class="grid_3 alpha">
                        <label for="responsibilityName">
                            Responsibility Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="responsibilityName" id="responsibilityName"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />


                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="jobTitleDescriptionCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.responsibilityForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.responsibilityForm.submit();

        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>