$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/nideshoptopic/list',
        datatype: "json",
        colModel: [			
			{ label: '活动编号', name: 'id', index: 'id', width: 50, key: true },
			{ label: '活动标题', name: 'title', index: 'title', width: 80 },
			{ label: '活动概要', name: 'subtitle', index: 'subtitle', width: 120 },
			{ label: '活动开始时间', name: 'startTime', index: 'end_time', width: 60 },
			{ label: '活动结束时间', name: 'endTime', index: 'end_time', width: 60 },
			{ label: '是否有效', name: 'isShow', width: 40, formatter: function(value, options, row){
					return value === 0 ?
						'<span class="label label-danger">无效</span>' :
						'<span class="label label-success">有效</span>';
				}},
			{ label: '是否推荐', name: 'isNew', width: 40, formatter: function(value, options, row){
				return value === 0 ?
					'<span class="label label-danger">否</span>' :
					'<span class="label label-success">是</span>';
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
				vm.nideshopTopic.scenePicUrl = r.url;
				vm.uploadTitle = '重新上传';
			}else{
				alert(r.msg);
			}
		}
	});

    $('#start_time').datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        minView: "month",//设置只显示到月份
        todayBtn: true,
        language: "zh-CN",
        todayHighlight: true,
        startDate: new Date()
    }).on('changeDate', function (ev) {
        var start_time = $("#start_time").val();
        $("#end_time").datetimepicker('setStartDate', start_time);
        $("#start_time").datetimepicker('hide');
    });

    $('#end_time').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",//设置只显示到月份
        autoclose: true,
        todayBtn: true,
        language: "zh-CN",
        todayHighlight: true,
    }).on('changeDate', function (ev) {
        var end_time = $("#end_time").val();
        $("#start_time").datetimepicker('setEndDate', end_time);
        $("#end_time").datetimepicker('hide');
    });

	$('.summernote').summernote({
		height : '220px',
		width : '600px;',
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		uploadTitle :'上传配图',
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
			vm.nideshopTopic = {isShow:1,scenePicUrl:'',};
            $('.summernote').summernote('code','');
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
            var start_time = $("#start_time").val();
            var end_time = $("#end_time").val();
            var sHTML = $('.summernote').summernote('code');
            vm.nideshopTopic.startTime = start_time;
            vm.nideshopTopic.endTime = end_time;
            vm.nideshopTopic.content = sHTML;
			console.log(vm.nideshopTopic)
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
							alert('保存成功', function(index){
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
                $('.summernote').summernote('code',vm.nideshopTopic.content);
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