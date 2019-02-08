package com.employee.casestudyemployeeinfo.UI.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.employee.casestudyemployeeinfo.R;
import com.employee.casestudyemployeeinfo.UI.adapter.EmployeesPageListAdapter;
import com.employee.casestudyemployeeinfo.UI.listeners.ItemClickListener;
import com.employee.casestudyemployeeinfo.UI.viewmodel.EmployeesDetailsViewModel;
import com.employee.casestudyemployeeinfo.UI.viewmodel.EmployeesListViewModel;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;


public class EmployeeListFragment extends Fragment implements ItemClickListener {

    protected EmployeesListViewModel viewModel;
    private EmployeesDetailsViewModel detailsViewModel;

    protected RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.employeesRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(getActivity()).get(EmployeesListViewModel.class);
        observersRegisters();
        return view;
    }

    private void observersRegisters() {

        final EmployeesPageListAdapter pageListAdapter = new EmployeesPageListAdapter(this);
        viewModel.getEmployees().observe(this, pageListAdapter::submitList);
        viewModel.getNetworkState().observe(this, networkState -> {
            pageListAdapter.setNetworkState(networkState);
        });
        recyclerView.setAdapter(pageListAdapter);
        detailsViewModel = ViewModelProviders.of(getActivity()).get(EmployeesDetailsViewModel.class);
    }

    @Override
    public void OnItemClick(Employee employee) {
        detailsViewModel.getEmployee().postValue(employee);
        if (!detailsViewModel.getEmployee().hasActiveObservers()) {
            // Create fragment and give it an argument specifying the article it should show
            EmployeeDetailsFragment detailsFragment = new EmployeeDetailsFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragmentsContainer, detailsFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        }
    }
}
