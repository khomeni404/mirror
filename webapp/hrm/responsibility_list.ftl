<#import "../gen_template/gen_app_layout.ftl" as layout>
<@layout.gen_app_layout "${PageTitle}" >

<script type="text/javascript">
    var responsibilityDataStore;
    var checkBoxSelectionModel;
    var responsibilityListingEditorGrid;

    Ext.onReady(function(){

    responsibilityDataStore = new Ext.data.Store({
        id: "responsibilityDataStore",
        url: "responsibilityJsonDataErp.erp",
        reader: new Ext.data.JsonReader({
            root: 'responsibilityList',
            totalProperty: 'totalCount',
            id: 'id'
        },[

            {name: 'id', type: 'int', mapping: 'id'},
            {name: 'responsibilityCode', type: 'string', mapping: 'responsibilityCode'},
            {name: 'responsibilityName', type: 'string', mapping: 'responsibilityName'}
        ]),
        autoLoad: true

    });
    checkBoxSelectionModel = new Ext.grid.CheckboxSelectionModel();
    responsibilityListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: 'responsibilityListingEditorGrid',
        store: responsibilityDataStore,
        selModel: checkBoxSelectionModel,
        clicksToEdit: 2,
        renderTo: 'responsibility_list',
        width: 400,
        height: 300,
        columns:[{
            dataIndex: 'id',
            header: 'Id',
            width: 100,
            sortable: true
        },
            {
                dataIndex: 'responsibilityCode',
                header: 'Responsibility Code',
                width: 100,
                sortable: true
            }],
        stripeRows: true,
        bbar: new Ext.PagingToolbar({
            store: responsibilityDataStore,
            pageSize: 10,
            displayInfo: true,
            displayMsg: 'Displaying {0} - {1} of {2}',
            emptyMsg: 'No records found'
        }),
        tbar: [{
            xtype: "button",
            text: "Add Responsibility",
            iconCls: "addRecord",
            handler: function(){
                window.location = "createResponsibility.erp"
            }
        }]
    });

    responsibilityDataStore.load({params: {start: 0, limit: 10}})

    });

</script>

<div class="bread_crumbs_ui_div" style="width: 663px">
    <ul id="crumbs_ui_custom">
        <li><a href="${DashBoardLink}">Dashboard</a></li>
        <li><a href="recruitHome.erp">Recruitment Home</a></li>
        <li>Responsibility</li>
    </ul>
</div>
<br class="clear"/>

<div class="widget" style="width: 661px">

    <div class="header" style="width: 661px;">
        <span><span class="ico job posting"></span>${PageTitle}</span>
    </div>

    <div id="responsibility_list" class="content" role="main" style="width: 609px;">

    </div>
    <!--end user create div-->


</div>
<#--End widget div-->

</@layout.gen_app_layout>