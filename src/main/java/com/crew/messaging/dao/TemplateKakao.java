package com.crew.messaging.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the template_kakao database table.
 * 
 */
@Entity
@Table(name="template_kakao")
@NamedQuery(name="TemplateKakao.findAll", query="SELECT t FROM TemplateKakao t")
public class TemplateKakao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idf_template_kakao", insertable=false, updatable=false, unique=true, nullable=false)
	private int idfTemplateKakao;

	@Column(name="button_name", length=14)
	private String buttonName;

	@Column(length=30)
	private String code;

	@Column(length=2000)
	private String content;

	@Column(name="reg_date", insertable=false, updatable=false)
	private Timestamp regDate;

	@Column(name="upd_date", insertable=false, updatable=false)
	private Timestamp updDate;

	@Column(length=255)
	private String url;

	//bi-directional many-to-one association to LogMessage
	@OneToMany(mappedBy="templateKakao")
	private List<LogMessage> logMessages;

	public TemplateKakao() {
	}

	public int getIdfTemplateKakao() {
		return this.idfTemplateKakao;
	}

	public void setIdfTemplateKakao(int idfTemplateKakao) {
		this.idfTemplateKakao = idfTemplateKakao;
	}

	public String getButtonName() {
		return this.buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<LogMessage> getLogMessages() {
		return this.logMessages;
	}

	public void setLogMessages(List<LogMessage> logMessages) {
		this.logMessages = logMessages;
	}

	public LogMessage addLogMessage(LogMessage logMessage) {
		getLogMessages().add(logMessage);
		logMessage.setTemplateKakao(this);

		return logMessage;
	}

	public LogMessage removeLogMessage(LogMessage logMessage) {
		getLogMessages().remove(logMessage);
		logMessage.setTemplateKakao(null);

		return logMessage;
	}

}