var checkBoxSelMod;
var listingEditorGrid;
var editFeature = "view";
var deleteFeature = "approve";
var featureDetails = "postpone";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var theDataStore;



Ext.onReady(function(){

    theDataStore = new Ext.data.Store({
        id: "voucherListApDataStore",      //This can be used for getting this DataStore
        url: 'voucherListApJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'voucherListAp',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                type: 'int',        mapping: 'id'},
            {name: 'type',      type: 'string',        mapping: 'type'},
            {name: 'voucherDate',         type: 'string',            mapping: 'voucherDate'},
            {name: 'voucherNo',     type: 'string',        mapping: 'voucherNo'},
            {name: 'amount',      type: 'int',        mapping: 'amount'},
            {name: 'drCr',      type: 'string',        mapping: 'drCr'},
            {name: 'status',      type: 'string',        mapping: 'status'},
            {name: 'approvedBy',       type: 'string',        mapping: 'approvedBy'},
            {name: 'note',       type: 'string',        mapping: 'note'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "voucher_list_ap_div",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'voucherNo',
                header: 'Voucher No',
                sortable: true,
                width: 100
            },{
                dataIndex: 'type',
                header: 'Type',
                sortable: true,
                width: 100
            },{
                dataIndex: 'voucherDate',
                header: 'Voucher Date',
                sortable: true,
                width: 100
            },{
                dataIndex: 'drCr',
                header: 'Dr/Cr',
                sortable: true,
                width: 40
            },{
                dataIndex: 'amount',
                header: 'Amount',
                sortable: true,
                width: 100
            },{
                dataIndex: 'note',
                header: 'Note',
                sortable: true,
                width: 200
            },{
                dataIndex: 'approvedBy',
                header: 'Approved By',
                sortable: true,
                width: 200
            },{
                header:'View',
                dataIndex: 'action',
                width: 80,
                renderer: function(v,p,record){
                    renderText="";
                    if(featureDetails = "view"){
                        renderText = renderText + "<a href=\"javascript:window.open(\'../resources/images/csd/adjustVr.png\',\'_blank\', \'toolbar=no, menubar=no, height=543px, width=756px,top=50px, left=200px\')\"><img title='Details' src='../resources/images/csd/papers.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    }

                    return renderText;
                }

            }
        ],
        stripeRows : true,
        bbar: new Ext.PagingToolbar({
            store : theDataStore,
            pageSize : 15,
            displayInfo : true,
            displaymsg : 'Displaying {0} - {1} of {2}',
            emptyMsg : "No records found"
        }),
        tbar: [{
            xtype: "button",
            text: "Not Approved List",
            iconCls: "",
            handler:function(){
                window.location="voucherListNa.erp"
            }
        },{
            xtype: "button",
            text: "Rejected List",
            iconCls: "",
            handler:function(){
                window.location="voucherListRe.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});


})

