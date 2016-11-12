<#import "../../gen_template/gen_app_layout.ftl" as layout>
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
        <span><span class="ico gray user"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="cidForm" id="cidForm" action="viewPaymentSummaryAll.erp" method="GET">
            <input type="text" name="range" id="range" placeholder="0-10000000"/>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img  alt="Print"
                    src="../../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img  alt="Pdf" src="../../resources/images/csd/pdf.png"/></a>
        </form>
        <div id="ele1" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
            <h2><label style="color: #1c0eff">${reportHead}</label></h2>
            <table>
                <tr style="font-weight: bold; color: #0200ff">
                    <th class="cid">MID</th>
                    <th class="cid">CID</th>
                    <th class="name">Customer Name</th>
                    <th class="reference">References</th>
                    <th class="reference">Date</th>
                </tr>
                <#list customers as customer>
                <tr class="${customer.CID}">
                    <td class="cid">${customer.referenceBy.mid}</td>
                    <td class="cid">${customer.CID}</td>
                    <td class="name">${customer.name}</td>
                    <td class="references">${customer.referenceBy.name}</td>

                    <td class="">${customer.bookingDate}</td>
                </tr>
                </#list>
            </table>
            </div>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span> </div>
        </div>
    </div>
    <script type="text/javascript">
        function submitForm() {
            document.dataForm.submit();
        }


        $("#range").keyup(function (event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if (id == "") {
                    document.getElementById("errorMsg").innerHTML = "Please insert CID";
                } else if (id.length > 3) {
                    document.getElementById("errorMsg").innerHTML = "CID too long !";
                } else if (id.length < 3) {
                    document.getElementById("errorMsg").innerHTML = "CID too short !";
                } else if (id.length == 3) {
                    document.cidForm.submit();
                }
            }
        });
        $(document).ready(function () {
            $("#range").focus();
        });

    </script>

    <style>
        table, hr {
            margin-left: 100px;
            margin-right: 50px;
        }

        table, th, td {
            border: 1px dotted black;
            border-collapse: collapse;
            padding: 3px;
            font-size: 18px;
        }

        .name {
            width: 300px;
        }

        th {
            text-align: center;
            font-weight: bold;
            color: green;
            background-color: #b9b9b9;
        }


        .cid {
            font-weight: bold;
            color: green;
            text-align: center;
            width: 140px;
        }

        .references{
            text-align: left;
            width: 300px;
        }

    </style>
</div>

</@layout.gen_app_layout>