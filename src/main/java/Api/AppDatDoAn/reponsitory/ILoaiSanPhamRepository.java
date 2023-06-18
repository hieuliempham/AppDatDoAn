package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
}
