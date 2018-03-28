package sri.lestari.com.transaksi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransaksiBaru extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button simpan, kembali;
    EditText id, namabarang, jumlah, harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_baru);

        dbHelper = new DataHelper(this);
        id = (EditText) findViewById(R.id.id);
        namabarang = (EditText) findViewById(R.id.namabarang);
        jumlah = (EditText) findViewById(R.id.jumlah);
        harga = (EditText) findViewById(R.id.harga);
        simpan = (Button) findViewById(R.id.btn_simpan);
        kembali = (Button) findViewById(R.id.btn_kembali);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into transaksi(id, namabarang, jumlah, harga) values('" +
                        id.getText().toString()+"','"+
                        namabarang.getText().toString() +"','" +
                        jumlah.getText().toString()+"','"+
                        harga.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil",
                        Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
