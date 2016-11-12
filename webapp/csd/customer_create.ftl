<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 700px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="#">Customer</a></li>
        <li><a  href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 700px">

    <div class="header" style="width: 700px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 650px;">

        <form name="customerForm" id="customerForm" action="saveCustomer.erp" method="POST">
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="bookingDate">
                            Booking Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="bookingDate" id="bookingDate" placeholder="YYYY-MM-DD" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>


                    <br class="clear" /> <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="cid">
                            Customer Identity:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="cid" id="cid" placeholder="CID: i.e. DPL 000564" style="height: 30px; padding-left: 5px; width: 200%;"/>
                        <br>${errorMsg}
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="name">
                            Customer Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="name" id="name" placeholder="Customer Name" onkeyup="changeCase();" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="name">
                            Key:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="keyName" id="keyName" placeholder="Key Name" onkeyup="changeCase();" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="buildingName">
                            Building:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>

                    <div class="grid_4 omega reset-input">
                        <select name="buildingId" id="buildingId" style="height: 30px; padding-left: 5px; width: 200%;">
                            <#list buildings as building>
                                <option value="${building.id}">${building.buildingName}</option>
                            </#list>
                        </select>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="aid">
                            Apartment ID:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="aid" id="aid" placeholder="e.g. 402, 201, 303" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="countryLocation">
                            Country Location:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="countryLocation" id="countryLocation" placeholder="e.g. JP, BD, USA, UAE" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="referenceId">
                            Reference ID:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" list="refId"  style="height: 30px; padding-left: 5px; width: 200%;" name="referenceId" id="referenceId" placeholder=""/>
                        <datalist id="refId"  autocomplete="off">
                            <#list mids as mida>
                                <option value="${mida.mid}">${mida.name}</option>
                            </#list>
                        </datalist>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="salesType">
                            Sales Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="salesType" id="salesType" style="height: 30px; padding-left: 5px; width: 200%;">
                            <option value="Sales">Sales</option>
                            <option value="Self">Self</option>
                        </select>
                    </div>



                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="offerId">
                            Offer Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="offerId" id="offerId"   style="height: 30px; padding-left: 5px; width: 200%;">
                            <#list offers as offer>
                                <option  style="width: 200px;" value="${offer.id}">${offer.offerName}</option>
                            </#list>
                        </select>

                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="careById">
                            Care By (MID):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="careId"  style="height: 30px; padding-left: 5px; width: 200%;" name="careById" id="careById" placeholder=""/>

                        <datalist id="careId"  autocomplete="off">
                            <#list empCsds as empCsd>
                            <option value="${empCsd.mid}">${empCsd.name}</option>
                            </#list>
                        </datalist>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="cellPhone">
                            Cell Phone:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="cellPhone" id="cellPhone" placeholder="cellPhone" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="altPhone">
                            Alternative Phone:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="altPhone" id="altPhone" placeholder="altPhone" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="emailAddress">
                            Email Address:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="emailAddress" onkeyup="removeEmailErrorMsg()" id="emailAddress" placeholder="emailAddress" style="height: 30px; padding-left: 5px; width: 200%;"/>
                        <br><label id="errorEmail" style="color: red"></label>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="mailingAddress">
                            Mailing Address:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="mailingAddress" id="mailingAddress" placeholder="mailingAddress" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="totalFloorSize">
                            Floor Size:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="floorSize" id="floorSize" placeholder="Floor Size" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="price">
                            Price / sft:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="price" id="price" placeholder="price / sft" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="handoverDate">
                            Handover Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="handoverDate" id="handoverDate" placeholder="YYYY-MM-DD" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="paymentType">
                            Payment Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="paymentType" id="paymentType" style="height: 30px; padding-left: 5px; width: 200%;">
                            <option value="Installment">Installment</option>
                            <option value="One Time">One Time</option>
                            <option value="Special">Special</option>
                        </select>
                    </div>
                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="notes">
                            Notes:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <textarea  name="notes" id="notes"  style="height: 30px; padding-left: 5px; width: 200%;"></textarea>
                    </div>

                    <br class="clear" /> <br class="clear" />
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve" onclick="submitForm(document.customerForm.emailAddress.value)">Save</a>
                        <a id="ff" class="button danger icon remove" onclick="document.customerForm.reset()">Clear</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->

    <script type="text/javascript">
        $(function() {
            $( "#bookingDate" ).datepicker({ dateFormat: 'yy-mm-dd', autoclose:true});
            $( "#handoverDate" ).datepicker({ dateFormat: 'yy-mm-dd', autoclose:true});
        });

        function submitForm(eAddress){
            var date = document.getElementById("bookingDate").value;
            if(!validateYYYYMMDD(date)){
                $('#bookingDate').focus();
                $('#bookingDate').select();
                return false;
            }
            if(!checkEmail(eAddress)){
                document.getElementById("errorEmail").innerHTML = "This is not valid E-address";
                return false;
            }
            var date = document.getElementById("handoverDate").value;
            if(!validateYYYYMMDD(date)){
                $('#handoverDate').focus();
                $('#handoverDate').select();
                return false;
            }
            document.customerForm.submit();
            //alert("Record Saved ! !");
        }

        function checkEmail(arg){
            var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
            if(pattern.test(arg)){
                return true;
            }else{
                return false;
            }
        }
        function removeEmailErrorMsg(){
            document.getElementById("errorEmail").innerHTML = "";
        }

        function changeCase() {
            //alert("Hi")
            //$('#name').val("Hello")
            var x = document.getElementById("name");
            x.value = x.value.toUpperCase();
        }

    </script>
    <style>
        select { width: 5.5em }
    </style>
</div>
<#--End widget div-->

</@layout.gen_app_layout>
