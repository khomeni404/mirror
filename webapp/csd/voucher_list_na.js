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
        id: "voucherListNaDataStore",      //This can be used for getting this DataStore
        url: 'voucherListNaJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'voucherListNa',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                type: 'int',        mapping: 'id'},
            {name: 'type',      type: 'string',        mapping: 'type'},
            {name: 'voucherDate',         type: 'string',            mapping: 'voucherDate'},
            {name: 'voucherNo',     type: 'string',        mapping: 'voucherNo'},
            {name: 'amount',      type: 'int',        mapping: 'amount'},
            {name: 'drCr',      type: 'string',        mapping: 'drCr'},
            {name: 'operator',      type: 'string',        mapping: 'operator'},
            {name: 'status',      type: 'string',        mapping: 'status'},
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
        renderTo: "voucher_list_na_div",
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
                width: 80
            },{
                dataIndex: 'voucherDate',
                header: 'Voucher Date',
                sortable: true,
                width: 90
            },{
                dataIndex: 'drCr',
                header: 'Dr/Cr',
                sortable: true,
                width: 40
            },{
                dataIndex: 'amount',
                header: 'Amount',
                sortable: true,
                width: 70
            },{
                dataIndex: 'note',
                header: 'Note',
                sortable: true,
                width: 180
            },{
                dataIndex: 'operator',
                header: 'Posted by',
                sortable: true,
                width: 150
            },{
                dataIndex: 'status',
                header: 'Status',
                sortable: true,
                width: 60
            },{
                header:'View | Approve | Reject',
                dataIndex: 'action',
                width: 180,
                renderer: function(v,p,record){
                    renderText="";
                    /*
                    if(featureDetails = "view"){
                        renderText = renderText + " <a href='../resources/images/csd/adjustVr.png'><img title='Details' src='../resources/images/details.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    } */
                    if(featureDetails = "view"){
                        renderText = renderText + "<a href=\"javascript:window.open(\'../resources/images/csd/adjustVr.png\',\'_blank\', \'toolbar=no, menubar=no, height=543px, width=756px,top=50px, left=200px\')\"><img title='Details' src='../resources/images/csd/papers.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    }
                    if(editFeature = "approve"){
                        renderText = renderText + " <a href='approveVoucher.erp?id="+record.get('id')+"'><img title='Approve' src='../resources/images/approve.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "postpone"){
                        renderText = renderText + " <a href='deleteBuilding.erp?id="+record.get('id')+"'><img title='Reject' src='../resources/images/reject.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
            text: "Approved List",
            iconCls: "",
            handler:function(){
                window.location="voucherListAp.erp"
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

