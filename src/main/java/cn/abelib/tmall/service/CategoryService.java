package cn.abelib.tmall.service;

import cn.abelib.tmall.bean.Category;
import cn.abelib.tmall.util.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abel on 2017/11/4.
 */
@Service
public interface CategoryService {
    /**
     * ͳ��Category������
     * @return
     */
    Integer countCategory();
    /**
     * �г����е�Category
     * @return
     */
    List<Category> listAllCategory(Page page);

    /**
     * ����Category, ���һ���id
     * @param category
     * @return Ӱ�����ݿ�����
     */
    Integer insertCategory(Category category);

    /**
     * ɾ�� ָ��id �� Category
     * @param id
     * @return
     */
    Integer deleteCategory(Integer id);

    /**
     *  �޸� Category
     * @param category
     * @return
     */
    Integer updateCategory(Category category);

    /**
     * ͨ�� id ��ȡ
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);
}
