<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<html>
<body>
<div>登陆后：${users.userpassword}</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
    $.post("allAction",{},function (data) {
        alert(JSON.stringify(data));
    });
</script>
<h2>Hello World!</h2>
</body>
</html>
