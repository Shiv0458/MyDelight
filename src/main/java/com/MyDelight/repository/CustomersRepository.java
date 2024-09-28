package com.MyDelight.repository;

import java.util.List;
import java.util.Optional;

import com.MyDelight.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomersRepository extends JpaRepository<Customers, Integer>{
	
	@Query("SELECT u from customers u where first_name = ?1")
	public Customers findByPassword(String first_name);
	
	@Query("SELECT u from customers u where phone_number = ?1")
	public List<Customers> findByPhoneNumber(String phone_number);
	
	@Query("SELECT u from customers u where u.phone_number = ?1")
	public Customers getCustomerInfo(String phone_number);

	@Query("SELECT u from customers u where u.phone_number = ?1")
	public Customers getByPhoneNumber(String phone_Number);
	
	@Query("SELECT u from customers u where u.mail_id = ?1")
	public List<Customers> getCustomerInfoByGmail(String gmail);


}
