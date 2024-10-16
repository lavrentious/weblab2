<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.lavrent.weblab2.models.Record" %>
<%@ page import="ru.lavrent.weblab2.models.RecordBean" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="recordBean" class="ru.lavrent.weblab2.models.RecordBean" scope="session" />
<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>LabWork2</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" type="text/css" media="screen" href="main.css" />

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
    rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
</head>

<body>
  <div class="container">
    <h1>ERROR!</h1>
    <img style="width: 100%;" src="https://familypolicyalliance.com/wp-content/uploads/2021/08/Policeman-blowing-whistle-iStock-w.jpg" />
    <h3 style="color: red; text-shadow: 2px 2px 3px #bbb;">
      ${error}
    </h3>
    <a href="index.jsp">back</a>
  </div>
</body>

</html>