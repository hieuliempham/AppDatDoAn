package Api.AppDatDoAn.entity;

import Api.AppDatDoAn.entity.compositeKey.ChiTietDonDatHangKey;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ct_ddh")
public class ChiTietDonDatHang {
    @EmbeddedId
    private ChiTietDonDatHangKey id;

    @ManyToOne
    @MapsId("masanpham")
    @JoinColumn(name = "masanpham")
    private SanPham sanpham;

    @ManyToOne
    @MapsId("madondathang")
    @JoinColumn(name = "madondathang")
    private DonDatHang dondathang;

    @Column(name = "soluong")
    private Long soluong;

    @Column(name = "dongiaban")
    private String dongiaban;
}
