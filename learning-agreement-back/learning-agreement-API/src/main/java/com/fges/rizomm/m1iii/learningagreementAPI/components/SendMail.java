package com.fges.rizomm.m1iii.learningagreementAPI.components;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.MailInformation;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.form.FormDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.user.UserDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.exceptions.MailSendingException;
import com.google.common.collect.Sets;
import freemarker.template.Configuration;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Ignore
public class SendMail {

    private final JavaMailSender mailSender;
    private final Configuration freemarkerConfiguration;
    private static final String MAIL_BODY_PASSWORD_FORGOT = "password_forgot.body.ftl";
    private static final String MAIL_SUBJECT_PASSWORD_FORGOT = "password_forgot.subject.ftl";
    private static final String MAIL_BODY_INVITE_FORM = "form_invite.body.ftl";
    private static final String MAIL_SUBJECT_INVITE_FORM = "form_invite.subject.ftl";


    @Value("${smtp_from}")
    private String from;

    @Value("${mail.enabled}")
    private boolean enabled;

    @Value("${client_url}")
    private String client_url;

    public boolean sendEmailForPasswordForgot (final UserDTO user) {
        String resetToken = this.client_url + "/api/user/passwordForgot/" + user.getResetToken();
        return sendMail(new MailInformation(
                Sets.newHashSet(user.getEmail()),
                new MailInformation.Template(
                        MAIL_SUBJECT_PASSWORD_FORGOT,
                        Collections.emptyMap()
                ),
                new MailInformation.Template(
                        MAIL_BODY_PASSWORD_FORGOT,
                        new HashMap<String, Object>() {
                            {
                                put("url", resetToken);
                            }
                        }
                ),
                new Locale("FR")
        ));
    }

    public boolean sendMailForInviteToken (final FormDTO formDTO) {
        String resetToken = this.client_url + "/api/form/invite/" + formDTO.getInviteToken();
        return sendMail(new MailInformation(
                Sets.newHashSet(formDTO.getMailRpiHost()),
                new MailInformation.Template(
                        MAIL_SUBJECT_PASSWORD_FORGOT,
                        Collections.emptyMap()
                ),
                new MailInformation.Template(
                        MAIL_BODY_PASSWORD_FORGOT,
                        new HashMap<String, Object>() {
                            {
                                put("url", resetToken);
                            }
                        }
                ),
                new Locale("FR")
        ));
    }

    @Async("mail")
    public boolean sendMail (final MailInformation mailInformation) {
        freemarkerConfiguration.setClassForTemplateLoading(this.getClass(), "/templates/");
        if (!enabled) {
            log.warn("Mail sending is disabled. Enable it by turning on the application.mail.enabled configuration variable");
            return false;
        }
        try {
            mailSender.send(mimeMessage -> {
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                mimeMessage.setSubject(
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfiguration.getTemplate(
                                        mailInformation.getSubject().getName(),
                                        mailInformation.getLocale(),
                                        "UTF-8"),
                                mailInformation.getSubject().getModel())
                );
                message.setFrom(from);
                message.setTo(mailInformation.getTo().toArray(new String[mailInformation.getTo().size()]));
                message.setText(
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfiguration.getTemplate(
                                        mailInformation.getBody().getName(),
                                        mailInformation.getLocale(),
                                        "UTF-8"),
                                mailInformation.getBody().getModel()),
                        true
                );
            });
            return true;
        } catch (final MailException e) {
            throw new MailSendingException(e.getMessage(), e);
        }
    }

}

