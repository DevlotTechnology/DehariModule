package com.nfg.devlot.dehari.Retrofit;

import com.nfg.devlot.dehari.Models.UserModel;
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
    Call<UserModel> checkUserMobile(@Field("phone") String phone);
}
