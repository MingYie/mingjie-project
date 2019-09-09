/**
 * 函数对象
 */
var Validate = function () {
    /**
     * 私有方法
     * 初始化 jquery validation
     */
    var handlerInitValidate = function () {

        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    };

    /**
     * 自定义前端正则验证规则
     */
    var handlerInitRegex = function () {
        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^1(3|4|5|7|8)\d{9}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");


        // 校验密码：只能输入6-20个字母、数字、下划线
        // $.validator.addMethod("password", function(value, element) {
        //     var length = value.length;
        //     var password =  /^(\w){6,20}$/;
        //     return this.optional(element) || (password.test(value));
        // }, "密码格式错误");
    };




    /**
     * 调用私有方法 给外部提供方法
     */
    return{
        init:function () {
            handlerInitRegex();
            handlerInitValidate();
        }
    }
}();

// 直接调用 上面的小括号绝对不能缺
$(document).ready(function () {
    Validate.init()
});