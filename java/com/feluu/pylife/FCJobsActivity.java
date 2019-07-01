package com.feluu.pylife;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.feluu.pylife.adapters.JobsAdapter;
import com.feluu.pylife.models.ViewModel;
import com.feluu.pylife.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class FCJobsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        ImageView leaveActivity;
        ImageView bgMain;
        TextView title;
        ViewPager viewPager;
        JobsAdapter adapter;
        List<ViewModel> models;

        leaveActivity = findViewById(R.id.exitActivity);
        bgMain = findViewById(R.id.bgmain);
        title = findViewById(R.id.textView1);
        viewPager = findViewById(R.id.viewPager);

        title.setText(R.string.fc_jobs_activity);

        leaveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        models = new ArrayList<>();

        if (sharedPref.loadNightModeState()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusColor));
            bgMain.setImageResource(R.drawable.bg_dark);
            models.add(new ViewModel(R.drawable.ic_build_light, intToString(R.string.mech_fc_title), intToString(R.string.mech_fc_earnings), intToString(R.string.mech_fc_locations), intToString(R.string.mech_fc_description)));
            models.add(new ViewModel(R.drawable.ic_build_light, intToString(R.string.mech_ba_title), intToString(R.string.mech_ba_earnings), intToString(R.string.mech_ba_locations), intToString(R.string.mech_ba_description)));
        } else {
            models.add(new ViewModel(R.drawable.ic_build, intToString(R.string.mech_fc_title), intToString(R.string.mech_fc_earnings), intToString(R.string.mech_fc_locations), intToString(R.string.mech_fc_description)));
            models.add(new ViewModel(R.drawable.ic_build, intToString(R.string.mech_ba_title), intToString(R.string.mech_ba_earnings), intToString(R.string.mech_ba_locations), intToString(R.string.mech_ba_description)));
        }

        adapter = new JobsAdapter(models, this);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);
    }

    public String intToString(int Res) {
        return getResources().getString(Res);
    }
}
