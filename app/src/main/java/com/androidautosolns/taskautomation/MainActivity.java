package com.androidautosolns.taskautomation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int SMS_PERMISSION_CODE = 1;
    //private int READ_SMS_PERMISSION_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SMS Permission on start
        requestSMSPermission();

    }

    private void requestSMSPermission (){
        //If you want to show why asking for Permission
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("Requires SMS Permission to Schedule Messages")
                    .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick (DialogInterface dialog, int which){
                          ActivityCompat.requestPermissions(MainActivity.this,
                                  new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }
}