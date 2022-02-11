package com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.SingleLiveEvent;

import java.util.List;

public class ProductsListViewModel extends ViewModel {
    private final ProductRepo productRepo;

    public ProductsListViewModel(Application mApplication) {
        productRepo = ProductRepo.getInstance(mApplication.getApplicationContext());
    }

    public SingleLiveEvent<List<ProductInfo>> getProductsFromRepo() {
        return productRepo.getProductsInfo();
    }

    public void getAllProductsFromRepo() {
        productRepo.getAllProductsFromRepo();
    }

    public void deleteProduct(int productId) {
        productRepo.deleteProduct(productId);
    }
}
