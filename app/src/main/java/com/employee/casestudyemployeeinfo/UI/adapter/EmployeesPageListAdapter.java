package com.employee.casestudyemployeeinfo.UI.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.employee.casestudyemployeeinfo.R;
import com.employee.casestudyemployeeinfo.UI.listeners.ItemClickListener;
import com.employee.casestudyemployeeinfo.UI.view.viewholder.EmployeeViewHolder;
import com.employee.casestudyemployeeinfo.UI.view.viewholder.NetworkStateItemViewHolder;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.employee.casestudyemployeeinfo.repository.storge.model.NetworkState;


/**
 * Created by Elad on 6/25/2018.
 */

public class EmployeesPageListAdapter extends PagedListAdapter<Employee, RecyclerView.ViewHolder> {

    private NetworkState networkState;
    private ItemClickListener itemClickListener;

    public EmployeesPageListAdapter(ItemClickListener itemClickListener) {
        super(Employee.DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.employee_item) {
            View view = layoutInflater.inflate(R.layout.employee_item, parent, false);
            EmployeeViewHolder viewHolder = new EmployeeViewHolder(view, itemClickListener);;
            return viewHolder;
        } else if (viewType == R.layout.network_state_item) {
            View view = layoutInflater.inflate(R.layout.network_state_item, parent, false);
            return new NetworkStateItemViewHolder(view);
        } else {
            throw new IllegalArgumentException("unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.employee_item:
                ((EmployeeViewHolder) holder).bindTo(getItem(position));
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) holder).bindView(networkState);
                break;
        }
    }

    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.employee_item;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }
}
