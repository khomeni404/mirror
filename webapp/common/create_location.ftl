<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >
    <#assign ctx = rc.getContextPath()/>
<!--Resources For Bootstrap-->
    <#include "../gen_template/resources_of_bootstrap.ftl">
<link rel="stylesheet" href="../resources/js/extjs/resources/css/ext-all.css" type="text/css">

<form action="${ctx}/csd/saveLocation.erp" method="POST">
    <input type="hidden" name="custId" value="${custId!}"/>
    <input type="hidden" name="errMsg" value=""/>
    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label style="font-weight: bolder; font-family: 'Arial Black'">New Location</label>
                    <br/> <label style="color: red" id="message-ps"></label>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-8 text-center">
                            <span style="color: red">${errMsg!}</span>
                            <div class="form-group  text-left">
                                <b>Location :</b> <input type="text" name="name" class="form-control" style="width: 100%"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8 text-center">
                            <div class="form-group  text-left">
                                <b>Country :</b> <input list="countryList"  class="form-control" style="width: 100%"
                                                      name="country" id="country" placeholder=""/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 text-left">
                            <button type="submit" id="view-customer-payment-month"
                                    class="btn  btn-block btn-success">
                                Save
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</form>
<datalist id="countryList" autocomplete="off">
    <#list countryList as country>
    <option value="${country}">
    </#list>
</datalist>
</@layout.gen_app_layout>


