/**
 * duduba
 * Created at 2016年5月1日 上午10:55:57
 */
package com.jtrips.tao.api.i18n;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author duduba
 *
 */
@Slf4j
//@Component
public class I18nProcessor implements BeanPostProcessor {

  /* (non-Javadoc)
   * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
   */
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  /* (non-Javadoc)
   * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
   */
  @Override
  public Object postProcessBeforeInitialization(final Object bean, final String name) throws BeansException {
    ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
      public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
          ReflectionUtils.makeAccessible(field);          
          I18n i18n = field.getAnnotation(I18n.class);
          if (i18n != null) {
            log.debug("Bean {} has I18N field {}!", name, field.getName());
            String codeType = i18n.codeType();
            String code = i18n.code();            
            field.set(bean, codeType + "." + code);
          }
      }
    });   
  return bean;    
  }


}
