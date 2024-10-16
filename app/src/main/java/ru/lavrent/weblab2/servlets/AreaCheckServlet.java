package ru.lavrent.weblab2.servlets;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.lavrent.weblab2.models.Record;
import ru.lavrent.weblab2.models.RecordBean;

@WebServlet(urlPatterns = "/check-area", loadOnStartup = 1)
public class AreaCheckServlet extends HttpServlet {
  @Inject
  RecordBean recordBean;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);

  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    float x = Float.parseFloat(request.getParameter("x"));
    float y = Float.parseFloat(request.getParameter("y"));
    float r = Float.parseFloat(request.getParameter("r"));

    Long startTimeMs = (Long) request.getAttribute("startTimeMs");
    Record record = new Record(x, y, r, startTimeMs != null ? System.currentTimeMillis() - startTimeMs : -1);

    recordBean.addRecord(record);
    request.getSession().setAttribute("recordBean", recordBean);

    request.setAttribute("record", record);
    request.getRequestDispatcher("/result.jsp").forward(request, response);

  }
}
