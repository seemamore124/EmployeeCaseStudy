package com.employee.casestudyemployeeinfo.repository.storge.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.SerializedName;


/**
 * Immutable model class for a Movie and entity in the Room database.
 */


@Entity(tableName = "employees")
public class Employee extends BaseObservable {

    @PrimaryKey()
    @ColumnInfo(name = "id") @SerializedName(value="id") private Integer mId;
    @ColumnInfo(name = "employee_name") @SerializedName(value="employee_name") private String name;



    // use for ordering the items in view
    public static DiffUtil.ItemCallback<Employee> DIFF_CALLBACK = new DiffUtil.ItemCallback<Employee>() {
        @Override
        public boolean areItemsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    };
    @Bindable
    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }


    public void setName(String name) {
        this.name = name;
    }
    @Bindable
    public String getName() {
        return name;
    }
}
