<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
    <#assign ctx = rc.getContextPath()/>

<div class="bread_crumbs_ui_div" style="width: 700px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a href="#">Customer</a></li>
        <li><a href="#">Edit</a></li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 700px">

    <div class="header" style="width: 700px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 650px; height:950px">

        <form name="customerForm" id="customerForm" action="updateCustomer.erp" method="POST">
            <input type="hidden" name="id" id="id" value="${customer.id}"
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="ss">
                            Status:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="status" id="status" style="height: 30px; padding-left: 5px; width: 200%;">
                            <option value="${customer.status}">${customer.status}</option>
                            <option value="Approved">Approved</option>
                            <option value="Cancelled">Cancelled</option>
                            <option value="Refunded">Refunded</option>

                        </select>
                    </div>


                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="bookingDate">
                            Booking Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="bookingDate" id="bookingDate" placeholder="YYYY-MM-DD"
                               value="${customer.bookingDate}" style="height: 30px; padding-left: 5px; width: 200%;"/>
                        <label id="bookingDateMsg" style="color: red"></label>
                    </div>


                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="cid">
                            Customer Identity:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="cid" id="cid" value="${customer.CID}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                    ${customerDuplicateErrMsg}
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="name">
                            Customer Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="name" id="name" placeholder="Customer Name" onkeyup="changeCase();"
                               value="${customer.name}" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="name">
                            Key :
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="keyName" id="keyName" placeholder="Key Name" onkeyup="changeCase();"
                               value="${customer.keyName}" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="buildingName">
                            Building:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="buildingId" id="buildingId" style="height: 30px; padding-left: 5px; width: 200%;">
                            <option value="${customer.building.id}">${customer.building.buildingName}</option>
                            <#list buildings as building>
                                <option value="${building.id}">${building.buildingName}</option>
                            </#list>
                        </select>
                        <input type="hidden" name="oldBuildingId" value="${customer.building.id}"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="aid">
                            Apartment ID:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="aid" id="aid" value="${customer.AID}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="countryLocation">
                            Location: <a href="${ctx}/csd/createLocation.erp?custId=${customer.id!}&errMsg=">Add New</a>
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <#assign locId = (customer.location)!''>
                        <#if locId = ''>
                            <#assign currentLocation = (customer.countryLocation)!''>
                        <#else >
                            <#assign currentLocation = customer.location.id + " : " +customer.location.toString()>
                        </#if>
                        <input list="locationList" style="height: 30px; padding-left: 5px; width: 130%;"
                               name="locationId" value="${currentLocation!}" placeholder=""/>
                        <datalist id="locationList" autocomplete="off">
                            <#list locationList as location>
                            <option value="${location.id} : ${(location.toString())!}">
                            </#list>
                        </datalist>
                    </div>

                    <br class="clear"/><br class="clear"/>
                    <div class="grid_3 alpha">
                        <label for="mailingAddress">
                            Mailing Address:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="mailingAddress" id="mailingAddress" value="${customer.mailingAddress}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="referenceId">
                            Reference ID:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="refId" style="height: 30px; padding-left: 5px; width: 200%;" name="referenceId"
                               id="referenceId" value="${customer.referenceBy.mid} : ${customer.referenceBy.name}"
                               placeholder=""/>
                        <datalist id="refId" autocomplete="off">
                            <#list midList as mida>
                            <option value="${mida.mid} : ${mida.name}">
                            </#list>
                        </datalist>
                        <input type="hidden" name="oldReferenceId" value="${(customer.referenceBy.mid)!}"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="salesType">
                            Sales Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="salesType" id="salesType" style="height: 30px; padding-left: 5px; width: 200%;">
                            <option value="${customer.salesType}">${customer.salesType}</option>
                            <option value="Sales">Sales</option>
                            <option value="Self">Self</option>
                        </select>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="offerId">
                            Offer Name:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="offerId" id="offerId" style="height: 30px; padding-left: 5px; width: 200%;">
                            <option value="${customer.offer.id}">${customer.offer.offerName}</option>
                            <#list offers as offer>
                                <option style="width: 200px;" value="${offer.id}">${offer.offerName}</option>
                            </#list>
                        </select>
                        <input type="hidden" name="oldOfferId" value="${customer.offer.id}"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="careById">
                            Care By (MID):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input list="careId" style="height: 30px; padding-left: 5px; width: 200%;" name="careById"
                               id="careById" placeholder="" value="${customer.careBy.mid} : ${customer.careBy.name}"/>

                        <datalist id="careId" autocomplete="off">
                            <#list empCsds as empCsd>
                            <option value="${empCsd.mid} : ${empCsd.name}">
                            </#list>
                        </datalist>
                        <input type="hidden" name="oldCareById" value="${customer.careBy.mid}"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="cellPhone">
                            Cell Phone:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="cellPhone" id="cellPhone" value="${customer.cellPhone}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="altPhone">
                            Alternative Phone:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="altPhone" id="altPhone" value="${customer.altPhone}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="emailAddress">
                            Email Address:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="emailAddress" id="emailAddress" value="${customer.emailAddress}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                        <br><label id="errorEmail" style="color: red"></label>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="totalFloorSize">
                            Floor Size:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="floorSize" id="floorSize" value="${customer.floorSize}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="price">
                            Price / sft:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="price" id="price" value="${customer.price}"
                               style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>


                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="handoverDate">
                            Handover Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="handoverDate" id="handoverDate" placeholder="YYYY-MM-DD"
                               value="${customer.handoverDate}" style="height: 30px; padding-left: 5px; width: 200%;"/>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="paymentType">
                            Payment Type:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="paymentType" id="paymentType">
                            <option value="${customer.paymentType}">${customer.paymentType}</option>
                            <option value="Installment">Installment</option>
                            <option value="One Time">One Time</option>
                            <option value="Special">Special</option>
                        </select>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_3 alpha">
                        <label for="notes">
                            Notes:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <textarea name="notes" id="notes"
                                  style="height: 30px; padding-left: 5px; width: 200%;">${customer.notes}</textarea>
                    </div>

                    <br class="clear"/> <br class="clear"/>

                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve"
                           onclick="submitForm(document.customerForm.emailAddress.value)">Update</a>
                        <a id="ff" class="button danger icon remove" onclick="document.customerForm.reset()">Clear</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <!--end user create div-->
    <script type="text/javascript">
        $(document).ready(function () {
            var bookingDate = $('#bookingDate');
            var bDate = reverseDate(bookingDate.val(), "-");
            // var bDate = smartDateToNormal(bookingDate.val(), "-");
            bookingDate.val(bDate);
            bookingDate.attr("placeholder", bDate);

            var handoverDate = $('#handoverDate');
            var hDate = reverseDate(handoverDate.val(), "-");
            //var hDate = smartDateToNormal(handoverDate.val(), "-");
            handoverDate.val(hDate);
            handoverDate.attr("placeholder", hDate);
        });

        function submitForm(eAddress) {
            var bookingDate = $('#bookingDate');
            if (bookingDate.val() == "") {
                bookingDate.focus();
                bookingDate.css('border-color', 'red');
                return false;
            }
            var handoverDate = $('#handoverDate');
            if (handoverDate.val() == "") {
                handoverDate.focus();
                handoverDate.css('border-color', 'red');
                return false;
            }

            if (checkEmail(eAddress)) {
                if (validateYYYYMMDD(bookingDate.val()) && validateYYYYMMDD(handoverDate.val()))
                    document.customerForm.submit();
            } else {
                document.getElementById("errorEmail").innerHTML = "This is not valid E-address";
            }
            //alert("Record Saved ! !");
            if (document.getElementById("bookingDate").equals("")) {
                document.getElementById("bookingDateMsg").innerHTML = "Please insert Booking Date as YYYY-MM-DD"
            } else {
                return null;
            }
        }

        function checkEmail(arg) {
            var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
            if (pattern.test(arg)) {
                return true;
            } else {
                return false;
            }
        }


        function changeCase() {
            var x = document.getElementById("name");
            x.value = x.value.toUpperCase();
        }

    </script>
    <style>
        select {
            width: 200%;
        }

        input {
            width: 200%;
        }

        .price {
            width: 200%;
        }
    </style>
</div>

</@layout.gen_app_layout>
