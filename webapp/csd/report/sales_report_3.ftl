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
        <form name="cidForm" id="cidForm" action="chequeListNotPassed.erp" method="GET">
            <input type="text" name="status" id="findFor" placeholder="N | Y | X " value="" style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#findFor").focus();
                });</script>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>
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
                            <h2><label style="color: #1c0eff">Sales Summary : Categorized by Sales Person</label></h2>
                        </td>
                    </tr>
                </table>
                <br/><br/><br/>
            </div>
            <div style="min-height: 1250px">

                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 130px">Sl.</th>
                        <th style="width: 400px">Sales Person Category</th>
                        <th style="width: 300px">Sales</th>
                    </tr>
                    <tr>
                        <td class="sl">1.</td>
                        <td>Director</td>
                        <td class="nos">${personSales.director}</td>
                    </tr>
                    <tr>
                        <td class="sl">2.</td>
                        <td>Share Holder</td>
                        <td class="nos">${personSales.shareHolder}</td>
                    </tr>
                    <tr>
                        <td class="sl">3.</td>
                        <td>Marketing</td>
                        <td class="nos">${personSales.mkt}</td>
                    </tr>
                    <tr>
                        <td class="sl">4.</td>
                        <td>Foreign Marketing Team</td>
                        <td class="nos">${personSales.fmt}</td>
                    </tr>
                    <tr>
                        <td class="sl">5.</td>
                        <td>Undefined</td>
                        <td class="nos">${personSales.others}</td>
                    </tr>
                    <tr>
                        <td colspan="2">Total</td>
                        <td class="nos total">
                        ${personSales.director+ personSales.shareHolder+  personSales.mkt+ personSales.fmt+personSales.others}
                        </td>
                    </tr>

                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="http:\\www.soft-engine.net"
                                                                      original-title="Soft Engine">Soft
                Engine</a> </span>
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