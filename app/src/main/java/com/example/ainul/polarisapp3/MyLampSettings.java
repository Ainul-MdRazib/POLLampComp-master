package com.example.ainul.polarisapp3;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MyLampSettings extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";


    public final static String MY_EMOTION_KEY = "MY_EMOTION_KEY";
    public final static String MY_PATTERN_KEY = "MY_PATTERN_KEY";

    private ImageButton BackFromMySettingsButton;
    private Button ChangeMyColourButton;
    private Button ChangeMyPatternButton;
    private Button DoneButton;
    private TextView textSetTitle;

    private SharedPreferences pref;


    String[] listItems;

    View MyColourDisplay;
    TextView MyPatternDisplay;

    int DefaultColour = -1;

    private DatabaseReference refc;
    private DatabaseReference refp;
    private String nodename;
    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lamp_settings);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        pref=getSharedPreferences(Constants.FILEEMOJI,MODE_PRIVATE);
        name=pref.getString(Constants.TAG,"");
        nodename=pref.getString(Constants.FirebaseUser,"John");
        MyColourDisplay = findViewById(R.id.MyColourDisplay);

        refc=FirebaseDatabase.getInstance().getReference(nodename);
        refp=FirebaseDatabase.getInstance().getReference(nodename);

        textSetTitle = findViewById(R.id.myLampSettingsTitle);
        textSetTitle.setText("My Lamp Settings");///////////////////////////////////////////////////////////////////;
        BackFromMySettingsButton = findViewById(R.id.BackFromMySettingsButton);
        BackFromMySettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ChangeMyColourButton = findViewById(R.id.ChangeMyColourButton);
        ChangeMyColourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColourPicker();
            }
        });

        MyPatternDisplay = findViewById(R.id.MyPatternDisplay);

        final String[] listItems = getResources().getStringArray(R.array.patterns_array);

        ChangeMyPatternButton = findViewById(R.id.ChangeMyPatternButton);
        ChangeMyPatternButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MyLampSettings.this);
                mBuilder.setTitle("Which pattern would you like?");

                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MyPatternDisplay.setText(listItems[i]);

                        if("I am happy!".equals(listItems[i])) {
                            refp.setValue("happy");
                        }
                        else if("I am so angry!!!".equals(listItems[i])) {
                            refp.setValue("angry");
                        }
                        else if("Sorry! I am too busy...".equals(listItems[i])) {
                            refp.setValue("busy");
                        }
                        else if("I am feeling down...".equals(listItems[i])) {
                            refp.setValue("cry");
                        }
                        else if("I was awesome today!".equals(listItems[i])) {
                            refp.setValue("party");
                        }
                        else if("I feel sick...".equals(listItems[i])) {
                            refp.setValue("sick");
                        }
                        else if("I am so tired...".equals(listItems[i])) {
                            refp.setValue("sleepy");
                        }
                        else if("Love you!".equals(listItems[i])) {
                            refp.setValue("love");
                        }
                        else if("Default Pattern".equals(listItems[i])) {
                            refp.setValue("default");
                        }
                        else if("Pattern 1".equals(listItems[i])) {
                            refp.setValue("1");
                        }
                        else if("Pattern 2".equals(listItems[i])) {
                            refp.setValue("2");
                        }
                        else if("Pattern 3".equals(listItems[i])) {
                            refp.setValue("3");
                        }


                        //TODO: send PATTERN to firebase
                        dialogInterface.dismiss();


                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

    }
    public void openColourPicker() {
        AmbilWarnaDialog colourPicker = new AmbilWarnaDialog(this, DefaultColour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                DefaultColour = color;
                MyColourDisplay.setBackgroundColor(color);

                //TODO: send colour integer to firebase
                refc.setValue(convertToRGB(color));

            }
        });
        colourPicker.show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(MY_EMOTION_KEY, ((ColorDrawable) MyColourDisplay.getBackground()).getColor());
        preferencesEditor.putString(MY_PATTERN_KEY, MyPatternDisplay.getText().toString());
        preferencesEditor.apply();
    }



    public int convertToRGB(int aw){
        int dec = aw + 16777216;
        return dec;
    }
}
