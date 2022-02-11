package com.example.lab_1_2_shubhambehal_c0835488_android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1_2_shubhambehal_c0835488_android.R;
import com.example.lab_1_2_shubhambehal_c0835488_android.interfaces.ProductEditDeleteListener;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;
import com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel.ProductsListViewModel;
import com.example.lab_1_2_shubhambehal_c0835488_android.viewmodel.ProductsListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductsListFragment extends Fragment implements ProductEditDeleteListener {
    private RecyclerView rvProductsList;
    private ProductsListViewModel viewModel;
    private ProductListAdapter adapter;
    private SearchView svSearch;
    private List<ProductInfo> productInfos;
    private String searchQuery = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this,
                new ProductsListViewModelFactory(requireActivity().getApplication()))
                .get(ProductsListViewModel.class);
        initViews(view);
        setAdapter();
        viewModel.getAllProductsFromRepo();
        setUpObservers();
    }

    private void setAdapter() {
        if (productInfos == null) {
            productInfos = new ArrayList<>();
        }
        adapter = new ProductListAdapter(new ArrayList<>(), this);
        rvProductsList.setLayoutManager(new LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL, false));
        rvProductsList.setAdapter(adapter);
    }


    private void setUpObservers() {
        viewModel.getProductsFromRepo().observe(getViewLifecycleOwner(), this::refreshList);
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchQuery = s;
                adapter.setData(productInfos, s);
                return false;
            }
        });
    }

    private void refreshList(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
        adapter.setData(productInfos, searchQuery);
    }

    private void initViews(View view) {
        rvProductsList = view.findViewById(R.id.rv_products_list);
        svSearch = view.findViewById(R.id.sv_search);
    }

    @Override
    public void deleteProduct(int productId) {
        viewModel.deleteProduct(productId);
        adapter.setData(productInfos, searchQuery);
    }

    @Override
    public void editProduct(int productId) {
        Bundle bundle = new Bundle();
        bundle.putInt("product_id", productId);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_productsListFragment_to_addEditProductFragment, bundle);
    }

    @Override
    public void openMap(double latitude, double longitude) {
        Bundle bundle = new Bundle();
        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_productsListFragment_to_mapFragment, bundle);
    }
}