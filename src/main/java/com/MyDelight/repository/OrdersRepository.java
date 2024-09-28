package com.MyDelight.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.MyDelight.entity.Customers;
import com.MyDelight.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	@Query(name = "SELECT a.ORDER_ID, a.ORDER_PRICE, a.customer_id, b.FIRST_NAME, b.PHONE_NUMBER, b.ADDRESS_LINE1, b.ADDRESS_LINE2 FROM USER_ORDERS a JOIN CUSTOMERS b WHERE a.customer_id = b.ID AND a.customer_id = ?1", nativeQuery = true)
	public List<Orders> getOrdersByCustomer(Customers customer);

	@Query("SELECT u FROM user_orders u WHERE u.order_sequence = (SELECT MAX(v.order_sequence) FROM user_orders v )")
	public Orders getOrderId();

	@Query("SELECT u from user_orders u where u.order_id = ?1")
	public Orders getOrderInfo(String order_id);

	@Query("SELECT u.order_status from user_orders u where u.order_id = ?1")
	public String getOrderStatus(String order_id);

	@Query("SELECT u.order_id from user_orders u where u.order_id = ?1")
	public String getOrdersId(String order_id);

	@Query("SELECT u from user_orders u where u.expected_delivery_date = ?1 AND u.order_status = ?2 ")
	public List<Orders> getOrdersByExpectedDeliveryDate(Date expectedDate, String status);

	@Query("SELECT order_id from user_orders where order_status = 'Placed' and customer_id = ?1 and expected_delivery_date = ?2 ")
	public String getOrders(int customerId, Date expectDate);

	@Query("SELECT u.customer from user_orders u where u.order_id = ?1")
	public Customers getCustomer(String orderId);

}
