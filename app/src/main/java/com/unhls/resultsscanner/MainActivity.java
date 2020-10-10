package com.unhls.resultsscanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marozzi.roundbutton.RoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    private RoundButton resultsScannerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resultsScannerButton=findViewById(R.id.resultsScannerButton);
        resultsScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helpers.scanner(MainActivity.this);
            }
        });

/*        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (data != null) {
                String resultsScanned = data.getStringExtra("SCAN_RESULT");
                   Log.d("SCANNED",resultsScanned);
                try
                {
                    if(URLUtil.isValidUrl(resultsScanned))
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(resultsScanned));
                        startActivity(intent);
                    }else showMessageErrorDialog(MainActivity.this,getString(R.string.invalid_QRCode));


                }catch (Exception e)
                {
                    e.printStackTrace();
                }


            }

        }

    }

    public  void showMessageErrorDialog(Context context, String message)
    {
        MaterialDialog errorDialog = new MaterialDialog.Builder(context)
                .title(R.string.failed)
                .content(message)
                .positiveText(R.string.try_again)
                .show();
    }

}