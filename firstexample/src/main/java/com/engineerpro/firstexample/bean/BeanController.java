package com.engineerpro.firstexample.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {

  private final ConfigurableApplicationContext applicationContext;

  public BeanController(ConfigurableApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @GetMapping("/api/beans")
  public List<Map<String, String>> listAllBeansWithScope() {
    // Get all bean names from the application context
    String[] beanNames = applicationContext.getBeanDefinitionNames();
    Arrays.sort(beanNames); // Sort the bean names alphabetically

    List<Map<String, String>> beansWithScope = new ArrayList<>();

    // Iterate through each bean name and retrieve its scope
    for (String beanName : beanNames) {
      BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition(beanName);
      String scope = beanDefinition.getScope();
      if (scope == null || scope.isEmpty()) {
        scope = BeanDefinition.SCOPE_SINGLETON; // Default scope is singleton
      }

      // Create a map for each bean with its name and scope
      beansWithScope.add(Map.of("beanName", beanName, "scope", scope));
    }

    return beansWithScope;
  }
}
