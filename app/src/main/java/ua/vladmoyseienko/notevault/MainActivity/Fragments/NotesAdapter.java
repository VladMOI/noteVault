package ua.vladmoyseienko.notevault.MainActivity.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import ua.vladmoyseienko.notevault.R;

public class NotesAdapter extends BaseAdapter {
    private Context context;
    int[] count;

    public NotesAdapter(Context context, int[] count){
        this.context = context;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.gridview_notes_item, parent, false);
        }
        for(int i = 0; i < count.length; i ++){
            TextView tvLabel = view.findViewById(R.id.gridview_label);
            TextView tvContent = view.findViewById(R.id.gridview_content);

            tvLabel.setText("Label");
            tvContent.setText("Some content. Lorem ipsum dias buenos");
        }
        return view;    }
}
