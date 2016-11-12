<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="itemList.erp">Item List</a></li>
        <li><a  href="#">Item Create</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="dataForm" id="dataForm" action="saveItem.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="name">
                            Item Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="name" id="name" placeholder=""/>
                    </div>
                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="catId">
                            Item Category:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="catId" id="catId">
                            <#list categories as category>
                                <option value="${category.id}">${category.name}</option>
                            </#list>
                        </select>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="unitId">
                            Measurement Unit:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="unitId" id="unitId">
                            <#list units as units>
                                <option value="${units.id}">${units.name}</option>
                            </#list>
                        </select>
                    </div>


                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="approximatePrice">
                            Approx Price:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="approximatePrice" id="approximatePrice" placeholder="Approximate Price"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="description">
                            Description:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="description" id="description" placeholder="Description"/>
                    </div>

                    <br class="clear" /> <br class="clear" />
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
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
