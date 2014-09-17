package net.anyjava.webParser1.vo;

public class ImgVO {
	private String id;
	private String site_no;
	private String subject;
	private String url;
	private String imgSeq;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSite_no() {
		return site_no;
	}
	public void setSite_no(String site_no) {
		this.site_no = site_no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getImgSeq() {
		return imgSeq;
	}
	
	public void setImgSeq( String seq ) {
		this.imgSeq = seq ;
	}
}
