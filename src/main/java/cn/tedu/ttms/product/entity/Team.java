package cn.tedu.ttms.product.entity;

import java.io.Serializable;
/**
 * 团目实体对象:一个项目可以有多个团
 * 序列化专题拓展:
 * 1,transient 关键字修饰的属性默认不可以被序列化
 * @author JUGG
 *
 */
import java.util.Date;
public class Team implements Serializable {
	private static final long serialVersionUID = 368009064732092303L;
	
	private Integer id;
	private String name;
	private Integer projectId;
	private Integer vaild;
	private String note;
	private Date createdDate;
	private Date modifiedDate;
	private String createdUser;
	private String modifiedUser;
	
	
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", vaild=" + vaild + ", note=" + note
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getVaild() {
		return vaild;
	}
	public void setVaild(Integer vaild) {
		this.vaild = vaild;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	
	
}

























