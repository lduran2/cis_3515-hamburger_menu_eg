package com.example.hamburgermenuexample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Inflates the menu on MainActivity.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_take_picture -> Toast.makeText(this, "You clicked the Take Picture option", Toast.LENGTH_SHORT).show()
            // goes to temple website
            R.id.action_help -> startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.temple.edu")))
            // deleting is very destructive, so don't allow accidental deletes
            // no undo option
            R.id.action_delete_picture -> deletePictureIfConfirm()
        }
        return super.onOptionsItemSelected(item)
    }

    fun deletePictureIfConfirm() {
        // alert builder pattern, takes context
        AlertDialog.Builder(this)
            .setTitle("Delete Confirmation")
            .setMessage("Are you sure you want to delete this picture? This ation cannot be undone.")
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                Toast.makeText(this, "Cool! You deleted the picture.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
            // cannot click outside to cancel the menu
            .setCancelable(false)
            // need to create and show
            .create()
            .show()
    }
}