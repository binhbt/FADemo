package com.example.binhbt.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.User;
import com.vn.vega.base.ui.VegaActivity;
import com.vn.vega.net.ApiService;
import com.vn.vega.net.RequestLoader;

import java.io.File;

import butterknife.Bind;

/**
 * Created by binhbt on 8/17/2016.
 */
public class CheckUpdateActivity extends VegaActivity{
    private UpdateEndPoints updateApi;
    @Bind(R.id.url) TextView txtUrl;
    @Bind(R.id.check) Button btnCheck;
    @Bind(R.id.update) Button btnUpdate;
    @Override
    protected void initView(Bundle savedInstanceState) {
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUpdate();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doUpdate();
            }
        });
        updateApi = new ApiService.Builder()
                .baseUrl(UpdateEndPoints.API_ENDPOINT)
                .logging(true)
                .build()
                .create(UpdateEndPoints.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkupdate;
    }
    private void checkUpdate(){
        new RequestLoader.Builder()
                .api(updateApi.checkVersion())
                .callback(new RequestLoader.CallBack<VersionApp>() {
                    @Override
                    public void onStart() {
                        showLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        hideLoading();
                        Toast.makeText(CheckUpdateActivity.this, "failed" +t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinish(VersionApp version) {
                        hideLoading();
                        //if ()
                        Toast.makeText(CheckUpdateActivity.this, "success", Toast.LENGTH_LONG).show();
                    }
                })
                .container(this)
                .tag("get_list")
                .build();
    }

    private void doUpdate(){
        //get destination to update file and set Uri
        //TODO: First I wanted to store my update .apk file on internal storage for my app but apparently android does not allow you to open and install
        //aplication with existing package from there. So for me, alternative solution is Download directory in external storage. If there is better
        //solution, please inform us in comment
        String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
        String fileName = "cliptv.apk";
        destination += fileName;
        final Uri uri = Uri.parse("file://" + destination);

        //Delete update file if exists
        File file = new File(destination);
        if (file.exists())
            //file.delete() - test this, I think sometimes it doesnt work
            file.delete();

        //get url of app on server
        //String url = getString(R.string.update_app_url);

        //set downloadmanager
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(UpdateEndPoints.URL_DOWNLOAD_APP));
        request.setDescription("download app");
        request.setTitle(this.getString(R.string.app_name));

        //set destination
        request.setDestinationUri(uri);

        // get download service and enqueue file
        final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        final long downloadId = manager.enqueue(request);

        //set BroadcastReceiver to install app when .apk is downloaded
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            public void onReceive(Context ctxt, Intent intent) {
                Intent install = new Intent(Intent.ACTION_VIEW);
                install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                install.setDataAndType(uri,
                        manager.getMimeTypeForDownloadedFile(downloadId));
                startActivity(install);

                unregisterReceiver(this);
                //finish();
                Toast.makeText(CheckUpdateActivity.this, "download success", Toast.LENGTH_LONG).show();
            }
        };
        //register receiver for when .apk download is compete
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
    public static void installApk(String filename){
        File file = new File(filename);
        if(file.exists()){
            try {
                String command;
                command = "adb install -r " + filename;
                Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", command });
                proc.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
