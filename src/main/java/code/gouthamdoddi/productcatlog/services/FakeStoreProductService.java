package code.gouthamdoddi.productcatlog.services;

import code.gouthamdoddi.productcatlog.dtos.GenericProductDto;
import code.gouthamdoddi.productcatlog.exception.NotFoundException;
import code.gouthamdoddi.productcatlog.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import code.gouthamdoddi.productcatlog.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Primary
@Service("FakeStoreProductService")
public class  FakeStoreProductService implements ProductService{

    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {

        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<GenericProductDto> answer = new ArrayList<>();

        List<FakeStoreProductDto> allProducts = fakeStoreProductServiceClient.getAllProducts();

        for (FakeStoreProductDto fakeStoreProductDto: allProducts) {

            answer.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }
        return answer;

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto deleteProduct (Long id) throws NotFoundException {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProduct(id));
    }

    @Override
    public GenericProductDto updateProduct(Long id) throws NotFoundException {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.updateProduct(id));
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
