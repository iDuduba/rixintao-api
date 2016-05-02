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
public class Realname {
  private String lastName;
  private String firstName;
  private String enLastName;
  private String enFirstName;
  private String jpLastName;
  private String jpFirstName;
  private String frontCertificate;
  private String backCertificate;
  private String handCertificate;
  private String status;
  private String rejectReason;
}
