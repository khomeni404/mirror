<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="leaveApplicationList.erp?status=0">Leave App. List</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">


<style>
    .left {
        text-align: left;
    }

    .right {
        text-align: right;
    }

    .center {
        text-align: center
    }

    .promo1, .promo2 {
        margin-left: 70px;
        margin-right: 60;
        font-size: 17px
    }

    .promo1 tr {
        height: 60px;
        border: 0px;
    }

    .promo2 tr {
        height: 30px;
        border: 0px;
    }

    .a {
        width: 250px
    }

    .name {
        width: 330px
    }

    ;
    .d {
        width: 220px
    }

    .e {
        width: 120px
    }

    .font {
        font-family: Kristen ITC;
        color: blue;
        font-size: 16px;
        font-weight: bold;
    }

    .signature {
        width: 360px;
    }

    .companyname, .address, .header {
        width: 1080px;
        text-align: center;
        font-weight: bold
    }

    .CompanyName {
        font-size: 30px;
    }

    .address {
        font-size: 14px;
    }

    .header {
        font-size: 20px;
    }

    hr {
        border: 1px double #000000
    }
</style>
<script type="text/javascript">
    function submitForm() {
        document.dataForm.submit();
    }


    $("#cid").keyup(function (event) {
        var id = document.getElementById("cid").value;
        if (event.keyCode == 13) {
            document.cidForm.submit();
        }
    });
    $(document).ready(function () {
        $("#cid").focus();
        $("#headerDiv").hide()
    });

    function toggle(obj) {
        var $input = $(obj);
        if ($input.prop('checked')) $("#headerDiv").hide();
        else $("#headerDiv").show();
    }

</script>
<div class="header">
    <span><span class="ico gray user"></span>${PageTitle}</span>

</div>

<div class="content">
    <form name="cidForm" id="cidForm" action="viewLeaveApplication.erp" method="GET">
        <input type="text" name="id" id="id" placeholder="Application no."/>

        <label id="errorMsg" name="errorMsg" style="color: red;"></label>
        <label style="position: absolute; left: 80%;"><input type="checkbox" checked="checked" onclick="toggle(this)"/> Hide
            Header</label>
        <a onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                alt="Print" src="../resources/images/csd/printer2.png"/></a>
        <a onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                alt="Pdf" src="../resources/images/csd/pdf.png"/></a>
    </form>
    <div id="ele1" class="content scaffold-list" role="main">
        <div style="min-height: 1400px">
            <br/><br/><br/><br/><br/>

            <div class="headerDiv" id="headerDiv">
                <table class="headTable" id="headTable">
                    <tr>
                        <td class="CompanyName">D<span style="color: #c04c05">O</span>RPAN Properties Limited</td>
                    </tr>
                    <tr>
                        <td class="address">Flat-5A, House-30, Sonargaon Janapath, Sector-11 Chowrasta, Uttara, Dhaka-1230.</td>
                    </tr>
                    <tr>
                        <td class="address">Ph: 0289 58752, cs.dorpan@gmail.com</td>
                    </tr>
                    <tr>
                        <td class="header"></td>
                    </tr>
                </table>
                <hr/>
                <br/><br/><br/>
            </div>


            <table class="promo1">
                <tr>
                    <td colspan="5" style="width: 800px; text-align: center; font-size: 22px; font-weight: bold">LEAVE
                        APPLICATION FORM
                    </td>
                </tr>
                <tr>
                    <td colspan="5" class="right">Date :<b class="font"> ${app.appliedOn}</b></td>
                </tr>
                <tr>
                    <td class="a left">Name of Emp.</td>
                    <td colspan="2" class="name font">${app.appliedBy.token.name}</td>
                    <td class="right d">Emp. ID :</td>
                    <td class="e right font">EMP-00${app.appliedBy.authorizeId}</td>
                </tr>
                <tr>
                    <td class="left a">Designation & Department</td>
                    <td colspan="4" class="font">${app.appliedBy.token.designation}
                        ,&nbsp;&nbsp; ${app.appliedBy.token.department}</td>
                </tr>
                <tr>
                    <td class="left a">Leave Date</td>
                    <td colspan="2" class="font left">${app.fromDate}&nbsp;&nbsp; -&nbsp; ${app.toDate}</td>
                    <td class="d right">Days:</td>
                    <td class="e right font">${app.days} <#if app.days <2> Day<#else >Days</#if></td>
                </tr>
                <tr>
                    <td class="a">Leave enjoyed (Up today):</td>
                    <td class="b font">4 (X)</td>
                    <td colspan="2" class="right">Leave due</td>
                    <td class="e right font">2 Days</td>
                </tr>
                <tr>
                    <td class="a">Purpose of leave</td>
                    <td colspan="4" class="font">${app.issue}</td>
                </tr>
                <tr>
                    <td class="a">Location of Leave</td>
                    <td colspan="4" class="font">${app.locationOnLeave}</td>
                </tr>
                <tr>
                    <td class="a">Phone (On Leave)</td>
                    <td colspan="4" class="font">${app.phoneOnLeave}</td>
                </tr>
                <tr>
                    <td class="a">In charge (On leave)</td>
                    <td class="font" colspan="2" style="width:460px">
                        <#if app.proxyBy??>
                        ${app.proxyBy.token.name}
                        <#else >
                            -------------------------------
                        </#if>
                    </td>

                    <td class="center" colspan="2">
                        <#if app.proxyBy??>
                            <img src="../resources/images/signature/${app.proxyBy.token.username}.png"
                                 alt="No signature found"/>
                        <#else >
                            <a href='updateLeaveApplication.erp?id=${app.id}&updateType=1' id="updateApp"
                               class="button icon approve myElement a7">
                                <img src="../resources/images/signature/signature.png"/>
                               </a>
                        </#if>
                    </td>
                </tr>

            </table>


            <br/><br/><br/><br/><br/>
            <table class="promo2">
                <tr>
                    <td class="left" colspan="3">
                        <img src="../resources/images/signature/${app.appliedBy.token.username}.png"
                             alt="No signature found"/>
                    </td>
                </tr>
                <tr>
                    <td class="left" colspan="3">
                        Applicant Signature
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="height:80px"></td>
                </tr>
                <tr>
                    <td class="left signature">
                        <#if app.status < 1>
                        <#else >
                            <#if app.acceptedBy??>
                                <img src="../resources/images/signature/${app.acceptedBy.token.username}.png"
                                     alt="No signature found"/>
                            <#else >
                                <a href='updateLeaveApplication.erp?id=${app.id}&updateType=2' id="updateApp"
                                   class="button icon approve"><img src="../resources/images/signature/signature.png"/></a>
                            </#if>
                        </#if>

                    </td>
                    <!--// 0 = Applied | 1 = Settled | 2 = Accepted | 3 = Received | 4 = Approved | 5 = Rejected-->
                    <td class="center signature">
                        <#if app.status < 2 >
                        <#else >
                            <#if app.receivedBy??>
                                <img src="../resources/images/signature/${app.receivedBy.token.username}.png"
                                     alt="No signature found"/>
                            <#else >
                                <a href='updateLeaveApplication.erp?id=${app.id}&updateType=3' id="updateApp"
                                   class="button icon approve"><img src="../resources/images/signature/signature.png"/></a>
                            </#if>
                        </#if>
                    </td>
                    <td class="right signature">
                        <#if app.status < 3>
                        <#else >
                            <#if app.approvedBy??>
                                <img src="../resources/images/signature/${app.approvedBy.token.username}.png"
                                     alt="No signature found"/>
                            <#else >
                                <a href='updateLeaveApplication.erp?id=${app.id}&updateType=4' id="updateApp"
                                   class="button icon approve"><img src="../resources/images/signature/signature.png"/></a>
                            </#if>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td class="left">
                        Head of Department
                    </td>
                    <td class="center">
                        In Charge (Admin)
                    </td>
                    <td class="right">
                        Approved By
                    </td>
                </tr>
            </table>


        </div>
        <div id="recruit_home" class="" role="main" style="width: 1000px; text-align: right;">
            <label style="color: green; text-align: right">Powered by: Khomeni, 01717659287</label>
        </div>

    </div>
</div>

</div>

</@layout.gen_app_layout>