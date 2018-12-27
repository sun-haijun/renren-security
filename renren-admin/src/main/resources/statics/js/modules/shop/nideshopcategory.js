var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
        uploadTitle :'上传配图',
		showList: true,
		title: null,
		nideshopCategory: {
            parentName:null,
            parentId:0,
            orderNum:0
		},
	},
	methods: {
        getCategory: function(){
            //加载部门树
            $.get(baseURL + "shop/nideshopcategory/select", function(r){
                ztree = $.fn.zTree.init($("#categoryTree"), setting, r.categoryList);
                var node = ztree.getNodeByParam("id", vm.nideshopCategory.parentId);
                ztree.selectNode(node);
                vm.nideshopCategory.parentName = node.name;
            })
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
            vm.nideshopCategory = {parentName:null,parentId:0,orderNum:0,wapBannerUrl:'',};
            vm.getCategory();
		},
        update: function () {
            var deptId = getCategoryId();
            if(deptId == null){
                return ;
            }
            $.get(baseURL + "shop/nideshopcategory/info/"+deptId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.nideshopCategory = r.nideshopCategory;
                vm.getCategory();
            });
        },
		saveOrUpdate: function (event) {
			var url = vm.nideshopCategory.id == null ? "shop/nideshopcategory/save" : "shop/nideshopcategory/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.nideshopCategory),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
        del: function () {
            var deptId = getCategoryId();
            if(deptId == null){
                return ;
            }
            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "shop/nideshopcategory/delete",
                    data: "deptId=" + deptId,
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        categoryTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择分类",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#categoryLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //console.log(node[0].isParent);
                    //选择上级部门
                    vm.nideshopCategory.parentId = node[0].id;
                    vm.nideshopCategory.parentName = node[0].name;
                    layer.close(index);
                }
            });
        },
		reload: function (event) {
            vm.showList = true;
            Category.table.refresh();
		}
	}
});

var Category = {
    id: "categoryTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Category.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '分类编号', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '分类名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级分类', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '排序号', field: 'sortOrder', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
    return columns;
};


function getCategoryId () {
    var selected = $('#categoryTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}

$(function () {
    $.get(baseURL + "shop/nideshopcategory/info", function(r){
        var colunms = Category.initColumn();
        var table = new TreeTable(Category.id, baseURL + "shop/nideshopcategory/list", colunms);
        table.setRootCodeValue(r.id);
        table.setExpandColumn(2);
        table.setIdField("id");
        table.setCodeField("id");
        table.setParentCodeField("parentId");
        table.setExpandAll(false);
        table.init();
        Category.table = table;
    });

    // 上传配图
    new AjaxUpload('#upload', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                alert('上传成功');
                vm.nideshopCategory.wapBannerUrl = r.url;
                vm.uploadTitle = '重新上传';
            }else{
                alert(r.msg);
            }
        }
    });
});