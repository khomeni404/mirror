<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="/mirror/admin/directorList.erp?type=Director">Director List</a></li>
        <li><a  href="#">Create</a></li>
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

        <form name="dataForm" id="dataForm" action="saveDirector.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="mid">
                            MID:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="mid" id="mid" placeholder="mid" value=""/>
                    </div>


                    <br class="clear" /> <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="name">
                            Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="name" id="name" placeholder="name"  value=""/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="reference">
                            Reference:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="reference" id="reference" placeholder="reference" value=""/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="type">
                            Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="type" id="type">
                            <option value="Director">Director</option>
                            <option value="Share Holder">Share Holder</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="designation">
                            Designation:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="designation" id="designation" placeholder="designation" value="No Designation"/>
                    </div>
                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="department">
                            Department:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="department" id="department" placeholder="department" value="N/A"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="contactOne">
                            Phone (1):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="contactOne" id="contactOne" placeholder="contactOne" value=""/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="contactTwo">
                            Phone (2):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="contactTwo" id="contactTwo" placeholder="contactTwo" value=""/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="email">
                            E-mail:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="email" id="email" placeholder="E-mail" value=""/>
                    </div>



                    <br class="clear" /> <br class="clear" />
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="directorCreate" class="button icon approve" onclick="submitForm()">Save</a>
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
            //alert("Record Saved ! !");
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
