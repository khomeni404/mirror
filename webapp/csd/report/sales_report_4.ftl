<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>


<br class="clear"/>

<div class="widget">
    <style>

        table, hr {
            margin-left: 30px;
            margin-right: 10px;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            font-size: 15px;
            padding: 2px;
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

    </style>
    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">

        <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                alt="Print"
                src="../resources/images/csd/printer2.png"/></a>
        <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                alt="Pdf"
                src="../resources/images/csd/pdf.png"/></a>

        <div id="ele1" class="content scaffold-list" role="main">
            <div class="headerDiv" id="headerDiv">
                <br/><br/><br/>
                <table class="headTable" id="headTable" style="border: 0px">
                    <tr>
                        <td class="CompanyName">D<span style="color: #c04c05">O</span>RPAN Properties Limited</td>
                    </tr>
                    <tr>
                        <td class="address">Flat-5A, House-30, Sonargaon Janapath, Sector-11 Chowrasta, Uttara, Dhaka-1230.</td>
                    </tr>
                    <tr>
                        <td class="address">Ph: 0289 58752, cs.dorpan@gmail.com</td>
                    </tr>
                    <tr>
                        <td class="header">
                            <h2><label style="color: #1c0eff">Sales Summary with due and overdue status</label></h2>
                        </td>
                    </tr>
                </table>
                <br/><br/><br/>
            </div>
            <div style="min-height: 1250px">
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 130px">CID</th>
                        <th style="width: 800px">Customer Name</th>						
                        <th style="width: 50px">Offer</th>
                        <th style="width: 50px">Mobile</th>
                        <th style="width: 50px">Total Price</th>
                        <th style="width: 50px">Paid</th>
                        <th style="width: 50px">Due</th>	
                        <th style="width: 50px">Due Inst.</th>
                        <th style="width: 50px">Tot. Overdue</th>
                    </tr>
                    <#assign sl = 1>
                    <#assign paidGrant = 0>
                    <#assign dueGrant = 0>
                    <#assign overdueGrant = 0>
                    <#list dataList as data>
                        <#if data.customer.status = "Cancelled" ||   data.customer.status = "Refunded">
                        <#else >
                            <tr>
                                <td class="sl">${data.customer.CID}</td>
                                <td>${data.customer.name}</td>
                                <td>${data.customer.offer.offerName}</td>
                                <td>${data.customer.cellPhone}</td>
                                <td class="nos">${data.price?string["0"]}</td>
                                <td class="nos">
                                    <a href="/mirror/csd/viewPaymentStatement.erp?cid=${data.customer.CID}">
                                    ${data.paid}
                                    </a>
                                </td>
                                <td class="nos"><a href="/mirror/csd/viewPaymentReport.erp?cid=${data.customer.CID}">${(data.price - data.paid)?string["0"]}</a></td>
                                <td  class="nos"><#if data.dueInst?string = ""><#else > ${data.dueInst?string["0.00"]}</#if></td>
                                <#assign overDue = data.instDueAmount + data.otherDueAmount - data.paid>
                                <td class="nos">
                                    <a href="/mirror/csd/viewCustomer.erp?cidView=${data.customer.CID}">
                                        <#if overDue <0>(Adv. ${(-overDue)?string["0"]})<#else >${overDue?string["0"]}</#if>
                                    </a>
                                </td>
                            </tr>
                            <#assign sl = sl + 1>
                            <#assign paidGrant = paidGrant+ data.paid>
                            <#assign dueGrant = dueGrant + (data.price - data.paid)>
                            <#assign overdueGrant = overdueGrant + (data.instDueAmount + data.otherDueAmount - data.paid)>
                        </#if>
                    </#list>
                    <tr>
                        <td colspan="6">Total</td>
                        <td class="nos total">${paidGrant?string["0"]}</td>
                        <td class="nos">${dueGrant?string["0"]}</td>
                        <td class="nos">${overdueGrant?string["0"]}</td>
                    </tr>
                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>

    <style type="text/css">
        a{ color: inherit; text-decoration: none;}
        .sl {
            text-align: center;
        }
        .nos {
            text-align: right;
            padding-right: 7px;
        }
        .total{
            background-color: #cecece;
        }

       

        .headTable tr td {
            margin-left: 0px;
            border: 0px solid #000000;
        }

        .CompanyName {
            width: 1000px;
            text-align: center;
            font-size: 30px;
            font-weight: bold
        }

        .address {
            width: 1000px;
            text-align: center;
            font-size: 14px;
            font-weight: bold
        }

        .header {
            width: 1000px;
            text-align: center;
            font-size: 20px;
            font-weight: bold
        }

        hr {
            border: 1px double #000000
        }
    </style>


</div>

</@layout.gen_app_layout>