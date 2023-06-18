package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.ChiTietDonDatHang;
import Api.AppDatDoAn.reponsitory.IChiTietDonDatHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChiTietDonDatHangService {
    @Autowired
    private IChiTietDonDatHangRepository chiTietDonDatHangReponsitory;

    public List<ChiTietDonDatHang> getAllCTDHByMaDH(UUID madonhang) {
        return chiTietDonDatHangReponsitory.findAllCTDHByMaDH(madonhang);
    }
}
