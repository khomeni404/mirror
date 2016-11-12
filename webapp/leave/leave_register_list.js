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
        url: 'leaveRegisterJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'leaveRegisterList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                            type: 'int',           mapping: 'id'},
            {name: 'fromDate',                      type: 'string',        mapping: 'fromDate'},
            {name: 'toDate',                        type: 'string',        mapping: 'toDate'},
            {name: 'applyDate',                     type: 'string',        mapping: 'applyDate'},
            {name: 'leaveDays',                     type: 'int',           mapping: 'leaveDays'},
            {name: 'leavePurpose',                  type: 'string',        mapping: 'leavePurpose'},
            {name: 'remark',                        type: 'string',        mapping: 'remark'},
            {name: 'leaveType',                     type: 'string',        mapping: 'leaveType'},
            {name: 'leaveStatus',                   type: 'int',           mapping: 'leaveStatus'},
            {name: 'approvedDisapprovedReason',     type: 'string',        mapping: 'approvedDisapprovedReason'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "leave_register_list",
        width: 960,
        height: 300,
        columns : [
              { dataIndex: 'id',            header: 'ID',           sortable: true, width: 80
            },{ dataIndex: 'fromDate',      header: 'From Date',    sortable: true, width: 90
            },{ dataIndex: 'toDate',        header: 'To Date',      sortable: true, width: 90
            },{ dataIndex: 'applyDate',     header: 'Apply Date',   sortable: true, width: 90
            },{ dataIndex: 'leaveDays',     header: 'Leave Days',   sortable: true, width: 90
            },{ dataIndex: 'leavePurpose',  header: 'Leave Purpose',sortable: true, width: 90
            },{ dataIndex: 'remark',        header: 'Remark',       sortable: true, width: 90
            },{ dataIndex: 'leaveType',     header: 'Leave Type',   sortable: true, width: 90
            },{ dataIndex: 'leaveStatus',   header: 'Leave Status', sortable: true, width: 90
            },{ dataIndex: 'approvedDisapprovedReason', header: 'Approved Disapproved Reason', sortable: true, width: 90
            },{ dataIndex: 'action',        header:'Actions',       width: 170,
                renderer: function(v,p,record){
                    renderText="";
                    if(editFeature = "edit"){
                        renderText = renderText + " <a href='updateLeaveRegister.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteLeaveRegister.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
                window.location="createLeaveRegister.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});

})

