package org.nk.dao.impl;

import java.util.List;

import org.nk.dao.ISaleOrderDao;
import org.nk.model.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class SaleOrderDaoImpl implements ISaleOrderDao {

	@Autowired
	private HibernateTemplate ht;
	
	@Override
	public Integer saveSaleOrder(SaleOrder ob) {

		return (Integer) ht.save(ob);
	}

	@Override
	public List<SaleOrder> getAllSaleOrder() {

		return ht.loadAll(SaleOrder.class);
	}

	@Override
	public void deleteSaleOrder(Integer id) {

		ht.delete(new SaleOrder(id));
	}

	@Override
	public SaleOrder getOneSaleOrder(Integer id) {

		return ht.get(SaleOrder.class, id);
	}

	@Override
	public void updateSaleOrder(SaleOrder ob) {

		ht.update(ob);
	}

	

}
