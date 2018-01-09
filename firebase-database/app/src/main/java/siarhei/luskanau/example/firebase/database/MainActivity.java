package siarhei.luskanau.example.firebase.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private TextView messageTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextView = findViewById(R.id.messageTextView);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");

        writeToDatabase(myRef);
        readFromDatabase(myRef);
    }

    private void writeToDatabase(final DatabaseReference myRef) {
        final Date date = new Date();
        final DateFormat dateFormat = android.text.format.DateFormat.getMediumDateFormat(this);
        final DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(this);

        // Write a message to the database
        myRef.setValue(String.format(Locale.ENGLISH, "Hello, World! %s %s", dateFormat.format(date), timeFormat.format(date)));
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
                Timber.d("Value is: " + value);
                messageTextView.setText(value);
            }

            @Override
            public void onCancelled(final DatabaseError error) {
                // Failed to read value
                Timber.w(error.toException(), "Failed to read value.");
            }
        });
    }

}
