package com.andstepko.teacher;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andstepko.teacher.Logic.ChildRecord;

import java.util.ArrayList;

/**
 * Created by andrew on 25.07.15.
 */
public class ChildRecordAdapter extends BaseAdapter{

    private ArrayList<ChildRecord> list;
    private Activity activity;

    public ChildRecordAdapter(ArrayList<ChildRecord> list, Activity activity){
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if((i < list.size()) && (i >= 0)){
            return list.get(i);
        }
        else{
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.achievement_list_item, viewGroup,false);
        }

        TextView chapterName = (TextView)view.findViewById(R.id.textView1);
        TextView chapterDesc = (TextView)view.findViewById(R.id.textView2);

        ChildRecord childRecord = list.get(i);

        chapterName.setText(childRecord.getChild().toStringNameSurnameRoom());
        chapterDesc.setText(childRecord.getRecord());

        return view;
    }

    public ChildRecord getChildRecord(int position){
        return list.get(position);
    }
}
