package ua.vladmoyseienko.notevault.MainActivity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ua.vladmoyseienko.notevault.APIFunctions.Notes.SelectNotes;
import ua.vladmoyseienko.notevault.R;

public class NotesFragment extends Fragment {
    public NotesFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            SelectNotes selectNotes = new SelectNotes();
            selectNotes.execute();
            selectNotes.get();
            String result = selectNotes.getResultSelect();
            System.out.println("All notes: " + result);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        int[] count = {1,2,3,4,5,6,7,8,9,10};
        View rootview = inflater.inflate(R.layout.fragment_notes, container,false);
        NotesAdapter adapter = new NotesAdapter(getActivity(), count);
        GridView gridview =  rootview.findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        return rootview;
    }
}
