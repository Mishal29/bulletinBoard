package bean;

import java.sql.Timestamp;;

public class PostBean {
	private int id;
	private String name;
	private String mail;
	private String content;
	private String file;
	private Timestamp postTime;
	private Timestamp updateTime;

	public PostBean(int id, String name, String mail, String content, String file, Timestamp postTime, Timestamp updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.content = content;
		this.file = file;
		this.postTime = postTime;
		this.updateTime = updateTime;
	}
	public PostBean(String name, String mail, String content, String file) {
		super();
		this.name = name;
		this.mail = mail;
		this.content = content;
		this.file = file;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Timestamp getPostTime() {
		return postTime;
	}
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
