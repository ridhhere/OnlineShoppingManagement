package com.ridh.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridh.entity.CartEntity;
import com.ridh.exception.RecordNotFoundException;
import com.ridh.model.CartModel;
import com.ridh.repo.CartRepo;
import com.ridh.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepo daoImpl;

	@Override
	public CartModel createCart(CartModel cartModel) {
		// TODO Auto-generated method stub
		CartEntity cartEntity = new CartEntity();
		BeanUtils.copyProperties(cartModel, cartEntity);
		CartEntity saveEntity = daoImpl.save(cartEntity);
		CartModel newCartModel = new CartModel();
		BeanUtils.copyProperties(saveEntity, newCartModel);
		return newCartModel;
	}

	@Override
	public CartModel getCartById(Long id) throws RecordNotFoundException {
		Optional<CartEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			CartEntity cartEntity = findById.get();
			CartModel newCartModel = new CartModel();
			BeanUtils.copyProperties(cartEntity, newCartModel);
			return newCartModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public CartModel updateCart(Long id, CartModel cartModel) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CartEntity> findById = daoImpl.findById(id);
		if (findById.isPresent()) {
			CartEntity cartEntity = findById.get();
			cartModel.setCartId(id);;
			BeanUtils.copyProperties(cartModel, cartEntity);
			CartEntity saveEntity = daoImpl.save(cartEntity);
			CartModel newCartModel = new CartModel();
			BeanUtils.copyProperties(saveEntity, newCartModel);
			return newCartModel;
		} else {
			throw new RecordNotFoundException("Given Record doesn't Exist");
		}
	}

	@Override
	public String deleteCart(Long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Optional<CartEntity> findById = daoImpl.findById(id);
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
