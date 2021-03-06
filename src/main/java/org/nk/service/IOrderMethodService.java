package org.nk.service;

import java.util.List;

import org.nk.model.OrderMethod;

public interface IOrderMethodService {

	public Integer saveOrderMethod(OrderMethod ob);

	public List<OrderMethod> getAllOrderMethod();

	public void deleteOrderMethod(Integer id);

	public OrderMethod getOneOrderMethod(Integer id);

	public void updateOrderMethod(OrderMethod ob);
	
	public List<Object[]> getOrderIdAndCode(String mode);
}
