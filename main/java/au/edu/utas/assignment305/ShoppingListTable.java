package au.edu.utas.assignment305;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class ShoppingListTable {

        public static final String TABLE_NAME = "ShoppingList";
        public static final String KEY_SHOPPING_LIST_ID = "ShoppingList_id";

        public static ShoppingList createFromCursor(Cursor c) {
            if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
                return null;
            }
            else {
                ShoppingList p = new ShoppingList();
                p.setShoppingListID(c.getInt(c.getColumnIndex(KEY_SHOPPING_LIST_ID)));
                return p;
            }
        }

        public static final
        String CREATE_STATEMENT = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + KEY_SHOPPING_LIST_ID
                + " integer primary key autoincrement, "
                + ");";
        public static void insert(SQLiteDatabase db, ShoppingList p) {
            ContentValues values = new ContentValues();
            db.insert(TABLE_NAME, null, values);
        }

        public static ArrayList<ShoppingList> selectAll(SQLiteDatabase db) {
            ArrayList<ShoppingList> results = new ArrayList<ShoppingList>();

            return results;
        }

        public void query(SQLiteDatabase db) {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
            //check for error
            if (c != null) {
                //make sure the cursor is at the start of the list
                c.moveToFirst();
                //loop through until we are at the end of the list
                while (!c.isAfterLast()) {
                    ShoppingList p = createFromCursor(c);
                    //results.add(p);

                    //increment the cursor
                    c.moveToNext();
                }
            }
        }
    }