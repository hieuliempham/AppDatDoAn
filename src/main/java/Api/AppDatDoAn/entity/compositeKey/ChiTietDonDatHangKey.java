package Api.AppDatDoAn.entity.compositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ChiTietDonDatHangKey implements Serializable {
    @Column(name = "masanpham")
    private String masanpham;

    @Column(name = "madondathang")
    private UUID madondathang;
}
