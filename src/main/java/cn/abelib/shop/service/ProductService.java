package cn.abelib.shop.service;


import cn.abelib.shop.common.result.Response;
import cn.abelib.shop.pojo.Product;
import cn.abelib.shop.vo.ProductDetailVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


/**
 * Created by abel on 2017/11/9.
 */
@Service
public interface ProductService {

    Response<PageInfo> listProduct(Integer pageNum, Integer pageSize);

    Integer deleteProduct(Integer id);

    Response<ProductDetailVo> getProductDetailPortal(Integer productId);

    Response<String> saveOrUpdateProduct(Product product);

    Response<String> setSalesStatus(Integer productId, Integer status);

    Response<ProductDetailVo> getProductDetail(Integer productId);

    Response<PageInfo> productSearch(String productName, Integer productId, Integer pageNum, Integer pageSize);

    Response<PageInfo> getProductByKeywordOrCategory(String keyword, Integer categoryId, Integer pageNum, Integer pageSize, String orderBy);
}