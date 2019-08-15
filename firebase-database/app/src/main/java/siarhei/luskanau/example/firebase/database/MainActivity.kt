package siarhei.luskanau.example.firebase.database

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Date
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageTextView = findViewById(R.id.messageTextView)

        try {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            writeToDatabase(myRef)
            readFromDatabase(myRef)
        } catch (t: Throwable) {
            messageTextView.text = t.toString()
        }
    }

    private fun writeToDatabase(myRef: DatabaseReference) {
        val date = Date()
        val dateFormat = android.text.format.DateFormat.getMediumDateFormat(this)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(this)

        // Write a message to the database
        myRef.setValue("Hello, World! ${dateFormat.format(date)} ${timeFormat.format(date)}")
    }

    private fun readFromDatabase(myRef: DatabaseReference) {
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                messageTextView.text = value
                Timber.d("Value is: %s", value)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Timber.w(error.toException(), "Failed to read value.")
            }
        })
    }
}
