<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>


<br class="clear"/>

<div class="widget">

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
                            <h2><label style="color: #1c0eff">Referenced Customer List</label></h2>
                        </td>
                    </tr>
                    <tr>
                        <td class="header">
                            <h2><label style="color: #1c0eff">From ${lastAgm!} to Today</label></h2>
                        </td>
                    </tr>
                </table>
                <br/><br/><br/>
            </div>
            <style>
                #identity-data tr td{
                    border: 1px solid black;
                    border-collapse: collapse;
                    vertical-align: top;
                    padding: 3px 2 3px 10px;
                }
                th{
                    border: 2px solid green;
                    background-color: #d5d5d5;
                    color: #000000;
                }
               /* #customer-data{
                    border: 0px solid black;
                }
                #customer-data tr td{
                    border-bottom: 1px solid black;
                }*/
.inner-td{
    border-top: 0px solid black;
    border-bottom: 1px solid black;
}

                /*table tr th{font-size: 19px}
                table tr td{font-size: 25px}*/
            </style>
            <div style="min-height: 1250px">
                <table id="identity-data">
                    <tr style="font-weight: bold; color: #0200ff">
                        <th style="width: 50px">SL</th>
                        <th style="width: 80px">MID</th>
                        <th style="width: 150px">Name</th>
                        <th style="width: 50px">Sales</th>
                        <th style="">Customer</th>
                    </tr>
                    <#assign sl = 1>
                    <#assign Sales = 0>
                    <#assign SalesValue = 0>
                    <#assign Collected = 0>
                    <#list dataList as identity>

                        <tr>
                            <td class="sl">${sl}</td>
                            <td>${identity.mid}</td>
                            <td>${identity.name}</td>
                            <#assign customerList = identity.getCustomersReferred()>
                            <#assign sales = customerList?size>

                            <td> ${sales}</td>
                            <td>

                                <table id="customer-data" border="0">
                                        <#list customerList as customer>
                                            <tr style="color: <#if customer.status = 'Refunded'>red</#if>">
                                                <td width="125" class="inner-td">${customer.CID!}</td>
                                                <td width="500" class="inner-td">${customer.name!}</td>
                                                <td width="140" class="inner-td">${customer.bookingDate!}</td>
                                                <td width="100" class="inner-td">${customer.status!}</td>
                                            </tr>
                                        </#list>
                                </table>
                            </td>


                        </tr>
                        <#assign sl = sl + 1>
                    </#list>


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