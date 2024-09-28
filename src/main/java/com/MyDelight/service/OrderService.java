package com.MyDelight.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.TimeZone;

import com.MyDelight.entity.Customers;
import com.MyDelight.entity.Orders;
import org.apache.commons.collections.PredicateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;
import org.springframework.stereotype.Service;

import com.MyDelight.exception.DelightException;
import com.MyDelight.repository.OrdersRepository;


@Service
public class OrderService {

	@Autowired
	private OrdersRepository orderRepo;

	public String addOrder(Orders order) throws ParseException {
		
		String msg;
		Date start = order.getOrder_start_date();
		Date end = order.getOrder_end_date();
		if (start.before(getDate()) || end.before(getDate())) {
			throw new DelightException("Please give future date only for start and end date...");
		}
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		int dateDiff = (int) ChronoUnit.DAYS.between(start.toInstant(), end.toInstant());
		dateDiff++;
		Date st = start;
		List<Orders> saveOrder = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(st);
		cal.add(Calendar.DATE, -1);

		Orders ord = orderRepo.getOrderId();
		int orderId = ord.getOrder_sequence();
		List<Orders> list = new ArrayList<>();

		while (dateDiff > 0) {
			Orders or = new Orders();
			cal.add(Calendar.DATE, 1);
			String str = format.format(cal.getTime());
			st = new SimpleDateFormat("dd-MM-yyyy").parse(str);
			or.setCustomer_id(order.getCustomer());
			or.setExpected_delivery_date(st);
			or.setOrder_start_date(order.getOrder_start_date());
			or.setOrder_end_date(order.getOrder_end_date());
			or.setQuantity(order.getQuantity());
			or.setOrder_price(order.getOrder_price());
			or.setProduct(order.getProduct());
			orderId++;
			String orderSeq = "MLK000" + orderId;
			or.setOrder_id(orderSeq);
			or.setOrder_placed_date(getDate());
			or.setOrder_status("Placed");
			list.add(or);
			dateDiff--;
		}

		saveOrder = orderRepo.saveAll(list);

		if (saveOrder.size() != 0) {
			msg = "Your Order Placed Successfully ...";
		}
		else
			msg = "Failed in placing Order...";

		return msg;
	}

	public String cancelOrder(String order_id) throws ParseException {

		Orders cartItem = orderRepo.getOrderInfo(order_id);
		String str = orderRepo.getOrdersId(order_id);
		String status = orderRepo.getOrderStatus(order_id);
		if (status.equals("Cancelled") && str != null) {
			throw new DelightException("Your Order is Cancelled Already...");
		} else if (status.equals("Delivered") && str != null) {
			throw new DelightException("Your Order is Delivered Already...");
		} else {
			cartItem.setOrder_cancelled_date(getDate());
			cartItem.setOrder_status("Cancelled");
			orderRepo.save(cartItem);
			return "Your Order is Cancelled..";
		}
	}

	public List<Orders> getOldOrders(Customers customer) {
		List<Orders> oldOrders = orderRepo.getOrdersByCustomer(customer);

		List<Orders> old = new ArrayList<>();
		oldOrders.forEach(orders -> {
			if (orders.getOrder_status() != null && orders.getOrder_status().equals("Delivered")) {

				old.add(orders);
			}
		});
		
		return old;
	}

	public List<Orders> getUpcomingOrders(Customers customer) {
		List<Orders> oldOrders = orderRepo.getOrdersByCustomer(customer);
		List<Orders> up = new ArrayList<>();
		oldOrders.forEach(orders -> {
			if (orders.getOrder_status() != null && orders.getOrder_status().equals("Placed")) {
				up.add(orders);
			}
		});
		return up;
	}

	public List<Orders> getCancelledOrders(Customers customer) {
		List<Orders> oldOrders = orderRepo.getOrdersByCustomer(customer);
		List<Orders> can = new ArrayList<>();
		oldOrders.forEach(orders -> {
			if (orders.getOrder_status() != null && orders.getOrder_status().equals("Cancelled")) {
				can.add(orders);
			}
		});
		return can;
	}

	public Orders updateOrderDeails(Orders order, String order_id) throws ParseException {

		Orders userCart = orderRepo.getOrderInfo(order_id);
		if (userCart == null)
			throw new DelightException("Invalid Order_id please provide valid order id...");
		String str = orderRepo.getOrderStatus(order_id);
		Date expDelDate = order.getExpected_delivery_date();
		if (str.equals("Cancelled") && str != null) {
			throw new DelightException("Your Order is Cancelled Already...");
		} else if (str.equals("Delivered") && str != null) {
			throw new DelightException("Your Order is Delivered Already...");
		}
		
		Customers customerId = orderRepo.getCustomer(order_id);
		int customer = customerId.getId();
		String ord = orderRepo.getOrders(customer, expDelDate);

		if (ord != null)
			throw new DelightException("You already placed an order on given expected delivery date...");

		if (expDelDate != null) {
			userCart.setExpected_delivery_date(expDelDate);
			userCart.setOrder_modified_date(getDate());
		}
		if (order.getOrder_price() != 0 && order.getQuantity() != 0) {
			userCart.setOrder_price(order.getOrder_price());
			userCart.setQuantity(order.getQuantity());
			userCart.setOrder_modified_date(getDate());
		}
		orderRepo.save(userCart);
		Orders or = orderRepo.getOrderInfo(order_id);
		return or;
	}

	public Orders getOrderDetails(String orderId) {
		Orders orders = orderRepo.getOrderInfo(orderId);
		if(orders == null) {
			throw new DelightException("Inavlid Order Id please provide valid order Id...");
		}
		return orders;
	}

	public Date getDate() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Date date = new Date();
		String str = dateFormat.format(date);
		Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(str);
		return date1;
	}

}
