<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="block se-block">
                <div class="header se-header">
                   <h2>Country List
                   <#if region??>: ${(region.name)!}</#if>
                   </h2>
                </div>
                <div class="content controls">
                    <table class="table table-striped">
                        <thead class="se-thead">
                        <tr>
                            <th class="col-lg-1">ID</th>
                            <th class="col-lg-5">Name</th>
                            <th class="col-lg-2">Total Division</th>
                            <th class="col-lg-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list countryList as country>
                            <tr>
                                <td>${country.id!}</td>
                                <td>${country.name!}</td>
                                <td class="text-right">${(region.divisionList?size)!}</td>
                                <td>
                                    <a style="color: #0000ff" href="${ctx}/admin/divisionList.se?countryId=${country.id}">Division List</a>
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
