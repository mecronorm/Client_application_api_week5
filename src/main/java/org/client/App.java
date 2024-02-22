package org.client;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.client.interfaces.DoctorService;
import org.client.model.Doctor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.print.Doc;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        DoctorService service = retrofit.create(DoctorService.class);

        while (true){
            System.out.println("Give a command (empty quits):\ndoctors: Displays all doctors\ndoctor: Displays a doctor by id\ncreate: Creates a new doctor");
            String command = scanner.nextLine();
            if (command.isEmpty()){
                break;
            }
            if (command.equals("doctors")){
                Call<Map<Integer,Doctor>> callSync = service.getDoctors();
                try {
                    Response<Map<Integer,Doctor>> response = callSync.execute();
                    Map<Integer,Doctor> doctors = response.body();
                    doctors.forEach((integer, doctor) -> System.out.println(doctor+ " | id= "+integer));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (command.equals("doctor")){
                System.out.print("Give id: ");
                String id = scanner.nextLine();

                Call<Doctor> call = service.getDoctor(id);

                try {
                    Response<Doctor> response = call.execute();
                    Doctor doctor = response.body();
                    System.out.println(doctor+" | id= "+id);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (command.equals("create")){
                System.out.print("Give full name: ");
                String name = scanner.nextLine();
                System.out.print("Give their specialization: ");
                String specialization = scanner.nextLine();

                Doctor doctor = new Doctor(name, specialization);

                Call<ResponseBody> call = service.createDoctor(doctor);

                try {
                    Response<ResponseBody> response = call.execute();
                    String isCreated = response.body().string();
                    System.out.println(isCreated);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println();
        }
    }
}
