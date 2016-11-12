<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">


<br class="clear"/>
<div class="row">

    <div class="col-lg-1 text-left">
        <button type="button" class="btn btn-warning btn-block " id="missed"><i class="fa fa-bell blink"></i></button>
    </div>
    <div class="col-lg-1"> </div>
    <div class="col-lg-1">
        <button type="button" class="btn btn-success  btn-block fa fa-arrow-up" id="before"></button>
    </div>
    <div class="col-lg-1">
        <button type="button" class="btn btn-success  btn-block  fa fa-ticket" id="today"> Today</button>
    </div>
    <div class="col-lg-1">
        <button type="button" class="btn btn-success  btn-block  fa fa-arrow-down" id="after"></button>
    </div>

    <div class="col-lg-2"> </div>

    <div class="col-lg-1 text-right">
        <button type="button" class="btn btn-primary  btn-block fa fa-arrow-left" id="previous"></button>
    </div>
    <div class="col-lg-2 text-center">
        <input type="text" placeholder="YYYY-MM-DD" readonly name="searchDate"
               id="search-date" value="${date!}" class="form-control  text-center">
    </div>
    <div class="col-lg-1 text-left">
        <button type="button" class="btn btn-primary btn-block fa fa-arrow-right" id="next"></button>
    </div>
    <div class="col-lg-1 text-left"></div>

</div>
<br/>
<#--<div class="widget" style="width: 500px">-->
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <h3 class="text-left text-success">List  ${PageTitle}</h3>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Called By</th>
                            <th>Customer Name</th>
                            <th>Client Comment</th>
                            <th>Remarks</th>
                        <#-- <th>Active</th>-->
                            <th class="text-center">Call</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list historyList as history>
                            <tr>
                                <td>
                                ${history.calledBy.token.name}  <br/>
                                ${history.date}
                                </td>
                                <td>
                                    <strong>
                                    ${history.customer.CID}<br/></strong>
                                ${history.customer.name}
                                </td>
                                <td>${history.clientsComment}</td>
                                <td>
                                ${history.remarks}  <br/>
                                    <strong>Call on: </strong>${history.attentionOn}
                                </td>
                            <#--<td>${history.active?string("Active", "Inactive")}</td>-->
                                <td class="text-center">
                                    <#if history.active?string = "true">
                                        <button type="button" class="create-follow-up text-center"
                                                cid="${history.customer.CID}">
                                            <i class="fa fa-phone fa-2x"></i>
                                        </button>
                                    </#if>
                                </td>

                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        //var date = ;
        //$('#search-date').val(date);

    });
    $('#after').click(function () {
        window.location = '/mirror/csd/followUpHistoryList.erp?date=after&of=after';
    });
    $('#today').click(function () {
        window.location = '/mirror/csd/followUpHistoryList.erp?date=today&of=today';
    });
    $('#before').click(function () {
        window.location = '/mirror/csd/followUpHistoryList.erp?date=before&of=before';
    });

    $("#search-date").change(function () {
        var date = $('#search-date').val();
        //alert(date);
        window.location = '/mirror/csd/followUpHistoryList.erp?of=0&date='+date;
    });

    $('#previous').click(function () {
        var date = $('#search-date').val();
        window.location = '/mirror/csd/followUpHistoryList.erp?of=-1&date='+date;
    });

    $('#next').click(function () {
        var date = $('#search-date').val();
        window.location = '/mirror/csd/followUpHistoryList.erp?of=1&date='+date;
    });

    $('#missed').click(function () {
        window.location = '/mirror/csd/followUpHistoryList.erp?of=missed&date=';
    });

    $('.create-follow-up').click(function () {
        var cid = $(this).attr('cid');
        var thisTr = $(this).parent().parent();
        // window.location = '/mirror/csd/viewCustomer.erp?cidView=' + cid;
		window.open('/mirror/csd/viewCustomer.erp?cidView=' + cid,'_blank');

    });

    $(function () {
        $("#search-date").datepicker({
            format: 'yyyy/mm/dd',
            autoclose:true
        });
    });

</script>
<style>
    table tr td {
        font-size: 12px;
    }
</style>

<#--</div>-->
<#--End widget div-->


</@layout.gen_app_layout>
