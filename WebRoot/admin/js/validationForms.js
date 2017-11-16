//判断输入的字符是否为:a-z,A-Z,0-9,且至少6位
function IsString(value)
{
    //var str = document.getElementById('str').value.trim();
    var str = value;
    if(str.length!=0){
        reg=/^[a-zA-Z0-9_]{6,}$/;
        if(reg.test(str)){
            return true;
        }
        return false;
    }
    return false;
}

//验证邮箱
function validation_email(value) {
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(myreg.test(value)){
        return true;
    }else{
        return false;
    }
}

//验证手机
function validation_telephone(value) {
    var myreg = /^((13[0-9]{1})|170|186)+\d{8}$/;
    if(myreg.test(value)){
        return true;
    }else{
        return false;
    }
}