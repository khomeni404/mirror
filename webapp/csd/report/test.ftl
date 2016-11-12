<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 650px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboardpl</a></li>

        <li><a href="userList.erp">Leave List</a></li>
        <li>Update Leave</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 650px">

    <div class="header" style="width: 650px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 600px;">

        <form onload="init();" name="leaveUpdateForm" id="leaveUpdateForm" action="updateLeave.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <table class="disburse">
                        <tr>
                            <th class="particular">Particular</th>
                            <th class="total">Total</th>
                            <th class="payable">Payable</th>
                            <th class="disburse">Disbursed</th>
                            <th class="due">Due
                            <th class="adjust">Adjustment</th>
                        </tr>

                        <tr>
                            <td class="particular">Instalment Amount</td>
                            <td class="total"><input style="width: 80px;" style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="payable"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="disburse"><input style="width: 80px;" type="text" value="dollarValue" id="instOld" name="instOld"/></td>
                            <td class="due"><input style="width: 80px;" type="text" value="dollarValue"/>
                            <td class="adjust"><input style="width: 80px;" class="adjust" type="text" name="instNew" id="instNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Down-payment</td>
                            <td class="total"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="payable"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="disburse"><input style="width: 80px;" type="text" value="dollarValue" name="dpOld" id="dpOld"/></td>
                            <td class="due"><input style="width: 80px;" type="text" value="dollarValue"/>
                            <td class="adjust"><input style="width: 80px;" class="adjust" type="text" name="dpNew" id="dpNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Special Payment</td>
                            <td class="total"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="payable"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="disburse"><input style="width: 80px;" type="text" value="dollarValue" id="spOld" name="spOld"/></td>
                            <td class="due"><input style="width: 80px;" type="text" value="dollarValue"/>
                            <td class="adjust"><input style="width: 80px;" class="adjust" type="text" name="spNew" id="spNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Car Parking</td>
                            <td class="total"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="payable"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="disburse"><input style="width: 80px;" type="text" value="dollarValue" id="cpOld" name="cpOld"/></td>
                            <td class="due"><input style="width: 80px;" type="text" value="dollarValue"/>
                            <td class="adjust"><input style="width: 80px;" class="adjust" type="text" name="cpNew" id="cpNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Other</td>
                            <td class="total"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="payable"><input style="width: 80px;" type="text" value="dollarValue"/></td>
                            <td class="disburse"><input style="width: 80px;" type="text" value="dollarValue" id="otherOld" name="otherOld"/></td>
                            <td class="due"><input style="width: 80px;" type="text" value="dollarValue"/>
                            <td class="adjust"><input style="width: 80px;" class="adjust" type="text" name="otherNew" id="otherNew" onkeyup="calculate();"/></td>
                        </tr>


                    </table>
                   <br/>
                    <table class="disburseAmount">
                        <tr>
                            <td class="particular">Paid Total</td>
                            <td><input style="width: 100px;" type="text" value="500" id="paid" name="paid"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="particular">Disburse Total</td>
                            <td><input style="width: 100px;" type="text" value="400" id="disburse" name="disburse" onkeyup="calculate();"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="particular">Rest Total</td>
                            <td><input style="width: 100px;" type="text" name="rest" id="rest"/></td>
                            <td id="msg" style="color:red; font-weight: bold;"></td>
                        </tr>
                    </table>

                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">

        var paid = document.getElementById("paid").value;

        var paidAmount = 0;
        if(paid!=""){
            paidAmount = eval(paid);
        }

        var disburse = document.getElementById("disburse").value;
        var disburseAmount = 0;
        if(disburse!=""){
            disburseAmount = eval(disburse);
        }

        var rest = document.getElementById("rest").value;
        var restAmount = 0;
        if(rest!=""){
            restAmount = eval(rest);
        }

        document.getElementById("rest").value = paidAmount-disburseAmount;
        function calculate() {
            document.getElementById("msg").innerHTML = "";
            var instNew = document.getElementById("instNew").value;
            var instNewAmount = 0;
            if(instNew!=""){
                instNewAmount = eval(instNew);
            }

            var dpNew = document.getElementById("dpNew").value;
            var dpNewAmount = 0;
            if(dpNew!=""){
                dpNewAmount = eval(dpNew);
            }

            var spNew = document.getElementById("spNew").value;
            var spNewAmount = 0;
            if(spNew!=""){
                spNewAmount = eval(spNew);
            }

            var cpNew = document.getElementById("cpNew").value;
            var cpNewAmount = 0;
            if(cpNew!=""){
                cpNewAmount = eval(cpNew);
            }

            var otherNew = document.getElementById("otherNew").value;
            var otherNewAmount = 0;
            if(otherNew!=""){
                otherNewAmount = eval(otherNew);
            }

            document.getElementById("disburse").value = disburseAmount+instNewAmount+dpNewAmount+spNewAmount+cpNewAmount+otherNewAmount;
            init();

        }

        function init(){
            var paid = document.getElementById("paid").value;

            var paidAmount = 0;
            if(paid!=""){
                paidAmount = eval(paid);
            }

            var disburse = document.getElementById("disburse").value;
            var disburseAmount = 0;
            if(disburse!=""){
                disburseAmount = eval(disburse);
            }
            if(paidAmount-disburseAmount < 0){
                document.getElementById("msg").innerHTML = "Sorry ! This amount is not acceptable.";
                alert("There are no enough money");
            }
            document.getElementById("rest").value = paidAmount-disburseAmount;
        }
    </script>
    <style>
        .disburse{font-family: calibri;}
        th{text-align:left;}
        .particular{width:150px;}
    </style>

</div>
<#--End widget div-->

</@layout.gen_app_layout>