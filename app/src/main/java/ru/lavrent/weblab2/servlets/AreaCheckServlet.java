package ru.lavrent.weblab2.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.lavrent.weblab2.HTTPUtils;
import ru.lavrent.weblab2.models.Record;

@WebServlet(urlPatterns = "/check-area", loadOnStartup = 1)
public class AreaCheckServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);

  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Boolean isValid = (Boolean) request.getAttribute("valid");
    if (isValid == null || !isValid) {
      HTTPUtils.sendError(response, "request was not validated - use ./controller instead");
      return;
    }

    float x = Float.parseFloat(request.getParameter("x"));
    float y = Float.parseFloat(request.getParameter("y"));
    float r = Float.parseFloat(request.getParameter("r"));

    Record record = new Record(x, y, r, 500); // TODO: script time

    HistoryServlet.addRecord(request, record);

    request.setAttribute("record", record);
    request.getRequestDispatcher("/result.jsp").forward(request, response);

  }
}
