/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 7/4/12
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */

var checkBoxSelMod;
var employeeListingEditorGrid;
var editEmployeeFeature = "editEmploymentStatus";
var deleteEmployeeFeature = "deleteEmploymentStatus";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var employeeStatusDataStore;



Ext.onReady(function(){

    employeeStatusDataStore = new Ext.data.Store({
        id: "authorityDataStore",
        url: 'employmentStatusJsonData.erp',
        reader: new Ext.data.JsonReader({
            root: 'employmentStatusList',
            totalProperty: 'totalCount',
            id: 'id'
        },[
            {name: 'id',                    type: 'int',        mapping: 'id'},
            {name: 'statusCode',            type: 'string',     mapping: 'statusCode'},
            {name: 'statusName',            type: 'string',     mapping: 'statusName'},
            {name: 'statusDescription',     type: 'string',     mapping: 'statusDescription'}
        ]),
        autoLoad : true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    employeeListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "employmentStatusEditorGrid",
        store : employeeStatusDataStore,
        selModel : checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "list_employment_status",
        width: 960,
        height: 300,
        columns : [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 80,
                sortable: true
            },{
                dataIndex: 'statusCode',
                header: 'Status Code',
                sortable: true,
                width: 90
            },{
                dataIndex: 'statusName',
                header: 'Status Name',
                sortable: true,
                width: 200
            },{
                dataIndex: 'statusDescription',
                header: 'Status Description',
                sortable: true,
                width: 270
            },{
                header:'Action',
                dataIndex: 'action',
                width: 70,
                renderer: function(v,p,record){
                    renderText="";
                    if(editEmployeeFeature = "editEmployeeStatus"){
                        renderText = renderText + " <a href='updateEmploymentStatus.erp?id="+record.get('statusCode')+"'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }

                    if(deleteEmployeeFeature = "deleteEmploymentStatus"){
                        renderText = renderText + " <a href='deleteEmploymentStatus.id="+record.get('id')+"'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    return renderText;
                }

            }
        ],
        stripeRows : true,
        bbar: new Ext.PagingToolbar({
            store : employeeStatusDataStore,
            pageSize : 15,
            displayInfo : true,
            displaymsg : 'Displaying {0} - {1} of {2}',
            emptyMsg : "No records found"
        }),
        tbar: [{
            xtype: "button",
            text: "Add EmpCsd Status",
            iconCls: "addRecord",
            handler:function(){
                window.location="createEmploymentStatus.erp"
            }
        }]
    });
    employeeStatusDataStore.load({params: {start: 0, limit: 15}});

})

