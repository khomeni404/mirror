<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="icmHome.erp">ICM Home</a></li>
        <li><a  href="#">Item In</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="dataForm" id="dataForm" action="saveItemIn.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="itemName">
                            Item:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="itemList" name="itemName" id="itemName" style="height: 30px; padding-left: 5px; width: 200px;"/>
                        <datalist id="itemList"  autocomplete="off">
                            <#list items as item>
                            <option value="${item.name}">
                            </#list>
                        </datalist>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="source">
                            Source:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="source" id="source">
                            <option value="Purchase">Purchase</option>
                            <option value="Get Back">Get Back</option>
                            <option value="Others">Others</option>
                        </select>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="qty">
                            Quantity/Amount:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="qty" id="qty" placeholder="Quantity / Amount"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="storeId">
                            Store:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="storeId" id="storeId">
                            <#list stores as store>
                                <option value="${store.id}">${store.name}</option>
                            </#list>
                        </select>
                    </div>

                    <!--Please remove this two hidden field when Checkedby and ApprovedBy will be activated-->
                    <input type="hidden" name="checkedId" id="checkedId" value="(NULL)"/>
                    <input type="hidden" name="approvedId" id="approvedId" value="(NULL)"/>

                    <#--
                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="checkedBy">
                            Item Checked By:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="userListCheck" name="checkedId" id="checkedId" style="height: 30px; padding-left: 5px; width: 200px;"/>
                        <datalist id="userListCheck"  autocomplete="off">
                            <#list users as user>
                            <option value="${user.token.name} : ${user.authorizeId}">
                            </#list>
                        </datalist>
                    </div>
                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="approveBy">
                            Item Approved By:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="userListApprove" name="approvedId" id="approvedId" style="height: 30px; padding-left: 5px; width: 200px;"/>
                        <datalist id="userListApprove"  autocomplete="off">
                            <#list users as user>
                            <option value="${user.token.name} : ${user.authorizeId}">
                            </#list>
                        </datalist>
                    </div>
                    -->
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
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
