package com.employee.casestudyemployeeinfo.repository.storge.paging;

import android.arch.paging.DataSource;

import com.employee.casestudyemployeeinfo.repository.storge.EmployeesDao;




public class DBEmployeesDataSourceFactory extends DataSource.Factory {

    private static final String TAG = DBEmployeesDataSourceFactory.class.getSimpleName();
    private DBEmployeesPageKeyedDataSource employeesPageKeyedDataSource;
    public DBEmployeesDataSourceFactory(EmployeesDao dao) {
        employeesPageKeyedDataSource = new DBEmployeesPageKeyedDataSource(dao);
    }

    @Override
    public DataSource create() {
        return employeesPageKeyedDataSource;
    }

}
