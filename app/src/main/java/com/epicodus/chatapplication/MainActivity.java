package com.epicodus.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Message> result;
    private MessageAdapter adapter;


    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String FIREBASE_CHILD_MESSAGE_CONTENT = "messageContent";
    private DatabaseReference mMessageContentReference;

    @Bind(R.id.chatEditText)
    TextView mChatEditText;
    @Bind(R.id.submitButton)
    Button mSubmitButton;
    //@Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    //private MessageListAdapter mAdapter;
    public ArrayList<Message> mMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        result = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.message_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lim);

        createResult();

        adapter = new MessageAdapter(result);
        recyclerView.setAdapter(adapter);


        mMessageContentReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(FIREBASE_CHILD_MESSAGE_CONTENT);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = mChatEditText.getText().toString();

                if (v == mSubmitButton) {
                    DatabaseReference messageRef = FirebaseDatabase
                            .getInstance()
                            .getReference(FIREBASE_CHILD_MESSAGE_CONTENT);

                    messageRef.push().setValue(content);
                    //saveContentToFirebase(content);
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                    Log.d(TAG, "Message content: " + content);


                }

            }
        });

    }
    private void createResult() {

       for (int i = 0; i < 10; i++) {
            result.add(new Message("content", ""));

         }
    }

} // end of class