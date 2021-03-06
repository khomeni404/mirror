<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

    <#include "../gen_template/resources_of_bootstrap.ftl">
<style>
    .se-thead tr th{
        background-color: #808080;
        font-weight: bold;
        text-transform: uppercase;
    }
</style>
<div class="row">
    <div class="col-lg-12 text-center" style="display: none">
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
                        <thead class="se-thead">
                        <tr>
                            <th>Name</th>
                            <th>Name Alias</th>
                            <th>Floor Sizes</th>
                            <th>Floor</th>
                            <th>Unit</th>
                            <th>Hand Over</th>
                            <th>Land Area</th>
                            <th></th>
                        </tr>
                        </thead>
                        <#list buildingList as building>
                            <tr>
                                <td>${building.buildingName!}</td>
                                <td>${(building.nameAlias)!}</td>
                                <td>${(building.floorSizes)!}</td>
                                <td>${(building.numberOfFloor)!}</td>
                                <td>${(building.numberOfUnit)!}</td>
                                <td>${(building.handOver)!}</td>
                                <td>${(building.landArea)!}</td>
                            <td><a href="/mirror/csd/viewBuilding.erp?id=${building.id}">View</a></td>
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
            autoclose: true
        });
    });


</script>


</@layout.gen_app_layout>

