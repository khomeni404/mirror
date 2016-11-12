<#import "../../gen_template/gen_app_layout.ftl" as layout>
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
        <div id="printDiv" class="content scaffold-list" role="main">
            <label style="font-size: 18px; color: green">${item.name}</label><br/><br/>
            <label style="font-size: 15px; color: blue">${user.token.name}</label>
            <div style="min-height: 1250px">

                <br/><br/>
                <table>
                    <tr class="odd">
                        <td class="big">Requested</td><td class="qty">${requested} ${unitName}</td><td class="action"></td>
                        <td class="tiny"></td>
                        <td class="big">Received</td><td class="qty">${received} ${unitName}</td>
                    </tr>
                    <tr class="even">
                        <td class="big">Accepted</td><td class="qty">${accepted} ${unitName}</td><td class="action"></td>
                        <td class="tiny"></td>
                        <td class="big">Used</td><td class="qty">${used} ${unitName}</td>
                    </tr>
                    <tr class="odd">
                        <td class="big">Rejected</td><td class="qty">${rejected} ${unitName}</td><td class="action"></td>
                        <td class="tiny"></td>
                        <td class="big">Lost</td><td class="qty">${lost} ${unitName}</td>
                    </tr>
                    <tr class="even">
                        <td class="big"></td><td class="qty"></td><td class="action"></td>
                        <td class="tiny"></td>
                        <td class="big">Back</td><td class="qty">${back} ${unitName}</td>
                    </tr>
                    <tr class="odd">
                        <td class="big">New Request</td><td class="qty">${reqQty}</td>
                        <td class="action">
                            <a href='updateInternalRequisition.erp?reqId=${reqId}&status=Accepted'><img title='Accept' src='../resources/images/common/accept.png'/></a>

                            <a href='updateInternalRequisition.erp?reqId=${reqId}&status=Rejected'><img title='Reject' src='../resources/images/common/reject.png'/></a>
                            &nbsp;&nbsp;&nbsp;
                        </td>
                        <td class="tiny"></td>
                        <td class="big">Rest</td><td class="qty">${rest} ${unitName}</td>
                    </tr>
                </table>
            </div>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
        <!--
        <form id="dataForm" class="dataForm" name="dataForm" action="updateInternalRequisition.erp" method="POST">
            <input type="hidden" name="irId" id="irId" value=""
        </form>
        -->
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

    </script>

    <style>
        table, hr {
            margin-left: 50px;
            margin-right: 50px;
        }

        table, th, td {
            border: 0px solid #FFFFFF;
            border-collapse: collapse;
            font-size: 17px;
            padding: 10px;
        }
        .even{background-color: #fee7e9}
        .odd{background-color: #d6eeb1
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
        .big{
            width: 150px
        }
        .tiny{width: 2px; background-color: #ffffff}
        .action{padding: 0px}

    </style>
</div>

</@layout.gen_app_layout>