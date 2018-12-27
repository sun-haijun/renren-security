$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/nideshopgoods/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '所属品牌', name: 'brandName', index: 'brand_id', width: 80 },
            { label: '所属分类', name: 'categoryName', index: 'category_id', width: 80 },
			{ label: '商品名称', name: 'name', index: 'name', width: 80 },
			{ label: '商品摘要', name: 'goodsBrief', index: 'goods_brief', width: 80 },
			{ label: '单位价格(元)', name: 'retailPrice', index: 'retail_price', width: 80 },
            { label: '排序编号', name: 'sortOrder', index: 'sort_order', width: 80 },
            { label: '是否有效', name: 'isDelete', width: 40, formatter: function(value, options, row){
				return value === 1 ?
					'<span class="label label-danger">无效</span>' :
					'<span class="label label-success">有效</span>';
			}},
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
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
                vm.nideshopGoods.listPicUrl = r.url;
                vm.uploadTitle = '重新上传';
            }else{
                alert(r.msg);
            }
        }
    });

    $('.summernote').summernote({
        height : '820px',
        width : '100%;',
        lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
    });
});

//编辑器新增的ajax上传图片函数
function sendFile(files, editor, $editable) {
    var size = files[0].size;
    if((size / 1024 / 1024) > 2) {
        alert("图片大小不能超过2M...");
        return false;
    }
    console.log("size="+size);
    var formData = new FormData();
    formData.append("file", files[0]);
    $.ajax({
        data : formData,
        type : "POST",
        url : baseURL + "sys/oss/upload",    // 图片上传出来的url，返回的是图片上传后的路径，http格式
        cache : false,
        contentType : false,
        processData : false,
        dataType : "json",
        success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名
            $('.summernote').summernote('insertImage',data.url);
        },
        error:function(){
            alert("上传失败");
        }
    });
}

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
		nideshopGoods: {
            categoryName:null,
            categoryId:'',
            orderNum:0
		}
	},
	methods: {
        getCategory: function(){
            //加载部门树
            $.get(baseURL + "shop/nideshopcategory/select", function(r){
                ztree = $.fn.zTree.init($("#categoryTree"), setting, r.categoryList);
            })
        },
        loadType: function(){
            var html = "";
            $.get(baseURL + "shop/nideshopbrand/select", function(r){
                if(r.code === 0){
                    //加载数据
                    for (var i = 0; i < r.brandList.length; i++) {
                        html += '<option value="' + r.brandList[i].id + '">' + r.brandList[i].name + '</option>'
                    }
                    $(".chosen-select").append(html);
                    $(".chosen-select").chosen({
                        width:"200px;",
                        height:"30px;"
                    });
                    //点击事件
                    $('.chosen-select').on('change', function(e, params) {
                        console.log(params.selected);
                        vm.nideshopGoods.brandId = params.selected;
                    });
                }else{
                    alert(r.msg);
                }
            });
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.nideshopGoods = {categoryName:null,categoryId:'',orderNum:0,isDelete:0,listPicUrl:''};
            $('.summernote').summernote('code','');
            vm.getCategory();
            vm.loadType();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.loadType();
            vm.getInfo(id);
            vm.getCategory();
		},
		saveOrUpdate: function (event) {
			var url = vm.nideshopGoods.id == null ? "shop/nideshopgoods/save" : "shop/nideshopgoods/update";
            var sHTML = $('.summernote').summernote('code');
            vm.nideshopGoods.goodsDesc = sHTML;
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.nideshopGoods),
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
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "shop/nideshopgoods/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "shop/nideshopgoods/info/"+id, function(r){
                vm.nideshopGoods = r.nideshopGoods;
                $('.summernote').summernote('code',vm.nideshopGoods.goodsDesc);
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
                    if(node[0].isParent){
                        alert('分类必须为子节点');
                    }else{
                        //选择上级部门
                        vm.nideshopGoods.categoryId = node[0].id;
                        vm.nideshopGoods.categoryName = node[0].name;
                        layer.close(index);
                    }
                }
            });
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});