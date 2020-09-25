package com.crew.messaging.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the log_message database table.
 * 
 */
@Entity
@Table(name="log_message")
@NamedQuery(name="LogMessage.findAll", query="SELECT l FROM LogMessage l")
public class LogMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idf_log_message", insertable=false, updatable=false, unique=true, nullable=false)
	private int idfLogMessage;

	@Column(name="reg_date", insertable=false, updatable=false)
	private Timestamp regDate;

	@Column(name="request_body")
	private String requestBody;

	@Column(length=3)
	private String status;

	@Column(length=1)
	private String type;

	@Column(name="udp_date", insertable=false, updatable=false)
	private Timestamp udpDate;

	//bi-directional many-to-one association to TemplateKakao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idf_template_kakao")
	private TemplateKakao templateKakao;

	public LogMessage() {
	}

	public int getIdfLogMessage() {
		return this.idfLogMessage;
	}

	public void setIdfLogMessage(int idfLogMessage) {
		this.idfLogMessage = idfLogMessage;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getRequestBody() {
		return this.requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUdpDate() {
		return this.udpDate;
	}

	public void setUdpDate(Timestamp udpDate) {
		this.udpDate = udpDate;
	}

	public TemplateKakao getTemplateKakao() {
		return this.templateKakao;
	}

	public void setTemplateKakao(TemplateKakao templateKakao) {
		this.templateKakao = templateKakao;
	}

}