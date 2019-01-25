package com.example.ainul.polarisapp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class XtraEmojiMenu extends AppCompatActivity {

    private Button BackFromOtherEmojiButton;

    private int colourCode;

    private ImageButton otherangrySelected;
    private ImageButton othersickSelected;
    private ImageButton othercryingSelected;
    private ImageButton othersmilingSelected;
    private ImageButton othersleepySelected;
    private ImageButton otherparty_popperSelected;
    private ImageButton otherhearteyesSelected;
    private ImageButton otherbusySelected;

    String emoji="";

    private DatabaseReference refe;
    private SharedPreferences prefe;
    private String nodeName;
    private String rawNode;

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xtra_emoji_menu);

        prefe=getSharedPreferences(Constants.FILEEMOJI,MODE_PRIVATE);
        rawNode=prefe.getString(Constants.FirebaseUser,"Jane");
        Log.i("TESTING","getting firebase username,"+rawNode);

        if(rawNode=="Jane"){
            nodeName="John";
        }else{
            nodeName="Jane";
        }

        Toast.makeText(XtraEmojiMenu.this,"Sending Emoji to "+nodeName,Toast.LENGTH_LONG).show();
        //index=prefe.getInt(Constants.UserIndex,)

        refe= FirebaseDatabase.getInstance().getReference(nodeName);
        refe= FirebaseDatabase.getInstance().getReference(nodeName);

        BackFromOtherEmojiButton = findViewById(R.id.BackFromOtherEmojiButton);
        BackFromOtherEmojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        otherangrySelected = findViewById(R.id.otherangry);
        otherangrySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colourCode = 16138303;

                emoji="angry";
                //TODO: colourcode to firebase
                refe.setValue(emoji);

                finish();
            }
        });

        otherbusySelected = findViewById(R.id.otherbusy);
        otherbusySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colourCode = 16097024;

                emoji="busy";
                //TODO: colourcode to firebase
                refe.setValue(emoji);

                finish();
            }
        });

        othercryingSelected = findViewById(R.id.othercrying);
        othercryingSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colourCode = 9654762;

                emoji="cry";
                //TODO: colourcode to firebase
                refe.setValue(emoji);

                finish();
            }
        });

        otherhearteyesSelected = findViewById(R.id.otherhearteyes);
        otherhearteyesSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colourCode = 15895474;

                emoji="love";
                //TODO: colourcode to firebase
                refe.setValue(emoji);

                finish();
            }
        });

        otherparty_popperSelected = findViewById(R.id.otherparty_popper);
        otherparty_popperSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colourCode = 10513131;

                emoji="party";
                //TODO: colourcode to firebase
                refe.setValue(emoji);
                finish();
            }
        });

        othersickSelected = findViewById(R.id.othersick);
        othersickSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colourCode = 9415707;

                emoji="sick";
                //TODO: colourcode to firebase
                refe.setValue(emoji);
                finish();
            }
        });

        othersleepySelected = findViewById(R.id.othersleepy);
        othersleepySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colourCode = 7650284;

                emoji="sleepy";
                //TODO: colourcode to firebase
                refe.setValue(emoji);
                finish();
            }
        });
        othersmilingSelected = findViewById(R.id.othersmiling);
        othersmilingSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colourCode = 16240432;

                emoji="happy";
                //TODO: colourcode to firebase
                refe.setValue(emoji);
                finish();
            }
        });
    }



}
