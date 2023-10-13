package code.gouthamdoddi.productcatlog.services;
import code.gouthamdoddi.productcatlog.dtos.GenericProductDto;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;

@Service("SelfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService  {

    public GenericProductDto getProductById(Long id) {

        return null;
    }
    public List<GenericProductDto> getAllProducts(){
        return null;
    }

    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    public  GenericProductDto updateProduct(Long id) {
        return null;
    }
}
