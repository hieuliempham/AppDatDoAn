package Api.AppDatDoAn.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class SendMailService {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String recipientEmail, String name, String phone, String tencuahang, String noidung)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String subject = "BOX SUPPORT";
        String content = "<html>\n" +
                "<head>\n" +
                "  <style>\n" +
                "    /* CSS */\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      line-height: 1.6;\n" +
                "      background-color: #f6f6f6;\n" +
                "    }\n" +
                "    \n" +
                "    .container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 20px;\n" +
                "      background-color: #fffff;\n" +
                "    }\n" +
                "    \n" +
                "    h1 {\n" +
                "      color: #FA6400;\n" +
                "    }\n" +
                "    \n" +
                "    p {\n" +
                "      color: black;\n" +
                "      margin: 14px;\n" +
                "      font-size: 16px;\n" +
                "    }\n" +
                "    \n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <h1>Đăng ký cửa hàng</h1>\n" +
                "    <h2>Xin chào " + name +",</h2>\n" +
                "    \n" +
                "    <h3>Bạn đã gửi email với nội dung:\n" + noidung + "</h3>\n" +
                "    \n" +
                "    <p>Tên cửa hàng: " + tencuahang + "</p>\n" +
                "    \n" +
                "    <p>Số điện thoại: " + phone + "</p>\n" +
                "    \n" +
                "    <hr style=\"background-color: blue; margin: 16px 0px;\">\n" +
                "    <p>DT: 098xxxxxxx - Email: box@gmail.com</p>\n" +
                "  </div>\n" +
                "  \n" +
                "</body>\n" +
                "</html>";

        helper.setSubject(subject);
        helper.setText(content, true);

        helper.setFrom(fromEmail);
        helper.setTo(recipientEmail);

        javaMailSender.send(message);
    }
}
