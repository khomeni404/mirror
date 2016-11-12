<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >


<br class="clear"/>


<div class="widget" style="width: 1000px">

    <div class="header" style="width: 1000px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>

    <span style="position: absolute; left: 76%;">
        <a href="#">
            <img src="../resources/images/common/back.png" title="Previous Customer"  height="30px" width="30px" alt=""  onclick="back('${cid}');"/>
        </a>
    </span>
    <span style="position: absolute; left: 79%;">
         <a href="#">
             <img src="../resources/images/common/next.png" title="Next Customer"  height="30px" width="30px" alt=""  onclick="next('${cid}');"/>
         </a>
    </span>
    </div>
    <script>
        function back(cid){
            //var cid = ;
            var id = cid.substring(4,10);
            var nextId = parseInt(id)-1;
            window.location = '/mirror/csd/viewCustomer.erp?cidView='+nextId;
        }
        function next(cid){
            var id = cid.substring(4,10);
            var nextId = parseInt(id)+1;
            window.location = '/mirror/csd/viewCustomer.erp?cidView='+nextId;
        }
    </script>
    <div id="user_crate" class="content" role="main" style="width: 950px;">

        <fieldset class="form">
            <#if customer??>
                <div class="container_16" style="font-size: 17px">
                    <table class="table1">
                        <tr>
                            <td class="a">Customer ID</td>
                            <td class="b">${customer.CID}</td>
                            <td rowspan="13" style="width: 300">
                                <img src="../resources/images/customer/${customer.CID}.jpg" height="344" width="286" alt="${customer.name}"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="a">Customer Name:</td>
                            <td class="b"  style="color: <#if customer.status == "Cancelled" || customer.status == "Refunded">red</#if>">${customer.name}</td>
                        </tr>
                        <tr>
                            <td class="a">Customer Key:</td>
                            <td class="b"  style="color: <#if customer.status == "Cancelled" || customer.status == "Refunded">red</#if>">${customer.keyName}</td>
                        </tr>

                        <tr>
                            <td class="a">Phone :</td>
                            <td class="b">${customer.cellPhone} / ${customer.altPhone}</td>
                        </tr>

                        <tr>
                            <td class="a">Email :</td>
                            <td class="b">${customer.emailAddress}</td>
                        </tr>



                        <tr>
                            <td class="a">Reference:</td>
                            <td class="b">${customer.referenceBy.name}<br/>${customer.referenceBy.type} - ${customer.referenceBy.mid}</td>
                        </tr>
                        <tr>
                            <td class="a">Booking Date :</td>
                            <td class="b">${customer.bookingDate}</td>
                        </tr>
                        <tr>
                            <td class="a">Offer :</td>
                            <td class="b">${customer.offer.offerName}</td>
                        </tr>
                        <tr>
                            <td class="a">Building :</td>
                            <td class="b">${customer.building.buildingName}</td>
                        </tr>
                        <tr>
                            <td class="a">AID: </td>
                            <td class="b">${customer.building.nameAlias} - ${customer.AID}</td>
                        </tr>
                        <tr>
                            <td class="a">Size :</td>
                            <td class="b">${customer.floorSize}</td>
                        </tr>
                        <tr>
                            <td class="a">Price :</td>
                            <td class="b">${customer.price}/- per SFT</td>
                        </tr>
                        <tr>
                            <td class="a">Status :</td>
                            <td class="b <#if customer.status == "Refunded">blink</#if>" style="color: <#if customer.status == "Cancelled" || customer.status == "Refunded">red</#if>">${customer.status}</td>
                        </tr>
                        <tr>
                            <td class="a">Address :</td>
                            <td class="b" colspan="2">${customer.mailingAddress}</td>
                        </tr>
                        <tr>
                            <td class="a">Note :</td>
                            <td class="b" colspan="2" style="font-weight: normal">${customer.notes}</td>
                        </tr>

                    </table>
                    <hr/>

                    </table>
                    <div align="right">
                        <a class="button"  style="color: #000000" href="/mirror/csd/viewPaymentReport.erp?cid=${customer.CID}">Payment Report</a>
                        <a class="button" style="color: #000000" href="/mirror/csd/viewPaymentStatement.erp?cid=${customer.CID}">Payment Statement</a>
                        <a class="button" style="color: #000000" href="/mirror/csd/viewInstallmentStatement.erp?cid=${customer.CID}">Installment Statement</a>

                        <a class="button" style="color: #000000" href="/mirror/csd/editCustomer.erp?id=${customer.id}&errMsg=">Edit</a>
                    </div>


                </div>
                <#else >
                <h2>No such ID found</h2>
            </#if>

            <script>
                $(document).ready(function () {
                    $(".viewCustomer").focus();
                });
            </script>
        </fieldset>

        <hr/>
        <form name="historyForm" id="historyForm" action="saveFollowUpHistory.erp" method="POST">
        <fieldset class="">
            <div class="">
                <input type="hidden" value="${customer.id}" name="customerId" id="customerId"/>
                <input type="hidden" value="1" name="attentionTo" id="attentionTo"/>
                <table class="table2">
                    <tr>
                        <td style="width:300px">Client's Comment</td>
                        <td style="width:300px">Remarks</td>
                        <td style="width:300px">Next Knocking Date</td>
                    </tr>
                    <tr>
                        <td rowspan="2">
                            <textarea style="width: 300px; font-size: 20px" name="comments" id="comments"
                                      placeholder="Client's Comment / Caller Message"></textarea>
                        </td>
                        <td rowspan="2">
                            <textarea style="width: 300px; font-size: 20px" name="remarks" id="remarks"
                                      placeholder="Client's Comment / Caller Message"></textarea>
                        </td>
                        <td>
                            <input type="text" name="attentionOn" id="attentionOn" placeholder="YYYY-MM-DD"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <a id="userCreate" class="button icon approve" onclick="submitForm()">Save Conversation</a>
                        </td>
                    </tr>

                </table>
                <hr/>
                <style>
                    .omega {
                        color: darkblue
                    }

                    .table1 {
                        font-size: 17px;
                        font-weight: bold;
                    }

                    tr {
                        border: 1px solid black;
                        border-collapse: collapse
                    }

                    table th {
                        font-weight: bold
                    }

                    .b {
                        color: darkblue
                    }

                    .table1 td {
                        padding: 3px 5px 3px 5px;
                        border: 1px solid #000000;
                        background-color: #d8f0b6
                    }

                    .table2 tr td {
                        padding: 3px 5px 3px 5px;
                        border: 1px solid #000000;
                        border-collapse: collapse;
                        background-color: #ebf2ff
                    }

                    .table3 th {
                        font-weight: bold;
                        background-color: #007eaa;
                        text-align: center;
                        padding: 2px;
                        color: white
                    }

                    .table3 {
                        background-color: #c2d7ff;
                        text-align: center;
                        padding: 2px;
                    }

                    .table3 tr td, th {
                        padding: 3px 5px 3px 5px;
                        border: 1px solid #000000;
                        font-size: 13px;
                        border-collapse: collapse
                    }

                    .a {
                        width: 160px
                    }
                </style>
                <div style="height: 10px">
                    <table class="table3">
                        <tr>
                            <th style="width: 120px">Date</th>
                            <th style="width: 250px">Called By</th>
                            <th style="width: 250px">Client's Comment</th>
                            <th style="width: 250px">Remarks</th>
                            <th style="width: 120px">Knocking Date</th>
                        </tr>
                        <#list historyList as history>
                            <tr>
                                <td>${history.date}</td>
                                <td>${history.calledBy.token.name}</td>
                                <td>${history.clientsComment}</td>
                                <td>${history.remarks}</td>
                                <td>${history.attentionOn}</td>
                            </tr>
                        </#list>
                    </table>
                </div>

                <script type="text/javascript">
                    $(function () {
                        $("#attentionOn").datepicker({dateFormat: 'yy-mm-dd'});
                    });

                    function submitForm(date) {
                        //document.historyForm.submit();
                        var comment = $('#comments');
                        var remark = $('#remarks');
                        var date = $('#attentionOn');
                        if (comment.val() == "") {
                            alert("Please insert Comment");
                            comment.focus();
                            return false;
                        } else if (remark.val() == "") {
                            alert("Please insert remark");
                            remark.focus();
                            return false;
                        } else if (date.val() == "") {
                            alert("Please Insert Attention Date");
                            date.focus();
                            return false;
                        } else {
                            if (!validateYYYYMMDD(date.val())) {
                                date.focus();
                                date.select();
                                return false;
                            } else {
                                document.historyForm.submit();
                            }
                        }
                    }
                </script>

            </div>
            </fieldset>
            </form>

    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>
