/**
 * duduba
 * Created at 2016年5月2日 下午12:01:23
 */
package com.jtrips.tao.api.vo;

import lombok.Data;

/**
 * @author duduba
 *
 */

@Data
public class BankCard {
  private String cardId;
  private String lastName;
  private String firstName;
  private String bankName;
  private String subbranchName;
  private String subbranchNo;
  private String cardNumber;
  private String cardType;
  private Boolean isDefault;
}
