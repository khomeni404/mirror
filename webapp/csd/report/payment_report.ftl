<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
    <#assign ctx = rc.getContextPath()/>
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
            window.location = '/mirror/csd/viewPaymentReport.erp?cid=' + nextId;
        }
        function next(cid) {
            var id = cid.substring(4, 10);
            var nextId = parseInt(id) + 1;
            window.location = '/mirror/csd/viewPaymentReport.erp?cid=' + nextId;
        }
    </script>

    <div class="content">

        <form <#--name="cidForm" id="cidForm" action="viewPaymentReport.erp" method="GET"-->>
        <#--<input type="text" name="cid" id="cid" placeholder="CID : e.g. 012, 123 (Press Enter)"/>-->
            <a href="viewPaymentStatement.erp?cid=<#if errMsg = "">${customer.CID}<#else >0</#if>" class="button">Payment
                Statement</a>
            <a href="viewInstallmentStatement.erp?cid=<#if errMsg = "">${customer.CID}<#else >0</#if>" class="button">Installment
                Statement</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a href="viewCustomer.erp?cidView=<#if errMsg = "">${customer.CID}<#else >0</#if>" class="button">View
                Customer</a>
            <a onclick="sendDueInfoSms('${customer.id!}');" style="position: absolute; left: 65%;"><img
                    alt="Print" src="../resources/images/csd/sms.png" width="32" height="32"/></a>

            <label style="position: absolute; left: 73%;"><input type="checkbox" onclick="toggleSpNotes(this)"/> Show
                Notes</label>
            <label style="position: absolute; left: 80%;"><input type="checkbox" onclick="toggle(this)"/> Show
                Header</label>
            <a onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                    alt="Print" src="../resources/images/csd/printer2.png"/></a>
            <a onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf" src="../resources/images/csd/pdf.png"/></a>
        </form>
        <span id="smsMsg" style="text-align: center; font-size: 17px; color: green;"></span>
        <br/>

        <div id="ele1" class="content scaffold-list" role="main">
            <#if errMsg = "">
                <div style="min-height: 1400px">
                    <br/><br/><br/><br/><br/> <br/><br/>

                    <div class="headerDiv" id="headerDiv">
                        <table class="headTable" id="headTable">
                            <tr>
                                <td class="CompanyName">D<span style="color: #c04c05">O</span>RPAN Properties Limited
                                </td>
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
                        <hr/>
                        <br/><br/><br/>
                    </div>
                    <table class="headerTable">
                        <tr>
                            <td class="right">File No.: ${fileNo}</td>
                        </tr>
                    </table>
                    <br/><br/>
                    <table class="headerTable">
                        <tr>
                            <td class="right date">${today}</td>
                        </tr>
                    </table>
                    <br/><br/> <br/><br/>
                    <table class="headerTable">
                        <tr>
                            <td style="width: 800px; text-align: center; font-size: 22px; font-weight: bold">Payment
                                Report
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 800px; text-align: center;">of</td>
                        </tr>
                    </table>
                    <table class="promo1" border="1px solid black" style="border-collapse: collapse">
                        <tr>
                            <td colspan="4" style="background-color: #41adff" class="full mid">${customer.CID}</td>
                        </tr>
                        <tr>
                            <td colspan="4" class="full left">
                                <label
                                        style="color: blue; font-size: 18px">${customer.name}
                                    <span style="color: red;">
                                        <#if customer.status = "Cancelled"> (Cancelled)
                                        <#else >
                                            <#if customer.status = "Refunded">
                                                (Cancelled & Refunded)
                                            </#if>
                                        </#if>
                                    </span>
                                </label></td>
                        </tr>
                        <tr>
                            <td colspan="4" class="full left"> ${customer.mailingAddress}</td>
                        </tr>
                        <tr>
                            <td class="p1d1">Cell :</td>
                            <td class="p1d2"> ${customer.cellPhone} / ${customer.altPhone} </td>
                            <td class="p1d1">Building :</td>
                            <td class="p1d2">${customer.building.buildingName} </td>
                        </tr>
                        <tr>
                            <td class="p1d1">Email :</td>
                            <td class="p1d2"> ${customer.emailAddress}</td>
                            <td class="p1d1">Apart ID(AID) :</td>
                            <td class="p1d2">  ${customer.building.nameAlias} - ${customer.AID} </td>
                        </tr>
                        <tr>
                            <td class="p1d1">MID :</td>
                            <td class="p1d2">${customer.referenceBy.mid} </td>
                            <td class="p1d1">Floor Size :</td>
                            <td class="p1d2"> ${customer.floorSize} sft</td>
                        </tr>

                        <tr>
                            <td class="p1d1">Offer :</td>
                            <td class="p1d2">${customer.offer.offerName} </td>
                            <td class="p1d1">Price/Sft :</td>
                            <td class="p1d2"> ${customer.price}/=</td>
                        </tr>
                        <tr>
                            <td class="p1d1">Project :</td>
                            <td class="p1d2">${customer.building.project.projectName} </td>
                            <td class="p1d1">Hand-Over :</td>
                            <td class="p1d2"> ${handOver}</td>
                        </tr>

                    </table>

                    <br/><br/>

                    <table class="promo2" border="1px solid black" style="border-collapse: collapse">
                        <tr>
                            <td class="left">Total price of the flat :</td>
                            <td class="right">${apartmentPrice?string("#,##0")}/=</td>
                            <td class=""></td>
                        </tr>
                        <tr style="background-color: #00dd70">
                            <td class="left">Total Paid Amount :</td>
                            <td class="right">${paidTotalActual?string("#,##0")}/=</td>
                            <td class="">[Till ${today}]</td>
                        </tr>
                        <tr>
                            <td class="left">Rest Amount :</td>
                            <td class="right" style="border-top: 4px double black">

                                <#if restTotal &gt; -0.9>
                                ${restTotal?string("#,##0")}/=
                                <#else >
                                    Adv: (${(-restTotal)?string("#,##0")})
                                </#if>

                            </td>
                            <td class=""></td>
                        </tr>

                    </table>

                    <br/><br/><br/><br/> <br/><br/>
                    <label style="padding-left: 80px; font-size: 17px; color: #00184b">Payment Distribution:</label>
                    <table class="statusOfPayment" style="border-collapse: collapse">
                        <tr style="background-color: #93a456;">
                            <th class="particular">Particular</th>
                            <th class="amount">Total</th>
                            <th class="amount">Recoverable</th>
                            <th class="amount">Paid</th>
                            <th class="status">Status/Due</th>
                        </tr>
                        <#if totalInst == 0>
                            <tr>
                                <td class="particular">Installment (Nos.)</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="status">---</td>
                            </tr>
                        <#else >
                            <tr>
                                <td class="particular">Number of Installment</td>
                                <td class="amount">${totalInst} <br/> Tk.${amountPerInst?string("#,##0")} / Inst.</td>
                                <td class="amount">${payableInst?string("#,##0.00")}</td>
                                <td class="amount">${paidInst?string("#,##0.00")}</td>
                                <td class="status">
                                    <#if dueInst &gt; -0.9>
                                    ${dueInst?string("#,##0.00")}
                                    <#else >
                                        Adv: (${(-dueInst)?string("#,##0.00")})
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                        <#if totalInstAmount == 0>
                            <tr>
                                <td class="particular">Installment Amount</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="status">---</td>
                            </tr>
                        <#else >
                            <tr>
                                <td class="particular">Installment Amount</td>
                                <td class="amount">${totalInstAmount?string("#,##0")}/=</td>
                                <td class="amount">${payableInstAmount?string("#,##0")}/=</td>
                                <td class="amount">${paidInstAmount?string("#,##0")}/=</td>
                                <td class="status">
                                    <#if dueInstAmount &gt; -0.9>
                                    ${dueInstAmount?string("#,##0")}/=
                                    <#else >
                                        Adv: (${(-dueInstAmount)?string("#,##0")})
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                        <#if totalDp == 0>
                            <tr>
                                <td class="particular">Down Payment</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="status">---</td>
                            </tr>
                        <#else >
                            <tr>
                                <td class="particular">Down Payment</td>
                                <td class="amount">${totalDp?string("#,##0")}/=</td>
                                <td class="amount">${payableDp?string("#,##0")}/=</td>
                                <td class="amount">${paidDp?string("#,##0")}/=</td>
                                <td class="status">
                                    <#if dueDp &gt; -0.9>
                                    ${dueDp?string("#,##0")}/=
                                    <#else >
                                        Adv: (${(-dueDp)?string("#,##0")})
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                        <#if totalSp == 0>
                            <tr>
                                <td class="particular">Special Payment</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="status">---</td>
                            </tr>
                        <#else >
                            <tr>
                                <td class="particular">Special Payment</td>
                                <td class="amount">${totalSp?string("#,##0")}/=</td>
                                <td class="amount">${payableSp?string("#,##0")}/=</td>
                                <td class="amount">${paidSp?string("#,##0")}/=</td>
                                <td class="status">
                                    <#if dueSp &gt; -0.9>
                                    ${dueSp?string("#,##0")}/=
                                    <#else >
                                        Adv: (${(-dueSp)?string("#,##0")})
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                        <#if totalCp == 0>
                            <tr>
                                <td class="particular">Car Parking</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="status">---</td>
                            </tr>
                        <#else >
                            <tr>
                                <td class="particular">Car Parking</td>
                                <td class="amount">${totalCp?string("#,##0")}/=</td>
                                <td class="amount">${payableCp}</td>
                                <td class="amount">${paidCp}</td>
                                <td class="status">
                                    <#if dueCp &gt; -0.9>
                                    ${dueCp?string("#,##0")}/=
                                    <#else >
                                        Adv: (${(-dueCp)?string("#,##0")})
                                    </#if>
                                </td>

                            </tr>
                        </#if>
                        <#if totalOp == 0>
                            <tr>
                                <td class="particular">Others/Delay Charge</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="amount">---</td>
                                <td class="status">---</td>
                            </tr>
                        <#else >
                            <tr>
                                <td class="particular">Others/Delay Charge</td>
                                <td class="amount">${totalOp?string("#,##0")}/=</td>
                                <td class="amount">${payableOp?string("#,##0")}/=</td>
                                <td class="amount">${paidOp?string("#,##0")}/=</td>
                                <td class="status">${dueOp?string("#,##0")}/=</td>
                            </tr>
                        </#if>
                        <tr style="border-top: 3px double #000000; text-align: right ">
                            <td colspan="4">DUE TOTAL =</td>
                            <td class="status">
                                <#if dueInstAmount+dueDp+dueSp+dueCp+dueOp &gt; -0.9>
                                ${(dueInstAmount+dueDp+dueSp+dueCp+dueOp)?string("#,##0")}/=
                                <#else >
                                    Adv: (${(-(dueInstAmount+dueDp+dueSp+dueCp+dueOp))?string("#,##0")})
                                </#if>
                            </td>
                        </tr>

                    </table>
                    <br/><br/><br/>
    <textarea style="font-size: 15px" cols="300" rows="" id="specialNotes" class="specialNotes"
              name="specialNotes">${customer.notes}</textarea>

                </div>
                <div id="recruit_home" class="" role="main" style="width: 1000px; text-align: right;">
                    <label style="color: green; text-align: right">Developed by: Khomeni, 01717659287</label>

                </div>
            <#else >
                <h1 style="color: #ff0000">${errMsg}</h1>
            </#if>
        </div>
    </div>
    <style>
        .statusOfPayment {
            border: 1px solid #000000;
            margin-left: 80px;
            margin-right: 50px;
            width: 850px;
        }

        .statusOfPayment tr td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 3 20px 3 10px;
            font-size: 14px;
            height: 30px;
        }

        .amount, .status {
            width: 130px;
            text-align: right;
            padding-right: 15px
        }

        .particular {
            width: 200px
        }

        .promo1, .specialNotes {
            margin-left: 80px;
            margin-right: 50px;
            width: 850px;
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

        .date {
            font-weight: bold;
            color: green
        }

        .full {
            width: 800px;
            padding-left: 5px;
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
            margin-left: 80px;
            margin-right: 50px;
            width: 850px;
            border: 0px;
        }

        td {
            width: 200px;
            font-weight: bold;
        }

        th {
            text-align: center;
            font-weight: bold;
            color: green;
            border: 1px solid #000000;
        }

        .promo1 td {
            padding: 4px 4px 4px 10px
        }

        .promo2 {
            margin-top: 30px;
            margin-left: 80px;
            margin-right: 50px;
            width: 850px;
        }

        .promo2 tr td {
            border: 0px;
            padding: 3 20px 3 10px
        }

        .headTable {
            margin-left: 0px
        }

        .CompanyName {
            width: 1000px;
            text-align: center;
            font-size: 30px;
            font-weight: bold
        }

        .address {
            width: 1000px;
            text-align: center;
            font-size: 14px;
            font-weight: bold
        }

        .header {
            width: 1000px;
            text-align: center;
            font-size: 20px;
            font-weight: bold
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
            $("#specialNotes").hide()
        });

        function toggle(obj) {
            var $input = $(obj);
            if ($input.prop('checked')) $("#headerDiv").show();
            else  $("#headerDiv").hide();
        }

        function toggleSpNotes(obj) {
            var $input = $(obj);
            if ($input.prop('checked')) $("#specialNotes").show();
            else  $("#specialNotes").hide();
        }

       /* function sendDueInfoSms(id) {
            window.location = '/mirror/csd/sendDueInfoSms.erp?id='+id;
        }*/

        function sendDueInfoSms(id) {
            //alert(id); return false;
            $.ajax({
                url: "${ctx}/csd/sendDueInfoSms.erp",
                data: {
                    id: id
                },
                type: "GET",
                success: function (result) {
                    //makModalAlert('warning', 'Attention !', "Please type a BRTC number");
                    var jsonData = JSON.parse(result); //jsonData.msgHead
                    $('#smsMsg').html("<br/><b>"+jsonData.msgHead+"</b><hr/>"+jsonData.msgBody+"<br/>");
                },
                failure: function () {
                    alert("Sorry");
                }
            });
        }

    </script>
</div>

</@layout.gen_app_layout>