<#assign ctx = rc.getContextPath()/>
<form id="data-form" action="saveAddress.se" method="post">
    <input type="hidden" name="holderId" value="${holder.id}"/>

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="block se-block">
                <div class="header se-header">
                <h2>${type!''} ADDRESS CREATE</h2>
                </div>
                <div class="content controls">

                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Address For</b></div>
                        <div class="col-md-8">
                            <input type="text"  readonly="readonly" value="${holder.name!} (${holder.getDiscriminatorValue()!})" style="color: #000000;" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Address Type</b></div>
                        <div class="col-md-8">
                        <#if type??>
                            <input type="text" name="type" id="type" readonly="readonly" value="${type!}" style="color: #000000;" class="form-control"/>
                        <#else >
                            <select name="type" id="type" class="form-control se-select">
                                <option value="Present">Present</option>
                                <option value="Permanent">Permanent</option>
                                <option value="Office">Office</option>
                            </select>

                        </#if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Address Line 1</b></div>
                        <div class="col-md-8">
                            <input type="text" name="line1" class="form-control" value=""/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Address Line 2</b></div>
                        <div class="col-md-8">
                            <input type="text" name="line2" class="form-control" value=""/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Country Name</b></div>
                        <div class="col-md-7">
                            <select name="countryId" id="" class="form-control se-select" onchange="loadSelector(this, 'Division')">
                                <option value="-1">--Select--</option>
                            <#list countryList as country>
                                <option value="${country.id!}">
                                ${country.name!}
                                </option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-md-1">
                            <input type="button" onclick="postMe('${ctx}/admin/createCountry.se', '')" class="btn btn-info" value="Add"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Division/State</b></div>
                        <div class="col-md-7">
                            <select name="divisionId" id="Division-id" class="form-control se-select" onchange="loadSelector(this, 'District')">

                            </select>
                        </div>
                        <div class="col-md-1">
                            <input type="button" onclick="postMe('${ctx}/admin/createDivision.se', '')" class="btn btn-info" value="Add"/>
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>District</b></div>
                        <div class="col-md-7">
                            <select name="districtId" id="District-id" class="form-control se-select" onchange="loadSelector(this, 'PoliceStation')">

                            </select>
                        </div>

                        <div class="col-md-1">
                            <input type="button" onclick="postMe('${ctx}/admin/createDistrict.se', '')" class="btn btn-info" value="Add"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Thana / PS</b></div>
                        <div class="col-md-7">
                            <select name="policeStationId" id="PoliceStation-id" class="form-control se-select">

                            </select>
                        </div>

                        <div class="col-md-1">
                            <input type="button" onclick="postMe('${ctx}/admin/createThana.se', '')" class="btn btn-info" value="Add"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Post Office</b></div>
                        <div class="col-md-8">
                            <input type="text" name="po" class="form-control" value=""/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-2 text-right"><b>Post Code</b></div>
                        <div class="col-md-8">
                            <input type="text" name="pc" class="form-control" value=""/>
                        </div>
                    </div>


                    <div class="form-row">
                        <div class="col-md-10"></div>
                        <div class="col-md-2">
                            <input type="submit" class="form-control btn btn-success"
                                   value="Save"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form>


<script type="text/javascript">
    function loadSelector(thisObj, targetType) {
        Ext.Ajax.request({
            url: '${ctx}/ajax/get'+targetType+'ListSelector.se',
            method: 'GET',
            params: {
                id: $(thisObj).val()
            },
            scope: this,
            success: function (response) {
                $('#'+targetType+'-id').html(response.responseText)
            }
        });
    }
</script>