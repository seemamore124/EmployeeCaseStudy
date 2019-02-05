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
    private TextView userRatingTextView;
    private ImageView thumbnailImageView;
    private ItemClickListener itemClickListener;

    public EmployeeViewHolder(View view, ItemClickListener itemClickListener) {
        super(view);
        this.titleTextView = view.findViewById(R.id.title);
        this.userRatingTextView = view.findViewById(R.id.userrating);
        this.thumbnailImageView = view.findViewById(R.id.thumbnail);
        this.itemClickListener = itemClickListener;
        view.setOnClickListener(this);

    }

    public void bindTo(Employee employee) {
        this.employee = employee;
        titleTextView.setText(employee.getName());
      /*  userRatingTextView.setText(String.format("%1$,.2f", movie.getAge()));
        if(movie.getProfileImage() != null) {
            String poster = SMALL_IMAGE_URL_PREFIX + movie.getProfileImage();
            Picasso.get().load(poster).into(thumbnailImageView);
        }*/
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.OnItemClick(employee); // call the onClick in the OnItemClickListener
        }
    }

}
