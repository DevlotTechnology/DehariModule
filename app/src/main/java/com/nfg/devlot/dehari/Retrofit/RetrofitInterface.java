package com.nfg.devlot.dehari.Retrofit;

import android.support.annotation.Nullable;

import com.nfg.devlot.dehari.Models.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hassan on 3/26/18.
 */

public interface RetrofitInterface {

    @POST("check_phone.php")
    @FormUrlEncoded
    Call<String> checkUserMobile(@Field("phone") String phone);
}
