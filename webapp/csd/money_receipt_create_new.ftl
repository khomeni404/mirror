<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">


<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="../home.erp">Dashboard</a></li>
        <li><a  href="moneyReceiptList.erp">All Receipts</a></li>
        <li><a  href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>

<#--<div class="widget" style="width: 500px">-->
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <form name="dataForm" id="dataForm" action="saveMoneyReceipt.erp" method="POST">
                    <input type="hidden" name="signature" id="signature" value="2"/>

                    <div class="col-lg-5 text-center">

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Customer ID:
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" name="CID" class="form-control myElement a0" fieldSl="0"
                                           onkeyup="checkClient()" id="CID" value="" placeholder="Customer Identity"/>
                                    <#if cidMsg??>${cidMsg}</#if>
                                    <label id="CIDErrMsg" class="errMsg" style="color: red"></label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Receipt No:
                            </div>
                            <div class="col-lg-8 text-left">
                                <div class="form-group  text-left">
                                    <input type="text" name="receiptNo" onkeyup="clearErrMsg()" id="receiptNo"
                                           class="form-control myElement a1" fieldSl="1" onclick="clearErrMsg()">
                                    <#if receiptNoMsg??>${receiptNoMsg}</#if>
                                    <label id="receiptNoErrMsg" class="errMsg" style="color: red"></label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Receipt Date:
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" name="receiptDate" onkeydown="clearErrMsg()"
                                           class="form-control myElement a2" fieldSl="2" id="receiptDate" placeholder="YYYY-MM-DD"
                                           onchange="setCashDate();clearErrMsg()" onblur="setCashDate();clearErrMsg()"
                                           onkeyup="setCashDate();clearErrMsg()"/>
                                    <label id="receiptDateErrMsg" class="errMsg" style="color: red"></label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Amount (BDT):
                            </div>
                            <div class="col-lg-8 text-left">
                                <div class="form-group  text-left">
                                    <div class="form-group has-success">
                                        <input type="text" name="amount"
                                               onchange="clearErrMsg()" onblur="clearErrMsg()" onkeyup="clearErrMsg()"
                                               class="form-control myElement a3" fieldSl="3" id="amount" placeholder="amount"
                                               value=""/>
                                        <label id="amountErrMsg" class="errMsg" style="color: red"></label>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Payment Method:
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  text-left">
                                    <select name="paymentMethod" class="form-control" id="paymentMethod">
                                        <option value="Cash">Cash</option>
                                        <option value="Cheque">Cheque</option>
                                        <option value="EFT">EFT</option>
                                        <option value="TT">TT</option>
                                        <option value="DD">DD</option>
                                        <option value="Incentive">Incentive</option>
                                        <option value="Profit">Profit</option>
                                        <option value="Others">Others</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Note::
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" name="note" id="note" onkeyup="clearErrMsg()"
                                           class="form-control myElement a4" fieldSl="4" placeholder="Note" value="---"/>
                                    <label id="noteErrMsg" class="errMsg" style="color: red"></label>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-lg-4 text-left">
                                Cash Date:
                            </div>
                            <div class="col-lg-8 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" name="cashDate" onkeyup="clearErrMsg()"
                                           class="form-control myElement a5" fieldSl="5" id="cashDate" placeholder="YYYY-MM-DD"
                                           value=""/>
                                    <label id="cashDateErrMsg" class="errMsg" style="color: red"></label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-3 text-left">
                                <span for="errMsgTotal" id="errMsgTotal" class="errMsg text-left text-danger"></span>
                            </div>
                            <div class="col-lg-9 text-left">
                                <button type="reset" class="btn btn-primary">Reset</button>
                                <button type="button" id="userCreate"  onclick="submitForm()"
                                        class="btn btn-success myElement a7" fieldSl="6">
                                    Save MR
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-1 text-center"></div>
                    <div class="col-lg-6 text-center">
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
                                    table tr td{border-bottom: 2px solid black; padding: 5px 1px 5px 16px}
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
                                        <td class="a">Customer ID: </td>
                                        <td class="b" id="customerId"></td>
                                    </tr>
                                    <tr>
                                        <td class="a">Customer Name: </td>
                                        <td class="b customerName"></td>
                                    </tr>
                                    <tr>
                                        <td class="a">Cell Number: </td>
                                        <td class="b" id="cellNo"></td>
                                    </tr>
                                    <tr>
                                        <td class="a">Address: </td>
                                        <td class="b" id="address"></td>
                                    </tr>
                                </table>
                            </div>

                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<#--</div>-->
<#--End widget div-->
</@layout.gen_app_layout>

<script type="text/javascript">


    $(function () {
        $("#receiptDate").datepicker({
            format: 'yyyy-mm-dd',
            autoclose:true
        });
        $("#cashDate").datepicker({
            format: 'yyyy-mm-dd',
            autoclose:true
        });
        //$("#receiptDate").datepicker({dateFormat: 'yy-mm-dd'});
        //$("#cashDate").datepicker({dateFormat: 'yy-mm-dd'});

        $(".a0").focus();
        $(".backup_picture").error(function () {
            $(this).attr('src', '../resources/images/common/backup_image.png');
        });
    });
    //var counter = 1;
    $(".myElement").keyup(function (event) {
        if (event.keyCode == 13) {
            var fieldSl = eval($(this).attr('fieldSl'));
            if(fieldSl != 5) {
                var newSl = eval(fieldSl + 1);
                $(".a" + newSl).focus();
            }else{
                submitForm();
            }
        }
    });

    function submitForm() {
        var receiptNo = $('#receiptNo').val();
        if (receiptNo === "") {
            $('#receiptNo').focus();
            $('#receiptNoErrMsg').html("Please put MR number");
            return false;
        }
        var mrDate = $('#receiptDate').val();
        if (!validateYYYYMMDD(mrDate)) {
            $('#receiptDate').focus();
            $('#receiptDate').select();
            $('#receiptDateErrMsg').html("Please check this date out !");
            return false;
        }

        var mrDate = $('#receiptDate').val();
        if (!validateYYYYMMDD(mrDate)) {
            $('#receiptDate').focus();
            $('#receiptDateErrMsg').html("Please check this date out !");
            return false;
        }
        var amount = $('#amount');
        if (!$.isNumeric(amount.val()) || amount.val() === "") {
            $('#amountErrMsg').html("Please insert amount")
            amount.focus();
            amount.select();
            return false;
        }
        var cid = $('#CID');
        if (cid.val() === "") {
            $('#CIDErrMsg').html("Please insert Customer ID")
            cid.focus();
            cid.select();
            return false;
        }
        var cashDate = $('#cashDate');
        if (!validateYYYYMMDD(cashDate.val())) {
            cashDate.focus();
            cashDate.select();
            $('#cashDateErrMsg').html("Please check this date out !");
            return false;
        }
        document.dataForm.submit();
    }

    function clearErrMsg() {
        $('.errMsg').html("");
    }

    function setCashDate() {
        var receiptDate = document.getElementById("receiptDate");
        var cashDate = document.getElementById("cashDate");
        cashDate.value = receiptDate.value;
    }

    function checkClient() {
        clearAll();
        //clearErrMsg();
        var cid = $('#CID').val();
        var customerName = $('.customerName');
        var customerId = $('#customerId');
        var cellNo = $('#cellNo');
        var address = $('#address');
        var photo = $('#cust-photo');
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
    }

</script>

