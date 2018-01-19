package edu.ohio.minuku.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

import edu.ohio.minuku.Utilities.ScheduleAndSampleManager;
import edu.ohio.minuku.config.Constants;
import edu.ohio.minuku.manager.DBManager;
import edu.ohio.minuku.model.Session;


/**
 * Created by Lawrence on 2017/6/5.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance = null;

    public static final String id = "_id";
    public static final String TAG = "DBHelper";

    public static final String home_col = "home";
    public static final String neighbor_col = "neighbor";
    public static final String outside_col = "outside";
    public static final String homeorfaraway = "homeorfaraway";
    public static final String staticornot = "staticornot";
    public static final String DEVICE = "device_id";
    public static final String USERID = "user_id";
    public static final String TIME = "time"; //timeToSQLite

//    public static final String TaskDayCount = "TaskDayCount";
//    public static final String HOUR = "hour";

    //checkFamiliarOrNot link list
    public static final String link_col = "link";
    public static final String clickornot_col = "clickornot";

    //Location and Trip
    public static final String sessionid_col = "sessionid";
    public static final String latitude_col = "latitude";
    public static final String longitude_col = "longitude";
    public static final String Accuracy_col = "Accuracy";
    public static final String Altitude_col = "Altitude";
    public static final String Speed_col = "Speed";
    public static final String Bearing_col = "Bearing";
    public static final String Provider_col = "Provider";
    public static final String IsTrip_col = "IsTrip";
    public static final String transportationMode_col = "TransportationMode";
    public static final String ongoingOrNot_col = "ongoingOrNot";

    //ActivityRecognition
    public static final String MostProbableActivity_col = "MostProbableActivity";
    public static final String ProbableActivities_col = "ProbableActivities";

    public static final String trip_col = "Trip";

    //Transportation
    public static final String confirmTransportation_col = "Transportation";

    //Trip Annotate data
    public static final String Trip_updatedStatus = "UpdatedStatus";
    public static final String Trip_startTimeSecond = "Trip_startTimeSecond";
    public static final String Trip_startTime = "Trip_startTime";
    public static final String Trip_endTime = "Trip_endTime";
    public static final String Trip_id = "Trip_id";
    public static final String activityType = "ActivityType";
    public static final String tripType = "TripType";
    public static final String preplan = "Preplan";
    public static final String ans1 = "Ques1";
    public static final String ans2 = "Ques2";
    public static final String ans3 = "Ques3";
    public static final String ans4 = "Ques4";
    public static final String ans4_1 = "Ques4_1";
    public static final String ans4_2 = "Ques4_2";
    public static final String lat = "latitude";
    public static final String lng = "longitude";

    public static JSONObject location_jsonObject;

    //ringer
    public static final String RingerMode_col = "RingerMode";
    public static final String AudioMode_col = "AudioMode";
    public static final String StreamVolumeMusic_col = "StreamVolumeMusic";
    public static final String StreamVolumeNotification_col = "StreamVolumeNotification";
    public static final String StreamVolumeRing_col = "StreamVolumeRing";
    public static final String StreamVolumeVoicecall_col = "StreamVolumeVoicecall";
    public static final String StreamVolumeSystem_col = "StreamVolumeSystem";

    //battery
    public static final String BatteryLevel_col = "BatteryLevel";
    public static final String BatteryPercentage_col = "BatteryPercentage";
    public static final String BatteryChargingState_col = "BatteryChargingState";
    public static final String isCharging_col = "isCharging";

    //connectivity
    public static final String NetworkType_col = "NetworkType";
    public static final String IsNetworkAvailable_col = "IsNetworkAvailable";
    public static final String IsConnected_col = "IsConnected";
    public static final String IsWifiAvailable_col = "IsWifiAvailable";
    public static final String IsMobileAvailable_col = "IsMobileAvailable";
    public static final String IsWifiConnected_col = "IsWifiConnected";
    public static final String IsMobileConnected_col = "IsMobileConnected";

    //telephony
    public static final String NetworkOperatorName_col = "NetworkOperatorName";
    public static final String CallState_col = "CallState";
    public static final String PhoneSignalType_col = "PhoneSignalType";
    public static final String GsmSignalStrength_col = "GsmSignalStrength";
    public static final String LTESignalStrength_col = "LTESignalStrength";
    //public static final String CdmaSignalStrength_col = "CdmaSignalStrength";
    public static final String CdmaSignalStrengthLevel_col = "CdmaSignalStrengthLevel";

    //AppUsage
    public static final String ScreenStatus_col = "ScreenStatus";
    public static final String Latest_Used_App_col = "Latest_Used_App";
    public static final String Latest_Foreground_Activity_col = "Latest_Foreground_Activity";

    //sensor
    public static final String ACCELEROMETER_col = "ACCELEROMETER";
    public static final String GYROSCOPE_col = "GYROSCOPE";
    public static final String GRAVITY_col = "GRAVITY";
    public static final String LINEAR_ACCELERATION_col = "LINEAR_ACCELERATION";
    public static final String ROTATION_VECTOR_col = "ROTATION_VECTOR";
    public static final String PROXIMITY_col = "PROXIMITY";
    public static final String MAGNETIC_FIELD_col = "MAGNETIC_FIELD";
    public static final String LIGHT_col = "LIGHT";
    public static final String PRESSURE_col = "PRESSURE";
    public static final String RELATIVE_HUMIDITY_col = "RELATIVE_HUMIDITY";
    public static final String AMBIENT_TEMPERATURE_col = "AMBIENT_TEMPERATURE";

    //session
    public static final String COL_ID = "_id";
    public static final String COL_SESSION_MODIFIED_FLAG = "session_modified_flag";
    public static final String COL_SESSION_START_TIME = "session_start_time";
    public static final String COL_SESSION_END_TIME = "session_end_time";
    public static final String COL_SESSION_ID = "session_id";
    public static final String COL_TIMESTAMP_STRING = "timestamp_string";
    public static final String COL_TIMESTAMP_LONG = "timestamp_long";
    public static final String COL_SESSION_ANNOTATION_SET = "session_annotation_set";
    public static final int COL_INDEX_SESSION_ID = 0;
    public static final int COL_INDEX_SESSION_TIMESTAMP_STRING = 1;
    public static final int COL_INDEX_SESSION_START_TIME = 2;
    public static final int COL_INDEX_SESSION_END_TIME= 3;
    public static final int COL_INDEX_SESSION_ANNOTATION_SET= 4;
    //table name
    public static final String checkFamiliarOrNot_table = "CheckFamiliarOrNot";
    public static final String checkFamiliarOrNotLinkList_table = "CheckFamiliarOrNotLinkList";
    public static final String intervalSampleLinkList_table = "intervalSampleLinkList";
    public static final String surveyLinkList_table = "SurveyLinkList";

    public static final String locationNoGoogle_table = "LocationNoGoogle";
    public static final String location_table = "Location";
    public static final String activityRecognition_table = "ActivityRecognition";
    public static final String transportationMode_table = "TransportationMode";
    public static final String annotate_table = "Annotate";
    public static final String trip_table = "Trip";
    public static final String telephony_table = "Telephony";
    public static final String ringer_table = "Ringer";
    public static final String battery_table = "Battery";
    public static final String connectivity_table = "Connectivity";
    public static final String appUsage_table = "AppUsage";
    public static final String sensor_table = "Sensor";
    public static final String accessibility_table = "Accessibility";
    public static final String session_table = "Session";
    public static final String SESSION_TABLE_NAME = "Session_Table";


    public static final String DATABASE_NAME = "MySQLite.db";
    public static int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        initiateDBManager();

    }

    public static DBHelper getInstance(Context applicationContext) {
        if (instance == null) {
            instance = new DBHelper(applicationContext);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db","oncreate");

        createSurveyLinkListTable(db);

        //deprecated but still keep for debugging
//        createIntervalSampleLinkListTable(db);
//        createCheckFamiliarOrNotLinkListTable(db);

        //basic
        createTransportationModeTable(db);
        createSessionTable(db);
        createAnnotateTable(db);
        createARTable(db);
        createLocationTable(db);
        createLocationNoGoogleTable(db);
        createCheckFamiliarOrNotTable(db);
        createTripTable(db);
        createTelephonyTable(db);
        createRingerTable(db);
        createBatteryTable(db);
        createConnectivityTable(db);
        createAppUsageTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //TODO recall it after the phone restarted.
    public void initiateDBManager() {
        DBManager.initializeInstance(this);
    }

    public void createSurveyLinkListTable(SQLiteDatabase db){
        Log.d(TAG,"create SurveyLinkList table");

        String cmd = "CREATE TABLE " +
                surveyLinkList_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                link_col+" TEXT," +
                clickornot_col+" INTEGER" +
                ");";

        db.execSQL(cmd);

    }

    public void createIntervalSampleLinkListTable(SQLiteDatabase db){
        Log.d(TAG,"create IntervalSampleLinkList table");

        String cmd = "CREATE TABLE " +
                intervalSampleLinkList_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                link_col+" TEXT," +
                clickornot_col+" INTEGER" +
                ");";

        db.execSQL(cmd);

    }

    public void createCheckFamiliarOrNotLinkListTable(SQLiteDatabase db){
        Log.d(TAG,"create CheckFamiliarOrNotLinkList table");

        String cmd = "CREATE TABLE " +
                checkFamiliarOrNotLinkList_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                link_col+" TEXT," +
                clickornot_col+" INTEGER" +
                ");";

        db.execSQL(cmd);

    }
/*
    public void createSessionTable(SQLiteDatabase db){
        Log.d(TAG,"create session table");

        String cmd = "CREATE TABLE " +
                session_table + "(" +
                id + "ID integer PRIMARY KEY AUTOINCREMENT," +
                TIME + " TEXT NOT NULL," +
                sessionid_col + " INT" +
                ");";

        db.execSQL(cmd);
    }
*/

    public void createTelephonyTable(SQLiteDatabase db){

        Log.d(TAG,"create telephony table");

        String cmd = "CREATE TABLE " +
                telephony_table + "(" +
                id + "ID integer PRIMARY KEY AUTOINCREMENT," +
                TIME + " TEXT NOT NULL," +
                NetworkOperatorName_col + " TEXT," +
                CallState_col + " INT," +
                PhoneSignalType_col + " INT," +
                GsmSignalStrength_col + " INT," +
                LTESignalStrength_col + " INT," +
                CdmaSignalStrengthLevel_col + " INT" +
                ");";

        db.execSQL(cmd);
    }

    private void createSensorTable(SQLiteDatabase db) {

        Log.d(TAG, "create sensor table");

        String cmd = "CREATE TABLE " +
                sensor_table + "(" +
                id + "ID integer PRIMARY KEY AUTOINCREMENT," +
                TIME + " TEXT NOT NULL," +
                ACCELEROMETER_col + " TEXT," +
                GYROSCOPE_col + " TEXT," +
                GRAVITY_col + " TEXT," +
                LINEAR_ACCELERATION_col + " TEXT," +
                ROTATION_VECTOR_col + " TEXT," +
                PROXIMITY_col + " TEXT," +
                MAGNETIC_FIELD_col + " TEXT," +
                LIGHT_col + " TEXT," +
                PRESSURE_col + " TEXT," +
                RELATIVE_HUMIDITY_col + " TEXT," +
                AMBIENT_TEMPERATURE_col + " TEXT" +
                ");";

        db.execSQL(cmd);
    }

    public void createTransportationModeTable(SQLiteDatabase db){
        Log.d(TAG,"create TransportationMode table");

        String cmd = "CREATE TABLE " +
                transportationMode_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                confirmTransportation_col+" TEXT" +
                ");";

        db.execSQL(cmd);

    }

    //TODO mabe need to expand
    public void createAppUsageTable(SQLiteDatabase db){
        Log.d(TAG,"create AppUsage table");

        String cmd = "CREATE TABLE " +
                appUsage_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                ScreenStatus_col+" TEXT," +
                Latest_Used_App_col+" TEXT," +
                Latest_Foreground_Activity_col+" TEXT" +
                ");";

        db.execSQL(cmd);
    }

    public void createConnectivityTable(SQLiteDatabase db){
        Log.d(TAG,"create Connectivity table");

        String cmd = "CREATE TABLE " +
                connectivity_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                NetworkType_col+" TEXT," +
                IsNetworkAvailable_col+" BOOLEAN," +
                IsConnected_col+" BOOLEAN," +
                IsWifiAvailable_col+" BOOLEAN," +
                IsMobileAvailable_col+" BOOLEAN," +
                IsWifiConnected_col+" BOOLEAN," +
                IsMobileConnected_col+" BOOLEAN" +
                ");";

        db.execSQL(cmd);
    }

    public void createBatteryTable(SQLiteDatabase db){
        Log.d(TAG,"create Battery table");

        String cmd = "CREATE TABLE " +
                battery_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                BatteryLevel_col+" INTEGER," +
                BatteryPercentage_col+" FLOAT," +
                BatteryChargingState_col+" TEXT," +
                isCharging_col+" BOOLEAN" +
                ");";

        db.execSQL(cmd);
    }

    public void createRingerTable(SQLiteDatabase db){
        Log.d(TAG,"create Ringer table");

        String cmd = "CREATE TABLE " +
                ringer_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                RingerMode_col+" TEXT," +
                AudioMode_col+" TEXT," +
                StreamVolumeMusic_col+" INTEGER," +
                StreamVolumeNotification_col+" INTEGER," +
                StreamVolumeRing_col+" INTEGER," +
                StreamVolumeVoicecall_col+" INTEGER," +
                StreamVolumeSystem_col+" INTEGER" +
                ");";

        db.execSQL(cmd);
    }

    public void createAnnotateTable(SQLiteDatabase db){
        Log.d(TAG,"create Annotate table");

        String cmd = "CREATE TABLE " +
                annotate_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
                TIME + " TEXT NOT NULL," +
                Trip_startTime+" TEXT,"+
                Trip_endTime+" TEXT,"+
                Trip_id + " TEXT, " +
//                activityType + " TEXT, " +
//                preplan + " TEXT, " +
                ans1 + " TEXT, " +
                ans2 + " TEXT, " +
                ans3 + " TEXT, " +
                ans4 + " TEXT, " +
//                ans4_1 + " TEXT, " +
//                ans4_2 + " TEXT, " +
                lat + " TEXT, " +
                lng+" TEXT, " +
//                Trip_startTimeSecond+" TEXT,"+
//                Trip_updatedStatus+" TEXT,"+
                tripType + " TEXT " +

                ");";

        db.execSQL(cmd);

    }

    public void createARTable(SQLiteDatabase db){
        Log.d(TAG,"create AR table");

        String cmd = "CREATE TABLE " +
                activityRecognition_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                MostProbableActivity_col+" TEXT," +
                ProbableActivities_col +" TEXT " +
                ");";

        db.execSQL(cmd);

    }

    public void createLocationTable(SQLiteDatabase db){
        Log.d(TAG,"create location table");

        String cmd = "CREATE TABLE " +
                location_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                latitude_col+" FLOAT,"+
                longitude_col +" FLOAT, " +
                Accuracy_col + " FLOAT, " +
                Altitude_col +" FLOAT," +
                Speed_col +" FLOAT," +
                Bearing_col +" FLOAT," +
                Provider_col +" TEXT, " +
                COL_SESSION_ID + " TEXT" +
                ");";

        db.execSQL(cmd);

    }

    public void createLocationNoGoogleTable(SQLiteDatabase db){
        Log.d(TAG,"create location NoGoogle table");

        String cmd = "CREATE TABLE " +
                locationNoGoogle_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                latitude_col+" FLOAT,"+
                longitude_col +" FLOAT, " +
                Accuracy_col + " FLOAT " +
                ");";

        db.execSQL(cmd);

    }

    public void createCheckFamiliarOrNotTable(SQLiteDatabase db){
        Log.d(TAG,"create checkFamiliarOrNot table");

        String cmd = "CREATE TABLE " +
                checkFamiliarOrNot_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                staticornot+" INTEGER,"+
                home_col +" INTEGER, " +
                neighbor_col + " INTEGER, " +
                outside_col +" INTEGER" +
                ");";

        db.execSQL(cmd);

    }

    public void createTripTable(SQLiteDatabase db){

        Log.d(TAG,"create trip table");

        String cmd = "CREATE TABLE " +
                trip_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                TaskDayCount+" TEXT NOT NULL,"+
//                HOUR+" TEXT NOT NULL,"+
                TIME + " TEXT NOT NULL," +
                sessionid_col + " TEXT," +
                latitude_col+" FLOAT,"+
                longitude_col +" FLOAT, " +
                Accuracy_col + " FLOAT, " +
                IsTrip_col + " TEXT, " +
                transportationMode_col + " TEXT, " +
                ongoingOrNot_col + " TEXT" +
                ");";

        /*String cmd = "CREATE TABLE " +
                trip_table + "(" +
                id+" INTEGER PRIMARY KEY NOT NULL, " +
//                DEVICE+" TEXT,"+
//                TIME + " TEXT NOT NULL," +
                trip_col +" TEXT " +
                ");";*/

        db.execSQL(cmd);
    }

    public void createSessionTable(SQLiteDatabase db){

        String cmd = "CREATE TABLE" + " " +
                SESSION_TABLE_NAME + " ( "+
                COL_ID + " " + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TIMESTAMP_STRING + " TEXT NOT NULL, " +
                COL_SESSION_START_TIME + " INTEGER NOT NULL, " +
                COL_SESSION_END_TIME + " INTEGER, " +
                COL_SESSION_ANNOTATION_SET + " TEXT, " +
                COL_SESSION_MODIFIED_FLAG + " INTEGER " +
                ");" ;

        db.execSQL(cmd);
    }

    public static long insertSessionTable(Session session){

        //TODO: the user should be able to specify the database because each study may have a different database.

        Log.d(TAG, "put session " + session.getId() + " to table " + SESSION_TABLE_NAME);

        long rowId=0;

        try{
            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            ContentValues values = new ContentValues();

//            values.put(COL_TASK_ID, session.getTaskId());
            values.put(COL_TIMESTAMP_STRING, ScheduleAndSampleManager.getTimeString(session.getStartTime()));
            values.put(COL_SESSION_START_TIME, session.getStartTime());

            //get row number after the insertion
            Log.d(TAG, "[testing sav and load session] Inserting session id: " + session.getId() + ": Session-" + session.getStartTime() +
                    " to the session table " + SESSION_TABLE_NAME);

            rowId = db.insert(SESSION_TABLE_NAME, null, values);


        }catch(Exception e){
            e.printStackTrace();
            rowId = -1;
        }

        DBManager.getInstance().closeDatabase();

        return rowId;
    }

    public static ArrayList<String> querySession(int sessionId){

//		Log.d(TAG, "[querySession] getsession " + sessionId);

        ArrayList<String> rows = new ArrayList<String>();

        try{

            SQLiteDatabase db = DBManager.getInstance().openDatabase();

            String sql = "SELECT *"  +" FROM " + "Session_Table" +
                    //condition with session id
                    " where " + COL_ID + " = " + sessionId + "";

//            Log.d(TAG, "[querySession] the query statement is " +sql);

            Cursor cursor = db.rawQuery(sql, null);
            int columnCount = cursor.getColumnCount();
            while(cursor.moveToNext()){
                String curRow = "";
                for (int i=0; i<columnCount; i++){
                    curRow += cursor.getString(i)+ Constants.DELIMITER;
                }
                rows.add(curRow);
            }
            cursor.close();

            DBManager.getInstance().closeDatabase();


        }catch (Exception e){

        }

        Log.d(TAG, "[querySession] the session is " +rows);

        return rows;

    }

    public static  ArrayList<String> querySessionsBetweenTimes(long startTime, long endTime) {

        ArrayList<String> rows = new ArrayList<String>();

        try{

            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            String sql = "SELECT *"  +" FROM " + SESSION_TABLE_NAME +
                    " where " + COL_SESSION_START_TIME + " > " + startTime + " and " +
                    COL_SESSION_START_TIME + " < " + endTime +
                    " order by " + COL_SESSION_START_TIME;

            // Log.d(TAG, "[querySessionsBetweenTimes] the query statement is " +sql);

            Cursor cursor = db.rawQuery(sql, null);
            int columnCount = cursor.getColumnCount();
            while(cursor.moveToNext()){
                String curRow = "";
                for (int i=0; i<columnCount; i++){
                    curRow += cursor.getString(i)+ Constants.DELIMITER;
                }
                rows.add(curRow);
            }
            cursor.close();

            DBManager.getInstance().closeDatabase();

        }catch (Exception e){

        }


        return rows;

    }


    //query task table
    public static ArrayList<String> queryModifiedSessions (){

        ArrayList<String> rows = new ArrayList<String>();

        try{

            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            String sql = "SELECT *"  +" FROM " + SESSION_TABLE_NAME + " where " +
                    COL_SESSION_MODIFIED_FLAG + " = 1";

            Cursor cursor = db.rawQuery(sql, null);
            int columnCount = cursor.getColumnCount();
            while(cursor.moveToNext()){
                String curRow = "";
                for (int i=0; i<columnCount; i++){
                    curRow += cursor.getString(i)+ Constants.DELIMITER;
                }
                rows.add(curRow);
            }
            cursor.close();

            DBManager.getInstance().closeDatabase();

        }catch (Exception e){

        }


        return rows;

    }


    //query task table
    public static ArrayList<String> querySessions (){

        ArrayList<String> rows = new ArrayList<String>();

        try{

            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            String sql = "SELECT *"  +" FROM " + DBHelper.SESSION_TABLE_NAME ;

            Cursor cursor = db.rawQuery(sql, null);
            int columnCount = cursor.getColumnCount();
            while(cursor.moveToNext()){
                String curRow = "";
                for (int i=0; i<columnCount; i++){
                    curRow += cursor.getString(i)+ Constants.DELIMITER;
                }
                rows.add(curRow);
            }
            cursor.close();

            DBManager.getInstance().closeDatabase();

        }catch (Exception e){

        }


        return rows;
    }

    //get the number of existing session
    public static long querySessionCount (){


        long count = 0;

        try{
            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            String sql = "SELECT * "  +" FROM " + SESSION_TABLE_NAME ;
            Cursor cursor = db.rawQuery(sql, null);
            count = cursor.getCount();

            cursor.close();

            DBManager.getInstance().closeDatabase();

        }catch (Exception e){

        }
        return count;

    }

    public static ArrayList<String> queryLastRecord(String table_name, int sessionId) {

        ArrayList<String> rows = new ArrayList<String>();

        try{

            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            String sql = "SELECT *"  +" FROM " + table_name  +
                    " where " + COL_SESSION_ID + " = " + sessionId +
                    " order by " + COL_ID + " DESC LIMIT 1";
            ;


            Log.d(TAG, "[queryLastRecord] the query statement is " +sql);

            //execute the query
            Cursor cursor = db.rawQuery(sql, null);
            int columnCount = cursor.getColumnCount();
            while(cursor.moveToNext()){
                String curRow = "";
                for (int i=0; i<columnCount; i++){
                    curRow += cursor.getString(i)+ Constants.DELIMITER;
                }
                Log.d(TAG, "[queryLastRecord] get result row " +curRow);

                rows.add(curRow);
            }
            cursor.close();


            DBManager.getInstance().closeDatabase();

        }catch (Exception e){

        }


        return rows;
    }

    public static ArrayList<String> queryRecordsInSession(String table_name, int sessionId, long startTime, long endTime) {

        ArrayList<String> rows = new ArrayList<String>();

        try{

            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            String sql = "SELECT *"  +" FROM " + table_name  +
                    " where " + COL_SESSION_ID + " = " + sessionId + " and " +
                    COL_TIMESTAMP_LONG + " > " + startTime + " and " +
                    COL_TIMESTAMP_LONG + " < " + endTime  +
                    " order by " + COL_TIMESTAMP_LONG;

//            Log.d(TAG, "[queryRecordsInSession][testgetdata] the query statement is " +sql);

            //execute the query
            Cursor cursor = db.rawQuery(sql, null);
            int columnCount = cursor.getColumnCount();
            while(cursor.moveToNext()){
                String curRow = "";
                for (int i=0; i<columnCount; i++){
//                    Log.d(TAG, "[queryRecordsInSession][testgetdata] column " + i + " content: " + cursor.getString(i));
                    curRow += cursor.getString(i)+ Constants.DELIMITER;

                }
//                Log.d(TAG, "[queryRecordsInSession][testgetdata] get result row " +curRow);

                rows.add(curRow);
            }
            cursor.close();

            DBManager.getInstance().closeDatabase();


        }catch (Exception e){

        }


        return rows;


    }

    public static ArrayList<String> queryRecordsInSession(String table_name, int sessionId) {

        ArrayList<String> rows = new ArrayList<String>();

        try{

            SQLiteDatabase db = DBManager.getInstance().openDatabase();
            String sql = "SELECT *"  +" FROM " + table_name  +
                    " where " + COL_SESSION_ID + " = " + sessionId +
                    " order by " + COL_TIMESTAMP_LONG;


            Log.d(TAG, "[queryRecordsInSession] the query statement is " +sql);

            //execute the query
            Cursor cursor = db.rawQuery(sql, null);
            int columnCount = cursor.getColumnCount();
            while(cursor.moveToNext()){
                String curRow = "";
                for (int i=0; i<columnCount; i++){
                    curRow += cursor.getString(i)+ Constants.DELIMITER;
                }
                Log.d(TAG, "[queryRecordsInSession] get result row " +curRow);

                rows.add(curRow);
            }
            cursor.close();

            DBManager.getInstance().closeDatabase();

        }catch (Exception e){

        }


        return rows;


    }
}
