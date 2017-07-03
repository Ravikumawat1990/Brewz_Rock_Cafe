package com.app.elixir.brewz_rock_cafe.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.elixir.brewz_rock_cafe.FoodOrdringApplication;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItems;
import com.app.elixir.brewz_rock_cafe.utils.CM;

import java.util.ArrayList;

public class Items {

    public static final String TableName = "Items";
    public static final String NMAPPERID = "nMapperID";
    public static final String NATTRIBUTEID = "nAttributeID";
    public static final String CATTRIBUTELABEL = "cAttributeLabel";
    public static final String ISMULTIPLE = "isMultiple";
    public static final String HEADER = "HEADER";
    public static final String ISREQUIRED = "isRequired";


    //private static String headerTitle1;


//    public static ArrayList<DiscloserPojo> getAllData(String headerTitle) {
//
//        SQLiteDatabase sqldb = Ayala.sqLiteDatabase;
//        ArrayList<DiscloserPojo> arrModelList = null;
//        Cursor cursor = null;
//
//        headerTitle1 = "\"" + headerTitle + "\"";
//        String Query = "SELECT * FROM " + TableName + " where HeaderTitle =" + headerTitle1;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<DiscloserPojo>();
//            do {
//                DiscloserPojo model = new DiscloserPojo();
//                model.setPdfId(cursor.getString(cursor.getColumnIndex(PDFID)));
//                model.setTitle(cursor.getString(cursor.getColumnIndex(PDFNAME)));
//                model.setDownload(cursor.getString(cursor.getColumnIndex(PDFPATH)));
//                model.setIsDownload(cursor.getString(cursor.getColumnIndex(ISDOWNLOAD)));
//                model.setIsViewed(cursor.getString(cursor.getColumnIndex(ISVIEWED)));
//                model.setHeaderTitle(cursor.getString(cursor.getColumnIndex(HEADERTITLE)));
//                model.setDownloadStatus(cursor.getString(cursor.getColumnIndex(DOWNLOADSTATUS)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }


//    public static ArrayList<DiscloserPojo> getDistintTitleData() {
//        SQLiteDatabase sqldb = Ayala.sqLiteDatabase;
//        ArrayList<DiscloserPojo> arrModelList = null;
//        Cursor cursor = null;
//        String Query = "SELECT DISTINCT " + HEADERTITLE + "  FROM " + TableName;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<DiscloserPojo>();
//            do {
//                DiscloserPojo model = new DiscloserPojo();
//                model.setHeaderTitle((cursor.getString(cursor.getColumnIndex(HEADERTITLE))));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }

//
//    public static ArrayList<PojoItems> getAllDownloadData() {
//        String download = "1";
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItems> arrModelList = null;
//        Cursor cursor = null;
//        String Query = "SELECT * FROM " + TableName + " where " + ISDOWNLOAD + " = " + download;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<DiscloserPojo>();
//            do {
//                DiscloserPojo model = new DiscloserPojo();
//                model.setPdfId(cursor.getString(cursor.getColumnIndex(PDFID)));
//                model.setTitle(cursor.getString(cursor.getColumnIndex(PDFNAME)));
//                model.setDownload(cursor.getString(cursor.getColumnIndex(PDFPATH)));
//                model.setIsDownload(cursor.getString(cursor.getColumnIndex(ISDOWNLOAD)));
//                model.setIsViewed(cursor.getString(cursor.getColumnIndex(ISVIEWED)));
//                model.setHeaderTitle(cursor.getString(cursor.getColumnIndex(HEADERTITLE)));
//                model.setDownloadStatus(cursor.getString(cursor.getColumnIndex(DOWNLOADSTATUS)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }


    public static ArrayList<PojoItems> getAlldata() {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItems> arrModelList = null;
        String bool = "\"" + "true" + "\"";
        Cursor cursor = null;
        String Query = "Select * from " + TableName + " where " + ISREQUIRED + " =" + bool;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItems>();
            do {
                PojoItems model = new PojoItems();
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setIsMultiple(cursor.getString(cursor.getColumnIndex(ISMULTIPLE)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                //model.setHedaer(cursor.getString(cursor.getColumnIndex(HEADER)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


    public static ArrayList<PojoItems> getAlldataUsingMapperId(String id) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItems> arrModelList = null;
        Cursor cursor = null;
        String Query = "Select * from " + TableName + " where " + NMAPPERID + " =" + id;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItems>();
            do {
                PojoItems model = new PojoItems();
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setIsMultiple(cursor.getString(cursor.getColumnIndex(ISMULTIPLE)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                //model.setHedaer(cursor.getString(cursor.getColumnIndex(HEADER)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }

    public static ArrayList<PojoItems> getAlldataNotReruird() {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItems> arrModelList = null;
        Cursor cursor = null;
        String bool = "\"" + "false" + "\"";
        boolean b = false;
        String Query = "Select * from " + TableName + " where " + ISREQUIRED + " =" + bool;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItems>();
            do {
                PojoItems model = new PojoItems();
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setIsMultiple(cursor.getString(cursor.getColumnIndex(ISMULTIPLE)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                //model.setHedaer(cursor.getString(cursor.getColumnIndex(HEADER)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


//    public static ArrayList<PojoItems> getSelectedIdRecord(String pdfId) {
//        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
//        ArrayList<PojoItems> arrModelList = null;
//        Cursor cursor = null;
//        //String Query = "Select * from " + TableName;
//        String Query = "Select * from " + TableName + " where " + NMAPPERID + " = " + pdfId;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            arrModelList = new ArrayList<PojoItems>();
//            do {
//                PojoItems model = new PojoItems();
//                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
//                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
//                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
//                model.setIsMultiple(cursor.getString(cursor.getColumnIndex(ISMULTIPLE)));
//                arrModelList.add(model);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }//end if(cursor!=null)
//        return arrModelList;
//    }
    /*
    * get single record
    * */


//    public static Model_ClubDetails SelectSingleRecord() {
//        SQLiteDatabase sqldb = KarnavatiApp.sqLiteDatabase;
//        Model_ClubDetails model = null;
//        Cursor cursor = null;
//        String Query = "Select * from " + TableName;
//        cursor = sqldb.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            model = new Model_ClubDetails();
//            model.Id = (cursor.getInt(cursor.getColumnIndex(ID)));
//            model.ClubName = (cursor.getString(cursor.getColumnIndex(CLUBNAME)));
//            model.ClubAddress = (cursor.getString(cursor.getColumnIndex(CLUBADDRESS)));
//            model.ClubPhone = (cursor.getString(cursor.getColumnIndex(CLUBPHONE)));
//            model.WebSite = (cursor.getString(cursor.getColumnIndex(WEBSITE)));
//            model.LatLong = (cursor.getString(cursor.getColumnIndex(LATLONG)));
//            model.EmailId = (cursor.getString(cursor.getColumnIndex(EMAILID)));
//            model.CreatedDate = (cursor.getString(cursor.getColumnIndex(CREATEDDATE)));
//            model.UpdatedDate = (cursor.getString(cursor.getColumnIndex(UPDATEDDATE)));
//            cursor.close();
//        }//end if(cursor!=null)
//        return model;
//    }


    public static void Insert(ArrayList<PojoItems> arrListModel) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        sqldb.beginTransaction();


        for (PojoItems model : arrListModel) {
            ContentValues values = new ContentValues();
            values.put(NMAPPERID, model.getnMapperID());
            values.put(NATTRIBUTEID, model.getnAttributeID());
            values.put(CATTRIBUTELABEL, model.getcAttributeLabel());
            values.put(ISMULTIPLE, model.getIsMultiple());
            values.put(ISREQUIRED, model.getIsRequired());


            //  values.put(HEADER, model.getHedaer());
            if (CM.CheckIsDataAlreadyInDBorNot(TableName, NMAPPERID, model.getnMapperID())) {
                try {
                    sqldb.update(TableName, values, NMAPPERID + "=" + model.getnMapperID(), null);
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                try {
                    sqldb.insert(TableName, null, values);
                } catch (Exception e) {
                    e.getMessage();
                }

            }


        }
        sqldb.setTransactionSuccessful();
        sqldb.endTransaction();
    }//End insert method

    public static int deleteAll() {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        int row = 0;
        try {
            row = sqldb.delete(TableName, null, null);
        } catch (Exception e) {
            e.getMessage();
        }
        return row;
    }
//
//    public static void updateAllKey() {
//        String isView = "1";
//        String view = "0";
//        SQLiteDatabase sqldb = Ayala.sqLiteDatabase;
//        String selectQuery = "UPDATE " + TableName + " SET " + ISVIEWED
//                + " = '" + isView + "'";
//        try {
//            sqldb.execSQL(selectQuery);
//
//        } catch (Exception e) {
//
//            e.getStackTrace();
//        }
//
//
//    }
//
//    public static void updateDonloadKey(String id, String downloadValue) {
//        SQLiteDatabase sqldb = Ayala.sqLiteDatabase;
//        String selectQuery = "UPDATE " + TableName + " SET " + ISDOWNLOAD
//                + " = '" + downloadValue + "' WHERE "
//                + PDFID + " = " + id;
//        try {
//            sqldb.execSQL(selectQuery);
//
//        } catch (Exception e) {
//
//            e.getStackTrace();
//        }
//
//
//    }
//
//    public static void updateDonloadStatus(String id, String status) {
//        SQLiteDatabase sqldb = Ayala.sqLiteDatabase;
//        String selectQuery = "UPDATE " + TableName + " SET " + DOWNLOADSTATUS
//                + " = '" + status + "' WHERE "
//                + PDFID + " = " + id;
//        try {
//            sqldb.execSQL(selectQuery);
//
//        } catch (Exception e) {
//
//            e.getStackTrace();
//        }
//
//
//    }

}