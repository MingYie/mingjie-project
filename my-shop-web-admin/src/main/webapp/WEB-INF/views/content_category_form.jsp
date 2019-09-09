<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmat" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<html>
<head>

    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">

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
                            <h3 class="box-title">${tbContentCategory.id==null ? "新增":"编辑"}分类</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start  保存用户信息 post-->
                        <%--使用Spring MVC 表单标签库  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/category/save" method="post" modelAttribute="tbContentCategory">
                            <%--id隐藏域--%>
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="parentId" class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                            <%--pid隐藏域--%>
                                        <form:hidden id="parentId" path="parent.id"/>
                                            <%--使用 <form:input path="name" /> 标签 等同于 <input id="name" name="name" type="text" value=""/>--%>
                                            <%--<form:input path="categoryId" cssClass="form-control required email" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default"/>--%>
                                        <input id="categoryName" class="form-control required" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default" value="${tbContentCategory.parent.name}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">分类名称</label>

                                    <div class="col-sm-10">
                                        <form:input path="name" cssClass="form-control required" placeholder="分类名称"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="sortOrder" class="col-sm-2 control-label">分类排列序号</label>

                                    <div class="col-sm-10">
                                        <form:input path="sortOrder" cssClass="form-control required digits" placeholder="分类排列序号"/>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <!-- 返回上一页 -->
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
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
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>

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
        })
    });
</script>
</body>
</html>