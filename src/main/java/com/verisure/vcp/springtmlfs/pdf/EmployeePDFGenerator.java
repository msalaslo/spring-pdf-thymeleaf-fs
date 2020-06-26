package com.verisure.vcp.springtmlfs.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.verisure.vcp.springtmlfs.domain.entity.Employee;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a JUnit test which will generate a PDF using Flying Saucer
 * and Thymeleaf templates. The PDF will display a letter styled with
 * CSS. The letter has two pages and will contain text and images.
 * <p>
 * Simply run this test to generate the PDF. The file is called:
 * <p>
 * /test.pdf
 */
@Slf4j
@Component
public class EmployeePDFGenerator {

    private static final String UTF_8 = "UTF-8";
    
    @Autowired
    private TemplateEngine templateEngine;
    
    public byte[] generatePdf(String templateName, Employee employee) throws Exception {

        Context context = new Context();
        context.setVariable("data", employee);
//        context.setLocale(Locale.ENGLISH);

        // Flying Saucer needs XHTML - not just normal HTML. To make our life
        // easy, we use JTidy to convert the rendered Thymeleaf template to
        // XHTML.
        String renderedHtmlContent = templateEngine.process(templateName, context);
        String xHtml = convertToXhtml(renderedHtmlContent);

        ITextRenderer renderer = new ITextRenderer();
//        renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);
        renderer.getFontResolver().addFont("templates/Code39.ttf", true);

        // FlyingSaucer has a working directory
        String baseUrl = FileSystems
                                .getDefault()
                                .getPath("src", "main", "resources", "templates")
                                .toUri()
                                .toURL()
                                .toString();
        LOGGER.debug("FlyingSaucer working directory:" + baseUrl );
        
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        // And finally, we create the PDF:
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        renderer.createPDF(baos);
        baos.close();
        LOGGER.debug("PDF generated.");
        return baos.toByteArray();
    }

    private static String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

}