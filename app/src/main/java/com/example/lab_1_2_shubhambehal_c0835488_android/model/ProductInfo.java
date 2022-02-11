package com.example.lab_1_2_shubhambehal_c0835488_android.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "products",
        indices = {@Index(value = {"productName"}, unique = true)})
public class ProductInfo {
    @PrimaryKey(autoGenerate = true)
    public int productId;
    public String productName;
    public String productDescription;
    public int productPrice;
    public int latitude;
    public int longitude;

    public ProductInfo(String productName, String productDescription, int productPrice, int latitude, int longitude) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
