$(document)
    .on("keyup","#inputConfirmPassword,#inputPassword",function () {
        var pass = $("#inputPassword").val();
        var passConf = $("#inputConfirmPassword").val();
        if(pass==passConf){
            $("#create-btn").removeAttr("disabled");

        }else {
            $("#create-btn").attr("disabled","");
        }
        if (pass.length==0 || passConf.length==0 ){
            $("#create-btn").attr("disabled","");
        }


    })