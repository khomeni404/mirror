<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a href="reportHome.erp">Report Home</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="cidForm" id="cidForm" action="moneyDisburseList.erp" method="GET">
            <input type="text" name="findFor" id="findFor" placeholder="not | all | CID" value=""
                   style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>
        <div id="ele1" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <h3><label style="color: #1c0eff">Voucher List</label></h3>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th class="vrNo">Voucher No</th>
                        <th class="a">Date</th>
                        <th class="b">Type</th>
                        <th class="a">From</th>
                        <th class="a">To</th>
                        <th class="b">Status</th>
                        <th class="b">Amount</th>
                        <th class="">Note</th>
                       <#-- <th class="action">Edit</th>-->
                    </tr>
                    <#assign x = 1>
                    <#list voucherList as voucher>
                        <#assign class = "odd">
                        <#if voucher.voucherNo?starts_with("TR")>
                            <tr class=" not ${class}">
                                <td class="vrNo">${voucher.voucherNo}</td>
                                <td class="a">${voucher.voucherDate}</td>
                                <td class="">Adjustment</td>
                                <td class=""><#if voucher.amount<0 > ${voucher.customer.CID}<#else >---</#if></td>
                                <td class=""><#if voucher.amount<0 >---<#else > ${voucher.customer.CID}</#if></td>
                                <td class="">${voucher.status}</td>
                                <td class=""><#if voucher.amount<0 > ${-voucher.amount}<#else >${voucher.amount}</#if></td>
                                <td class="note">${voucher.note}</td>
                                <#--<td class="action"><a href="editMoneyDisburse.erp?cid=${voucher.id}" id="" name=""><img
                                        title='Edit' src='../resources/images/csd/moneyDisburse.png'/></a></td>-->
                            </tr>
                        <#else >
                        <tr class=" not ${class}" style="color: red;">
                            <td class="vrNo">${voucher.voucherNo}</td>
                            <td class="">${voucher.voucherDate}</td>
                            <td class="">Withdraw</td>
                            <td class="">${voucher.customer.CID}</td>
                            <td class="">---</td>
                            <td class="">${voucher.status}</td>
                            <td class="">${-voucher.amount}</td>
                            <td class="note">${voucher.note}</td>
                            <#--<td class="action"><a href="editMoneyDisburse.erp?cid=${voucher.id}" id="" name=""><img
                                    title='Edit' src='../resources/images/csd/moneyDisburse.png'/></a></td>-->
                        </#if>
                        <#assign x = x + 1>
                    </#list>
                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $("#findFor").keyup(function (event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if (id.toUpperCase() != "ALL" || id.toUpperCase() != "ALL") {
                    document.getElementById("errorMsg").innerHTML = "Please insert ???";
                } else {
                    document.cidForm.submit();
                }
            }
        });
        $(document).ready(function () {
            $("#findFor").focus();
        });

        function openHelp() {
            var content = "Please do any one of the following to get result<hr/>" +
                    "To get result for all type <b>'all'</b> and press Enter<br/>" +
                    "To get result for pending type <b>'not'</b> and press Enter<br/>" +
                    "To get result for individual type<b> 'cid'</b> like <b> '12'</b> and press Enter<br/>";
            top.helpPan = window.open('', '', 'width=500, height=250, top=270, left=520, ')
            top.helpPan.document.writeln(
                    '<html><head><title>Help</title></head>'
                    + '<body bgcolor=white onLoad="self.focus()" style="font-family: calibri; color: green">'
                    + content
                    + '</body></html>'
            )
            top.helpPan.document.close()
        }

    </script>

    <style>
        table, hr {
            margin-left: 10px;
            margin-right: 10px;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            font-size: 15px;
            padding: 3px 3px 3px 15px;
        }

        tr {
            height: 30px
        }

        .even {
            background-color: #d0e3e8
        }

        th {
            text-align: center;
            font-weight: bold;
            color: green;
            background-color: #b9b9b9;
        }

        .vrNo {
            font-weight: bold;
            color: green;
            text-align: left;
            width: 130px;
        }

        .a{
            width: 110px;
        }

        .action {
            width: 100px;
            text-align: center;
        }
    </style>
</div>

</@layout.gen_app_layout>