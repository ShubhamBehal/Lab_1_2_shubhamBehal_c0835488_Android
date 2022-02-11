package com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.SingleLiveEvent;

public class AddEditProductViewModel extends ViewModel {
    private final ProductRepo productRepo;

    public AddEditProductViewModel(Application mApplication) {
        productRepo = ProductRepo.getInstance(mApplication.getApplicationContext());
    }

    public void insertProductToRepo(ProductInfo productInfo, int id) {
        productRepo.insertProductToRepo(productInfo, id);
    }

    public void getProductDataFromRepo(int productId) {
        productRepo.getProductDataFromRepo(productId);
    }

    public SingleLiveEvent<ProductInfo> getProductInfo() {
        return productRepo.getProductInfo();
    }
}
