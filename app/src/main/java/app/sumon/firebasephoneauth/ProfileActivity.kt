package app.sumon.firebasephoneauth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class ProfileActivity : AppCompatActivity() {
    private val TAG = "VerificationActivity"

    var phoneNumber: String? = null
    var mobileNumber: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // get saved phone number
        val prefs = applicationContext.getSharedPreferences(
            "USER_PREF",
            MODE_PRIVATE
        )
        phoneNumber = prefs.getString("phoneNumber", null)
        mobileNumber = findViewById(R.id.mobileNumber)
        mobileNumber?.setText(phoneNumber)
        findViewById<View>(R.id.buttonLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@ProfileActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}