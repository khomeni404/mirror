var checkBoxSelMod;
var jobTitleListingEditorGrid;
var editJobTitleFeature = "editJobTitle";
var deleteJobTitleFeature = "deleteJobTitle";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var jobTitleDataStore;



Ext.onReady(function(){

    jobTitleDataStore = new Ext.data.Store({
        id: "authorityDataStore",      //This can be used for getting this DataStore
        url: 'jobTitleJsonData.erp',
        reader: new Ext.data.JsonReader({
            root: 'jobTitleList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                   type: 'int',        mapping: 'id'},
            {name: 'titleCode',            type: 'string',     mapping: 'titleCode'},
            {name: 'titleName',            type: 'string',     mapping: 'titleName'},
            {name: 'titleDescription',     type: 'string',     mapping: 'titleDescription'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    jobTitleListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "jobTitleListingEditorGrid",
        store : jobTitleDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "job_title_list",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'titleCode',
                header: 'Title Code',
                sortable: true,
                width: 90
            },{
                dataIndex: 'titleName',
                header: 'Title Name',
                sortable: true,
                width: 200
            },{
                dataIndex: 'titleDescription',
                header: 'Titles Description',
                sortable: true,
                width: 270
            },{
                header:'Action',
                dataIndex: 'action',
                width: 170,
                renderer: function(v,p,record){
                    renderText="";
                    if(editJobTitleFeature = "editJobTitle"){
                        renderText = renderText + " <a href='updateJobTitle.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteJobTitleFeature = "deleteJobTitle"){
                        renderText = renderText + " <a href='deleteJobTitle.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    return renderText;
                }

            }
        ],
        stripeRows : true,
        bbar: new Ext.PagingToolbar({
            store : jobTitleDataStore,
            pageSize : 15,
            displayInfo : true,
            displaymsg : 'Displaying {0} - {1} of {2}',
            emptyMsg : "No records found"
        }),
        tbar: [{
            xtype: "button",
            text: "Add Title",
            iconCls: "addRecord",
            handler:function(){
                window.location="createJobTitle.erp"
            }
        }]
    });
    jobTitleDataStore.load({params: {start: 0, limit: 15}});

})

