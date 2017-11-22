package com.ao.frozens.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.ao.frozens.app.Frozen;
import com.ao.frozens.net.callback.IRequest;
import com.ao.frozens.net.callback.ISuccess;
import com.ao.frozens.utils.file.FileUtils;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * com.ao.frozens.net.download
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {


    private final IRequest IREQUSET;
    private final ISuccess ISUCCESS;

    public SaveFileTask(IRequest iRequest, ISuccess iSuccess) {
        this.IREQUSET = iRequest;
        this.ISUCCESS = iSuccess;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected File doInBackground(Object... objects) {

        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody responseBody = (ResponseBody) objects[2];
        final String name = (String) objects[3];
        final InputStream inputStream = responseBody.byteStream();

        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "default";
        }
        if (name == null) {
            return FileUtils.writeToDisk(inputStream, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtils.writeToDisk(inputStream, downloadDir, name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (ISUCCESS != null) {
            ISUCCESS.onSuccess(file.getPath());
        }
        if (IREQUSET != null) {
            IREQUSET.onRequsetEnd();
        }
        autoInstalAPK(file);
    }


    private void autoInstalAPK(File file){

        if (FileUtils.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Frozen.getApplication().startActivity(install);
        }
    }
}

