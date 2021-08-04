package com.example.scriptgov20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scriptgov20.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val scriptCollectionRef = Firebase.firestore.collection("scripts")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var scriptList = mutableListOf(
            Script("Title"),
            Script("Great!")
        )

        val adapter = ScriptAdapter(scriptList)
        binding.rvScripts.adapter = adapter
        binding.rvScripts.layoutManager = LinearLayoutManager(this)

        binding.btnAddScript.setOnClickListener {
            val title = binding.etScript.text.toString()
            val script = Script(title)
            scriptList.add(script)
            adapter.notifyItemInserted(scriptList.size - 1)
            saveScript(script)
        }
    }

    private fun saveScript(script: Script) = CoroutineScope(Dispatchers.IO).launch {
        try {
            scriptCollectionRef.add(script).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Successfully saved data.", Toast.LENGTH_LONG).show()
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}