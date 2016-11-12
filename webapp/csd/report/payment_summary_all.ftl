<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a href="reportHome.erp">Report Home</a></li>
        <li><a href="#">${PageTitle}</a></li>
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
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img  alt="Print"
                    src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img  alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>
        <div id="ele1" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">

            <h2><label style="color: #1c0eff">${reportHead}</label></h2>
            <table>
                <tr style="font-weight: bold; color: #0200ff">
                    <th class="sl">Sl</th>
                    <th class="cid">CID</th>
                    <th class="name">Customer Name</th>
                    <th class="">Progress</th>
                    <th class="amount">Paid Total</th>
                </tr>
                <#assign x = 1>
                <#assign grandTotal = 0>
                <#list summaries as summary>
                <tr class="${summary.status}  <#if x%2 == 0>even<#else > odd</#if>">
                    <td class="sl">${x}</td>
                    <td class="cid">${summary.cid}</td>
                    <td class="name">${summary.name}</td>
                    <td class="">
                        <progress value="${summary.paidAmount}" max="${summary.totalPrice}"
                                  style="background-color: red; width: 350px">
                    </td>
                    <td class="amount">
                        <a href="viewPaymentStatement.erp?cid=${summary.cid}" >${summary.paidAmount}</a>
                    </td>
                </tr>
                <#assign x = x + 1>
                    <#assign grandTotal = grandTotal + summary.paidAmount>
                </#list>
                <tr style="font-weight: bold; color: #0200ff; border-top: 3px double #000000">
                    <th colspan="4">Grand Total</th>
                    <th >${grandTotal}</th>
                </tr>
            </table>
            </div>

            <br/><br/>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span> </div>
        </div>
    </div>
    <script type="text/javascript">
        function submitForm() {
            document.dataForm.submit();
        }


        $("#range").keyup(function (event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
               document.cidForm.submit();
            }
        });
        $(document).ready(function () {
            $("#range").focus();
        });

        function openHelp() {
            var content = "Please do any one of the following to get result<hr/>"+
                    "Who paid upto 500/- type <b>'500'</b> and press Enter<br/>"+
                    "Who paid equal to 500/- type <b>'=500'</b> and press Enter<br/>"+
                    "Who paid between 500/- and 800/= type<b> '500-800'</b> and press Enter<br/>"+
                    "For all type<b> 'all'</b> and press Enter<br/>";
            top.helpPan = window.open('','','width=500, height=250, top=270, left=520')
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
        table{margin-left: 50px}
        table, th, td {

            border-collapse: collapse;
            font-size: 13px;
            padding: 2px 3px 2px 10px;
        }
        th{background-color: #de850c ; font-size: 19px; font-weight: bold;
        }
        tr{height:40px; border-bottom:1pt solid #5c5c5c;}          /*    */
        .amount{
            text-align: right; padding-right: 10px; font-weight: bolder;
        }
        .cid{width: 100px}
        .name{width: 350px}
        .odd{background-color: #e7e7e7
        }
        /*
        .even{background-color: #afc2c1
        }
        .odd{background-color: #c5d7d6
        } *  /


    </style>
</div>

</@layout.gen_app_layout>