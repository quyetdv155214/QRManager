package com.example.quyet.qrappmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.quyet.qrappmanager.viewholder.BaseViewHolder;

import java.util.List;

import com.example.quyet.qrappmanager.BR;

/**
 * Created by Khuong Duy on 9/26/2017.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected final LayoutInflater layoutInflater;
    protected List<T> listItem;
    protected Presenter presenter;
    protected Decorator decorator;

    public BaseRecyclerViewAdapter(Context context) {
        //Get system layout inflate
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final Object item = listItem.get(position);
        // set item variable
        holder.getBinding().setVariable(BR.viewModel, item);
        holder.getBinding().setVariable(BR.listener, getPresenter());
        holder.getBinding().executePendingBindings();
        if (decorator != null) {
            decorator.decorate(holder, position, getItemViewType(position));
        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void remove(int position) {
        listItem.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public interface Presenter {
    }

    public interface Decorator {
        void decorate(BaseViewHolder holder, int position, int viewType);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }
}
