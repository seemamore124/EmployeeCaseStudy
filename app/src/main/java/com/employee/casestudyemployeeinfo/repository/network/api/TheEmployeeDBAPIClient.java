package com.employee.casestudyemployeeinfo.repository.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.employee.casestudyemployeeinfo.UI.Constants.Employess_BASE_URL;
import static com.employee.casestudyemployeeinfo.UI.Constants.ARRAY_LIST_CLASS_TYPE;



public class TheEmployeeDBAPIClient {

    public static EmployeesAPIInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        // create OkHttpClient and register an interceptor
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                // we remove from the response some wrapper tags from our movies array
                .registerTypeAdapter(ARRAY_LIST_CLASS_TYPE, new EmployeesJsonDeserializer())
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(Employess_BASE_URL);

        return builder.build().create(EmployeesAPIInterface.class);
    }
}
