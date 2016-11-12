<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

    <#include "../gen_template/resources_of_bootstrap.ftl">

<#--

<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap/bootstrap-datepicker.css">
<script src="../resources/js/bootstrap/bootstrap-datepicker.js"></script>

-->

<#--<script src="../resources/js/jquery-1.9.1.min.js"></script>-->
<#--<script type="text/javascript" src="../resources/js/bootstrap/bootstrap.min.js"></script>-->

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="/mirror/csd/csdConfigurationHome.erp">CSD Home</a></li>
        <li><a href="/mirror/csd/incentiveDistList.erp?status=all">Incentive List</a></li>
        <li><a href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>

<#--<div class="widget" style="width: 500px">-->
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-left text-success">Create ${PageTitle}</h3>

                <form name="dataForm" id="dataForm" action="saveIncentiveDist.erp" method="POST">
                    <div class="col-lg-12 text-center">


                        <div class="row">
                            <div class="col-lg-2 text-left">
                                Customer Identity (CID):
                            </div>
                            <div class="col-lg-4 text-center">
                                <div class="form-group">
                                    <select name="salesMan" id="salesMan" class="form-control">
                                        <#list customerList as customer>
                                            <option value="${customer.id}">${customer.CID} : ${customer.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-1 text-left">
                                <button type="button" id="viewDistribution"
                                        class="btn btn-success">
                                    View Distribution
                                </button>

                            </div>
                            <div class="col-lg-3 text-left">

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-2 text-left">
                                Application Date:
                            </div>
                            <div class="col-lg-10 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" placeholder="YYYY-MM-DD" readonly name="applicationDate"
                                           id="applicationDate" class="form-control" onclick="clearErrMsg()">
                                    <span for="errMsgDate" id="errMsgDate" class="errMsg text-left text-danger"></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-2 text-left">
                                Amount (Sales Man):
                            </div>
                            <div class="col-lg-10 text-left">
                                <div class="form-group  text-left">
                                    <div class="form-group has-success">
                                        <input type="text" name="salesManAmount" id="salesManAmount"
                                               class="form-control" onclick="clearErrMsg()">
                                        <span for="errMsgSM" id="errMsgSM" class="errMsg text-danger"></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-2 text-left">
                                Amount (Staff):
                            </div>
                            <div class="col-lg-10 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" name="personnelAmount" id="personnelAmount"
                                           class="form-control" onclick="clearErrMsg()">
                                    <span for="errMsgSa" id="errMsgSa" class="errMsg text-left text-danger"></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-3 text-left">
                                <span for="errMsgTotal" id="errMsgTotal" class="errMsg text-left text-danger"></span>
                            </div>
                            <div class="col-lg-9 text-left">
                                <button type="reset" class="btn btn-primary">Reset</button>
                                <button type="button" id="saveClient" onclick="saveIncentiveDist()"
                                        class="btn btn-success">
                                    Send To CCD
                                </button>
                            </div>
                        </div>

                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function saveIncentiveDist() {
        var dateF = $("#applicationDate");
        var date = dateF.val();
        if (date == "") {
            $('#errMsgDate').html("This field cant be empty");
            return false;
        }
        var manF = $("#salesManAmount");
        var manAmount = manF.val();

        if (manAmount == "") {
            manF.val(0);
        } else if (isNaN(manAmount)) {
            $('#errMsgSM').html("Must put a number.");
            return false;
        }


        var staffF = $("#personnelAmount");
        var staffAmount = staffF.val();

        if (staffAmount == "") {
            staffF.val(0);
        } else if (isNaN(staffAmount)) {
            $('#errMsgSa').html("Must put a number.");
            return false;
        }
        if($("#salesManAmount").val()+$("#personnelAmount").val() == 0){
            $('#errMsgTotal').html("Something went wrong. Total = 0 ?");
            return false;
        }
        document.dataForm.submit();
    }

    function clearErrMsg() {
        $('.errMsg').html("");
    }

    function loadDistData() {
        var cid = $('#salesMan');
        alert(cid.val());
        //alert($('#cid').val());
        //return false;
        $.ajax({
            url: '/mirror/csd/getIncentiveDistData.erp',
            data: {
                cid: cid.val()
            },
            dataType: 'json',
            success: function (data) {
                if (data.msg == "No") {
                    alert('Sorry');
                } else {
                    alert("got")
                }
            }
        })
    }

    $('#viewDistribution').click(function () {
        var id = $('#salesMan').val();
        window.location = "/mirror/csd/viewIncentiveDist.erp?id=" + id;
    });


    $(function () {
        $("#applicationDate").datepicker({
            format: 'yyyy/mm/dd',
            autoclose:true
        });
    });


</script>

<#--</div>-->
<#--End widget div-->

</@layout.gen_app_layout>














<!--Form right column--><#--
                    <div class="col-lg-6 text-center">
                        <div class="col-lg-4 text-left">
                            Customer Name:
                        </div>
                        <div class="col-lg-8 text-center">
                            <div class="form-group">
                                <div class="form-group has-success">
                                    <input type="text" name="customerName" id="customerName" class="form-control" disabled>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-4 text-left">
                            Total Apartment Price :
                        </div>
                        <div class="col-lg-8 text-center">
                            <div class="form-group">
                                <div class="form-group has-success">
                                    <input type="text" name="tv" id="tv" class="form-control" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 text-left">
                            Distributed Incentive:
                        </div>
                        <div class="col-lg-8 text-center">
                            <div class="form-group">
                                <div class="form-group has-success">
                                    <input type="text" name="distributed" id="distributed" class="form-control" disabled>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 text-left">
                            Previous Incentive(s):
                        </div>
                        <div class="col-lg-8 text-center">
                            <div class="form-group">
                                <textarea name="pastIncentive" id="pastIncentive" class="form-control" disabled
                                          rows="3"></textarea>
                            </div>
                        </div>





                    </div>-->
