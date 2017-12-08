package com.example.grace.eatme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grace on 12/3/2017.
 */

public class CartDatabase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CartDatabase";
    private static final String TABLE_NAME= "OrderDetails";
    private static final String COL1 ="ProductId";
    private static final String COL2 = "ProductName";
    private static final String COL3 = "Quantity";
    private static final String COL4= "Price";
    private static final String COL5 = "Discount";


    public CartDatabase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable= "CREATE TABLE "+ TABLE_NAME + " ( "+ COL1 + " VARCHAR PRIMARY KEY," +COL2 + " TEXT,"+COL3 + " TEXT," +COL4 + " TEXT,"+ COL5 + " TEXT " + " )";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public List<Order>getCart()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {COL1,"ProductName","Quantity","Price","Discount"};
        String sqlTable = TABLE_NAME;

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);





        final List<Order> result = new ArrayList<>();
        if (c.moveToFirst())
        {
            do
                {
                    result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                            c.getString(c.getColumnIndex("ProductName")),
                            c.getString(c.getColumnIndex("Quantity")),
                            c.getString(c.getColumnIndex("Price")),
                            c.getString(c.getColumnIndex("Discount"))
                    ));
                }while (c.moveToNext());
        }
        return result;


    }

    public void addToCart(Order order)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1,order.getProductId());
        values.put(COL2,order.getProductName());
        values.put(COL3,order.getQuantity());
        values.put(COL4,order.getPrice());
        values.put(COL5,order.getDiscount());


        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public int clearCart(String productId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ProductId",new String[]{(productId)});


    }



//    private static final String DB_NAME = "android.db";
//    private static  final int DB_VER=1;
//
//
//    public CartDatabase(Context context) {
//        super(context, DB_NAME, null, DB_VER);
//
//    }
//
//
//    public List<Order> getCart()
//    {
//        SQLiteDatabase db = getReadableDatabase();
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//
//        String [] sqlSelect = {"ProductId","ProductName","Quantity","Price","Discount"};
//        String sqlTable = "OrderDetail";
//
//        qb.setTables(sqlTable);
//        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);
//
//        final List<Order> result = new ArrayList<>();
//        if (c.moveToFirst())
//        {
//            do
//                {
//                    result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
//                            c.getString(c.getColumnIndex("ProductName")),
//                            c.getString(c.getColumnIndex("Quantity")),
//                            c.getString(c.getColumnIndex("Price")),
//                            c.getString(c.getColumnIndex("Discount"))
//                    ));
//                }while (c.moveToNext());
//        }
//        return result;
//    }
//
//    public void addToCart(Order order)
//    {
//        SQLiteDatabase db = getReadableDatabase();
//        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount)VALUES('%s','%s','%s','%s','%s')",
//                order.getProductId(),
//                order.getProductName(),
//                order.getQuantity(),
//                order.getPrice(),
//                order.getDiscount()
//                );
//        db.execSQL(query);
//
//    }
//
//    public void clearCart()
//    {
//        SQLiteDatabase db = getReadableDatabase();
//        String query = String.format("DELETE FROM OrderDetail");
//
//        db.execSQL(query);
//
//    }
}
