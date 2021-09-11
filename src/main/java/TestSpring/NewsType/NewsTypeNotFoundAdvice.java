package TestSpring.NewsType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class NewsTypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(NewsTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String newsTypeNotFoundHandler(NewsTypeNotFoundException ex) {
        return ex.getMessage();
    }
}
