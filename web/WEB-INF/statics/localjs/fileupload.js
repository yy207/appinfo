//upload
var uploadBtn = document.querySelector('.el-upload');
$(".el-upload").bind("change",function(){
    //ajax后台验证--APKName是否已存在
    $.ajax({
        type:"POST",//请求类型
        url:"/appinfo/upload",//请求的url

        contentType:"multipart/form-data",
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
          console.log(data)
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            alert("请求错误！");
        }
    });
});
uploadBtn.onchange = function (e) {
    let files = this.files;
    console.log(this.files);

    const xhr = new XMLHttpRequest();
    xhr.open("post", "/appinfo/upload", true);
    // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            console.log(JSON.parse(this.responseText));
            const {data} = JSON.parse(this.responseText);
            if(!data) return;
            const imageList = data.slice(0);
            let imageStr = '';
            imageList.forEach(img=>{
                imageStr += `<img src="${img}" />`;
            });
            document.getElementById("result").innerHTML = imageStr;
        }
    };

    const formData = new FormData();

    // 多个file 同时上传
    if(files && files.length){
        for (let i=0;i<files.length;i++) {
            formData.append("file", files[i])
        }
    }

    console.log(formData);

    xhr.send(formData);
};