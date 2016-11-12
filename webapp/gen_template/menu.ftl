<!--Slider-->
<div id="left_menu">
			
    <ul id="main_menu" class="main_menu">
        <li class="" ><a href="#"><span class="ico gray clock" ></span><b id="clock"></b></a>

        <li class="limenu"><a href="/mirror/home.erp"><span class="ico gray shadow home" ></span><b>Dashboard</b></a></li>

        <li><a href="/mirror/mis/home.erp"><span class="ico gray csd" ></span><b>MIS Home</b></a></li>
        <li><a href="/mirror/csd/createMoneyReceipt.erp" class="null"><span class="ico gray chain" ></span>Receipt</a></li>
        <li><a href="/mirror/csd/moneyDisburseList.erp?findFor=not"><span class="ico gray cts" ></span><b>Disburse</b></a></li>
        <li><a href="/mirror/csd/csdConfigurationHome.erp"><span class="ico gray mis" ></span><b>Configuration</b></a></li>
        <li><a href="/mirror/csd/paymentHome.erp"><span class="ico gray salary" ></span><b>Payment</b></a></li>
        <li><a href="/mirror/csd/approveHome.erp"><span class="ico gray locator" ></span><b>Approving</b></a></li>


        <#--
        <li class="limenu" ><a href="#" ><span class="ico gray access_menu"></span><b>Access Control</b></a>
            <ul>
                <li><a href="/mirror/security/userList.erp" class="null">User</a></li>
                <li><a href="/mirror/security/authorityList.erp" class="null">Authority</a></li>
                <li><a href="/mirror/security/featureList.erp" class="null">Feature</a></li>
                <li><a href="/mirror/security/userGroupList.erp" class="null">User Group</a></li>
                <li><a href="/mirror/security/componentList.erp" class="null">Component</a></li>
            </ul>
        </li>


        <li class="limenu" ><a href="#"><span class="ico gray csd" ></span><b>C S D</b></a>
            <ul>

                <li><a href="/mirror/csd/reportHome.erp"><b>Report</b></a></li>
                <li><a href="/mirror/csd/createMoneyReceipt.erp" class="null">Receipt</a></li>
                <li><a href="/mirror/csd/moneyDisburseList.erp?findFor=not"><b>Disburse</b></a></li>
                <li><a href="/mirror/csd/csdConfigurationHome.erp"><b>Configuration</b></a></li>
                <li><a href="/mirror/csd/paymentHome.erp"><b>Payment</b></a></li>
                <li><a href="/mirror/csd/approveHome.erp"><b>Approving</b></a></li>


            </ul>
        </li>
        <li class="limenu" ><a href="#" ><span class="ico gray icm_menu"></span><b>I &nbsp;C M</b></a>
            <ul>
                <li><a href="/mirror/icm/myIcmHome.erp" class="null">Its Me</a></li>
                <li><a href="/mirror/icm/icmHome.erp" class="null">Its Admin</a></li>
            </ul>

        </li>
        <li class="limenu" ><a href="#"><span class="ico gray clock" ></span><b>Time & Leave</b></a>
            <ul>

                <li><a href="/mirror/time/weekendList.erp?lookingFor=CM.." class="null">Weekend</a></li>
                <li><a href="/mirror/time/attendanceRegisterList.erp?dateFor=Today" class="null">Attendance</a></li>
                <li><a href="/mirror/time/leaveApplicationList.erp?status=0" class="null">Application</a></li>
                <li><a href="/mirror/time/timeHome.erp" class="null">Admin Home</a></li>
            </ul>

        </li>
        -->
        <li class="limenu" ><a href="/mirror/security/logout.erp"><span class="ico gray signOut" ></span><b>Sign Out</b></a>
            <ul>
                <li><a href="/mirror/security/editUser.erp?id=0" class="null">Change Password</a></li>
                <li><a href="/mirror/time/#.erp" class="null">View Profile</a></li>
            </ul>

        </li>
        <br/>

        <li>
            <form action="/mirror/csd/viewCustomerByName.erp" method="GET">
                <input type="text" name="customerName" id="customerName" placeholder="Name (Search)" class="findCustomer"/>
            </form>
        </li>
        <li>
            <form action="/mirror/csd/viewPaymentReport.erp" method="GET">
                <input type="text" name="cid" id="cid" placeholder="CID (View Report)"/>
            </form>
        </li>
        <li>
            <form action="/mirror/csd/viewCustomer.erp" method="GET">
                <input type="text" name="cidView" placeholder="CID (View Customer)" class="viewCustomer"/>
            </form>
        </li>
        <li>
            <form action="/mirror/search/searchCustomer.erp" method="GET">
                <input type="text" name="referenceName" placeholder="Reference Person" class="viewCustomer"/>
            </form>
        </li>
        <li>
            <form action="/mirror/csd/viewMoneyReceipt.erp" method="GET">
                <input type="text" name="mrNo" id="mrNo" placeholder="MR No. (Search)"/>
            </form>
        </li>
        <!--
        <li class="limenu" ><a href="#"><span class="ico gray  dimensions" ></span><b>HRM</b></a>

            <ul>
                <li><a href="/mirror/leave/leaveHome.erp" class="null">Leave</a></li>
                <li><a href="/mirror/payroll/payrollConfigurationHome.erp" class="null">Payroll Home</a></li>
                <li><a href="/mirror/employee/createEmployee.erp" class="null">Employee</a></li>
                <li><a href="/mirror/calendar/calendarHomeConfig.erp" class="null">Time</a></li>
                <li><a href="/mirror/time/attendanceHome.erp" class="null">Attendance</a></li>
                <li><a href="/mirror/recruitment/recruitHome.erp" class="null">Recruitment</a></li>
                <li><a href="/mirror/performance/performanceReviewList.erp" class="null">Performance</a></li>
                <li><a href="/mirror/hrm2/hrmConfigurationHome.erp" class="null">Configurations</a></li>

            </ul>
        </li>

        <li class="limenu" ><a href="/mirror/admin/index"><span class="ico gray configuration" ></span><b>Configuration</b></a>

            <ul>
               <li><a href="/mirror/admin/organizationConfigHome" class="null">Organization</a></li>
               <li><a href="/mirror/admin/attendanceConfigHome" class="null">Attendance</a></li>
               <li><a href="/mirror/admin/leaveList" class="null">Leave</a></li>
               <li><a href="/mirror/admin/jobHome.erp" class="null">Job</a></li>
            </ul>

        </li>

        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray document" ></span><b>DMS</b></a>
        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray money" ></span><b>Finance</b></a>
        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray tasks" ></span><b>Task Management</b></a>
        <li class="" ><a href="/mirror/scm/home.erp"><span class="ico gray chain" ></span><b>SCM</b></a>
        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray cts" ></span><b>CTS</b></a>
        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray mis" ></span><b>MIS</b></a>
        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray salary" ></span><b>Payroll Management</b></a>
        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray locator" ></span><b>Document Locator</b></a>
        <li class="" ><a href="/mirror/configuration/index"><span class="ico gray crm" ></span><b>CRM</b></a>


        <li class="" ><a href="/mirror/csd/test.erp"><span class="ico gray crm" ></span><b>Credentials</b></a>
        -->



        <!--
        <li class="" ><a href="/mirror/csd/viewMoneyDisbursePd.erp"><span class="ico gray crm" ></span><b>MD Processing</b></a>
        -->
    </ul>
</div>

<script>
    var myVar=setInterval(function(){myTimer()},1000);

    function myTimer() {
        var d = new Date();
        document.getElementById("clock").innerHTML = d.toLocaleTimeString();
    }
</script>
<!--/Slider-->