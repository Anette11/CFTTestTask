package com.example.cfttesttask.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cfttesttask.R
import com.example.cfttesttask.navigation.CardNavHost
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            CardNavHost(
                onPhoneClick = { phone: String -> onPhoneClick(phone = phone) },
                onUrlClick = { url: String -> onUrlClick(url = url) },
                onCoordinatesClick = { latitude: Double, longitude: Double ->
                    onCoordinatesClick(latitude, longitude)
                },
                onError = { message: String -> showToast(message = message) }
            )
        }
    }

    private fun onPhoneClick(
        phone: String
    ) = try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        showToast(message = getString(R.string.activity_not_found_exception_message))
    }

    private fun onUrlClick(
        url: String
    ) = try {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                if (url.startsWith("https://") || url.startsWith("http://")) url
                else "https://".plus(url)
            )
        )
        startActivity(intent)
    } catch (e: Exception) {
        showToast(message = getString(R.string.activity_not_found_exception_message))
    }

    private fun onCoordinatesClick(
        latitude: Double,
        longitude: Double
    ) = try {
        val coordinates = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(coordinates))
        startActivity(intent)
    } catch (e: Exception) {
        showToast(message = getString(R.string.activity_not_found_exception_message))
    }

    private fun showToast(
        message: String
    ) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}