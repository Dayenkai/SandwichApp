package com.source.sandwich_app.Vue;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.source.sandwich_app.R;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = null;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        context = this;
        if(mediaPlayer==null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.classy_restaurant);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        ImageView img = (ImageView) findViewById(R.id.food_nation);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, Start1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.zoom_in);
            }
        });
        /*MessageReceiver message = new MessageReceiver();
        InstanceIdService service = new InstanceIdService();*/
        /*FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/
       }
}
