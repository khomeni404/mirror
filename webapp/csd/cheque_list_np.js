var checkBoxSelMod;
var listingEditorGrid;
var editFeature = "view";
var deleteFeature = "passed";
var featureDetails = "postpone";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var theDataStore;



Ext.onReady(function(){

    theDataStore = new Ext.data.Store({
        id: "chequeListNpDataStore",      //This can be used for getting this DataStore
        url: 'chequeListNpJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'chequeList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                type: 'int',        mapping: 'id'},
            {name: 'chequeDetails',      type: 'string',        mapping: 'chequeDetails'},
            {name: 'cid',         type: 'string',            mapping: 'cid'},
            {name: 'name',     type: 'string',        mapping: 'name'},
            {name: 'receiptDate',      type: 'string',        mapping: 'receiptDate'},
            {name: 'receiptNo',      type: 'string',        mapping: 'receiptNo'},
            {name: 'chequeDate',       type: 'string',        mapping: 'chequeDate'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "cheque_list_np_div",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'chequeDetails',
                header: 'Details',
                sortable: true,
                width: 200
            },{
                dataIndex: 'cid',
                header: 'cid',
                sortable: true,
                width: 80
            },{
                dataIndex: 'name',
                header: 'name',
                sortable: true,
                width: 260
            },{
                dataIndex: 'receiptDate',
                header: 'receiptDate',
                sortable: true,
                width: 90
            },{
                dataIndex: 'receiptNo',
                header: 'receiptNo',
                sortable: true,
                width: 60
            },{
                dataIndex: 'chequeDate',
                header: 'chequeDate',
                sortable: true,
                width: 90
            },{
                header:'View | Passed | Reject',
                dataIndex: 'action',
                width: 180,
                renderer: function(v,p,record){
                    renderText="";
                    /*
                    if(featureDetails = "view"){
                        renderText = renderText + " <a href='../resources/images/csd/adjustVr.png'><img title='Details' src='../resources/images/details.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    } */
                    if(featureDetails = "view"){
                        renderText = renderText + "<a href=\"javascript:window.open(\'../resources/images/csd/cheque.JPG\',\'_blank\', \'toolbar=no, menubar=no, height=543px, width=756px,top=50px, left=200px\')\"><img title='Details' src='../resources/images/csd/papers.png'/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    }
                    /*
                    if(editFeature = "passed"){
                        renderText = renderText + " <a href='updateChequeStatus.erp?id="+record.get('id')+"'><img title='Approve' src='../resources/images/approve.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    */
                    renderText = renderText + "<label onclick='abc("+record.get('id')+", "+record.get('chequeDate')+");'><img title='Approve' src='../resources/images/csd/pass.png'/></label> ";
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
            text: "Passed List",
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
    theDataStore.load({params: {start: 0, limit: 10015}});

})

