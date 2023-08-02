var token = localStorage.getItem("token");
if (token != 'null') {

    function renderForm() {
        layui.use('form', function () {
            var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
            form.render();
        });
    }

    layui.use(['form', 'step'], function () {
        var $ = layui.$,
            form = layui.form,
            layer = layui.layer,
            step = layui.step;


        var url = "http://127.0.0.1:9100/QueryBankcard?token=" + token;
        var BankcardList;
        var httpRequest = new XMLHttpRequest();
        httpRequest.open('GET', url, true);
        httpRequest.send();
        httpRequest.onreadystatechange = function () {
            if (httpRequest.readyState == 4 && httpRequest.status == 200) {
                BankcardList = httpRequest.responseText;
                var BankcardListOBJ = JSON.parse(BankcardList);
                for (var i in BankcardListOBJ) {
                    $("#fillbankcard").append("<option value='" + BankcardListOBJ[i].card_id + "'>" + BankcardListOBJ[i].card_id + "------" + BankcardListOBJ[i].card_bank + "</option>");
                    renderForm();
                }
            }
        }


        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '750px',
            height: '500px',
            stepItems: [{
                title: '填写充值业务信息'
            }, {
                title: '确认充值业务信息'
            }, {
                title: '完成'
            }]
        });


        form.on('submit(formStep)', function (data) {
            parameterList = ($('#fillinform').serialize());
            parameterList = decodeURIComponent(parameterList, true);
            fillbankcard = $('#fillbankcard').val();
            if (fillbankcard != null) {
                step.next('#stepForm');
            } else {
                layer.msg('请选择转账账户!');
            }
            return false;
        });

        form.on('submit(formStep2)', function (data) {
            var urltopup = "http://127.0.0.1:9100/operation/topUp.do?" + parameterList;
            var httpRequesttopup = new XMLHttpRequest();
            httpRequesttopup.open('GET', urltopup, true);
            httpRequesttopup.send();
            httpRequesttopup.onreadystatechange = function () {
                if (httpRequesttopup.readyState == 4 && httpRequesttopup.status == 200) {
                    var flag = httpRequesttopup.responseText;
                    if (flag == 'C10001') {
                        step.next('#stepForm');
                    } else if (flag == 'C10002') {
                        layer.msg('余额不足!');
                    } else if (flag == 'C10003') {
                        layer.msg('密码有误!');
                    } else if (flag == 'C10004') {
                        layer.msg('账户状态异常!');
                    } else if (flag == '66666') {
                        layer.msg('可能因网络原因引起的数据库异常，请联系管理员或稍后再试!!');
                    } else if (flag == '77777') {
                        layer.msg('可能因网络原因引起的系统异常，请联系管理员或稍后再试!');
                    }
                }
            }
            return false;
        });

        $('.pre').click(function () {
            step.pre('#stepForm');
        });

        $('.next').click(function () {
            window.location = './personalCenter.html';
        });

        $('.calloff').click(function () {
            window.location = './personalCenter.html';
        });

        $('.nextstep').click(function () {
            fillbankcard = $('#fillbankcard').val();
            fillnumber = $('#fillnumber').val();
            remark = $('#fillinremark').val();
            $("#bankcard").html(fillbankcard);
            $("#number").html(Number(fillnumber).toFixed(2));
            if (remark == '') {
                $("#remark").html("无");
            } else {
                $("#remark").html(remark);
            }
        });
    })
} else {
    window.location = '../page/login.html'
}
