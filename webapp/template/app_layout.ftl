<#macro app_layout title="">
<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <#include "stylesheets.ftl"/>
    <!-- Scripts -->
    <#include "scripts.ftl"/>
    <!-- Scripts -->
</head>
<body class="dashborad">
<#-- Header content
    <#include "header.ftl"/>     -->
<!-- Header content -->


<#-- Header content  -->
    <#include "../gen_template/menu.ftl"/>
<!-- Header content -->

<!--Body content-->
<div id="content">
    <div class="inner">
        <div class="topcolumn">
            <div class="logo"></div>
            <div><h1>D<span style="color: #eb5c13">O</span>RPAN Cust<span style="color: #eb5c13">O</span>mer Supp<span
                    style="color: #eb5c13">O</span>rt management</h1></div>
        <#-- This should be opened
        <ul id="shortcut">
            <li> <a href="#" title="Back To home"> <img src="images/icon/shortcut/home.png" alt="home"/><strong>Home</strong> </a> </li>
            <li> <a href="#" title="Website Graph"> <img src="images/icon/shortcut/graph.png" alt="graph"/><strong>Graph</strong> </a> </li>
            <li> <a href="#" title="Setting" > <img src="images/icon/shortcut/setting.png" alt="setting" /><strong>Setting</strong></a> </li>
            <li> <a href="#" title="Messages"> <img src="images/icon/shortcut/mail.png" alt="messages" /><strong>Message</strong></a><div class="notification" >10</div></li>
        </ul>
        -->
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <!-- full width -->


        <!-- full width -->
        <!-- End full width -->


        <!-- Two column window column_left -->
        <div class="column_left">
            <!-- full width -->
            <div class="widget" style="min-height: 600px">
                <div class="header"><span><span class="ico gray notepad"></span> Sales Information </span></div>
                <div class="content tableName">
                    <table class="display data_table">
                        <thead>
                        <tr>
                            <th width="224">
                                <div style="text-align:left; margin-left:10px">Topic</div>
                            </th>
                            <th width="195">
                                <div style="text-align:right; ">Count</div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>

                        <tr>

                            <td>

                                <div class="msg">
                                    <div class="msg_icon new"></div>
                                    <div class="msg_topic"><strong>Number of sales apartment :</strong></div>

                                </div>
                            </td>
                            <td style="font-size: 22px; color: green; font-weight: bold">
                                <div class="msg_date total_count">${numberOfBooking}<span>(All) </span></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="msg">
                                    <div class="msg_icon new"></div>
                                    <div class="msg_topic"><strong>Approved</strong></div>

                                </div>
                            </td>
                            <td>
                                <div class="msg_date total_count">${approved}<span>(Approved)</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="msg">
                                    <div class="msg_icon new"></div>
                                    <div class="msg_topic"><strong>Cancelled</strong></div>

                                </div>
                            </td>
                            <td>
                                <div class="msg_date total_count">${cancelled}<span>(Cancelled)</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="msg">
                                    <div class="msg_icon new"></div>
                                    <div class="msg_topic"><strong>Refunded</strong></div>

                                </div>
                            </td>
                            <td>
                                <div class="msg_date total_count">${refunded} <span>(Refunded)</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="msg">
                                    <div class="msg_icon new"></div>
                                    <div class="msg_topic"><strong>Processing</strong></div>

                                </div>
                            </td>
                            <td>
                                <div class="msg_date total_count">${pending}<span>(Processing/Not Approved)</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="msg">
                                    <div class="msg_icon new"></div>
                                    <div class="msg_topic"><strong>Total amount collected</strong></div>

                                </div>
                            </td>
                            <td style="font-size: 22px; color: green; font-weight: bold">
                                <div class="msg_date total_count">${collectedAmount}/= <span>(Till today) </span></div>
                            </td>
                        </tr>


                        </tbody>
                    </table>

                </div>
                <!-- End content -->
            </div>
            <!-- End full width -->

            <!-- full width
            <div class="widget">
                <div class="header"><span><span class="ico gray record"></span> Welcome to D<mak style="color: orangered">O</mak>RPAN Properties Limited</span></div>
                <div class="content">


                    <div class="boxtitle min"> Welcome to site, </div>

                    <p class="overflow" style="height:165px">Bangladesh is a developing country having abundant resource with limited
                        area of land. After passing four decades of independence the growth rate is raised not up to the mark & the
                        basic needs of housing yet not ensured for all. Adequate investment with efficient leadership can fulfill the
                        basic needs within a shortest possible way.
                        DORPAN Properties Limited (DPL) a sister concern of DORPAN GROUP is a dynamic incorporation in the field of
                        real estate in Bangladesh. DPL is such a journey lead by a competitive & efficient leadership to move in the
                        right way of progress. A lot of professionals & business fellows participate here to run the enterprise with a
                        successful conclusion. Our mission & vision is housing for all.
                        In DPL, a set of dynamic & visionary leadership is committed to serve a smart & eco friendly housing project to
                        the valued customers. Reasonable price, effective road connectivity & placement of community facilities are
                        remarkable identity of value addition. The company will be liable to bear the responsibility & committed to
                        the rights of people of the society.
                        DPL is looking forward to engage people, serve people & go ahead with the people. It deserves the
                        common thinking of progress & prosperity.  </p>

                </div>
            </div>
            -->
        </div>
        <!-- End column_left -->


        <!-- Two column window column_right -->
        <div class="column_right">
            <div class="widget" style="min-height: 600px">
                <div class="header"><span><span
                        class="ico gray notepad"></span> Collection After Last BOD meeting </span></div>
                <div class="content tableName">
                    <table class="display data_table">
                        <thead>
                        <tr>
                            <th width="">
                                <div style="text-align:left; margin-left:10px">Head</div>
                            </th>
                            <th width="">
                                <div style="text-align:right; ">Overdue</div>
                            </th>
                            <th width="">
                                <div style="text-align:right; ">Regular Due</div>
                            </th>
                            <th width="">
                                <div style="text-align:right; ">Advanced</div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                            <#assign ODA = 0.0>
                            <#assign RDA = 0.0>
                            <#assign ADA = 0.0>
                            <#list logList as log>
                            <tr>
                                <td>${log.head!}</td>
                                <td>${log.overDue!}</td>
                                <td>${log.regularDue!}</td>
                                <td>${log.advanced!}</td>
                            </tr>

                                <#assign ODA = ODA+log.overDue>
                                <#assign RDA = RDA+log.regularDue>
                                <#assign ADA = ADA+log.advanced>
                            </#list>
                        <tr style="font-weight: bolder">
                            <td>Total</td>
                            <td>${ODA!}</td>
                            <td>${RDA!}</td>
                            <td>${ADA!}</td>
                        </tr>


                        </tbody>
                    </table>

                </div>
                <!-- End content -->
            </div>

            <!-- full width -->
            <!--
            <div class="widget">
                <div class="header"><span><span class="ico gray access_point"></span>Leave and Attendance</span></div>
                <div class="content tableName">
                    <table class="display data_table" >
                        <thead>
                        <tr>
                            <th width="224" ><div style="text-align:left; margin-left:10px" >Topic </div></th>
                            <th width="195" ><div style="text-align:right; " >Count</div></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td ><div class="msg">
                                <div class="msg_icon new"></div>
                                <div class="msg_topic"><strong>Today Attendance</strong></div>
                            </div></td>
                            <td ><div class="msg_date" >${totalAttend} <span>2014/11/06 </span></div></td>
                        </tr>
                        <tr>
                            <td ><div class="msg">
                                <div class="msg_icon new"></div>
                                <div class="msg_topic"><strong>On leave</strong></div>
                            </div></td>
                            <td ><div class="msg_date" >${onLeave}<span>2014/11/06 </span></div></td>
                        </tr>
                        <tr>
                            <td ><div class="msg">
                                <div class="msg_icon new"></div>
                                <div class="msg_topic"><strong>Absent</strong></div>
                            </div></td>
                            <td ><div class="msg_date" >${14-totalAttend-onLeave}<span>2014/11/06 </span></div></td>
                        </tr>
                        <tr>
                            <td ><div class="msg">
                                <div class="msg_icon new"></div>
                                <div class="msg_topic"><strong>Application to be received
                                    <b class="<#if appWaiting == 0><#else> red blink</#if>"> <#if appWaiting == 0><#else> *</#if></b>
                                </strong></div>
                            </div></td>
                            <td ><div class="msg_date" >${appWaiting} <span>20124/11/02 </span></div></td>
                        </tr>
                        <tr>
                            <td ><div class="msg">
                                <div class="msg_icon new"></div>
                                <div class="msg_topic"><strong>Application to be approved
                                    <b class="<#if appToApprove == 0><#else> red blink</#if>"> <#if appToApprove == 0><#else> *</#if></b>
                                </strong></div>
                            </div></td>
                            <td ><div class="msg_date" >${appToApprove}<span>2014/11/02 </span></div></td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div> End full width -->


        </div>
        <!-- End column_right -->

        <!-- clear fix -->
        <div class="clear"></div>

        <div id="footer"> &copy; Copyright 2014 <span class="tip"><a href="#" title="DORPAN Properties Ltd.">DORPAN
            Properties Ltd.</a> </span></div>

    </div>
    <!--// End inner -->
    <!--Body content-->

    <!-- Footer content -->
    <#include "footer.ftl"/>
    <!-- Footer content -->


</body>
</html>
</#macro>