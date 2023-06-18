package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.ChiTietDonDatHang;
import Api.AppDatDoAn.entity.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDonDatHangRepository extends JpaRepository<DonDatHang, UUID> {
    @Query(value = "SELECT DISTINCT ddh.* FROM dondathang ddh " +
            "JOIN ct_ddh ctdh ON ctdh.madondathang = ddh.madondathang " +
            "JOIN sanpham sp ON sp.masanpham = ctdh.masanpham " +
            "JOIN cuahang ch ON ch.macuahang = sp.cuahang_id " +
            "WHERE ch.macuahang = ?1", nativeQuery = true)
    List<DonDatHang> findAllDDHByMaCH(String macuahang);

}
