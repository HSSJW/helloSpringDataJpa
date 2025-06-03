package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        // 비인증 사용자도 접근 가능하도록 단순화
        return "redirect:/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "성공적으로 로그아웃되었습니다.");
        }
        return "auth/login";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {

        // 유효성 검사 실패
        if (result.hasErrors()) {
            return "auth/signup";
        }

        // 이메일 중복 확인
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "이미 사용 중인 이메일입니다.");
            return "auth/signup";
        }

        try {
            userService.registerUser(user);
            model.addAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요.");
            return "auth/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
            return "auth/signup";
        }
    }
}