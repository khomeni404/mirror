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
        url: 'leaveJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'leaveList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',             type: 'int',           mapping: 'id'},
            {name: 'employeeProfile',      type: 'string',        mapping: 'employeeProfile'},
            {name: 'regularIncentives',  type: 'int',           mapping: 'regularIncentives'},
            {name: 'leaveNotes',     type: 'string',        mapping: 'leaveNotes'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "leave_lists",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'employeeProfile',
                header: 'EmpCsd Profile',
                sortable: true,
                width: 190
            },{
                dataIndex: 'regularIncentives',
                header: 'Regular Incentives',
                sortable: true,
                width: 90
            },{
                dataIndex: 'occasionalIncentives',
                header: 'Occasional Incentives',
                sortable: true,
                width: 270
            },{
                header:'Actions',
                dataIndex: 'action',
                width: 170,
                renderer: function(v,p,record){
                    renderText="";
                    if(editFeature = "edit"){
                        renderText = renderText + " <a href='editEmployee.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteEmployee.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
                window.location="createEmployee.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});

})

