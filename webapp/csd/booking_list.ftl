<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a href="">Booking List</a></li>
    </ul>
</div>

<div class="bread_crumbs_ui_div" style="width: 1211px">
    <ul id="crumbs_ui_custom">
        <#list buildingList as building>
            <li><a href="/mirror/csd/viewBuilding.erp?id=${building.id}">${building.nameAlias}</a></li>
        </#list>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="cidForm" id="cidForm" action="viewPaymentSummaryAll.erp" method="GET">
            <input type="text" name="range" id="range" placeholder="all, amount, =amount, amount-amount"/>
            <a onclick="openHelp();">Help(?)</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>
        <div id="ele1" class="content scaffold-list" role="main">
            <div class="headerDiv" id="headerDiv">
                <table class="headerTable" id="headTable">
                    <tr>
                        <td class="CompanyName">D<span style="color: #c04c05">O</span>RPAN Properties Limited</td>
                    </tr>
                    <tr>
                        <td class="CompanyName">Name of the Building : ${building.buildingName}</td>
                    </tr>
                    <tr>
                        <td class="address">
                        ${(building.totalFloorSize)/ ((building.numberOfUnit)/(building.numberOfFloor))} sft
                            :
                        ${(building.handOver)}
                        </td>
                    </tr>
                    <tr>
                        <td class="address"></td>
                    </tr>
                    <tr>
                        <td class="header"></td>
                    </tr>
                </table>
                <br/><br/>
                <hr/>
            </div>
            <div style="min-height: 1250px;">
                <hr/>
                <table id="bodyTable">
                    <tr>
                        <th class="apt">Apt</th>
                        <th class="name">Name of the Applicant</th>
                        <th class="cid">CID</th>
                        <th class="location">Notes</th>
                        <th class="mid">MID</th>
                        <th class="booking">Booking</th>
                        <th class="date">Date</th>
                    </tr>

                    <#list bookingList as booking>
                        <tr style="color: <#if booking.status = "CANCELLED">red</#if>">
                            <td class="apt">
                                <if booking.apt??>${booking.apt}</if>
                            </td>
                            <td class="name">
                                <if booking.applicant??> ${booking.applicant}</if>
                            </td>
                            <td class="cid">
                                <if booking.cid??>${booking.cid}</if>
                            </td>
                            <td class="location">
                                <if booking.location??>${booking.status}</if>
                            </td>
                            <td class="mid">
                                <if booking.mid??>${booking.mid}</if>
                            </td>
                            <td class="booking">
                                <if booking.booking??>${booking.booking}</if>
                            </td>
                            <td class="date">
                                <if booking.date??>${booking.date}</if>
                            </td>
                        </tr>
                    </#list>
                </table>
            </div>

            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>

</div>

<style>
    #bodyTable {
        border-collapse: collapse;
    }

    #bodyTable th {
        border: 2px solid black;
        border-collapse: collapse;
        padding: 5px 5px 5px 5px;
        text-align: center;
        font-weight: bolder;
        background-color: #d0d1ce;
    }

    #bodyTable tr td {
        border: 2px solid black;
        border-collapse: collapse;
        padding: 5px 5px 5px 5px;
    }

    #headTable tr td {
        border: 0px solid black;
        width: 1200px;
        text-align: center;
        font-size: 20px;
        font-weight: bolder;

    }

    .apt {
        width: 100px;
        text-align: center;
    }

    .cid, .location, .mid, .booking, .date {
        width: 150px;
        text-align: center;
    }

    .cid, .mid {
        font-weight: bolder;
    }

    .name {
        width: 500px;
    }
</style>
</@layout.gen_app_layout>