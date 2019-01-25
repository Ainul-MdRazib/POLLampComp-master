package com.example.ainul.polarisapp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserNameSettingActivity extends AppCompatActivity {

    private Button saveUsername;
    private TextView userNameInput;
    private String username;
    private SharedPreferences pref;
    public SharedPreferences setFirebaseNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_setting);

        setFirebaseNode=getSharedPreferences(Constants.FILEEMOJI,MODE_PRIVATE);
        pref=getSharedPreferences(Constants.FILE,MODE_PRIVATE);


        Intent i=getIntent();



        saveUsername=(Button) findViewById(R.id.setUsername);
        userNameInput=(TextView)findViewById(R.id.UsernameInput);

        pref=getSharedPreferences(Constants.FILE,MODE_PRIVATE);
        //username=pref.getString(Constants.TAG,"");



        saveUsername.setOnClickListener(new View.OnClickListener(){
            //String temp;
            public void onClick(View view){
                //temp=userNameInput.getText().toString();
                if(userNameInput.getText().toString()==""){
                    Toast.makeText(UserNameSettingActivity.this,"Please enter a name for your Lamp",Toast.LENGTH_LONG).show();
                }
                username=userNameInput.getText().toString();
                Log.d("TESTNAME",username+"//////////////////////username setting page");
                Toast.makeText(UserNameSettingActivity.this, "Lamp name saved!", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(UserNameSettingActivity.this,AnotherMessageActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor=pref.edit();
        preferencesEditor.putString(Constants.TAG,username);
        preferencesEditor.apply();
        Log.d("DEBUGGING","SENDING USERNAME TO CLOUD");

    }

}

