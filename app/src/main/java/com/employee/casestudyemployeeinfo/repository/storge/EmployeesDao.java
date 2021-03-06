package com.employee.casestudyemployeeinfo.repository.storge;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;

import java.util.List;

/**
 * Data Access Object for the movies table.
 */
@Dao
public interface EmployeesDao {

    /**
     * Get the Movies from the table.
     * -------------------------------
     * Since the DB use as caching, we don't return LiveData.
     * We don't need to get update every time the database update.
     * We using the get query when application start. So, we able to display
     * data fast and in case we don't have connection to work offline.
     * @return the movies from the table
     */
    @Query("SELECT * FROM employees")
    List<Employee> getEmployees();

    /**
     * Insert a movie in the database. If the movie already exists, replace it.
     *
     * @param employee the movie to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEmployees(Employee employee);

    @Query("DELETE FROM employees")
    abstract void deleteAllEmployees();
}
