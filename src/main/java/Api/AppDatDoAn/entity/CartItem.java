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
