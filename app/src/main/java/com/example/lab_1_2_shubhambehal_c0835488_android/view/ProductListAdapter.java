package com.example.lab_1_2_shubhambehal_c0835488_android.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1_2_shubhambehal_c0835488_android.R;
import com.example.lab_1_2_shubhambehal_c0835488_android.interfaces.ProductEditDeleteListener;
import com.example.lab_1_2_shubhambehal_c0835488_android.model.ProductInfo;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final List<ProductInfo> products;
    private final ProductEditDeleteListener listener;

    public ProductListAdapter(List<ProductInfo> products, ProductEditDeleteListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_list_child, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        ProductInfo product = products.get(position);
        viewHolder.tvName.setText(product.productName);
        viewHolder.tvDesc.setText(product.productDescription);
        viewHolder.tvPrice.setText(String.format(viewHolder.tvPrice.getContext()
                .getString(R.string.price), product.productPrice));
        viewHolder.tvLocation.setText(
                String.format(viewHolder.tvLocation.getContext().getString(R.string.location),
                        product.latitude, product.longitude));

        viewHolder.ivDelete.setOnClickListener(view -> listener.deleteProduct(product.productId));

        viewHolder.ivEdit.setOnClickListener(view -> listener.editProduct(product.productId));
        viewHolder.cvRoot.setOnClickListener(view -> listener
                .openMap(product.latitude, product.longitude));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ProductInfo> products, String searchQuery) {
        this.products.clear();
        for (ProductInfo p : products) {
            if (p.productName.toLowerCase().contains(searchQuery.toLowerCase())) {
                this.products.add(p);
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvDesc;
        private final TextView tvPrice;
        private final TextView tvLocation;
        private final ImageView ivDelete;
        private final ImageView ivEdit;
        private final CardView cvRoot;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_title);
            tvDesc = view.findViewById(R.id.tv_desc);
            tvPrice = view.findViewById(R.id.tv_price);
            tvLocation = view.findViewById(R.id.tv_location);
            ivDelete = view.findViewById(R.id.iv_delete);
            ivEdit = view.findViewById(R.id.iv_edit);
            cvRoot = view.findViewById(R.id.cv_root);
        }
    }
}