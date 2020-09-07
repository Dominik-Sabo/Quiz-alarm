package com.sabo.dominik.quizalarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<AlarmViewHolder>{

    private AlarmClickInterface clickInterface;
    private List<Alarm> dataList = new ArrayList<>();

    public RecyclerAdapter(AlarmClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view, viewGroup, false);
        return new AlarmViewHolder(view, clickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder alarmViewHolder, int position) {
        alarmViewHolder.setInfo(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Alarm> data)
    {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(Alarm data)
    {
        int position = dataList.size();
        dataList.add(position, data);
        notifyItemInserted(position);
    }

    public void changeItem(Alarm data, int position){
        dataList.set(position, data);
        notifyItemChanged(position);
    }


    public void removeItem(int position)
    {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

}
