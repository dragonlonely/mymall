package com.dragon.xiaomi.mapper;
/**
 * @Classname AddressMapper
 * @Description TODO
 * @Date 2018/10/17 22:31
 * @Created by Administrator
 */

import com.dragon.xiaomi.address.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018/10/17 22:31
 * @Classname AddressMapper
 */
public interface AddressMapper {

    List<Address> getAddressList(int uid);

    void defautAdd(@Param("id") Integer id, @Param("level") Integer level);

    void addAddress(Address address);

    void updateAddress(Address address);

    void deleteAddress(String id);

    List<Address> findByUserId(int id);

    Address findByAid(int aid);
}
