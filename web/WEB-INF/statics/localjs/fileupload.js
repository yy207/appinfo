//upload
var uploadBtn = document.querySelector('.el-upload');
$(".el-upload").bind("change",function(){
    let files = this.files;
    const formData = new FormData();
    // 多个file 同时上传
    if(files && files.length){
        for (let i=0;i<files.length;i++) {
            formData.append("file", files[i])
            console.log(files[i])
        }
    }


    $.ajax({
        type:"POST",//请求类型
        url:"/appinfo/upload",//请求的url
        contentType:false,
        data:formData,
        cache: false,
        processData:false,
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(res){//data：返回数据（json对象）
          console.log(res.data)
            if (res.code == 0){
                $("#logoLocPath").val(res.data)
                $("#logoPicPath").val(res.data)
                alert("上传成功！")
            }
            else{
                alert("上传失败！")
            }

        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            alert("请求错误！");
        }
    });
});