<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<!--end user create div-->
<style type="text/css">
    table{margin-left: 100px}
    table, tr, td{border: 0px solid black; border-collapse: collapse; padding: 9px 4px 9px 4px; font-size: 14px}
    th{padding: 3px 4px 3px 4px; color: #060606; background-color: #6f6361; font-weight: bold}
    .a{width: 232px}
    .b{width: 116px; text-align: right; font-weight: bold}
    .c{width: 150px; font-weight: bold; text-align: center}
    .d{width: 272px}
    .headTable{margin-left: 0}
    .CompanyName{width: 1000px; text-align: center; font-size: 30px; font-weight: bold}
    .address{width: 1000px; text-align: center; font-size: 12px; font-weight: bold}
    .header{width: 1000px; text-align: center; font-size: 20px; font-weight: bold}
    hr{border: 1px double #000000}
</style>

<div class="bread_crumbs_ui_div" style="width: 1000px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="paymentHome.erp">Payment Home</a></li>
        <li><a  href="#">Other Payment</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 1000px">

    <div class="header" style="width: 1000px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 950px; min-height: 600px">
        <form name="cidForm" id="cidForm" action="calculateDelayCharge.erp" method="GET">
            <a href="createOtherPayment.erp" class="button icon approve">Add Other Payment</a>
            <a href="createInstallment.erp" class="button icon approve">Add Installment</a>
            <a href="calculateDelayCharge.erp?cid=${cid}" class="button icon approve">View Delay Charge</a>
            &nbsp;&nbsp;&nbsp;
            <input type="text" name="cid" id="cid" placeholder="CID : e.g. 012, 123 (Press Enter)"/>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 85%;"><img alt="Print"
            src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 87%;"><img
                    alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>

        <form name="dataForm" id="dataForm" action="calculateDelayCharge.erp" method="POST">
            <fieldset class="form">

                <div id="ele1" class="container_16">
                    <table class="headTable">
                        <tr><td class="CompanyName">DORPAN Properties Limited</td></tr>
                        <tr><td class="address">Flat <b>5A</b>, House <b>30</b>, Sonargaon Janapath, Sector <b>11 Chowrasta</b></td></tr>
                        <tr><td class="address">Uttara, Dhaka <b>1230, </b>Ph: <b>0289 58752</td></tr>
                        <tr><td class="header">Delay Charge : Month Wise</td></tr>
                    </table>
                    <hr/><br/><br/><br/><br/><br/>
                    <h4>${cid} : ${name}</h4>
                    <table>
                        <tr>
                            <th>Particular</th>
                            <th style="text-align: right">Amount</th>
                            <th style="text-align: center">Dead Line</th>
                            <th>Total</th>
                        </tr>
                        <#assign totalCharge = 0.0>
                        <#list delayChargeList as dc>
                            <#assign totalCharge = totalCharge+ dc.amount>
                            <tr class="">
                                <td class="a">${dc.chargeOf!}</td>
                                <td class="b">${dc.amount!}</td>
                                <td class="c">${dc.deadLine?string('dd/MM/yyyy')!}</td>
                                <td class="d">${dc.note!}</td>
                            </tr>
                        </#list>
                        <tr>
                            <td colspan="">Total</td>
                            <td class="b">${totalCharge!}</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </form>
    </div>

    <script type="text/javascript">

        $(document).ready(function () {
            $("#cid").focus();
        });

    </script>

</div>
<#--End widget div-->

</@layout.gen_app_layout>
