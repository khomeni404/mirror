<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 850px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">ICM Home</a></li>
        <li><a  href="itemOutList.erp?status=n">Item Out List</a></li>
        <li><a  href="#">Item Out</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 850px">

    <div class="header" style="width: 850px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 800px;">

        <form name="dataForm" id="dataForm" action="saveItemOut.erp" method="POST">
            <fieldset class="form">
                <div class="container_16" style="font-size: 15px">
                    <div class="grid_3 alpha">
                        <label for="userId">
                            Send For:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="hidden" id="userId" name="userId" value="${userId}"/>
                        <label name="userName" id="userName">${userName}</label>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="itemName">
                            Item Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <label>${itemName}</label>
                        <input type="hidden" name="itemName" id="itemName" value="${itemName}" readonly/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label>
                            Item Available:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <label style="font-size: 15; color: green" >${available} ${unitName}</label>
                    </div>
                         <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label>
                            Request For:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <label style="font-size: 15; color: red"  id="limit" name="limit">${requestFor} ${unitName}</label>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="qty">
                            Quantity/Amount:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <#if available - requestFor < 0>
                            <label style="color: red">Sorry ! No item available</label>
                        <#else >
                            <input type="text" name="qty" id="qty" placeholder="Quantity / Amount" onkeyup="checkLimit();"/>
                        </#if>
                    </div>
                    <label style="font-size: 14; color: green" id="msg3" name="msg3" ></label>
                    <label style="font-size: 12;" id="msg4" name="msg4" ></label>
                    <label style="font-size: 14; color: red" id="msg5" name="msg5" ></label>
                    <label style="font-size: 12;" id="msg6" name="msg6" ></label>
                    <label style="font-size: 14; color: red" id="msg7" name="msg7" ></label>


                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="storeName">
                            From:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select  name="storeName" id="storeName">
                            <#list stores as store>
                                <option value="${store.name}">${store.name}</option>
                            </#list>

                        </select>
                    </div>



                    <br class="clear" /> <br class="clear" />
                    <div class="grid_4 omega reset-input reset-button-left">
                        <#if available - requestFor < 0>
                            <#else >
                            <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        </#if>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function checkLimit(){
            var items = document.getElementById("limit").innerHTML;
            var itemNumber = parseInt(items);
            var qty = document.getElementById("qty").value;
            if(qty == ""){
                qty = 0;
            }
            document.getElementById("msg3").innerHTML = qty;
            document.getElementById("msg4").innerHTML = " out of ";
            document.getElementById("msg5").innerHTML = itemNumber;
            if(itemNumber-qty >= 0){
                document.getElementById("msg6").innerHTML = " | Rest is ";
                document.getElementById("msg7").innerHTML = itemNumber-qty;
            }else{
                document.getElementById("msg3").innerHTML = "0";
                document.getElementById("msg6").innerHTML = " | Sorry you can't disburse ";
                document.getElementById("msg7").innerHTML = "more than "+items;
                document.getElementById("qty").value = "";
            }
        }


        function submitForm(){
            document.dataForm.submit();
            //alert("Record Saved ! !");
        }

        $(document).ready(function () {
            $("#qty").focus();
        });
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
