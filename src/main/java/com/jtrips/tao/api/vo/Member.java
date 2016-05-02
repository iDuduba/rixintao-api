/**
 * duduba
 * Created at 2016年5月2日 下午8:54:13
 */
package com.jtrips.tao.api.vo;

import java.util.List;

import lombok.Data;

/**
 * @author duduba
 *
 */
@Data
public class Member {
  private String userId;
  private String username;
  private String password;
  private String paymentPassword;
  private String nickname;
  private Character sex;
  private String language;
  private String portrait;
  private String myInviteCode;
  private String creditLevel;
  private Integer points;
  private Integer balance;
  private Realname realName;
  private List<BankCard> bankCards;
  private List<Address> addresses;
}
