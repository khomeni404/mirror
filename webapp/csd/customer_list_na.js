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
        id: "customerListNaDataStore",      //This can be used for getting this DataStore
        url: 'customerListNaJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'customerListNa',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                type: 'int',        mapping: 'id'},
            {name: 'cid',      type: 'string',        mapping: 'cid'},
            {name: 'bookingDate',         type: 'string',            mapping: 'bookingDate'},
            {name: 'name',     type: 'string',        mapping: 'name'},
            {name: 'referredBy',      type: 'string',        mapping: 'referredBy'},
            {name: 'floorSize',      type: 'int',        mapping: 'floorSize'},
            {name: 'pps',      type: 'int',        mapping: 'pps'},
            {name: 'paid',      type: 'int',        mapping: 'paid'},
            {name: 'handOver',      type: 'string',        mapping: 'handOver'},
            {name: 'offer',       type: 'string',        mapping: 'offer'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "customer_list_na_div",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'cid',
                header: 'CID',
                sortable: true,
                width: 80
            },{
                dataIndex: 'bookingDate',
                header: 'Booking Date',
                sortable: true,
                width: 80
            },{
                dataIndex: 'name',
                header: 'Name',
                sortable: true,
                width: 210
            },{
                dataIndex: 'referredBy',
                header: 'Referred By',
                sortable: true,
                width: 130
            },{
                dataIndex: 'floorSize',
                header: 'Size',
                sortable: true,
                width: 40
            },{
                dataIndex: 'pps',
                header: 'Price',
                sortable: true,
                width: 40
            },{
                dataIndex: 'paid',
                header: 'Paid',
                sortable: true,
                width: 70
            },{
                dataIndex: 'handOver',
                header: 'Hand Over',
                sortable: true,
                width: 80
            },{
                dataIndex: 'offer',
                header: 'Offer',
                sortable: true,
                width: 70
            },{
                header:'View | Approve | Reject',
                dataIndex: 'action',
                width: 160,
                renderer: function(v,p,record){
                    renderText="";
                    /*
                    if(featureDetails = "view"){
                        renderText = renderText + " <a href='../resources/images/csd/adjustVr.png'><img title='Details' src='../resources/images/details.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(featureDetails = "view"){
                        renderText = renderText + "<a href=\"javascript:window.open(\'../resources/images/csd/adjustVr.png\',\'_blank\', \'toolbar=no, menubar=no, height=543px, width=756px,top=50px, left=200px\')\"><img title='Details' src='../resources/images/csd/papers.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    }
                    */
                    if(editFeature = "approve"){
                        renderText = renderText + " <a href='approveCustomer.erp?id="+record.get('id')+"'><img title='Approve' src='../resources/images/approve.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
            pageSize : 50,
            displayInfo : true,
            displaymsg : 'Displaying {0} - {1} of {2}',
            emptyMsg : "No records found"
        }),
        tbar: [{
            xtype: "button",
            text: "Approved List",
            iconCls: "",
            handler:function(){
                window.location="customerListAp.erp"
            }
        },{
            xtype: "button",
            text: "Cancelled List",
            iconCls: "",
            handler:function(){
                window.location="customerListCa.erp"
            }
        },{
            xtype: "button",
            text: "Refunded List",
            iconCls: "",
            handler:function(){
                window.location="customerListRe.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 100015}});

})

