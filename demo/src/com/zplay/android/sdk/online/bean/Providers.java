package com.zplay.android.sdk.online.bean;

public enum Providers {
	// 百度
	BAIDU("com.zplay.android.sdk.online.adapter.baidu.BaiDuAdapter"),
	// 360
	SANLIULING("com.zplay.android.sdk.online.adapter.sanliuling.SanLiuLingAdapter"), 
	// UC
	UC("com.zplay.android.sdk.online.adapter.uc.UCAdapter"),
	// 当乐
	DANGLE("com.zplay.android.sdk.online.adapter.dangle.DangLeAdapter"), 
	// 豌豆荚
	WANDOUJIA("com.zplay.android.sdk.online.adapter.wdj.WdjAdapter"), 
	// 魅族
	MEIZU("com.zplay.android.sdk.online.adapter.meizu.MeiZuAdapter"),
	// 安智
	ANZHI("com.zplay.android.sdk.online.adapter.anzhi.AnZhiAdapter"),
	// 联想
	LIAINXIANG("com.zplay.android.sdk.online.adapter.lianxiang.LianXiangAdapter"),
	// 拇指玩
	MUZHIWAN("com.zplay.android.sdk.online.adapter.mzw.MzwAdapter"), 
	// HTC
	HTC("com.zplay.android.sdk.online.adapter.htc.HtcAdapter"),
	// 金立
	JINLI("com.zplay.android.sdk.online.adapter.jinli.JinLiAdapter"), 
	// 华为
	HUAWEI("com.zplay.android.sdk.online.adapter.huawei.HuaWeiAdapter"),
	// vivo
	VIVO("com.zplay.android.sdk.online.adapter.vivo.VivoAdapter"),
	// oppo
	OPPO("com.zplay.android.sdk.online.adapter.oppo.OppoAdapter"),
	// 小米
	XIAOMI("com.zplay.android.sdk.online.adapter.xiaomi.XiaoMiAdapter"),
	// 卓易市场
	
	// 4399
	SSJJ("com.zplay.android.sdk.online.adapter.ssjj.SSJJAdapter"),
	// 机锋
	
	// 木蚂蚁
	MUMAYI("com.zplay.android.sdk.online.adapter.mmy.MumayiAdapter"),
	// 搜狗
	SOUGOU("com.zplay.android.sdk.online.adapter.sougou.SouGouAdapter"),
	
	//酷狗
	KUGOU("com.zplay.android.sdk.online.adapter.kugou.KuGouAdapter")
	;
	
	private String refName;
	
	private Providers(String refName){
		this.refName = refName;
	}
	
	public String getRefName(){
		return refName;
	}
	
}
