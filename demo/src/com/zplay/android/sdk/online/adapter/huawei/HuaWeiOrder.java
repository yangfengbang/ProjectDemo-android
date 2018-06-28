package com.zplay.android.sdk.online.adapter.huawei;

import com.zplay.android.sdk.online.bean.ZplayBaseOrder;

public class HuaWeiOrder implements ZplayBaseOrder{
	
	// 商品信息详情， 不能为空， 可用用来做开发者扩展参数, 开发者在这里传入什么，在交易完成后，我们服务器就会原封不动传回开发者的通知接口
	private String productDesc = "";
	
	// 游戏角色ID
	private String roleId = "";
	
	// 用户的游戏角色名字
	private String roleName = "";
	
	// 用户的游戏角色等级
	private String grade;
	
	// 单位： 元 ;设置允许充值的金额， 此为可选参数， 默认为0。如果设置了此金额不为0，则表示只允许用户按指定金额充值；如果不指定金额或指定为0，则表示用户在充值时可以自由选择或输入希望充入的金额。
	private float amount;
	
	// 回调地址， 非必填参数， 此处设置或开放平台录入， 优先取客户端设置的地址， 设置后游戏在支付完成后SDK回调充值信息到此地址，必须为带有http头的URL形式。
	private String notifyUrl = "";
	
	// 订单号
	private String orderId;
	
	// 购买数量
	private String productNumber;
	
	// 商品名称
	private String productName;
	
	private String currency;
	
	//人民币兑换比例
    private String rate;
    
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public HuaWeiOrder(String productDesc, String roleId, String roleName,
			String grade, float amount, String orderId, String productNumber, String productName, String rate, String currency) {
		super();
		this.productDesc = productDesc;
		this.roleId = roleId;
		this.roleName = roleName;
		this.grade = grade;
		this.amount = amount;
		//this.notifyUrl = notifyUrl;
		this.orderId = orderId;
		this.productNumber = productNumber;
		this.productName = productName;
		this.currency=currency;
		this.rate = rate;
	}

	public float getRate() {
		return Float.parseFloat(rate);
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	
}
