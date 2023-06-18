package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.services.AccountService;
import Api.AppDatDoAn.services.CuaHangService;
import Api.AppDatDoAn.services.IImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/cua-hang")
public class CuaHangController {
    @Autowired
    private CuaHangService cuaHangService;
    @Autowired
    private AccountService accountService;
    @Autowired
    IImageService iImageService;

    @GetMapping
    public String cuaHangAdmin(Model model, Principal principal) {
        Account account = accountService.getAccountByUsername(principal.getName());
        String[] roles = accountService.getRolesOfAccount(account.getAccountId());

        if (Arrays.asList(roles).contains("ADMIN")) {
            List<CuaHang> cuaHangs = cuaHangService.getAllCuaHang();
            model.addAttribute("cuaHangs", cuaHangs);
        } else {
            CuaHang cuaHangs = cuaHangService.getCuaHangById(account.getMacuahang());
            model.addAttribute("cuaHangs", cuaHangs);
        }

        return "cuahang/cua-hang";
    }

    @GetMapping("/them-cua-hang")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String themCuaHang(Model model) {
        model.addAttribute("newCuaHang", new CuaHang());
        return "cuahang/them-cua-hang";
    }
    @PostMapping("/them-cua-hang")
    public String themCuaHang(@Valid @ModelAttribute("newCuaHang") CuaHang cuaHang,
                              @RequestParam(name = "file") MultipartFile file,
                              BindingResult result, Model model) {
        if (result.hasErrors())
        {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors)
            {
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "cuahang/them-cua-hang";
        }

        try {
            String folderName = "Food_store";
            String fileName = iImageService.save(file, folderName);
            String imageUrl = iImageService.getImageUrl(folderName, fileName);
            cuaHang.setHinhanh(imageUrl);

            System.out.println("Upload file " + fileName + " successfully.");
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }

        cuaHangService.saveCuaHang(cuaHang);
        return "redirect:/cua-hang";
    }

    @GetMapping("/sua-cua-hang/{macuahang}")
    public String suaCuaHang(@PathVariable("macuahang")String macuahang, Model model) {
        CuaHang cuaHang = cuaHangService.getCuaHangById(macuahang);
        model.addAttribute("editCuaHang", cuaHang);
        return "cuahang/sua-cua-hang";
    }
    @PostMapping("/sua-cua-hang")
    public String suaCuaHang(@Valid @ModelAttribute("editCuaHang") CuaHang cuaHang,
                             @RequestParam(name = "file") MultipartFile file) {
        CuaHang currentCuaHang = cuaHangService.getCuaHangById(cuaHang.getMacuahang());
        boolean isImageUpdate = file != null && !file.isEmpty();

        if (isImageUpdate) {
            try {
                String folderName = "Food_store";
                String fileName = iImageService.save(file, folderName);
                String imageUrl = iImageService.getImageUrl(folderName, fileName);
                cuaHang.setHinhanh(imageUrl);

                System.out.println("Upload file " + fileName + " successfully.");
            } catch (Exception ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        } else {
            cuaHang.setHinhanh(currentCuaHang.getHinhanh());
        }

        cuaHangService.updateCuaHang(cuaHang);
        return "redirect:/cua-hang";
    }

    @GetMapping("/xoa-cua-hang/{macuahang}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String xoaCuaHang(@PathVariable("macuahang")String maCuaHang) {
        cuaHangService.removeCuaHang(maCuaHang);
        return "redirect:/cua-hang";
    }
}
