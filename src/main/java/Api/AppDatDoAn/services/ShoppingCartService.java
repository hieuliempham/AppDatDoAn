package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.entity.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(SanPham sanPham, int soLuong, String username);

    ShoppingCart updateCart(SanPham sanPham, int soLuong, String username);

    ShoppingCart removeItemFromCart(SanPham sanPham, String username);

    ShoppingCart addItemToCartSession(ShoppingCart cartDto, SanPham sanPham, int quantity);

    ShoppingCart updateCartSession(ShoppingCart cartDto, SanPham sanPham, int quantity);

    ShoppingCart removeItemFromCartSession(ShoppingCart cartDto, SanPham sanPham, int quantity);

    ShoppingCart combineCart(ShoppingCart cartDto, ShoppingCart cart);


    void deleteCartById(Long id);

    ShoppingCart getCart(String username);
}
