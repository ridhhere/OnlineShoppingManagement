package com.ridh.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.entity.CheckoutEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CheckoutModel;
import com.ridh.repo.CheckoutRepo;
import com.ridh.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	@Autowired
	private CheckoutRepo daoImpl;

	@Override
	public CheckoutModel createCheckout(CheckoutModel checkoutModel) {
		// TODO Auto-generated method stub
		CheckoutEntity checkoutEntity = new CheckoutEntity();
		BeanUtils.copyProperties(checkoutModel, checkoutEntity);
		CheckoutEntity saveEntity = daoImpl.save(checkoutEntity);
		CheckoutModel newCheckoutModel = new CheckoutModel();
		BeanUtils.copyProperties(saveEntity, newCheckoutModel);
		return newCheckoutModel;
	}

	@Override
	public CheckoutModel getCheckoutById(Long id) throws RecordNotFoundException {
		Optional<CheckoutEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			CheckoutEntity checkoutEntity = findById.get();
			CheckoutModel newCheckoutModel = new CheckoutModel();
			BeanUtils.copyProperties(checkoutEntity, newCheckoutModel);
			return newCheckoutModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public CheckoutModel updateCheckout(Long id, CheckoutModel checkoutModel) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CheckoutEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			CheckoutEntity checkoutEntity = findById.get();
			checkoutModel.setCheckoutId(id);;
			BeanUtils.copyProperties(checkoutModel, checkoutEntity);
			CheckoutEntity saveEntity = daoImpl.save(checkoutEntity);
			CheckoutModel newCheckoutModel = new CheckoutModel();
			BeanUtils.copyProperties(saveEntity, newCheckoutModel);
			return newCheckoutModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public String deleteCheckout(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CheckoutEntity> findById = daoImpl.findById(id);
		String msg="";
		if (findById.isPresent()) {
			daoImpl.deleteById(id);
			msg="Deleted Succesfully!!!";
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
		return msg;

	}

}
