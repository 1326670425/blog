package cn.novalue.blog.service.impl;

import cn.novalue.blog.service.MailService;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * 邮件服务类
 *
 * @author Wu Yangjie
 * @date 2020-06-10
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {
    // 发件人，这里读取配置文件中的发件人用户名
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;


    @Override
    @Async
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }

    @Override
    @Async
    public void sendTemplateMail(String to, String subject, Map<String, Object> content, String templateFilename, String attachFilename) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            if (!StringUtils.isEmpty(attachFilename)) {
                helper.addAttachment(attachFilename, new File(attachFilename));
            }
            Template template = freeMarkerConfig.getConfiguration().getTemplate(templateFilename);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, content);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("failed to send mail to: [{}], subject: [{}], content: [{}]", to, subject, content);
        }
    }
}
