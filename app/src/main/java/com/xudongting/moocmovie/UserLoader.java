package com.xudongting.moocmovie;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xudongting.moocmovie.entity.Movice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by xudongting on 2018/4/19.
 */

public class UserLoader extends AsyncTaskLoader<ArrayList<Movice>> {
    private ArrayList<Movice> movices;
    private static final String TAG = "ddd";

    public UserLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(isStarted()){
            forceLoad();
        }
    }

    @Override
    public ArrayList<Movice> loadInBackground() {
        try {
            URL url = new URL("http://www.imooc.com/api/movie");
            HttpURLConnection coon = (HttpURLConnection) url.openConnection();
            coon.setRequestMethod("GET");
            coon.setReadTimeout(6000);
            if (coon.getResponseCode() == 200) {
                InputStream in = coon.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int length = 0;
                while ((length = in.read(b)) > -1) {
                    baos.write(b, 0, length);
                }
                String msg = baos.toString();
                JSONObject obj = new JSONObject(msg);
                String data = obj.getString("movies");
                Log.d(TAG, "run: "+data);
                Gson gson=new Gson();
                movices = gson.fromJson(data,new TypeToken<ArrayList<Movice>>(){}.getType());
                Log.d(TAG, "run: "+ movices);
                in.close();
                baos.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movices;
    }


}
