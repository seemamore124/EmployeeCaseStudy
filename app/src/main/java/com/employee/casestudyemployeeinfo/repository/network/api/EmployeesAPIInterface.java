package com.employee.casestudyemployeeinfo.repository.network.api;

import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.employee.casestudyemployeeinfo.UI.Constants.API_KEY_REQUEST_PARAM;
import static com.employee.casestudyemployeeinfo.UI.Constants.LANGUAGE_REQUEST_PARAM;
import static com.employee.casestudyemployeeinfo.UI.Constants.PAGE_REQUEST_PARAM;


public interface EmployeesAPIInterface {

    @GET("./api/v1/employees")
    Call<ArrayList<Employee>> getEmployees();
}

