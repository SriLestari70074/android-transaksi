package sri.lestari.com.transaksi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatTransaksi extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button kembali;
    TextView id, namabarang, jumlah, harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_transaksi);

        dbHelper = new DataHelper(this);
        id = (TextView) findViewById(R.id.txt_id);
        namabarang = (TextView) findViewById(R.id.txt_nama);
        jumlah = (TextView) findViewById(R.id.txt_jumlah);
        harga = (TextView) findViewById(R.id.txt_harga);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * from transaksi WHERE namabarang = '" +getIntent().getStringExtra("namabarang")+"'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0).toString());
            namabarang.setText(cursor.getString(1).toString());
            jumlah.setText(cursor.getString(2).toString());
            harga.setText(cursor.getString(3).toString());
        }
        kembali = (Button) findViewById(R.id.btn_kembali);

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
