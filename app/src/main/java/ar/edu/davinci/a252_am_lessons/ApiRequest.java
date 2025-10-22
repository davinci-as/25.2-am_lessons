package ar.edu.davinci.a252_am_lessons;

import android.os.AsyncTask;
import android.util.Log;

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
        try {
            JSONObject response = new JSONObject(json);
            JSONObject info = response.getJSONObject("info");
            JSONArray results = response.getJSONArray("results");
            JSONObject albertEinstein = (JSONObject) results.get(10);
            String imageAE = albertEinstein.getString("image");
            Log.i("json-array", results.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
