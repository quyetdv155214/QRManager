package com.example.quyet.qrappmanager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.model.MenuCategory;

import java.util.List;

/**
 * Created by Quyet on 31/10/2017.
 */

public class CategoryRecycleAdapter extends RecyclerView.Adapter<CategoryRecycleAdapter.RecyclerViewHolder> {

    List<MenuCategory> menuCategoryList;
    public CategoryRecycleAdapter(List<MenuCategory> list){
        this.menuCategoryList = list;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tvCateName.setText(menuCategoryList.get(position).getCatename());
        holder.tvNumOfItem.setText(menuCategoryList.get(position).getItemCount() +"");

    }

    @Override
    public int getItemCount() {
        return menuCategoryList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvCateName;
        TextView tvNumOfItem;
        RippleView rlEdit;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvCateName = (TextView) itemView.findViewById(R.id.tvCateName);
            tvNumOfItem = (TextView) itemView.findViewById(R.id.tvNumOfItem);
            rlEdit = (RippleView) itemView.findViewById(R.id.rlEdit);
        }
    }
}
