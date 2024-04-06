package vn.phamthang.phamthang_hw_day07.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import vn.phamthang.phamthang_hw_day07.Interface.IOnChangeQuantityModel;
import vn.phamthang.phamthang_hw_day07.Model.Food;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cart.db";
    public static final String FOOD_TABLE = "FOOD";

    public static final String ID_COLUMN = "id";
    public static final String FOODNAME_COLUMN = "food_name";
    public static final String IMAGE_COLUMN = "image";
    public static final String PRICE_COLUMN = "price";
    public static final String WEIGHT_COLUMN = "weight";
    private static ArrayList<Food> mlist = new ArrayList<>();
    private Context context;
    private static final int VERSION = 1;

    private IOnChangeQuantityModel iOnChangeQuantityModel;

    public void setiOnChangeQuantityModel(IOnChangeQuantityModel iOnChangeQuantityModel) {
        this.iOnChangeQuantityModel = iOnChangeQuantityModel;
    }

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + FOOD_TABLE + "("
                + ID_COLUMN + " TEXT NOT NULL PRIMARY KEY ,"
                + FOODNAME_COLUMN + " TEXT NOT NULL, "
                + PRICE_COLUMN + " NUMERIC NOT NULL, "
                + WEIGHT_COLUMN + " NUMERIC NOT NULL, "
                + IMAGE_COLUMN + " NUMERIC NOT NULL);";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addFood(Food food) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_COLUMN, food.getId());
        contentValues.put(FOODNAME_COLUMN, food.getFoodName());
        contentValues.put(PRICE_COLUMN, food.getPrice());
        contentValues.put(IMAGE_COLUMN, food.getImageFood());
        contentValues.put(WEIGHT_COLUMN, food.getWeight());


        database.insert(FOOD_TABLE, null, contentValues);
        database.close();
    }

    public ArrayList<Food> getAll() {
        SQLiteDatabase database = getReadableDatabase();
        mlist.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM " + FOOD_TABLE, null);

        while (cursor.moveToNext()) {
            Food food = getFoodFromCursor(cursor);
            mlist.add(food);
        }
        cursor.close();
        database.close();
        return mlist;

    }

    private Food getFoodFromCursor(Cursor cursor) {

        @SuppressLint("Range")
        int id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
        @SuppressLint("Range")
        Integer image = cursor.getInt(cursor.getColumnIndex(IMAGE_COLUMN));
        @SuppressLint("Range")
        String foodname = cursor.getString(cursor.getColumnIndex(FOODNAME_COLUMN));
        @SuppressLint("Range")
        double price = cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN));

        return new Food(id, image, foodname, price);
    }

    @SuppressLint("Range")
    public void onClickPlus(int idFood) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + FOOD_TABLE + " WHERE " + ID_COLUMN + "=?",
                new String[]{String.valueOf(idFood)});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
            @SuppressLint("Range")
            Integer image = cursor.getInt(cursor.getColumnIndex(IMAGE_COLUMN));
            @SuppressLint("Range")
            String foodname = cursor.getString(cursor.getColumnIndex(FOODNAME_COLUMN));
            @SuppressLint("Range")
            double price = cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN));
            @SuppressLint("Range")
            double weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT_COLUMN));

            double newWeight = weight * 1.1;

            // Cập nhật trọng lượng mới vào cơ sở dữ liệu
            ContentValues values = new ContentValues();
            values.put(PRICE_COLUMN, newWeight);
            String whereClause = ID_COLUMN + " =?";
            String[] whereArgs = {String.valueOf(idFood)};
            database.update(FOOD_TABLE, values, whereClause, whereArgs);

            iOnChangeQuantityModel.onClickPlus(idFood);

            cursor.close();
            database.close();
        }
    }
}
