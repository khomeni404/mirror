<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<script type="text/javascript">
    Ext.onReady(function () {
    })
</script>

<div class="bread_crumbs_ui_div" style="width: 1100px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li><a href="findOtherPayment.erp?cid=1">Payment Schedule</a></li>
        <li> Create Payments</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 1100px">

<div class="header" style="width: 1100px;">
    <span><span class="ico job posting"></span>${PageTitle}</span>
</div>

<div id="job_posting_create" class="content" role="main" onmousemove="" style="width: 1050px;">
    <br/><br class="clear"/><br class="clear"/>

    <form name="dataForm" id="dataForm" action="saveOtherPayment.erp" method="POST">
        <table class="head">
            <tr>
                <td>Customer Identity:</td>
                <td><input type="text" name="cid" id="cidId" placeholder="Customer ID" onkeyup="errorMsgHandler();"/>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>Number of Payment:</td>
                <td><input type="text" name="numberOfPayment" id="numberOfPayment" placeholder="Number of Other Payment"
                           onkeyup="makeRow();"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>
                    <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                    <a id="ff" class="button danger icon remove" onclick="document.dataForm.reset()">Clear</a>
                </td>
            </tr>
        </table>
        <label id="errorMsg" style="color: red; font-weight: bold"></label>

        <br class="clear"/><br class="clear"/>
        <table class="tab1" style="margin-left: 50px">
            <tr class="trh">
                <th>Payment Type</th>
                <th>Payment Name</th>
                <th>Amount</th>
                <th>Dead Line</th>
                <th>Note</th>
            </tr>
            <tr class="tr1">
                <td class="">
                    <select id="paymentType1" name="paymentType1">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName1" id="paymentName1"/></td>
                <td class=""><input type="text" name="amount1" id="amount1"/></td>
                <td class=""><input type="text" name="deadLine1" id="deadLine1" placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note1" id="note1"/></td>
            </tr>
            <tr class="tr2">
                <td class="">
                    <select id="paymentType2" name="paymentType2">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName2" id="paymentName2"/></td>
                <td class=""><input type="text" name="amount2" id="amount2"/></td>
                <td class=""><input type="text" name="deadLine2" id="deadLine2" placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note2" id="note2"/></td>
            </tr>
            <tr class="tr3">
                <td class="">
                    <select id="paymentType3" name="paymentType3">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName3" id="paymentName3"/></td>
                <td class=""><input type="text" name="amount3" id="amount3"/></td>
                <td class=""><input type="text" name="deadLine3" id="deadLine3" placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note3" id="note3"/></td>
            </tr>
            <tr class="tr4">
                <td class="">
                    <select id="paymentType4" name="paymentType4">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName4" id="paymentName4"/></td>
                <td class=""><input type="text" name="amount4" id="amount4"/></td>
                <td class=""><input type="text" name="deadLine4" id="deadLine4" placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note4" id="note4"/></td>
            </tr>
            <tr class="tr5">
                <td class="">
                    <select id="paymentType5" name="paymentType5">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName5" id="paymentName5"/></td>
                <td class=""><input type="text" name="amount5" id="amount5"/></td>
                <td class=""><input type="text" name="deadLine5" id="deadLine5" placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note5" id="note5"/></td>
            </tr>
            <tr class="tr6">
                <td class="">
                    <select id="paymentType6" name="paymentType6">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName6" id="paymentName6" readonly
                                    placeholder="Sorry these field are readonly"/></td>
                <td class=""><input type="text" name="amount6" id="amount6" readonly/></td>
                <td class=""><input type="text" name="deadLine6" id="deadLine6" readonly placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note6" id="note6" readonly/></td>
            </tr>
            <tr class="tr7">
                <td class="">
                    <select id="paymentType7" name="paymentType7">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName7" id="paymentName7" readonly/></td>
                <td class=""><input type="text" name="amount7" id="amount7" readonly/></td>
                <td class=""><input type="text" name="deadLine7" id="deadLine7" readonly placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note7" id="note7" readonly/></td>
            </tr>
            <tr class="tr8">
                <td class="">
                    <select id="paymentType8" name="paymentType8">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName8" id="paymentName8" readonly/></td>
                <td class=""><input type="text" name="amount8" id="amount8" readonly/></td>
                <td class=""><input type="text" name="deadLine8" id="deadLine8" readonly placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note8" id="note8" readonly/></td>
            </tr>
            <tr class="tr9">
                <td class="">
                    <select id="paymentType9" name="paymentType9">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName9" id="paymentName9" readonly/></td>
                <td class=""><input type="text" name="amount9" id="amount9" readonly/></td>
                <td class=""><input type="text" name="deadLine9" id="deadLine9" readonly placeholder="YYYY-MM-DD"/></td>
                <td class=""><input type="text" name="note9" id="note9" readonly/></td>
            </tr>
            <tr class="tr10">
                <td class="">
                    <select id="paymentType10" name="paymentType10">
                        <option value="Down Payment">Down Payment</option>
                        <option value="Special Payment">Special Payment</option>
                        <option value="Car Park">Car Park</option>
                        <option value="Other Payment">Other Payment</option>
                    </select>
                </td>
                <td class=""><input type="text" name="paymentName10" id="paymentName10" readonly/></td>
                <td class=""><input type="text" name="amount10" id="amount10" readonly/></td>
                <td class=""><input type="text" name="deadLine10" id="deadLine10" readonly placeholder="YYYY-MM-DD"/>
                </td>
                <td class=""><input type="text" name="note10" id="note10" readonly/></td>
            </tr>

        </table>
        <br class="clear"/><br class="clear"/>
    </form>
</div>
<!--end user create div-->
<script type="text/javascript">
    function submitForm() {
        var cid = document.getElementById("cidId").value;
        if (cid == "") {
            document.getElementById("cidId").focus();
            document.getElementById("errorMsg").innerHTML = "Please insert CID";
            return false;
        }else if (cid != "") {
            var times = document.getElementById("numberOfPayment").value;
            for (var i = 1; i <= times; i++) {
                var paymentName = document.getElementById("paymentName" + i).value;
                if(paymentName===""){
                    alert("Please insert Payment Name")
                    $('#paymentName'+i).focus();
                    return false;
                }
                var amount = document.getElementById("amount" + i).value;
                if(!$.isNumeric( amount ) || amount === ""){
                    alert("Please insert amount")
                    $('#amount'+i).focus();
                    $('#amount'+i).select();
                    return false;
                }
                var date = document.getElementById("deadLine" + i).value;
                if(!validateYYYYMMDD(date)){
                    $('#deadLine'+i).focus();
                    $('#deadLine'+i).select();
                    return false;
                }
            }
            document.dataForm.submit();

        }

    }
    function errorMsgHandler() {
        var cid = document.getElementById("cidId").value;
        if (cid == "") {
            document.getElementById("cidId").focus();
            document.getElementById("errorMsg").innerHTML = "Please insert CID";
            return false;
        }
        else if (cid != "") {
            document.getElementById("errorMsg").innerHTML = "";
        }
    }
    $(document).ready(function () {
        for (var i = 1; i <= 10; i++) {
            $(".tr" + i).hide();
            document.getElementById("paymentName" + i).value = "No Name";
            document.getElementById("amount" + i).value = -1;
            document.getElementById("deadLine" + i).value = "2011/11/11";
            document.getElementById("note" + i).value = "No Note";

        }
        $(".trh").hide();
    });
    function makeRow() {
        errorMsgHandler();
        var times = document.getElementById("numberOfPayment").value;
        for (var i = 1; i <= 10; i++) {
            $(".tr" + i).hide();
            document.getElementById("paymentName" + i).value = "No Name";
            document.getElementById("amount" + i).value = -1;
            document.getElementById("deadLine" + i).value = "2011/11/11";
            document.getElementById("note" + i).value = "No Note";
        }
        $(".trh").hide();
        for (var i = 1; i <= times; i++) {
            $(".tr" + i).show();
            document.getElementById("paymentName" + i).value = "";
            document.getElementById("amount" + i).value = "";
            document.getElementById("deadLine" + i).value = "";
            document.getElementById("note" + i).value = "---";
            $(".trh").show();
        }
    }
</script>


<#--as it is in recruitment controller but the ftl and js is in hrm2 folder so the src will be preceded by ../hrm2-->
<script src="../csd/other_payment.js"></script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>