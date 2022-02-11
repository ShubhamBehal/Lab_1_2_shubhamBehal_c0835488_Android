package com.example.lab_1_2_shubhambehal_c0835488_android.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.lab_1_2_shubhambehal_c0835488_android.R;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;
import com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel.AddEditProductViewModel;
import com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel.AddEditProductViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AddEditProductFragment extends Fragment {

    private AddEditProductViewModel viewModel;
    private Button btnSave;
    private TextInputEditText tietName;
    private TextInputEditText tietDesc;
    private TextInputEditText tietPrice;
    private TextInputEditText tietLat;
    private TextInputEditText tieLong;
    private int productId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_edit_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this,
                new AddEditProductViewModelFactory(requireActivity().getApplication()))
                .get(AddEditProductViewModel.class);
        getArgumentData();
        initViews(view);
        if (productId != 0) {
            viewModel.getProductDataFromRepo(productId);
        }
        setUpObservers();
    }

    private void getArgumentData() {
        if (getArguments() != null) {
            productId = getArguments().getInt("product_id", 0);
        }
    }

    private void setUpObservers() {
        viewModel.getProductInfo().observe(getViewLifecycleOwner(), this::setDataToUI);
        btnSave.setOnClickListener(view -> onSaveClicked());
    }

    private void setDataToUI(ProductInfo productInfo) {
        tietName.setText(productInfo.productName);
        tietDesc.setText(productInfo.productDescription);
        tietPrice.setText(String.valueOf(productInfo.productPrice));
        tietLat.setText(String.valueOf(productInfo.latitude));
        tieLong.setText(String.valueOf(productInfo.longitude));
    }

    private void onSaveClicked() {
        if (isInputValid()) {
            ProductInfo productInfo = new ProductInfo(
                    Objects.requireNonNull(tietName.getText()).toString().trim(),
                    Objects.requireNonNull(tietDesc.getText()).toString().trim(),
                    Integer.parseInt(Objects.requireNonNull(tietPrice.getText()).toString().trim()),
                    Integer.parseInt(Objects.requireNonNull(tietLat.getText()).toString().trim()),
                    Integer.parseInt(Objects.requireNonNull(tieLong.getText()).toString().trim()));
            viewModel.insertProductToRepo(productInfo, productId);
            Toast.makeText(requireActivity(), getString(R.string.saved_success),
                    Toast.LENGTH_SHORT).show();
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigateUp();
        }
    }

    private void initViews(View view) {
        btnSave = view.findViewById(R.id.btn_save_details);
        tietName = view.findViewById(R.id.tiet_name);
        tietDesc = view.findViewById(R.id.tiet_desc);
        tietPrice = view.findViewById(R.id.tiet_price);
        tietLat = view.findViewById(R.id.tiet_latitude);
        tieLong = view.findViewById(R.id.tiet_longitude);
    }

    private boolean isInputValid() {
        if (TextUtils.isEmpty(Objects.requireNonNull(tietName.getText()).toString())) {
            Toast.makeText(requireActivity(), getString(R.string.empty_name_error),
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(tietDesc.getText()).toString())) {
            Toast.makeText(requireActivity(), getString(R.string.empty_description_error),
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(tietPrice.getText()).toString())) {
            Toast.makeText(requireActivity(), getString(R.string.empty_price_error),
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(tietLat.getText()).toString())) {
            Toast.makeText(requireActivity(), getString(R.string.empty_lat_error),
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(tieLong.getText()).toString())) {
            Toast.makeText(requireActivity(), getString(R.string.empty_long_error),
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        return true;
    }
}