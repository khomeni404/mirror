<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "Test" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../gen_template/resources_of_bootstrap.ftl">

<div class="row">
    <div class="col-lg-12"><h4 style="color: green">${message!}</h4></div>
</div>
<div class="row">
    <div class="col-md-7">
        <div class="block se-block">
            <div class="header se-header">
                <h2>Badge List</h2>
            </div>
            <div class="content controls">
                <table class="table table-striped">
                    <thead class="se-thead">
                    <tr style="background-color: #8d8d8d; padding: 5px 5px 5px 5px">
                        <th class="col-md-3">Name</th>
                        <th class="col-md-4">Description</th>
                        <th class="col-md-3">Customer</th>
                    </tr>
                    </thead>
                    <tbody>

                        <#list badgeList as badge>
                        <tr>
                            <td>${badge.name!}</td>
                            <td>${badge.description!}</td>
                            <td>
                                <form action="${ctx}/mis/getCustomerListCustomized.erp" method="POST">
                                    <input type="hidden" name="badgeId" value="${badge.id}"/>
                                    <input type="submit" class="btn btn-info" value="View Customer List"/>
                                </form>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="col-md-5">
        <form action="saveBadge.erp" method="post">
            <input type="hidden" name="badgeSign" value="user"/>

            <div class="row">
                <div class="col-md-12" style="background-color: #8d8d8d; padding: 5px 5px 5px 5px ">
                    <b>Add New Badge</b>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-12">
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
                            <input type="submit" value="Save Badge" class="btn btn-success"/>
                        </div>
                    </div>
                </div>
            </div>


        </form>
    </div>
</div>
</@layout.gen_app_layout>
