package ru.lavrent.weblab2;

import ru.lavrent.weblab2.exceptions.ValidationException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HTTPUtils {
  public static Map<String, String> parseQueryParamsString(String query) {
    Map<String, String> queryPairs = new HashMap<>();

    String[] pairs = query.split("&");
    for (String pair : pairs) {
      String[] keyValue = pair.split("=");
      String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
      String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";
      queryPairs.put(key, value);
    }
    return queryPairs;
  }

  public static int getInt(String query, String key) {
    Map<String, String> map = parseQueryParamsString(query);
    if (!map.containsKey(key)) {
      throw new ValidationException("no %s parameter provided".formatted(key));
    }
    String value = map.get(key);
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new ValidationException("key %s is not an int: %s".formatted(key, value));
    }
  }

  public static float getFloat(String query, String key) {
    Map<String, String> map = parseQueryParamsString(query);
    if (!map.containsKey(key)) {
      throw new ValidationException("no %s parameter provided".formatted(key));
    }
    String value = map.get(key);
    try {
      return Float.parseFloat(value);
    } catch (NumberFormatException e) {
      throw new ValidationException("key %s is not a float: %s".formatted(key, value));
    }
  }

  public static void sendError(HttpServletResponse response, String message) throws IOException {
    Gson gson = new Gson();
    Map<String, Object> jsonResponse = new HashMap<>() {
      {
        put("message", message);
      }
    };
    response.setContentType("application/json");
    response.getWriter().write(gson.toJson(jsonResponse));
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

  public static void sendErrorHtml(HttpServletRequest request, HttpServletResponse response, String message)
      throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    request.setAttribute("error", message);
    request.getRequestDispatcher("/error.jsp").forward(request, response);
  }
}
