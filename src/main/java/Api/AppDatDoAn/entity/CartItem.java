package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "shopping_cart_id")
    private ShoppingCart cart;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "masanpham")
    private SanPham sanPham;

    private int soLuong;
    private double donViTinh;

    public CartItem(SanPham product, int quantity, ShoppingCart cart) {
        this.sanPham = product;
        this.soLuong = quantity;
        this.cart = cart;
    }

    public double getSubtotal() {
        return sanPham.getGia() * soLuong;
    }

    public void incrementQuantity(int soLuong) {
        this.soLuong += soLuong;
    }
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart =" + cart.getId() +
                ", Sản phẩm = " + sanPham.getTensanpham() +
                ", Số lượng = " + soLuong +
                ", Đơn vị = " + donViTinh +
                ", Tổng cổng =" +
                '}';
    }
}
