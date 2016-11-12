<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="#">CSD Home</a></li>
        <li><a  href="paymentHome.erp">Payment Home</a></li>
        <li><a  href="#">Other Payment</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 800px">

    <div class="header" style="width: 800px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 850px;">
        <form name="cidForm" id="cidForm" action="findOtherPayment.erp" method="GET">
            <a href="createOtherPayment.erp" class="button icon approve">Add Payment</a>
            <input type="text" name="cid" id="cid" placeholder="CID : e.g. 012, 123 (Press Enter)"/>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img alt="Print"
                                                                                                                         src="../../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../../resources/images/csd/pdf.png"/></a>
        </form>

        <form name="dataForm" id="dataForm" action="findOtherPayment.erp" method="POST">
            <fieldset class="form">

                <div id="ele1" class="container_16">
                    <h2>${cid} : ${name}</h2>
                    <table>
                        <tr>
                            <th>Particular</th>
                            <th style="text-align: right">Amount</th>
                            <th style="text-align: center">Dead Line</th>
                            <th>Note</th>
                        </tr>
                        <#list otherPayments as op>
                            <tr class="">
                                <td class="a">${op.paymentType} (${op.paymentName})</td>
                                <td class="b">${op.amount}</td>
                                <td class="c">${op.deadLine}</td>
                                <td class="d">${op.note}</td>
                            </tr>
                        </#list>
                    </table>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->
    <style>
        table{margin-left: 100px}
        table, tr, th, td{border: 2px solid black; border-collapse: collapse; padding: 3px; font-size: 13px}
        th{color: #ffffff; background-color: #000000}
        .a, .d{width: 200px}
        .b, .c{width: 150; font-weight: bold}
        .b{text-align: right}
        .c{text-align: center}
    </style>
    <script type="text/javascript">
        function submitForm() {
            document.dataForm.submit();
        }


        $("#cid").keyup(function (event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if (id == "") {
                    document.getElementById("errorMsg").innerHTML = "Please insert CID";
                } else if (id.length > 3) {
                    document.getElementById("errorMsg").innerHTML = "CID too long !";
                } else if (id.length < 3) {
                    document.getElementById("errorMsg").innerHTML = "CID too short !";
                } else if (id.length == 3) {
                    document.cidForm.submit();
                }
            }
        });
        $(document).ready(function () {
            $("#cid").focus();
        });

    </script>

</div>
<#--End widget div-->

</@layout.gen_app_layout>
