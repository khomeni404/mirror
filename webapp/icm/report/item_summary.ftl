<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="icmHome.erp">ICM Home</a></li>
        <li><a href="#">Item Status</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="itemInList.erp" method="GET">
            <a class="print-link no-print" href="createItemIn.erp" style="position: absolute;"><img
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
                        <th>Sl.No.</th>
                        <th>Item Name</th>
                        <th>Total In</th>
                        <th><a href="/mirror/icm/internalRequisitionList.erp?status=Requested" original-title="Request List">Request</a></th>
                        <th><a href="/mirror/icm/viewWaitingItemList.erp?listFor=all" original-title="Accept List">Accepted</a></th>
                        <th>Total Out</th>
                        <th>Available</th>
                    </tr>
                    <#assign x = 1>
                    <#list itemSummarises as itemSummary>
                        <#if x%2==0><tr class="even"></#if>
                        <#if x%2!=0><tr class="odd"></#if>
                        <td class="sl">${x}</td>
                            <td class="item">${itemSummary.itemName} </td>
                            <td class="qty">${itemSummary.totalIn} ${itemSummary.unitName}</td>
                            <td class="qty <#if itemSummary.requested == 0 >green <#else >blink red</#if>">${itemSummary.requested} ${itemSummary.unitName}</td>
                            <td class="qty">${itemSummary.accepted} ${itemSummary.unitName}</td>
                            <td class="qty">${itemSummary.totalOut} ${itemSummary.unitName}</td>
                            <td class="qty <#if itemSummary.available == 0 >red blink<#else > green</#if>">${itemSummary.available} ${itemSummary.unitName}</td>
                        </tr>
                        <#assign x = x + 1>
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
        tr{height:30px}
        table, th, td {
            border-collapse: collapse;
            font-size: 13px;
            padding: 3px;
        }
        .sl{width: 10px; text-align: center}
        .even{background-color: #d3d3d3
        }
        .odd{background-color: #f9e3b7
        }
        th {
            text-align: left;
            font-weight: bold;
            color: green;
            background-color: #b9b9b9;
        }
        .qty{
            width: 140px;
        }
        .item {
            width: 180px;
        }
        .red{color: red; font-weight: bold; font-size: 15px}
        .green{color: green; font-weight: bold; font-size: 15px}
    </style>
</div>

</@layout.gen_app_layout>