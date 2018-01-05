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
                    <h2>Division List</h2>
                </div>
                <div class="content controls">
                    <table class="table table-striped">
                        <thead class="se-thead">
                        <tr>
                            <th class="col-lg-1">ID</th>
                            <th class="col-lg-7">Name</th>
                            <th class="col-lg-2 text-right">Total Dist</th>
                            <th class="col-lg-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <#assign total = 0>
                        <#list divisionList as division>
                            <tr>
                                <td>${division.id!}</td>
                                <td>${division.name!}</td>
                                <td class="text-right">${(division.districtList?size)!}</td>
                                <td>
                                    <a style="color: #0000ff" href="${ctx}/admin/districtList.se?divisionId=${division.id}">District List</a>
                                </td>
                            </tr>
                            <#assign total = total + (division.districtList?size)!>
                        </#list>
                        <tr>
                            <td colspan="2" class="text-right">TOTAL DISTRICT</td>
                            <td class="text-right">${total!}</td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</@layout.gen_app_layout>
