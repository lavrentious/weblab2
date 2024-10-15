package ru.lavrent.weblab2.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.lavrent.weblab2.HTTPUtils;
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

      request.setAttribute("valid", true);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/check-area");
      dispatcher.forward(request, response);
    } catch (ValidationException e) {
      HTTPUtils.sendError(response, e.getMessage());
      return;
    }
  }

}
