package com.example.mongoclient.service;

import com.example.mongoclient.customannotation.Purgeable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class PurgeServiceImpl {

    public void purgeData() {

        Class<?>[] purgeableClasses = findAllConfigurationClassesInPackage("com.example.mongoclient.domain");

        Arrays.stream(purgeableClasses).forEach(System.out::println);
        Arrays.stream(purgeableClasses).forEach(aClass -> {
            System.out.println("aClass.getClass() = " + aClass.getClass());
        });
        System.out.println("purgeableClasses = " + purgeableClasses);
    }

    public Class<?>[] findAllConfigurationClassesInPackage(String packageName) {
        final List<Class<?>> result = new LinkedList<>();
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
                true, new StandardServletEnvironment());
        provider.addIncludeFilter(new AnnotationTypeFilter(Purgeable.class));

        for (BeanDefinition beanDefinition : provider
                .findCandidateComponents(packageName)) {
            try {
                result.add(Class.forName(beanDefinition.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                log.warn(
                        "Could not resolve class object for bean definition", e);
            }
        }
        return result.toArray(new Class<?>[result.size()]);
    }
}
