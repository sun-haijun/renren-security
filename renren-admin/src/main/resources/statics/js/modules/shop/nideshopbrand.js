$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/nideshopbrand/list',
        datatype: "json",
        colModel: [
            {
                label: '品牌编号',
                name: 'id',
                index: 'id',
                width: 40,
                key: true
            },
            {
                label: '品牌名称',
                name: 'name',
                index: 'name',
                width: 60
            },
            {
                label: '品牌介绍',
                name: 'simpleDesc',
                index: 'simple_desc',
                width: 100
            },
            {
                label: '首页配图',
                name: 'newPicUrl',
                index: 'newPicUrl',
                width: 80,
                formatter: function (value, options, row) {
                    return '<button type="button" class="btn-sm btn-success" onclick="showImage(\''+ row.newPicUrl+ '\');">点击预览配图</button>';
                }
            },
            {
                label: '列表配图',
                name: 'appListPicUrl',
                index: 'appListPicUrl',
                width: 80,
                formatter: function (value, options, row) {
                    return '<button type="button" class="btn-sm btn-success" onclick="showImage(\''+ row.appListPicUrl+ '\');">点击预览配图</button>';
                }
            },
            {
                label: '起步价(元)',
                name: 'floorPrice',
                index: 'floor_price',
                width: 30
            },
            {
                label: '是否有效',
                name: 'isShow',
                index: 'is_show',
                width: 30,
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
                }
            },
            {
                label: '首页推荐',
                name: 'isNew',
                index: 'is_new',
                width: 30,
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">否</span>' :
                        '<span class="label label-success">是</span>';
                }
            },
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({
                "overflow-x": "hidden"
            });
        }
    });

    new AjaxUpload('#uploadNewPicUrl', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.code == 0) {
                alert('上传首页配图成功');
                vm.nideshopBrand.newPicUrl = r.url;
            } else {
                alert(r.msg);
            }
        }
    });


    new AjaxUpload('#uploadAppListPicUrl', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {

            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete: function (file, r) {
            console.log(vm.nideshopBrand)
            if (r.code == 0) {
                alert('上传配图成功');
                vm.nideshopBrand.appListPicUrl = r.url;
            } else {
                alert(r.msg);
            }
        }
    });
});


function showImage(obj) {
    vm.tempImageUrl = obj;
    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: "查看配图",
        area: ['515px', '370px'],
        shadeClose: false,
        content: jQuery("#showImage"),
        btn: ['确定', '取消'],
        btn1: function (index) {
            layer.close(index);
        }
    });
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        tempImageUrl:'',
        showList: true,
        title: null,
        nideshopBrand: {},
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.nideshopBrand = {isShow:1,isNew:1,newPicUrl:'',appListPicUrl:''};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.nideshopBrand.id == null ? "shop/nideshopbrand/save" : "shop/nideshopbrand/update";
            console.log(JSON.stringify(vm.nideshopBrand));
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.nideshopBrand),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "shop/nideshopbrand/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "shop/nideshopbrand/info/" + id, function (r) {
                vm.nideshopBrand = r.nideshopBrand;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});