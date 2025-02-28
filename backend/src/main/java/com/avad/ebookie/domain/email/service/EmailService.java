package com.avad.ebookie.domain.email.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void sendForgotPasswordMail(@NotBlank @Email String email, String randomPassword) {
        String title = "테스트: 임시 비밀번호 안내 이메일입니다.";
        String content = """
             <!DOCTYPE html>
             <html lang="ko">

             <head>
               <meta charset="UTF-8">
               <meta name="viewport" content="width=device-width,initial-scale=1">
               <meta name="x-apple-disable-message-reformatting">
               <title>이메일 템플릿</title>
             </head>

             <body style="margin:0;padding:0;">
               <table role="presentation" style="width:100%%;border-collapse:collapse;border:0;border-spacing:0;background:#ffffff;">
                 <tr>
                   <td align="center" style="padding: 0;">
                     <table role="presentation" style="width:600px;border-collapse:collapse;border-spacing:0; text-align:left;">
                       <tr>
                         <td style="padding:40px 8px 8px 8px; width: 100%%; border-bottom: 1px solid #cccccc;">
                           <table role="presentation" align="center" style="width: 100%%; border-collapse: collapse; border: 0; border-spacing: 0;">
                             <tr>
                               <td align="left" valign="middle" style="vertical-align:middle; width: 50%%;">
                                 <img alt="회사 로고" width="150" style="height: auto; display: block;" src="https://i.imgur.com/fKgNiXH.png">
                               </td>
                             </tr>
                           </table>
                         </td>
                       </tr>
                       <tr>
                         <td style="padding: 30px 15px 15px 15px;">
                           <table role="presentation" style="border: 0; border-collapse: collapse; border-spacing: 0; width: 100%%;">
                             <tr>
                               <td style="padding: 0; color: #333333; background: #ffffff;">
                                 <h1 style="font-size: 18px; margin: 0; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif; margin: 0 0 12px 0;">임시비밀번호 안내</h1>

                                 <p style="margin: 0 0 12px 0; font-size: 16px; line-height: 24px; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif;">
                                   고객님, 저희 서비스를 이용해 주셔서 진심으로 감사드립니다. 아래는 서비스 이용에 필요한 임시비밀번호 입니다. 귀하의 원활한 서비스 이용을 위해 안내해 드리는 내용을 꼭 확인해 주시기 바랍니다.
                                 </p>

                                 <p style="margin: 0 0 12px 0; font-size: 16px; line-height: 16px; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif;">
                                   임시 비밀번호: <b>%s</b>
                                 </p>

                                 <table role="presentation" style="width: 100%%; border-collapse: collapse; border: 0; border-spacing: 0;">
                                   <tr>
                                     <td style="padding: 30px 0 48px 0;" align="center">
                                       <table role="presentation" cellpadding="0" cellmargin="0" border="0" height="44" width="178" style="border-collapse: collapse; border: 5px solid #2568b5;">
                                         <tr>
                                           <td bgcolor="#2568b5" valign="middle" align="center" width="174">
                                             <div style="font-size: 16px; color: #ffffff; line-height: 1; margin: 0; padding: 0; mso-table-lspace:0; mso-table-rspace:0;">
                                               <a href="http://192.168.2.69:8080" style="text-decoration: none; color: #ffffff; border: 0; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif; mso-table-lspace:0; mso-table-rspace:0;" border="0">
                                                 <strong style="text-decoration: none; color: #ffffff;">웹사이트 바로가기</strong>
                                               </a>
                                             </div>
                                           </td>
                                         </tr>
                                       </table>
                                     </td>
                                   </tr>
                                 </table>
                               </td>
                             </tr>
                           </table>
                         </td>
                       </tr>
                       <tr>
                         <td style="padding: 15px; width: 100%%;">
                           <table role="presentation" style="border-collapse: collapse; border: 0; border-spacing: 0; width: 100%%;">
                             <tr>
                               <td style="padding: 0; margin: 0 0 4px 0;">
                                 <img alt="회사 로고" width="100" style="height: auto; display: block; margin: 0 0 8px 0;" src="https://i.imgur.com/fKgNiXH.png">
                               </td>
                             </tr>
                             <tr>
                               <td style="padding: 0 0 4px 0;">
                                 <p style="margin: 0; font-size: 12px; color: #5a5a5a; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif;">©%d eBookie. All rights reserved.</p>
                               </td>
                             </tr>
                             <tr>
                               <td style="padding: 4px 0 0 0; width: 100%%; border-top: 1px solid #cccccc;">
                                 <table role="presentation" style="width: 100%%; border-collapse: collapse; border: 0; border-spacing: 0;">
                                   <tr>
                                     <td style="color: #5a5a5a; width: 100%%;">
                                       <p style="font-size: 12px; margin: 0; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif;">이 이메일은 자동으로 생성된 메시지입니다.</p>
                                       <p style="font-size: 12px; margin: 0; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif;">본 메일은 중요한 정보를 포함하고 있으며 허가 없는 정보 공유나 배포는 불가합니다.</p>
                                       <p style="font-size: 12px; margin: 0; font-family: 'Malgun Gothic', '맑은 고딕', Dotum, 돋움, Arial, sans-serif;">추가 정보가 필요하면 해당 메일로 답변 바랍니다.</p>
                                     </td>
                                   </tr>
                                 </table>
                               </td>
                             </tr>
                           </table>
                         </td>
                       </tr>
                     </table>
                   </td>
                 </tr>
               </table>
             </body>

             </html>
             """.formatted(randomPassword, Year.now().getValue());

        System.out.println("content = " + content);


        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setTo(email);
            helper.setFrom(from);
            helper.setSubject(title);
            helper.setText(content, true);
        };

        mailSender.send(preparator);
    }
}
