package siarhei.luskanau.example.firebase.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView messageTextView;

    private TextView messageTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageTextView = (TextView) findViewById(R.id.message);

        messageTextView = (TextView) findViewById(R.id.messageTextView);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");

        writeToDatabase(myRef);
        readFromDatabase(myRef);
    }

    private void writeToDatabase(final DatabaseReference myRef) {
        // Write a message to the database
        myRef.setValue("Hello, World! " + new Date());
    }

    private void readFromDatabase(final DatabaseReference myRef) {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                final String value = dataSnapshot.getValue(String.class);
                messageTextView.setText(value);
                Log.d(TAG, "Value is: " + value);
                messageTextView.setText(value);
            }

            @Override
            public void onCancelled(final DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
