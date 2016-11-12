<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<style type="text/css">

    .adjustImg{
        margin-bottom: -11px;
        cursor: pointer;
    }

</style>
<script type="text/javascript">

    Ext.onReady(function(){



    })

</script>

<div class="bread_crumbs_ui_div" style="width: 663px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li><a href="recruitHome.erp">Recruitment Home</a></li>
        <li> Create Job Posting</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 661px">

    <div class="header" style="width: 661px;">
        <span><span class="ico job posting"></span>${PageTitle}</span>
    </div>

    <div id="job_posting_create" class="content" role="main" style="width: 609px;">

        <form name="userForm" id="userForm" action="saveUser.erp" method="POST">

            <fieldset class="form">

                <div class="container_16">
                    <!--
                    <div class="grid_3 alpha">
                        <label for="jobTitle">
                            Job title:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    -->
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="jobTitle" id="jobTitle"  placeholder= "Job title"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <!--
                    <div class="grid_3 alpha">
                        <label for="noOfEmployees">
                            No of EmpCsd:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    -->
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="noOfEmployees" id="noOfEmployees"  placeholder= "No of Employee"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />
                    <!--
                    <div class="grid_3 alpha">
                        <label for="responsibility1">
                            Responsibility:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    -->
                    <div class="grid_4 omega reset-input" id="responsibilityGroup" style="width: 315px;">

                        <input type="text" name="responsibility1" id="responsibility1" placeholder= "Responsibility 1"/>
                        <!--<img src="../resources/images/responsibility.png" id="responsibility" class="adjustImg" alt="" title="Select Responsibility">-->
                        <img src="../resources/images/Khomeni/add.png" id="addResponsibility" class="adjustImg" alt="Add" title="Add Responsibility">
                        <img src="../resources/images/Khomeni/remove.png" id="removeRequirement" class="adjustImg"  alt="Remove" title="Remove Responsibility">

                    </div>

                    <br class="clear" />
                    <br class="clear" />
                <#--
                add requirements dynamically

                -->
                    <!--
                    <div class="grid_3 alpha">
                        <label for="requirements">
                            Requirements:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    -->
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="requirements" id="requirements"  placeholder= "Requirements"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                <#--Salary range from some amount to another amount-->
                    <!--
                    <div class="grid_3 alpha">
                        <label for="salaryRange">
                            Salary Range:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                       -->
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="salaryRange" id="salaryRange"  placeholder= "Salary Range"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                <#--Benefits what benefit will be provided by the company
                    add dynamically
                --> <!--
                    <div class="grid_3 alpha">
                        <label for="benefit">
                            Benefits:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                      -->
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="benefit" id="benefit"  placeholder= "Benefits"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />

                <#--Job location where the employee will be posted-->
                    <!--
                    <div class="grid_3 alpha">
                        <label for="jobLocation">
                            Job Location:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                     -->
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="jobLocation" id="jobLocation"  placeholder= "Job Location"/>
                    </div>

                    <br class="clear" />
                    <br class="clear" />



                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm()">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.userForm.reset()">Clear</a>
                    </div>



                </div>

            </fieldset>


        </form>
    </div>
    <!--end user create div-->



<#--as it is in recruitment controller but the ftl and js is in hrm2 folder so the src will be preceded by ../hrm2-->
<script src="../hrm/job_posting.js"></script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>