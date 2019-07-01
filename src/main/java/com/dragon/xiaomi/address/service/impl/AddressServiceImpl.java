package com.dragon.xiaomi.address.service.impl;

import com.dragon.xiaomi.address.pojo.Address;
import com.dragon.xiaomi.address.service.AddressService;
import com.dragon.xiaomi.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/17 22:30
 * @Classname AddressServiceImpl
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;


    public List<Address> getAddressList(int uid) {
        return addressMapper.getAddressList(uid);
    }


    public void defautAdd(Integer id,Integer level) {
        addressMapper.defautAdd(id,level);
    }


    public void addAddress(Address address) {

       // address.setUid(32);
        //address.setLevel(0);
        addressMapper.addAddress(address);
    }


    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }


    public void deleteAddress(String id) {
        addressMapper.deleteAddress(id);
    }


    public List<Address> findByUserId(int id) {
        return addressMapper.findByUserId(id);
    }


    public Address findByAid(int aid) {
        return addressMapper.findByAid(aid);
    }
}
