<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >
<script src="#"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a href="index">Dashboard</a></li>
        <li><a href="icmHome.erp">ICM Config Home</a></li>
        <li><a href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray money_disburse"></span>${PageTitle}</span>

    </div>

    <div class="content">
        <form name="itemForm" id="cidForm" action="itemList.erp" method="GET">
            <a class="print-link no-print" href="createItem.erp" style="position: absolute;"><img
                    alt="Print"
                    src="../resources/images/common/add.png"/></a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="findFor" id="findFor" placeholder="type text" value="" style="font-size: 15px"/>
            <a onclick="openHelp();">Help(?)</a>
            <label id="errorMsg" name="errorMsg" style="color: red;"></label>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 88%;"><img
                    alt="Print"
                    src="../resources/images/common/printer2.png"/></a>
            <a class="print-link no-print" onclick="jQuery('#printDiv').print()" style="position: absolute; left: 90%;"><img
                    alt="Pdf"
                    src="../resources/images/common/pdf2.png"/></a>
        </form>
        <div id="printDiv" class="content scaffold-list" role="main">
            <div style="min-height: 1250px">
                <table class="headTable" id="headTable">
                    <tr><td class="CompanyName">D<span style="color: #c04c05">O</span>RPAN Properties Limited</td></tr>
                    <tr><td class="address">Flat-5A, House-30, Sonargaon Janapath, Sector-11 Chowrasta, Uttara, Dhaka-1230.</td></tr>
                    <tr><td class="address">ad.dorpan@gmail.com</td></tr>
                </table>
                <hr/>
                <table>
                    <tr><th class="headerTitle">INTERNAL ITEM LIST</th></tr>
                </table>
                <br/>
                <table>
                    <tr style="font-weight: bold; color: #0200ff">
                        <th>Sl. No.</th>
                        <th>Name</th>
                        <th>Item Category</th>
                        <th>Description</th>
                        <th>Price (Approx)</th>
                        <th>Unit</th>
                        <th colspan="2" class="action">Action</th>
                    </tr>
                    <#assign x = 1>
                    <#list items as item>
                        <#if x%2 == 0>
                        <tr class="odd">
                        <#else>
                        <tr class="even">
                        </#if>
                        <td class="sl">${x}</td>
                        <td class="name">
                            <a href="/mirror/icm/createItemIn.erp" style="text-decoration: none; color: #000000">
                                ${item.name}
                            </a>
                        </td>
                        <td class="cat">${item.parentCategory} (${item.itemCategory})</td>
                        <td class="desc">${item.description}</td>
                        <td class="price">${item.approximatePrice}/${item.unit}</td>
                        <td class="unit">${item.unit}</td>
                        <td class="action"><a href="editItem.erp?id=${item.id}" id="id" name="id"><img title='Edit' src='../resources/images/common/edit.png'/></a> </td>
                        <td class="action"><a href="#" id="id" name="id"><img title='Delete' src='../resources/images/common/delete.png'/></a> </td>
                    </tr>
                        <#assign x = x+1>
                    </#list>
                </table>
            </div>
            <div class="copyright"> Powered by : <span class="tip"><a href="#" original-title="zero.com.bd">Khomeni</a> </span>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $("#findFor").keyup(function (event) {
            var id = document.getElementById("cid").value;
            if (event.keyCode == 13) {
                if (id.toUpperCase() != "ALL" || id.toUpperCase() != "ALL") {
                    document.getElementById("errorMsg").innerHTML = "Please insert ???";
                } else {
                    document.itemForm.submit();
                }
            }
        });
        $(document).ready(function () {
            $("#findFor").focus();
        });

        function openHelp() {
            var content = "Please do any one of the following to get result<hr/>"+
                    "To get result for all type <b>'all'</b> and press Enter<br/>"+
                    "To get result for pending type <b>'not'</b> and press Enter<br/>"+
                    "To get result for individual type<b> 'cid'</b> like <b> '12'</b> and press Enter<br/>";
            top.helpPan = window.open('','','width=500, height=250, top=270, left=520, ')
            top.helpPan.document.writeln(
                    '<html><head><title>Help</title></head>'
                            +'<body bgcolor=white onLoad="self.focus()" style="font-family: calibri; color: green">'
                            +content
                            +'</body></html>'
            )
            top.helpPan.document.close()
        }

    </script>

    <style>
        table, hr {
            margin-left: 20px;
            margin-right: 20px;
        }

        table, th, td {
            border-collapse: collapse;
            font-size: 13px;
            padding: 3px;
            height:30px;
        }

        th {
            text-align: left;
            font-weight: bold;
            color: white;
            background-color: #c04c05;
        }
        .cat{
            width: 340px;
        }
        .name {
            width: 180px;
        }
        .unit {
            width: 90px;
        }
        .desc{width: 200;}
        .price{width: 130;}
        .action{
            width: 40px;
            text-align: center;
        }
        ul, li{
            list-style: upper-alpha;
        }
        .sl{width:70px; text-align: center}
        .even{background-color: #d3d3d3
        }
        .odd{background-color: #f9e3b7
        }

        .headTable, .header{margin-left: 50px; margin-right: 50px}
        .CompanyName{width: 970px; text-align: center; font-size: 30px; font-weight: bold}
        .address{width: 970px; text-align: center; font-size: 14px; font-weight: bold}
        .headerTitle{width: 1100px; text-align: center; color: #000000; font-size: 20px; font-weight: bold; background-color: #cbe0e1
        }
    </style>
</div>

</@layout.gen_app_layout>