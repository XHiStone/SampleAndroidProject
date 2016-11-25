package com.app.sampleandroidproject.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.service.IBaseAidlInterface;
import com.app.sampleandroidproject.service.IBaseService;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;

public class AidlActivity extends BaseActivity implements ServiceConnection {

    @BindView(R.id.et_num1)
    EditText etNum1;
    @BindView(R.id.et_num2)
    EditText etNum2;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.btn_total)
    Button btnTotal;
    private IBaseAidlInterface stub;

    @Override
    protected int getContentResource() {
        return R.layout.activity_aidl;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("Aidl");
        bindService(new Intent(this, IBaseService.class), this, BIND_AUTO_CREATE);
        RxTextView.textChanges(etNum1)
                .subscribe(charSequence -> {
                    checkInput(charSequence.toString(),
                            etNum2.getText().toString());
                });

        RxTextView.textChanges(etNum2)
                .subscribe(charSequence -> {
                    checkInput(etNum1.getText().toString(),
                            charSequence.toString());
                });

        RxView.clicks(btnTotal).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    try {
                        if (stub != null)
                            tvTotal.setText("" + stub.add(Integer.parseInt(etNum1.getText().toString()),
                                    Integer.parseInt(etNum2.getText().toString())));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void checkInput(String s, String s1) {
        Observable.just(s, s1).map(s2 -> {
            if (!TextUtils.isEmpty(s2))
                return true;
            return false;
        }).subscribe(aBoolean -> {
            btnTotal.setEnabled(aBoolean);
        });
    }

    @Override
    protected void stopWork() {
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        //实现接口
        stub = IBaseAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        //滞空接口，防止内存溢出
        stub = null;
    }
}
