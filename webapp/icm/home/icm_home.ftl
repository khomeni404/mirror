<#import "../../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<div class="bread_crumbs_ui_div" style="width: 900px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashboardLink}">Dashboard</a></li>
        <li>${PageTitle}</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 900px;">
    <style>
        .content{height: 125px;}
        .widgetlist { list-style: none; }
        .widgetlist li { display: inline-block; float: left; width: 130px; margin: 0 10px 10px 0; }
        .widgetlist li a { display: block; padding: 15px; border: 1px solid #ccc; color: #333; text-align: center;  }
        .widgetlist li a { -moz-border-radius: 3px; -webkit-border-radius: 3px; border-radius: 3px; -moz-box-shadow: 1px 1px 0 #fff; color: #069; }
        .widgetlist li a span { font-size: 12px; display: block; margin-top: 10px; }
        .widgetlist li a:hover { -moz-box-shadow: 0 0 4px #ddd; text-decoration: none; }
    </style>
    <div class="header" style="width: 900px;">
        <span><span class="ico gray reportHome"></span>&nbsp;&nbsp;${PageTitle}</span>
    </div>

    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">ICM Home</label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/icm/internalRequisitionList.erp?status=Requested" original-title="Request List">
                    <img src="../resources/images/icm/request.png"><span>Item Request</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/viewWaitingItemList.erp?listFor=all" original-title="Item Summary">
                    <img src="../resources/images/icm/to.png"><span>Waiting Item</span>
                </a>
            </li>

            <li>
                <a href="/mirror/icm/itemUsageHistoryList.erp?isVerified=No" original-title="Usage Request">
                    <img src="../resources/images/icm/aa.png"><span>Usage Request</span>
                </a>
            </li>

        </ul>
    </div>
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">ICM Configuration</label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/icm/createItemIn.erp" original-title="Payment Report">
                    <img src="../resources/images/icm/in.png"><span>Item In</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/itemList.erp" original-title="Payment Report">
                    <img src="../resources/images/icm/item.jpg"><span>Items</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/itemCategoryList.erp" original-title="Payment Report">
                    <img src="../resources/images/icm/category.png"><span>Category</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/storeList.erp" original-title="Payment Statement">
                    <img src="../resources/images/icm/store.png"><span>Store</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/unitList.erp" original-title="Find Other Payment">
                    <img src="../resources/images/icm/scale.png"><span>Unit</span>
                </a>
            </li>
        </ul>
    </div>
    <div id="recruit_home" class="content scaffold-list" role="main" style="width: 850px;height: 120px">
        <label style="color: green; font-size: 15px;">ICM Report</label><hr/>
        <ul class="widgetlist">
            <li>
                <a href="/mirror/icm/viewItemSummary.erp?category=all" original-title="Item Summary">
                    <img src="../resources/images/icm/summary.png"><span>Item Summary</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/viewEmpItem.erp?id=1" original-title="Item Summary">
                    <img src="../resources/images/icm/summary.png"><span>Emp Item</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/viewAsset.erp?type=FA" original-title="Item Summary">
                    <img src="../resources/images/icm/summary.png"><span>Fixed Asset</span>
                </a>
            </li>
            <li>
                <a href="/mirror/icm/viewAsset.erp?type=ST" original-title="Item Summary">
                    <img src="../resources/images/icm/summary.png"><span>Stationary</span>
                </a>
            </li>
        </ul>
    </div>

</div>
<#--End widget div-->

</@layout.gen_app_layout>

