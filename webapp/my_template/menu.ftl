<!--Slider-->

<div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav side-nav">
        <li>
            <a href="../home.erp"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
        </li>
        <li>
            <a href="javascript:;" data-toggle="collapse" data-target="#access_dd"><i class="fa fa-fw fa-edit"></i> Access <i class="fa fa-fw fa-caret-down"></i></a>
            <ul id="access_dd" class="collapse">
                <li><a href="/mirror/security/createUser.erp?errMsg=" class="null">User</a></li>
                <li><a href="/mirror/security/authorityList.erp" class="null">Authority</a></li>
                <li><a href="/mirror/security/featureList.erp" class="null">Feature</a></li>
                <li><a href="/mirror/security/userGroupList.erp" class="null">User Group</a></li>
                <li><a href="/mirror/security/componentList.erp" class="null">Component</a></li>
            </ul>
        </li>
        <li>
            <a href="javascript:;" data-toggle="collapse" data-target="#hrm_dd"><i class="fa fa-fw fa-desktop"></i> H R M <i class="fa fa-fw fa-caret-down"></i></a>
            <ul id="hrm_dd" class="collapse">
                <li><a href="/mirror/hrm/userDetailsList.erp">User</a></li>
                <li><a href="/mirror/hrm/createDepartment.erp">Department</a></li>
                <li><a href="/mirror/hrm/createDesignation.erp">Designation</a></li>
                <#--<li><a href="/mirror/hrm/#.erp">User Type</a></li>-->
            </ul>
        </li>
        <li>
            <a href="javascript:;" data-toggle="collapse" data-target="#brtc_dd"><i class="fa fa-fw fa-wrench"></i> B R T C <i class="fa fa-fw fa-caret-down"></i></a>
            <ul id="brtc_dd" class="collapse">
                <li><a href="/mirror/brtc/clientList.erp"><b>Client</b></a></li>
                <li><a href="/mirror/brtc/brtcProjectList.erp?findFor=all"><b>Project</b></a></li>
             <#--   <li><a href="/mirror/brtc/moneyReceiptList.erp">Team</a></li>
                <li><a href="/mirror/brtc/projectHistoryList.erp?fileNo=33"><b>History</b></a></li>-->
            </ul>
        </li>
        <li>
            <a href="javascript:;" data-toggle="collapse" data-target="#finance_dd"><i class="fa fa-fw fa-dollar"></i> Finance <i class="fa fa-fw fa-caret-down"></i></a>
            <ul id="finance_dd" class="collapse">
                <li><a href="/mirror/brtc/clientPaymentList.erp?findFor=all&status=0"><b>Client Payment</b></a></li>
                <li><a href="/mirror/brtc/paymentRequestList.erp?status=0"><b>Advance Req.</b></a></li>
                <#--<li><a href="/mirror/brtc/#"><b>Adv. Adjustment</b></a></li>--><#--
                <li><a href="/mirror/brtc/createDistribution.erp?fileNo=33"><b>Distribution</b></a></li>-->
            </ul>
        </li>
        <li>
            <a href="/mirror/report/reportHome.erp"><i class="fa fa-fw fa-list"></i> Reports</a>
        </li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li>
            <div class="col-lg-12 text-center">
                <form name="searchForm" role="form" id="searchForm" action="/mirror/brtc/viewBrtcProject.erp" method="GET">
                    <script>
                        function submitForm() {
                            document.searchForm.submit();
                        }
                    </script>
                    <div class="form-group input-group">
                        <input type="text" name="fileNo" id="fileNoForProjectSearch" class="form-control" placeholder="File No.">
                        <span class="input-group-addon"><a class="fa fa-search" href="#" onclick="submitForm();"></a></span>
                    </div>
                </form>
            </div>
        </li>
        <!--
        <li>
            <div class="col-lg-12 text-center">
                <form name="searchPaymentRequest" role="form" id="searchPaymentRequest" action="/mirror/brtc/viewPaymentRequest.erp" method="GET">
                    <input type="hidden" name="status" id="status" value="1"/>
                    <script>
                        function submitForm(){
                            document.searchPaymentRequest.submit();
                        }
                    </script>
                    <div class="form-group input-group">
                        <input type="text" name="AprId" id="AprId" class="form-control"  placeholder="APR ID.">
                        <span class="input-group-addon"><a class="fa fa-search" href="#" onclick="submitForm();"></a></span>
                    </div>
                </form>
            </div>
        </li>
        -->
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
        <li><a href="#"><i class="fa fa-fw"></i></a></li>
    </ul>
</div>

<script>
    /*
    var myVar=setInterval(function(){myTimer()},1000);

    function myTimer() {
        var d = new Date();
        document.getElementById("clock").innerHTML = d.toLocaleTimeString();
    }   */
</script>
<!--/Slider-->