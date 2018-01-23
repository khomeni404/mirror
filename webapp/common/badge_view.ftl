<#import "../se_template/org_layout.ftl" as layout>
<style>
    .col-lg-3 {
        height: 40px; background-color: #d3c4a8;
    }
    .col-lg-9 {
        height: 40px; background-color: #acc5d3;
    }
</style>
<@layout.org_layout "Test" >
    <#assign ctx = rc.getContextPath()/>
    <#include "../se_template/array.ftl">
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <div class="block se-block">
            <div class="header se-header">
                <h2>Badge View</h2>
            </div>
            <div class="content controls">
                <div class="col-lg-12">
                    <div class="form-row">
                        <div class="col-lg-3" style=""><b>Badge Name</b></div>
                        <div class="col-lg-9">${(badge.name)!}</div>
                    </div>
                    <div class="form-row">
                        <div class="col-lg-3"><b>Badge Description</b></div>
                        <div class="col-lg-9">${(badge.description)!}</div>
                    </div>
                    <#--<div class="form-row">
                        <div class="col-lg-3"><b>Total Member</b></div>
                        <div class="col-lg-9">${badge.memberList?size!}</div>
                    </div>-->
                    <div class="form-row">
                        <div class="col-lg-3"><b></b></div>
                        <div class="col-lg-9">
                            <a style="color: #0000ff" href="${ctx}/edu/memberList.se?badgeId=${badge.id}">View Member
                                List</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.org_layout>
