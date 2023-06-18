package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.ChiTietDonDatHang;
import Api.AppDatDoAn.entity.compositeKey.ChiTietDonDatHangKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface IChiTietDonDatHangRepository extends JpaRepository<ChiTietDonDatHang, ChiTietDonDatHangKey> {
    @Query("SELECT ctdh FROM ChiTietDonDatHang ctdh WHERE ctdh.dondathang.madondathang = ?1")
    List<ChiTietDonDatHang> findAllCTDHByMaDH(UUID madonhang);
}
