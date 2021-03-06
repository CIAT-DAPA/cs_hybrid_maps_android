package org.cgiar.ciat.hybridmapsandroid.models.points.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.cgiar.ciat.hybridmapsandroid.models.DBBase;
import org.cgiar.ciat.hybridmapsandroid.models.points.source.Points;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HSOTELO on 1/20/2016.
 */
public class PointsDB extends DBBase {

    /* Constants */
    // name of database
    private static final String DB_NAME = "points.gpkg";
    private static final int DB_VERSION = 1;

    /**
     * Method Construct
     *
     * @param context
     */
    public PointsDB(Context context) {
        super(context,DB_NAME, DB_VERSION);
    }

    /*Entities*/

    public class PointsEntity {
        public List all() {
            List entities = new LinkedList();
            String query = "SELECT fid, id, name, lat, lon FROM " + Points.TABLE_NAME;
            try {

                SQLiteDatabase db = getWritableDatabase();
                Cursor cursor = db.rawQuery(query, null);
                Points entity = null;
                if (cursor.moveToFirst()) {
                    do {
                        entity = new Points(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4));
                        entities.add(entity);
                    } while (cursor.moveToNext());
                }
                return entities;
            }catch (Exception e){
                Log.d("DB_POINTS_POINTS",e.getMessage());
                return null;
            }
        }
    }
}
