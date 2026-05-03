package gotenberg.ex.urltopdf;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gotenberg/url-to-pdf")
public class UrlToPdfExController {

    private final UrlToPdfExService urlToPdfExService;
    public UrlToPdfExController(UrlToPdfExService urlToPdfExService) {
        this.urlToPdfExService = urlToPdfExService;
    }

    @GetMapping
    public ResponseEntity<Resource> getUrlToPdf(String pdfUrl) {
        return
            ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gotenberg_Url_To_Pdf.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(urlToPdfExService.getUrlToPdf(pdfUrl));
    }
}
