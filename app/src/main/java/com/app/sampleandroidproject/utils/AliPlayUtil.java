package com.app.sampleandroidproject.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.app.sampleandroidproject.app.Constants;
import com.app.sampleandroidproject.beans.PayCommentBean;


public class AliPlayUtil {
	PayCommentBean notify;
	private final int SDK_PAY_FLAG = 1;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				String resultStatus = payResult.getResultStatus();
				if (resultStatus.equals("9000") || resultStatus.equals("8000")) {
					notify = new PayCommentBean(Constants.PAY_SUCCESS);
				} else {
					notify = new PayCommentBean(Constants.PAY_FALT);
				}
				BusProvider.post(notify);
				break;
			}
			default:
				break;
			}
		};
	};

	public void play(final String payInfo, final Activity context) {
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(context);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

}
