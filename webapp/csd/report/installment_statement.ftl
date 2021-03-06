<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="#">CSD Home</a></li>
        <li><a href="reportHome.erp">Report Home</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>${PageTitle}</span>
        <span style="position: absolute; left: 86%;">
        <a href="#">
            <img src="../resources/images/common/back.png" title="Previous Customer" height="30px" width="30px" alt=""
                 onclick="back('${cid}');"/>
        </a>
    </span>
    <span style="position: absolute; left: 89%;">
         <a href="#">
             <img src="../resources/images/common/next.png" title="Next Customer" height="30px" width="30px" alt=""
                  onclick="next('${cid}');"/>
         </a>
    </span>

    </div>
    <script>
        function back(cid) {
            //var cid = ;
            var id = cid.substring(4, 10);
            var nextId = parseInt(id) - 1;
            window.location = '/mirror/csd/viewInstallmentStatement.erp?cid=' + nextId;
        }
        function next(cid) {
            var id = cid.substring(4, 10);
            var nextId = parseInt(id) + 1;
            window.location = '/mirror/csd/viewInstallmentStatement.erp?cid=' + nextId;
        }
    </script>

    <div class="content">
        <form <#--name="cidForm" id="cidForm" action="viewPaymentStatement.erp" method="GET"-->>
        <#--<input type="text" name="cid" id="cid" placeholder="CID : e.g. 12, 123" style="font-size: 15px"/>
        <a onclick="openHelp();">Help(?)</a>-->
            <a href="viewPaymentReport.erp?cid=<#if errMsg = "">${customer.CID}<#else >0</#if>" class="button">Payment
                Report</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <label style="position: absolute; left: 80%;"><input type="checkbox" checked="checked"
                                                                 onclick="toggle(this)"/> Hide
                Header</label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>
        <br/>

        <div id="ele1" class="content scaffold-list" role="main">
            <br/><br/>

            <div class="headerDiv" id="headerDiv">
                <table class="headerTable" id="headTable">
                    <tr>
                        <td class="CompanyName">D<span style="color: #c04c05">O</span>RPAN Properties Limited</td>
                    </tr>
                    <tr>
                        <td class="address">Flat <b>5A</b>, House <b>30</b>, Sonargaon Janapath, Sector <b>11 Chowrasta</b></td>
                    </tr>
                    <tr>
                        <td class="address">Uttara, Dhaka <b>1230, </b>Ph: <b>0289 58752</td>
                    </tr>
                    <tr>
                        <td class="header"></td>
                    </tr>
                </table>
                <br/><br/>
                <hr/>
            </div>
            <#if errMsg = "">


                <div style="min-height: 1000px">

                    <div id="hideShowA">
                        <table style="margin-left: 50px; margin-right: 40px;">
                            <tr>
                                <td class="right" style="width: 950px;font-weight: bold">File No.: ${fileNo}</td>
                            </tr>
                        </table>
                        <br/><br/>
                    </div>
                    <div id="hideShowB">
                        <table style="margin-left: 50px; margin-right: 40px;">
                            <tr>
                                <td style="width: 950px" class="right date">${today}</td>
                            </tr>
                        </table>
                    </div>
                    <div id="hideShowC">
                        <br/><br/> <br/>
                    </div>
                    <br/>

                    <table class="headerTable">
                        <tr>
                            <td style="width: 800px; text-align: center; font-size: 22px; font-weight: bold">Installment
                                Statement
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 800px; text-align: center;"></td>
                        </tr>
                    </table>
                    <table class="promo1" border="1px solid black" style="border-collapse: collapse">
                        <tr class="borderA">
                            <td colspan="4" style="background-color: #41adff; font-size: 18px" class="full left">
                                Customer Identity (CID) : ${customer.CID}</td>
                        </tr>
                        <tr class="borderA">
                            <td colspan="4" class="full left"><label
                                    style="color: blue; font-size: 18px">${customer.name}</label></td>
                        </tr>
                        <tr class="borderA">
                            <td colspan="4" class="full left"> ${customer.mailingAddress}</td>
                        </tr>
                        <tr>
                            <td class="p1d1">Building :</td>
                            <td class="p1d2">${customer.building.buildingName} </td>
                            <td class="p1d1">Floor Size :</td>
                            <td class="p1d2"> ${customer.floorSize} sft</td>
                        </tr>
                        <tr>
                            <td class="p1d1">Project :</td>
                            <td class="p1d2">${customer.building.project.projectName} </td>
                            <td class="p1d1">Price/Sft :</td>
                            <td class="p1d2"> ${customer.price}/=</td>
                        </tr>


                    </table>


                    <br/><br/>

                    </h3>

                <#if installmentList?has_content>

                    <table class="instTable">
                        <tr style="font-weight: bold; color: #0200ff">
                            <th>Nos.</th>
                            <th>Installment Of</th>
                            <th>Status</th>
                            <th>Nos.</th>
                            <th>Installment Of</th>
                            <th>Status</th>
                            <th>Nos.</th>
                            <th>Installment Of</th>
                            <th>Status</th>
                        </tr>
                        <#assign trN = 0>
                        <#assign due = payable-paid>
                        <#assign rest = paid>
                        <#assign nos = 1>

                        <#list installmentList as installment>
                            <#if trN%3==0>
                            <tr>
                                <#assign tdN = 0>
                            </#if>

                            <td class="nos">${nos}</td>
                            <td class="instTd">${installment.installmentOf}</td>
                            <td class="statusTd">
                                <#if rest < installment.amount >
                                    <#if rest <= 0 >
                                        <#if due <= 0>
                                        <#else >
                                            <label for="" style="color: #ff0517">Due ${installment.amount}</label>
                                            <#assign due = due-(installment.amount)>
                                        </#if>
                                    <#else >
                                        <label for="" style="color: #00c8ff">Due ${installment.amount-rest}</label>
                                         <#--of ${installment.amount}-->
                                        <#assign due = due-(installment.amount-rest)>
                                    </#if>
                                <#else >
                                <label for="" style="color: #1c8716"> Clear </label>
                                </#if>
                                <#assign rest = rest-(installment.amount)>
                            </td>

                            <#if trN%3==0 && tdN == 3>
                            </tr>
                            </#if>
                            <#assign trN = trN + 1>
                            <#assign tdN = tdN + 1>
                            <#assign nos = nos + 1>
                        </#list>


                    </table>

                <#else >
                <h1>This customer has no installment. It's under one time payment system.</h1>
                </#if>

                </div>
            <#--
                            <#assign x=3>
                            <#list 1..x as i>
                                <td>${installment.installmentOf}</td>
                                <td>---</td>
                            </#list>-->
            <#else >
                <h1 style="color: red">${errMsg}</h1>
            </#if>
        </div>
    </div>
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

        function openHelp() {
            var content =
                    "Please type <b>'cid'</b> and press Enter to get <b>Payment Statement</b><br/>" +
                    "<i>e.g. </i> <b>12</b>, <b>125</b>,  <b>126</b> etc. and press Enter<br/>";
            top.helpPan = window.open('', '', 'width=500, height=250, top=270, left=520, ')
            top.helpPan.document.writeln(
                    '<html><head><title>Help</title></head>'
                    + '<body bgcolor=white onLoad="self.focus()" style="font-family: calibri; color: green; font-size: 32px">'
                    + content
                    + '</body></html>'
            )
            top.helpPan.document.close()
        }
        $(document).ready(function () {
            $("#cid").focus();
            $("#headerDiv").hide();
        });

        function toggle(obj) {
            var $input = $(obj);
            if ($input.prop('checked')) {
                $("#headerDiv").hide();
                $("#hideShowA").show();
                $("#hideShowB").show();
                $("#hideShowC").show();
            } else {
                $("#headerDiv").show();
                $("#hideShowA").hide();
                $("#hideShowC").hide();
            }
        }
    </script>

    <style>


        table, hr, .headerTable, .promo1 {
            margin-left: 50px;
            margin-right: 50px;
        }

        table, th, td {
            border: 0px dotted black;
            border-collapse: collapse;
            font-size: 14px;
            padding: 5px 16px 5px 6px;
        }

        .datam {
            height: 35px;
            border-bottom: 1px dotted #747474
        }

        td {
            width: 110px;
        }

        th {
            text-align: center;
            font-weight: bold;
            color: #000200;
            background-color: #b96519;
            border-top: 1px solid black;
            border-bottom: 1px solid black;
        }

        .total {
            border-top: 2px double #000000;
            font-weight: bold;
            text-align: right;
        }

        .closing {
            border-top: 5px double #000000;

            font-weight: bold;
            font-size: 17px;
            text-align: right;
            height: 40px;
        }

        .amount {
            font-weight: bold;
            text-align: right;
            width: 150px;
        }

        .date {
            width: 250px;
            text-align: center
        }

        .mode {
            width: 200px;
            text-align: center
        }

        .vrNo {
            width: 180px;
            text-align: center
        }

        .status {
            width: 320px;
            text-align: left;
            font-size: 11px;
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

        .date {
            font-weight: bold;
            color: green
        }

        .full {
            width: 800px;
            padding-left: 5px;
            border: 1px;
        }

        .mid {
            text-align: center;
        }

        .left {
            text-align: left;
        }

        .right {
            text-align: right;
        }

        .headerTable {
            width: 940px;
            border: 0px;
        }

        .promo1 td {
            padding: 4px 4px 4px 10px
        }

        .promo1 {
            border: 1px solid #000000;
            padding: 3 20px 3 10px;

        }

        .promo1 .p1d1 {
            width: 150px;
            border: 0px solid #000000;
            font-weight: normal
        }

        .promo1 .p1d2 {
            width: 350px;
            border-right: 1px solid #000000;
            border-left: 0px solid #000000;
            border-top: 0px solid #000000;
            border-bottom: 0px solid #000000;
        }

        .full {
            width: 800px;
            padding-left: 5px;
        }

        .borderA {
            border: 1px solid black
        }

        .odd {
            background-color: #e3e3e3
        }

        .instTable td {
            border: 1px solid #000000;
            padding: 5px;
            border-collapse: collapse;

        }

        .instTd {
            width: 175px;
        }

        .statusTd {
            width: 120px;
            text-align: center;
            font-weight: bolder;
        }
        .nos{
            width: 20px;
            text-align: center;
            font-weight: bolder;
        }
    </style>
</div>

</@layout.gen_app_layout>