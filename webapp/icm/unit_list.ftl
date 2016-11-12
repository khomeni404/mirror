<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="icmHome.erp">ICM Config Home</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="itemList.erp" method="GET">
            <a class="print-link no-print" href="createItem.erp" style="position: absolute;"><img
                    alt="Print"
                    src="../resources/images/common/add.png"/></a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="findFor" id="findFor" placeholder="type text" value="" style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/common/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/common/pdf2.png"/></a>
        </form>
        <div id="printDiv" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th>Unit Name</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                    <#list units as unit>
                        <tr>
                            <td class="name">${unit.name}</td>
                            <td class="cat">${unit.description}</td>
                            <td class="action"><a href="deleteUnit.erp?cid=${unit.id}" id="id" name="id"><img title='Edit' src='../resources/images/csd/moneyDisburse.png'/></a> </td>
                        </tr>
                    </#list>
                </table>
            </div>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $("#findFor").keyup(function (event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if (id.toUpperCase() != "ALL" || id.toUpperCase() != "ALL") {
                    document.getElementById("errorMsg").innerHTML = "Please insert ???";
                } else {
                    document.itemForm.submit();
                }
            }
        });
        $(document).ready(function () {
            $("#findFor").focus();
        });

        function openHelp() {
            var content = "Please do any one of the following to get result<hr/>"+
                    "To get result for all type <b>'all'</b> and press Enter<br/>"+
                    "To get result for pending type <b>'not'</b> and press Enter<br/>"+
                    "To get result for individual type<b> 'cid'</b> like <b> '12'</b> and press Enter<br/>";
            top.helpPan = window.open('','','width=500, height=250, top=270, left=520, ')
            top.helpPan.document.writeln(
                    '<html><head><title>Help</title></head>'
                            +'<body bgcolor=white onLoad="self.focus()" style="font-family: calibri; color: green">'
                            +content
                            +'</body></html>'
            )
            top.helpPan.document.close()
        }

    </script>

    <style>
        table, hr {
            margin-left: 50px;
            margin-right: 50px;
        }

        table, th, td {
            border-collapse: collapse;
            font-size: 13px;
            padding: 3px;
        }

        th {
            text-align: left;
            font-weight: bold;
            color: green;
            background-color: #b9b9b9;
        }
        .cat{
            width: 140px;
        }
        .name {
            width: 120px;
        }
        .unit {
            width: 60px;
        }
        .desc{width: 200;}
        .price{width: 130;}
        .action{
            width: 100px;
            text-align: center;
        }
        ul, li{
            list-style: upper-alpha;
        }
    </style>
</div>

</@layout.gen_app_layout>