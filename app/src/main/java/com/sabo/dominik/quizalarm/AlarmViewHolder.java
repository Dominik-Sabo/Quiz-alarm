package com.sabo.dominik.quizalarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AlarmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SwitchCompat swSwitch;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvTheme;
    private RelativeLayout rvLayout;
    private AlarmClickInterface clickInterface;

    public AlarmViewHolder(@NonNull View itemView, AlarmClickInterface clickInterface) {
        super(itemView);
        swSwitch = itemView.findViewById(R.id.swSwitch);
        tvName = itemView.findViewById(R.id.tvName);
        tvTime = itemView.findViewById(R.id.tvTime);
        tvTheme = itemView.findViewById(R.id.tvTheme);
        rvLayout = itemView.findViewById(R.id.rvLayout);
        this.clickInterface = clickInterface;
        rvLayout.setOnClickListener(this);
        swSwitch.setOnClickListener(this);


    }

    public void setInfo(Alarm data) {
        tvName.setText(data.getName());
        tvTime.setText(formatTimeString(data.getHour(), data.getMinute()));
        tvTheme.setText(data.getQuizTheme());
        swSwitch.setChecked(data.getActive());
    }

    @Override
    public void onClick(View v) {
        if(v == rvLayout) {
            clickInterface.onAlarmClick(getAdapterPosition());
        }
        else clickInterface.onSwitchClick(getAdapterPosition());
    }


    private String formatTimeString(int hour, int minute) {
        String minutes;
        if (minute < 10) {
            minutes = "0" + minute;
        } else minutes = "" + minute;
        return hour + ":" + minutes;
    }
}