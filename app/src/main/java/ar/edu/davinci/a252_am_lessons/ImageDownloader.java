package ar.edu.davinci.a252_am_lessons;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        String content = strings[0];
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
