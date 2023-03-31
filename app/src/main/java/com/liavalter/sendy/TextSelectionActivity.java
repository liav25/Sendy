package com.liavalter.sendy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class TextSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CharSequence text = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefix = sharedPreferences.getString(getString(R.string.selected_prefix), null);

        if (text != null && prefix != null) {
            Intent outgoingIntent = new Intent();
            openUrl(text, prefix);
        } else {
            Toast.makeText(this, "Text cannot be modified" + prefix, Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void openUrl(CharSequence a, String b) {
        String phoneNumber = (a.charAt(0) == '+') ? (String) a : b + a.subSequence(1, a.length());
        String url = "https://wa.me/" + phoneNumber;
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}

