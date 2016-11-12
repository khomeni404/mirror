<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="#">Item In</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="itemInList.erp" method="GET">
            <a class="print-link no-print" href="createItemOut.erp" style="position: absolute;"><img
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
                <table style="1px">
                    <tr style="font-weight: bold; color: #0200ff">
                        <th>Date</th>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>From</th>
                        <th>For</th>
                        <th>Out By</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <#list itemOuts as itemOut>
                        <tr>
                            <td class="date">${itemOut.dateOut}</td>
                            <td class="item">${itemOut.item.name} </td>
                            <td class="qty">${itemOut.qty} ${itemOut.item.unit.name}</td>
                            <td class="store">${itemOut.from.name}</td>
                            <td class="user">${itemOut.outFor.token.name}</td>
                            <td class="user">${itemOut.outBy.token.name}</td>
                            <td class="status">${itemOut.status}</td>
                            <#--<td class="operator">${itemOut.outBy.token.name}</td>-->

                            <!--If Item status "RECEIVED" action button can't be displayed-->
                            <td class="action"><a href="deleteItem.erp?cid=${itemOut.id}" id="id" name="id"><img title='Edit' src='../resources/images/common/edit.png'/></a> </td>
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
            margin-right: 30px;
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
        .qty, .date, .store{
            width: 80px;
        }
        .item {
            width: 120px;
        }
        .user{width: 190;}
        .action{
            width: 50px;
            text-align: center;
        }
        .status{width:90px}
    </style>
</div>

</@layout.gen_app_layout>