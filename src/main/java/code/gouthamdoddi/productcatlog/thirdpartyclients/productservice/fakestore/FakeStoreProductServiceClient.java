package code.gouthamdoddi.productcatlog.thirdpartyclients.productservice.fakestore;

import code.gouthamdoddi.productcatlog.dtos.GenericProductDto;
import code.gouthamdoddi.productcatlog.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductServiceClient{

    private RestTemplateBuilder restTemplateBuilder;

    private String requestUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakesore.products}") String url) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.requestUrl = url;
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        String url = requestUrl + "/" + id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(url, FakeStoreProductDto.class, id);


        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with "+ id + " is not found");
        }

        return fakeStoreProductDto;

    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity <FakeStoreProductDto[]> response =
                restTemplate.getForEntity(requestUrl, FakeStoreProductDto[].class);

        List<FakeStoreProductDto> answer = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: Arrays.stream(Objects.requireNonNull(response.getBody())).toList()) {

            answer.add(fakeStoreProductDto);
        }

        return answer;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.postForEntity(requestUrl, product, FakeStoreProductDto.class);

        return response.getBody();
    }

    public FakeStoreProductDto deleteProduct (Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        String url = "https://fakestoreapi.com/products/{id}";

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);

        if (response.getBody() == null) {
            throw new NotFoundException("Product with "+ id + " is not found");
        }
        return response.getBody();
    }

    public FakeStoreProductDto updateProduct(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        String url = "https://fakestoreapi.com/products/{id}";


        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(url, HttpMethod.PUT, null, FakeStoreProductDto.class, id);

        if (response.getBody() == null) {
            throw new NotFoundException("Product with "+ id + " is not found");
        }
        return response.getBody();
    }

    private GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }
}
