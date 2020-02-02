package siarhei.luskanau.example.firebase.database

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Date
import siarhei.luskanau.example.firebase.database.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(LayoutInflater.from(this))
            .also {
                binding = it
                setContentView(binding.root)
            }

        runCatching {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            writeToDatabase(myRef)
            readFromDatabase(myRef)
        }.onFailure { t: Throwable ->
            binding.messageTextView.text = t.toString()
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
                binding.messageTextView.text = value
                Timber.d("Value is: %s", value)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                binding.messageTextView.text = error.message
                Timber.w(error.toException(), "Failed to read value.")
            }
        })
    }
}
