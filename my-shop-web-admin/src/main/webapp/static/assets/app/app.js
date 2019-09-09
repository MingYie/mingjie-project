// 私有函数对象
// 私有函数对象里面是多个私有方法，一般都是封装一些jsp中所需要的代码放到私有方法中，通过return来给jsp提供私有方法调用
var checkboxAll = function () {

    //删除功能的存放id的数组
    var _idArray;

    //全选功能的成员变量
    var _maseterCheckbox;//第一个checkbox
    var _checkbox;//接下来所有的checkbox

    //默认的Dropzone参数
    var defaultDropzoneOpts = {
        //uploadController的url地址
        url:"",
        paramName:'dropFile',//文件名
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',  // 设置默认的提示语句
        dictMaxFilesExceeded: "您最多只能上传"+this.maxFiles+"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

    /**
     * //初始化icheck 让他有选中的样式 这是私有方法
     */
    var handlerInitCheckbox = function () {
        // 激活 iCheck  给checkbox添加样式
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

    };

    /**
     * 全选功能
     */
    var handlerCheckboxAll = function () {
        //控制端的checkbox
        _maseterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        //控制端下的所有checkbox
        _checkbox = $('input[type="checkbox"].minimal');

        _maseterCheckbox.on("ifClicked",function (e) {
            //如果e.target.checked为true 就是没有选中第一个
            if (e.target.checked) {
                //那么下边的也都不选中
                _checkbox.iCheck("uncheck")
            }
            //第一个被选中了
            else {
                // 下边的也都选中
                _checkbox.iCheck("check")
            }
        })
    };

    /**
     * 删除单个数据记录
     * @param url
     * @param id
     * @param msg
     */
    var handlerDeleteSingle = function (url, id, msg) {
        //可选参数，msg可传可不传
        if (!msg) msg = null;

        //将id放入数组中，以便和批量删除通用
        _idArray = new Array();
        _idArray.push(id);
        $("#modal-message").html(msg == null ? "您确定要删除数据项吗？":msg);
        $("#modal-default").modal("show");

        /**
         * 调用AJAX异步删除
         * 绑定确定按钮，添加删除方法
         */
        $("#modalOK").bind("click", function () {
            handlerDeleteData(url);
        });
    };


    /**
     * 模态框的弹出条件 和 确定按钮的批量删除和隐藏
     * @param url
     */
    var handlerDeleteIdsClicked = function (url) {
        /**
         * 判断checkbox是否选中 不是undefine 不是空 （用户是否选择了数据项）
         * 把id存到数组
         */
        //定义1个存放id的数组
        _idArray = new Array();
        //遍历所有checkbox 把他们1个个放到数组中去
        _checkbox.each(function () {
            //拿到对应的id
            var _id = $(this).attr("id");
            //如果id不是空和undefine并且被选中的
            if (_id != null && _id != 'undefine' && $(this).is(":checked")) {
                //把对应的id存到数组
                _idArray.push(_id);
            }
        });

        /**
         * 判断用户是否选择了数据项
         * 判断id数组里是否有id 没有id提示信息  有id提示要删除吗？
         */
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据，请至少选择一项");
        } else {
            $("#modal-message").html("您确定要删除这些吗？");
        }
        //用户点了删除按钮，不管怎样都要弹出来弹出框
        $("#modal-default").modal("show");

        /**
         * 如果用户选择了数据项就调用AJAX异步删除
         * 绑定确定按钮，添加删除方法
         */
        $("#modalOK").bind("click", function () {
            handlerDeleteData(url);
        });
    };

    /**
     * AJAX异步删除  批量删除
     * @param url
     */
        //当前私有函数的私有函数 删除操作 ajax异步处理 把前端的id以json格式传到后台
    var handlerDeleteData = function (url) {
        //这是确定按钮的点击事件 不管怎样点了确定都要隐藏
        $("#modal-default").modal("hide");

        //如果没有选择数据项则关闭模态框
        if (_idArray.length > 0) {
            //AJAX异步删除操作
            setTimeout(function () {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "data":{"ids":_idArray.toString()},
                    "dataType":"JSON",
                    "success":function (data) {
                        //无论失败与否，都要解绑原来确定按钮的点击事件，再绑定点击事件为隐藏，防止死循环，来回请求多次。
                        $("#modalOK").unbind("click");

                        //删除成功 data的数据就是BaseResult里的自定义按返回消息success方法
                        if (data.status === 200) {
                            $("#modalOK").bind("click",function () {
                                //刷新页面
                                window.location.reload();
                            });
                        }

                        //删除失败 modal弹出框里的消息是换成data里的message 并且显示来
                        else {
                            //确定按钮的事件改为隐藏
                            $("#modalOK").bind("click",function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        //无论失败与否都要把data里的信息传到模态框里的p标签里，再show出来，这是通用的，都要弹出失败和成功消息
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            },500);

        }
    };


    /**
     * 封装dataTables表格插件所共同的部分
     * @param url 网页地址
     * @param columns 样式和dataTables所需要的参数 这些都是可变的
     */
    var handlerInitDataTables = function (url,columns) {
        //初始化表格
        //dataTable的样式控制 比如我不要搜索框就searching:false
        var _dataTable = $("#dataTable").DataTable({
            "paging":true,
            "info":true,
            "lengthChange":false,
            "ordering":false,
            "processing":true,
            "searching":false,
            "serverSide":true,
            "deferRender":true,
            "ajax":{
                "url":url
            },
            //dataTable是jq的表格插件需要自己的代码规范来取数据  row里面有id值  data里面有对象的属性值
           columns,
            //国际范 本地化语言
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            // 回调函数 因为数据是ajax拿回来的，icheck样式需要被激活 所以只能在页面加载完后重调app.js里的全选方法和icheck样式
            "drawCallback": function( settings ) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        //返回表格数据
        return _dataTable;
    };

    /**
     * 查看详情方法
     *封装具体方法/通过ajax请求html的方式把user_detail.jsp整个页面都放到footer页面的新模态框中。这样每个页面都能查看了
     * @param url 从list中的查看按钮传过来的具体url
     */
    var handlerShowDetail = function (url) {
        $.ajax({
            url:url,
            type:"get",
            dataType:"html" ,
            success:function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 初始化zTree
     * @param url  请求分类ContentCategoryController里的树形结构链接
     * @param autoParam 对应节点的id值
     * @param callback  回调函数 拿到到的是id，放在页面上的是name名字，实际上存的是id
     */
    var handlerInitZtree = function (url,autoParam,callback) {
        var setting = {
            //只能单选
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                // 请求内容分类的url
                url:url,
                autoParam:autoParam
            }
        };

        $.fn.zTree.init($("#myTree"), setting);

        $("#modalOK").bind("click",function () {
            //拿到我的myTree
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            //拿到所有父节点
            var nodes = zTree.getSelectedNodes();
            //如果父节点里面没有数据   （未选择）
            if (nodes.length == 0) {
                alert("请先选择一个节点");
            }
            // (已选择)
            else {
                callback(nodes);
            }
        });
    };


    /**
     * Dropzone的图片拖拽上传的代码规范
     * Dropzone和uploadController后台关联 实现图片拖拽上传
     */
    var handlerInitDropzone = function (opts) {
        Dropzone.autoDiscover = false;//把Dropzone的自动发现关闭控制台就不会爆红了
        //让Dropzone的一些默认参数defaultDropzoneOpts继承content-form中的opts
        $.extend(defaultDropzoneOpts,opts);
        new Dropzone(defaultDropzoneOpts.id,defaultDropzoneOpts);
    };




    //调用私有函数里的私有方法来给外部（jsp）提供使用
    return{
        //初始化调用全选函数
        initAlldo:function(){
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        /**
         * 删除单个
         * @param url
         * @param id
         * @param msg 可选参数
         */
        deleteIdSingle:function(url,id,msg){
            handlerDeleteSingle(url,id,msg);
        },
        /**
         * 批量删除
         * @param url
         */
        deleteIdArray:function (url) {
            handlerDeleteIdsClicked(url);
        },
        /**
         * 初始化DataTables
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTables:function (url, columns) {
            return handlerInitDataTables(url,columns);
        },
        /**
         * 显示详情
         * @param url
         */
        showDetail:function (url) {
            handlerShowDetail(url);
        },

        /**
         * 初始化zTree
         * @param url  请求分类ContentCategoryController里的树形结构链接
         * @param autoParam 对应节点的id值
         * @param callback  回调函数 拿到到的是id，放在页面上的是name名字，实际上存的是id
         */
        initZtree:function (url,autoParam,callback) {
            handlerInitZtree(url,autoParam,callback);
        },

        /**
         * 初始化Dropzone
         * @param opts 是1个对象，里面存elementId和url
         */
        initDropzone:function (opts) {
            handlerInitDropzone(opts);
        }
    }
}();
// 直接调用 上面的小括号绝对不能缺
$(document).ready(function () {
    checkboxAll.initAlldo();
});