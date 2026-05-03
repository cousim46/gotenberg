package gotenberg.ex.markdowntopdf;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gotenberg/markdown-to-pdf")
public class MarkdownToPdfExController {

    private final MarkdownToPdfExService markdownToPdfExService;
    public MarkdownToPdfExController(MarkdownToPdfExService markdownToPdfExService) {
        this.markdownToPdfExService = markdownToPdfExService;
    }

    @GetMapping
    public ResponseEntity<Resource> getMarkdwonToPdf() throws IOException {
        return
            ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gotenberg_Markdown_To_Pdf.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(markdownToPdfExService.getMarkdownToPdf());
    }
}
