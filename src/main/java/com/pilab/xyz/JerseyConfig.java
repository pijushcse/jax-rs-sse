package com.pilab.xyz;

import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
		scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
		Set<Class<?>> collect = scanner.findCandidateComponents("com.pilab.xyz").stream()
				.map(beanDefinition -> ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), getClassLoader()))
				.collect(Collectors.toSet());

		this.registerClasses(collect);

	}
}