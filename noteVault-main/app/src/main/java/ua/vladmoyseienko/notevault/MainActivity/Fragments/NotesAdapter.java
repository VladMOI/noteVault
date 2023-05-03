package ua.vladmoyseienko.notevault.MainActivity.Fragments;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import ua.vladmoyseienko.notevault.R;

public class NotesAdapter extends BaseAdapter {
    private Context context;
    ArrayList notes;

    public NotesAdapter(Context context, ArrayList notes){
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
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
        for (int i = 0; i < notes.size(); i++){
            HashMap<String, String> currentNote = new HashMap<>();
            currentNote = (HashMap<String, String>) notes.get(i);
            String label = currentNote.get("label");
            Uri contentUri = Uri.parse(currentNote.get("content"));
            System.out.println("Label: " + label + " uri: " + contentUri);
            TextView tvLabel = view.findViewById(R.id.gridview_label);
            tvLabel.setText(label);
            ImageView ivContent = view.findViewById(R.id.gridview_content);
            ivContent.setImageURI(contentUri);

        }
        return view;
    }
    public void clear(){
        notes.clear();
    }
    public void update(ArrayList notes){
        this.notes = notes;
    }
}
