package com.employee.casestudyemployeeinfo.UI.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;


import com.employee.casestudyemployeeinfo.repository.EmployeesRepository;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.employee.casestudyemployeeinfo.repository.storge.model.NetworkState;


public class EmployeesListViewModel extends AndroidViewModel {
    private EmployeesRepository repository;

    public EmployeesListViewModel(@NonNull Application application) {
        super(application);
        repository = EmployeesRepository.getInstance(application);
    }
    public LiveData<PagedList<Employee>> getMovies() {
        return repository.getEmployees();
    }

    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }

}
