<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="#">CSD Home</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="cidForm" id="cidForm" action="viewMoneyDisburse.erp" method="GET">
            <input type="text" name="findFor" id="findFor" placeholder="0-10000000" value=""/>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../../resources/images/csd/pdf.png"/></a>
        </form>
        <div id="ele1" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <h2><label style="color: #1c0eff">${reportHead}</label></h2>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th>CID</th>
                        <th>Paid Total</th>
                        <th>Disburse Total</th>
                        <th>Not Disbursed</th>
                        <th>Actions</th>
                    </tr>
                    <#list disburses as disburse>
                        <tr class="">
                            <td class="cid">${disburse.cid}</td>
                            <td class="dp">${disburse.paidTotal}</td>
                            <td class="sp">${disburse.disburseTotal}</td>
                            <td class="cp">${disburse.rest}</td>
                            <td><a href="editMoneyDisburse.erp?cid=${disburse.cid}" id="" name="">Edit</a> </td>
                        </tr>
                    </#list>
                </table>
            </div>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function submitForm() {
            document.dataForm.submit();
        }


        $("#findFor").keyup(function (event) {
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
            $("#findFor").focus();
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
            padding-left: 5px;
            padding-right: 5px;
            padding-top: 1px;
            padding-bottom: 1px;
            font-size: 14px;
        }

        .name {
            width: 400px;
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

        .amount {
            width: 120px;
            text-align: right;
        }

        .Approved {
            color: #170AFE;
            font-weight: bold;
        }

        .Cancelled {
            color: #ff0000;
        }

    </style>
</div>

</@layout.gen_app_layout>