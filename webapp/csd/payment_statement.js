var checkBoxSelMod;
var listingEditorGrid;
var editFeature = "edit";
var deleteFeature = "delete";
var featureDetails = "details";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var theDataStore;

function theCallbackFunction(){
    return "125"; //document.getElementById("cidd").value;
}


Ext.onReady(function(){

    theDataStore = new Ext.data.Store({
        id: "paymentStatementDataStore",            //This can be used for getting this DataStore
        url: 'paymentStatementJsonData.erp',        //This is action url for getting JSON data
        callbackParam: 'theCallbackFunction',
        callbackPrefix: 'DPL-000',
        scriptIdPrefix: 'injectedScript',
        reader: new Ext.data.JsonReader({
            root: 'receiptList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                type: 'int',           mapping: 'id'},
            {name: 'receiptNo',         type: 'string',        mapping: 'receiptNo'},
            {name: 'receiptDate',       type: 'string',        mapping: 'receiptDate'},
            {name: 'amount',            type: 'int',           mapping: 'amount'},
            {name: 'paymentMethod',     type: 'string',        mapping: 'paymentMethod'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "mr_list_div",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'receiptNo',
                header: 'Receipt No',
                sortable: true,
                width: 120
            },{
                dataIndex: 'receiptDate',
                header: 'receiptDate',
                sortable: true,
                width: 100
            },{
                dataIndex: 'amount',
                header: 'Amount',
                sortable: true,
                width: 100
            },{
                dataIndex: 'paymentMethod',
                header: 'paymentMethod',
                sortable: true,
                width: 100
            },{
                header:'Actions',
                dataIndex: 'action',
                width: 110,
                renderer: function(v, p, record){
                    renderText="";
                    if(editFeature = "edit"){
                        renderText = renderText + " <a href='editMoneyReceipt.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteMoneyReceipt.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(featureDetails = "details"){
                        renderText = renderText + " <a href='viewMoneyReceipt.erp?id="+record.get('id')+"'><img title='Details' src='../resources/images/details.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
            text: "Add",
            iconCls: "addRecord",
            handler:function(){
                window.location="createMoneyReceipt.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});

})

