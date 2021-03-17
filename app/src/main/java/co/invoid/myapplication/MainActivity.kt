package co.invoid.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import co.invoid.livenesscheck.LivenessHelper
import co.invoid.livenesscheck.LivenessResponse
import co.invoid.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startVerificationButton.setOnClickListener {
            val authKey = binding.authkeyEditText.text?.trim().toString()
            if(authKey == "") {
                Snackbar.make(binding.root, "Invalid Authkey!", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.statusTextView.visibility = View.INVISIBLE
            binding.statusImageView.visibility = View.INVISIBLE

            LivenessHelper.with(this@MainActivity, authKey).start()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == LivenessHelper.LIVENESS_HELPER_REQ_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                val livenessResponse = data!!.getParcelableExtra<LivenessResponse>(LivenessHelper.RESULT)
                binding.statusTextView.visibility = View.VISIBLE
                binding.statusImageView.visibility = View.VISIBLE
                binding.statusImageView.isActivated = livenessResponse?.livenessApiResponse?.livenessStatus == "1"

            } else if(resultCode == LivenessHelper.AUTHORIZATION_RESULT_CODE) {
                Snackbar.make(binding.root, "Not authorised!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}