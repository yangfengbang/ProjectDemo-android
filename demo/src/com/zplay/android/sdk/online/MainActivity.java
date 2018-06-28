package com.zplay.android.sdk.online;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.zplay.android.sdk.online.bean.Providers;
import com.zplay.android.sdk.online.bean.ResultCode;
import com.zplay.android.sdk.online.bean.ZplayBaseLoginBean;
import com.zplay.android.sdk.online.listener.ZplayOnlineExitListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLoginListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLogoutListener;
import com.zplay.android.sdk.online.listener.ZplayOnlinePayResultListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineSwitchListener;
import com.zplay.android.sdk.online.utils.LogUtils;
import net.mobigame.zombietsunami.huawei.R;

public class MainActivity extends Activity implements OnClickListener {

	private Button init, login, logout, pay, release, switchUser, feedback;
	private Button visitorLogin;

	private static final String TAG = "MainActivity";
	private ZplayOnlineLoginListener logListener;
	private ZplayOnlinePayResultListener payListener;
	private ZplayOnlineExitListener exitListener;

	private Button center;
	private Button quit;
	private ZplayOnlineLogoutListener logoutListener;
	private ZplayOnlineSwitchListener switchListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		logListener = new ZplayOnlineLoginListener() {
			@Override
			public void loginResult(boolean success,
					ZplayBaseLoginBean loginUser, ResultCode error,
					String errorMsg) {
				Log.d(TAG, "--" + error + "," + errorMsg);
				if (ResultCode.LOGIN_SUCCESS == error) {
					ZplayLoginBean user = (ZplayLoginBean) loginUser;
					Log.d(TAG, "--zplayToken：" + user.getZplayToken()
							+ ", uid:" + user.getLoginUid());
					Toast.makeText(
							getApplicationContext(),
							"uid=" + user.getLoginUid() + "zplayToken:"
									+ user.getZplayToken(), 0).show();

					ZplayAgentOnline.onZplaySdkSubmitExtendData("10", "我是玩家名称",
							"100", "20", "像素飞机大战", "");
				}
			}
		};
		payListener = new ZplayOnlinePayResultListener() {

			@Override
			public void onPaySuccess(String orderId, String zplayOrderId,
					String amount) {
				LogUtils.d(TAG, "支付成功" + orderId + ",金钱" + amount);
				Toast.makeText(MainActivity.this,
						"支付成功" + orderId + ",金钱" + amount+",zplayOrderId"+zplayOrderId, Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public void onPayFailed(String orderId, String zplayOrderId,
					String amount, ResultCode error, String errorMsg) {
				LogUtils.d(TAG, "支付失败" + orderId + ",金钱" + amount + "状态码:"
						+ error + "错误消息:" + errorMsg);
				Toast.makeText(
						MainActivity.this,
						"支付失败" + orderId + ",金钱" + amount + "状态码:" + error
								+ "错误消息:" + errorMsg, Toast.LENGTH_LONG).show();
			}

		};

		logoutListener = new ZplayOnlineLogoutListener() {

			@Override
			public void onLogoutSuccess() {
				Toast.makeText(MainActivity.this, "注销成功", Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public void onLogoutError(String msg) {
			}
		};

		exitListener = new ZplayOnlineExitListener() {

			@Override
			public void exitPlay(String data) {
				Toast.makeText(getApplicationContext(), "退出游戏", 0).show();
				finish();
			}

			@Override
			public void continuePlay(String data) {

			}
		};

		switchListener = new ZplayOnlineSwitchListener() {

			@Override
			public void onSwitchSuccess(ZplayBaseLoginBean loginUser,
					String errorMsg) {
				Toast.makeText(MainActivity.this,
						"uid=" + loginUser + ";errormasg" + errorMsg,
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSwitchFail(String errorMsg) {
				Toast.makeText(MainActivity.this, "errormasg" + errorMsg,
						Toast.LENGTH_LONG).show();
			}
		};

		ZplayAgentOnline.setZplaySdkKey("100013013");
		ZplayAgentOnline.initSDK(Providers.HUAWEI, this, logListener,
				payListener, exitListener, logoutListener, switchListener);

	}

	private void initView() {
		init = (Button) findViewById(R.id.init);
		login = (Button) findViewById(R.id.login);
		logout = (Button) findViewById(R.id.logout);
		pay = (Button) findViewById(R.id.pay);
		release = (Button) findViewById(R.id.release);
		center = (Button) findViewById(R.id.userCenter);
		quit = (Button) findViewById(R.id.quit);
		switchUser = (Button) findViewById(R.id.switchUser);
		feedback = (Button) findViewById(R.id.feedback);

		init.setOnClickListener(this);
		login.setOnClickListener(this);
		logout.setOnClickListener(this);
		pay.setOnClickListener(this);
		release.setOnClickListener(this);
		center.setOnClickListener(this);
		quit.setOnClickListener(this);
		switchUser.setOnClickListener(this);
		feedback.setOnClickListener(this);
		visitorLogin = (Button) findViewById(R.id.visitorLogin);
		visitorLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.init:
			break;
		case R.id.login:
			ZplayAgentOnline.login(MainActivity.this);
			break;
		case R.id.logout:
			ZplayAgentOnline.logout(MainActivity.this);
			break;
		case R.id.release:
			break;
		case R.id.pay:
			/**
			 * 支付接口
			 * 
			 * @param activity
			 * @param orderId
			 *            订单号
			 * @param currency
			 *            游戏货币名字(搜狗必填)
			 * @param rate
			 *            人民币兑换比例(搜狗必填)
			 * @param productName
			 *            购买商品名字(搜狗必填)
			 * @param amount
			 *            充值金额(搜狗必填),单位分
			 * @param unitPrice
			 *            商品单价,单位分
			 * @param productNumber
			 *            购买数量（必传）
			 * @param roleId
			 *            角色ID
			 * @param roleName
			 *            用户的游戏角色名字， (此为必选参数)
			 * @param grade
			 *            用户的游戏角色等级(此为可选参数)
			 * @param body
			 *            商品描述
			 * @param productId
			 *            游戏道具Id
			 * @param productSubject
			 *            订单标题,格式为： ”购买 N 枚金币
			 * @param product_unit
			 *            游戏道具的单位，默认值： ””
			 * @param gameAbbreviation
			 *            游戏简称
			 */
			ZplayAgentOnline.doPay(MainActivity.this,
					System.currentTimeMillis() + "", "元宝", "1", "宝刀", "1", "1",
					"1", "555", "nihao", "56", "号令天下", "456", "购买 N 枚金币", "",
					"", "", "", "", "zombie");
			break;
		case R.id.userCenter:
			ZplayAgentOnline.userCenter(MainActivity.this);

			break;

		case R.id.quit:
			ZplayAgentOnline.onQuit(MainActivity.this);

			break;

		case R.id.switchUser:
			ZplayAgentOnline.onZplaySwitchUser(MainActivity.this);

			break;

		case R.id.feedback:
			ZplayAgentOnline.onZplayUserFeedback(MainActivity.this, "牛逼的游戏",
					"这款游戏为何如此牛逼,小伙伴们都惊呆了");
			break;
		case R.id.visitorLogin:
			ZplayAgentOnline.visitorLogin(MainActivity.this);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		ZplayAgentOnline.onActivityResult(getApplicationContext(), requestCode,
				resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onPause() {
		super.onPause();
		ZplayAgentOnline.onZplayPause(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		ZplayAgentOnline.onZplayResume(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (isTaskRoot()) {
			ZplayAgentOnline.onZplayDestroy(this);
		}
	}

}
