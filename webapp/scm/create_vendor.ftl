<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a href="/mirror/home.erp">Dashboard</a></li>

        <li><a href="jobHome.erp">Job Home</a></li>
        <li>Create Job Title</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 900px">

    <div class="header" style="width: 900px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="vendor_create" class="content" role="main" style=" height: 700px" >
    <form name="createVendorForm" id="createVendorForm" action="saveVendor.erp" method="POST">
        <div class="container_16">
            <div class="grid_6 alpha leftElement">

                <fieldset class="from">

                    <legend class="legend-color">Vendor Info</legend>

                <#--%{--username--}%-->
                    <div style="padding-top: 5px">

                    </div>

                    <div class="grid_2" style="padding-top: 5px;">
                        <label >
                            First Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="firstName" id="firstName">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            Middle Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="middleName" id="middleName">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            Last Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="lastName" id="lastName">
                    </div>

                    <br class="clear" />
                    <br class="clear" />

            </div><!--End left element-->


            <#--start right element-->
            <div class="grid_6 omega rightElement">


                    <legend class="legend-color">Address information</legend>

                <#--%{--username--}%-->
                    <div style="padding-top: 5px">

                    </div>

                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            House No:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="houseNo" id="houseNo">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            Floor No:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="floorNo" id="floorNo">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            Post Office:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="postOffice" id="postOffice">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            post Box:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="postBox" id="postBox">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            Police Station:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="policeStation" id="policeStation">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            District:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="district" id="district">
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <div class="grid_2" style="padding-top: 5px;">
                        <label>
                            Country:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_3">
                        <input type="text" name="country" id="country">
                    </div>

                    <br class="clear" />
                    <br class="clear" />

            </div><!--End left element-->

            <div class="container_16">
                <div class="grid_6 alpha leftElement" style="margin-top: -150px">


                        <legend class="legend-color">Phone Information</legend>

                    <#--%{--username--}%-->
                        <div style="padding-top: 5px">

                        </div>

                        <div class="grid_2" style="padding-top: 5px;">
                            <label >
                                Number:
                                <span class="required-indicator">*</span>
                            </label>
                        </div>
                        <div class="grid_3">
                            <input type="text" name="number" id="number">
                        </div>

                        <br class="clear" />
                        <br class="clear" />
                        <div class="grid_2" style="padding-top: 5px;">
                            <label>
                                Type:
                                <span class="required-indicator">*</span>
                            </label>
                        </div>
                        <div class="grid_3">
                            <input type="text" name="type" id="type">
                        </div>

                        <br class="clear" />
                        <br class="clear" />


                </div><!--End left element-->

                <div class=" grid_6 alpha leftElement" style="margin-top: -8px">


                        <legend class="legend-color">Email Information</legend>

                    <#--%{--username--}%-->
                        <div style="padding-top: 5px">

                        </div>

                        <div class="grid_2" style="padding-top: 5px;">
                            <label>
                                Address:
                                <span class="required-indicator">*</span>
                            </label>
                        </div>
                        <div class="grid_3">
                            <input type="text" name="address" id="address">
                        </div>

                        <br class="clear" />
                        <br class="clear" />
                        <div class="grid_2" style="padding-top: 5px;">
                            <label>
                                Email Type:
                                <span class="required-indicator">*</span>
                            </label>
                        </div>
                        <div class="grid_3">
                            <input type="text" name="emailType" id="emailType">
                        </div>

                        <br class="clear" />
                        <br class="clear" />


                </div><!--End left element-->
                <div class="grid_4 omega reset-input reset-button-left" style="margin-top: 50px;margin-left: 200px">
                    <a id="createVendor" class="button icon approve" onclick="submitForm()">Save</a>
                    <a id="ff" class="button danger icon remove" onclick="document.createVendorForm.reset()">Clear</a>
                </div>

            </fieldset>

        </div><!--End container 16 for top element-->

    </div><!--End content-->

     </form>
    <script type="text/javascript">
        function submitForm(){
            document.createVendorForm.submit();
            alert(1)
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>