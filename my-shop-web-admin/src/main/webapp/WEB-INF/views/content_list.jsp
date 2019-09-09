<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmat" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<html>
<head>


    <jsp:include page="../includes/header.jsp"/>

    <title>我的商城 | 内容列表</title>

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
                    <%--高级搜素--%>
                    <div class="box box-info box-info-search" style="display: none">
                        <div class="box-header">
                            <div class="box-title">高级搜索</div>
                        </div>
                        <%--  搜索栏--%>
                        <div class="box-body">
                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="title" class="col-sm-4 control-label">标题</label>

                                        <div class="col-sm-8">
                                            <input id="title" class="form-control" placeholder="标题">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="subTitle" class="col-sm-4 control-label">子标题</label>

                                        <div class="col-sm-8">
                                            <input id="subTitle" class="form-control" placeholder="子标题">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>

                                        <div class="col-sm-8">
                                            <input id="titleDesc" class="form-control" placeholder="标题描述">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--                            搜索按钮 dataTables中的专门的动态传参数 绑定search--%>
                        <div class="box-footer">
                            <button type="button" class="btn btn-info pull-right" onclick="serch();">搜索</button>
                        </div>
                    </div>
                    <%--高级搜索结束--%>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                        </div>

                        <div class="box-body">
                            <%--新增按钮--%>
                            <a href="/content/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-default" onclick="checkboxAll.deleteIdArray('/content/delete');"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-primary" onclick="$('.box-info-search').css('display')=='none' ? $('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 搜索</button>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>ID</th>
                                    <th>所属分类</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
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
<sys:modal />
<script>
    //初始化表格
    var _dataTable;
    //通过dataTables表格插件来实现前台分页操作
    //dataTable是jq的表格插件需要自己的代码规范来取json数据  row里面有id值  data里面有对象的属性值
    $(function () {
        var _columns = [
            {
                "data":function (row, type, val, meta) {
                    return '<input class="minimal" id="' +row.id+ '" type="checkbox"/>'
                }
            },
            {"data": "id"},
            {"data": "tbContentCategory.name"},
            {"data": "title"},
            {"data":"subTitle"},
            {"data":"titleDesc"},
            {"data":function (row, type, val, meta) {
                    if (row.url == null){
                        return '';
                    }
                    return '<a href="' +row.url+ '" target="_blank">查看</a>'
                }},
            {"data":function (row, type, val, meta) {
                    if (row.pic == null){
                        return '';
                    }
                    return '<a href="' +row.pic+ '" target="_blank">查看</a>'
                }},
            {"data":function (row, type, val, meta) {
                    if (row.pic2 == null){
                        return '';
                    }
                    return '<a href="' +row.pic2+ '" target="_blank">查看</a>'
                }},
            {"data":function(row, type, val, meta){
                    return DateTime.format(row.updated,"yyyy-MM-dd HH:mm:ss")
                }
            },
            {
                "data":function (row, type, val, meta) {
                    //app.js中查看详情方法的url参数  给查看按钮绑定app.js中的查看详情方法
                    var detailUrl = "/content/detail?id="+row.id;
                    var deleteUrl = "/content/delete";
                    return '<button type="button" class="btn btn-sm btn-default" onclick="checkboxAll.showDetail(\''+detailUrl+'\');"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;'+
                        '<a href="/content/form?id='+row.id+'" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;'+
                        '<button type="button" class="btn btn-sm btn-danger" onclick="checkboxAll.deleteIdSingle(\''+deleteUrl+'\',\''+row.id+'\')"><i class="fa fa-trash-o"></i> 删除</button>';
                }
            }
        ];
        //返回表格数据
        _dataTable = checkboxAll.initDataTables("/content/page",_columns);
    });

    //dataTables的动态传参数给服务器，来实现搜索功能
    function serch() {
        //封装搜索栏里的参数   //当你需要多条件查询，你可以调用此方法，动态修改参数传给服务器
        var title = $("#title").val();
        var subTitle = $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();

        var param ={
            "title":title,
            "subTitle":subTitle,
            "titleDesc":titleDesc
        };

        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
</body>
</html>

