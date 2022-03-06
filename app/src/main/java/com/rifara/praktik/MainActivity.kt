package com.rifara.praktik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.rifara.praktik.databinding.ActivityMainBinding

// View.OnClickListener: adalah listener yg kita implementasikan untuk memantau kejadian klik
// pada komponen button.
class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Mendeklarasikan komponen view yang akan dimanipulasi.
    // Dideklarasikan secara global agar bisa dikenal di keseluruhan bagian kelas
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    // onCreate: metode utama pada activity
    // setContentView(R.layout.activity_main): berfungsi untuk mengubungkan file kotlin ke file xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // findViewById berfungsi untuk menghubungkan variable yang dibuat sebelumnya dengan id di layout
    // Casting View: object edtWidth disesuaikan casting dengan komponen edittext ber id editText2 di XML
        edtWidth = findViewById(R.id.editText2)
        edtHeight = findViewById(R.id.editText)
        edtLength = findViewById(R.id.editText3)
        btnCalculate = findViewById(R.id.button)
        tvResult = findViewById(R.id.textView4)

    // setOnClickListener: memberikan aksi kepada komponen apabila di klik oleh user
    // keyword this merujuk pada object activity saat ini yaitu MainActivity yg telah
    // mengimplementasikan listener OnClickListener
        btnCalculate.setOnClickListener(this)

    // Menggunakan nilai bundle yg telah kita simpan sebelumnya pada onSaveInstanceState.
    // Nilai tersebut didapatkan dengan menggunakan key yg sama saat menyimpan, yaitu STATE_RESULT
    // Kemudian diisikan kembali pada tvResult
        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }



    override fun onClick(v: View) {
        if (v.id == R.id.button) {
    // Membuat variable baru untuk insialisasi variable yang sudah di casting view sebelumnya.

    // .text.toString(): konversi menjadi string agar dapat mengambil isi dari sebuah edittext,
    // kemudian menyimpan di suatu variable inputLength.

    // .trim(): digunakan untuk menghiraukan spasi sehingga hanya dapat menerima inputan berupa angka
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

    // Fungsi perhitungan volume P * L * T
    // ini masih salah karena ketika user tidak memasukan apa apa didalam edit text kemudian,
    // klik button HITUNG maka aplikasi akan force close.
//      val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
//      tvResult.text = volume.toString()

    // Solusi dari masalah diatas adalah membuat pengecekan menggunakan percabangan agar apabila,
    // edittext kosong akan kita kasih aksi, dan apabila terisi kita kasih aksi.
            var isEmptyFields = false

            if (inputLength.isEmpty()){
                isEmptyFields = true // agar bisa lanjut ke proses selanjutnya
                edtLength.error="Field Tidak Boleh Kosong" // Menampilkan pesan error
            }
            if (inputWidth.isEmpty()){
                isEmptyFields = true // agar bisa lanjut ke proses selanjutnya
                edtWidth.error="Field Tidak Boleh Kosong" // Menampilkan pesan error
            }
            if (inputHeight.isEmpty()){
                isEmptyFields = true // agar bisa lanjut ke proses selanjutnya
                edtHeight.error="Field Tidak Boleh Kosong" // Menampilkan pesan error
            }
    // Memberikan isEmptyFields bernilai true dan memberikan perhitungan volume dan mengkonversi,
    // dari sebelumnya String ke Double agar bisa memberikan perhitungan bilangan.
            if (!isEmptyFields){
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
    // Kemudian komponen variable tvResult yang sebelumnya bernilai double maka konversi ke string,
    // karena untuk menampilkan kedalam textview harus bernilai string
                tvResult.text = volume.toString()
            }
        }
    }


    // Method onSaveInstanceState
    // companion object untuk membuat komponen yg static dan bisa di akses dari luar kelas
    // untuk onSaveInstanceState
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    // Berhubungan dengan line 45
    // onSaveInstanceState: Perubahan yg terjadi pada acivity perlu disimpan terlebih dahulu
    // sebelum di destroy
    // hasil perhitungan yg ditampilkan pada tvResult dimasukan pada bundle kemudian disimpan isinya.
    // untuk menyimpan data disini menggunakan konsep key-value, dengan STATE_RESULT
    // sebagai key dan isi dari tvResult sebagai value
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}