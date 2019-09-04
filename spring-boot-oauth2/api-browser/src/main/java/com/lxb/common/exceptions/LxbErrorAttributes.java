package com.lxb.apibrowser.common.exceptions;

import com.lxb.apibrowser.security.core.support.Response;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* @Author: lixubin
* @Date: 2019-08-20
* @Description:
*/
@Controller
public class LxbErrorAttributes extends BasicErrorController {


    public LxbErrorAttributes(ErrorAttributes errorAttributes,
                              ServerProperties serverProperties,
                              List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    /**
     * 错误处理方法
     *
     * @param request
     * @return
     */
    @Override
    @RequestMapping
    public ResponseEntity error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        Response response = new Response()
                .setSuccess(false)
                .setStatus(status.value())
                .setMessage(String.valueOf(body.get("message")));

        return new ResponseEntity<>(response, status);
    }
}
