package com.app.sampleandroidproject.ui;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.http.download.DownloadProgressListener;
import com.app.sampleandroidproject.ui.base.BaseActivity;

import java.io.File;

import butterknife.BindView;
import rx.Subscriber;

public class RetrofitDownLoadActivity extends BaseActivity {
    @BindView(R.id.progress)
    ProgressBar progress;

    @Override
    protected int getContentResource() {
        return R.layout.activity_retrofit_down_load;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        File outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), "file.apk");
        htttpRequest(AppManagers.getHttpManager().downLoadApk("http://download.fir.im/v2/app/install/5818acbcca87a836f50014af?download_token=a01301d7f6f8f4957643c3fcfe5ba6ff", outputFile,listener, new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        }));
    }

    DownloadProgressListener listener=new DownloadProgressListener() {
        @Override
        public void update(long bytesRead, long contentLength, boolean done) {
            int progre = (int) (bytesRead * 100 / contentLength);
            Log.d("download", "progre: " + progre);
            progress.setProgress(progre);
        }
    };


    @Override
    protected void stopWork() {

    }
}
