package com.employee.casestudyemployeeinfo.repository.storge;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.employee.casestudyemployeeinfo.repository.storge.paging.DBEmployeesDataSourceFactory;



import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.employee.casestudyemployeeinfo.UI.Constants.DATA_BASE_NAME;
import static com.employee.casestudyemployeeinfo.UI.Constants.NUMBERS_OF_THREADS;


/**
 * The Room database that contains the Users table
 */
@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeesDatabase extends RoomDatabase {

    private static EmployeesDatabase instance;
    public abstract EmployeesDao employeesDao();
    private static final Object sLock = new Object();
    private LiveData<PagedList<Employee>> employeesPaged;

    public static EmployeesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        EmployeesDatabase.class, DATA_BASE_NAME)
                        .build();
                instance.init();

            }
            return instance;
        }
    }

    private void init() {
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build();
        Executor executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);
        DBEmployeesDataSourceFactory dataSourceFactory = new DBEmployeesDataSourceFactory(employeesDao());
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        employeesPaged = livePagedListBuilder.setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<Employee>> getEmployees() {
        return employeesPaged;
    }
}
