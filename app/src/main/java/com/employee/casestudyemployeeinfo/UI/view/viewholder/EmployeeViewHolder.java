package com.employee.casestudyemployeeinfo.UI.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.employee.casestudyemployeeinfo.R;
import com.employee.casestudyemployeeinfo.UI.listeners.ItemClickListener;
import com.employee.casestudyemployeeinfo.repository.storge.model.Employee;
import com.squareup.picasso.Picasso;

import static com.employee.casestudyemployeeinfo.UI.Constants.SMALL_IMAGE_URL_PREFIX;


public class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private Employee employee;
    private TextView titleTextView;
    private TextView salary;
    private ImageView profileImageView;
    private ItemClickListener itemClickListener;

    public EmployeeViewHolder(View view, ItemClickListener itemClickListener) {
        super(view);
        this.titleTextView = view.findViewById(R.id.title);
       // this.profileImageView = view.findViewById(R.id.profileImageView);
        this.itemClickListener = itemClickListener;
        view.setOnClickListener(this);

    }

    public void bindTo(Employee employee) {
        this.employee = employee;
        titleTextView.setText(employee.getName());
      /*  if(employee.getProfileImage() != null) {
            String profile = SMALL_IMAGE_URL_PREFIX + employee.getProfileImage();
            Picasso.get().load(profile).into(profileImageView);
        }*/
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.OnItemClick(employee); // call the onClick in the OnItemClickListener
        }
    }

}
