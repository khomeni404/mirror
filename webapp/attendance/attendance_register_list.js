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
        url: 'attendanceRegisterJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'attendanceRegisterList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',             type: 'int',           mapping: 'id'},
            {name: 'jobDate',      type: 'string',        mapping: 'jobDate'},
            {name: 'remark',  type: 'string',           mapping: 'remark'},
            {name: 'inTime',  type: 'string',           mapping: 'inTime'},
            {name: 'outTime',  type: 'string',           mapping: 'outTime'},
            {name: 'attendant',  type: 'string',           mapping: 'attendant'},
            {name: 'actualWorkHours',  type: 'double',           mapping: 'actualWorkHours'},
            {name: 'lateMinute',     type: 'double',        mapping: 'lateMinute'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "attendance_register_list",
        width: 960,
        height: 300,
        columns : [
            { dataIndex: 'id',            header: 'ID',           sortable: true, width: 80
            },{ dataIndex: 'jobDate',      header: 'Job Date',    sortable: true, width: 90
            },{ dataIndex: 'remark',        header: 'Remark',      sortable: true, width: 90
            },{ dataIndex: 'inTime',     header: 'In Time',   sortable: true, width: 90
            },{ dataIndex: 'outTime',     header: 'Out Time',   sortable: true, width: 90
            },{ dataIndex: 'attendant',  header: 'Attendant',sortable: true, width: 90
            },{ dataIndex: 'actualWorkHours',        header: 'Actual Work Hours',       sortable: true, width: 90
            },{ dataIndex: 'lateMinute', header: 'Late Minute', sortable: true, width: 90
            },{
                header:'Actions',
                dataIndex: 'action',
                width: 170,
                renderer: function(v,p,record){
                    renderText="";
                    if(editFeature = "edit"){
                        renderText = renderText + " <a href='editAttendanceRegister.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteAttendanceRegister.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
                window.location="createAttendanceRegister.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});

})

