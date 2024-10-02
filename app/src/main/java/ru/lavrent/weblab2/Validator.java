package ru.lavrent.weblab2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ru.lavrent.weblab2.exceptions.ValidationException;

public class Validator {
  public static final Set<Float> rValues = new HashSet<>(Arrays.asList(1f, 1.5f, 2f, 2.5f, 3f));

  public static void validate(float x, float y, float r) {
    if (!(-3 <= x && x <= 3)) {
      throw new ValidationException("x must be between -3 and 3");
    }
    if (!(-5 <= y && y <= 5)) {
      throw new ValidationException("y must be between -5 and 5");
    }
    if (!rValues.contains(r)) {
      throw new ValidationException(
          "r must be in set " + rValues.stream()
              .map(String::valueOf)
              .collect(Collectors.joining(", ")));
    }
  }

  /**
   * checks whether a parameter is present and not empty
   * 
   * @param isPresentFn
   * @param isNotEmptyFn
   * @param paramName
   */
  public static void checkExists(Supplier<Boolean> isPresentFn, Supplier<Boolean> isNotEmptyFn, String paramName) {
    if (!isPresentFn.get()) {
      throw new ValidationException("param %s must be specified".formatted(paramName));
    }
    if (!isNotEmptyFn.get()) {
      throw new ValidationException("param %s must not be empty".formatted(paramName));
    }
  }

  public static int toInt(String value, String paramName) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new ValidationException("key %s is not an int: %s".formatted(paramName, value));
    }
  }

  public static float toFloat(String value, String paramName) {
    try {
      return Float.parseFloat(value);
    } catch (NumberFormatException e) {
      throw new ValidationException("key %s is not a float: %s".formatted(paramName, value));
    }
  }
}
