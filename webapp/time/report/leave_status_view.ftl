<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="leaveApplicationList.erp?status=0">Application List</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="viewLeaveSummary.erp" method="GET">
            <input type="text" name="lookingFor" id="lookingFor" placeholder="YYYY" value="" style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/common/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/common/pdf2.png"/></a>
        </form> <br/>
        <div id="printDiv" class="printDiv content scaffold-list" role="main">
            <div style="min-height: 1250px">

                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th class="sl">Sl. No.</th>
                        <th class="name">Employee Name</th>
                        <th >Attained Leave</th>
                        <th >Applied For</th>
                        <th >Waiting For Approving</th>
                        <th >Enjoyed Leave</th>
                        <th >Rest Leave</th>
                    </tr>
                    <#assign x = 1>
                    <#list statusList as status>
                        <#if x%2 == 0>
                        <tr class="odd">
                        <#else>
                        <tr class="even">
                        </#if>
                            <td class="sl">${x}</td>
                            <td class="name">${status.name}</td>
                            <td class="number">${status.attained}</td>
                            <td class="number <#if status.applied == 0 >green <#else >blink red</#if>">${status.applied}</td>
                            <td class="number <#if status.waiting == 0><#else> red blink</#if>">${status.waiting}</td>
                            <td class="number">${status.enjoyed}</td>
                            <td class="number">${status.attained - status.enjoyed}</td>
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
            var content = "Please do any of the followings to get result<hr/>" +
                    "To get result for Specified Month's, like <b>May 2014</b>. Type <b>'0514'</b> and press Enter<br/>" +
                    "To get result for Current month type <b>'CM / cm / Cm / cM'</b> and press Enter<br/>";
            top.helpPan = window.open('', '', 'width=550, height=250, top=270, left=520, ')
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
            margin-right: 20px;
        }

        table, th, td {
            border-collapse: collapse;

            padding: 3px;
            height:30px;
        }
        table td{font-size: 18px;}

        th {
            text-align: left;
            font-weight: bold;
            color: white;
            background-color: #c04c05;
            text-align: center;
        }
        .name {
            width: 380px;
            text-align: left;
        }

        .number{width: 130; text-align: center;}

        ul, li{
            list-style: upper-alpha;
        }
        .sl{width:70px; text-align: center}
        .even{background-color: #d3d3d3
        }
        .odd{background-color: #f9e3b7
        }
        .red{color: red; font-weight: bold; font-size: 25px}

    </style>
</div>

</@layout.gen_app_layout>