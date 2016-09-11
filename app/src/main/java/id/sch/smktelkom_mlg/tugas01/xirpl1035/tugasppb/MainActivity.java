package id.sch.smktelkom_mlg.tugas01.xirpl1035.tugasppb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText etNama, etTahun;
    Spinner spGame, spPM;
    String[][] arGame = {{"No Man's Sky", "Dying Light", "COD : Ghost"}, {"GTA V", "Infamous Second Son", "Watch Dogs"}};
    ArrayList<String> listGame = new ArrayList<>();
    ArrayAdapter<String> adapter;
    CheckBox cbSP, cbCD, cbMD;
    RadioGroup kirim;
    TextView tvHasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        kirim = (RadioGroup) findViewById(R.id.pengiriman);
        spPM = (Spinner) findViewById(R.id.spinnerPM);
        spGame = (Spinner) findViewById(R.id.spinnerGame);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listGame);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGame.setAdapter(adapter);
        cbSP = (CheckBox) findViewById(R.id.checkBoxSP);
        cbCD = (CheckBox) findViewById(R.id.checkBoxCD);
        cbMD = (CheckBox) findViewById(R.id.checkBoxMD);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        spPM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listGame.clear();
                listGame.addAll(Arrays.asList(arGame[pos]));
                adapter.notifyDataSetChanged();
                spGame.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                klik();
            }
        });
        findViewById(R.id.buttonCon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvHasil.setText("Pembelian Berhasil");
                findViewById(R.id.buttonCon).setVisibility(View.GONE);
            }
        });
    }

    private void klik() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int umur = 2016 - tahun;
            int biaya = 0;
            String Pengiriman = null;
            String game = spGame.getSelectedItem().toString();
            if (game == "No Man's Sky") {
                biaya = 550000;
            } else if (game == "Dying Light") {
                biaya = 850000;
            } else if (game == "COD : Ghost") {
                biaya = 400000;
            } else if (game == "GTA V") {
                biaya = 650000;
            } else if (game == "Infamous Second Son") {
                biaya = 500000;
            } else if (game == "Watch Dogs") {
                biaya = 300000;
            }
            String extra = "\nExtras :";
            if (cbSP.isChecked()) {
                extra += "\n" + cbSP.getText();
                biaya += 200000;
            }
            if (cbMD.isChecked()) {
                extra += "\n" + cbMD.getText();
                biaya += 50000;
            }
            if (cbCD.isChecked()) {
                extra += "\n" + cbCD.getText();
                biaya += 100000;
            }
            if (kirim.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton) findViewById(kirim.getCheckedRadioButtonId());
                Pengiriman = rb.getText().toString();
                biaya += 20000;
            }

            tvHasil.setText("Nama    :" + nama +
                    "\nUmur     :" + umur +
                    "\nGame    :" + game +
                    extra +
                    "\n\nPengiriman :" + Pengiriman +
                    "\nBiaya           :" + "Rp" + biaya + ",00");
            findViewById(R.id.buttonCon).setVisibility(View.VISIBLE);
        }


    }

    private boolean isValid() {
        boolean valid = true;
        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();
        TextView Extra = (TextView) findViewById(R.id.textViewExtra);
        TextView Kirim = (TextView) findViewById(R.id.textViewKirim);
        if (nama.isEmpty()) {
            etNama.setError("Nama Belum diisi");
            valid = false;
        }
        if (tahun.isEmpty() || tahun.length() != 4) {
            etTahun.setError("Format Pengisian Belum Sesuai");
            valid = false;
        }
        if (cbMD.isChecked() || cbSP.isChecked() || cbCD.isChecked()) {
            valid = true;
        } else {
            Extra.setError("Belum Memilih");
            valid = false;
        }
        if (kirim.getCheckedRadioButtonId() <= 0) {
            Kirim.setError("Belum Memilih");
            valid = false;
        }
        return valid;
    }
}
