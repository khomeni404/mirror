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
        <form name="itemForm" id="cidForm" action="attendanceRegisterList.erp" method="GET">
            <a href='createAttendanceRegister.erp' id="create"  style="height: 16px"
               class="button icon add">Manual Attendance</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="dateFor" id="dateFor" placeholder="yyyy-mm-dd / today" value="" style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/common/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/common/pdf2.png"/></a>
        </form><br/>
        <div id="printDiv" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <!--
                <table class="headTable" id="headTable">
                    <tr><td class="CompanyName">D<span style="color: #c04c05">O</span>RPAN Properties Limited</td></tr>
                    <tr><td class="address">Flat-5A, House-30, Sonargaon Janapath, Sector-11 Chowrasta, Uttara, Dhaka-1230.</td></tr>
                    <tr><td class="address">ad.dorpan@gmail.com</td></tr>
                </table>
                -->
                <hr/>
                <table>
                    <tr><th style="text-align: right; background-color: honeydew; color: black;">Date: ${dateFor}</th></tr>
                    <tr><th class="headerTitle">Attendance</th></tr>
                </table>
                <br/>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th class="sl">Sl. No.</th>
                        <th class="name">Employee Name</th>
                        <th class="date">Entry Type</th>
                        <th class="date">Date</th>
                        <th class="date">Time In</th>
                        <th class="time">Note</th>
                        <th class="date">Time Out</th>
                        <th colspan="2" class="action">Action</th>
                    </tr>
                    <#assign x = 1>
                    <#list registers as register>
                        <#if x%2 == 0>
                        <tr class="odd <#if register.approved?string == "false">red</#if>">
                        <#else>
                        <tr class="even <#if register.approved?string == "false">red</#if>">
                        </#if>
                            <td class="sl">${x}</td>
                            <td class="name">${register.employee.token.name}</td>
                            <td class="date">${register.entryType}</td>
                            <td class="date">${register.date}</td>
                            <td class="date">${register.timeIn}</td>
                            <td class="note">${register.note}</td>
                            <td class="date">---</td>

                            <td class="action"><a href='updateAttendanceRegister.erp?id=${register.id}' id="id" name="id"><img title='Edit' src='../resources/images/common/ok.png'/></a>
                        &nbsp;&nbsp;&nbsp;
                            <a href='#?id=${register.id}' id="id" name="id"><img title='Delete' src='../resources/images/common/delete.png'/></a> </td>
                        </tr>
                        <#assign x = x+1>
                    </#list>
                </table>
                <br/><br/><hr/><br/>
                <#if weekends?has_content>
                    <table>
                        <tr>
                            <td style="font-weight: bolder; font-size: 16px">
                               <u> Weekend:</u>
                            </td>
                        </tr>
                        <#list weekends as weekend>
                            <tr>
                                <td style="padding-left: 30px">${weekend.employee.token.name}</td>
                            </tr>
                        </#list>
                    </table>
                </#if>


                <br/><br/>
                <#if applications?has_content>
                    <table>
                        <tr>
                            <td style="font-weight: bolder; font-size: 16px">
                               <u> On leave :</u>
                            </td>
                        </tr>
                        <#list applications as application>
                            <tr>
                                <td style="padding-left: 30px">${application.appliedBy.token.name}</td>
                            </tr>
                        </#list>
                    </table>
                </#if>
                <#--
                <table>
                    <tr><th class="headerTitle">The Day Off</th></tr>
                </table>
                <br/>
                <table class="weekend">
                    <tr>
                        <th class="name">Employee Name</th>
                        <th class="type">Weekend Type</th>
                        <th class="note">Note</th>
                        <th style="width: 300px; text-align: center">Action</th>
                    </tr>
                    <#list weekends as weekend>
                        <tr class="even">
                            <#if weekend.status = "Yes">
                                <td class="name">${weekend.employee.token.name}</td>
                                <td class="type">${weekend.type} Weekend</td>
                                <td class="note">${weekend.type} of ${weekend.note}</td>
                                <td class="action">----</td>
                            </#if>
                        </tr>
                    </#list>
                </table>
                <br/><br/><br/><br/><hr/><br/><br/><br/>
                <table>
                    <tr><th class="headerTitle">On Leave</th></tr>
                </table>
                <br/>
                <table class="leave">
                    <tr>
                        <th class="name">Employee Name</th>
                        <th class="date">Applied On</th>
                        <th class="date">From</th>
                        <th class="date">To</th>
                        <th class="days">Days</th>
                        <th class="name">Approved By</th>
                        <th class="action">Action</th>
                    </tr>
                    <#list applications as application>
                            <#if x%2 == 0>
                            <tr class="odd <#if application.approvedBy??> <#else >red</#if>">
                            <#else>
                            <tr class="even <#if application.approvedBy??><#else >red</#if>">
                            </#if>
                                <td class="name">${application.appliedBy.token.name}</td>
                                <td class="date">${application.appliedOn}</td>
                                <td class="date">${application.fromDate}</td>
                                <td class="date">${application.toDate}</td>
                                <td class="days">${application.days}</td>
                                <td class="name">
                                    <#if application.approvedBy??>
                                        ${application.approvedBy.token.name}
                                        <#else >
                                        Not Approved
                                    </#if>
                                </td>
                                <td class="action">----</td>
                            </tr>
                    <#assign x = x + 1>
                    </#list>
                </table>
                -->
            </div>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#dateFor").focus();
        });

        function openHelp() {
            var content = "Please do any one of the following to get result<hr/>"+
                    "To get result for Today type <b>'today'</b> and press Enter<br/>"+
                    "To get result for specified date type <b>'2014-10-5'</b> and press Enter<br/>";
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

        .date{width: 120;}
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
        .red{color: red}
        .weekend .name{padding-left: 15px}
        .leave .name{padding-left: 15px}
        .days{width: 60px; text-align: center; font-weight: bold}
        .type{width:220px}
    </style>
</div>

</@layout.gen_app_layout>