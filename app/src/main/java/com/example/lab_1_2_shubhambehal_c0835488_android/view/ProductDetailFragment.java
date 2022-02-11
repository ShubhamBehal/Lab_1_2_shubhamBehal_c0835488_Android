package com.example.lab_1_2_shubhambehal_c0835488_android.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.lab_1_2_shubhambehal_c0835488_android.R;
import com.example.lab_1_2_shubhambehal_c0835488_android.SharedPrefsUtil;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;
import com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel.ProductDetailViewModel;
import com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel.ProductDetailViewModelFactory;

import java.util.List;

public class ProductDetailFragment extends Fragment {
    private TextView tvTotalProductCount;
    private TextView tvProductId;
    private TextView tvProductName;
    private TextView tvProductDesc;
    private TextView tvProductPrice;
    private TextView tvProductProviderLocation;
    private ProductDetailViewModel viewModel;
    private Group detailsGroup;
    private Button btnSeeAllProducts;
    private Button btnAddProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this,
                new ProductDetailViewModelFactory(requireActivity().getApplication()))
                .get(ProductDetailViewModel.class);
        initViews(view);
        if (!SharedPrefsUtil.getInstance().isAlreadyLaunched(requireActivity())) {
            viewModel.populateDB();
        }else{
            viewModel.getAllProductsFromRepo();
        }
        setUpObservers();
    }

    private void setUpObservers() {
        btnAddProduct.setOnClickListener(view -> navigateToAddProductScreen());
        btnSeeAllProducts.setOnClickListener(view -> navigateToAllProductsScreen());
        viewModel.getProductsFromRepo().observe(getViewLifecycleOwner(), this::setDataToViews);
    }

    private void navigateToAddProductScreen() {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_productDetailFragment_to_addEditProductFragment);
    }

    private void navigateToAllProductsScreen() {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_productDetailFragment_to_productsListFragment);
    }

    @SuppressLint("DefaultLocale")
    private void setDataToViews(List<ProductInfo> productInfos) {
        if (!productInfos.isEmpty()) {
            tvTotalProductCount.setText(String.format(getString(R.string.total_products),
                    productInfos.size()));
            setProductDescription(productInfos.get(0));
            detailsGroup.setVisibility(View.VISIBLE);
        } else {
            tvTotalProductCount.setText(String.format(getString(R.string.total_products),
                    0));
            detailsGroup.setVisibility(View.GONE);
        }
    }

    private void setProductDescription(ProductInfo productInfo) {
        tvProductId.setText(String.valueOf(productInfo.productId));
        tvProductName.setText(productInfo.productName);
        tvProductDesc.setText(productInfo.productDescription);
        tvProductPrice.setText(String.format(getString(R.string.dollar_sign),
                productInfo.productPrice));
        tvProductProviderLocation.setText(String.format(getString(R.string.lat_long),
                productInfo.latitude, productInfo.longitude));
    }

    private void initViews(View view) {
        tvTotalProductCount = view.findViewById(R.id.tv_total_products);
        tvProductId = view.findViewById(R.id.tv_product_id_value);
        tvProductName = view.findViewById(R.id.tv_product_name_value);
        tvProductDesc = view.findViewById(R.id.tv_product_desc_value);
        tvProductPrice = view.findViewById(R.id.tv_product_price_value);
        tvProductProviderLocation = view.findViewById(R.id.tv_product_location_value);
        detailsGroup = view.findViewById(R.id.grp_details);
        btnSeeAllProducts = view.findViewById(R.id.btn_see_all_products);
        btnAddProduct = view.findViewById(R.id.btn_add_a_product);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPrefsUtil.getInstance().setAlreadyLaunched(requireActivity(), true);
    }
}