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
        <li><a href="#">BOD Meeting List</a></li>
        <li><a href="/mirror/admin/createBoardMeeting.erp">Create</a></li>
    </ul>
</div>
<br class="clear"/>

<#--<div class="widget" style="width: 500px">-->
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-left text-success">Create ${PageTitle}</h3>

                <form name="dataForm" id="dataForm" action="saveBoardMeeting.erp" method="POST">
                    <div class="col-lg-12 text-center">
                        <div class="row">
                        <#--<div class="col-lg-2 text-left">
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
                            </div>-->

                        </div>

                        <div class="row">
                            <div class="col-lg-2 text-left">
                                Held On:
                            </div>
                            <div class="col-lg-10 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" placeholder="DD/MM/YYYY" readonly name="heldOn"
                                           id="heldOn" class="form-control" onclick="clearErrMsg()">
                                    <span for="errMsgDate" id="errMsgDate" class="errMsg text-left text-danger"></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-2 text-left">
                                Meeting Number
                            </div>
                            <div class="col-lg-10 text-left">
                                <div class="form-group  text-left">
                                    <div class="form-group has-success">
                                        <input type="text" name="meetingName" id="meetingName"
                                               placeholder="e.g. 13, 42 (number only)"
                                               class="form-control" onclick="clearErrMsg()">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-2 text-left">
                                Number of Director (Attended)
                            </div>
                            <div class="col-lg-10 text-center">
                                <div class="form-group  text-left">
                                    <input type="text" name="remarks" id="remarks" placeholder="(number only)"
                                           class="form-control" onclick="clearErrMsg()">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-3 text-left">
                                <span for="errMsgTotal" id="errMsgTotal" class="errMsg text-left text-danger"></span>
                            </div>
                            <div class="col-lg-9 text-left">
                                <button type="submit" class="btn btn-success">Save</button>
                                <button type="reset" class="btn btn-primary">Reset</button>
                            </div>
                        </div>

                    </div>

                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                    <tr>
                        <th>Meeting Name</th>
                        <th>Held On</th>
                        <th>Remarks</th>
                        <th>Record On</th>
                        <th>Action</th>
                    </tr>
                    <#list meetingList as meeting>
                            <tr>
                                <td>${meeting.meetingName!}</td>
                                <td>${meeting.heldOn!}</td>
                                <td>${meeting.remarks!}</td>
                                <td>${meeting.recordOn!}</td>
                                <td><a href="/mirror/admin/deleteBoardMeeting.erp?id=${meeting.id}">Delete</a></td>
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
        $("#heldOn").datepicker({
            format: 'dd/mm/yyyy',
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
