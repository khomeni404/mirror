<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 750px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboardpl</a></li>

        <li><a href="userList.erp">Disburse</a></li>
        <li>Update</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 750px">

    <div class="header" style="width: 750px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 700px;">

        <form onload="init();" name="dataForm" id="dataForm" action="updateMoneyDisburse.erp" method="POST">
            <input type="hidden" name="id" id="id" value="${pdb.disbursementId}"/>
            <input type="hidden" name="cid" id="cid" value="${pdb.cid}"/>
            <fieldset class="form">
                <div class="container_16">
                    <table class="disburse">
                        <tr>
                            <th class="particular"></th>
                            <th class="total">Total</th>
                            <th class="payable">Payable</th>
                            <th class="disburse">Disbursed</th>
                            <th class="due">Due
                            <th class="adjust">Adjustment</th>
                        </tr>

                        <tr>
                            <td class="particular">Instalment Amount</td>
                            <td class="total"><input readonly style="width: 90px; text-align: right; font-size: 18px" style="width: 80px;" type="text" value="${pdb.totalInst}"/></td>
                            <td class="payable"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableInst}"/></td>
                            <td class="disburse"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.disburseInst}" id="instOld" name="instOld"/></td>
                            <td class="due"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableInst-pdb.disburseInst}"/>
                            <td class="adjust"><input style="width: 90px; text-align: right; font-size: 18px" value="0" class="adjust" type="text" name="instNew" id="instNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Down-payment</td>
                            <td class="total"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.totalDp}"/></td>
                            <td class="payable"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableDp}"/></td>
                            <td class="disburse"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.disburseDp}" name="dpOld" id="dpOld"/></td>
                            <td class="due"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableDp-pdb.disburseDp}"/>
                            <td class="adjust"><input style="width: 90px; text-align: right; font-size: 18px" value="0" class="adjust" type="text" name="dpNew" id="dpNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Special Payment</td>
                            <td class="total"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.totalSp}"/></td>
                            <td class="payable"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableSp}"/></td>
                            <td class="disburse"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.disburseSp}" id="spOld" name="spOld"/></td>
                            <td class="due"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableSp-pdb.disburseSp}"/>
                            <td class="adjust"><input style="width: 90px; text-align: right; font-size: 18px" value="0" class="adjust" type="text" name="spNew" id="spNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Car Parking</td>
                            <td class="total"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.totalCp}"/></td>
                            <td class="payable"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableCp}"/></td>
                            <td class="disburse"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.disburseCp}" id="cpOld" name="cpOld"/></td>
                            <td class="due"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableCp-pdb.disburseCp}"/>
                            <td class="adjust"><input style="width: 90px; text-align: right; font-size: 18px" value="0" class="adjust" type="text" name="cpNew" id="cpNew" onkeyup="calculate();"/></td>
                        </tr>
                        <tr>
                            <td class="particular">Other</td>
                            <td class="total"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.totalOp}"/></td>
                            <td class="payable"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableOp}"/></td>
                            <td class="disburse"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.disburseOp}" id="opOld" name="opOld"/></td>
                            <td class="due"><input readonly style="width: 90px; text-align: right; font-size: 18px" type="text" value="${pdb.payableOp-pdb.disburseOp}"/>
                            <td class="adjust"><input style="width: 90px; text-align: right; font-size: 18px" value="0" class="adjust" type="text" name="opNew" id="opNew" onkeyup="calculate();"/></td>
                        </tr>


                    </table>
                   <br/>
                    <table class="disburseAmount">
                        <tr>
                            <td class="particular">Paid Total</td>
                            <td><input readonly style="width: 100px; text-align: right; font-size: 18px" type="text" value="${pdb.paidTotal}" id="paid" name="paid"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="particular">Disburse Total</td>
                            <td><input readonly style="width: 100px; text-align: right; font-size: 18px; color: green" type="text" value="${pdb.disbursedTotal}" id="disburse" name="disburse" onkeyup="calculate();"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="particular">Rest Total</td>
                            <td><input readonly style="width: 100px; text-align: right; font-size: 18px; color: red" type="text" name="rest" id="rest"/></td>
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
            document.getElementById("msg").innerHTML = "<a id='userCreate' class='button icon approve' onclick='document.dataForm.submit()'>Update Disbursement</a>";
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

            var otherNew = document.getElementById("opNew").value;
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
        .disburse{font-family: calibri;  font-size: 18px}
        th{text-align:center;  font-size: 18px}
        .particular{width:200px;  font-size: 18px}
    </style>

</div>
<#--End widget div-->

</@layout.gen_app_layout>