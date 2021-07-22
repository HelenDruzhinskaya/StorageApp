package ru.myitacademy.samsung.storageapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class MainActivity : AppCompatActivity() {
    lateinit var txt: TextView
    lateinit var im: ImageView
    lateinit var storageRef: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.txt)
        im = findViewById(R.id.iv)
    }

    fun downloadPic(v: View) {
        storageRef = Firebase.storage.reference
        val pathReference = storageRef.child("SecretFolder/eight.png")
        pathReference.getBytes(1024 * 1024).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            im.setImageBitmap(bitmap)
        }.addOnFailureListener {}
    }

    fun downloadFile(V: View) {
        storageRef = Firebase.storage.reference
        val pathReference = storageRef.child("cheerful.png")
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(applicationContext).load(it).into(im)

        }.addOnFailureListener {}
    }

}