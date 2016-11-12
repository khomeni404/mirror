/**
 * Created by IntelliJ IDEA.
 * User: Md Dablu Hossain
 * Date: 7/22/12
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */

var categoryDataStore;
var checkBoxSelMod;
var categoryListingEditorGrid;
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var allowanceGroupStore;



Ext.onReady(function(){

    categoryDataStore = new Ext.data.Store({
        id: "categoryDataStore",
        url: 'categoryJsonData.erp',
        reader: new Ext.data.JsonReader({
            root: 'categoryList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'categoryId', type: 'int', mapping: 'categoryId'},
            {name: 'categoryName', type: 'string', mapping: 'categoryName'},
            {name: 'parentCategory', type: 'string', mapping: 'parentCategory'}

        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    categoryListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "categoryListingEditorGrid",
        store : categoryDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "category_list",
        width: 560,
        height: 300,
        columns : [
            {
                dataIndex: 'categoryId',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'categoryName',
                header: 'ItemCategory Name',
                sortable: true,
                width:200,
                editable: false
            },{
                dataIndex: 'parentCategory',
                header: 'Parent ItemCategory',
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
                window.location="createCategory.erp"
            }

        }]
    });
    categoryDataStore.load({params: {start: 0, limit: 15}});

})

