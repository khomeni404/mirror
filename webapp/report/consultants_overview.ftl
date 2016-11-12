<div class="panel panel-default" style="background-color: #dff1c7">
    <div class="panel-body">
        <div class="row">
            <div class="col-lg-2 text-left" style="font-size: 20px; font-weight: bold;">Consultants' Overview</div>
            <div class="col-lg-10 text-left" style="border-left: 3px solid white">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                  <label>1. Teacher List : Consulted Project Status (According Department) </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <select name="departmentId-CO-1" id="departmentId-CO-1" class="form-control">
                                    <option value="null">--Select a Department--</option>
                                    <option value="1">Civil Engineering</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <button type="button" id="downloadReport-CO-1"
                                        class="btn btn-success"><span
                                        class="fa fa-file-pdf-o"></span> Download Report
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group has-success">
                                  <label class="">2. Teacher List : Teachers & Received Amount (According Department) </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <select name="departmentId-CO-1" id="departmentId-CO-2" class="form-control">
                                    <option value="null">--Select a Department--</option>
                                    <option value="1">Civil Engineering</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <button type="button" id="downloadReport-CO-2"
                                        class="btn btn-success"><span
                                        class="fa fa-file-pdf-o"></span> Download Report
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<script>
    $('#downloadReport-CO-1').click(function () {
        var deptId =  $('#departmentId-CO-1').val();
        if ([deptId].indexOf("null") > -1) {
            ExpandSelect("departmentId-CO-1");
        } else {
            window.location.href = '/mirror/report/teacherProjects.erp?deptId=' + deptId;
        }
    });

    $('#downloadReport-CO-2').click(function () {
        var deptId =  $('#departmentId-CO-2').val();
        if ([deptId].indexOf("null") > -1) {
            ExpandSelect("departmentId-CO-2");
        } else {
            window.location.href = '/mirror/report/teacherFinance.erp?deptId=' + deptId;
        }
    });

</script>
