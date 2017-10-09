package com.example.dani.quizseries.web;

/**
 * Created by dani on 20/08/17.
 */

import com.example.dani.quizseries.models.Serie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class WebClient {
    public List<Serie> get() {
        try {



            //end of colors


            String jsonstring;
            List<Serie> respostas = new ArrayList();

            URL url = new URL("https://quizseries.herokuapp.com/series");

            URLConnection conn = url.openConnection();

            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            InputStream is = httpConn.getInputStream();

            //pega retorno e converte em String
            jsonstring = convertinputStreamToString(is);

            JSONObject json = new JSONObject(jsonstring);
            JSONArray seriesJsonArray = json.getJSONArray("series");


            int len = seriesJsonArray.length();

            for (int i = 0; i < len; i++) {

                respostas.add(new Serie(seriesJsonArray.getJSONObject(i).getString("label"),seriesJsonArray.getJSONObject(i).getString("id"), seriesJsonArray.getJSONObject(i).getString("cor"), seriesJsonArray.getJSONObject(i).getString("url_icon")));

            }

            return respostas;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertinputStreamToString(InputStream ists)
            throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}