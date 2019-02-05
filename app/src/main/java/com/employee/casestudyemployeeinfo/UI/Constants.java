package com.employee.casestudyemployeeinfo.UI;

import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class Constants {
    // network
    public static final String MOVIES_ARRAY_DATA_TAG = "results";
    public static final Type ARRAY_LIST_CLASS_TYPE = (new ArrayList<Employee>()).getClass();
    public static final String Employess_BASE_URL = "http://dummy.restapiexample.com";
    private static final String IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/";
    public static final String SMALL_IMAGE_URL_PREFIX = IMAGE_URL_PREFIX + "w300";
    public static final String BIG_IMAGE_URL_PREFIX = IMAGE_URL_PREFIX + "w500";
    public static final String API_KEY_REQUEST_PARAM = "api_key";
    public static final String LANGUAGE_REQUEST_PARAM = "language";
    public static final String PAGE_REQUEST_PARAM = "page";
    public static final String API_KEY = "04f2f288263683f12131ae2ae1d348c6";
    public static final String LANGUAGE = "en";
    public static final int LOADING_PAGE_SIZE = 20;
    // DB
    public static final String DATA_BASE_NAME = "TMBb.db";
    public static final int NUMBERS_OF_THREADS = 3;
}
