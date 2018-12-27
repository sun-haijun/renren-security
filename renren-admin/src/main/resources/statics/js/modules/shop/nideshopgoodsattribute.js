$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/nideshopgoodsattribute/list',
        datatype: "json",
        colModel: [			
			{ label: '商品名称', name: 'goodsName', index: 'goods_id', width: 80 },
			{ label: '参数项目', name: 'attributeName', index: 'attribute_id', width: 80 },
			{ label: '配置说明', name: 'value', index: 'value', width: 80 }			
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		nideshopGoodsAttribute: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        loadType: function(){
            var html = "";
            $.get(baseURL + "shop/nideshopattribute/select", function(r){
                if(r.code === 0){
                    //加载数据
                    for (var i = 0; i < r.attrList.length; i++) {
                        html += '<option value="' + r.attrList[i].id + '">' + r.attrList[i].name + '</option>'
                    }
                    $("#attrType").append(html);
                    $("#attrType").chosen({
                        width:"200px;",
                        height:"30px;"
                    });
                    //点击事件
                    $('#attrType').on('change', function(e, params) {
                        vm.nideshopGoodsAttribute.attributeId = params.selected;
                    });
                }else{
                    alert(r.msg);
                }
            });
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
                        vm.nideshopGoodsAttribute.goodsId = params.selected;
                    });
                }else{
                    alert(r.msg);
                }
            });
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增配置";
			vm.nideshopGoodsAttribute = {isDelete:0,value:''};
            vm.loadType();
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
			var url = vm.nideshopGoodsAttribute.id == null ? "shop/nideshopgoodsattribute/save" : "shop/nideshopgoodsattribute/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.nideshopGoodsAttribute),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功,可以继续操作，操作完成后点击"返回"按钮即可', function(index){
                            vm.nideshopGoodsAttribute.value = "";
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
				    url: baseURL + "shop/nideshopgoodsattribute/delete",
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
			$.get(baseURL + "shop/nideshopgoodsattribute/info/"+id, function(r){
                vm.nideshopGoodsAttribute = r.nideshopGoodsAttribute;
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