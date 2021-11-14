package eu.vargapeter.financialstatistics.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Model model, HttpServletRequest req, Exception e) throws Exception {

        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        model.addAttribute("exception", e.getMessage());
        model.addAttribute("url", req.getRequestURL());

        log.error(e.getMessage() + '\n' + e.getClass() + ' ' + e);

        return "error";
    }

}
