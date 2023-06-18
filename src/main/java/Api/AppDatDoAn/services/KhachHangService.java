package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.KhachHang;
import Api.AppDatDoAn.reponsitory.IKhachHangRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangService {
    @Autowired
    private IKhachHangRepository khachHangReponsitory;

    public KhachHang getKhachHangById(UUID id) {
        return khachHangReponsitory.findById(id).orElse(null);
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangReponsitory.findAll();
    }

    public KhachHang saveKhachHang(KhachHang khachhang) {
        return khachHangReponsitory.save(khachhang);
    }

    public void updateKhachHang(UUID id, KhachHang khachhang) {
        KhachHang eKhachhang = khachHangReponsitory.findById(id).orElse(null);

        eKhachhang.setTenkhachhang(khachhang.getTenkhachhang());
        eKhachhang.setSodienthoai(khachhang.getSodienthoai());
        eKhachhang.setDiachi(khachhang.getDiachi());

        khachHangReponsitory.save(eKhachhang);
    }

    public void removeKhachHang(UUID id) {
        khachHangReponsitory.deleteById(id);
    }
}
