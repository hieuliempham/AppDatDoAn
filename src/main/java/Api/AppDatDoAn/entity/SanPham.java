package Api.AppDatDoAn.entity;

import Api.AppDatDoAn.validator.annotation.ValidMaLoaiSP;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @Column(name = "masanpham", length = 10, unique = true)
    private String masanpham;

    @NotBlank(message = "Tên sản phẩm không được phép bỏ trống")
    @Column(name = "tensanpham", length = 64)
    @Size(max = 64, message = "Chuỗi không được quá 64 ký tự")
    private String tensanpham;

    @Column(name = "mota", length = 144)
    private String mota;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "luotmua")
    private Long luotmua;

    @Column(name = "tinhtrang", length = 255)
    private String tinhtrang;

    @Column(name = "ghichu", length = 255)
    private String ghichu;

    @Column(name = "hinhanh", length = 1000)
    private String hinhanh;

    @Column(name = "phanloai", length = 100)
    private String phanloai;

    @ManyToOne
    @JoinColumn(name = "loaisanpham_id", nullable = false)
    @ValidMaLoaiSP
    private LoaiSanPham loaisanpham;

    @ManyToOne
    @JoinColumn(name = "cuahang_id", nullable = false)
    private CuaHang cuahang;

    @OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
    private Set<ChiTietDonDatHang> chiTietDonDatHangs;
}
