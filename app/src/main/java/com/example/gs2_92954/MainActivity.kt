package com.example.gs2_92954

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gs2_92954.viewmodel.ItemsAdapter
import com.example.gs2_92954.viewmodel.ItemsViewModel
import com.example.gs2_92954.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Dicas Ecológicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }

        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val btnIntegrantes = findViewById<Button>(R.id.btnIntegrantes)
        val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)

        button.setOnClickListener {
            if (editTextTitle.text.isEmpty()) {
                editTextTitle.error = "Preencha um valor"
                return@setOnClickListener
            }
            if (editTextDescription.text.isEmpty()) {
                editTextDescription.error = "Preencha um valor"
                return@setOnClickListener
            }
            viewModel.addItem(editTextTitle.text.toString(), editTextDescription.text.toString())
            editTextTitle.text.clear()
            editTextDescription.text.clear()
        }
        btnIntegrantes.setOnClickListener {
            val intent = Intent(this, GroupActivity::class.java)
            startActivity(intent)
        }

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}