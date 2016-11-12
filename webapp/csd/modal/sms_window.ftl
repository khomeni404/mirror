<#if customer??>
<div class="modal fade" id="sms-window-modal" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content panel-success">
            <div class="modal-header panel-heading">

                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="alert-success-title">SMS to : ${customer.cellPhone!}</h3>
            </div>
            <div class="modal-body" id="alert-success-body" style="font-size: 18px">
                <div class="panel panel-default">
                    <div class="panel-body">

                        <div class="col-lg-12 text-center">

                            <div class="col-lg-4 text-left">
                                <button type="button" onclick="sendGenInfoSms('${customer.id!}');"
                                        class="btn btn-sm btn-info btn-block">Send Gen. Info.
                                </button>
                            </div>
                            <div class="col-lg-8 text-left">
                                <div class="form-group">
                                    <div class="form-group has-success">
                                        Send General Information of Apartment
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 text-left">
                                <button type="button" onclick="sendDueInfoSms('${customer.id!}');"
                                        class="btn btn-sm btn-danger btn-block">Send Due Info.
                                </button>
                            </div>
                            <div class="col-lg-8 text-left">
                                <div class="form-group">
                                    <div class="form-group has-success">
                                        Send Due Information of Apartment
                                    </div>
                                </div>
                            </div>
                            <form name="update-cheque-form" action="sendCustomSms.erp" method="POST">
                                <input type="hidden" value="" name="custSmsId" id="custSmsId"/>

                                <div class="col-lg-4 text-left">
                                    Compose SMS
                                </div>
                                <div class="col-lg-8 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            <textarea name="customSmsText" class="form-control" id="customSmsText"
                                                      cols="" rows="4" placeholder="Type SMS here"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-8 text-left">
                                    &nbsp;
                                </div>
                                <div class="col-lg-4 text-left">
                                    <div class="form-group">
                                        <div class="form-group has-success">
                                            <button type="button" onclick="sendCustomSms('${customer.id!}')"
                                                    class="btn btn-sm btn-success btn-block">Send SMS
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</#if>
