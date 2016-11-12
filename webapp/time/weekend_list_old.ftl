<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="timeHome.erp">Time Home</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="theDayOffList.erp" method="GET">
            <input type="text" name="lookingFor" id="lookingFor" placeholder="mmYY" value="" style="font-size: 15px"/>
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
                <br/>
                <table>
                    <tr><th style="text-align: right; background-color: honeydew; color: black;">${lookingFor}</th></tr>
                    <tr><th class="headerTitle">Weekend</th></tr>
                </table>
                <br/>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th class="sl">Sl. No.</th>
                        <th class="name">Employee Name</th>
                        <th class="date">Date</th>
                        <th class="note">Note</th>
                        <th class="name">Approve By</th>
                        <th colspan="2" class="action">Action</th>
                    </tr>
                    <#assign x = 1>
                    <#list weekends as weekend>
                        <#if x%2 == 0>
                        <tr class="odd ">
                        <#else>
                        <tr class="even">
                        </#if>
                            <td class="sl">${x}</td>
                            <td class="name">${weekend.employee.token.name}</td>
                            <#assign i = 1>
                        <td class="date">${weekend.date}  <hr>
                            <a href='updateTheDayOff.erp?id=${weekend.id}&lookingFor=${lookingFor}' id="id" name="id"><img title='OK' src='../resources/images/common/ok.png'/></a>
                            &nbsp;&nbsp;
                            <a href='#?id=${weekend.id}' id="id" name="id"><img title='View' src='../resources/images/details.png'/></a>
                            &nbsp;&nbsp;
                            <a href='#?id=${weekend.id}' id="id" name="id"><img title='Delete' src='../resources/images/common/delete.png'/></a>
                        </td>
                            <td class="note">${weekend.note}</td>
                            <#if weekend.approveBy??>
                                <td class="name">${weekend.approveBy.token.name}</td>
                            <#else>
                                <td class="name red">Not Approve</td>
                            </#if>
                            <td class="action"><a href='updateTheDayOff.erp?id=${weekend.id}&lookingFor=${lookingFor}' id="id" name="id"><img title='Edit' src='../resources/images/common/ok.png'/></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href='#?id=${weekend.id}' id="id" name="id"><img title='Delete' src='../resources/images/common/delete.png'/></a> </td>
                        </tr>
                        <#assign x = x+1>
                    </#list>
                </table>
            </div>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#lookingFor").focus();
        });

        function openHelp() {
            var content = "Please do any of the followings to get result<hr/>"+
                    "To get result for Specified Month's, like <b>May 2014</b>. Type <b>'0514'</b> and press Enter<br/>"+
                    "To get result for Current month type <b>'CM / cm / Cm / cM'</b> and press Enter<br/>";
            top.helpPan = window.open('','','width=550, height=250, top=270, left=520, ')
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
        table{
            margin-left: 20px;
            margin-right: 20px;
        }

        table, th, td {
            border-collapse: collapse;
            font-size: 13px;
            padding: 3px;
            height:30px;
        }

        th {
            text-align: left;
            font-weight: bold;
            color: white;
            background-color: #c04c05;
        }
        .name {
            width: 380px;
        }

        .date{width: 120; text-align: center}
        .note{width: 150;}
        .action{
            width: 140px;
            text-align: center;
        }
        ul, li{
            list-style: upper-alpha;
        }
        .sl{width:70px; text-align: center}
        .even{background-color: #d3d3d3
        }
        .odd{background-color: #f9e3b7
        }
        .red{color:red; font-weight: bold}
        .headerTitle{width: 1100px; text-align: center; color: #000000; font-size: 20px; font-weight: bold; background-color: #cbe0e1
        }
    </style>
</div>

</@layout.gen_app_layout>