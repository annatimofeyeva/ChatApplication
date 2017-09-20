package com.epicodus.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String FIREBASE_CHILD_MESSAGE_CONTENT = "messageContent";
    private DatabaseReference mMessageContentReference;

    @Bind(R.id.chatEditText) TextView mChatEditText;
    @Bind(R.id.submitButton) Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mMessageContentReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(FIREBASE_CHILD_MESSAGE_CONTENT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = mChatEditText.getText().toString();

                saveContentToFirebase(content);

                Log.d(TAG, "Message content: " + content);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("content", content);
                startActivity(intent);


            }

            public void saveContentToFirebase(String content) {
                mMessageContentReference.setValue(content);

            }


        });
    }
}
