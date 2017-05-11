package kpi.ipt.organizer.mail.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import kpi.ipt.organizer.mail.model.MailType;
import kpi.ipt.organizer.mail.service.MailBodyRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

@Component
public class FreemarkerMailBodyRenderer implements MailBodyRenderer {

    private Configuration configuration;

    @Autowired
    public void init(@Value("${freemarker.templatesPath}") String templatesPath) {
        configuration = new Configuration(Configuration.VERSION_2_3_26);

        configuration.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), templatesPath);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.US);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    @Override
    public void render(MailType mailType, Map<String, Object> parameters, Writer out) {
        try {
            Template template = configuration.getTemplate(mailType.getTemplateName() + ".ftl");
            template.process(parameters, out);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
