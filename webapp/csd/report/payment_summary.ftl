<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<input type="hidden" value="125" id="cidd" name="cidd">
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="#">CSD Home</a></li>
        <li><a  href="paymentHome.erp">Payment Home</a></li>
        <li><a  href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="cidForm" id="cidForm" action="findPaymentSummary.erp" method="GET">
            <input type="text" name="cid" id="cid" placeholder="CID : e.g. 012, 123 (Press Enter)"/>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img src="../../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img src="../../resources/images/csd/pdf.png"/></a>
        </form>

        <div id="ele1" class="content scaffold-list" role="main">
            <h3><label style="color: #1c0eff">${cid}</label> : <label style="color: green">${customerName}</label></h3>
            <table>
                 <tr><td style="width:200px">Floor Price:</td><td style="text-align: right">${floorPrice}</td></tr>
                 <tr><td>Deposited:</td><td style="text-align: right">${grandTotal}</td></tr>
                 <tr><td>Rest Amount:</td><td style="text-align: right">${restAmount}</td></tr>
            </table>

            <br class="clear"/><br class="clear"/><br/>
            <table>
                <tr style="font-weight: bold; color: #0200ff">
                    <td style="width:100px">Particular</td><td style="width:100px; text-align: right">Debt</td>  <td style="width:100px; text-align: right">Credit</td><td style="width:100px; text-align: right">Total</td>
                </tr>

                <tr><th>Regular Deposit</th>    <th style="text-align: right">---</th>    <th style="text-align: right">${deposit}</th><th style="text-align: right">${deposit}</th></tr>
                <tr><td>Adjustment</td>         <td style="text-align: right">${adjustmentDr}</td>       <td style="text-align: right">${adjustmentCr}</td><td style="text-align: right">${totalAdjustment}</td></tr>
                <tr><td>Withdraw</td>           <td style="text-align: right">${withdraw}</td>    <td style="text-align: right">---</td><td style="text-align: right">${withdraw}</td></tr>
                <tr><td>Grand Total = </td>     <td style="text-align: right; font-weight: bold">${totalDr}</td>    <td style="text-align: right; font-weight: bold">${totalCr}</td><td style="text-align: right; font-weight: bold; color:green;">${grandTotal}</td></tr>
            </table>
             <!-- Here the data will be showed from java script file according to div id -->

        </div>
    </div>
    <script type="text/javascript">
        function submitForm(){
            document.dataForm.submit();
        }


        $("#cid").keyup(function(event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if(id == ""){
                    document.getElementById("errorMsg").innerHTML = "Please insert CID"
                }else if(id.length > 3){
                    document.getElementById("errorMsg").innerHTML = "CID too long !"
                }else if(id.length < 3){
                    document.getElementById("errorMsg").innerHTML = "CID too short !"
                }else if(id.length == 3){
                    document.cidForm.submit();
                }
            }
        });
        $( document ).ready(function() {
            $( "#cid" ).focus();
        });
        /*
        Ext.onReady(function(){
            $( "#cid" ).focus();
        })  */
    </script>

    <style>
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;padding:5px;
        }
    </style>
</div>

</@layout.gen_app_layout>