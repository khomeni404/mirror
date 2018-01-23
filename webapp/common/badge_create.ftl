<#import "../se_template/org_layout.ftl" as layout>
<@layout.org_layout "Test" >
    <#assign ctx = rc.getContextPath()/>
<form action="saveBadge.se" method="post">
    <div class="row">
        <div class="col-md-6">
            <div class="block se-block">
                <div class="header se-header">
                    <h2>Add New ${PageTitle!}</h2>
                </div>
                <div class="content controls">
                    <div class="form-row">
                        <div class="col-md-4 text-right"><b>Name</b></div>
                        <div class="col-md-8"><input type="text" name="name" id="name" class="form-control"
                                                     value=""/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-4 text-right"><b>Description</b></div>
                        <div class="col-md-8"><textarea name="description" id="description" cols="3"
                                                        class="form-control se-textarea"></textarea>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-4 text-right"><b>Badge Sign</b></div>
                        <div class="col-md-8"><input type="text" name="badgeSign" id="badgeSign" class="form-control"
                                                     value=""/>
                        </div>
                    </div>


                    <div class="form-row">
                        <div class="col-md-10"></div>
                        <div class="col-md-2">
                            <input type="submit" class="form-control btn btn-success" value="Save"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="block se-block">
                <div class="header se-header">
                    <h2>Existing Badge List</h2>
                </div>
                <div class="content controls">
                    <div class="col-lg-12" style="padding: 10px 10px 10px 10px">
                        <div class="list-text">
                            <#list badgeList as badge>
                                <span class="label" onclick="postMe('${ctx}/org/viewBadge.se', 'id=${badge.id}')"
                                      style="cursor: pointer;">
                                        <i class="icon-${(badge.badgeSign)!}"></i> ${(badge.name)!}
                                    </span>&nbsp;
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

</@layout.org_layout>
