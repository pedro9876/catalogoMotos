package yamaha.catalogomotos.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import yamaha.catalogomotos.R;
import yamaha.catalogomotos.model.Moto;

public class LVAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Moto> items;
    private ViewHolder viewHolder;
    private TextView tvAux = null;

    public LVAdapter(Activity activity, ArrayList<Moto> data) {
        this.context = activity;
        this.items = data;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Moto getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = view;
        convertView = LayoutInflater.from(context).inflate(R.layout.item_moto, viewGroup, false);
        viewHolder = new ViewHolder(convertView);

        Moto moto = getItem(i);

        viewHolder.name.setTextColor(Color.WHITE);
        viewHolder.name.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL|Gravity.CENTER_HORIZONTAL);

        viewHolder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        Typeface typeface = ResourcesCompat.getFont(context,R.font.nobellight);
        viewHolder.name.setTypeface(typeface);

        viewHolder.name.setText(moto.getName());

        return convertView;
    }


    private class ViewHolder {
        TextView name;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.tvName);

        }
    }

}