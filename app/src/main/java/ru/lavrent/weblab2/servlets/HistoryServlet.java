package ru.lavrent.weblab2.servlets;

import java.io.IOException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    String ifModifiedSinceHeader = request.getHeader("If-Modified-Since");
    Date lastModifiedDate = getLastModifiedDate(request);
    response.setHeader("Last-Modified", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(lastModifiedDate));
    if (ifModifiedSinceHeader != null) {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        Date ifModifiedSince = dateFormat.parse(ifModifiedSinceHeader);

        if (!lastModifiedDate.after(ifModifiedSince)) {
          response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
          return;
        }
      } catch (Exception e) {
      }
    }

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
    setLastModifiedDate(request, new Date());
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
  }

  public static void addRecord(HttpServletRequest request, Record record) {
    RecordBean recordBean = HistoryServlet.getBean(request);
    setLastModifiedDate(request, new Date());
    recordBean.addRecord(record);
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

  private static Date getLastModifiedDate(HttpServletRequest request) {
    HttpSession session = request.getSession();
    Date lastModified = (Date) session.getAttribute("lastModified");
    if (lastModified == null) {
      lastModified = new Date();
      session.setAttribute("lastModified", lastModified);
    }
    return lastModified;
  }

  private static void setLastModifiedDate(HttpServletRequest request, Date lastModified) {
    HttpSession session = request.getSession();
    session.setAttribute("lastModified", lastModified);
  }
}
