package ua.vladmoyseienko.notevault.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ua.vladmoyseienko.notevault.MainActivity.Fragments.NotesFragment;
import ua.vladmoyseienko.notevault.MainActivity.Fragments.ProfileFragment;
import ua.vladmoyseienko.notevault.MainActivity.Fragments.SettingsFragment;
import ua.vladmoyseienko.notevault.R;

public class MainActivity extends AppCompatActivity {
    private Fragment NotesFragment;
    private Fragment ProfileFragment;
    private Fragment SettingsFragment;
    private Fragment ActiveFragment;

    private BottomNavigationView bottomNavigationView;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        NotesFragment = new NotesFragment();
        ProfileFragment = new ProfileFragment();
        SettingsFragment = new SettingsFragment();
        ActiveFragment = NotesFragment;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, NotesFragment)
                .add(R.id.fragment_container, ProfileFragment)
                .add(R.id.fragment_container, SettingsFragment)
                .hide(ProfileFragment)
                .hide(SettingsFragment)
                .commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_notes:
                        Log.d(TAG, "Notes");
                        getSupportFragmentManager().beginTransaction().hide(ActiveFragment).show(NotesFragment).commit();
                        ActiveFragment = NotesFragment;
                        return true;
                    case R.id.menu_profile:
                        Log.d(TAG, "Profile");
                        getSupportFragmentManager().beginTransaction().hide(ActiveFragment).show(ProfileFragment).commit();
                        ActiveFragment = ProfileFragment;
                        return true;
                    case R.id.menu_settings:
                        Log.d(TAG, "Settings");
                        getSupportFragmentManager().beginTransaction().hide(ActiveFragment).show(SettingsFragment).commit();
                        ActiveFragment = SettingsFragment;
                        return true;
                }
                return false;
            }
        });

    }
}