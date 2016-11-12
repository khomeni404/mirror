<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "" >

<div class="bread_crumbs_ui_div" style="width: 500px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">ICM Home</a></li>
        <li><a  href="#">Item</a></li>
        <li><a  href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span></span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="dataForm" id="dataForm" action="saveItem.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                     ${unitName}
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.dataForm.submit();
            //alert("Record Saved ! !");
        }
    </script>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
