package org.client.interfaces;

import okhttp3.ResponseBody;
import org.client.model.Doctor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface DoctorService {
    @GET("doctors")
    public Call<Map<Integer,Doctor>> getDoctors();

    @GET("doctors/{id}")
    public Call<Doctor> getDoctor(@Path("id") String id);

    @POST("doctors")
    public Call<ResponseBody> createDoctor(@Body Doctor doctor);

}
