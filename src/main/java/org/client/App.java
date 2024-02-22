package org.client;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.client.interfaces.DoctorService;
import org.client.model.Doctor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        DoctorService service = retrofit.create(DoctorService.class);
        Call<Map<Integer,Doctor>> callSync = service.getDoctors();

        try {
            Response<Map<Integer,Doctor>> response = callSync.execute();
            Map<Integer,Doctor> doctor = response.body();
            System.out.println(doctor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Doctor doctor = new Doctor("Fitz Gerald", "MAV");

        Call<ResponseBody> call = service.createDoctor(doctor);

        try {
            Response<ResponseBody> response = call.execute();
            String isExecuted = response.body().string();
            System.out.println(isExecuted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        callSync = service.getDoctors();

        try {
            Response<Map<Integer,Doctor>> response = callSync.execute();
            Map<Integer, Doctor> doctors = response.body();
            System.out.println(doctors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
