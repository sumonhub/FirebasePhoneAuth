package app.sumon.firebasephoneauth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val TAG = "VerificationActivity"

    var editTextCountryCode: TextInputEditText? = null
    var editTextPhone: TextInputEditText? = null
    var buttonContinue: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        FirebaseApp.initializeApp(this)
        //
        editTextCountryCode = findViewById(R.id.editTextCountryCode)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonContinue = findViewById(R.id.buttonContinue)
        buttonContinue?.setOnClickListener(View.OnClickListener {
            val code = editTextCountryCode?.getText().toString().trim { it <= ' ' }
            val number = editTextPhone?.getText().toString().trim { it <= ' ' }
            if (number.isEmpty() || number.length < 10) {
                editTextPhone?.setError("Valid number is required")
                editTextPhone?.requestFocus()
                return@OnClickListener
            }
            val phoneNumber = code + number
            val intent = Intent(this@MainActivity, VerificationActivity::class.java)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
        })
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
