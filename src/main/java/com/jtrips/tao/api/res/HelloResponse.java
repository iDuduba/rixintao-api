/**
 * duduba
 * Created at 2016年4月30日 下午11:10:02
 */
package com.jtrips.tao.api.res;

import java.lang.reflect.Field;
import java.util.Properties;

import com.jtrips.tao.api.enums.LangEnum;
import com.jtrips.tao.api.i18n.I18n;

import lombok.Data;

/**
 * @author duduba
 *
 */
@Data
public class HelloResponse extends I18nResponse {
  @I18n(codeType="China", code="yyy")
  private String xxx;
  
  private String yyy = "Chendu";
  
  private Properties properties = new Properties();

//  private List<Parameter> properties = new ArrayList<>();
  
  public void add(String name, String value) {
    properties.put(name, value);
//    Parameter parameter = new Parameter();
//    parameter.setName(name);
//    parameter.setValue(value);
//    properties.add(parameter);
  }  
}
