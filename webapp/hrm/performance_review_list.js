/**
* Created by IntelliJ IDEA.
* User: Md Dablu Hossain
* Date: 15/07/12
* Time: 12:27 AM
* To change this template use File | Settings | File Templates.
*/

var allowanceDataStore;
var checkBoxSelMod;
var allowanceListingEditorGrid;
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var allowanceGroupStore;



Ext.onReady(function(){

    allowanceDataStore = new Ext.data.Store({
        id: "authorityDataStore",
        url: 'performanceReviewJsonData.erp',
        reader: new Ext.data.JsonReader({
            root: 'performanceList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id', type: 'int', mapping: 'id'},
            {name: 'reviewee', type: 'string', mapping: 'reviewee'},
            {name: 'reviewer', type: 'string', mapping: 'reviewer'},
            {name: 'department', type: 'boolean', mapping: 'department'},
            {name: 'from', type: 'int', mapping: 'from'},
            {name: 'to', type: 'string', mapping: 'to'},
            {name: 'dueDate', type: 'string', mapping: 'dueDate'},
            {name: 'notes', type: 'boolean', mapping: 'notes'},
            {name: 'status', type: 'boolean', mapping: 'status'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    allowanceListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "authorityListingEditorGrid",
        store : allowanceDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "list_performance",
        width: 560,
        height: 300,
        columns : [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'reviewee',
                header: 'Review',
                sortable: true,
                width:200,
                editable: false
            },{
                dataIndex: 'reviewer',
                header: 'Reviewer',
                sortable: true,
                width: 75
            },{
                dataIndex: 'department',
                header: 'Department',
                sortable: true,
                width: 75
            },{
                dataIndex: 'from',
                header: 'From',
                sortable: true,
                width: 75
            } ,  {
                dataIndex: 'to',
                header: 'To',
                width: 80,
                sortable: true
            },{
                dataIndex: 'dueDate',
                header: 'Due Date',
                sortable: true,
                width:200,
                editable: false
            },{
                dataIndex: 'notes',
                header: 'Notes',
                sortable: true,
                width: 75
            },{
                dataIndex: 'status',
                header: 'Active Status',
                sortable: true,
                width: 75
            }
        ],
        stripeRows : true,
        bbar: new Ext.PagingToolbar({
            store : allowanceDataStore,
            pageSize : 15,
            displayInfo : true,
            displaymsg : 'Displaying {0} - {1} of {2}',
            emptyMsg : "No records found"
        }),
        tbar: [{
            xtype: "button",
            text: "Add User",
            iconCls: "addRecord",
            handler:function(){
                window.location="createUser.erp"
            }
        },{
            xtype: "button",
            text: "Group Assignment",
            iconCls: "assignment",
            handler:function(){
                window.location="createUser.erp"
            }
        }]
    });
    allowanceDataStore.load({params: {start: 0, limit: 15}});

})

