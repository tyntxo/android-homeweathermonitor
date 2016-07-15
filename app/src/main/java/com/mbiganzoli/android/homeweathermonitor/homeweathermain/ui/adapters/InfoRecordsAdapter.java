package com.mbiganzoli.android.homeweathermonitor.homeweathermain.ui.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbiganzoli.android.homeweathermonitor.R;
import com.mbiganzoli.android.homeweathermonitor.entities.InfoRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;


public class InfoRecordsAdapter extends RecyclerView.Adapter<InfoRecordsAdapter.ViewHolder> {
    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;
    List<InfoRecord> records;
    OnItemClickListener onItemClickListener;


    public InfoRecordsAdapter(List<InfoRecord> records, OnItemClickListener onItemClickListener) {
        this.records = records;
        this.onItemClickListener = onItemClickListener;
    }

    public void setRecords(List<InfoRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (records.isEmpty()) {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        } else {
            return VIEW_TYPE_OBJECT_VIEW;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = null;

        switch(viewType) {
            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.no_results, parent, false);
                break;
            case VIEW_TYPE_OBJECT_VIEW:
               v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_stored_record, parent, false);
                break;
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (records.isEmpty())
            return;

        InfoRecord currentInfoRecord = records.get(position);

        Context context = holder.view.getContext();

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(currentInfoRecord.getTimestamp());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = sdf.format(cal.getTime());

        holder.txtDateTime.setText(date);

        holder.txtTemperature.setText(String.format(context.getString(R.string.main_temperature_msg_format),
                String.valueOf(currentInfoRecord.getTemperature())));

        holder.txtHumidity.setText(String.format(context.getString(R.string.main_humidity_msg_format),
                String.valueOf(currentInfoRecord.getHumidity())));

        holder.txtPressure.setText(String.format(context.getString(R.string.main_pressure_msg_format),
                String.valueOf(currentInfoRecord.getPressure())));

        holder.setOnItemClickListener(currentInfoRecord, this.onItemClickListener);
    }

    @Override
    public int getItemCount() {

        if (records.isEmpty()) return 1;
        return records.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable @Bind(R.id.txtDateTime)
        TextView txtDateTime;
        @Nullable @Bind(R.id.txtTemperature)
        TextView txtTemperature;
        @Nullable @Bind(R.id.txtHumidity)
        TextView txtHumidity;
        @Nullable @Bind(R.id.txtPressure)
        TextView txtPressure;


        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }


        public void setOnItemClickListener(final InfoRecord record,
                                           final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(record);
                }
            });


        }
    }
}
