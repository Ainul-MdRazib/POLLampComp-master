package com.example.ainul.polarisapp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yuku.ambilwarna.AmbilWarnaDialog;

public class OtherColourMenu extends AppCompatActivity {
    Button OtherEmojiFromColourButton;
    Button OtherAudioFromColourButton;

    Button OtherColourSendToButton;
    private DatabaseReference refco;

    int DefaultColour = -1;
    Button OtherColourPicker;
    String clorCode="";
    private SharedPreferences pref;
    String nodename;
    int index;
    String rawNode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_colour_menu);

        pref=getSharedPreferences(Constants.FILEEMOJI,MODE_PRIVATE);
        rawNode=pref.getString(Constants.FirebaseUser,"Jane");

        if(rawNode=="Jane"){
            nodename="John";
        }else{
            nodename="Jane";
        }
        Toast.makeText(OtherColourMenu.this,"Sending Colour to "+nodename,Toast.LENGTH_LONG).show();


        refco= FirebaseDatabase.getInstance().getReference(nodename);
        OtherColourPicker = (Button) findViewById(R.id.OtherColourPicker);
        OtherColourPicker.setBackgroundColor(DefaultColour);
        OtherColourPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColourPicker();
            }
        });


        OtherColourSendToButton= (Button) findViewById(R.id.OtherColourSendToButton);
        OtherColourSendToButton.setEnabled(false);

        OtherColourSendToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int fromAmbilWarna = DefaultColour;
                int mColourCode = convertToRGB(fromAmbilWarna);

                clorCode=Integer.toString(mColourCode);
                //TODO: Send mColourCode to firebase

                refco.setValue(mColourCode);
                finish();

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
                OtherColourPicker.setBackgroundColor(DefaultColour);
                OtherColourSendToButton.setBackgroundResource(R.drawable.checkbox_checked);


                Toast.makeText(OtherColourMenu.this, "Colour set!", Toast.LENGTH_LONG);

                OtherColourSendToButton.setEnabled(true);
            }
        });
        colourPicker.show();
    }

    public int convertToRGB(int aw){
        int dec = aw + 16777216;
        return dec;
    }


}

