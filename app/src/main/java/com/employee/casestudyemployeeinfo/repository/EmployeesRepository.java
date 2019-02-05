package com.employee.casestudyemployeeinfo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.paging.PagedList;
import android.content.Context;
import android.util.Log;


import com.employee.casestudyemployeeinfo.repository.network.EmployeesNetwork;
import com.employee.casestudyemployeeinfo.repository.network.paging.NetEmployeesDataSourceFactory;
import com.employee.casestudyemployeeinfo.repository.storge.EmployeesDatabase;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.employee.casestudyemployeeinfo.repository.storge.model.NetworkState;

import rx.schedulers.Schedulers;

public class EmployeesRepository {
    private static final String TAG = EmployeesRepository.class.getSimpleName();
    private static EmployeesRepository instance;
    final private EmployeesNetwork network;
    final private EmployeesDatabase database;
    final private MediatorLiveData liveDataMerger;

    private EmployeesRepository(Context context) {

        NetEmployeesDataSourceFactory dataSourceFactory = new NetEmployeesDataSourceFactory();

        network = new EmployeesNetwork(dataSourceFactory, boundaryCallback);
        database = EmployeesDatabase.getInstance(context.getApplicationContext());
        // when we get new movies from net we set them into the database
        liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(network.getPagedEmployees(), value -> {
            liveDataMerger.setValue(value);
            Log.d(TAG, value.toString());
        });

        // save the movies into db
        dataSourceFactory.getEmployees().
                observeOn(Schedulers.io()).
                subscribe(employee -> {
                    database.employeesDao().insertEmployees(employee);
                });

    }

    private PagedList.BoundaryCallback<Employee> boundaryCallback = new PagedList.BoundaryCallback<Employee>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            liveDataMerger.addSource(database.getEmployees(), value -> {
                liveDataMerger.setValue(value);
                liveDataMerger.removeSource(database.getEmployees());
            });
        }
    };
    public static EmployeesRepository getInstance(Context context){
        if(instance == null){
            instance = new EmployeesRepository(context);
        }
        return instance;
    }

    public LiveData<PagedList<Employee>> getEmployees(){
        return  liveDataMerger;
    }

    public LiveData<NetworkState> getNetworkState() {
        return network.getNetworkState();
    }
}
