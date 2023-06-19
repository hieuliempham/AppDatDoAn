package Api.AppDatDoAn.customerController;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.ShoppingCart;
import Api.AppDatDoAn.services.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping
    public String home_customer(Model model, Principal principal, HttpSession session) {
        model.addAttribute("title", "Home");
        model.addAttribute("page", "Home");
        if (principal != null) {
            AccountService accountService = new AccountService();
            Account customer = accountService.getAccountByUsername(principal.getName());
            session.setAttribute("username", customer.getUsername());
            ShoppingCart shoppingCart = customer.getCart();
            if (shoppingCart != null) {
                session.setAttribute("totalItems", shoppingCart.getTongMatHang());
            }
        }
        return "customer/home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact");
        model.addAttribute("page", "Contact");
        return "customer/contact-us";
    }

//    private final CustomerService customerService;
//    private final CountryService countryService;
//    private final PasswordEncoder passwordEncoder;
//    private final CityService cityService;
//
//    @GetMapping("/profile")
//    public String profile(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            String username = principal.getName();
//            CustomerDto customer = customerService.getCustomer(username);
//            List<Country> countryList = countryService.findAll();
//            List<City> cities = cityService.findAll();
//            model.addAttribute("customer", customer);
//            model.addAttribute("cities", cities);
//            model.addAttribute("countries", countryList);
//            model.addAttribute("title", "Profile");
//            model.addAttribute("page", "Profile");
//            return "customer-information";
//        }
//    }
//
//    @PostMapping("/update-profile")
//    public String updateProfile(@Valid @ModelAttribute("customer") CustomerDto customerDto,
//                                BindingResult result,
//                                RedirectAttributes attributes,
//                                Model model,
//                                Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            String username = principal.getName();
//            CustomerDto customer = customerService.getCustomer(username);
//            List<Country> countryList = countryService.findAll();
//            List<City> cities = cityService.findAll();
//            model.addAttribute("countries", countryList);
//            model.addAttribute("cities", cities);
//            if (result.hasErrors()) {
//                return "customer-information";
//            }
//            customerService.update(customerDto);
//            CustomerDto customerUpdate = customerService.getCustomer(principal.getName());
//            attributes.addFlashAttribute("success", "Update successfully!");
//            model.addAttribute("customer", customerUpdate);
//            return "redirect:/profile";
//        }
//    }
//
//    @GetMapping("/change-password")
//    public String changePassword(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("title", "Change password");
//        model.addAttribute("page", "Change password");
//        return "change-password";
//    }
//
//    @PostMapping("/change-password")
//    public String changePass(@RequestParam("oldPassword") String oldPassword,
//                             @RequestParam("newPassword") String newPassword,
//                             @RequestParam("repeatNewPassword") String repeatPassword,
//                             RedirectAttributes attributes,
//                             Model model,
//                             Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            CustomerDto customer = customerService.getCustomer(principal.getName());
//            if (passwordEncoder.matches(oldPassword, customer.getPassword())
//                    && !passwordEncoder.matches(newPassword, oldPassword)
//                    && !passwordEncoder.matches(newPassword, customer.getPassword())
//                    && repeatPassword.equals(newPassword) && newPassword.length() >= 5) {
//                customer.setPassword(passwordEncoder.encode(newPassword));
//                customerService.changePass(customer);
//                attributes.addFlashAttribute("success", "Your password has been changed successfully!");
//                return "redirect:/profile";
//            } else {
//                model.addAttribute("message", "Your password is wrong");
//                return "change-password";
//            }
//        }
//    }
}
