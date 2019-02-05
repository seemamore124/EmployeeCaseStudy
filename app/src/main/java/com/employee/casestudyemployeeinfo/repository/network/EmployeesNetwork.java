package com.employee.casestudyemployeeinfo.repository.network;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.employee.casestudyemployeeinfo.repository.network.paging.NetEmployeesDataSourceFactory;

import com.employee.casestudyemployeeinfo.repository.network.paging.NetEmployeesPageKeyedDataSource;

import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.employee.casestudyemployeeinfo.repository.storge.model.NetworkState;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.employee.casestudyemployeeinfo.UI.Constants.LOADING_PAGE_SIZE;
import static com.employee.casestudyemployeeinfo.UI.Constants.NUMBERS_OF_THREADS;


/**
 * Created by Elad on 6/25/2018.
 */

public class EmployeesNetwork {

    final private static String TAG = EmployeesNetwork.class.getSimpleName();
    final private LiveData<PagedList<Employee>> employeesPaged;
    final private LiveData<NetworkState> networkState;

    public EmployeesNetwork(NetEmployeesDataSourceFactory dataSourceFactory, PagedList.BoundaryCallback<Employee> boundaryCallback){
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(LOADING_PAGE_SIZE).setPageSize(LOADING_PAGE_SIZE).build();
        networkState = Transformations.switchMap(dataSourceFactory.getNetworkStatus(),
                (Function<NetEmployeesPageKeyedDataSource, LiveData<NetworkState>>)
                        NetEmployeesPageKeyedDataSource::getNetworkState);
        Executor executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        employeesPaged = livePagedListBuilder.
                setFetchExecutor(executor).
                setBoundaryCallback(boundaryCallback).
                build();

    }


    public LiveData<PagedList<Employee>> getPagedEmployees(){
        return employeesPaged;
    }



    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

}
