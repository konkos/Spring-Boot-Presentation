package gr.uom.opensource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyAdvice {
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> courseNotFoundHandler() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", String.valueOf(HttpStatus.NOT_FOUND));
        errorMap.put("message", "Course Not Found");
        return errorMap;
    }

}
