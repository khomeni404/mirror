<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
    <#assign ctx = rc.getContextPath()/>
<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">
<style>
    .a {
        width: 180px;
        font-size: 20px;
        background-color: #8c8c8c
    }

    .b {
        color: darkblue;
        font-size: 17px;
    }

    .a2 {
        width: 35%;
        height: 30px;
        font-weight: bold;
        background-color: #d3d3d3;
        padding-left: 7px;
    }

    .b2 {
        width: 15%;
        height: 30px;
        background-color: #d8ecd0;
        text-align: center;
    }
</style>

<div class="row">
    <div class="col-lg-2 pull-left">
        <button type="button" onclick="viewPaymentReport()"
                class="btn  btn-block btn-success">
            Payment Report
        </button>
    </div>
    <div class="col-lg-2 pull-left">
        <button type="button" onclick="viewPaymentStatement()"
                class="btn  btn-block btn-success">
            Payment STM
        </button>
    </div>
    <div class="col-lg-2 pull-left">
        <button type="button" onclick="viewInstallmentStatement()"
                class="btn  btn-block btn-success">
            Installment STM
        </button>
    </div>
    <div class="col-lg-1 pull-right">
        <a href="#" class=""><i class="fa fa-arrow-right btn btn-info"
                                onclick="next('${cid}')"></i></a>
    </div>
    <div class="col-lg-1 pull-right">
        <a href="#"><i class="fa fa-arrow-left btn btn-info"
                       onclick="back('${cid}')"></i></a>
    </div>
</div>

<div class="row">
    <#if customer??>
        <div class="col-lg-8 pull-left"><br/>
            <label style="font-weight: bolder; font-size: x-large; font-family: 'Arial Black'">Customer Details</label>
        </div>

        <div class="col-lg-4 pull-left">
            <div class="row text-left">
                <table style="width: 100%">
                    <tr>
                        <td class="a2">Total Inst.</td>
                        <td class="b2">${customer.totalInst}</td>
                        <td class="a2">Paid Inst.</td>
                        <td class="b2">${customer.paidInst}</td>
                    </tr>
                    <tr>
                        <td class="a2">Recoverable Inst.</td>
                        <td class="b2">${customer.recoverableInst}</td>
                        <td class="a2">Due Inst.</td>
                        <td class="b2">${customer.dueInst}</td>
                    </tr>
                </table>  <br/>
            </div>
        </div>
        <div class="col-lg-8 pull-left">

            <table id="simple-table"
                   class="table table-striped table-bordered table-hover">
                <tbody>
                <tr>
                    <td class="a">Customer ID</td>
                    <td class="b" colspan="3"><b>${customer.CID}</b></td>
                </tr>

                <tr>
                    <td class="a">Customer Name:</td>
                    <td class="b" colspan="3"
                        style="color: <#if customer.status == "Cancelled" || customer.status == "Refunded">red</#if>">${customer.name}</td>
                </tr>

                <tr>
                    <td class="a">Reference:</td>
                    <td class="b" colspan="3">${customer.referenceBy.name} [${customer.referenceBy.type}
                        - ${customer.referenceBy.mid}]
                    </td>
                </tr>

                <tr>
                    <td class="a">Booking Date :</td>
                    <td class="b">${customer.bookingDate}</td>

                    <td class="a">Mark-up Key:</td>
                    <td class="b">${customer.keyName}</td>
                </tr>


                <tr>
                    <td class="a">Phone :</td>
                    <td class="b">${customer.cellPhone} / ${customer.altPhone}</td>

                    <td class="a">Email :</td>
                    <td class="b">${customer.emailAddress}</td>
                </tr>

                <tr>
                    <td class="a">Offer :</td>
                    <td class="b">${customer.offer.offerName}</td>

                    <td class="a">Building :</td>
                    <td class="b" colspan="3">${customer.building.buildingName}</td>
                </tr>
                <tr>
                    <td class="a">AID:</td>
                    <td class="b">${customer.building.nameAlias} - ${customer.AID}</td>

                    <td class="a">Size :</td>
                    <td class="b">${customer.floorSize}</td>
                </tr>
                <tr>
                    <td class="a">Price :</td>
                    <td class="b">${customer.price}/- per SFT</td>

                    <td class="a">Status :</td>
                    <td class="b <#if customer.status == "Refunded">blink</#if>"
                        style="color: <#if customer.status == "Cancelled" || customer.status == "Refunded">red</#if>
                                ">${customer.status}</td>
                </tr>
                <tr>
                    <td class="a">Location :</td>
                    <td class="b" colspan="3">${(customer.location.toString())!}</td>
                </tr>
                <tr>
                    <td class="a">Address :</td>
                    <td class="b" colspan="3">${customer.mailingAddress}</td>
                </tr>
                <tr>
                    <td class="a">Note :</td>
                    <td class="b" colspan="3" style="font-weight: normal">${customer.notes}</td>
                </tr>
                </tbody>
            </table>
            <br class="clear"/>
        </div>
        <div class="col-lg-4 text-center">

            <div class="row">
                <img src="../resources/images/customer/${customer.CID}.jpg"
                     class="img-thumbnail customer-profile-picture"
                     alt="${customer.name}"
                     width="304"
                     height="236"> <br/>
                <label for="" id="avatar-name">${customer.name}</label>
                <hr/>
                <a href="#" class="pull-right"><i class="fa fa-pencil btn btn-danger"
                                                  onclick="editCustomer('${customer.id}')"> Edit</i></a> &nbsp; &nbsp;
                <a href="#" class="pull-left"><i class="fa fa-plus btn btn-info"
                                                  onclick="window.location = '${ctx}/common/addCustomerBadge.erp?id=${customer.id}'"> Add Badge</i></a>

            </div>

        </div>
    <#else >
        <hr/>
        <hr/>
        <hr/>
        <div class="col-lg-12 text-danger"><label class="blink" style="font-size: 28px">No customer (${cid}) found
            !</label></div>
    </#if>
</div>

    <#if customer??>
    <div class="row" id="print-div">
        <div class="col-lg-12 text-center">
            <div class="panel panel-default">
                <div class="panel-heading"><label
                        style="font-weight: bolder; font-size: x-large; font-family: 'Arial Black'">Create
                    Conversation</label>
                </div>
                <div class="panel-body">
                    <form name="historyForm" id="historyForm" action="saveFollowUpHistory.erp" method="POST">
                        <input type="hidden" value="${customer.id}" name="customerId" id="customerId"/>
                        <input type="hidden" value="1" name="attentionTo" id="attentionTo"/>

                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <tr>
                                    <td style="width:300px">Client's Comment</td>
                                    <td style="width:300px">Remarks</td>
                                    <td style="width:300px">Next Knocking Date</td>
                                </tr>
                                <tr>
                                    <td rowspan="2">
                            <textarea style="width: 300px; font-size: 20px" name="comments" id="comments"
                                      placeholder="Client's Comment / Caller Message"></textarea>
                                    </td>
                                    <td rowspan="2">
                            <textarea style="width: 300px; font-size: 20px" name="remarks" id="remarks"
                                      placeholder="Client's Comment / Caller Message"></textarea>
                                    </td>
                                    <td>
                                        <input type="text" name="attentionOn" id="attention-on"
                                               placeholder="YYYY-MM-DD"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <button type="button" onclick="submitForm()"
                                                        class="btn btn-sm btn-primary">Save Conversation
                                                </button>

                                                <button type="button" id="sms-window-btn"
                                                        class="btn btn-sm btn-success">Use SMS
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <#--History Div Show-->
    <div class="row" id="print-div">
        <div class="col-lg-12 text-center">
            <div class="panel panel-default">
                <div class="panel-heading"><label
                        style="font-weight: bolder; font-size: x-large; font-family: 'Arial Black'">Conversation
                    History</label>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                            <tr style="font-weight: bold; background-color: #c0ad99">
                                <th style="width: 120px">Date</th>
                                <th style="width: 250px">Called By</th>
                                <th style="width: 250px">Client's Comment</th>
                                <th style="width: 250px">Remarks</th>
                                <th style="width: 120px">Knocking Date</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if historyList??>
                                    <#list historyList as history>
                                    <tr>
                                        <td>${history.date!}</td>
                                        <td>${history.calledBy.token.name!}</td>
                                        <td>${history.clientsComment!}</td>
                                        <td>${history.remarks!}</td>
                                        <td>${history.attentionOn!}</td>
                                    </tr>
                                    </#list>
                                </#if>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
    </#if>
    <#include "../csd/modal/sms_window.ftl"/>

<div class="modal fade" id="alertModal-sms" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content panel-success">
            <div class="modal-header panel-heading">

                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="sms-title">(Dynamic Title Here)</h3>
            </div>
            <div class="modal-body" id="sms-body" style="font-size: 18px">
                (Dynamic Message Here)
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">OK</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
    $(function () {
        $(".viewCustomer").focus();

        $("#attention-on").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
        // customer-profile-picture
        $(".customer-profile-picture").error(function () {
            $(this).attr('src', '../resources/images/profile-avatar.png');
            $('#avatar-name').html("No image found !")
        });
    });

    $('#sms-window-btn').click(function () {
        $('#sms-window-modal').modal("show");
    });

    function sendCustomSms(id) {
        $('#sms-window-modal').modal("hide");
        var text = $('#customSmsText').val();
        // alert(id + text);
        $.ajax({
            url: "${ctx}/csd/sendCustomSms.erp",
            data: {
                custSmsId: id,
                customSmsText: text
            },
            type: "GET",
            success: function (result) {
                var jsonData = JSON.parse(result);
                makModalAlert('sms', jsonData.msgHead, jsonData.msgBody);
            },
            failure: function () {
                alert("Sorry");
            }
        });
    }

    function sendDueInfoSms(id) {
        $('#sms-window-modal').modal("hide");
        $.ajax({
            url: "${ctx}/csd/sendDueInfoSms.erp",
            data: {
                id: id
            },
            type: "GET",
            success: function (result) {
                var jsonData = JSON.parse(result);
                makModalAlert('sms', jsonData.msgHead, jsonData.msgBody);
            },
            failure: function () {
                alert("Sorry");
            }
        });
    }

    function sendGenInfoSms(id) {
        $('#sms-window-modal').modal("hide");
        $.ajax({
            url: "${ctx}/csd/sendGenInfoSms.erp",
            data: {
                id: id
            },
            type: "GET",
            success: function (result) {
                var jsonData = JSON.parse(result);
                makModalAlert('sms', jsonData.msgHead, jsonData.msgBody);
            },
            failure: function () {
                alert("Sorry");
            }
        });
    }

    function makModalAlert(type, head, body) {
        $('#alertModal-' + type).modal('show');
        $('#sms-title').html(head);
        $('#sms-body').html(body);
    }


    function viewPaymentReport() {
        window.location = '/mirror/csd/viewPaymentReport.erp?cid=${cid}';
    }
    function viewPaymentStatement() {
        window.location = '/mirror/csd/viewPaymentStatement.erp?cid=${cid}';
    }
    function viewInstallmentStatement() {
        window.location = '/mirror/csd/viewInstallmentStatement.erp?cid=${cid}';
    }
    function editCustomer(id) {
        window.location = '/mirror/csd/editCustomer.erp?id=' + id + '&errMsg=';
    }
    function back(cid) {
        //var cid = ;
        var id = cid.substring(4, 10);
        var nextId = parseInt(id) - 1;
        window.location = '/mirror/csd/viewCustomer.erp?cidView=' + nextId;
    }
    function next(cid) {
        var id = cid.substring(4, 10);
        var nextId = parseInt(id) + 1;
        window.location = '/mirror/csd/viewCustomer.erp?cidView=' + nextId;
    }

    function submitForm(date) {
        //document.historyForm.submit();
        var comment = $('#comments');
        var remark = $('#remarks');
        var date = $('#attentionOn');
        if (comment.val() == "") {
            alert("Please insert Comment");
            comment.focus();
            return false;
        } else if (remark.val() == "") {
            alert("Please insert remark");
            remark.focus();
            return false;
        } else if (date.val() == "") {
            alert("Please Insert Attention Date");
            date.focus();
            return false;
        } else {
            document.historyForm.submit();

        }
    }
</script>






</@layout.gen_app_layout>



