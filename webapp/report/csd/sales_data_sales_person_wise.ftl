<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>
<link rel="stylesheet" href="clear.css"/>
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
    #form-table, #form-table tr td {
        border: 0 solid white;
    }
</style>

<br class="clear"/>
<div class="widget">
    <div class="content">

        <form name="reportForm" action="/mirror/report/getSalesDataSalesPersonWise.erp" method="GET">
            <table id="form-table">
                <tr>
                    <td>From:</td>
                    <td><input type="text" name="from" id="from" value="${FROM!}" placeholder="DD/MM/YYYY"
                               style="height: 30px; padding-left: 5px; width: 100%;"/></td>
                    <td>To :</td>
                    <td><input type="text" name="to" id="to" value="${TO!}" placeholder="DD/MM/YYYY"
                               style="height: 30px; padding-left: 5px; width: 100%;"/></td>
                    <td>Type :</td>
                    <td><select name="type" class="reset-this">
                        <option value="${TYPE!}">${TYPE!}</option>
                        <option value="Employee">Employee</option>
                        <option value="Director">Director</option>
                        <option value="Share Holder">Share Holder</option>
                    </select></td>
                    <td></td>
                    <td><a class="button icon approve" style="color: green"
                           onclick="document.reportForm.submit()">Show Report</a></td>
                    <td><a id="userCreate" class="button icon approve" style="color: green"
                           onclick="document.reportForm1.submit()">Print Report</a></td>
                </tr>
            </table>
        </form>
        <form name="reportForm1" action="/mirror/mis/getSalesDataSalesPersonWise.erp" method="GET">
            <input type="hidden" name="from" value="${FROM!}"/>
            <input type="hidden" name="to" value="${TO!}"/>
            <input type="hidden" name="type" value="${TYPE!}"/>
        </form>
    </div>
</div>
<div class="widget">

   <#-- <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>-->

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
                        <td class="address">Flat-5A, House-30, Sonargaon Janapath, Sector-11 Chowrasta, Uttara,
                            Dhaka-1230.
                        </td>
                    </tr>
                    <tr>
                        <td class="address">Ph: 0289 58752, cs.dorpan@gmail.com</td>
                    </tr>
                    <tr>
                        <td class="header">
                            <h2><label style="color: #1c0eff">Sales and Collection Summary</label></h2>
                        </td>
                    </tr>
                    <tr>
                        <td class="header">
                            <h2><label style="font-weight: bold; ">${FROM!} to ${TO!}</label></h2>
                        </td>
                    </tr>
                </table>
                <br/><br/><br/>
            </div>
            <style>
                .amt {
                    text-align: right;
                    padding-right: 10px;
                    font-weight: bold
                }

                .center {
                    text-align: center;
                    font-weight: bold
                }

                /*table tr th{font-size: 19px}
                table tr td{font-size: 25px}*/
            </style>
            <div style="min-height: 1250px">
                <table style="/*margin-left: 160px;*/ ">
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 130px">SL</th>
                        <th style="width: 350px">Name</th>
                        <th style="width: 100px">Total Sales</th>
                        <th style="width: 100px">Collection</th>
                        <th style="width: 150px">Total Collection</th>
                    <#--<th style="width: 150px">Type</th>-->
                    <#--<th style="width: 100px">Sales Value (Crore)</th>-->
                    <#--<th style="width: 100px">Net Collection (Crore)</th>-->
                    </tr>
                    <#assign sl = 1>
                <#--<#assign Sales = 0>-->
                <#--<#assign SalesValue = 0>-->
                <#--<#assign Collected = 0>-->
                    <#list dataList as data>

                        <tr>
                            <td class="sl">${sl}</td>
                            <#--<td>${data.SALES_MAN.mid}</td>-->
                            <td>${data.NAME}</td>
                            <td class="center">${data.SALES}</td>
                        <#--<td style="text-align: center">${data.Sales}</td>-->
                            <td class="amt">${data.COLLECTION}</td>
                            <td class="amt">${data.TOTAL_COLLECTION}</td>
                        <#--<td class="amt">${((data.Collected)/10000000)?string["0.00"]}</td>-->

                        </tr>
                        <#assign sl = sl + 1>
                    <#--<#assign Sales = Sales + data.Sales>-->
                    <#--<#assign Collected = Collected + data.Collected>-->
                    <#--<#assign SalesValue = SalesValue + data.SalesValue>-->
                    </#list>

                    <#--<tr>-->
                        <#--<td class="sl"></td>-->
                        <#--<td></td>-->
                        <#--<td></td>-->
                    <#--<td style="text-align: center">${Sales}</td>-->
                    <#--<td class="amt">${((SalesValue)/10000000)?string["0.00"]}</td>-->
                    <#--<td class="amt">${((Collected)/10000000)?string["0.00"]}</td>-->

                    <#--</tr>-->

                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>

    <style type="text/css">
        a {
            color: inherit;
            text-decoration: none;
        }

        .sl {
            text-align: center;
        }

        .nos {
            text-align: right;
            padding-right: 7px;
        }

        .total {
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

<script>
    $(function () {
        $("#from").datepicker({dateFormat: 'dd/mm/yy', autoclose: true});
        $("#to").datepicker({dateFormat: 'dd/mm/yy', autoclose: true});
    });
</script>