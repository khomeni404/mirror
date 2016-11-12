<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li><a href="#">Item In</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="itemUsageHistoryList.erp" method="GET">
            <input type="text" name="isVerified" id="isVerified" placeholder="No | Yes" value="" style="font-size: 15px"/>
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
                        <th class="user">Request By</th>
                        <th class="item">Item Name</th>
                        <th class="qty">Quantity</th>
                        <th class="status">Status</th>
                        <th class="note">Note</th>
                        <th class="verified">Verified ?</th>
                        <th class="action">Actions</th>
                    </tr>
                    <#list histories as history>
                        <#if history.verified == isVerified>
                            <tr>
                                <td class="user">${history.owner.token.name} </td>
                                <td class="item">${history.item.name} </td>
                                <td class="qty">${history.quantity} ${history.item.unit.name}</td>
                                <td class="status">${history.status}</td>
                                <td class="note">${history.note}</td>
                                <td class="verified">${history.verified}</td>
                                <td class="action">
                                    <a href='#'><img title='View' src='../resources/images/icm/check_out.png'/></a>
                                    &nbsp;&nbsp;
                                    <a href='updateItemUsageHistory.erp?id=${history.id}&isVerified=Yes'><img title='View' src='../resources/images/icm/ok.png'/></a>
                                </td>
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
            $("#isVerified").focus();
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
        .qty{
            width: 70px;
        }
        .user{
            width: 210px;
        }
        .item {
            width: 120px;
        }
        .status{width: 80px}
        .note{width: 200;}
        .action{
            width: 150px;
            text-align: center;
        }
        .verified{width: 90px}
    </style>
</div>

</@layout.gen_app_layout>