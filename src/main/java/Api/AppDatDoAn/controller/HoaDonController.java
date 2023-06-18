package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.dto.HoaDonDto;
import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.HoaDon;
import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.services.AccountService;
import Api.AppDatDoAn.services.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private AccountService accountService;

    public HoaDonDto toDto(HoaDon hoaDon) {
        HoaDonDto hoaDonDto = new HoaDonDto();
        hoaDonDto.setMahoadon(hoaDon.getMahoadon());
        hoaDonDto.setMadondathang(hoaDon.getDonDatHang().getMadondathang());
        hoaDonDto.setTenkhachhang(hoaDon.getDonDatHang().getKhachhang().getTenkhachhang());
        hoaDonDto.setNgaylap(hoaDon.getNgaylap());
        hoaDonDto.setTongtien(hoaDon.getTongtien());
        return hoaDonDto;
    }

    @GetMapping
    public String hoaDon(Model model, Principal principal) {
        Account account = accountService.getAccountByUsername(principal.getName());
        String[] roles = accountService.getRolesOfAccount(account.getAccountId());
        List<HoaDon> hoaDons;

        if (Arrays.asList(roles).contains("ADMIN")) {
            hoaDons = hoaDonService.getAllHoaDon();
            List<HoaDonDto> hoaDonDtos = new ArrayList<>();
            for (HoaDon hoaDon : hoaDons) {
                hoaDonDtos.add(toDto(hoaDon));
            }
            model.addAttribute("hoaDons", hoaDonDtos);
        } else {
            hoaDons = hoaDonService.getAllHoaDonByMaCH(account.getMacuahang());
            List<HoaDonDto> hoaDonDtos = new ArrayList<>();
            for (HoaDon hoaDon : hoaDons) {
                hoaDonDtos.add(toDto(hoaDon));
            }
            model.addAttribute("hoaDons", hoaDonDtos);
        }

        return "hoadon/hoa-don";
    }
}
