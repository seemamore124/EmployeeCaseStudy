package com.employee.casestudyemployeeinfo.UI.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;


public class EmployeesDetailsViewModel extends ViewModel {
    final private MutableLiveData employee;

    public EmployeesDetailsViewModel() {
        employee = new MutableLiveData<Employee>();
    }

    public MutableLiveData<Employee> getEmployee() {
        return employee;
    }
}
