package au.edu.utas.assignment305;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ShoppingListDB {

        //This is the tag that is used for the Logcat output (useful for filtering output)
        private static final String TAG = "PropertyDatabase";

        //The name of the database
        private static final String DATABASE_NAME = "PropertyDatabase";

        //The version of the database. Increment this whenever you change the /structure/
        private static final int DATABASE_VERSION = 2;

        //The connection to the database itself
        private SQLiteDatabase mDb;

        //The helper which we will use to open a connection to the database
        private DatabaseHelper mDbHelper;

        //The application context in which this code is run
        private final Context mCtx;

        // Constructor
        public ShoppingListDB(Context ctx) {
            this.mCtx = ctx;
        }

        private static class DatabaseHelper extends SQLiteOpenHelper {
            //This constructor creates the database.
            DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            //This code is called only the first time the database is created
            @Override
            public void onCreate (SQLiteDatabase db) {
                db.execSQL(ShoppingListTable.CREATE_STATEMENT);
                Log.d(TAG, "DatabaseHelper onCreate");
            }

            //This code is called if the version number changes
            @Override
            public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + ShoppingListTable.TABLE_NAME);
                onCreate(db); //this will recreate the database as if it were new
                Log.d(TAG, "DatabaseHelper onUpgrade");
            }
        }

        public SQLiteDatabase open() {
            mDbHelper = new DatabaseHelper(mCtx);
            mDb = mDbHelper.getWritableDatabase();
            return mDb;
        }

        public void close(SQLiteDatabase db) {
            close(mDb);
            mDb = null;
        }
    }
