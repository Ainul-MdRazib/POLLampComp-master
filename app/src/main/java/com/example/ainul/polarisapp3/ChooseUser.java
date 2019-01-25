package com.example.ainul.polarisapp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseUser extends AppCompatActivity {


    Button jane;
    Button john;
    private int indicator;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        pref=getSharedPreferences(Constants.FILEEMOJI,MODE_PRIVATE);

        Button jane= (Button) findViewById(R.id.userJane);
        john=findViewById(R.id.userJohn);

        jane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int userIndex=1;
                indicator=1;
                Intent i1=new Intent(ChooseUser.this,UserNameSettingActivity.class);
                i1.putExtra(Constants.UserIndex,Constants.a);
                startActivity(i1);
            }
        });

        john.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //int userIndex=2;
                indicator=2;
                Intent i2=new Intent(ChooseUser.this,UserNameSettingActivity.class);
                i2.putExtra(Constants.UserIndex,Constants.b);
                startActivity(i2);
            }
        });
    }

    protected void onPause(){
        String a="";
        super.onPause();
        if(indicator==1){
            a="Jane";
        }else if(indicator==2){
            a="John";
        }

        SharedPreferences.Editor preferencesEditor=pref.edit();
        preferencesEditor.putString(Constants.FirebaseUser,a);
        preferencesEditor.apply();

    }
}
