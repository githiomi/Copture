package com.githiomi.copture.data.interfaces;

import com.githiomi.copture.data.models.Offence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("offences")
    Call<List<Offence>> getOffences();

}
