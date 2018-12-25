$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/nideshopad/list',
        datatype: "json",
        colModel: [
            {
                label: '广告主题',
                name: 'name',
                index: 'name',
                width: 80
            },
            {
                label: '广告跳转链接',
                name: 'link',
                index: 'link',
                width: 80
            },
            {
                label: '活动配图',
                name: 'imageUrl',
                index: 'image_url',
                width: 80
            },
            {
                label: '活动说明',
                name: 'content',
                index: 'content',
                width: 80
            },
            {
                label: '活动结束时间',
                name: 'startTime',
                index: 'end_time',
                width: 80
            },
            {
                label: '活动结束时间',
                name: 'endTime',
                index: 'end_time',
                width: 80
            },
            {
                label: '是否启用',
                name: 'enabled',
                width: 60,
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
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

    new AjaxUpload('#upload', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (vm.config.type == null) {
                alert("云存储配置未配置");
                return false;
            }
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.code == 0) {
                alert('上传成功');
                vm.bannerUrl = r.url;
                vm.nideshopAd.imageUrl = r.url;
                vm.uploadTitle = '重新上传';
            } else {
                alert(r.msg);
            }
        }
    });

    $('#start_time').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
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
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        language: "zh-CN",
        todayHighlight: true,
    }).on('changeDate', function (ev) {
        var end_time = $("#end_time").val();
        $("#start_time").datetimepicker('setEndDate', end_time);
        $("#end_time").datetimepicker('hide');
    });



    $('input[type=radio][name=linkType]').change(function () {
        vm.linkType = this.value;
        vm.nideshopAd.linkType = this.value;
        console.log(vm.nideshopAd.linkType);
    });

    $('#topicInput').typeahead({
        source: function (query, process) {
            return $.ajax({
                url: baseURL + "shop/nideshoptopic/select",
                type: 'post',
                data: {
                    topicName: query
                },
                dataType: 'json',
                success: function (result) {
                    // 这里的数据解析根据后台传入格式的不同而不同
                    if (result.code == "0") {
                        console.log(result.data);
                        var resultList = result.data.map(function (item) {
                            var aItem = {
                                id: item.id,
                                name: item.title
                            };
                            return JSON.stringify(aItem);
                        });
                        return process(resultList);
                    } else {
                        alert(result.msg);
                    }
                }
            });
        },
        matcher: function (obj) {
            var item = JSON.parse(obj);
            return~ item.name.toLowerCase().indexOf(this.query.toLowerCase())
        },
        sorter: function (items) {
            var beginswith = [],
                caseSensitive = [],
                caseInsensitive = [],
                item;
            while (aItem = items.shift()) {
                var item = JSON.parse(aItem);
                if (!item.name.toLowerCase().indexOf(this.query.toLowerCase()))
                    beginswith.push(JSON.stringify(item));
                else if (~item.name.indexOf(this.query)) caseSensitive.push(JSON.stringify(item));
                else caseInsensitive.push(JSON.stringify(item));
            }

            return beginswith.concat(caseSensitive, caseInsensitive)

        },
        highlighter: function (obj) {
            var item = JSON.parse(obj);
            var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
            return item.name.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
                return '<strong>' + match + '</strong>'
            })
        },

        updater: function (obj) {
            var item = JSON.parse(obj);
            vm.nideshopAd.linkId = item.id;
            return item.name;
        }

    })
});


// tree 配置
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        uploadTitle: '上传配图',
        showList: true,
        title: null,
        nideshopAd: {
            enabled: 1,
            linkType: '',
        },
        config: {},
        bannerUrl: '',
        linkType: '',
        linkId: '',
        dept: {
            parentName: null,
            parentId: 0,
            orderNum: 0
        }
    },
    created: function () {
        this.getConfig();
    },
    methods: {
        query: function () {
            vm.reload();
        },
        getDept: function () {
            //加载部门树
            $.get(baseURL + "sys/dept/select", function (r) {
                console.log(r);
                ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
                var node = ztree.getNodeByParam("deptId", vm.dept.parentId);
                ztree.selectNode(node);
                vm.dept.parentName = node.name;
            })
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增广告";
            vm.nideshopAd = {};
            vm.nideshopAd = {
                enabled: 1
            };
            vm.getDept();
        },
        getConfig: function () {
            $.getJSON(baseURL + "sys/oss/config", function (r) {
                vm.config = r.config;
            });
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
            var url = vm.nideshopAd.id == null ? "shop/nideshopad/save" : "shop/nideshopad/update";
            var start_time = $("#start_time").val();
            var end_time = $("#end_time").val();

            console.log(start_time)
            console.log(end_time)
            vm.nideshopAd.adStartTime = start_time;
            vm.nideshopAd.adEndTime = end_time;
            console.log(JSON.stringify(vm.nideshopAd));
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.nideshopAd),
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
                    url: baseURL + "shop/nideshopad/delete",
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
            $.get(baseURL + "shop/nideshopad/info/" + id, function (r) {
                vm.nideshopAd = r.nideshopAd;
            });
        },
        // 选择树
        goodsTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    console.log(node[0].deptId)
                    console.log(node[0].name)
                    layer.close(index);
                }
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