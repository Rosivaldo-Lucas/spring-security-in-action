package com.github.rosivaldolucas.spring_security_in_action;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Controller {

    @GetMapping
    public ResponseEntity<String> test(HttpServletRequest request) {
        String code = request.getParameter("code");

        String response = """
                {
                    "code": %s
                }
                """.formatted(code);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
