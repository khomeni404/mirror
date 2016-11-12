var checkBoxSelMod;
var listingEditorGrid;
var editFeature = "edit";
var deleteFeature = "delete";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var theDataStore;



Ext.onReady(function(){

    theDataStore = new Ext.data.Store({
        id: "authorityDataStore",      //This can be used for getting this DataStore
        url: 'incentiveJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'incentiveList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                            type: 'int',        mapping: 'id'},
            {name: 'incentiveName',                 type: 'string',     mapping: 'incentiveName'},
            {name: 'incentiveType',                 type: 'string',     mapping: 'incentiveType'},
            {name: 'incentiveAmount',               type: 'string',     mapping: 'incentiveAmount'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "incentive_list",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'incentiveName',
                header: 'Incentive Name',
                sortable: true,
                width: 190
            },{
                dataIndex: 'incentiveAmount',
                header: 'Incentive Amount',
                sortable: true,
                width: 170
            },{
                dataIndex: 'incentiveType',
                header: 'Incentive Type',
                sortable: true,
                width: 170
            },{
                header:'Action',
                dataIndex: 'action',
                width: 170,
                renderer: function(v,p,record){
                    renderText="";
                    if(editFeature = "edit"){
                        renderText = renderText + " <a href='updateIncentive.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteIncentive.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
            text: "Regular Incentive",
            iconCls: "addRecord",
            handler:function(){
                window.location="createRegularIncentive.erp"
            }
        },{
            xtype: "button",
            text: "Occasional Incentive",
            iconCls: "addRecord",
            handler:function(){
                window.location="createOccasionalIncentive.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});

})

