package gotenberg.ex.htmltopdf;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gotenberg/html-to-pdf")
public class HtmlToPdfExController {

    private final HtmlToPdfExService htmlToPdfExService;
    public HtmlToPdfExController(HtmlToPdfExService htmlToPdfExService) {
        this.htmlToPdfExService = htmlToPdfExService;
    }

    @GetMapping
    public ResponseEntity<Resource> getHtmlToPdf() throws IOException {
        return
            ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gotenberg_Html_To_Pdf.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(htmlToPdfExService.getHtmlToPdf());
    }
}
