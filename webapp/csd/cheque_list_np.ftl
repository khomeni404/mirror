<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<script src="cheque_list_np.js"></script>

<div class="bread_crumbs_ui_div" style="width: 611px">
    <ul id="crumbs_ui_custom">
        <li><a  href="">Dashboard</a></li>
        <li><a href="//home.erp">CSD Home</a></li>
        <li><a  href="approveHome.erp">Approve Home</a></li>
        <li><a  href="#">${PageTitle}</a></li>
    </ul>
</div>

<br class="clear"/>

<div class="widget">

    <div class="header">
        <span><span class="ico gray user"></span>${PageTitle}</span>
    </div>

    <div class="content">
        <div id="cheque_list_np_div" class="content scaffold-list" role="main">

            <!-- Here the data will be showed from java script file according to div id -->

        </div>
    </div>

</div>
<script type="text/javascript">
    function abc(id, chqDate){
        //var date = new Date(parseInt(chqDate.substr(2)));
        //var newDate = dateFormat(chqDate, "mm/dd/yyyy");
        var a = new Date(chqDate);
        //var a = parseJsonDate(chqDate);
        var passedDate = prompt("Type cheque passed date", a);
        var href = "updateChequeStatus.erp";
        window.location = href+"?passedDate="+passedDate+"&id="+id;
    }
    function parseJsonDate(jsonDateString) {
        return new Date(parseInt(jsonDateString.replace('/Date(', '')));
    }
</script>

</@layout.gen_app_layout>