package Api.AppDatDoAn.customerController;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.KhachHang;
import Api.AppDatDoAn.services.CustomUserDetailsService;
import Api.AppDatDoAn.services.KhachHangService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequiredArgsConstructor
@RequestMapping("/customer")
public class LoginController {
    private CustomUserDetailsService customerService;
    private  BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login Page");
        model.addAttribute("page", "Home");
        return "customer/login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("page", "Register");
        model.addAttribute("customerDto", new KhachHang());
        return "customer/register";
    }


//    @PostMapping("/do-register")
//    public String registerCustomer(@Valid @ModelAttribute("customerDto") Account customerDto,
//                                   BindingResult result,
//                                   Model model) {
//        try {
//            if (result.hasErrors()) {
//                model.addAttribute("customerDto", customerDto);
//                return "customer/register";
//            }
//            String username = Account.getUsername();
//            Account customer = (Account) customerService.loadUserByUsername(username);
//            if (customer != null) {
//                model.addAttribute("customerDto", customerDto);
//                model.addAttribute("error", "Email has been register!");
//                return "customer/register";
//            }
//            if (Account.getPassword().equals(customerDto.getResetPasswordToken())) {
//                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
//                customerService.save(customerDto);
//                model.addAttribute("success", "Register successfully!");
//            } else {
//                model.addAttribute("error", "Password is incorrect");
//                model.addAttribute("customerDto", customerDto);
//                return "customer/register";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("error", "Server is error, try again later!");
//        }
//        return "customer/register";
//    }
}
