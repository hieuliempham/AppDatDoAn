package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "dondathang")
public class DonDatHang {
    @Id
    @GeneratedValue
    private UUID madondathang;

    @Column(name = "ngaydat")
    private LocalDate ngaydat;

    @Column(name = "tinhtrang")
    private String tinhtrang;

    @Column(name = "giohen")
    private LocalTime giohen;

    @ManyToOne
    @JoinColumn(name = "khachhang_id")
    private KhachHang khachhang;

    @OneToMany(mappedBy = "dondathang", cascade = CascadeType.ALL)
    private Set<ChiTietDonDatHang> chiTietDonDatHangs;

    @OneToOne(mappedBy = "donDatHang")
    private HoaDon hoaDon;

    @PrePersist
    public void prePersistNgayDat() {
        this.ngaydat = LocalDate.now();
    }
}
