package ru.lavrent.weblab2.servlets;

import java.io.IOException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.lavrent.weblab2.models.RecordBean;
import ru.lavrent.weblab2.models.Record;
import java.io.PrintWriter;
import com.google.gson.Gson;

@WebServlet(urlPatterns = "/history", loadOnStartup = 1)
public class HistoryServlet extends HttpServlet {
  @Inject
  private RecordBean recordBean;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Record> history = recordBean.getRecords();

    String ifModifiedSinceHeader = request.getHeader("If-Modified-Since");
    Date lastModifiedDate = recordBean.getLastModifiedDate();
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
    this.recordBean.clear();
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
  }
}
