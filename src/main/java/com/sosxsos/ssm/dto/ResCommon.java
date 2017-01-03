package com.sosxsos.ssm.dto;

/**
 * @author PENG
 *
 */
public class ResCommon {
	

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	private String transaction_id;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private int status;
	
	private int responseResult;
	public int getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(int responseResult) {
		this.responseResult = responseResult;
	}
	
	

	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

    private String image_url;
    
	
	
	
	

}
