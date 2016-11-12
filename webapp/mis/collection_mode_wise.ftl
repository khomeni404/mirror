<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >

<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">

<#--<form action="/mirror/mis/showPaymentStatement.erp" method="get">-->
<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <label style="font-weight: bolder; font-family: 'Arial Black'">Collection : Mode Wise</label>
                <br/> <label style="color: red" id="message-ps"></label>
            </div>
            <div class="panel-body">
                <table class="table">
                    <thead>
                        <tr>
                            <th>SL</th><th>Mode Name</th><th>Total Tr.</th><th>Tr. Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                    <#list dataList as data>
                    <tr>
                        <td>${data.SL}</td>
                        <td>${data.MODE_NAME!}</td>
                        <td>${data.TOTAL_TR!}</td>
                        <td class="text-right">${data.TR_AMOUNT!}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#--</form>-->


</@layout.gen_app_layout>



