package com.employee.casestudyemployeeinfo.UI.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.employee.casestudyemployeeinfo.R;


public class EmployeesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragmentsContainer) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            EmployeeListFragment moviesListFragment = new EmployeeListFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            moviesListFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragmentsContainer' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentsContainer, moviesListFragment).commit();
        }
    }
}

