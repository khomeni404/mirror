<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>
<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">



<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>
    <style>

        table, hr {
            margin-left: 30px;
            margin-right: 10px;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            font-size: 13px;
            padding: 3px;
        }

        tr {
            height: 30px
        }

        .even {
            background-color: #d0e3e8
        }

        th {
            text-align: center;
            font-weight: bold;
            color: green;
            background-color: #b9b9b9;
        }

        .cid {
            font-weight: bold;
            color: green;
            text-align: center;
            width: 110px;
        }

        .date {
            width: 100px;
            text-align: center
        }

        .rNo {
            width: 70px;
            text-align: center
        }

        .sl {
            width: 40px;
            text-align: center
        }

        .name {
            width: 300;
        }

        .amount {
            width: 80px;
            text-align: center
        }

        .action {
            width: 150px;
            text-align: center;
        }
    </style>

    <div class="content">
        <form name="cidForm" id="cidForm" action="chequeListNotPassed.erp" method="GET">
            <input type="text" name="status" id="findFor" placeholder="all | N | Y | X " value=""
                   style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#findFor").focus();
                });</script>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/csd/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/csd/pdf.png"/></a>
        </form>
        <div id="ele1" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <h3><label style="color: #1c0eff">${PageTitle}</label></h3>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th>Sl.</th>
                        <th>Cheque Details</th>
                        <th>CID</th>
                        <th>Name</th>
                        <th>Amount</th>
                        <th>Receipt Date</th>
                        <th>Receipt</th>
                        <th>Cheque Date</th>
                        <#if status == "Not Passed">
                            <th>Action</th>
                        <#else >
                            <th>Update By</th>

                        </#if>

                    </tr>
                    <#assign x = 1>
                    <#if chequeList?has_content>
                        <#list chequeList as cheque>
                            <#if cheque.status == status && cheque.paymentMethod == "Cheque">
                                <#if x%2==0>
                                <tr class=" not even"></#if>
                                <#if x%2!=0>
                                <tr class="not odd"></#if>
                                <td class="sl">${x}</td>
                                <td class="note">${cheque.note}</td>
                                <td class="cid">${cheque.customer.CID}</td>
                                <td class="name">${cheque.customer.name}</td>
                                <td class="amount">${cheque.amount}</td>
                                <td class="dateMr">${cheque.receiptDate}</td>
                                <td class="rNo">${cheque.receiptNo}</td>
                                <td class="dateCash">${cheque.cashDate}</td>
                                <#if cheque.status == "Not Passed">
                                    <td class="action">
                                        <input type="hidden" name="chequeDate${cheque.id}" id="chequeDate${cheque.id}"
                                               value="${cheque.cashDate}"/>
                                        <label id="${cheque.id}" code="1111"
                                               class="update-label" <#--onclick="abc(${cheque.id}, 1111)-->">
                                        <img title='Update' src='../resources/images/common/progress.png'/>
                                        </label>
                                        &nbsp;
                                        <label id="${cheque.id}" code="2222" class="update-label">
                                            <img title='Update' src='../resources/images/common/reject4.png'/>
                                        </label>
                                    </td>
                                <#else >
                                    <#if cheque.statusUpdateBy??>
                                        <td class="action">${cheque.statusUpdateBy.token.name}</td>
                                    <#else >
                                        <td>---</td>
                                    </#if>
                                </#if>
                                <#assign x = x + 1>
                            </tr>
                            <#else >

                            </#if>
                        </#list>
                    <#else >
                        <tr>
                            <td colspan="9" style="width: 1050px; text-align: center;"><h2>No Data found !</h2></td>
                        </tr>
                    </#if>
                </table>
            </div>
            <br/><br/>

            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $("#findFor").keyup(function (event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if (id.toUpperCase() != "ALL" || id.toUpperCase() != "ALL") {
                    document.getElementById("errorMsg").innerHTML = "Please insert ???";
                } else {
                    document.cidForm.submit();
                }
            }
        });

        function openHelp() {
            var content = "Please do any one of the following to get result<hr/>" +
                    "To get result for "
            Rejected
            List
            " type <b>'X / x'</b> and press Enter<br/>" +
            "To get result for "
            Processing
            List
            " type <b>'N / n'</b> and press Enter<br/>" +
            "To get result for "
            Passed
            " type<b> 'Y, y'</b> and press Enter<br/>";
            top.helpPan = window.open('', '', 'width=500, height=250, top=270, left=520, ')
            top.helpPan.document.writeln(
                    '<html><head><title>Help</title></head>'
                    + '<body bgcolor=white onLoad="self.focus()" style="font-family: calibri; color: green">'
                    + content
                    + '</body></html>'
            )
            top.helpPan.document.close()
        }


    </script>


</div>


<form name="update-cheque-form" action="updateChequeStatus.erp" method="GET">
    <div class="modal fade" id="update-cheque-form" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content panel-success">
                <div class="modal-header panel-heading">

                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title" id="alert-success-title">Update Check Status</h3>
                </div>
                <div class="modal-body" id="alert-success-body" style="font-size: 18px">
                    <div class="panel panel-default">
                        <div class="panel-body">

                            <div class="col-lg-12 text-center">

                                <div class="col-lg-4 text-left">
                                    CID
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            : <label for="" id="cid-text"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 text-left">
                                    Name
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            : <label for="" id="name-text"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 text-left">
                                    Cheque
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            : <label for="" id="chq-text"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 text-left">
                                    Amount
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            : <label for="" id="amount-text"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 text-left">
                                    MR No
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            : <label for="" id="mr-no-text"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 text-left">
                                    MR Date
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            : <label for="" id="receipt-date-text"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 text-left">
                                    Cheque Date
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="chq-group has-success">
                                            : <label for="" id="chq-date-text"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 text-left " id="cash-reject-date">
                                    Cash Date    <#--cash-->
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group has-success">
                                        <input type="text" placeholder="YYYY-MM-DD" readonly
                                               name="passedDate" id="cash-date-text" class="form-control"
                                               onclick="clearErrMsg()">
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel
                        <button type="button" class="btn btn-success" id="update-chq-btn"
                                onclick="updateCheque();" <#-- data-dismiss="modal"-->>Update Cheque
                        </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</form>

<script type="text/javascript">
    var mrId = "";
    var mrCode = "";
    function updateCheque() {
        //alert(); return false;
        var cashDate = $('#cash-date-text');
        if (cashDate.val() == "") return false;
        window.location = "updateChequeStatus.erp?passedDate=" + cashDate.val() + "&id=" + mrId + "&code=" + mrCode;

    }
    $('.update-label').click(function () {
        mrId = $(this).attr("id");
        mrCode = $(this).attr("code");
        var cid = $(this).parent().closest('td').siblings('.cid').text();
        var name = $(this).parent().closest('td').siblings('.name').text();
        var note = $(this).parent().closest('td').siblings('.note').text();
        var amount = $(this).parent().closest('td').siblings('.amount').text();
        var dateMr = $(this).parent().closest('td').siblings('.dateMr').text();
        var rNo = $(this).parent().closest('td').siblings('.rNo').text();
        var dateCash = $(this).parent().closest('td').siblings('.dateCash').text();
        //alert(v);
        $('#cid-text').html(cid);
        $('#name-text').html(name);
        $('#chq-text').html(note);
        $('#mr-no-text').html(rNo);
        $('#amount-text').html(amount);
        $('#receipt-date-text').html(dateMr);
        $('#chq-date-text').html(dateCash);
        $('#cash-date-text').val(reverseDate(dateCash, "/"));
        //alert();
        var button = $('#update-chq-btn');
        if (mrCode == 2222) {
            button.html("Reject Cheque");
            $('#cash-reject-date').html('Reject Date');
            button.attr('class', "btn btn-danger")
        } else {
            button.html("Update Cheque");
            $('#cash-reject-date').html('Cash Date');
            button.attr('class', "btn btn-success")
        }
        $('#update-cheque-form').modal('show');

    });

    $(function () {
        $("#cash-date-text").datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
    });
</script>


</@layout.gen_app_layout>