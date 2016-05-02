/**
 * duduba
 * Created at 2016年5月2日 上午11:59:28
 */
package com.jtrips.tao.api.vo;

import java.util.Properties;

import com.jtrips.tao.api.res.HelloResponse;

import lombok.Data;

/**
 * @author duduba
 *
 */
@Data
public class Address {
  private String addressId;
  private String addressName;
  private String address;
  private String zipcode;
  private Boolean isDefault;
}
