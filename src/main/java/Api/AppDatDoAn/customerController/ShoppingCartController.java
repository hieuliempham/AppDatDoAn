package Api.AppDatDoAn.customerController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {
//    private final ShoppingCartService cartService;
//    private final ProductService productService;
//    private final CustomerService customerService;
//
//    @GetMapping("/cart")
//    public String cart(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            Customer customer = customerService.findByUsername(principal.getName());
//            ShoppingCart cart = customer.getCart();
//            if (cart == null) {
//                model.addAttribute("check");
//
//            }
//            if (cart != null) {
//                model.addAttribute("grandTotal", cart.getTotalPrice());
//            }
//            model.addAttribute("shoppingCart", cart);
//            model.addAttribute("title", "Cart");
//            return "cart";
//        }
//
//    }
//
//    @PostMapping("/add-to-cart")
//    public String addItemToCart(@RequestParam("id") Long id,
//                                @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
//                                HttpServletRequest request,
//                                Model model,
//                                Principal principal,
//                                HttpSession session) {
//
//
//        ProductDto productDto = productService.getById(id);
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            String username = principal.getName();
//            ShoppingCart shoppingCart = cartService.addItemToCart(productDto, quantity, username);
//            session.setAttribute("totalItems", shoppingCart.getTotalItems());
//            model.addAttribute("shoppingCart", shoppingCart);
//        }
//        return "redirect:" + request.getHeader("Referer");
//    }
//
//    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
//    public String updateCart(@RequestParam("id") Long id,
//                             @RequestParam("quantity") int quantity,
//                             Model model,
//                             Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            ProductDto productDto = productService.getById(id);
//            String username = principal.getName();
//            ShoppingCart shoppingCart = cartService.updateCart(productDto, quantity, username);
//            model.addAttribute("shoppingCart", shoppingCart);
//            return "redirect:/cart";
//        }
//
//    }
//
//    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
//    public String deleteItem(@RequestParam("id") Long id,
//                             Model model,
//                             Principal principal
//    ) {
//        if (principal == null) {
//            return "redirect:/login";
//        } else {
//            ProductDto productDto = productService.getById(id);
//            String username = principal.getName();
//            ShoppingCart shoppingCart = cartService.removeItemFromCart(productDto, username);
//            model.addAttribute("shoppingCart", shoppingCart);
//            return "redirect:/cart";
//        }
//    }
}
