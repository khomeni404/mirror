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
                            <h2><label style="color: #1c0eff">Customer List : Fully Paid</label></h2>
                        </td>
                    </tr>
                </table>
                <br/><br/><br/>
            </div>
            <div style="min-height: 1250px">
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 5px">SL</th>
                        <th style="width: 130px">CID</th>
                        <th style="width: 800px">Customer Name</th>
                        <th style="width: 50px">Offer</th>
                        <th style="width: 50px">Mobile</th>
                        <th style="width: 50px">Size</th>
                        <th style="width: 50px">Handover</th>
                        <th style="width: 50px">Total Price</th>
                        <th style="width: 50px">Paid</th>
                    </tr>
                    <#assign sl = 1>
                    <#assign priceGrand = 0>
                    <#assign paidGrand = 0>
                    <#list dataList as data>
                        <#if data.customer.status = "Cancelled" ||   data.customer.status = "Refunded">
                        <#else >
                            <tr>
                                <td>${(sl)!}</td>
                                <td>${(data.customer.CID?replace(" ", ""))!}</td>
                                <td>${(data.customer.name)!}</td>
                                <td>${(data.customer.offer.offerName)!}</td>
                                <td>${(data.customer.cellPhone)!}</td>
                                <td>${(data.customer.floorSize)!}</td>
                                <td>${(data.customer.handoverDate?string('yyyy'))!}</td>
                                <td class="nos">${data.price?string["0"]}</td>
                                <td class="nos">
                                    <a href="/mirror/csd/viewPaymentStatement.erp?cid=${data.customer.CID}">
                                    ${data.paid}
                                    </a>
                                </td>
                            </tr>

                            <#assign priceGrand = priceGrand +(data.price)!0.0>
                            <#assign paidGrand = paidGrand +(data.paid)!0.0>
                            <#assign sl = 1+sl>
                        </#if>
                    </#list>
                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
</div>

</@layout.gen_app_layout>

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