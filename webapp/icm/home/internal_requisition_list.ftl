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
        <form name="itemForm" id="cidForm" action="#" method="GET">
            <a class="print-link no-print" href="createInternalRequisition.erp" style="position: absolute;"><img
                    alt="Print"
                    src="../resources/images/common/add.png"/></a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="status" id="status" placeholder="A | R | anything" value="" style="font-size: 15px"/>
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
                        <th class="date">Request on</th>
                        <th class="check">Request for</th>
                        <th class="operator">Status</th>
                        <th class="requestBy">Request by</th>
                        <th class="action">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Detail</th>
                    </tr>
                    <#list irs as ir>
                        <#if ir.status == status>
                            <tr>
                                <td class="item">${ir.item.name} </td>
                                <td class="qty">${ir.qty} ${ir.item.unit.name}</td>
                                <td class="date">${ir.requestDate}</td>
                                <td class="check">${ir.note}</td>
                                <td class="operator">${ir.status}</td>
                                <td class="requestBy">${ir.requestBy.token.name}</td>
                                <#if status != "Requested">
                                    <td class="action">

                                    </td>
                                <#else >
                                    <td class="action">
                                        <a href='viewStatusOfRequestedItem.erp?itemId=${ir.item.id}&userId=${ir.requestBy.authorizeId}&reqQty=${ir.qty}&reqId=${ir.id}'><img title='View' src='../resources/images/icm/check_out.png'/></a>
                                        <a href='#'><img title='View' src='../resources/images/icm/ok.png'/></a>
                                    </td>
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
                    "To get result for Accepted List type <b>'a'</b> or <b>'A'</b> and press Enter<br/>"+
                    "To get result for Rejected List type <b>'r'</b> or <b>'R'</b> and press Enter<br/>"+
                    "To get result for Requested List type<b> 'anything'</b> like <b> 'b, c, d, all, etc'</b> and press Enter<br/>";
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
        .date{
            width: 110px;
        }
        .requestBy{
            width: 220px}
        .item {
            width: 120px;
        }
        .check, .operator{width: 150;}
        .action{
            width: 150px;
            text-align: center;
        }
    </style>
</div>

</@layout.gen_app_layout>