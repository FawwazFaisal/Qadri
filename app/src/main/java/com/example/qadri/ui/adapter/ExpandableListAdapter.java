package com.example.qadri.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.qadri.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private final Context _context;
    private final List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private final HashMap<String, List<String>> _listDataChild;
    private final List<Integer> _icon;           //icon List


    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData,
                                 List<Integer> icons) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._icon = icons;

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, parent, false);
        }
        TextView txtListChild = convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        int size = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!Objects.equals(this._listDataChild.get(this._listDataHeader.get(groupPosition)), null)) {
                size = this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
            }
        }
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, parent, false);
        }

        ImageView iconView = convertView.findViewById(R.id.icon);
        iconView.setImageResource(this._icon.get(groupPosition));
        iconView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(_context, R.color.colorAccent)));

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);

        ImageView indicator = convertView.findViewById(R.id.indicator);
        if (_listDataChild.containsKey(_listDataHeader.get(groupPosition))) {
            indicator.setVisibility(View.VISIBLE);
            indicator.setImageDrawable(ContextCompat.getDrawable(_context, R.drawable.ic_right));
            indicator.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(_context, R.color.colorAccent)));
        } else {
            indicator.setVisibility(View.GONE);
        }
//        if (isExpanded) {
//            convertView.findViewById(R.id.indicator).setRotation(90);
//        } else {
//            convertView.findViewById(R.id.indicator).setRotation(0);
//        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
