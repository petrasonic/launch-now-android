package spaceappsottawa.launchnow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import spaceappsottawa.launchnow.R;
import spaceappsottawa.launchnow.models.RocketLaunchListViewItem;

public class GeneralRocketLaunchDataListViewAdapter extends BaseAdapter {

    private static final String TAG = "GeneralRocketLaunchDataListViewAdapter";

    private Context context;
    private ArrayList<RocketLaunchListViewItem> rocketLaunchItems;

    public GeneralRocketLaunchDataListViewAdapter(Context context, ArrayList<RocketLaunchListViewItem> rocketLaunchItems) {
        this.context = context;
        this.rocketLaunchItems = rocketLaunchItems;
    }

    @Override
    public int getCount() {
        return rocketLaunchItems.size();
    }

    @Override
    public RocketLaunchListViewItem getItem(int i) {
        return rocketLaunchItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        RocketLaunchListViewItem item = getItem(i);

        if (view == null) {
            viewHolder = new ViewHolder(item.getName());
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_general_rocket_info_row, viewGroup, false);
            viewHolder.name_textView = ((TextView) view.findViewById(R.id.launch_name_textView));
            viewHolder.company_textView = ((TextView) view.findViewById(R.id.lsp_textView));
            viewHolder.location_textView = ((TextView) view.findViewById(R.id.country_textView));
            viewHolder.date_textView = ((TextView) view.findViewById(R.id.date_textView));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            if (!viewHolder.name.equals(item.getName())) {
                viewHolder = new ViewHolder(item.getName());
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_general_rocket_info_row, viewGroup, false);
                viewHolder.name_textView = ((TextView) view.findViewById(R.id.launch_name_textView));
                viewHolder.company_textView = ((TextView) view.findViewById(R.id.lsp_textView));
                viewHolder.location_textView = ((TextView) view.findViewById(R.id.country_textView));
                viewHolder.date_textView = ((TextView) view.findViewById(R.id.date_textView));
                view.setTag(viewHolder);
            }
        }

        viewHolder.name_textView.setText(item.getName());
        viewHolder.company_textView.setText(item.getCompany());
        viewHolder.location_textView.setText(item.getLocation());
        viewHolder.date_textView.setText(item.getDate());

        return view;
    }

    public class ViewHolder {
        protected String name;

        public ViewHolder(String name) {
            this.name = name;
        }

        protected TextView name_textView;
        protected TextView company_textView;
        protected TextView location_textView;
        protected TextView date_textView;
    }

}