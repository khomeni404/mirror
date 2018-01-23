<#import "../gen_template/gen_app_layout.ftl" as layout>
<#import "/spring.ftl" as spring/>

<@layout.gen_app_layout "${PageTitle}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">


<form action="updateCustomerBadge.erp" method="post">
    <@spring.formHiddenInput 'Customer.id'/>
    <input type="hidden" name="customerId" value="${Customer.id!}"/>

    <div class="row">
        <div class="col-md-12">
            <span style="font-weight: bold; font-size:15px">Badges List <br/>${Customer.CID!} - ${Customer.name!}</span>
        </div>
    <div class="col-md-3">
        <#assign c = 0>
        <#list dataList as badge>
            <div class="checkbox" style="padding-left: 18px">
                <label>
                    <input name="jsonArray" class="checkbox" ${badge.checked!}
                           value="{id:${badge.id!}}" type="checkbox" style="font-size: 15px"/>
                    <span class="lbl"> ${(badge.name)!'--'}</span>
                </label>
            </div>
            <#assign c = c+1>
            <#if c % 5 = 0>
            </div>
            <div class="col-md-3">
            </#if>
        </#list>
    </div>
    </div>

    <hr/>
${message!}
    <hr/>
    <input type="submit" value="Update Badge" class="btn btn-success"/>
</form>
<hr/>
<form action="saveBadge.erp" method="post">
    <input type="hidden" name="badgeSign" value="user"/>

    <div class="row">
        <div class="col-md-6" style="background-color: #8d8d8d; padding: 5px 5px 5px 5px ">
            <b>Add New Badge</b>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <div class="col-md-4"><b>Name : </b></div>
                <div class="col-md-8">
                     <input type="text" name="name" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-4"><b>Description : </b></div>
                <div class="col-md-8">
                     <input type="text" name="description" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-9"></div>
                <div class="col-md-3 pull-right">
                    <input type="submit" value="Update Badge" class="btn btn-success"/>
                </div>
            </div>
        </div>
    </div>


</form>
</@layout.gen_app_layout>