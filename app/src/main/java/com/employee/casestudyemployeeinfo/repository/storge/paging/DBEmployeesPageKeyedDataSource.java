package com.employee.casestudyemployeeinfo.repository.storge.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.employee.casestudyemployeeinfo.repository.storge.EmployeesDao;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;

import java.util.List;

/**
 * Created by Elad on 6/25/2018.
 */

public class DBEmployeesPageKeyedDataSource extends PageKeyedDataSource<String, Employee> {

    public static final String TAG = DBEmployeesPageKeyedDataSource.class.getSimpleName();
    private final EmployeesDao employeesDao;
    public DBEmployeesPageKeyedDataSource(EmployeesDao dao) {
        employeesDao = dao;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, Employee> callback) {
        Log.i(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize);
        List<Employee> employees = employeesDao.getEmployees();
        if(employees.size() != 0) {
            callback.onResult(employees, "0", "1");
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, final @NonNull LoadCallback<String, Employee> callback) {
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Employee> callback) {
    }
}
