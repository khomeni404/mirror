/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 7/4/12
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */

var userDataStore;
var checkBoxSelMod;
var userListingEditorGrid;
var editUserFeature = "editUser";
var deleteUserFeature = "deleteUser";
var confirmationMessage;

var checkBoxSelModForGroupAssignment;
var groupListingEditorGridForAssignment;
var userGroupStore;


Ext.onReady(function () {

    userDataStore = new Ext.data.Store({
        id: "authorityDataStore",
        url: 'userJsonData.erp',
        reader: new Ext.data.JsonReader({
            root: 'userList',
            totalProperty: 'totalCount',
            id: 'id'
        }, [
            {name: 'id', type: 'int', mapping: 'id'},
            {name: 'username', type: 'string', mapping: 'username'},
            {name: 'password', type: 'string', mapping: 'password'},
            {name: 'name', type: 'string', mapping: 'name'},
            {name: 'designation', type: 'string', mapping: 'designation'},
            {name: 'department', type: 'string', mapping: 'department'},
            {name: 'active', type: 'boolean', mapping: 'active'}
        ]),
        autoLoad: true
    })

    checkBoxSelMod = new Ext.grid.CheckboxSelectionModel();
    userListingEditorGrid = new Ext.grid.EditorGridPanel({
        id: "userListingEditorGrid",
        store: userDataStore,
        selModel: checkBoxSelMod,
        clicksToEdit: 2,
        renderTo: "list_employee",
        width: 960,
        height: 600,
        columns: [
            {
                dataIndex: 'id',
                header: 'ID',
                width: 50,
                sortable: true
            },
            {
                dataIndex: 'name',
                header: 'User\'s Name',
                sortable: true,
                width: 340
            },
            {
                dataIndex: 'designation',
                header: 'Designation',
                sortable: true,
                width: 150
            },
            {
                dataIndex: 'department',
                header: 'Department',
                sortable: true,
                width: 150
            },
            {
                dataIndex: 'username',
                header: 'Username',
                sortable: true,
                width: 100,
                editable: false
            },
            {
                header: 'Action',
                dataIndex: 'action',
                width: 150,
                renderer: function (v, p, record) {
                    renderText = "";
                    if (editUserFeature = "editUser") {
                        renderText = renderText + " <a href='editUser.erp?id=" + record.get('id') + "'><img title='Edit' src='../resources/images/edit.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }

                    if (deleteUserFeature = "deleteUser") {
                        renderText = renderText + " <a href='deleteUser.erp?id=" + record.get('id') + "'><img title='Delete' src='../resources/images/delete.png'/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    return renderText;
                }

            }
        ],
        stripeRows: true,
        bbar: new Ext.PagingToolbar({
            store: userDataStore,
            pageSize: 50,
            displayInfo: true,
            displaymsg: 'Displaying {0} - {1} of {2}',
            emptyMsg: "No records found"
        }),
        tbar: [
            {
                xtype: "button",
                text: "Add User",
                iconCls: "addRecord",
                handler: function () {
                    window.location = "createUser.erp"
                }
            },
            {
                xtype: "button",
                text: "Group Assignment",
                iconCls: "assignment",
                handler: function () {
                    window.location = "createUser.erp"
                }
            }
        ]
    });
    userDataStore.load({params: {start: 0, limit: 50}});

})

