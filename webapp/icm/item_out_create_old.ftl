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
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="itemName">
                            Item:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select  name="itemName" id="itemName">
                            <#list items as item>
                                <option value="${item.name}">${item.name}</option>
                            </#list>
                        </select>
                    </div>
                     <#--
                    <div class="grid_4 omega reset-input">
                        <input list="itemList" name="itemName" id="itemName" style="height: 30px; padding-left: 5px; width: 200px;"/>
                        <datalist id="itemList"  autocomplete="off">
                            <#list items as item>
                            <option value="${item.name}">
                            </#list>
                        </datalist>
                    </div>
                    -->
                    <label style="font-size: 12" id="itemAvailable1" name="itemAvailable1" ></label>
                    <label style="font-size: 15; color: green" id="itemAvailable2" name="itemAvailable2" ></label>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="qty">
                            Quantity/Amount:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="qty" id="qty" placeholder="Quantity / Amount" onkeyup="count();"/>
                    </div>
                    <label style="font-size: 14; color: green" id="itemAvailable3" name="itemAvailable3" ></label>
                    <label style="font-size: 12;" id="itemAvailable4" name="itemAvailable4" ></label>
                    <label style="font-size: 14; color: red" id="itemAvailable5" name="itemAvailable5" ></label>
                    <label style="font-size: 12;" id="itemAvailable6" name="itemAvailable6" ></label>
                    <label style="font-size: 14; color: red" id="itemAvailable7" name="itemAvailable7" ></label>


                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="storeName">
                            From:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="storeList" name="storeName" id="storeName" style="height: 30px; padding-left: 5px; width: 200px;"/>
                        <datalist id="storeList"  autocomplete="off">
                            <#list stores as store>
                            <option value="${store.name}">
                            </#list>
                        </datalist>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="user">
                            For:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="userList" name="user" id="user" style="height: 30px; padding-left: 5px; width: 200px;"/>
                        <datalist id="userList"  autocomplete="off">
                            <#list users as user>
                            <option value="${user.token.name} : ${user.authorizeId}">
                            </#list>
                        </datalist>
                    </div>

                    <#-- Hidden field for storing item and corresponding values   -->
                    <#list itemSummarises as itemSummary>
                        <input type="hidden"
                               id="${itemSummary.itemName}"
                               name="${itemSummary.itemName}"
                               value="${itemSummary.available} ${itemSummary.unitName}"/>
                    </#list>

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
        function count(){
            var items = document.getElementById("itemAvailable2").innerHTML;
            var itemNumber = parseInt(items);
            var qty = document.getElementById("qty").value;
            if(qty == ""){
                qty = 0;
            }
            document.getElementById("itemAvailable3").innerHTML = qty;
            document.getElementById("itemAvailable4").innerHTML = " out of ";
            document.getElementById("itemAvailable5").innerHTML = itemNumber;
            if(itemNumber-qty >= 0){
                document.getElementById("itemAvailable6").innerHTML = " | Rest is ";
                document.getElementById("itemAvailable7").innerHTML = itemNumber-qty;
            }else{
                document.getElementById("itemAvailable3").innerHTML = "0";
                document.getElementById("itemAvailable6").innerHTML = " | Sorry you can't disburse ";
                document.getElementById("itemAvailable7").innerHTML = "more than "+items;
                document.getElementById("qty").value = "";
            }

        }
        $(document).ready(function(){
            $("select").change(function(){

                $( "select option:selected").each(function(){
                    var itemName = $(this).attr("value");
                    var itemAvailable = document.getElementById(itemName).value;
                    document.getElementById("itemAvailable1").innerHTML = "Available ";
                    document.getElementById("itemAvailable2").innerHTML = itemAvailable;
                    clearItemAvailableLabels();
                });
            }).change();
        });

        function clearItemAvailableLabels(){
            document.getElementById("itemAvailable3").innerHTML = "";
            document.getElementById("itemAvailable4").innerHTML = "";
            document.getElementById("itemAvailable5").innerHTML = "";
            document.getElementById("itemAvailable6").innerHTML = "";
            document.getElementById("itemAvailable7").innerHTML = "";
        }

        function submitForm(){
            document.dataForm.submit();
            //alert("Record Saved ! !");
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
