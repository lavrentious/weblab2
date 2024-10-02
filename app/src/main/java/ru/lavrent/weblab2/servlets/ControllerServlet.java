package ru.lavrent.weblab2.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ru.lavrent.weblab2.Validator;
import ru.lavrent.weblab2.exceptions.ValidationException;

@WebServlet(urlPatterns = "/controller", loadOnStartup = 1)
public class ControllerServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // for X, Y, R check present and not empty
    try {
      for (String paramName : new String[] { "x", "y", "r" }) {
        Validator.checkExists(
            () -> request.getParameter(paramName) != null,
            () -> !request.getParameter(paramName).isEmpty(),
            paramName);
      }

      float x = Validator.toFloat(request.getParameter("x"), "x");
      float y = Validator.toFloat(request.getParameter("y"), "y");
      float r = Validator.toFloat(request.getParameter("r"), "r");
      Validator.validate(x, y, r);

      response.sendRedirect("./check-area?" + request.getQueryString());
    } catch (ValidationException e) {
      sendError(response, e.getMessage());
      return;
    }
  }

  private void sendError(HttpServletResponse response, String message) throws IOException {
    Gson gson = new Gson();
    Map<String, Object> jsonResponse = new HashMap<>() {
      {
        put("message", message);
      }
    };
    response.setContentType("application/json");
    response.getWriter().write(gson.toJson(jsonResponse));
    response.setStatus(400);
  }
}
