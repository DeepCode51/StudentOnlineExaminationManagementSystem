package com.sanqing.dao.base.impl;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

public class AutoBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private static final String RESOURCE_PATTERN = "/**/*.class";
    private static final String DAO_CALSS_NAME = "com.sanqing.dao.base.impl.BaseDaoImpl";
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private TypeFilter[] daoTypeFilters = new TypeFilter[] { new AnnotationTypeFilter(AutoDao.class, false) };

    private String[] packagesToScan;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) throws BeansException {
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        scanPackages(registry);
    }

    private void scanPackages(BeanDefinitionRegistry registry) {
        if (this.packagesToScan != null) {
            try {
                for (String pkg : this.packagesToScan) {
                    String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                            + ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
                    Resource[] resources = this.resourcePatternResolver.getResources(pattern);
                    MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            MetadataReader reader = readerFactory.getMetadataReader(resource);
                            if (matchesFilter(reader, readerFactory, daoTypeFilters)) {
                                Class<?> domainClass = Class.forName(reader.getClassMetadata().getClassName());
                                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
                                beanDefinition.setBeanClassName(DAO_CALSS_NAME);
                                ConstructorArgumentValues cavs = new ConstructorArgumentValues();
                                cavs.addIndexedArgumentValue(0, domainClass);
                                beanDefinition.setConstructorArgumentValues(cavs);
                                beanDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
                                registry.registerBeanDefinition(domainClass.getAnnotation(AutoDao.class).name(),
                                        beanDefinition);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException("Failed to scan classpath for unlisted classes", ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("Failed to load class", ex);
            }
        }
    }

    private static boolean matchesFilter(MetadataReader reader, MetadataReaderFactory readerFactory,
            TypeFilter[] typeFilters) throws IOException {
        if (typeFilters != null) {
            for (TypeFilter filter : typeFilters) {
                if (filter.match(reader, readerFactory)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPackagesToScan(String[] packagesToScan) {
        this.packagesToScan = packagesToScan;
    }
}
