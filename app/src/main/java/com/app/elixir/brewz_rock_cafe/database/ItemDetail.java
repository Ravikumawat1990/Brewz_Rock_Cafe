package com.app.elixir.brewz_rock_cafe.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.elixir.brewz_rock_cafe.FoodOrdringApplication;
import com.app.elixir.brewz_rock_cafe.pojo.PojoItems;

import java.util.ArrayList;

import com.app.elixir.brewz_rock_cafe.pojo.PojoItemDetail;
import com.app.elixir.brewz_rock_cafe.utils.CM;

public class ItemDetail {

    public static final String TableName = "ItemDetail";
    public static final String NATTRIBUTEID = "nAttributeID";
    public static final String CATTRIBUTELABEL = "cAttributeLabel";
    public static final String NMAPPERDETAILID = "nMapperDetailID";


    public static final String NMAPPERID = "nMapperID";
    public static final String NATTRIBUTEVALUEMASTERID = "nAttributeValueMasterID";
    public static final String CATTRIBUTEVALUELABEL = "cAttributeValueLabel";

    public static final String NPRICE = "nPrice";
    public static final String ISCHECKED = "isChecked";

    public static final String ISMULTI = "isMulti";

    public static final String HEADER = "header";
    public static final String ISREQUIRED = "isRequired";


    private static String headerTitle1;


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


    public static ArrayList<PojoItemDetail> getAllData() {
        String download = "1";
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItemDetail> arrModelList = null;
        Cursor cursor = null;
        String Query = "SELECT * FROM " + TableName;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItemDetail>();
            do {
                PojoItemDetail model = new PojoItemDetail();
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


    public static ArrayList<PojoItemDetail> getSelectedIdRecord(String pdfId) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItemDetail> arrModelList = null;
        Cursor cursor = null;
        //String Query = "Select * from " + TableName;
        String Query = "Select * from " + TableName + " where " + NMAPPERID + " = " + pdfId;
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItemDetail>();
            do {
                PojoItemDetail model = new PojoItemDetail();
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


    public static ArrayList<PojoItemDetail> getSelectedate(String name) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItemDetail> arrModelList = null;
        Cursor cursor = null;
        //String Query = "Select * from " + TableName;
        headerTitle1 = "\"" + name + "\"";
        String Query = "Select * from " + TableName + " where " + HEADER + " = " + headerTitle1 + " and isChecked = " + "1";
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItemDetail>();
            do {
                PojoItemDetail model = new PojoItemDetail();
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }
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


    public static ArrayList<PojoItemDetail> getUniqueName() {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItemDetail> arrModelList = null;
        Cursor cursor = null;
        String Query = "SELECT DISTINCT " + HEADER + " from " + TableName + " Where isChecked = " + "1";
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItemDetail>();
            do {
                PojoItemDetail model = new PojoItemDetail();
                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


    public static ArrayList<PojoItemDetail> getAllCheckedRecord() {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItemDetail> arrModelList = null;
        Cursor cursor = null;
        //String Query = "Select * from " + TableName;
        String Query = "SELECT * FROM " + TableName + " where isChecked = " + "1";
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItemDetail>();
            do {
                PojoItemDetail model = new PojoItemDetail();
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }

    public static String getAllCheckeditemRecord() {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItemDetail> arrModelList = null;
        Cursor cursor = null;
        String total = null;
        String Query = "SELECT SUM(nPrice) FROM " + TableName + " where isChecked = " + "1";

        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                total = String.valueOf(cursor.getInt(0));
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return total;
    }

    public static ArrayList<PojoItems> getAlldataNotReruirdwithAddOnRequird() {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItems> arrModelList = null;
        Cursor cursor = null;
        String bool = "\"" + "true" + "\"";
        boolean b = false;
        String Query = "Select * from " + TableName + " where " + ISREQUIRED + " =" + bool + " and " + ISCHECKED + "= 1";
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItems>();
            do {
                PojoItems model = new PojoItems();
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }


    public static void Insert(ArrayList<PojoItemDetail> arrListModel) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        sqldb.beginTransaction();


        for (PojoItemDetail model : arrListModel) {
            ContentValues values = new ContentValues();
            values.put(NATTRIBUTEID, model.getnAttributeID());
            values.put(CATTRIBUTELABEL, model.getcAttributeLabel());
            values.put(NMAPPERDETAILID, model.getnMapperDetailID());
            values.put(NMAPPERID, model.getnMapperID());
            values.put(NATTRIBUTEVALUEMASTERID, model.getnAttributeValueMasterID());
            values.put(CATTRIBUTEVALUELABEL, model.getcAttributeValueLabel());
            values.put(NPRICE, model.getnPrice());
            values.put(ISCHECKED, model.getIsChecked());
            values.put(ISMULTI, model.getIsMulti());
            values.put(HEADER, model.getHeader());
            values.put(ISREQUIRED, model.getIsRequired());
            if (CM.CheckIsDataAlreadyInDBorNot(TableName, NMAPPERDETAILID, model.getnMapperDetailID())) {
                try {
                    sqldb.update(TableName, values, NMAPPERDETAILID + "=" + model.getnMapperDetailID(), null);
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

    public static void updateCheckBox(String isCheck, String nMapperDetailID, String nMapperID) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        String selectQuery = "UPDATE " + TableName + " SET " + ISCHECKED
                + " = " + isCheck + " WHERE "
                + NMAPPERID + " = " + nMapperID + " AND " + NMAPPERDETAILID + "=" + nMapperDetailID;
        try {
            sqldb.execSQL(selectQuery);

        } catch (Exception e) {

            e.getStackTrace();
        }


    }

    public static void setUncheckedRadioButton(String isCheck, String nMapperDetailID, String nMapperID) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        String selectQuery = "UPDATE " + TableName + " SET " + ISCHECKED
                + " = " + 0 + " WHERE "
                + NMAPPERID + " = " + nMapperID;
        try {
            sqldb.execSQL(selectQuery);

        } catch (Exception e) {

            e.getStackTrace();
        }


    }


    public static void updateRadioButton(String isCheck, String nMapperDetailID, String nMapperID) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        String selectQuery = "UPDATE " + TableName + " SET " + ISCHECKED
                + " = " + isCheck + " WHERE "
                + NMAPPERID + " = " + nMapperID + " AND " + NMAPPERDETAILID + "=" + nMapperDetailID;
        try {
            sqldb.execSQL(selectQuery);

        } catch (Exception e) {

            e.getStackTrace();
        }


    }

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
    public static ArrayList<PojoItemDetail> getAlldataUsingMapperId(String id) {
        SQLiteDatabase sqldb = FoodOrdringApplication.sqLiteDatabase;
        ArrayList<PojoItemDetail> arrModelList = null;
        Cursor cursor = null;
        //String Query = "Select * from " + TableName;
        String Query = "Select * from " + TableName + " where " + NMAPPERID + " =" + id + " and isChecked = 1 ";
        cursor = sqldb.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            arrModelList = new ArrayList<PojoItemDetail>();
            do {
                PojoItemDetail model = new PojoItemDetail();
                model.setnAttributeID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEID)));
                model.setcAttributeLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTELABEL)));
                model.setnMapperDetailID(cursor.getString(cursor.getColumnIndex(NMAPPERDETAILID)));
                model.setnMapperID(cursor.getString(cursor.getColumnIndex(NMAPPERID)));
                model.setnAttributeValueMasterID(cursor.getString(cursor.getColumnIndex(NATTRIBUTEVALUEMASTERID)));
                model.setcAttributeValueLabel(cursor.getString(cursor.getColumnIndex(CATTRIBUTEVALUELABEL)));
                model.setnPrice(cursor.getString(cursor.getColumnIndex(NPRICE)));
                model.setIsChecked(cursor.getString(cursor.getColumnIndex(ISCHECKED)));
                model.setIsMulti(cursor.getString(cursor.getColumnIndex(ISMULTI)));
                model.setHeader(cursor.getString(cursor.getColumnIndex(HEADER)));
                model.setIsRequired(cursor.getString(cursor.getColumnIndex(ISREQUIRED)));
                arrModelList.add(model);
            } while (cursor.moveToNext());
            cursor.close();
        }//end if(cursor!=null)
        return arrModelList;
    }
}