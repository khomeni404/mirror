<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="#">Dashboard</a></li>

        <li><a href="#">Dashboard</a></li>
        <li>Create Over Time</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="overtime_crate" class="content" role="main" style="width: 450px;">

        <form name="overtimeForm" id="overtimeForm" action="saveUser.erp" method="POST">

            <fieldset class="form">


                <div class="container_16">

                    <div class="grid_3 alpha">
                        <label for="overtime">
                            Overtime:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <input type="text" name="overtime" id="overtime"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />



                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="overtimeCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.overtimeForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.overtimeForm.submit();
            alert(1)
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>