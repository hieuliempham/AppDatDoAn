package Api.AppDatDoAn.customerController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrderController {
//    private final CustomerService customerService;
//    private final OrderService orderService;
//
//    private final ShoppingCartService cartService;
//
//    private final CountryService countryService;
//
//    private final CityService cityService;
//
//    @GetMapping("/check-out")
//    public String checkOut(Principal principal, Model model) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            CustomerDto customer = customerService.getCustomer(principal.getName());
//            if (customer.getAddress() == null || customer.getCity() == null || customer.getPhoneNumber() == null) {
//                model.addAttribute("information", "You need update your information before check out");
//                List<Country> countryList = countryService.findAll();
//                List<City> cities = cityService.findAll();
//                model.addAttribute("customer", customer);
//                model.addAttribute("cities", cities);
//                model.addAttribute("countries", countryList);
//                model.addAttribute("title", "Profile");
//                model.addAttribute("page", "Profile");
//                return "customer-information";
//            } else {
//                ShoppingCart cart = customerService.findByUsername(principal.getName()).getCart();
//                model.addAttribute("customer", customer);
//                model.addAttribute("title", "Check-Out");
//                model.addAttribute("page", "Check-Out");
//                model.addAttribute("shoppingCart", cart);
//                model.addAttribute("grandTotal", cart.getTotalItems());
//                return "checkout";
//            }
//        }
//    }
//
//    @GetMapping("/orders")
//    public String getOrders(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            Customer customer = customerService.findByUsername(principal.getName());
//            List<Order> orderList = customer.getOrders();
//            model.addAttribute("orders", orderList);
//            model.addAttribute("title", "Order");
//            model.addAttribute("page", "Order");
//            return "order";
//        }
//    }
//
//    @RequestMapping(value = "/cancel-order", method = {RequestMethod.PUT, RequestMethod.GET})
//    public String cancelOrder(Long id, RedirectAttributes attributes) {
//        orderService.cancelOrder(id);
//        attributes.addFlashAttribute("success", "Cancel order successfully!");
//        return "redirect:/orders";
//    }
//
//
//    @RequestMapping(value = "/add-order", method = {RequestMethod.POST})
//    public String createOrder(Principal principal,
//                              Model model,
//                              HttpSession session) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            Customer customer = customerService.findByUsername(principal.getName());
//            ShoppingCart cart = customer.getCart();
//            Order order = orderService.save(cart);
//            session.removeAttribute("totalItems");
//            model.addAttribute("order", order);
//            model.addAttribute("title", "Order Detail");
//            model.addAttribute("page", "Order Detail");
//            model.addAttribute("success", "Add order successfully");
//            return "order-detail";
//        }
//    }
}
