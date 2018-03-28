package sri.lestari.com.transaksi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ocand on 2018-03-27.
 */

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaksibarang.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table transaksi(id integer primary key, namabarang text null, jumlah integer, harga integer);";
        Log.d("Data", "onCreate: " +sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
