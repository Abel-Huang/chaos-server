package cn.abelib.tmall.service;

import cn.abelib.tmall.bean.Product;
import cn.abelib.tmall.util.Page;

import java.util.List;

/**
 * Created by abel on 2017/11/9.
 */
public interface ProductService {
    /**
     * �г����е�Product
     * @param
     * @return
     */
    List<Product> listAllProduct(Integer categoryId);

    /**
     * ����Product�����һ���id
     * @param product
     * @return
     */
    Integer insertProduct(Product product);

    /**
     * ɾ����Ӧid��Product
     * @param id
     * @return
     */
    Integer deleteProduct(Integer id);

    /**
     *  ����Product
     * @param product
     * @return
     */
    Integer updateProduct(Product product);

    /**
     * ͨ��id��ȡProduct
     * @param id
     * @return
     */
    Product getProductById(Integer id);
}
