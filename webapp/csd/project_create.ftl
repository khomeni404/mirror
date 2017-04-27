<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

    <#include "../gen_template/resources_of_bootstrap.ftl">
<style>
    .se-thead tr th{
        background-color: #808080;
        font-weight: bold;
        text-transform: uppercase;
    }
</style>
<div class="row">
    <div class="col-lg-12 text-center">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead class="se-thead">
                        <tr>
                            <th>Name</th>
                            <th>location</th>
                            <th>address</th>
                            <th>hot Line</th>
                            <th>number Of Building</th>
                            <th>land Area</th>
                        </tr>
                        </thead>
                        <#list projectList as project>
                            <tr>
                                <td>${project.projectName!}</td>
                                <td>${(project.location)!}</td>
                                <td>${(project.address)!}</td>
                                <td>${(project.hotLine)!}</td>
                                <td>${(project.numberOfBuilding)!}</td>
                                <td>${(project.landArea)!}</td><#--
                                <td>${(project.landArea)!}</td>
                                <td><a href="/mirror/csd/viewBuilding.erp?id=${project.id}">View</a></td>-->
                            </tr>
                        </#list>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#heldOn").datepicker({
            format: 'dd/mm/yyyy',
            autoclose: true
        });
    });


</script>


</@layout.gen_app_layout>

