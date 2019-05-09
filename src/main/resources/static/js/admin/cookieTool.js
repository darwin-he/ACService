    function setCookie (name, value) {
        if (value) {
            var Days = 365
            var exp = new Date()
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000)
            document.cookie = name + '=' + escape(value) + ';expires=' + exp.toGMTString()
        }
    }

    function getCookie (name) {
        if (document.cookie.length > 0) {
            var begin = document.cookie.indexOf(name + '=')
            if (begin !== -1) {
                begin += name.length + 1 // cookie值的初始位置
                var end = document.cookie.indexOf(';', begin) // 结束位置
                if (end === -1) {
                    end = document.cookie.length // 没有;则end为字符串结束位置
                }
                return unescape(document.cookie.substring(begin, end))
            }
        }
        return null
    }

    function delCookie (name) {
        var exp = new Date()
        exp.setTime(exp.getTime() - 1)
        var cval = setCookie(name)
        if (cval && cval != null) {
            document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString()
        }
    }

    function clearCookie () {
        var keys = document.cookie.match(/[^ =;]+(?=\=)/g)
        if (keys) {
            for (var i = keys.length; i--;) {
                document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
            }
        }
    }