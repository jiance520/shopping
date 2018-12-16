$(document).ready(function () {
    $('#dg').datagrid({
        //真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
        fitColumns:true,
        //隔行变色
        striped:true,
        //是否显示行号
//        rownumbers:true,
//        工具栏在最下面
//        fit:true,
        columns:[[
            // {field:'ck',checkbox:true},
//            {field:'id',title:'bill Id',width:60},
            {field:'billCode',title:'订单编码',width:70,align:'center',sortable:true},
            {field:'productName',title:'商品名称',width:100,align:'center',editor:'text',sortable:true,order:'desc'},
            {field:'totalPrice',title:'订单金额',width:70,align:'center',editor:'numberbox',sortable:true,order:'asc'},
            {field:'isPayment',title:'是否付款',width:50,align:'center',sortable:true,
                formatter:function (value,row,index) {
                    if(value=="2"){
                        return "已付款";
                    }
                    else{
                        return "<span style='font-size:12px;color:red'>未付款</span>";
                    }
                },
                editor:'numberbox'},
            // {field:'createdBy',title:'制表人',width:100},
            {field:'creationDate',title:'创建时间',width:100,align:'center',sortable:true,
                formatter:function(value,row,index){//alert(value)是一长串数字，
                    var date = new Date(value);//var date = new Date("september 1,2013,14:58:12");
                    var creationDate1 = myformatter(date);//用来显示，无时分秒
                    var creationDate2 = myformatter2(date);//真实时间带时分秒
                    row.creationDate = creationDate2;//有时分秒
//                    row.creationDate = creationDate1;//无时分秒
                    return creationDate1;
                }},
            {field:'proName',title:'供应商',width:150,align:'center',sortable:true,
                editor: {
                    type: 'combobox',
                    options: {
                        url: '../prviderAction',
                        valueField: "id",
                        textField: "proname",
                        editable: true//定义用户是否可以直接输入文本到字段中。
                    }
                }
            },
            {field:'action',title:'操作',width:100,align:'center',
                formatter:function(value,row,index){ // value ：当前值，row:行对象，index:行下标
                    if (row.editing){ // row.editing 是否被编辑
                        var s = '<a href="javascript:void(0)"  onclick="saverow('+index+')">保存</a> ';
                        var c = '<a href="javascript:void(0)"  onclick="cancelrow('+index+')">取消</a>';
                        return s+c;
                    } else {
                        var f = '<a href="javascript:void(0)"  onclick="showrow('+index+')"><img src="../images/read.png" height="20" width="20"/></a> ';
                        var e = '<a href="javascript:void(0)"  onclick="editrow('+index+')"><img src="../images/xiugai.png" height="20" width="20"/></a> ';
                        var d = '<a href="javascript:void(0)"  onclick="deleterow('+index+')"><img src="../images/schu.png" height="20" width="20"/></a>';
                        return f+e+d;
                    }
                }
            }
        ]],
        onBeforeEdit:function(index,row){
            row.editing = true;
            //重新刷新行
            $('#dg').datagrid('refreshRow', index);
        },
        onAfterEdit:function(index,row){
            row.editing = false;
            //重新刷新行
            //$('#dg').datagrid('refreshRow', index);
            //如果有时间字段，使用以下代码刷新。
            var jstr = JSON.stringify(row);//对象转 json string
//            var sobj = JSON.parse(jstr); //json string 转 js对象
//            var dt = sobj.creationDate;
//            var dt2 = dt.split(" ")[0];
//            dt2 = dt2.split("/");
//            var times = dt2[2]+"-"+dt2[0]+"-"+dt2[1];//转换后的时间
//            sobj.creationDate = times ;
//            alert(jstr);
            $.post('../updateBill',row,function(data){
                $('#dg').datagrid('reload');
            });
        },
        onCancelEdit:function(index,row){
            row.editing = false;
            $('#dg').datagrid('refreshRow', index);
        },
        pagination:true,
        pageSize:8,
        pageList:[8,16,24],
        toolbar:[{
            text:'添加菜单',
            iconCls:'icon-add',
            handler:function(){
                $("#win-bill-add").window('open');
            }
        }]
    });
})
function editrow(index){
    // 开始编辑，发送命令
    $('#dg').datagrid('beginEdit', index);
}
function deleterow(index){
    $.messager.confirm('Confirm','Are you sure?',function(r){
        if (r){
            // 得到所有行
            var rows = $('#dg').datagrid('getRows');
//                alert(rows.length);
            // 根据下标拿当前行的数据
            var billCode = rows[index].billCode ;
            // 发送ajax
            $.post('../delBill',{billcode:billCode},function(data){
//                    alert(data);
                if(data>0){
                    // 删除1行，发命令
                    $('#dg').datagrid('deleteRow', index);
                    $('#dg').datagrid('refreshRow', index);
                }
            });

        }
    });
}
function saverow(index){
    // 结束编辑
    $('#dg').datagrid('endEdit', index);
}
function cancelrow(index){
    $('#dg').datagrid('cancelEdit', index);
}
function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myformatter2(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    var h = date.getHours();
    var mi = date.getMinutes();
    var s = date.getSeconds();
    var sss = date.get
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+":"+(mi<10?('0'+mi):mi)+":"+(s<10?('0'+s):s);
}
//             分页
var pager = $('#dg').datagrid().datagrid('getPager');      // get the pager of datagrid
pager.pagination({
    displayMsg:'显示第 {from} 到 {to} 条记录，共 {total} 条记录'
});
<!--下拉选择菜单-->
$.post("../prviderAction",{},function (data) {
//            $(".providerid").html("");/*empty()*/
    $.each(data,function (i,n) {
        var node = "<option>";
        $("[name='providerid']").append($(node).val(n.id).html(n.proname));
    });
},"json");
<!--供应商下拉菜单--><!--combobox下拉菜单-->
$('#providerIdBillAdd').combobox({
    url:'../prviderAction',
    valueField:'id',
    textField:'proname',
    editable: true,
    formatter: formatItemProvider
});
//    标题搜索下拉菜单
$('#productname').combobox({
    url:'billAction',
    valueField:'id',
    textField:'productname',
    editable: true,
    formatter: formatItemBill
});
function formatItemProvider(row){
    var s = '<span style="font:normal 12px 宋体">' + row.proname + '</span>';
    return s;
}
function formatItemBill(row){
    var s = '<span style="font:normal 12px 宋体">' + row.productname + '</span>';
    return s;
}
<!--模糊查询-->
function submitBillSearch() {
    var productname = $("#productname").val();
    var providerid = $(".providerid").val();
    var ispayment = $(".ispayment").val();
    $('#dg').datagrid('load', {
        productname:productname,
        providerid:providerid,
        ispayment:ispayment
    });
}
<!--工具栏弹出表单窗口-->
function submitBillAdd(){
    $('#ff-bill-add').form('submit',{
            url:'../addBill',
            onSubmit:function(){
                //return false;
            },
            success:function(data){
//                    alert(data);
                if(data>0){
                    $('#win-bill-add').window('close');
                    $('#dg').datagrid('reload');
                    $.messager.show({
                        title:"添加提示",
                        msg:"添加成功",
                        timeout:"3000",
                        showType:"slide"
                    });
                }
            }
        }
    );
}
//   关闭弹窗
function backBillAdd(){
    $('#win-bill-add').window('close');
}