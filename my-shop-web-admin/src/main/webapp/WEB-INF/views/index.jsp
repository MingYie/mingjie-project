<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    Head Begin--%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>我的商城 | 登录</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="static/assets/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
<!-- Font Awesome -->
<link rel="stylesheet" href="static/assets/bower_components/font-awesome/css/font-awesome.min.css"/>
<!-- Ionicons -->
<link rel="stylesheet" href="static/assets/bower_components/Ionicons/css/ionicons.min.css"/>
<!-- Theme style -->
<link rel="stylesheet" href="static/assets/css/AdminLTE.min.css"/>
<!-- iCheck -->
<link rel="stylesheet" href="static/assets/plugins/iCheck/square/blue.css"/>

</head>
<%--Head End--%>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#">我的商城</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">欢迎管理员登录</p>
        <form action="/index" method="post">

            <div class="alert alert-danger alert-dismissible" ${message==null? "style='display:none;'": ""}>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                ${message}
            </div>

            <div class="form-group has-feedback">
                <input name="email" type="email" class="form-control" placeholder="邮箱">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input name="password" type="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox"> 记住我
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <a href="#">忘记密码？</a><br>
        <a href="#" class="text-center">注册1个新的账号</a>
    </div>
</div>


<!-- jQuery 3 -->
<script src="static/assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="static/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="static/assets/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });
    });
</script>

</body>
</html>
