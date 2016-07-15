package com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mbiganzoli.android.homeweathermonitor.HomeWeatherMonitorApp;
import com.mbiganzoli.android.homeweathermonitor.R;
import com.mbiganzoli.android.homeweathermonitor.entities.InfoRecord;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.HomeWeatherMainPresenter;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.di.HomeWeatherMainComponent;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.adapters.InfoRecordsAdapter;
import com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.adapters.OnItemClickListener;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeWeatherMainActivity extends AppCompatActivity implements HomeWeatherMainView, OnItemClickListener {

    private int CURRENT_YEAR;
    private int CURRENT_MONTH;
    private int CURRENT_DAY;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.main_content)
    CoordinatorLayout content;
    @Bind(R.id.change_date_btn)
    FloatingActionButton changeDateBtn;

    private InfoRecordsAdapter adapter;
    private HomeWeatherMainPresenter presenter;
    private HomeWeatherMainComponent component;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeweather_main);

        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        initDate();
        presenter.onCreate();
        getRecords();
    }

    private void getRecords() {
        presenter.getRecordsByDay(String.format("%04d", CURRENT_YEAR), String.format("%02d", CURRENT_MONTH + 1), String.format("%02d", CURRENT_DAY));
    }

    private void initDate() {
        Calendar c = Calendar.getInstance();
        CURRENT_YEAR = c.get(Calendar.YEAR);
        CURRENT_MONTH = c.get(Calendar.MONTH);
        CURRENT_DAY = c.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        HomeWeatherMonitorApp app = (HomeWeatherMonitorApp) getApplication();
        component = app.getHomeWeatherMainComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    public InfoRecordsAdapter getAdapter() {
        return component.getAdapter();
    }

    public HomeWeatherMainPresenter getPresenter() {
        return component.getPresenter();
    }

    private void setupToolbar() {


    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void setInfoRecords(List<InfoRecord> data) {
        adapter.setRecords(data);
    }

    @Override
    public void onGetInfoRecordsError(String error) {
        String msgError = String.format(getString(R.string.main_get_records_error), error);
        Snackbar.make(content, msgError, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(InfoRecord record) {

        Toast toast = Toast.makeText(this,
                String.format(getString(R.string.main_toast_msg),
                        String.valueOf(record.getTemperature())), Toast.LENGTH_SHORT);
        toast.show();

    }

    @OnClick(R.id.change_date_btn)
    public void onClick() {
        showCalendar();
    }

    private void showCalendar() {


        DatePickerDialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        CURRENT_YEAR = year;
                        CURRENT_MONTH = monthOfYear;
                        CURRENT_DAY = dayOfMonth;

                        getRecords();
                        
                    }
                }, CURRENT_YEAR, CURRENT_MONTH, CURRENT_DAY);
        dialog.show();
    }
}
