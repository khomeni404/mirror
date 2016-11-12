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

        <form name="dataForm" id="dataForm" action="saveWeekend.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="dateOf">
                            Weekend (Date):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="dateOf" class="myElement a1" id="dateOf" placeholder="YYYY-MM-DD"/>
                    </div>

                    <br class="clear" /> <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="type">
                            Weekend Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="type" id="type">
                            <option value="General">General Weekend</option>
                            <option value="Substitute">Substitute Weekend</option>
                        </select>
                    </div>

                    <br class="clear" /> <br class="clear" />
                    <div class="grid_3 alpha dateIns">
                        <label for="note">
                            Date Instead of :
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input dateIns">
                        <input type="text" name="note" class="myElement a3" id="note" placeholder="10/12/2014"/>
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
        $(document).ready(function(){
            $(".a1").focus();
            $("select").change(function(){
                $( "select option:selected").each(function(){
                    if($(this).attr("value")=="General"){
                        $(".dateIns").hide();
                        document.getElementById("note").value = "General";
                    }
                    if($(this).attr("value")=="Substitute"){
                        document.getElementById("note").value = "";
                        $(".dateIns").show();
                    }
                    if($(this).attr("value")=="other"){

                    }
                });
            }).change();
        });


        function submitForm(){
            document.dataForm.submit();
            //alert("Record Saved ! !");
        }

    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
