package spaceappsottawa.launchnow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import spaceappsottawa.launchnow.R;
import spaceappsottawa.launchnow.models.Launch;

public class GeneralRocketLaunchDataListViewAdapter extends BaseAdapter {

    private static final String TAG = "GeneralRocketLaunchDataListViewAdapter";

    private Context context;
    private ArrayList<Launch> rocketLaunchItems;

    public GeneralRocketLaunchDataListViewAdapter(Context context, ArrayList<Launch> rocketLaunchItems) {
        this.context = context;
        this.rocketLaunchItems = rocketLaunchItems;
    }

    @Override
    public int getCount() {
        return rocketLaunchItems.size();
    }

    @Override
    public Launch getItem(int i) {
        return rocketLaunchItems.get(i);
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

}