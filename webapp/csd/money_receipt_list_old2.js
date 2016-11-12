
Ext.onReady(function(){

    var itemsPerPage = 12;

    var store = Ext.create('Ext.data.Store', {
        id:'simpsonsStore',
        autoLoad: false,
        fields:['id', 'receiptNo'],
        pageSize: itemsPerPage,
        proxy: {
            type: 'ajax',
            url: '/mirror/csd/moneyReceiptJsonData.erp',
            reader: {
                type: 'json',
                root: 'receiptList',
                totalProperty: 'totalCount'
            }
        }
    });

// specify segment of data you want to load using params
    store.load({
        params:{
            start:0,
            limit: itemsPerPage
        }
    });

    Ext.create('Ext.grid.Panel', {
        title: 'Simpsons',
        store: store,
        columns: [
            {header: 'ID',  dataIndex: 'id'},
            {header: 'MR No.', dataIndex: 'receiptNo', flex:1}
        ],
        width: 400,
        height: 125,
        dockedItems: [{
            xtype: 'pagingtoolbar',
            store: store,   // same store GridPanel is using
            dock: 'bottom',
            displayInfo: true
        }],
        renderTo: Ext.get('mr_list_div')
    });

});

