package kr.ac.hansung.cse.hellospringdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "애플리케이션이 정상 작동 중입니다!";
    }

    // 간단한 HTML 응답 테스트
    @GetMapping("/test-html")
    @ResponseBody
    public String testHtml() {
        return """
            <!DOCTYPE html>
            <html>
            <head><title>테스트</title></head>
            <body>
                <h1>HTML 응답 테스트</h1>
                <p>이 페이지가 보이면 컨트롤러는 정상 작동합니다.</p>
            </body>
            </html>
            """;
    }
}