<div class="panel panel-default" style="background-color: #dff1c7">
    <div class="panel-body">
        <div class="row">
            <div class="col-lg-2 text-left" style="font-size: 20px; font-weight: bold;">Project Overview</div>
            <div class="col-lg-10 text-left" style="border-left: 3px solid white">
                <div class="row" style="border-bottom: 3px solid white">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <label>Please insert file number and click relevant button to download Report </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 text-right">File No:</div>
                    <div class="col-lg-2">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <input list="projectDataList-PO-1" name="fileNo-PO-1" id="fileNo-PO-1"
                                       class="form-control">
                            <#-- Data List code-->
                                <datalist id="projectDataList-PO-1" autocomplete="on">
                                <#list projectList as project>
                                <option value="${project.fileNo}">
                                </#list>
                                </datalist>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <label>1. Project Details : By File Number </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <button type="button" id="downloadReport-PO-1"
                                        class="btn btn-success downloadReport-PO"><span
                                        class="fa fa-file-pdf-o"></span> Download Report
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <label>2. Project History : By File Number</label>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <button type="button" id="downloadReport-PO-2"
                                        class="btn btn-success downloadReport-PO"><span
                                        class="fa fa-file-pdf-o"></span> Download History
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <label>2. Financial & Distribution Status : By File Number </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <button type="button" id="downloadReport-PO-3"
                                        class="btn btn-success downloadReport-PO"><span
                                        class="fa fa-file-pdf-o"></span> Download Report
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <label>2. Assignment Letter of the project : By File Number </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <div class="form-group has-success" id="">
                                <button type="button" id="downloadReport-PO-4"
                                        class="btn btn-success downloadReport-PO"><span
                                        class="fa fa-file-pdf-o"></span> Download Letter
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

    $('.downloadReport-PO').click(function () {
        var id = $(this).attr("id");
        var index = id.substring(id.length - 1, id.length);
        var file = $('#fileNo-PO-1');
        var fileNo = file.val();
        if ([fileNo].indexOf("") > -1) {
            $('#alertModal-warning').modal('show');
            $('#alert-warning-title').html("Attention !") ;
            $('#alert-warning-body').html("Please Put a File Number") ;
            file.focus();
            return;
        } else {
            if (index == 1)
                window.location.href = '/mirror/report/projectDetails.erp?fileNo=' + fileNo;
            if (index == 2)
                window.location.href = '/mirror/report/projectHistory.erp?fileNo=' + fileNo;
            if (index == 3)
                window.location.href = '/mirror/report/projectFinance.erp?fileNo=' + fileNo;
            if (index == 4)
                window.location.href = '/mirror/report/assignmentLetter.erp?fileNo=' + fileNo;
        }
    });

</script>
