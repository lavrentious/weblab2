<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.lavrent.weblab2.models.Record" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
  <head>
    <title>result</title>
  </head>

  <body>
    <h2>records:</h2>
    <% Record record = (Record) request.getAttribute("record"); %>
    <% if (record != null) { %>
      <table border="1">
        <tr>
          <th>x</th>
          <th>y</th>
          <th>r</th>
          <th>hit</th>
        </tr>
        <tr>
          <td>
            <%= record.getX() %>
          </td>
          <td>
            <%= record.getY() %>
          </td>
          <td>
            <%= record.getR() %>
          </td>
          <td>
            <%= record.isHit() ? "Y" : "N" %>
          </td>
        </tr>
      </table>
    <% } else { %>
      <p>no record???</p>
    <% } %>

      <a href="index.jsp">back</a>
  </body>

</html>