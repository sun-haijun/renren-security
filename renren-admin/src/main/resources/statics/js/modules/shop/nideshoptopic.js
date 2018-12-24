$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/nideshoptopic/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'title', index: 'title', width: 80 }, 			
			{ label: '', name: 'content', index: 'content', width: 80 }, 			
			{ label: '', name: 'avatar', index: 'avatar', width: 80 }, 			
			{ label: '', name: 'itemPicUrl', index: 'item_pic_url', width: 80 }, 			
			{ label: '', name: 'subtitle', index: 'subtitle', width: 80 }, 			
			{ label: '', name: 'topicCategoryId', index: 'topic_category_id', width: 80 }, 			
			{ label: '', name: 'priceInfo', index: 'price_info', width: 80 }, 			
			{ label: '', name: 'readCount', index: 'read_count', width: 80 }, 			
			{ label: '', name: 'scenePicUrl', index: 'scene_pic_url', width: 80 }, 			
			{ label: '', name: 'topicTemplateId', index: 'topic_template_id', width: 80 }, 			
			{ label: '', name: 'topicTagId', index: 'topic_tag_id', width: 80 }, 			
			{ label: '', name: 'sortOrder', index: 'sort_order', width: 80 }, 			
			{ label: '', name: 'isShow', index: 'is_show', width: 80 }, 			
			{ label: '', name: 'address', index: 'address', width: 80 }			
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
		nideshopTopic: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.nideshopTopic = {};
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
			var url = vm.nideshopTopic.id == null ? "shop/nideshoptopic/save" : "shop/nideshoptopic/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.nideshopTopic),
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
				    url: baseURL + "shop/nideshoptopic/delete",
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
			$.get(baseURL + "shop/nideshoptopic/info/"+id, function(r){
                vm.nideshopTopic = r.nideshopTopic;
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