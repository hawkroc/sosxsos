package com.sosxsos.ssm.entity;

public class BananaEntity {
	/**
	 * { “action_type”: int, “banana”: { “bubble”: { “topic”: int, “key_word”:
	 * string } “product: { “selling_reason”: int, “item_info”: { “name”:
	 * string, “desc”: string, “price”: double, “currency”:string }, “tags”: [{
	 * “tag”: int, “value”: string, “desc”: string }, { “tag”: int, “value”:
	 * string, “desc”: string }] } } }
	 */
	private static final long serialVersionUID = 1L;
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	private String video_url;
	private ProductEntity product;
	private BubbleEntity bubble;
	private MediaEntity media;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private int status;

	public MediaEntity getMedia() {
		return media;
	}

	public void setMedia(MediaEntity media) {
		this.media = media;
	}

	public BubbleEntity getBubble() {
		return bubble;
	}

	public void setBubble(BubbleEntity bubble) {
		this.bubble = bubble;
	}

	private int thoughtId;

	public int getThoughtId() {
		return thoughtId;
	}

	public void setThoughtId(int thoughtId) {
		this.thoughtId = thoughtId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	private int productId;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	private int userid;

}
