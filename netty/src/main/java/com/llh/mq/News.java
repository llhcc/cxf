package com.llh.mq;

import java.io.Serializable;

public class News implements Serializable{
	private String title;

	private String content;

	private String author;

	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content + ", author="
				+ author + ", url=" + url + "]";
	}
}
