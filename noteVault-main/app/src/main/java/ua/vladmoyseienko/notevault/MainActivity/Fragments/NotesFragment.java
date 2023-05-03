package ua.vladmoyseienko.notevault.MainActivity.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import ua.vladmoyseienko.notevault.APIFunctions.Notes.SelectNotes;
import ua.vladmoyseienko.notevault.LocalDB.Notes.SelectLocalNotes;
import ua.vladmoyseienko.notevault.R;

public class NotesFragment extends Fragment {
    private final String TAG = "NotesFragment";
    private View rootview = null;
    private GridView gridview;
    private ArrayList notes;
    private NotesAdapter adapter;
    public NotesFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SelectLocalNotes selectLocalNotes = new SelectLocalNotes();
        ArrayList resultSelect = selectLocalNotes.selectAllNotes();
        notes = resultSelect;
        rootview = inflater.inflate(R.layout.fragment_notes, container,false);
        adapter = new NotesAdapter(getActivity(), notes);
        gridview =  rootview.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        FloatingActionButton fab = rootview.findViewById(R.id.fab_add_note);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.fab_add_note:
                        Log.d(TAG, "listener fab_add_note");
                        AlertDialog.Builder builder = new AlertDialog.Builder(rootview.getContext());

                        LayoutInflater inflater = getLayoutInflater();
                        View dialogLayout = inflater.inflate(R.layout.alertdialog_add_note, null);
                        builder.setView(dialogLayout);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        ImageView ivGallery = dialog.getWindow().findViewById(R.id.iv_galery);
                        ImageView ivCamera = dialog.getWindow().findViewById(R.id.iv_camera);

                        ivCamera.setOnClickListener(listener);
                        ivGallery.setOnClickListener(listener);

                        break;
                }
            }
        });
        return rootview;
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_galery:
                    Log.d(TAG, "Gallery click");
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), 1);
                    break;
                case R.id.iv_camera:
                    Log.d(TAG, "Camera click");
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imageUri = data.getData();
        String sampleLabel = "sample";
        HashMap<String, String> map = new HashMap<>();
        map.put("content", imageUri.toString());
        map.put("label", sampleLabel);
        notes.add(map);
        Log.d(TAG, "URI, STRING = " + imageUri.toString() + sampleLabel);
        // Далее можно обработать полученный URI
        // Например, вывести его в ImageView
        updateUi();
    }

    public void updateUi(){
        adapter.clear();
        adapter.update(notes);
    }

    public boolean isLocalNotesExists(){
        SelectLocalNotes sn = new SelectLocalNotes();
        ArrayList notes = sn.selectAllNotes();
        if(notes.size() > 0 && notes != null){
            return true;
        }
        else{
            return false;
        }
    }
}
