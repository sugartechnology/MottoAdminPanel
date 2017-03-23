/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsatir.serviceImpl;

import com.fsatir.service.ProductCategoryService;
import com.fsatir.types.Brand;
import com.fsatir.types.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;

@Dependent
@Stateless
public class ProductCategoryServiceImpl extends BaseServiceImpl implements ProductCategoryService {

    @Override
    public void saveCategory(ProductCategory fetvaCategory) throws Exception {

        em = accessEntityManager();
        em.getTransaction().begin();
        em.persist(fetvaCategory);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<ProductCategory> listOfCategory() {
        em = accessEntityManager();
        List<ProductCategory> categoryList = new ArrayList<>();
        em.getTransaction().begin();
        categoryList = em.createQuery("from PhotoCategory").getResultList();
        em.getTransaction().commit();
        em.close();
        return categoryList;
    }
    
    @Override
    public List<ProductCategory> listOfCategoryByBrand(String brandId){

        List<ProductCategory> productCategoryList = new ArrayList<>();
        try {
            em = accessEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("from ProductCategory a where a.brand.id=:param1");
            query.setParameter("param1", brandId);
            productCategoryList = query.getResultList();
            em.getTransaction().commit();
            em.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productCategoryList;
    }

    @Override
    public void deleteCategory(ProductCategory fetvaCategory) throws Exception {
        em = accessEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(fetvaCategory) ? fetvaCategory : em.merge(fetvaCategory));
        em.getTransaction().commit();
        em.close();
    }

}