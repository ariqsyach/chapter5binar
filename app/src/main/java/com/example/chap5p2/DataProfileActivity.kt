package com.example.chap5p2

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chap5p2.database.DatabaseContract
import com.example.chap5p2.database.NoteHelper
import com.example.chap5p2.helper.MappingHelper
import kotlinx.android.synthetic.main.activity_data_profile.*
import kotlinx.android.synthetic.main.dialog_edit_memo.view.*
import kotlinx.android.synthetic.main.dialog_memo.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DataProfileActivity : AppCompatActivity() {
    private lateinit var noteList: MutableList<Item>
    private lateinit var adapter: DatabaseAdapter
    private lateinit var noteHelper: NoteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_profile)
        val sp = getSharedPreferences("coba", Context.MODE_PRIVATE)
        val esp = sp.edit()

        esp.commit()
        val tvuname = et_uname as TextView
        val tvemail = et_email as TextView
        tvuname.text = sp.getString("name", "")
        tvemail.text = sp.getString("email", "")
        btn_backpilih.setOnClickListener {
            val intent = Intent(this@DataProfileActivity, PilihActivity::class.java)
            startActivity(intent)
        }
        noteHelper = NoteHelper.getInstance(this)
        noteHelper.open()
        noteList = mutableListOf()
        adapter = DatabaseAdapter(noteList)
        adapter.setOnClickListener(object : DatabaseAdapter.OnClickListenerCallback<Item> {
            override fun onClickListener(data: Item, position: Int) {
                Toast.makeText(this@DataProfileActivity, data.title, Toast.LENGTH_SHORT).show()
                showEditDialog(data, position)
            }
        })

        fab_addMemo.setOnClickListener {
            showAddDialog()
        }

        rv_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_main.setHasFixedSize(true)
        rv_main.adapter = adapter
        loadNotesAsync()
    }

    private fun loadNotesAsync() {
        lifecycleScope.launch(Dispatchers.Main) {
            MappingHelper.mapCursorToList(noteHelper.queryAll()) {
                noteList.clear()
                noteList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun showAddDialog() {
        this.let {
            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val myView = inflater.inflate(R.layout.dialog_memo, null)
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            builder.setView(myView)
            var tanggal = myView.et_tambahTanggal as TextView
            tanggal.text = "${currentDate}"
            val dialog = builder.create()
            dialog.show()
            myView.btn_tambah.setOnClickListener {
                val title = tanggal.text.toString()
                val content = myView.et_tambahMemo.text.toString()
                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(this, "ISI dulu DATANYA", Toast.LENGTH_SHORT).show()
                } else {
                    val values = ContentValues()
                    values.put(DatabaseContract.NoteColumns.TITLE, title)
                    values.put(DatabaseContract.NoteColumns.CONTENT, content)
                    lifecycleScope.launch {
                        val result = noteHelper.insert(values)
                        if (result > 0) {
                            Toast.makeText(
                                this@DataProfileActivity,
                                "BERHASIL DISIMPAN",
                                Toast.LENGTH_SHORT
                            ).show()
                            loadNotesAsync()
                        } else {
                            Toast.makeText(
                                this@DataProfileActivity,
                                "GAGAL DISIMPAN",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    dialog.dismiss()
                }

            }
            myView.btn_tambahcancel.setOnClickListener {
                dialog.dismiss()
            }
        }
    }


    fun showEditDialog(note: Item, position: Int) {
        this.let {
            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val myView = inflater.inflate(R.layout.dialog_edit_memo, null)
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            myView.et_editTanggal.setText(note.title)
            myView.et_editMemo.setText(note.content)
            builder.setView(myView)
            var tgl = myView.et_editTanggal as TextView
            tgl.text = "${currentDate}"
            val dialog = builder.create()
            dialog.show()
            myView.btn_canceledit.setOnClickListener {
                //            builder.setNeutralButton("BATAL"){dialog,_->
                dialog.cancel()
            }
//            builder.setPositiveButton("UPDATE"){_,_->
            myView.btn_updateedit.setOnClickListener {
                val title = tgl.text.toString()
                val content = myView.et_editMemo.text.toString()
                val values = ContentValues()
                values.put(DatabaseContract.NoteColumns.TITLE, title)
                values.put(DatabaseContract.NoteColumns.CONTENT, content)
                lifecycleScope.launch {
                    val result = noteHelper.update(note.id.toString(), values)
                    if (result > 0) {
                        Toast.makeText(
                            this@DataProfileActivity,
                            "BERHASIL DIUPDATE",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadNotesAsync()
                    } else {
                        Toast.makeText(
                            this@DataProfileActivity,
                            "GAGAL DIUPDATE",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }
                dialog.dismiss()
            }

//            builder.setNegativeButton("DELETE"){_,_->
            myView.btn_hapusedit.setOnClickListener {
                lifecycleScope.launch {
                    val result = noteHelper.deleteById(note.id.toString())
                    if (result > 0) {
                        Toast.makeText(
                            this@DataProfileActivity,
                            "BERHASIL DIHAPUS",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadNotesAsync()
                    } else {
                        Toast.makeText(
                            this@DataProfileActivity,
                            "GAGAL DIHAPUS",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
                dialog.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        noteHelper.close()
    }
}
