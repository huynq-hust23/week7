package com.example.myapplication// com.example.myapplication.MainActivity.kt
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var searchView: SearchView

    private val studentList = listOf(
        Student("Hoang Hai Dang", "123456"),
        Student("Nguyen Nhat Huy", "234567"),
        Student("Nguyen Ngoc Le", "345678"),
        Student("Nguyen Huy Vu Dung","345678")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        searchView = findViewById(R.id.searchView)
        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = if (!newText.isNullOrEmpty() && newText.length > 2) {
                    studentList.filter {
                        it.name.contains(newText, ignoreCase = true) ||
                                it.studentId.contains(newText)
                    }
                } else {
                    studentList
                }
                studentAdapter.updateList(filteredList)
                return true
            }
        })
    }
}
