package ar.edu.davinci.a252_am_lessons;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRequest extends AsyncTask<String, Number, String> {

    final OkHttpClient client = new OkHttpClient();
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        try {
            String data = run(url);
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);
        //try {

            Gson gson = new Gson();
            RickAndMortyApi response = gson.fromJson(json, RickAndMortyApi.class);
            for (Character character: response.results) {
                Log.i("gson data", String.valueOf(character.name));
            }
    }
}
