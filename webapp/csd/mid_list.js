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
        id: "mIDDataStore",      //This can be used for getting this DataStore
        url: 'mIDJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'mIDList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                type: 'int',        mapping: 'id'},
            {name: 'mid',      type: 'string',        mapping: 'mid'},
            {name: 'name',         type: 'string',            mapping: 'name'},
            {name: 'type',     type: 'string',        mapping: 'type'},
            {name: 'designation',          type: 'string',        mapping: 'designation'},
            {name: 'department',       type: 'string',        mapping: 'department'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "mid_list_div",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'mid',
                header: 'MID',
                sortable: true,
                width: 50
            },{
                dataIndex: 'name',
                header: 'Full Name',
                sortable: true,
                width: 170
            },{
                dataIndex: 'type',
                header: 'Who ?',
                sortable: true,
                width: 70
            },{
                dataIndex: 'designation',
                header: 'Designation',
                sortable: true,
                width: 90
            },{
                dataIndex: 'department',
                header: 'Department',
                sortable: true,
                width: 200
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
    theDataStore.load({params: {start: 0, limit: 10015}});

})

