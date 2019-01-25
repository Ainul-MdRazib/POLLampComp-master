package com.example.ainul.polarisapp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnotherMessageActivity extends AppCompatActivity {

    private ChatAdapter adapter;
    private ListView listView;
    private EditText message;
    public  String username;
    private TextView namedisplay;
    private DatabaseReference reference;
    private SharedPreferences pref;
    private SharedPreferences prefname;
    private String firebaseNode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_message);

        ImageButton BackFromMessageButton = findViewById(R.id.BackFromMessageButton);
        BackFromMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AnotherMessageActivity.this,MainMenu.class);
                startActivity(i);
            }
        });

        ImageButton EmojiFromMessageButton = findViewById(R.id.EmojiFromMessageButton);
        EmojiFromMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnotherMessageActivity.this, XtraEmojiMenu.class));
            }
        });

        ImageButton ColourFromMessageButton = findViewById(R.id.ColourFromMessageButton);
        ColourFromMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnotherMessageActivity.this,OtherColourMenu.class));
            }
        });





        //pref=getSharedPreferences(Constants.FILEEMOJI,MODE_PRIVATE);
        pref=getSharedPreferences(Constants.FILEEMOJI,MODE_PRIVATE);
        prefname=getSharedPreferences(Constants.FILE,MODE_PRIVATE);


        username=prefname.getString(Constants.TAG,"message username");
        firebaseNode=pref.getString(Constants.FirebaseUser,"Jane");
        //Log.d("TESTNAME","getting username, in message page///////////////////"+username);



        listView = findViewById(R.id.list);
        message = findViewById(R.id.message);

        namedisplay=findViewById(R.id.AnotherMessageTitle);
        namedisplay.setText("Chat Room");

        ImageButton send = findViewById(R.id.send_message);


        adapter = new ChatAdapter(this, R.id.list);
        listView.setAdapter(adapter);



        ////////////////////////////////////////////////////////firebase initiating....///////////////////////////////////////////////////////////////////////
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("chat");
        //Log.d("TEST","get user name from last page");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage msg = dataSnapshot.getValue(ChatMessage.class);
                adapter.add(msg);
                scrollToBottom();
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            public void onCancelled(DatabaseError databaseError) {}
        });

        //Log.d("TEST","get user name from last page");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatMessage msg = new ChatMessage(username, message.getText().toString());
                reference.push().setValue(msg);
                message.setText("");
                scrollToBottom();
            }
        });


    }

    @Override


    protected void onPause(){
        super.onPause();
    }

    private void scrollToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.setSelection(adapter.getCount() - 1);
            }
        });
    }
}
