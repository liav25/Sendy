package com.liavalter.sendy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class MainActivity extends PreferenceActivity {

    private SharedPreferences sharedPreferences;
    private ListPreference listPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        listPreference = (ListPreference) findPreference(getString(R.string.country_prefixes_key));

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String selectedPrefix = newValue.toString();
                sharedPreferences.edit().putString(getString(R.string.selected_prefix), selectedPrefix).apply();
                return true;
            }
        });

    }



}