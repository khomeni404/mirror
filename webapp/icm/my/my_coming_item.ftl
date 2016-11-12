<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li><a href="myIcmHome.erp">My Home</a></li>
        <li><a href="#">Coming Item</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="#" method="GET">
            <a class="print-link no-print" href="viewComingItem.erp" style="position: absolute;"><img
                    alt="Print"
                    src="../resources/images/common/add.png"/></a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="status" id="status" placeholder="N | R | B" value="" style="font-size: 15px"/>
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
                        <th class="item">Item Name</th>
                        <th class="qty">Quantity</th>
                        <th class="name">Out By</th>
                        <th class="date">Out Date</th>
                        <th class="date">Status</th>
                        <th class="action">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Actions</th>
                    </tr>
                    <#list itemOuts as io>
                        <#if io.status == status && io.outFor.authorizeId == userId>
                            <tr>
                                <td class="item">${io.item.name}</td>
                                <td class="qty">${io.qty} ${io.item.unit.name}</td>
                                <td class="name">${io.outBy.token.name}</td>
                                <td class="date">${io.dateOut}</td>
                                <td class="date">${io.status}</td>
                                <#if io.status == "Received">
                                    <td class="action">N/A</td>
                                <#else>
                                    <td class="action"><a href="updateItemOut.erp?ioId=${io.id}&status=Received" id="id" name="id"><img title='Edit' src='../resources/images/icm/receive.png'/></a></td>
                                </#if>
                            </tr>
                        </#if>
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
            $("#status").focus();
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

        .name {
            width: 290px;
        }

        .qty{width: 100;}
        .item{width: 130;}
        .date{width: 90;}
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