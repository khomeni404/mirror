<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle!}" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">

<form action="saveCountry.se" method="post">
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
                        <div class="col-md-2 text-right"><b>Region</b></div>
                        <div class="col-md-8">
                            <select name="regionId" class="form-control se-select" style="width: 100%;" tabindex="-1">
                                <#list regionList as region>
                                    <option value="${region.id}">${region.name}</option>
                                </#list>
                            </select>
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
</@layout.gen_app_layout>
