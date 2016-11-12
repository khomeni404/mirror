<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="index">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="#">Building</a></li>
        <li><a  href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>


<div class="widget" style="width: 500px">

    <div class="header" style="width: 500px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>
    <div  style="width: 500px;">
        <span><span style="color: red; font-weight: bold"></span></span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 450px;">

        <form name="dataForm" id="dataForm" action="updateMoneyReceipt.erp" method="POST">
            <input type="hidden" name="id" id="id" value="${id}"/>
            <fieldset class="form">
                <div class="container_16">
                    <div class="grid_3 alpha">
                        <label for="receiptNo">
                            Receipt No:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="receiptNo" class="myElement a2" id="receiptNo" placeholder="amount" value="${receiptNo}"/>
                    </div>

                    <br class="clear" /> <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="receiptDate">
                            Receipt Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="receiptDate" class="myElement a5" value="${receiptDate}" id="receiptDate" placeholder="YYYY/MM/DD"/>
                    </div>



                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="paymentMethod">
                            Payment Method:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="paymentMethod" id="paymentMethod">
                            <option value="${mr.paymentMethod}">${mr.paymentMethod}</option>
                            <option value="Cash">Cash</option>
                            <option value="Cheque">Cheque</option>
                            <option value="EFT">EFT</option>
                            <option value="TT">TT</option>
                            <option value="DD">DD</option>
                            <option value="Incentive">Incentive</option>
                            <option value="Profit">Profit</option>
                            <option value="Others">Others</option>
                        </select>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="amount">
                            Amount (BDT):
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="amount" class="myElement a2" id="amount" placeholder="amount" value="${amount}"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="newCid">
                            Customer ID:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="newCid" class="myElement a3" id="newCid" value="${cid}" placeholder="Customer Identity"/>
                        <input type="hidden" name="oldCid" class="myElement a3" id="oldCid" value="${cid}" placeholder="Customer Identity"/>
                    </div>



                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="note">
                            Note:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="note" id="note" class="myElement a4" placeholder="Note" value="${note}"/>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="cashDate">
                            Cash Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <input type="text" name="cashDate" class="myElement a5" id="cashDate" placeholder="YYYY/MM/DD" value="${cashDate}"/>
                    </div>

                    <script>
                        $(document).ready(function (){
                            var receiptDate = $('#receiptDate');
                            var rDate = reverseDate(receiptDate.val(), "-");
                            receiptDate.val(rDate);
                            receiptDate.attr("placeholder", rDate);

                            var cashDate = $('#cashDate');
                            var cDate = reverseDate(cashDate.val(), "-");
                            cashDate.val(cDate);
                            cashDate.attr("placeholder", cDate);
                        });

                        $(function() {
                            $( "#receiptDate" ).datepicker({ dateFormat: 'yy-mm-dd'});
                        });
                        $(function() {
                            $( "#cashDate" ).datepicker({ dateFormat: 'yy-mm-dd'});
                        });

                    </script>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="postDate">
                            Post Date:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <label for="postDate">
                            ${entryDate}
                        </label>
                    </div>

                    <br class="clear" />   <br class="clear" />
                    <div class="grid_3 alpha">
                        <label for="status">
                            Status:
                            <span class="required-indicator">*</span>
                        </label>
                    </div>
                    <div class="grid_4 omega reset-input">
                        <select name="status" class="status a5" id="status">
                            <option value="${status}">${status}</option>
                            <option value="Passed">Passed</option>
                            <option value="Not Passed">Not Passed</option>
                            <option value="Rejected">Rejected</option>
                        </select>
                    </div>

                    <br class="clear" /> <br class="clear"/>
                    <div class="grid_4 omega reset-input reset-button-left">
                        <a id="userCreate" class="button icon approve myElement a7"  onclick="submitForm()">Update</a>
                    </div>
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
