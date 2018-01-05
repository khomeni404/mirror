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
                    <h2>${(district.name)!'All Police Stations'}</h2>
                </div>
                <div class="content controls">
                    <table class="table table-striped">
                        <thead class="se-thead">
                        <tr>
                            <th>Thana ID</th>
                            <th>Name</th>
                            <th>District</th>
                            <th>Division</th>
                            <th>Country</th>
                            <th>Region</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list thanaList as thana>
                            <tr>
                                <td>PS-${thana.id!}</td>
                                <td>${thana.name!}</td>
                                <td>${thana.district.name!}</td>
                                <td>${thana.district.division.name!}</td>
                                <td>${thana.district.division.country.name!}</td>
                                <td>${thana.district.division.country.region.name!}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</@layout.gen_app_layout>
