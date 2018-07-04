
<%@ page contentType="text/html;charset=utf-8" pageEncoding="Shift_JIS" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="jp">
<head>
  <title>社員情報転換</title>
  <meta charset="Shift_JIS">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet" charset="Shift_JIS">
  <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="Shift_JIS"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript" charset="Shift_JIS"></script>
</head>
<body>
<div class="container container-fluid">
  <h1>事務センター</h1>
  <hr>
  <form class="form form-inline" name="emailForm" action="${pageContext.request.contextPath}/email.action" method="post">
    <h4>送信用csvファイルを作成しました。下記ボタンを押下して、関係者にメールを送ります。</h4>
    <input class="btn btn-warning" type="submit" value="下記方々にメールする">
  </form>
  <table class="table table-responsive">
    <thead>
    <tr>
      <th width="10%">番号</th>
      <th width="30%">氏名</th>
      <th width="60%">メール</th>
    </tr>
    </thead>
    <tbody>
    <%--<c:forEach items="employeeList" var="employee">--%>
      <%--<tr>--%>
        <%--<td align="center">${employee.id}</td>--%>
        <%--<td align="center">${employee.name}</td>--%>
        <%--<td align="center">${employee.email}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
    </tbody>
  </table>
</div>
</body>
</html>
