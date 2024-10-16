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
  <header class="header">
    <span>Лаврентьев Лев Денисович</span>
    <small>P3219</small>
    <small style="float: right">Вариант: 10283</small>
  </header>

  <div class="container">
    <h1>LabWork2</h1>
    <div style="display: flex; flex-wrap: wrap">
      <div class="p-1 m-1" style="flex-grow: 1">
        <h2>График</h2>
        <canvas id="canvas" class="outlined" width="400" height="400"></canvas>

        <hr />

        <form id="form" method="get" action="controller">
          <div class="w-100">
              <label for="xInput">X: </label>
              <input class="w-100" type="text" id="xInput" name="x" placeholder="Введите значение X от -3 до 3" />
          </div>

          <div class="w-100">
              <label for="yInput">Y: </label>
              <input class="w-100" type="text" id="yInput" name="y" placeholder="Введите значение Y от -5 до 5" />
          </div>

          <fieldset class="outlined" id="formRFieldset" style="list-style-type: none">
            <legend>R:</legend>
          </fieldset>
          <input type="submit" value="Проверить" id="submitButton" class="l-button w-100" disabled/>
        </form>
      </div>

      <div class="p-1 m-1" style="flex-grow: 10">
        <div>
          <h2 style="display: inline; vertical-align: middle">История</h2>
          <button style="vertical-align: middle" class="l-button sm muted m-1" id="historyClearButton">
            очистить
          </button>
        </div>
        <table class="l-table w-100">
          <thead>
            <tr>
              <th>Дата и время</th>
              <th>X</th>
              <th>Y</th>
              <th>R</th>
              <th>Результат</th>
              <th>Время работы скрипта</th>
            </tr>
          </thead>
          <tbody id="historyTableBody">
            <% if (recordBean != null && !recordBean.getRecords().isEmpty()) { %>
              <% for (Record record : recordBean.getRecords()) { %>
              <tr>
                  <td><%= new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(record.getCreatedAt())) %></td>
                  <td><%= record.getX() %></td>
                  <td><%= record.getY() %></td>
                  <td><%= record.getR() %></td>
                  <td><%= record.isHit() ? "Y" : "N" %></td>
                  <td><%= record.getScriptTime() %></td>
              </tr>
              <% } %>
            <% } else { %>
                <td colspan="6">history empty</td>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <!-- <script>var exports = {};</script> -->
  <script type="module" src="js/bundle.js"></script>
</body>

</html>