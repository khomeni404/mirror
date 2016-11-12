<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

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
        <form name="cidForm" id="cidForm" action="findOtherPayment.erp" method="GET">
            <a href="createOtherPayment.erp" class="button icon approve">Add Other Payment</a>
            <a href="createInstallment.erp" class="button icon approve">Add Installment</a>
            &nbsp;&nbsp;&nbsp;
            <input type="text" name="cid" id="cid" placeholder="CID : e.g. 012, 123 (Press Enter)"/>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 85%;"><img alt="Print"
            src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 87%;"><img
                    alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>

        <form name="dataForm" id="dataForm" action="findOtherPayment.erp" method="POST">
            <fieldset class="form">

                <div id="ele1" class="container_16">
                    <h4>${cid} : ${name}</h4>
                    <table>
                        <tr>
                            <th class="a">Particular</th>
                            <th class="b" style="text-align: right">Amount</th>
                            <th class="c" style="text-align: center">Dead Line</th>
                            <th class="d">Note</th>
                            <th class="e">Action</th>
                        </tr>
                        <#list otherPayments as op>
                            <tr class="">
                                <td class="a">${op.paymentType} (${op.paymentName})</td>
                                <td class="b">${op.amount}</td>
                                <td class="c">${op.deadLine}</td>
                                <td class="d">${op.note}</td>
                                <td class="e"><a href='deleteOtherPayment.erp?id=${op.id}'><img title='Delete' src='../resources/images/delete.png'/></a></td>
                            </tr>
                        </#list>
                        <#assign x = 1/>
                        <#list instInfo as ii>
                            <#if ii.start??>
                                <tr class="" style="background-color: #dcdcdc;">
                                    <td class="a">Installment (Phase-${ii.set}) : ${ii.instNumber} Installment</td>
                                    <td class="b">${ii.amount} / <#if ii.months != 1>${ii.months}</#if> month</td>
                                    <td class="c">Every 1-5 th day</td>
                                    <td class="d">
                                        <#if ii.start = ii.end>
                                        ${ii.start}
                                        </#if>
                                        <#if ii.start != ii.end>
                                        ${ii.start} - ${ii.end}
                                        </#if>
                                    </td>
                                    <#if x == 1>
                                        <td class="e"><a href='deleteInstallment.erp?forWhat=all&id=${id}'><img title='Delete All Installment' src='../resources/images/delete_all.png'/></a></td>
                                    <#else>
                                        <td class="e"></td>
                                    </#if>
                                <#assign x = x+1/>
                                </tr>
                            </#if>
                        </#list>
                        <#list installments as ins>
                            <tr class="">
                                <td class="a">${ins.installmentName}</td>
                                <td class="b">${ins.amount}</td>
                                <td class="c">${ins.deadLine}</td>
                                <td class="d">${ins.installmentOf}</td>
                                <td class="e"><a href='deleteInstallment.erp?forWhat=one&id=${ins.id}'><img title='Delete' src='../resources/images/delete.png'/></a></td>
                            </tr>
                        </#list>

                    </table>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->
    <style>
        table{margin-left: 30px}
        table, tr, td{border: 0px solid black; border-collapse: collapse; padding: 9px 4px 9px 4px; font-size: 14px}
        th{padding: 3px 4px 3px 4px; color: #060606; background-color: #6f6361; font-weight: bold}
        .a{width: 252px}
        .b{width: 116; text-align: right; font-weight: bold}
        .c{width: 150; font-weight: bold; text-align: center}
        .d{width: 242px}
        .e{width: 100px}
        .header{width: 1000px; text-align: center; font-size: 20px; font-weight: bold}
        hr{border: 1px double #000000}
    </style>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#cid").focus();
        });

    </script>

</div>
<#--End widget div-->

</@layout.gen_app_layout>
