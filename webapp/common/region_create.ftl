<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">
<form action="saveRegion.se" method="post">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="block se-block">
                <div class="header se-header">
                    <h2>Add New ${PageTitle!}</h2>
                </div>
                <div class="content controls">
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Name</b></div>
                        <div class="col-md-8"><input type="text" name="name" id="name" class="form-control"
                                                     value=""/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Description</b></div>
                        <div class="col-md-8"><textarea name="description" id="description" cols="3"
                                                        class="form-control se-textarea"></textarea>
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="col-md-10"></div>
                        <div class="col-md-2">
                            <input type="submit" class="form-control btn btn-success" value="Save ${PageTitle!}"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <div class="block se-block">
            <div class="header se-header">
                Division List
            </div>
            <div class="content controls">
                <table class="table table-striped">
                    <thead class="se-thead">
                    <tr>
                        <th class="col-lg-1">ID</th>
                        <th class="col-lg-7">Name</th>
                        <th class="col-lg-2 text-right">Total Country</th>
                        <th class="col-lg-2"></th>
                    </tr>
                    </thead>
                    <tbody>

                        <#list regionList as region>
                        <tr>
                            <td>${region.id!}</td>
                            <td>${region.name!}</td>
                            <td class="text-right">${(region.countryList?size)!}</td>
                            <td>
                                <a style="color: #0000ff" href="${ctx}/admin/countryList.se?countryId=${region.id}">Country List</a>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</@layout.gen_app_layout>
