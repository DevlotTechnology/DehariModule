package com.nfg.devlot.dehari.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hassan on 3/24/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static int DATABASE_VERSION =1;
    private static String DATABASE_NAME ="dehari_db";

    // Table names.
    public static final String table_service_seeker = "SERVICE_SEEKER";
    public static final String table_category = "CATEGORY";
    public static final String table_services = "SERVICES";
    public static final String table_jobs = "JOBS";

    // Service seeker: column names.
    public static final String col_ss_id = "ID";
    public static final String col_ss_name = "NAME";
    public static final String col_ss_email = "EMAIL";
    public static final String col_ss_phone_number = "PHONE_NUMBER";
    public static final String col_ss_image_path = "IAMGE_PATH";

    // Category: column names.
    public static final String col_cat_id = "ID";
    public static final String col_cat_name = "NAME";

    // Services: column names.
    public static final String col_service_id = "ID";
    public static final String col_service_category_id = "CATEGORY_ID";
    public static final String col_service_name = "NAME";
    public static final String col_service_base_rate = "BASE_RATE";
    public static final String col_service_hourly_rate = "HOURLY_RATE";
    public static final String col_service_estimated_time = "ESTIMATED_TIME";

    // Jobs: column names.
    public static final String col_job_id = "ID";
    public static final String col_job_s_id = "SERVICE_ID";
    public static final String col_job_ss_id = "SERVICE_SEEKER_ID";
    public static final String col_job_sp_id = "SERVICE_PROVIDER_ID";
    public static final String col_job_start_time = "START_TIME";
    public static final String col_job_ending_time = "ENDING_TIME";
    public static final String col_job_location = "LOCATION";
    public static final String col_job_status = "STATUS";
    public static final String col_job_additional_charges = "ADDITIONAL_CHARGES";


    DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
