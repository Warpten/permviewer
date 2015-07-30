package org.warpten.permissionmonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ApplicationListAdapter extends ArrayAdapter<ApplicationInfo> {

    public ApplicationListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ApplicationListAdapter(Context context, List<ApplicationInfo> items) {
        super(context, android.R.layout.simple_list_item_1, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(android.R.layout.simple_list_item_1, null);
        }

        ApplicationInfo p = getItem(position);
        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(android.R.id.text1);
            if (tt1 != null)
                tt1.setText(p.Name);
        }

        return v;
    }

}
