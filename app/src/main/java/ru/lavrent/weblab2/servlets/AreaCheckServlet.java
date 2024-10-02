package ru.lavrent.weblab2.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.lavrent.weblab2.models.RecordBean;
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
    int x = Integer.parseInt(request.getParameter("x"));
    int y = Integer.parseInt(request.getParameter("y"));
    float r = Float.parseFloat(request.getParameter("r"));

    Record record = new Record(x, y, r, 500); // TODO: script time

    HttpSession session = request.getSession();
    RecordBean recordBean = (RecordBean) session.getAttribute("recordBean");
    if (recordBean == null) {
      recordBean = new RecordBean();
      session.setAttribute("recordBean", recordBean);
    }
    recordBean.addRecord(record);

    request.setAttribute("record", record);
    request.getRequestDispatcher("/result.jsp").forward(request, response);

  }
}
