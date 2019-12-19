/*
定制
* GET
* url http://lvzhou-at.h3c.com:8000/iot/findall?
* {"tagNames":["aa","bb"]
*
* */

function merge(url,obj,params) {
    var temp =  params[0].toString()
    var str = "name"
    var zh = JSON.parse(temp)
    var ss= zh.attributes[str]
    var js = JSON.parse(obj)
    var arry = obj.tagName
    var str = "";
    for (var i = 0,len = arry.length; i < len; i++) {
        str = str+arry[i]
        if (i != len-1){
            str +=","
        }
    }

    delete js.tagName
    url=url+str
    res={"method":"GET","URL":url,"body":js}
    // res.method= "GET"
    // res.url = url
    return JSON.stringify(res)
    // return js
}
// console.log("aa")