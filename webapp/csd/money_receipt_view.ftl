<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="#">Receipt List</a></li>
        <li><a href="#">Create</a></li>
    </ul>
</div>
<br class="clear"/>


<div class="widget" style="width: 1000px">

    <div class="header" style="width: 1000px;">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>
    <div style="width: 1000px;">
        <span><span style="color: red; font-weight: bold"></span></span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 950px;">
        <fieldset class="form">
            <div class="container_16" style="font-size: 17px">
                <#if mr??>
                    <style>
                        .omega {
                            color: darkblue
                        }

                        table {
                            font-size: 19px;
                            font-weight: bold
                        }

                        tr {
                            border: 1px solid black;
                            border-collapse: collapse
                        }

                        .b {
                            color: darkblue
                        }

                        td {
                            padding: 10px 5px 10px 20px
                        }
                    </style>

                    <table>
                        <tr>
                            <td class="a">Receipt No:</td>
                            <td class="b">${mr.receiptNo}</td>
                        </tr>
                        <tr>
                            <td class="a">Customer Name:</td>
                            <td class="b">${mr.customer.name}</td>
                        </tr>
                        <tr>
                            <td class="a">Customer ID (CID):</td>
                            <td class="b">${mr.customer.CID}</td>
                        </tr>
                        <tr>
                            <td class="a">Receipt Date:</td>
                            <td class="b">${mr.receiptDate}</td>
                        </tr>
                        <tr>
                            <td class="a">Amount:</td>
                            <td class="b" style="color: <#if mr.status = "Rejected">red<#else >green</#if>">${mr.amount}
                                /-
                            </td>
                        </tr>
                        <tr>
                            <td class="a">Payment Method:</td>
                            <td class="b">${mr.paymentMethod}</td>
                        </tr>
                        <tr>
                            <td class="a">
                                <#if mr.status = 'Rejected'>
                                    Reject Date:
                                <#else >
                                    Cash Date:
                                </#if>
                            </td>
                            <td class="b">${mr.cashDate}</td>
                        </tr>
                        <tr>
                            <td class="a">Data Operator:</td>
                            <td class="b">${mr.operator.token.name}</td>
                        </tr>
                        <tr>
                            <td class="a">Note:</td>
                            <td class="b">${mr.note}</td>
                        </tr>
                        <tr>
                            <td class="a">Status:</td>
                            <td class="b"
                                style="color: <#if mr.status = "Rejected">red<#else >green</#if>">${mr.status}</td>
                        </tr>
                        <#if mr.statusUpdateBy??>
                            <tr>
                                <td class="a">Status updated by:</td>
                                <td class="b">${mr.statusUpdateBy.token.name}</td>
                            </tr>
                        </#if>

                        <tr>
                            <td class="a">Record Date:</td>
                            <td class="b">${mr.entryDate}</td>
                        </tr>

                    </table>

                    <div align="right">
                        <a href="editMoneyReceipt.erp?id=${mr.id}" class="button">Edit</a>
                    </div>


                <#else >
                    <h1 style="color: #ff0000">No receipt found !</h1>
                </#if>


            </div>
            <script>
                $(document).ready(function () {
                    $("#mrNo").focus();
                });
            </script>
        </fieldset>
    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>
