package com.employee.casestudyemployeeinfo.repository.network.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.employee.casestudyemployeeinfo.repository.network.api.EmployeesAPIInterface;
import com.employee.casestudyemployeeinfo.repository.network.api.TheEmployeeDBAPIClient;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.employee.casestudyemployeeinfo.repository.storge.model.NetworkState;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.ReplaySubject;

import static com.employee.casestudyemployeeinfo.UI.Constants.API_KEY;
import static com.employee.casestudyemployeeinfo.UI.Constants.LANGUAGE;


/**
 * Created by Elad on 6/25/2018.
 *
 * Responsible for loading the data by page
 */

public class NetEmployeesPageKeyedDataSource extends PageKeyedDataSource<String, Employee> {

    private static final String TAG = NetEmployeesPageKeyedDataSource.class.getSimpleName();
    private final EmployeesAPIInterface employeesService;
    private final MutableLiveData networkState;
    private final ReplaySubject<Employee> employeesObservable;

    NetEmployeesPageKeyedDataSource() {
        employeesService = TheEmployeeDBAPIClient.getClient();
        networkState = new MutableLiveData();
        employeesObservable = ReplaySubject.create();
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public ReplaySubject<Employee> getEmployees() {
        return employeesObservable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, Employee> callback) {
        Log.i(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);
        Call<ArrayList<Employee>> callBack = employeesService.getEmployees();
        callBack.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), Integer.toString(1), Integer.toString(2));
                    networkState.postValue(NetworkState.LOADED);
                    Log.i(TAG, "JSON Response " + response+"  employeesObservable "+employeesObservable);

                    if(response != null){
                        if(employeesObservable != null){
                         //   response.body().forEach(employeesObservable::onNext);

                        }
                    }
                } else {
                    Log.e("API CALL", response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<>(), Integer.toString(1), Integer.toString(2));
            }
        });
    }



    @Override
    public void loadAfter(@NonNull LoadParams<String> params, final @NonNull LoadCallback<String, Employee> callback) {
        Log.i(TAG, "Loading page " + params.key );
        networkState.postValue(NetworkState.LOADING);
        final AtomicInteger page = new AtomicInteger(0);
        try {
            page.set(Integer.parseInt(params.key));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Call<ArrayList<Employee>> callBack = employeesService.getEmployees();
        callBack.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(),Integer.toString(page.get()+1));
                    networkState.postValue(NetworkState.LOADED);
                    response.body().forEach(employeesObservable::onNext);
                } else {
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                    Log.e("API CALL", response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<>(),Integer.toString(page.get()));
            }
        });
    }


    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Employee> callback) {

    }
}
