package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.ChiTietDonDatHang;
import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.services.AccountService;
import Api.AppDatDoAn.services.ChiTietDonDatHangService;
import Api.AppDatDoAn.services.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/don-dat-hang")
public class DonDatHangController {
    @Autowired
    private DonDatHangService donDatHangService;
    @Autowired
    private ChiTietDonDatHangService chiTietDonDatHangService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String donDatHang(Model model, Principal principal) {
        Account account = accountService.getAccountByUsername(principal.getName());
        String[] roles = accountService.getRolesOfAccount(account.getAccountId());

        List<DonDatHang> donDatHangs;

        if (Arrays.asList(roles).contains("ADMIN")) {
            donDatHangs = donDatHangService.getAllDDH();
            model.addAttribute("donDatHangs", donDatHangs);
        } else {
            donDatHangs = donDatHangService.getAllDDHByMaCH(account.getMacuahang());
            model.addAttribute("donDatHangs", donDatHangs);
        }

        return "dondathang/don-dat-hang";
    }

    @GetMapping("/chi-tiet-don-hang/{id}")
    public String getCTDHang(@PathVariable("id")UUID madonhang, Model model) {
        List<ChiTietDonDatHang> chiTietDonDatHangs = chiTietDonDatHangService.getAllCTDHByMaDH(madonhang);

        model.addAttribute("chiTietDonDatHangs", chiTietDonDatHangs);
        return "dondathang/chi-tiet-don-hang";
    }
}
