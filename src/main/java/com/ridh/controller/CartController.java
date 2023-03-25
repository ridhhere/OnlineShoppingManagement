//package com.ridh.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ridh.exception.RecordNotFoundException;
//import com.ridh.model.CartModel;
//import com.ridh.service.impl.CartServiceImpl;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//    @Autowired
//    private CartServiceImpl cartServiceImpl;
//    
//    @PostMapping("/add")
//    public CartModel createCart(@RequestBody CartModel cartModel) {
//        return cartServiceImpl.createCart(cartModel);
//    }
//    
//    @GetMapping("/get/{id}")
//    public CartModel getCartById(@PathVariable long id) throws RecordNotFoundException {
//        return cartServiceImpl.getCartById(id);
//    }
//    
//    @PutMapping("/update/{id}")
//    public CartModel updateCart(@PathVariable long id, @RequestBody CartModel cartModel) throws RecordNotFoundException {
//    	cartModel.setCartId(id);
//        return cartServiceImpl.updateCart(id,cartModel);
//    }
//    
//    @DeleteMapping("delete/{id}")
//    public String deleteCart(@PathVariable long id) throws RecordNotFoundException {
//    	return cartServiceImpl.deleteCart(id);
//    }
//}
