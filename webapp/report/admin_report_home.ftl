<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

    <#include "../gen_template/resources_of_bootstrap.ftl">

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
                            <div class="col-lg-3 text-left">
                                <a href="/mirror/report/viewReport.erp?reportName=one">Print Report One</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=02_Director">2. Director</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=03_Shareholder_Exclude_Director">3. Shareholder (Exclude Director)</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=04_Shareholder_Include_Director">4. Shareholder (Include Director)</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=05_Director_And_Customer_Also"> 5. Director (And Customer Also)</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=06_Shareholder_And_Customer_Also__Exclude_Director"> 6. Shareholder (And Customer Also, Exclude Director)</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=07_Shareholder_And_Customer_Also__Include_Director"> 7. Shareholder (And Customer Also, Include Director)</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=one"> 8. Customer (All)</a>
                                <hr/>
                                <a href="/mirror/report/viewReport.erp?reportName=one"> 9. Customer (Exclude Director & Shareholder)</a>
                                <hr/>
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
</@layout.gen_app_layout>




