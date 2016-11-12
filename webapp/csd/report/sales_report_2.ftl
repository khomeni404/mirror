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
            font-size: 18px;
            padding: 9px;
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
                    <#--<td class="header">
                            <h2><label style="color: #1c0eff">Sales Status</label></h2>
                        </td>-->
                    </tr>
                </table>
                <br/><br/><br/>
            </div>
            <div style="min-height: 1250px">
                <h3>Sales by Status</h3>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 130px">Sl.</th>
                        <th style="width: 400px">Status</th>
                        <th style="width: 300px">Sales</th>
                        <th style="width: 300px">After Last BOD Meeting<br/><small style="font-weight: normal; color: black">(${lastBodMeetingDate})</small></th>
                    </tr>
                    <#assign ssl = 1>
                    <#assign stTotal = 0>
                    <#assign stBodTotal = 0>
                    <#list statusSales as status>
                        <#if status.name !="Refunded">
                            <tr>
                                <td class="sl">${ssl}</td>
                                <td>${status.name}</td>
                                <td class="nos">${status.sales}</td>
                                <td class="nos">${status.bod}</td>
                            </tr>
                            <#assign ssl = ssl + 1>
                            <#assign stTotal = stTotal+ status.sales>
                            <#assign stBodTotal = stBodTotal + status.bod>
                        </#if>
                    </#list>
                    <tr>
                        <td colspan="2">Total</td>
                        <td class="nos total">${stTotal}</td>
                        <td class="nos">${stBodTotal}</td>
                    </tr>
                </table>
            <#-- </div>-->

                <br/>
                <br/>

            <#-- <div style="min-height: 1250px">-->
                <h3>Sales by Offer</h3>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 130px">Sl.</th>
                        <th style="width: 400px">Offer Name</th>
                        <th style="width: 300px">Number of Sales</th>
                        <th style="width: 300px">After Last BOD Meeting<br/><small style="font-weight: normal; color: black">(${lastBodMeetingDate})</small></th>
                    </tr>
                    <#assign osl = 1>
                    <#assign ofTotal = 0>
                    <#assign ofBodTotal = 0>
                    <#list offerSales as offer>
                        <tr>
                            <td class="sl">${osl}</td>
                            <td>${offer.offerName}</td>
                            <td class="nos">${offer.sales}</td>
                            <td class="nos">${offer.bod}</td>
                        </tr>
                        <#assign osl = osl + 1>
                        <#assign ofTotal = ofTotal+ offer.sales>
                        <#assign ofBodTotal = ofBodTotal + offer.bod>
                    </#list>
                    <tr>
                        <td colspan="2">Total</td>
                        <td class="nos total">${ofTotal}</td>
                        <td class="nos">${ofBodTotal}</td>
                    </tr>
                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>

    <style type="text/css">
        .sl {
            text-align: center;
        }
        .nos {
            text-align: right;
            padding-right: 76px;
        }
        .total{
            background-color: #cecece;
        }

        .statusOfPayment tr td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 3 20px 3 10px;
            font-size: 14px;
            height: 30px;
        }

        td {
            width: 200px;
            font-weight: bold;
        }

        th {
            text-align: center;
            font-weight: bold;
            color: green;
            border: 1px solid #000000;
        }

        .promo1 td {
            padding: 4px 4px 4px 10px
        }

        .promo2 tr td {
            border: 0px;
            padding: 3 20px 3 10px
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