package code.gouthamdoddi.productcatlog.services;

import code.gouthamdoddi.productcatlog.dtos.GenericProductDto;
import code.gouthamdoddi.productcatlog.exception.NotFoundException;

import java.lang.reflect.Array;
import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto deleteProduct(Long id) throws NotFoundException;

    GenericProductDto updateProduct(Long id  ) throws NotFoundException;

}
