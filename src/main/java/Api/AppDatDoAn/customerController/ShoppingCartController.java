package Api.AppDatDoAn.customerController;

import Api.AppDatDoAn.entity.*;
import Api.AppDatDoAn.services.CustomUserDetailsService;
import Api.AppDatDoAn.services.KhachHangService;
import Api.AppDatDoAn.services.SanPhamService;
import Api.AppDatDoAn.services.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/customer")
public class ShoppingCartController {

    private ShoppingCartService cartService;
    @Autowired
    private SanPhamService productService;
    @Autowired
    private CustomUserDetailsService customerService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/customer/login";
        } else {
            CustomUserDetail userDetail = (CustomUserDetail) ((Authentication) principal).getPrincipal();
            Account customer = userDetail.getAccount();
            ShoppingCart cart = customer.getCart();
            if (cart == null) {
                model.addAttribute("check", true);
            }
            if (cart != null) {
                model.addAttribute("grandTotal", cart.getTongTien());
            }
            model.addAttribute("shoppingCart", cart);
            model.addAttribute("title", "Cart");
            return "customer/cart";
        }

    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(@RequestParam("id") String id,
                                @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                                HttpServletRequest request,
                                Model model,
                                Principal principal,
                                HttpSession session) {


        SanPham productDto = productService.getSanPhamById(id);
        if (principal == null) {
            return "redirect:/login";
        } else {
            String username = principal.getName();
            ShoppingCart shoppingCart = cartService.addItemToCart(productDto, quantity, username);
            session.setAttribute("totalItems", shoppingCart.getTongMatHang());
            model.addAttribute("shoppingCart", shoppingCart);
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("id") String id,
                             @RequestParam("quantity") int quantity,
                             Model model,
                             Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            SanPham productDto = productService.getSanPhamById(id);
            String username = principal.getName();
            ShoppingCart shoppingCart = cartService.updateCart(productDto, quantity, username);
            model.addAttribute("shoppingCart", shoppingCart);
            return "redirect:/cart";
        }

    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
    public String deleteItem(@RequestParam("id") String id,
                             Model model,
                             Principal principal
    ) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            SanPham productDto = productService.getSanPhamById(id);
            String username = principal.getName();
            ShoppingCart shoppingCart = cartService.removeItemFromCart(productDto, username);
            model.addAttribute("shoppingCart", shoppingCart);
            return "redirect:/cart";
        }
    }
}
