var checkBoxSelMod;
var listingEditorGrid;
var editFeature = "edit";
var deleteFeature = "delete";
var featureDetails = "details";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var theDataStore;



Ext.onReady(function(){

    theDataStore = new Ext.data.Store({
        id: "projectDataStore",      //This can be used for getting this DataStore
        url: 'projectJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'projectList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                type: 'int',        mapping: 'id'},
            {name: 'projectName',       type: 'string',        mapping: 'projectName'},
            {name: 'location',          type: 'string',            mapping: 'location'},
            {name: 'address',           type: 'string',        mapping: 'address'},
            {name: 'hotLine',               type: 'string',        mapping: 'hotLine'},
            {name: 'numberOfBuilding',          type: 'int',        mapping: 'numberOfBuilding'},
            {name: 'landArea',              type: 'int',        mapping: 'landArea'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "project_list_div",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'projectName',
                header: 'Project Name',
                sortable: true,
                width: 250
            },{
                dataIndex: 'location',
                header: 'location',
                sortable: true,
                width: 180
            },{
                dataIndex: 'address',
                header: 'address',
                sortable: true,
                width: 100
            },{
                dataIndex: 'hotLine',
                header: 'hotLine',
                sortable: true,
                width: 130
            },{
                dataIndex: 'numberOfBuilding',
                header: 'numberOfBuilding',
                sortable: true,
                width: 90
            },{
                dataIndex: 'landArea',
                header: 'landArea',
                sortable: true,
                width: 90
            },{
                header:'Actions',
                dataIndex: 'action',
                width: 110,
                renderer: function(v,p,record){
                    renderText="";
                    if(editFeature = "edit"){




                        renderText = renderText + " <a href='editBuilding.erp?id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteBuilding.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if(featureDetails = "details"){
                        renderText = renderText + " <a href='viewBuilding.erp?id="+record.get('id')+"'><img title='Details' src='../resources/images/details.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
                window.location="createBuilding.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 15}});

})

