package com.dragon.xiaomi.address.service;


import com.dragon.xiaomi.address.pojo.Address;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/17 22:30
 * @Classname AddressService
 */
public interface AddressService {


    List<Address> getAddressList(int uid);

    void defautAdd(Integer id, Integer level);

    void addAddress(Address address);

    void updateAddress(Address address);

    void deleteAddress(String id);

    List<Address> findByUserId(int id);

    Address findByAid(int aid);
}
