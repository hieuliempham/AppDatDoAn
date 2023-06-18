package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.services.SendMailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SendMailController {
    @Autowired
    private SendMailService sendMailService;

    @GetMapping("/support")
    public String supportForm() {
        return "/support";
    }

    @PostMapping("/support")
    public String sendMailSupport(@RequestParam("email") String email,
                                  @RequestParam("name") String name,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("tencuahang") String tencuahang,
                                  @RequestParam("message") String noidung)
            throws MessagingException, UnsupportedEncodingException {

        sendMailService.sendMail(email, name, phone, tencuahang, noidung);
        return "/success";
    }
}
