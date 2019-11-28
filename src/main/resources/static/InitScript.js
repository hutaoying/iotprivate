/*
定制
* GET
* url http://lvzhou-at.h3c.com:8000/iot/findall?
* {"tagNames":["aa","bb"]
*
* */

function merge(url,obj) {

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