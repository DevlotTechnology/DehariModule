package com.nfg.devlot.dehari.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hassan on 3/26/18.
 */

public class RetrofitApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getApiClient(String url)
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().
                                    baseUrl(url).
                                    addConverterFactory(GsonConverterFactory.create()).build();

        }

        return  retrofit;
    }
}
