package com.employee.casestudyemployeeinfo.repository.network.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;


import rx.subjects.ReplaySubject;

/*
    Responsible for creating the DataSource so we can give it to the PagedList.
 */
public class NetEmployeesDataSourceFactory extends DataSource.Factory {

    private static final String TAG = NetEmployeesDataSourceFactory.class.getSimpleName();
    private MutableLiveData<NetEmployeesPageKeyedDataSource> networkStatus;
    private NetEmployeesPageKeyedDataSource employeesPageKeyedDataSource;
    public NetEmployeesDataSourceFactory() {
        this.networkStatus = new MutableLiveData<>();
        employeesPageKeyedDataSource = new NetEmployeesPageKeyedDataSource();
    }


    @Override
    public DataSource create() {
        networkStatus.postValue(employeesPageKeyedDataSource);
        return employeesPageKeyedDataSource;
    }

    public MutableLiveData<NetEmployeesPageKeyedDataSource> getNetworkStatus() {
        return networkStatus;
    }

    public ReplaySubject<Employee> getEmployees() {
        return employeesPageKeyedDataSource.getEmployees();
    }

}
