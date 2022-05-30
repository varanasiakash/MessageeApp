package com.example.internshipchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Message extends AppCompatActivity {

    private RecyclerView recyclerMessage;
    private TextView profileNameMessage;
    private ImageView imageProfile;
    private EditText textMessage;
    private ImageView sendMessage;
    private ProgressBar progressMessage;



    private ImageView linking_file,Emoji,callOption,VideoCall, menuOption;


    private MessageAdapter messagesAdapter;
    private ArrayList<Messages> messages;

    String usernameOfTheRoommate;
    String chatRoomId;
    String email_Of_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        usernameOfTheRoommate  = getIntent().getStringExtra("username_of_roommate");
        email_Of_user = getIntent().getStringExtra("email_of_roommate");


        recyclerMessage = findViewById(R.id.recyclerMessage);
        profileNameMessage = findViewById(R.id.profileNameMessage);
        imageProfile = findViewById(R.id.profile_button_register);
        textMessage = findViewById(R.id.texting);
        sendMessage = findViewById(R.id.send_text);
        progressMessage = findViewById(R.id.progressBarMessage);

        messages = new ArrayList<>();

        profileNameMessage.setText(usernameOfTheRoommate);

        linking_file = findViewById(R.id.linking_file);
        Emoji = findViewById(R.id.emoji_button);

        VideoCall = findViewById(R.id.videoCall);
        callOption = findViewById(R.id.callMessage);

        menuOption = findViewById(R.id.menu_button);

        menuOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Message.this, "Menu Option", Toast.LENGTH_SHORT).show();
            }
        });

        VideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Message.this,VoiceCall.class));

            }
        });

        callOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Message.this,VoiceCall.class));
            }
        });

        Emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Message.this, "emoji option", Toast.LENGTH_SHORT).show();
            }
        });

        linking_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Message.this, "Attach files", Toast.LENGTH_SHORT).show();
            }
        });
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("messages/"+chatRoomId).push()
                        .setValue(new Messages(FirebaseAuth.getInstance().getCurrentUser().getEmail()
                                ,email_Of_user,textMessage.getText().toString()));
                textMessage.setText("");
            }
        });


        messagesAdapter = new MessageAdapter(messages,getIntent().getStringExtra("My_image"),
                getIntent().getStringExtra("image_of_roommate"),Message.this);

        Glide.with(Message.this).load(getIntent().getStringExtra("image_of_roommate"))
                .placeholder(R.drawable.ic_account).error(R.drawable.ic_account).into(imageProfile);

        recyclerMessage.setLayoutManager(new LinearLayoutManager(this));
        recyclerMessage.setAdapter(messagesAdapter);


        setupChatRoom();

    }

    private void setupChatRoom() {
        FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String myUsername = snapshot.getValue(UsersChat.class).getUsername();
                        if (usernameOfTheRoommate.compareTo(myUsername)>0)
                        {
                            chatRoomId  = myUsername + usernameOfTheRoommate;
                        }
                        else if (usernameOfTheRoommate.compareTo(myUsername)==0)
                        {
                            chatRoomId  = myUsername + usernameOfTheRoommate;
                        }
                        else
                        {
                            chatRoomId  = usernameOfTheRoommate + myUsername;
                        }
                        attachMessageListener(chatRoomId);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void attachMessageListener(String chatRoomId) {

        FirebaseDatabase.getInstance().getReference("messages/"+chatRoomId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    //gets triggered when new messages arrive
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //array list messages
                        messages.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            messages.add(dataSnapshot.getValue(Messages.class));
                        }
                        messagesAdapter.notifyDataSetChanged();
                        recyclerMessage.scrollToPosition(messages.size() -1);
                        recyclerMessage.setVisibility(View.VISIBLE);
                        progressMessage.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}