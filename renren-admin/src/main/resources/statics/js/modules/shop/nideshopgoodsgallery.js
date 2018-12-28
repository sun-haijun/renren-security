$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/nideshopgoodsgallery/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '商品名称', name: 'goodsName', index: 'goods_id', width: 80 },
            {
                label: '轮播图片',
                name: 'imgUrl',
                index: 'img_url',
                width: 80,
                formatter: function (value, options, row) {
                    return '<button type="button" class="btn-sm btn-success" onclick="showImage(\''+ row.imgUrl+ '\');">点击预览配图</button>';
                }
            },
			{ label: '排序编号', name: 'sortOrder', index: 'sort_order', width: 80 }
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
                vm.nideshopGoodsGallery.imgUrl = r.url;
                vm.uploadTitle = '重新上传';
            }else{
                alert(r.msg);
            }
        }
    });
});

function showImage(obj) {
    console.log(obj);
    vm.tempImageUrl = obj;
    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: "轮播图",
        area: ['415px', '370px'],
        shadeClose: false,
        content: jQuery("#showImage"),
        btn: ['确定', '取消'],
        btn1: function (index) {
            layer.close(index);
        }
    });
}

var vm = new Vue({
	el:'#rrapp',
	data:{
        uploadTitle :'上传配图',
        tempImageUrl:'',
		showList: true,
		title: null,
		nideshopGoodsGallery: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        loadGoods: function(){
            var html = "";
            $.get(baseURL + "shop/nideshopgoods/select", function(r){
                if(r.code === 0){
                    //加载数据
                    for (var i = 0; i < r.goodsList.length; i++) {
                        html += '<option value="' + r.goodsList[i].id + '">' + r.goodsList[i].name + '</option>'
                    }
                    $("#goodisId").append(html);
                    $("#goodisId").chosen({
                        width:"200px;",
                        height:"30px;"
                    });
                    //点击事件
                    $('#goodisId').on('change', function(e, params) {
                        vm.nideshopGoodsGallery.goodsId = params.selected;
                    });
                }else{
                    alert(r.msg);
                }
            });
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.nideshopGoodsGallery = {imgUrl:'',tempImageUrl:'',};
            vm.loadGoods();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.nideshopGoodsGallery.id == null ? "shop/nideshopgoodsgallery/save" : "shop/nideshopgoodsgallery/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.nideshopGoodsGallery),
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
				    url: baseURL + "shop/nideshopgoodsgallery/delete",
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
			$.get(baseURL + "shop/nideshopgoodsgallery/info/"+id, function(r){
                vm.nideshopGoodsGallery = r.nideshopGoodsGallery;
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