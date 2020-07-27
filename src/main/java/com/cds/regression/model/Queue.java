package com.cds.regression.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "queue_details")
@AllArgsConstructor
@NoArgsConstructor
public class Queue {

	@Id
	@Column
	private String id;
	@Column
	private String projectId;
	@Column
	private String projectName;
	@Column
	private String environment;
	@Column
	private String moduleName;
	@Column
	private String ip;
	@Column
	private String port;
	@Column
	private String messageBroker;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String requestQueue;
	@Column
	private String requestExchange;
	@Column
	private String requestRoute;
	@Column
	private String responeQueue;
	@Column
	private String responseExchange;
	@Column
	private String responseRoute;
	@Column
	private String failureQueue;
	@Column
	private String failureExchange;
	@Column
	private String failureRoute;
} // End of Queue