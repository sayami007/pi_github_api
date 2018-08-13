package com.planetinnovative.githubapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public String BASE_URL = "https://api.github.com/";

    TextView userName, name;
    ImageView imageView;
    ProgressBar pbar;
    EditText searchUser;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName = findViewById(R.id.username);
        name = findViewById(R.id.name);
        imageView = findViewById(R.id.gitImage);
        pbar = findViewById(R.id.pbar);

        searchUser = findViewById(R.id.searchUser);
        searchButton = findViewById(R.id.searchButton);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbar.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                GithubServie service = retrofit.create(GithubServie.class);
                service.getGithubInformation(searchUser.getText().toString().trim()).enqueue(new Callback<GithubResponseModel>() {
                    @Override
                    public void onResponse(Call<GithubResponseModel> call, Response<GithubResponseModel> response) {
                        userName.setText(response.body().login);
                        name.setText(response.body().name);
                        Picasso.with(getApplicationContext()).load(response.body().avatar_url).into(imageView);
                        pbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<GithubResponseModel> call, Throwable t) {
                        System.out.println("ERROR");
                        System.out.println(t.getMessage());
                        pbar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}
