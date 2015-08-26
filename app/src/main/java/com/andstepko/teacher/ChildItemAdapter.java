package com.andstepko.teacher;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andstepko.teacher.Logic.Child;
import com.andstepko.teacher.Logic.ChildItem;
import com.andstepko.teacher.Logic.ChildRecord;
import com.andstepko.teacher.Logic.ChildSortType;

import java.util.ArrayList;

/**
 * Created by andrew on 26.07.15.
 */
public class ChildItemAdapter extends BaseAdapter{

    private ArrayList<ChildItem> list;
    private ChildSortType childSortType;
    private Activity activity;
    private boolean flag;

    public ChildItemAdapter(ArrayList<Child> list, ChildSortType childSortType, Activity activity){
        this.list = new ArrayList<ChildItem>();
        for(Child child : list){
            this.list.add(new ChildItem(child));
        }
        this.childSortType = childSortType;
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
            view = inflater.inflate(R.layout.child_list_item, viewGroup,false);
        }

        TextView chapterName = (TextView)view.findViewById(R.id.childItemTextView1);
        TextView chapterDesc = (TextView)view.findViewById(R.id.childItemTextView2);

        Child child = list.get(i).getChild();

        chapterName.setText(child.toString(childSortType));
        chapterDesc.setText(child.getRemarks());

        return view;
    }

    public ChildItem getChildItem(int position){
        return list.get(position);
    }
}
