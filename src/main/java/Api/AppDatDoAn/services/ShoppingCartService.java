package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.entity.ShoppingCart;
import Api.AppDatDoAn.reponsitory.IAccountRepository;
import Api.AppDatDoAn.reponsitory.ICartRepository;
import Api.AppDatDoAn.reponsitory.ISanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class ShoppingCartService {
    private static final String CART_SESSION_KEY = "cart";
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ISanPhamRepository sanPhamRepository;
    @Autowired
    private ICartRepository cartRepository;
    public ShoppingCart addItemToCart(SanPham sanPham, int soLuong, String username){
        Account customer = accountRepository.findByUsername(username);
        if (customer == null) {
            // tạo một thực thể account mới nếu khách hàng không tồn tại
            customer = new Account(username, "", new HashSet<>());
            customer = accountRepository.save(customer);
        }
        ShoppingCart cart = getCart(username);
        cart.addItem(sanPham, soLuong);
        return cartRepository.save(cart);
    }

    public ShoppingCart updateCart(SanPham sanPham, int soLuong, String username){
        ShoppingCart cart = getCart(username);
        cart.updateItem(sanPham, soLuong);
        return cartRepository.save(cart);
    }

    public ShoppingCart removeItemFromCart(SanPham sanPham, String username){
        ShoppingCart cart = getCart(username);
        cart.removeItem(sanPham);
        return cartRepository.save(cart);
    }

    public ShoppingCart addItemToCartSession(ShoppingCart cartDto, SanPham sanPham, int quantity){
        cartDto.addItem(sanPham, quantity);
        return cartDto;
    }

    public ShoppingCart updateCartSession(ShoppingCart cartDto, SanPham sanPham, int quantity){
        cartDto.updateItem(sanPham, quantity);
        return cartDto;
    }

    public ShoppingCart removeItemFromCartSession(ShoppingCart cartDto, SanPham sanPham, int quantity){
        cartDto.removeItem(sanPham);
        return cartDto;
    }

//    ShoppingCart combineCart(ShoppingCart cartDto, ShoppingCart cart){
//        cartDto.combine(cart);
//        return cartDto;
//    }


    public void deleteCartById(Long id){
        cartRepository.deleteById(id);
    }

    public ShoppingCart getCart(String username){
        Optional<ShoppingCart> optionalCart = cartRepository.findByCustomerUsername(username);
        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            ShoppingCart newCart = new ShoppingCart(username);
            return cartRepository.save(newCart);
        }
    }
}
