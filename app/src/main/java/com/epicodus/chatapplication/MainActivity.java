package com.epicodus.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

    private FirebaseDatabase database;
    private DatabaseReference reference;




    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String FIREBASE_CHILD_MESSAGE_CONTENT = "messageContent";
    //private DatabaseReference mMessageContentReference;

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

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("messages");

        result = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.message_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lim);

        //createResult();

        adapter = new MessageAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();

//        mMessageContentReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(FIREBASE_CHILD_MESSAGE_CONTENT);


//        mSubmitButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String content = mChatEditText.getText().toString();
//
//                if (v == mSubmitButton) {
//                    DatabaseReference messageRef = FirebaseDatabase
//                            .getInstance()
//                            .getReference(FIREBASE_CHILD_MESSAGE_CONTENT);
//
//                    messageRef.push().setValue(content);
//                    //saveContentToFirebase(content);
//                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
//
//                    Log.d(TAG, "Message content: " + content);
//
//
//                }
//
//            }
//        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()) {

            case 0:
                break;
            case 1:
                break;
        }

        return super.onContextItemSelected(item);
    }

//    private void createResult() {
//
//        for (int i = 0; i < 10; i++) {
//            result.add(new Message("content", ""));
//
//        }
//    }

    private void updateList(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                result.add(dataSnapshot.getValue(Message.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Message model = dataSnapshot.getValue(Message.class);

                int index = getItemIndex(model);

                result.remove(index);

                adapter.notifyItemRemoved(index);


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                Message model = dataSnapshot.getValue(Message.class);

                int index = getItemIndex(model);

                result.set(index, model);

                adapter.notifyItemChanged(index);


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



     private int getItemIndex(Message message){

        int index = -1;

         for (int i = 0; i < result.size(); i++){
             if(result.get(i).key.equals(message.key)) {
                 index = i;
                 break;
             }
         }

         return index;
    }



} // end of class