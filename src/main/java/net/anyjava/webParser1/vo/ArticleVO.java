package net.anyjava.webParser1.vo;

public class ArticleVO {

	private String articleNo;
	private String subject;
	private String cntComment;
	private String nick;
	private String date;
	private String hit;
	
	private String url;

	
	
	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCntComment() {
		return cntComment;
	}

	public void setCntComment(String cntComment) {
		this.cntComment = cntComment;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("NO      : ").append( this.articleNo ).append("\n");
		sb.append("SUBJECT : ").append( this.subject ).append("\n");
		sb.append("URL     : ").append( this.url ).append("\n");
		sb.append("DATE    : ").append( this.date ).append("\n");
		sb.append("WRITER  : ").append( this.nick ).append("\n");
		sb.append("HIT     : ").append( this.hit ).append("\n");
		
		return sb.toString();
	}
	
	
	
}
