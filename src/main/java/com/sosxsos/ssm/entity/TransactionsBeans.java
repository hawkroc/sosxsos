package com.sosxsos.ssm.entity;

import com.sosxsos.ssm.util.JacksonUtil;

public class TransactionsBeans {
	// {
	// "status": 0,
	// "prev_status": 0,
	// "time_remaining": 0,
	// "zoned_time": "string",
	// "threading": {
	// "time": "string",
	// "place": "string"
	// },
	// "ended_time": "string"
	// }
	//




	

	public long getBanana_id() {
		return banana_id;
	}

	public void setBanana_id(long banana_id) {
		this.banana_id = banana_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPrev_status() {
		return prev_status;
	}

	public void setPrev_status(int prev_status) {
		this.prev_status = prev_status;
	}

	public String getTime_remaining() {
		return time_remaining;
	}

	public void setTime_remaining(String time_remaining) {
		this.time_remaining = time_remaining;
	}

	public String getZoned_time() {
		return zoned_time;
	}

	public void setZoned_time(String zoned_time) {
		this.zoned_time = zoned_time;
	}

	public String getEnded_time() {
		return ended_time;
	}

	public void setEnded_time(String ended_time) {
		this.ended_time = ended_time;
	}

	public Threading getThreading() {
		return threading;
	}

	public void setThreading(Threading threading) {
		this.threading = threading;
	}


	

	public long getSharesby() {
		return sharesby;
	}

	public void setSharesby(long sharesby) {
		this.sharesby = sharesby;
	}

	public long getGetsby() {
		return getsby;
	}

	public void setGetsby(long getsby) {
		this.getsby = getsby;
	}
//	"getsby_threads: int,
//    "getsby_sucesses": int,

    private int getsby_threads;
    public int getGetsby_threads() {
		return getsby_threads;
	}

	public void setGetsby_threads(int getsby_threads) {
		this.getsby_threads = getsby_threads;
	}

	public int getGetsby_sucesses() {
		return getsby_sucesses;
	}

	public void setGetsby_sucesses(int getsby_sucesses) {
		this.getsby_sucesses = getsby_sucesses;
	}

	private int getsby_sucesses ;

	private long sharesby;
	

	private long getsby;
	private long banana_id;
	private int status;
	private int prev_status;
	private String time_remaining;
	private String zoned_time;
	private String ended_time;
	private Threading threading;
	private String id;
	public int getCancle_reason() {
		return cancle_reason;
	}

	public void setCancle_reason(int cancle_reason) {
		this.cancle_reason = cancle_reason;
	}




	private int cancle_reason;
	



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJsonFromThreading() {
		String json = null;
		if (this.threading != null) {
			json = JacksonUtil.toJSon((this.threading));

		}

		return json;
	}

	private String jsonFromThreading;

	
}
