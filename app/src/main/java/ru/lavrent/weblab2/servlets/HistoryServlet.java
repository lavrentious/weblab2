package ru.lavrent.weblab2.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.lavrent.weblab2.models.RecordBean;
import ru.lavrent.weblab2.models.Record;
import java.io.PrintWriter;
import com.google.gson.Gson;

@WebServlet(urlPatterns = "/history", loadOnStartup = 1)
public class HistoryServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RecordBean bean = HistoryServlet.getBean(request);
    List<Record> history = bean.getRecords();
    String historyJson = new Gson().toJson(history);

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    out.print(historyJson);
    out.flush();
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HistoryServlet.getBean(request).clear();
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
  }

  public static RecordBean getBean(HttpServletRequest request) {
    HttpSession session = request.getSession();
    RecordBean bean = (RecordBean) session.getAttribute("recordBean");
    if (bean == null) {
      bean = new RecordBean();
      session.setAttribute("recordBean", bean);
    }
    return bean;
  }
}
