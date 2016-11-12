<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="paymentHome.erp">Payment Home</a></li>
        <li><a  href="#">Other Payment</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 800px">

    <div class="header" style="width: 800px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 850px;">
        <form name="cidForm" id="cidForm" action="findOtherPayment.erp" method="GET">
            <a href="createOtherPayment.erp" class="button icon approve">Add Payment</a>
            <input type="text" name="cid" id="cid" placeholder="CID : e.g. 012, 123, 005   (Press Enter)"/>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
        </form>

        <form name="dataForm" id="dataForm" action="findOtherPayment.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <h2>${cid} : ${name}</h2>
                    <#list otherPayments as op>
                        <div class="grid_3 alpha">
                            <label for="buildingName">
                                ${op.paymentType} (${op.paymentName})
                            </label>
                        </div>
                        <div class="grid_3 alpha">
                            <label for="buildingName">
                            ${op.amount}
                            </label>
                        </div>
                        <div class="grid_3 alpha">
                            <label for="buildingName">
                            ${op.deadLine}
                            </label>
                        </div>
                        <div class="grid_3 alpha">
                            <label for="buildingName">
                            ${op.note}
                            </label>
                        </div>  <br/>
                    </#list>
                    <br class="clear" />
                    <br class="clear" />

                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        function submitForm(){
            document.dataForm.submit();
        }


        $("#cid").keyup(function(event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if(id == ""){
                    document.getElementById("errorMsg").innerHTML = "Please insert CID"
                }else if(id.length > 3){
                    document.getElementById("errorMsg").innerHTML = "CID too long !"
                }else if(id.length < 3){
                    document.getElementById("errorMsg").innerHTML = "CID too short !"
                }else if(id.length == 3){
                    document.cidForm.submit();
                }
            }
        });
        $( document ).ready(function() {
            $( "#cid" ).focus();
        });
        /*
        Ext.onReady(function(){
            $( "#cid" ).focus();
        })  */
    </script>

</div>
<#--End widget div-->

</@layout.gen_app_layout>
