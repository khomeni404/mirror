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
        id: "customerDataStore",      //This can be used for getting this DataStore
        url: 'customerJsonData.erp',     //This is action url for getting JSON data
        reader: new Ext.data.JsonReader({
            root: 'customerList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',             type: 'int',           mapping: 'id'},
            {name: 'bookingDate',      type: 'string',        mapping: 'bookingDate'},
            {name: 'CID',  type: 'string',           mapping: 'CID'},
            {name: 'name',     type: 'string',        mapping: 'name'},
            {name: 'projectName',     type: 'string',        mapping: 'projectName'},
            {name: 'buildingName',     type: 'string',        mapping: 'buildingName'},
            {name: 'AID',     type: 'string',        mapping: 'AID'},
            {name: 'countryLocation',     type: 'string',        mapping: 'countryLocation'},
            {name: 'referenceBy',     type: 'string',        mapping: 'referenceBy'},
            {name: 'offerName',     type: 'string',        mapping: 'offerName'},
            {name: 'status',     type: 'string',        mapping: 'status'},
            {name: 'careBy',     type: 'string',        mapping: 'careBy'},
            {name: 'cellPhone',     type: 'string',        mapping: 'cellPhone'},
            {name: 'altPhone',     type: 'string',        mapping: 'altPhone'},
            {name: 'emailAddress',     type: 'string',        mapping: 'emailAddress'},
            {name: 'mailingAddress',     type: 'string',        mapping: 'mailingAddress'},
            {name: 'floorSize',     type: 'int',        mapping: 'floorSize'},
            {name: 'price',     type: 'int',        mapping: 'price'},
            {name: 'handoverDate',     type: 'string',        mapping: 'handoverDate'},
            {name: 'paymentType',     type: 'string',        mapping: 'paymentType'}

        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    listingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "listingEditorGrid",
        store : theDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "customer_list_div",
        width: 1150,
        height: 500,
        columns : [
            {
                dataIndex: 'CID',
                header: 'CID',
                sortable: true,
                width: 80
            },{
                dataIndex: 'name',
                header: 'CUSTOMER NAME',
                sortable: true,
                width: 280
            },{
                dataIndex: 'AID',
                header: 'AID',
                sortable: true,
                width: 80
            },{
                dataIndex: 'projectName',
                header: 'Project Name',
                sortable: true,
                width: 80
            },{
                dataIndex: 'floorSize',
                header: 'Floor Size',
                sortable: true,
                width: 90
            },{
                dataIndex: 'price',
                header: 'BDT/sft',
                sortable: true,
                width: 90
            },{
                dataIndex: 'referenceBy',
                header: 'Ref',
                sortable: true,
                width: 190
            },{
                dataIndex: 'status',
                header: 'STATUS',
                sortable: true,
                width: 90
            },{
                header:'Actions',
                dataIndex: 'action',
                width: 110,
                renderer: function(v,p,record){
                    renderText="";
                    if(editFeature = "edit"){
                        renderText = renderText + " <a href='editCustomer.erp?errMsg=&id="+record.get('id')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }/*
                    if(deleteFeature = "delete"){
                        renderText = renderText + " <a href='deleteCustomer.erp?id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }*/
                    if(featureDetails = "details"){
                        renderText = renderText + " <a href='viewCustomer.erp?id="+record.get('id')+"'><img title='Details' src='../resources/images/details.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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
                window.location="createCustomer.erp"
            }
        }]
    });
    theDataStore.load({params: {start: 0, limit: 100015}});

})

