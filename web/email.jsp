
<%@ page contentType="text/html;charset=cp943" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="jp">
<head>
  <title>�Ј����]��</title>
  <meta charset="Shift_JIS">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div class="container container-fluid">
  <h1>�����Z���^�[</h1>
  <hr>
  <form class="form form-inline" name="emailForm" action="" method="post">
    <h4>���M�pcsv�t�@�C�����쐬���܂����B���L�{�^�����������āA�֌W�҂Ƀ��[���𑗂�܂��B</h4>
    <input class="btn btn-warning" type="submit" value="���L���X�Ƀ��[������">
  </form>
  <table class="table table-responsive">
    <thead>
    <tr>
      <th width="10%">�ԍ�</th>
      <th width="30%">����</th>
      <th width="60%">���[��</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="employeeList" var="employee">
      <tr>
        <td align="center">${employee.id}</td>
        <td align="center">${employee.name}</td>
        <td align="center">${employee.email}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
