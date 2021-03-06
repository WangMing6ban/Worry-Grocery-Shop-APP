package com.sky.Bean;
//评论

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Comment {
	private int comment_id;
	private User user;
	private Invitation invitation;
	private String comment_content;
	private String comment_state;
	private String comment_namexu;
	private String comment_image;
	private Date comment_time;
	private int comment_praiseNum;
	private Set replySet=new HashSet<Reply>();
	
	public Set getReplySet() {
		return replySet;
	}
	public void setReplySet(Set replySet) {
		this.replySet = replySet;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Invitation getInvitation() {
		return invitation;
	}
	
	public String getComment_namexu() {
		return comment_namexu;
	}
	public void setComment_namexu(String comment_namexu) {
		this.comment_namexu = comment_namexu;
	}
	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_state() {
		return comment_state;
	}
	public void setComment_state(String comment_state) {
		this.comment_state = comment_state;
	}
	
	public String getComment_image() {
		return comment_image;
	}
	public void setComment_image(String comment_image) {
		this.comment_image = comment_image;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public int getComment_praiseNum() {
		return comment_praiseNum;
	}
	public void setComment_praiseNum(int comment_praiseNum) {
		this.comment_praiseNum = comment_praiseNum;
	}
	
	
}
