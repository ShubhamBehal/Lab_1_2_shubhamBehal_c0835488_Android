package com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductDetailViewModelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;


    public ProductDetailViewModelFactory(Application application) {
        mApplication = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductDetailViewModel(mApplication);
    }
}