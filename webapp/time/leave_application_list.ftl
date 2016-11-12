<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="createLeaveApplication.erp">App. Create</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="cidForm" id="cidForm" action="viewLeaveApplication.erp" method="GET">
            <div class="bread_crumbs_ui_div" style="width: 1170px">
                <ul id="crumbs_ui_custom">
                    <li><a href='createLeaveApplication.erp' id="updateApp">+ Create</a></li>
                    <li><input type="text" name="id" id="id" placeholder="Application no."/></li>
                    <li><a href='leaveApplicationList.erp?status=0' id="updateApp">Applied</a></li>
                    <li><a href='leaveApplicationList.erp?status=1' id="updateApp">Proxy OK</a></li>
                    <li><a href='leaveApplicationList.erp?status=2' id="updateApp">Accepted</a></li>
                    <li><a href='leaveApplicationList.erp?status=3' id="updateApp">Received</a></li>
                    <li><a href='leaveApplicationList.erp?status=4' id="updateApp">Approved</a></li>
                    <li><a href='leaveApplicationList.erp?status=5' id="updateApp">Rejected</a></li>
                </ul>

            </div>
        </form>
        <br/>
        <div id="ele1" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <h3><label style="color: #1c0eff"></label></h3>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th class="aNo">App. No.</th>
                        <th class="name">Name</th>
                        <th class="from">From</th>
                        <th class="to">To</th>
                        <th class="days">Days</th>
                        <th class="proxy">Proxy by</th>
                        <th class="status">Status</th>
                        <th class="action" colspan="2">Action</th>
                    </tr>
                    <#assign x = 1>
                    <#list applications as app>
                          <#if app.status == status>
                                <#if x%2==0><tr class=" not even status${app.status}"></#if>
                                <#if x%2!=0><tr class="not odd status${app.status}"></#if>
                                    <td class="aNo">LA - ${app.id}</td>
                                    <td class="name">${app.appliedBy.token.name}</td>
                                    <td class="from">${app.fromDate}</td>
                                    <td class="to">${app.toDate}</td>
                                    <td class="days">${app.days}</td>
                                    <td class="proxy">
                                        <#if app.proxyBy??>${app.proxyBy.token.name}<#else >-------------</#if>
                                    </td>
                                    <td class="status">
                                        <#if app.status==0>
                                            Applied
                                            <#elseif app.status == 1>
                                            Proxy OK
                                            <#elseif app.status == 2>
                                            Accepted
                                            <#elseif app.status == 3>
                                            Received
                                            <#elseif app.status == 4>
                                            Approved
                                            <#else>
                                            Rejected
                                        </#if>
                                    </td>

                                    <td class="action"><a href="viewLeaveApplication.erp?id=${app.id}" id="" name=""><img title='Details' src='../resources/images/details.png'/></a>&nbsp;&nbsp;
                                    <a href="deleteLeaveApplication.erp?id=${app.id}" id="" name=""><img title='Delete' src='../resources/images/delete.png'/></a> </td>
                                <#assign x = x + 1>
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
        $("#status").keyup(function (event) {
            var id = document.getElementById("status").value;
            if (event.keyCode == 13) {
                if (id.toUpperCase() != "ALL" || id.toUpperCase() != "ALL") {
                    document.getElementById("errorMsg").innerHTML = "Please insert ???";
                } else {
                    document.cidForm.submit();
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
            margin-left: 20px;
            margin-right: 30px;
        }
        tr{height:40px}
        table, th, td {
            border: 0px dotted black;
            border-collapse: collapse;
            font-size: 13px;
            padding: 3px;
        }
        .even{background-color: #ededc4
        }
        th {
            padding-left: ;
            text-align: left;
            font-weight: bold;
            color: green;
            background-color: #b9b9b9;
        }

        .days {
            font-weight: bold;
            text-align: left;
            width: 50px;
        }
        .status {
            font-weight: bold;
            text-align: left;
            width: 100px;
        }
        .action{text-align: center}
        .from, .to {
            width: 100px;
            text-align: left;
        }
        .name{width:250; padding-left: 10px}.proxy{width: 200;}
        .action{
            width: 100px;
            text-align: center;
        }
        .aNo{width: 100px; padding-left: 5px; text-align: center}
        .status4{color: green; font-weight: bold}
    </style>
</div>

</@layout.gen_app_layout>