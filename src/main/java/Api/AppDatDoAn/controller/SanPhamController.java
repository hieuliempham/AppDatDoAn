package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private IImageService iImageService;

    @GetMapping
    public String sanPham(Model model, Principal principal) {
        Account account = accountService.getAccountByUsername(principal.getName());
        String[] roles = accountService.getRolesOfAccount(account.getAccountId());
        List<SanPham> sanPhams;
        if (Arrays.asList(roles).contains("ADMIN")) {
            sanPhams = sanPhamService.getAllSanPham();
            model.addAttribute("sanPhams", sanPhams);
        } else {
            sanPhams = sanPhamService.getAllSanPhamByMaCuaHang(account.getMacuahang());
            model.addAttribute("sanPhams", sanPhams);
        }
        return "sanpham/san-pham";
    }

    @GetMapping("/them-san-pham")
    public String themSanPham(Model model, Principal principal) {
        Account account = accountService.getAccountByUsername(principal.getName());
        String macuahang = account.getMacuahang();

        model.addAttribute("newSanPham", new SanPham());
        model.addAttribute("macuahang", macuahang);
        model.addAttribute("loaiSanPhams", loaiSanPhamService.getAll());
        return "sanpham/them-san-pham";
    }
    @PostMapping("/them-san-pham")
    public String themSanPham(@Valid @ModelAttribute("newSanPham") SanPham sanPham,
                              @RequestParam(name = "file")MultipartFile file,
                              BindingResult result, Model model, Principal principal) {
        Account account = accountService.getAccountByUsername(principal.getName());
        if (result.hasErrors())
        {
            model.addAttribute("macuahang", account.getMacuahang());
            model.addAttribute("loaiSanPhams", loaiSanPhamService.getAll());
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors)
            {
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "sanpham/them-san-pham";
        }

        try {
            String folderName = "Product_image";
            String fileName = iImageService.save(file, folderName);
            String imageUrl = iImageService.getImageUrl(folderName, fileName);
            sanPham.setHinhanh(imageUrl);

            System.out.println("Upload file " + fileName + " successfully.");
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        sanPhamService.saveSanPham(sanPham);
        return "redirect:/san-pham";
    }

    @GetMapping("/sua-san-pham/{masanpham}")
    public String suaSanPham(@PathVariable("masanpham")String maSanPham,
                             Model model, Principal principal) {
        SanPham sanPham = sanPhamService.getSanPhamById(maSanPham);
        Account account = accountService.getAccountByUsername(principal.getName());

        model.addAttribute("editSanPham", sanPham);
        model.addAttribute("loaiSanPhams", loaiSanPhamService.getAll());
        model.addAttribute("macuahang", account.getMacuahang());
        return "sanpham/sua-san-pham";
    }
    @PostMapping("/sua-san-pham")
    public String suaSanPham(@Valid @ModelAttribute("editSanPham")SanPham sanPham,
                             @RequestParam(name = "file")MultipartFile file,
                             Model model, Principal principal) {
        SanPham currentSanPham = sanPhamService.getSanPhamById(sanPham.getMasanpham());
        Account account = accountService.getAccountByUsername(principal.getName());

        boolean isImageUpdate = file != null && !file.isEmpty();

        model.addAttribute("loaiSanPhams", loaiSanPhamService.getAll());
        model.addAttribute("macuahang", account.getMacuahang());
        if (isImageUpdate) {
            try {
                String folderName = "Product_image";
                String fileName = iImageService.save(file, folderName);
                String imageUrl = iImageService.getImageUrl(folderName, fileName);
                sanPham.setHinhanh(imageUrl);

                System.out.println("Upload file " + fileName + " successfully.");
            } catch (Exception ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        } else {
            sanPham.setHinhanh(currentSanPham.getHinhanh());
        }

        sanPhamService.updateSanPham(sanPham);
        return "redirect:/san-pham";
    }

    @GetMapping("/xoa-san-pham/{masanpham}")
    public String xoaSanPham(@PathVariable("masanpham")String masanPham) {
        sanPhamService.removeSanPham(masanPham);
        return "redirect:/san-pham";
    }
}
