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
                    <h2>
                        ${(division.name)!'All District List'}
                    </h2>
                </div>
                <div class="content controls">
                    <table class="table table-striped">
                        <thead class="se-thead">
                        <tr>
                            <th>SL</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Area</th>
                            <th>Population</th>
                            <th>Density</th>
                            <th>Division</th>
                            <th>Thana</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list districtList as district>
                            <tr>
                                <td>${district?index+1}</td>
                                <td>D-${district.id!}</td>
                                <td>${district.name!}</td>
                                <td>${district.area!}</td>
                                <td>${district.population!}</td>
                                <td>${district.populationDensity!}</td>
                                <td>${district.division.name!}</td>
                                <td>
                                    <a style="color: #0000ff" href="${ctx}/admin/thanaList.se?districtId=${district.id}">Thana List</a>
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
