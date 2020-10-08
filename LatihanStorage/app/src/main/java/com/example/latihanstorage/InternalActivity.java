package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "namafile.txt";
    Button buat, ubah, hapus, baca;
    TextView txtbaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        buat = findViewById(R.id.btnBuat);
        ubah = findViewById(R.id.btnUbah);
        hapus = findViewById(R.id.btnHapus);
        baca = findViewById(R.id.btnBaca);
        txtbaca = findViewById(R.id.txtbaca);

        buat.setOnClickListener(this);
        ubah.setOnClickListener(this);
        baca.setOnClickListener(this);
        hapus.setOnClickListener(this);
    }

    void buatFile(){
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(InternalActivity.this, "Sudah Save", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            file.delete();
            txtbaca.setText("");
            Toast.makeText(InternalActivity.this, "Sudah Hapus", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        jalankanPerintah(view.getId());
    }

    private void jalankanPerintah(int id) {
        switch (id) {
            case R.id.btnBuat:
                buatFile();
                break;
            case R.id.btnUbah:
                ubahFile();
                break;
            case R.id.btnHapus:
                hapusFile();
                break;
            case R.id.btnBaca:
                bacaFile();
                break;
        }
    }

    private void bacaFile() {
        File lok = getFilesDir();
        File file = new File(lok, FILENAME);

        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line!=null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error "+ e.getMessage());
            }
            txtbaca.setText(text.toString());
        }
    }

    private void ubahFile() {
        String isiFile = "Ubah File Text";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(InternalActivity.this, "Sudah Ganti", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
