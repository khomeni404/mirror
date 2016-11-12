<#import "../../gen_template/gen_app_layout.ftl" as layout>
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
        <form name="itemForm" id="cidForm" action="viewAttendanceSummary.erp" method="GET">
            <a href='/mirror/time/createHolidayEntitlement.erp' id="create"  style="height: 16px"
               class="button icon add">Add Holiday</a>

            <input type="text" name="lookingFor" id="lookingFor" placeholder="mmYY" value="" style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/common/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/common/pdf2.png"/></a>
        </form> <br/>
        <br/>
        <div id="printDiv" class="printDiv content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <table>
                    <tr>
                        <td colspan="10" class="head" ><h2>Attendance Summary</h2></td>
                    </tr>
                    <tr>
                        <td colspan="10" class="head"><h3>For the month of ${month}, ${year}</h3></td>
                    </tr>
                </table>
                <table border="1px">

                    <tr style="font-weight: bold; color: #0200ff">
                        <th class="sl" rowspan="2">Sl. No.</th>
                        <th class="name" rowspan="2">Employee Name</th>
                        <th rowspan="2">Exact</th>
                        <th colspan="4">Late</th>
                        <th rowspan="2">Total Attendance</th>
                        <th rowspan="2">Leave</th>
                        <th rowspan="2">Weekend</th>
                        <th rowspan="2">Absent & (LateCount)</th>
                        <th rowspan="2">Working Days (Current/Total)</th>
                    </tr>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th >9:01-9:15</th>
                        <th >9:16-9:30</th>
                        <th >After 9:30</th>
                        <th >Total</th>
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
                            <td class="number">${status.exact}</td>
                            <td class="time">${status.within9to915}</td>
                            <td class="time">${status.within915to930}</td>
                            <td class="time">${status.after930}</td>
                            <td class="time">${status.after930+status.within915to930+status.within9to915}</td>
                            <td class="number">${status.totalAttendance}</td>
                            <td class="number">${status.leave}</td>
                            <td class="number">${status.dayOff}</td>
                            <td class="number">

                                ${pastDays- status.publicHolidayTillToday
                                - status.dayOff
                                - status.totalAttendance
                                - status.leave}
                                    (${status.absent})
                            </td>
                            <td class="number">
                                ${pastDays - status.publicHolidayTillToday} /
                                ${totalDaysOfMonth - status.publicHolidayAll}
                            </td>
                        </tr>
                        <#assign x = x+1>
                    </#list>

                </table>

                <table>
                    <tr>
                        <td colspan="10" class="leg" >*Total Days of this month : ${totalDaysOfMonth} days</td>
                    </tr>

                    <#list holidays as holiday>
                            <tr>
                            <td colspan="10" class="leg" >*${holiday.date} : ${holiday.note} (${holiday.days} day)</td>
                            </tr>

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
            font-size: 13px;
            padding: 3px;
            height:30px;
        }
        table td{font-size: 13px;}

        th {
            text-align: left;
            font-weight: bold;
            color: white;
            background-color: #c04c05;
            text-align: center;
        }
        .name {
            width: 220px;
            text-align: left;
        }

        .number{width: 60; text-align: center; font-size: 17px}
        .time{width: 70; text-align: center; font-size: 17px;}

        ul, li{list-style: upper-alpha;}
        .sl{width:50px; text-align: center}
        .even{background-color: #d3d3d3}
        .odd{background-color: #f9e3b7}
        .red{color: red; font-weight: bold; font-size: 13px}
        .head{text-align: center; border: 0px;
            width: 1090px;}
        .leg{text-align: right; border: 0px;
            width: 1090px; height: 7px}
    </style>
</div>

</@layout.gen_app_layout>