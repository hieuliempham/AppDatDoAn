package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "accountId")
    private Account customer;

    private double tongTien;

    private int tongMatHang;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new HashSet<>();
        this.tongMatHang = 0;
        this.tongTien = 0.0;
    }
    public void addItem(SanPham sanPham, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getSanPham().getMasanpham().equals(sanPham.getMasanpham())) {
                item.incrementQuantity(quantity);
                recalculateCart();
                return;
            }
        }
        CartItem newItem = new CartItem(sanPham, quantity, this);
        cartItems.add(newItem);
        recalculateCart();
    }

    public void updateItem(SanPham sanPham, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getSanPham().getMasanpham().equals(sanPham.getMasanpham())) {
                item.setSoLuong(quantity);
                recalculateCart();
                return;
            }
        }
    }


    public void removeItem(SanPham sanPham) {
        for (Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
            CartItem item = iterator.next();
            if (item.getSanPham().getMasanpham().equals(sanPham.getMasanpham())) {
                iterator.remove();
                item.setCart(null);
                recalculateCart();
                return;
            }
        }
    }

    public void clear() {
        for (CartItem item : cartItems) {
            item.setCart(null);
        }
        cartItems.clear();
        tongMatHang = 0;
        tongTien = 0.0;
    }




    private void recalculateCart() {
        tongMatHang = 0;
        tongTien = 0.0;
        for (CartItem item : cartItems) {
            tongMatHang += item.getSoLuong();
            tongTien += item.getSubtotal();
        }
    }

    public ShoppingCart(String username) {
        this();
        this.customer = new Account(username, "", new HashSet<>());
    }
    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", customer=" + customer.getUsername() +
                ", totalPrice=" + tongTien +
                ", totalItems=" + tongMatHang +
                ", cartItems=" + cartItems.size() +
                '}';
    }
}
