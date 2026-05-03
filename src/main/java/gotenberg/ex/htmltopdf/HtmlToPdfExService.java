package gotenberg.ex.htmltopdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class HtmlToPdfExService {

    private static final String GOTENBERG_URL = "http://localhost:3000/forms/chromium/convert/html";

    public Resource getHtmlToPdf() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ClassPathResource resource =
            new ClassPathResource("templates/gotenberg_html_to_pdf.html");

        File tempFile = File.createTempFile("temp", ".html");
        try (InputStream in = resource.getInputStream();
            OutputStream out = new FileOutputStream(tempFile)) {
            in.transferTo(out);
        }

        body.add("files", new FileSystemResource(tempFile) {
            @Override
            public String getFilename() {
                return "index.html";
            }
        });
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity =
            new HttpEntity<>(body, headers);
        ResponseEntity<Resource> postedForEntity = restTemplate.postForEntity(GOTENBERG_URL,
            requestEntity,
            Resource.class);

        return postedForEntity
            .getBody();
    }
}
