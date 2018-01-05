<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">
    <#include "array.ftl">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="block se-block">
                <div class="header se-header">
                    Region List
                </div>
                <div class="content controls">
                    <table class="table table-striped">
                        <thead class="se-thead">
                        <tr>
                            <th class="col-lg-1">ID</th>
                            <th class="col-lg-7">Name</th>
                            <th class="col-lg-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list regionList as region>
                            <tr>
                                <td>${region.id!}</td>
                                <td>${region.name!}</td>
                                <#--<td class="text-right">${(region.districtList?size)!}</td>-->
                                <td>
                                    <a style="color: #0000ff" href="${ctx}/admin/countryList.se?regionId=${region.id}">Country List</a>
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
