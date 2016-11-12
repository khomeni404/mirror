<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 1000px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">ICM Home</a></li>
        <li><a  href="#.erp">Item History Create</a></li>

    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 1000px">

    <div class="header" style="width: 1000px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 950px;">

        <form name="dataForm" id="dataForm" action="saveItemUsageHistory.erp" method="POST">
            <fieldset class="form">
                <div class="grid_3 container_16">
                    <h3 style="color: #070fc4">${itemName}</h3>
                    <input type="hidden" name="itemName" id="itemName" value="${itemName}"/>
                    <table>
                        <tr>
                            <td class="particular">Received Item</td><td class="amount">${received} ${unitName}</td>
                            <td class="blankA"></td> <td class="blankB"></td><td class="blankA"></td>
                            <td class="particular" style="font-weight: bold; color: green; font-size: 18px">Item Usage History</td><td class="amount"></td>
                        </tr>
                        <tr>
                            <td class="particular">Used Item</td><td class="amount">${used} ${unitName}</td>
                            <td class="blankA"></td> <td class="blankB"></td><td class="blankA"></td>
                            <td class="particular">New Usage</td>
                            <td class="amount">
                                <input type="text" name="qty" id="qty" onkeyup="checkLimit();" placeholder="${unitName}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="particular">Lost Item</td><td class="amount">${lost} ${unitName}</td>
                            <td class="blankA"></td> <td class="blankB"></td><td class="blankA"></td>
                            <td class="particular"></td><td class="amount"></td>
                        </tr>
                        <tr>
                            <td class="particular">Backed Item</td><td class="amount">${back} ${unitName}</td>
                            <td class="blankA"></td> <td class="blankB"></td><td class="blankA"></td>
                            <td class="particular">Usage Type</td>
                            <td class="amount">
                                <select name="status" id="status">
                                    <option value="Used">Used</option>
                                    <option value="Lost">Lost</option>
                                    <option value="Back">Back</option>
                                    <option value="Damaged">Damaged</option>
                                    <option value="Other">Other</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="particular">Existing Item</td><td class="amount" id="limit">${rest} ${unitName}</td>
                            <td class="blankA"></td> <td class="blankB"></td><td class="blankA"></td>
                            <td class="particular"></td><td class="amount"></td>
                        </tr>
                        <tr>
                            <td class="particular"></td><td class="amount"></td>
                            <td class="blankA"></td> <td class="blankB"></td><td class="blankA"></td>
                            <td class="particular">Cause</td>
                            <td class="amount">
                                <input type="text" id="note" name="note" placeholder="Note"/>
                            </td>
                        </tr>
                        <td class="particular"></td><td class="amount"></td>
                        <td class="blankA"></td> <td class="blankB">|</td><td class="blankA"></td>
                        <td class="particular"></td><td class="amount"></td>
                        <tr>
                            <td class="particular"></td><td class="amount"></td>
                            <td class="blankA"></td> <td class="blankB"></td><td class="blankA"></td>
                            <td class="particular"></td>
                            <td class="amount">
                                <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                                <a id="ff" class="button danger icon remove" onclick="document.dataForm.reset()">Clear</a>
                            </td>
                        </tr>
                    </table>
                    <style>
                        .particular{
                            width: 200;
                        }
                        .blankA{
                            width: 100;
                        }
                        .blankB{
                            background-color: #bc5105;
                            color: #bc5105;
                            width: 5px;
                        }
                    </style>

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
            //document.getElementById("msg3").innerHTML = qty;
            //document.getElementById("msg4").innerHTML = " out of ";
            //document.getElementById("msg5").innerHTML = itemNumber;
            if(itemNumber-qty >= 0){
                //document.getElementById("msg6").innerHTML = " | Rest is ";
                //document.getElementById("msg7").innerHTML = itemNumber-qty;
            }else{
                //document.getElementById("msg3").innerHTML = "0";
                document.getElementById("msg").innerHTML = "Sorry you have only "+itemNumber;
                //document.getElementById("msg7").innerHTML = "more than "+items;
                document.getElementById("qty").value = "";
            }
        }
        function submitForm(){
            document.dataForm.submit();
        }

        $(document).ready(function(){
            $("select").change(function(){
                $( "select option:selected").each(function(){
                    if($(this).attr("value")=="Lost"){
                        $(".cidFrom").show();
                    }
                    if($(this).attr("value")=="withdraw"){
                        $(".cidFrom").show();
                        $(".cidTo").hide();
                        $(".drCr").show();
                    }
                    if($(this).attr("value")=="other"){

                    }
                });
            }).change();
        });

    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
