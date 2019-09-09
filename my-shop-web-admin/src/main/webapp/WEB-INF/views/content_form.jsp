<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmat" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<html>
<head>

    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <%--ztree插件实现模态框中的树形结构--%>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
    <%--dropzone实现图片拖拽上传--%>
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dropzone.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>


    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small>空</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/shouye"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <%--                    //从UserController返回消息的判断 是就显示success 不是就danger--%>
                    <c:if test="${baseResult.message != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id==null ? "新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start  保存用户信息 post-->
                        <%--使用Spring MVC 表单标签库  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <%--id隐藏域--%>
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                        <form:hidden id="categoryId" path="tbContentCategory.id"/>
                                        <%--使用 <form:input path="name" /> 标签 等同于 <input id="name" name="name" type="text" value=""/>--%>
<%--                                        <form:input path="categoryId" cssClass="form-control required email" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default"/>--%>
                                        <input id="categoryName" class="form-control required" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default" value="${tbContent.tbContentCategory.name}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="title" cssClass="form-control required" placeholder="标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control required" placeholder="子标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control required" placeholder="标题描述"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>

                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control required" placeholder="链接"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control required" placeholder="图片1"/>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control required" placeholder="图片2"/>
                                        <div id="dropz2" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">详情</label>

                                    <div class="col-sm-10">
                                        <form:hidden path="content"/>
                                            <%-- <form:textarea rows="5" path="content" cssClass="form-control required" placeholder="详情"/>--%>
                                            <%--富文本编辑器--%>
                                        <div id="div3">${tbContent.content}</div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <!-- 返回上一页 -->
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button id="btnSubmit" type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/Copyright.jsp"/>

    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<jsp:include page="../includes/footer.jsp"/>
<%--ztree插件实现模态框中的树形结构--%>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<%--dropzone实现图片拖拽上传--%>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>

<%--自定义模态框--%>
<sys:modal title="请选择" message="<ul id='myTree' class='ztree'></ul>"/>

<script>
    // zTree插件里的代码规范
    $(function () {
        checkboxAll.initZtree("/content/category/tree/data",["id"],function (nodes) {
            //返回的1个数组，而我只需要1个
            var node = nodes[0];
            //拿到的是id，放在页面上的是name名字，实际上存的是id
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            //点确定隐藏
            $("#modal-default").modal("hide");


        });

        InitWangEditor();
    });

    /**
     * 初始化wangEditor富文本编辑器
    */
    function InitWangEditor() {
        var E = window.wangEditor;
        var editor = new E('#div3');
        //配置服务器端地址和文件名 都在UploadController中
        editor.customConfig.uploadImgServer = '/upload';
        editor.customConfig.uploadFileName = 'wangEditorFile';
        editor.create();
        //给提交按钮添加点击事件
        $("#btnSubmit").bind("click",function () {
            //获取html内容
            var contentHtml = editor.txt.html();
            $("#content").val(contentHtml);
        })
    }

    /**
     * 下面的2个是Dropzone的对象参数，具体封装都在app.js中
     */
    //传入的是opts对象 让defaultDropzoneOpts继承
    checkboxAll.initDropzone({
        id:"#dropz",
        url:"/upload",
        // 文件上传的事件，用来把图片的名字传到数据库
        init:function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件 注意这里取的是UploadController中存在Map集合中的值
                // 而且是按照键名fileName取的值
                $("#pic").val(data.fileName);
            });
        }
    });

    //传入的是opts对象 让defaultDropzoneOpts继承
    checkboxAll.initDropzone({
        id:"#dropz2",
        url:"/upload",
        // 文件上传的事件，用来把图片的名字传到数据库
        init:function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件 注意这里取的是UploadController中存在Map集合中的值
                // 而且是按照键名fileName取的值
                $("#pic2").val(data.fileName);
            });
        }
    });
</script>
</body>
</html>

