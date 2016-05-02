/**
 * duduba
 * Created at 2016年5月1日 下午9:54:35
 */
package com.jtrips.tao.api.res;

import java.lang.reflect.Field;

import com.jtrips.tao.api.enums.LangEnum;
import com.jtrips.tao.api.i18n.I18n;

/**
 * @author duduba
 *
 */
public class I18nResponse extends BaseResponse {

  /* (non-Javadoc)
   * @see com.jtrips.tao.api.res.BaseResponse#translate(com.jtrips.tao.api.enums.LangEnum)
   */
  @Override
  public void translate(LangEnum lang) {
    Class<?> clazz = getClass();
    Field[] fields = clazz.getDeclaredFields();
    for(Field field : fields) {
      if(field.isAnnotationPresent(I18n.class)) {
        field.setAccessible(true);
        I18n i18n = field.getAnnotation(I18n.class);
        String codeType = i18n.codeType();
        String code = i18n.code();   
        try {
          Field codeField = clazz.getDeclaredField(code);    
          codeField.setAccessible(true);
          field.set(this, lang.name() + ":" + codeType + "." + codeField.get(this));
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (NoSuchFieldException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (SecurityException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

}
