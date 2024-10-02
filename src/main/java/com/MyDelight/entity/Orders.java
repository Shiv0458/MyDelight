package com.MyDelight.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity(name = "user_orders")
@Table(name = "user_orders")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int order_sequence;

	private int order_price;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customers customer;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Products product;

	private int quantity;

	private String order_status;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a", timezone = "Asia/Kolkata")
	private Date order_placed_date;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a", timezone = "Asia/Kolkata")
	private Date order_modified_date;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a", timezone = "Asia/Kolkata")
	private Date order_cancelled_date;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a", timezone = "Asia/Kolkata")
	private Date order_delivered_date;

	private String order_id;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	private Date expected_delivery_date;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	private Date order_start_date;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	private Date order_end_date;

	public Date getOrder_start_date() {
		return order_start_date;
	}

	public void setOrder_start_date(Date order_start_date) {
		this.order_start_date = order_start_date;
	}

	public Date getOrder_end_date() {
		return order_end_date;
	}

	public void setOrder_end_date(Date order_end_date) {
		this.order_end_date = order_end_date;
	}

	public Date getExpected_delivery_date() {
		return expected_delivery_date;
	}

	public void setExpected_delivery_date(Date expected_delivery_date) {
		this.expected_delivery_date = expected_delivery_date;
	}

	public Date getOrder_delivered_date() {
		return order_delivered_date;
	}

	public void setOrder_delivered_date(Date order_delivered_date) {
		this.order_delivered_date = order_delivered_date;
	}

	public int getOrder_sequence() {
		return order_sequence;
	}

	public void setOrder_sequence(int order_sequence) {
		this.order_sequence = order_sequence;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public Date getOrder_placed_date() {
		return order_placed_date;
	}

	public void setOrder_placed_date(Date order_placed_date) {
		this.order_placed_date = order_placed_date;
	}

	public Date getOrder_modified_date() {
		return order_modified_date;
	}

	public void setOrder_modified_date(Date order_modified_date) {
		this.order_modified_date = order_modified_date;
	}

	public Date getOrder_cancelled_date() {
		return order_cancelled_date;
	}

	public void setOrder_cancelled_date(Date order_cancelled_date) {
		this.order_cancelled_date = order_cancelled_date;
	}

	public Orders() {
		super();
	}

	public Orders(int order_sequence, int order_price) {
		super();
		this.order_sequence = order_sequence;
		this.order_price = order_price;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public void setCustomer_id(Customers customer) {
		this.customer = customer;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}

	public Customers getCustomer() {
		return customer;
	}
}
