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
                        <td class="address">Flat-5A, House-30, Sonargaon Janapath, Sector-11 Chowrasta, Uttara,
                            Dhaka-1230.
                        </td>
                    </tr>
                    <tr>
                        <td class="address">Ph: 0289 58752, cs.dorpan@gmail.com</td>
                    </tr>
                    <tr>
                        <td class="header">
                            <h2><label style="color: #1c0eff">Sales vs Collection By Year</label></h2>
                        </td>
                    </tr>
                </table>
                <br/><br/><br/>
            </div>
            <style>
                .amt {
                    text-align: right;
                    padding-right: 10px
                }

                table tr th {
                    font-size: 19px
                }

                table tr td {
                    font-size: 25px
                }
                .inner-table{text-align: left}
                .inner-table table{border:0 solid;}
                .inner-table table tr td{
                    font-size: 17px;
                    border: 0 solid;
                }
            </style>
            <div style="min-height: 900px">
                <table style="margin-left: 50px; ">
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 50px">SL</th>
                        <th style="width: 80px">Year</th>
                        <th style="width: 360px">Type : One Time</th>
                        <th style="width: 360px">Type : Installment</th>
                    </tr>
                    <#assign sl = 1>
                    <#assign Sales = 0>
                    <#assign SalesValue = 0>
                    <#assign Collected = 0>
                    <#list dataList as data>

                        <tr>
                            <td class="sl">${sl}</td>
                            <td style="text-align: center">${data.year!}</td>
                            <td class="inner-table">
                                <table>
                                    <tr>
                                        <td width="190">Booking</td>
                                        <td width="10">:</td>
                                        <td width="160">${((data.otBooking))?string["0"]}</td>
                                    </tr>
                                    <tr>
                                        <td>Sales Value </td>
                                        <td>:</td>
                                        <td>${((data.otSalesValue)/10000000)?string["0.00"]} Crore</td>
                                    </tr>
                                    <tr>
                                        <td>Payable Inst. Amt</td>
                                        <td>:</td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>Payable DP Amt.</td>
                                        <td>:</td>
                                        <td>${((data.otPayableDP)/100000)?string["0.00"]} Lakh</td>
                                    </tr>
                                    <tr>
                                        <td>Collected Total</td>
                                        <td>:</td>
                                        <td> ${((data.otCollected)/100000)?string["0.00"]} Lakh</td>
                                    </tr>
                                </table>
                            </td>
                            <td class="inner-table">
                                <table>
                                    <tr>
                                        <td width="190">Booking</td>
                                        <td width="10">:</td>
                                        <td width="160">${((data.instBooking))?string["0"]}</td>
                                    </tr>
                                    <tr>
                                        <td>Sales Value</td>
                                        <td>:</td>
                                        <td>${((data.instSalesValue)/10000000)?string["0.00"]} Crore</td>
                                    </tr>
                                    <tr>
                                        <td>Payable Inst. Amt.</td>
                                        <td>:</td>
                                        <td>${((data.instPayableInst)/100000)?string["0.00"]} Lakh</td>
                                    </tr>
                                    <tr>
                                        <td>Payable DP</td>
                                        <td>:</td>
                                        <td>${((data.instPayableDP)/100000)?string["0.00"]} Lakh</td>
                                    </tr>
                                    <tr>
                                        <td>Collected Total</td>
                                        <td>:</td>
                                        <td> ${((data.instCollected)/100000)?string["0.00"]} Lakh</td>
                                    </tr>
                                </table>
                            </td>

                        </tr>
                        <#assign sl = sl + 1>
                    <#--<#assign Sales = Sales + data.Sales>-->
                    <#--<#assign Collected = Collected + data.Collected>-->
                    <#--<#assign SalesValue = SalesValue + data.SalesValue>-->
                    </#list>
                <#--  <tr>
                      <td class="sl"></td>
                      <td style="text-align: center"></td>
                      <td style="text-align: center">${Sales}</td>
                      <td class="amt">${((SalesValue)/10000000)?string["0.00"]}</td>
                      <td class="amt">${((Collected)/10000000)?string["0.00"]}</td>

                  </tr>-->
                    <tr>
                        <td colspan="4" style="text-align: right; font-size: 14px;">
                            * The report is calculated neglecting 'Refunded' customer<br/>
                            ** All payable amounts depend on the 'Due Date' of payment<br/>
                            *** A payment can be paid before due date too. Therefore, Collected Amt can be greater than Payable Amt
                        </td>
                    </tr>

                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by :
                <span class="tip"><a href="#"  original-title="zero.com.bd">Khomeni,</a>
                </span> Report taken on <b id="today_34"></b>
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
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!

        var yyyy = today.getFullYear();
        if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }
        var today = dd+'/'+mm+'/'+yyyy;
        document.getElementById("today_34").innerHTML = today;
    })
</script>