package gotenberg.ex.urltopdf;

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
public class UrlToPdfExService {

    private static final String GOTENBERG_URL = "http://localhost:3000/forms/chromium/convert/url";

    public Resource getUrlToPdf(String pdfUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("url", pdfUrl);
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
