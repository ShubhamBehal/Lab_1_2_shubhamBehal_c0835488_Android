package com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel;

import android.content.Context;

import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductDB;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.SingleLiveEvent;

import java.util.List;

public class ProductRepo {

    private static ProductRepo instance;
    private final ProductDB db;
    private final SingleLiveEvent<List<ProductInfo>> productsInfo = new SingleLiveEvent<>();
    private final SingleLiveEvent<ProductInfo> productInfo = new SingleLiveEvent<>();

    public ProductRepo(ProductDB db) {
        this.db = db;
    }

    public static ProductRepo getInstance(Context context) {
        if (instance == null) {
            instance = new ProductRepo(
                    ProductDB.getInstance(context));
        }
        return instance;
    }

    public void populateDB(List<ProductInfo> products) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                db.productDao().insertProducts(products);

                productsInfo.postValue(db.productDao().getAllProducts());
            }
        };
        thread.start();
    }

    public SingleLiveEvent<List<ProductInfo>> getProductsInfo() {
        return productsInfo;
    }

    public void getAllProductsFromRepo() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                productsInfo.postValue(db.productDao().getAllProducts());
            }
        };
        thread.start();
    }

    public void insertProductToRepo(ProductInfo product, int id) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                if (id == 0) {
                    db.productDao().insertProduct(product);
                } else {
                    db.productDao().updateProduct(product.productName, product.productDescription,
                            product.productPrice, product.latitude, product.longitude,
                            id);
                }
                productsInfo.postValue(db.productDao().getAllProducts());
            }
        };
        thread.start();
    }

    public void deleteProduct(int productId) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                db.productDao().deleteProduct(productId);

                productsInfo.postValue(db.productDao().getAllProducts());
            }
        };
        thread.start();
    }

    public void getProductDataFromRepo(int productId) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                productInfo.postValue(db.productDao().getProductFromProductId(productId));
            }
        };
        thread.start();
    }

    public SingleLiveEvent<ProductInfo> getProductInfo() {
        return productInfo;
    }
}
