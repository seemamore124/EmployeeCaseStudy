package com.employee.casestudyemployeeinfo.UI.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.employee.casestudyemployeeinfo.R;
import com.employee.casestudyemployeeinfo.UI.viewmodel.EmployeesDetailsViewModel;
import com.employee.casestudyemployeeinfo.databinding.FragmentDetailsBinding;
import com.employee.casestudyemployeeinfo.databinding.FragmentDetailsBindingImpl;
import com.squareup.picasso.Picasso;

import static com.employee.casestudyemployeeinfo.UI.Constants.BIG_IMAGE_URL_PREFIX;


public class EmployeeDetailsFragment extends Fragment {

    private EmployeesDetailsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(EmployeesDetailsViewModel.class);
        View view = binding.getRoot();
        viewModel.getEmployee().observe(this, binding::setEmployee);
        return view;
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        if(url != null) {
            Picasso.get().load(BIG_IMAGE_URL_PREFIX + url).into(view);
        }
    }
}
