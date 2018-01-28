package com.gamesmantra.flatbuffer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gamesmantra.flatbuffer.flat.ReposList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Flat_Buffs";
    private TextView mTvFlatStats;
    private TextView mTvJsonStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvFlatStats = findViewById(R.id.tv_Flat_buffer_time);
        mTvJsonStats = findViewById(R.id.tv_json_time);
    }

    public void loadFromFlatBuffer(View view) {
        byte[] buffer = readRawResource(getApplication(), R.raw.repos_flatbuff);
        long startTime = System.currentTimeMillis();
        ByteBuffer bb = ByteBuffer.wrap(buffer);
        ReposList reposList = ReposList.getRootAsReposList(bb);
        long timeTaken = System.currentTimeMillis() - startTime;
        String logText = "FlatBuffer : " + timeTaken + "ms";
        mTvFlatStats.setText(logText);
        Log.d(TAG, "loadFromFlatBuffer " + logText);
    }

    public void loadFromJson(View view) {
        String jsonText = new String(readRawResource(getApplication(), R.raw.repos_json));
        long startTime = System.currentTimeMillis();
        ReposList reposList = new Gson().fromJson(jsonText, ReposList.class);
        long timeTaken = System.currentTimeMillis() - startTime;
        String logText = "Json : " + timeTaken + "ms";
        mTvJsonStats.setText(logText);
        Log.d(TAG, "loadFromJson " + logText);
    }

    private byte[] readRawResource(Context context, int resId) {
        InputStream stream = null;
        byte[] buffer = null;
        try {
            stream = context.getResources().openRawResource(resId);
            buffer = new byte[stream.available()];
            while (stream.read(buffer) != -1) ;
        } catch (IOException ignored) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return buffer;
    }
}
