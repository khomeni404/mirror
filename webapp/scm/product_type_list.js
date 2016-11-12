/**
 * Created by IntelliJ IDEA.
 * User: Masum
 * Date: 7/24/12
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */

var productTypeDataStore;
var checkBoxSelMod;
var productTypeListingEditorGrid;
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var allowanceGroupStore;



Ext.onReady(function(){

    productTypeDataStore = new Ext.data.Store({
        id: "productTypeDataStore",
        url: 'productTypeJsonData.erp',
        reader: new Ext.data.JsonReader({
            root: 'productTypeList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'productTypeId', type: 'int', mapping: 'productTypeId'},
            {name: 'name', type: 'string', mapping: 'name'},
            {name: 'description', type: 'string', mapping: 'description'}

        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    productTypeListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "productTypeListingEditorGrid",
        store : productTypeDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "productType_list",
        width: 560,
        height: 300,
        columns : [
            {
                dataIndex: 'productTypeId',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'name',
                header: 'Product Type Name',
                sortable: true,
                width:200,
                editable: false
            },{
                dataIndex: 'description',
                header: 'Product description',
                sortable: true,
                width: 75
            }
        ],
        stripeRows : true,
        bbar: new Ext.PagingToolbar({
            store : categoryDataStore,
            pageSize : 15,
            displayInfo : true,
            displaymsg : 'Displaying {0} - {1} of {2}',
            emptyMsg : "No records found"
        }),
        tbar: [{
            xtype: "button",
            text: "Add ItemCategory",
            iconCls: "addRecord",
            handler:function(){
                window.location="createProductType.erp"
            }

        }]
    });
    categoryDataStore.load({params: {start: 0, limit: 15}});

})


