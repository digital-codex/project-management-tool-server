package dev.digitalcodex.web.application.service;

import dev.digitalcodex.web.application.ApplicationConstants;
import dev.digitalcodex.web.application.data.NotificationMail;
import dev.digitalcodex.web.application.exception.ProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service(ApplicationConstants.MAIL_SERVICE_BEAN_NAME)
public class MailService {
    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public static final String NOTIFICATION_MAIL_TEXT_MSG_FORMAT = "Thank you for signing up for our Forum Web Application, please click on the below url to activate your account : http://localhost:8080/api/auth/verify/%s";

    @Autowired
    public MailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void send(NotificationMail mail) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom("forum-webapp@email.com");
            helper.setTo(mail.to());
            helper.setSubject(mail.subject());
            helper.setText(this.build(mail.text()));
        };

        try {
            javaMailSender.send(preparator);
            log.info("Verification email sent!!");
        } catch (MailException e) {
            throw new ProcessingException(ProcessingException.MAIL_EXCEPTION_MSG_FORMAT.formatted(mail.to()), e);
        }
    }

    private String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return this.templateEngine.process("mail", context);
    }
}
