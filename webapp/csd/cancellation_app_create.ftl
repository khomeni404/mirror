<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">

<div class="bread_crumbs_ui_div" style="width: 611px">
<#-- <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="/mirror/csd/csdConfigurationHome.erp">CSD Home</a></li>
        <li><a href="#">BOD Meeting List</a></li>
        <li><a href="/mirror/admin/createBoardMeeting.erp">Create</a></li>
    </ul>-->
</div>
<br class="clear"/>

<#--<div class="widget" style="width: 500px">-->
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-left text-success">Cancellation App Create</h3>

                <div class="col-lg-7 text-center">
                    <form name="dataForm" id="dataForm" action="saveCancellationApp.erp" method="POST">

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Application Date:
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" placeholder="DD/MM/YYYY" readonly name="applicationDate"
                                           id="applicationDate" class="form-control" onclick="clearErrMsg()">
                                    <span for="errMsgDate" id="errMsgDate" class="errMsg text-left text-danger"></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Customer Identity (CID):
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group">
                                    <div class="form-group has-success">
                                        <input type="text" name="cid" id="cid" onkeyup="checkClient(this.value)"
                                               class="form-control" onclick="clearErrMsg()">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Applicant Name
                            </div>
                            <div class="col-lg-8 text-left">
                                <div class="form-group  text-left">
                                    <div class="form-group has-success">
                                        <input type="text" name="applicantName" id="applicantName"
                                               class="form-control" onclick="clearErrMsg()">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Deposited Amt
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  has-success text-left">
                                    <input type="text" name="depositedAmt" id="depositedAmt"
                                        value="0.0"   class="form-control" onclick="clearErrMsg()">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Demanded Amt
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  has-success text-left">
                                    <input type="text" name="demandedAmt" id="demandedAmt"
                                      value="0.0"     class="form-control" onclick="clearErrMsg()">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Ref Account Name
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  has-success text-left">
                                    <input type="text" name="refAccountName" id="refAccountName"
                                           class="form-control" onclick="clearErrMsg()">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Ref Account No
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  has-success text-left">
                                    <input type="text" name="refAccountNo" id="refAccountNo"
                                           class="form-control" onclick="clearErrMsg()">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Ref Bank
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  has-success text-left">
                                    <input type="text" name="refBank" id="refBank"
                                           class="form-control" onclick="clearErrMsg()">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Ref Branch
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  has-success text-left">
                                    <input type="text" name="refBranch" id="refBranch"
                                           class="form-control" onclick="clearErrMsg()">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                <span for="errMsgTotal" id="errMsgTotal" class="errMsg text-left text-danger"></span>
                            </div>
                            <div class="col-lg-8 text-left">
                                <button type="submit" class="btn btn-success">Save</button>
                                <button type="reset" class="btn btn-primary">Reset</button>
                            </div>
                        </div>


                    </form>
                </div>
                <div class="col-lg-5 text-center">
                    <div class="row">
                        <div class="col-lg-12 text-center" style="min-height: 320px">
                            <img id="cust-photo" src="" width="236" height="304" alt=""
                                 class="img-thumbnail customer-profile-picture backup_picture"/><br/>
                            <label for="" name="" id="customerName"
                                   class="cust-info customerName"></label>
                            <hr/>

                        </div>
                        <div class="col-lg-12 text-left">
                            <style>
                                table tr td {
                                    border-bottom: 2px solid black;
                                    padding: 5px 1px 5px 16px
                                }

                                .a {
                                    width: 180px;
                                    font-size: 20px;
                                    background-color: #8c8c8c
                                }

                                .b {
                                    color: darkblue;
                                    background-color: #dbdfe8;
                                    font-size: 17px;
                                }
                            </style>
                        <#-- Customer ID: <label for="" name="customerId" id="customerId"
                                            class="cust-info"></label>
                         <hr/>
                         Customer Name : <label for="" name="" id="customerName" class="customerName cust-info"></label>
                         <hr/>
                         Cell No : <label for="" name="cellNo" id="cellNo" class="cust-info"></label>
                         <hr/>
                         <label for="" name="address" id="address" class="cust-info"></label>-->
                            <table style="width: 100%">
                                <tr>
                                    <td class="a">Customer ID:</td>
                                    <td class="b" id="customerId"></td>
                                </tr>
                                <tr>
                                    <td class="a">Customer Name:</td>
                                    <td class="b customerName"></td>
                                </tr>
                                <tr>
                                    <td class="a">Cell Number:</td>
                                    <td class="b" id="cellNo"></td>
                                </tr>
                                <tr>
                                    <td class="a">Address:</td>
                                    <td class="b" id="address"></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>


            </div>
        </div>
    </div>
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <tr style="background-color: darkgray">
                            <th>applicationDate</th>
                            <th>CID</th>
                            <th>Customer Name</th>
                            <th>Status</th>
                            <th>Amount</th>
                            <th>A/C Info</th>
                            <th>Branch & Bank</th>
                        </tr>
                        <#list appList as application>
                            <tr>
                                <td>${application.applicationDate!}</td>
                                <td>${application.customer.CID!}</td>
                                <td>${application.customer.name!}</td>
                                <td>${util.getStatus(application.status)}</td>
                                <td>
                                    Deposited : ${application.depositedAmt}/- <br/>
                                    Demanded : ${application.demandedAmt}/-
                                </td>
                                <td>${application.refAccountName!}<br/>${application.refAccountNo!}</td>
                                <td>${application.refBranch!}<br/>${application.refBank!}</td>
                            <#--<td><a href="/mirror/admin/deleteApplication.erp?id=${application.id}">Delete</a></td>-->
                            </tr>
                            <tr>
                                <td>

                                </td>
                            </tr>
                        </#list>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#applicationDate").datepicker({
            format: 'dd/mm/yyyy',
            autoclose: true
        });

        $(".backup_picture").error(function () {
            $(this).attr('src', '../resources/images/common/backup_image.png');
        });
    });


    function checkClient(cid) {
        clearAll();
        //clearErrMsg();
        //var cid = $('#CID').val();
        var customerName = $('.customerName');
        var customerId = $('#customerId');
        var cellNo = $('#cellNo');
        var address = $('#address');
        var photo = $('#cust-photo');
        var refAccountName = $('#refAccountName');
        var applicantName = $('#applicantName');
        $.ajax({
            url: "${ctx}/ajax/getClientJsonData.erp",
            data: {
                cid: cid
            },
            type: "GET",
            success: function (result) {
                //makModalAlert('warning', 'Attention !', "Please type a BRTC number");
                var jsonData = JSON.parse(result);
                customerName.html(jsonData.NAME);
                refAccountName.val(jsonData.NAME);
                applicantName.val(jsonData.NAME);
                //alert(jsonData.NAME);
                customerId.html(jsonData.CID);
                cellNo.html(jsonData.CELL);
                address.html(jsonData.ADDRESS);
                photo.attr("src", "../resources/images/customer/" + jsonData.CID + ".jpg");
            },
            failure: function () {
                alert("Sorry");
            }
        });
    }

    function clearAll() {
        $('.customerName').html("<b style='color: red'>No Customer Found!</b>");
        $('#customerId').html("---");
        $('#cellNo').html("---");
        $('#address').html("---");
        $('#cust-photo').attr("src", "");
        $('#refAccountName').val("");
        $('#applicantName').val("");
    }


</script>

<#--</div>-->
<#--End widget div-->

</@layout.gen_app_layout>