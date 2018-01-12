<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 1200px">
    <ul id="crumbs_ui_custom">
        <li><a href="/mirror/home.erp">Dashboard</a></li>
        <li><a href="/mirror/mis/home.erp">MIS Home</a></li>
    </ul>
</div>
<br class="clear"/>


<div class="widget" style="width: 1200px">

    <div class="header" style="width: 1050px;">
        <span><span class="ico gray user"></span>Searched Customer (${status})</span>
    </div>
    <div style="width: 1050px;">
        <span><span style="color: red; font-weight: bold"></span></span>
    </div>

    <div id="user_crate" class="content" role="main" style="width: 1000px;">
        <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 88%;"><img
                alt="Print"
                src="../resources/images/csd/printer2.png"/></a>
        <a class="print-link no-print" onclick="jQuery('#ele1').print()" style="position: absolute; left: 90%;"><img
                alt="Pdf"
                src="../resources/images/csd/pdf.png"/></a>

        <fieldset class="form">
            <div id="ele1" class="content scaffold-list" role="main">
                <div class="container_16" style="font-size: 17px">
                    <#if customerList?size != 0>

                        <table>
                            <#list customerList as customer>
                                <#if status == "All">
                                    <tr>
                                        <td class="a">${customer.CID}</td>
                                        <td class="b">${customer.name}</td>
                                        <td class=""><b style="color: green">${customer.referenceBy.mid}</b>-${customer.referenceBy.name}</td>
                                        <td><a style="color: #000000"
                                               href="/mirror/csd/viewPaymentReport.erp?cid=${customer.CID}">Report</a>
                                        </td>
                                        <#--<td><a style="color: #000000"
                                               href="/mirror/csd/viewPaymentStatement.erp?cid=${customer.CID}">Statement</a>
                                        </td>-->
                                    <#--<td><a style="color: #000000" href="/mirror/csd/editCustomer.erp?id=${customer.id}">Edit Customer</a></td>-->
                                        <td><a style="color: #000000"
                                               href="/mirror/csd/viewCustomer.erp?cidView=${customer.CID}">View
                                            Customer</a></td>
                                    </tr>
                                <#else >
                                    <#if customer.status == status>
                                        <tr>
                                            <td class="a">${customer.CID}</td>
                                            <td class="b">${customer.name}</td>

                                            <td><a style="color: #000000"
                                                   href="/mirror/csd/viewPaymentReport.erp?cid=${customer.CID}">Report</a>
                                            </td>
                                            <td><a style="color: #000000"
                                                   href="/mirror/csd/viewPaymentStatement.erp?cid=${customer.CID}">Statement</a>
                                            </td>
                                        <#--<td><a style="color: #000000" href="/mirror/csd/editCustomer.erp?id=${customer.id}">Edit Customer</a></td>-->
                                            <td><a style="color: #000000"
                                                   href="/mirror/csd/viewCustomer.erp?cidView=${customer.CID}">View
                                                Customer</a></td>
                                        </tr>
                                    </#if>
                                </#if>
                            </#list>
                        </table>



                    <#else >
                        <h1 style="color: #ff0000">No such name found !</h1>
                    </#if>


                </div>
            </div>
            <script>
                $(document).ready(function () {
                    $(".findCustomer").focus();
                });
            </script>
        </fieldset>
    </div>

</div>
<#--End widget div-->
<style>
    .omega {
        color: darkblue
    }

    table {
        font-size: 13px;
        font-weight: bold
    }

    tr {
        border: 1px solid black;
        border-collapse: collapse
    }

    .a {
        color: darkblue;
        width: 100px
    }
    .b {
        color: darkblue;
        width: 300px
    }

    td {
        padding: 10px 5px 10px 20px;
        border-bottom: 1px solid #000000
    }
</style>
</@layout.gen_app_layout>
