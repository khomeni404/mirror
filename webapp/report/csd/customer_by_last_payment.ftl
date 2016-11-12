<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >

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

                table tr td {
                    font-size: 13px
                }
                .inner-table{text-align: left}
                .inner-table table{border:0 solid;}
                .inner-table table tr td{
                    font-size: 17px;
                    border: 0 solid;
                }
            </style>
            <div style="min-height: 900px">
                <table style="margin-left: 20px; ">
                    <tr>
                        <th>SL</th>
                        <th style="width: 100px">CID</th>
                        <th>Customer Name</th>
                        <th>Reference Name</th>
                        <th>Last Pay Date</th>
                    </tr>
                    <#assign sl = 1>
                    <#list dataList as data>
                        <tr>
                            <td class="sl">${sl!}</td>
                            <td style="text-align: center">${data.CID!}</td>
                            <td>${data.NAME!}</td>
                            <td>${data.REF_NAME!}</td>
                            <td>${data.LAST_DATE!}</td>
                        </tr>
                        <#assign sl = sl+ 1>
                    </#list>

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

<#--
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
</script>-->
