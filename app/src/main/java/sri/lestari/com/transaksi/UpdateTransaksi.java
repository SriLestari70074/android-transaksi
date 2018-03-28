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

public class UpdateTransaksi extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button simpan, kembali;
    EditText id, namabarang, jumlah, harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaksi);

        dbHelper = new DataHelper(this);
        id = (EditText) findViewById(R.id.edt_id);
        namabarang = (EditText) findViewById(R.id.edt_namabarang);
        jumlah = (EditText) findViewById(R.id.edt_jumlah);
        harga = (EditText) findViewById(R.id.edt_harga);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * from transaksi WHERE namabarang = '"+getIntent().getStringExtra("namabarang")+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0).toString());
            namabarang.setText(cursor.getString(1).toString());
            jumlah.setText(cursor.getString(2).toString());
            harga.setText(cursor.getString(3).toString());
        }
        simpan = (Button) findViewById(R.id.btn_simpan);
        kembali = (Button) findViewById(R.id.btn_kembali);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update transaksi set namabarang='"+
                namabarang.getText().toString()+"', jumlah='" +
                jumlah.getText().toString()+"', harga='" +
                harga.getText().toString()+"' where id='" +
                id.getText().toString()+"'");

                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
