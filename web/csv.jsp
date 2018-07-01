<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="jp">
<head>
  <title>社員情報転換</title>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="UTF-8"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div class="container container-fluid">
  <h1>事務センター</h1>
  <hr>
  <form class="form form-inline" name="csvForm" action="${pageContext.request.contextPath}/csv.action" method="post">
    <h4>下記ボタンを押下して、ファイルを処理する。</h4>
    <input class="btn btn-primary" type="submit" value="社員情報を処理">
  </form>
</div>
</body>
</html>
