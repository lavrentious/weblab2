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

    <% if (record.isHit()) { %>
      <img src="https://steamuserimages-a.akamaihd.net/ugc/5072773634787328458/CF4C94259BB7B3C5107F39F03C32B4F88EC091DB/?imw=512&amp;imh=523&amp;ima=fit&amp;impolicy=Letterbox&amp;imcolor=%23000000&amp;letterbox=true" />
    <% } else { %>
      <img src="https://thumbs.dreamstime.com/b/angry-man-showing-thumb-down-gesture-as-rejection-symbol-dislike-african-american-grimacing-white-studio-background-130155704.jpg" />
    <% } %>
  </body>

</html>