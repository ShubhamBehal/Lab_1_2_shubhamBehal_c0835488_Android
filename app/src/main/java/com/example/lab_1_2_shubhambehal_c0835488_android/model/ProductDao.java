package com.example.lab_1_2_shubhambehal_c0835488_android.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProducts(List<ProductInfo> products);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(ProductInfo product);

    @Query("SELECT * FROM  products")
    List<ProductInfo> getAllProducts();

    @Query("DELETE FROM  products WHERE productId = :productId")
    void deleteProduct(int productId);

    @Query("SELECT * FROM  products  WHERE productId = :productId")
    ProductInfo getProductFromProductId(int productId);

    @Query("UPDATE products SET productName = :productName, productDescription = :productDescription " +
            ", productPrice= :productPrice, latitude = :latitude, longitude =:longitude " +
            "WHERE productId = :productId")
    void updateProduct(String productName, String productDescription, double productPrice,
                       double latitude, double longitude, int productId);
}
