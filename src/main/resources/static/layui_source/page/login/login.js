layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })



    //登录按钮
    form.on("submit(login)",function() {
        $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
        setTimeout(function () {
            console.log('进入登录程序');
            var username = $("#userName").val();
            var password = $("#password").val();
            console.log(username);
            console.log(password);
            var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            console.log('username:' + username + 'password:' + password);
              $.post('afterCheckLogin',{
                  username:username,
                  password:password
              },function(res){
                /*  top.layer.close(index);
                  localStorage.setItem('token', res.token);*/
                  if(res.code=="true"){
                      // alert(res.code);
                      var token=getToken();
                      window.location.href="/toAfterIndex";
                  }else{
                      alert(res.msg);
                      window.location.reload();
                  }
              },'json')
          },1000);
           /* $.ajax({
                url: "/afterCheckLogin",
                method: 'GET',
                data: {
                    username: username,
                    password: password
                },
              /!*  beforeSend: function (request) {
                    request.setRequestHeader("token", token);
                    request.setRequestHeader("version", version);
                    request.setRequestHeader("taskId", currentTask.id);
                },*!/
                success: function (rs) {

                    alert(rs);
                    //  top.layer.close(index);
                    alert(rs.code);
                    if (rs.code=='true') {
                        // alert(res.code);
                        window.location.href = "/toAfterIndex";
                    } else {
                        alert("rs.msg:"+rs.msg);
                        window.location.reload();
                    }
                },
                error: function  (rs) {
                    alert(rs.msg)
                }

            })*/
        })

    function getToken(){

        if(!!localStorage.getItem('token')){
            return localStorage.getItem('token');
        }

        return null;//如果获取不到token就发送null给服务器端
    }

    function handleTokenFailed(code){
        if(code==401){
            localStorage.clear();
            new Toast().showMsg('登录信息已过期，请重新登录',500)
            setTimeout(function () {
                location.href = 'index.html';
            },1000)

        }
    }

        //表单输入效果
        $(".loginBody .input-item").click(function (e) {
            e.stopPropagation();
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
        })
        $(".loginBody .layui-form-item .layui-input").focus(function () {
            $(this).parent().addClass("layui-input-focus");
        })
        $(".loginBody .layui-form-item .layui-input").blur(function () {
            $(this).parent().removeClass("layui-input-focus");
            if ($(this).val() != '') {
                $(this).parent().addClass("layui-input-active");
            } else {
                $(this).parent().removeClass("layui-input-active");
            }
        })

})
