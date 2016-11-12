<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 450px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="#">Payment</a></li>
        <li><a  href="#">Find</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">
        <a href="createOtherPayment.erp" class="button icon approve">Add Payment</a>
        <form name="dataForm" id="dataForm" action="viewOtherPayment.erp" method="GET">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="buildingName">
                            Customer Identity:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_3 alpha reset-input">
                        <input type="text" name="cid" id="cid" placeholder="CID : e.g. 012, 123, 005"/> <input type="submit" name="sub" id="sub" value="Find"/>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.dataForm.submit();
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
