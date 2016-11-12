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
        url: 'jobSpecJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'jobSpecList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                        type: 'int',        mapping: 'id'},
            {name: 'jobSpecName',               type: 'string',     mapping: 'jobSpecName'},
            {name: 'jobSpecDuties',             type: 'string',     mapping: 'jobSpecDuties'},
            {name: 'jobSpecDescription',        type: 'string',     mapping: 'jobSpecDescription'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "job_spec_list",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'jobSpecName',
                header: 'Spec Name',
                sortable: true,
                width: 90
            },{
                dataIndex: 'jobSpecDuties',
                header: 'Spec Duties',
                sortable: true,
                width: 90
            },{
                dataIndex: 'jobSpecDescription',
                header: 'Spec Description',
                sortable: true,
                width: 270
            },{
                header:'Action',
                dataIndex: 'action',
                width: 170,
                renderer: function(v,p,record){
                    renderText="";
                    if(editFeature = "edit"){
                        renderText = renderText + " <a href='updateJobSpec.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteJobSpec.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
            text: "Add Spec",
            iconCls: "addRecord",
            handler:function(){
                window.location="createJobSpec.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});

})

