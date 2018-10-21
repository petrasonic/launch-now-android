package spaceappsottawa.launchnow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import spaceappsottawa.launchnow.R;
import spaceappsottawa.launchnow.models.Launch;

public class GeneralRocketLaunchDataListViewAdapter extends BaseAdapter implements Filterable {

    private static final String TAG = "GeneralRocketLaunchDataListViewAdapter";

    private Context context;
    private ArrayList<Launch> rocketLaunchItems;
    private ArrayList<Launch> filteredList;
    private LaunchFilter launchFilter;

    public GeneralRocketLaunchDataListViewAdapter(Context context, ArrayList<Launch> rocketLaunchItems) {
        this.context = context;
        this.rocketLaunchItems = rocketLaunchItems;
        filteredList = rocketLaunchItems;

        getFilter();
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Launch getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Launch item = getItem(i);

        if (view == null) {
            viewHolder = new ViewHolder(item.getName());
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_general_rocket_info_row, viewGroup, false);
            viewHolder.launch_name_textView = ((TextView) view.findViewById(R.id.launch_name_textView));
            viewHolder.lsp_textView = ((TextView) view.findViewById(R.id.lsp_textView));
            viewHolder.country_textView = ((TextView) view.findViewById(R.id.country_textView));
            viewHolder.date_textView = ((TextView) view.findViewById(R.id.date_textView));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            if (!viewHolder.name.equals(item.getName())) {
                viewHolder = new ViewHolder(item.getName());
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_general_rocket_info_row, viewGroup, false);
                viewHolder.launch_name_textView = ((TextView) view.findViewById(R.id.launch_name_textView));
                viewHolder.lsp_textView = ((TextView) view.findViewById(R.id.lsp_textView));
                viewHolder.country_textView = ((TextView) view.findViewById(R.id.country_textView));
                viewHolder.date_textView = ((TextView) view.findViewById(R.id.date_textView));
                view.setTag(viewHolder);
            }
        }

        viewHolder.launch_name_textView.setText(item.getName());
        viewHolder.lsp_textView.setText(item.getLaunchServiceProvider().getName());
        viewHolder.country_textView.setText(item.getLocation().getCountryCode());

        String formattedDate = item.getWindowStart();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss zzz", Locale.US);
            Date date = sdf.parse(item.getWindowStart());
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
            formattedDate =  fmtOut.format(date);
        } catch (ParseException e) {
            formattedDate = item.getWindowStart();
        }

        viewHolder.date_textView.setText(formattedDate);

        return view;
    }

    @Override
    public Filter getFilter() {
        if (launchFilter == null) {
            launchFilter = new LaunchFilter();
        }

        return launchFilter;
    }

    public class ViewHolder {
        protected String name;

        public ViewHolder(String name) {
            this.name = name;
        }

        protected TextView launch_name_textView;
        protected TextView lsp_textView;
        protected TextView country_textView;
        protected TextView date_textView;
    }

    /**
     * Custom filter for friend list
     * Filter content in friend list according to the search text
     */
    private class LaunchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Launch> tempList = new ArrayList<Launch>();

                // search content in friend list
                for (Launch launch : rocketLaunchItems) {
                    if (launch.getName() != null && launch.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(launch);
                    }  else if (launch.getLocation() != null && launch.getLocation().getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(launch);
                    } else if (launch.getRocket() != null && launch.getRocket().getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(launch);
                    } else if (launch.getMission() != null && launch.getMission().getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(launch);
                    }  else if (launch.getLaunchServiceProvider().getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(launch);
                    } else if (launch.getLocation().getCountryCode().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(launch);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = rocketLaunchItems.size();
                filterResults.values = rocketLaunchItems;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Launch>) results.values;
            notifyDataSetChanged();
        }
    }

}