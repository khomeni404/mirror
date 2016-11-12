var checkBoxSelMod;
var jobRoleListingEditorGrid;
var editJobRoleFeature = "editJobRole";
var deleteJobRoleFeature = "deleteJobRole";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var jobRoleDataStore;



Ext.onReady(function(){

    jobRoleDataStore = new Ext.data.Store({
        id: "authorityDataStore",      //This can be used for getting this DataStore
        url: 'jobRoleJsonData.erp',
        reader: new Ext.data.JsonReader({
            root: 'jobRoleList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                    type: 'int',        mapping: 'id'},
            {name: 'roleName',              type: 'string',     mapping: 'roleName'},
            {name: 'roleDescription',       type: 'string',     mapping: 'roleDescription'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    jobRoleListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "jobRoleListingEditorGrid",
        store : jobRoleDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "job_role_list",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'roleName',
                header: 'Role Name',
                sortable: true,
                width: 90
            },{
                dataIndex: 'roleDescription',
                header: 'Role Description',
                sortable: true,
                width: 270
            },{
                header:'Action',
                dataIndex: 'action',
                width: 170,
                renderer: function(v,p,record){
                    renderText="";
                    if(editJobRoleFeature = "editJobRole"){
                        renderText = renderText + " <a href='updateJobRole.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteJobRoleFeature = "deleteJobRole"){
                        renderText = renderText + " <a href='deleteJobRole.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    return renderText;
                }

            }
        ],
        stripeRows : true,
        bbar: new Ext.PagingToolbar({
            store : jobRoleDataStore,
            pageSize : 15,
            displayInfo : true,
            displaymsg : 'Displaying {0} - {1} of {2}',
            emptyMsg : "No records found"
        }),
        tbar: [{
            xtype: "button",
            text: "Add Role",
            iconCls: "addRecord",
            handler:function(){
                window.location="createJobRole.erp"
            }
        }]
    });
    jobRoleDataStore.load({params: {start: 0, limit: 15}});

})

