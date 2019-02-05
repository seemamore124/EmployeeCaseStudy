package com.employee.casestudyemployeeinfo.repository.network.api;

import android.util.Log;

import com.employee.casestudyemployeeinfo.UI.Constants;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Elad on 6/25/2018.
 */

class EmployeesJsonDeserializer implements JsonDeserializer {

    private static String TAG = EmployeesJsonDeserializer.class.getSimpleName();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Employee> employees = null;
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray employeesJsonArray = jsonObject.getAsJsonArray(Constants.MOVIES_ARRAY_DATA_TAG);
            employees = new ArrayList<>(employeesJsonArray.size());
            for (int i = 0; i < employeesJsonArray.size(); i++) {
                // adding the converted wrapper to our container
                Employee dematerialized = context.deserialize(employeesJsonArray.get(i), Employee.class);
                employees.add(dematerialized);
            }
        } catch (JsonParseException e) {
            Log.e(TAG, String.format("Could not deserialize Movie element: %s", json.toString()));
        }
        return employees;
    }
}