package com.gkatzioura.jobapi.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.spring.SpringCacheManager;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.kubernetes.TcpDiscoveryKubernetesIpFinder;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Slf4j
public class SpringCacheConfiguration {

	@Bean
	public Ignite igniteInstance() {
		log.info("Creating ignite instance");
		TcpDiscoveryKubernetesIpFinder tcpDiscoveryKubernetesIpFinder = new TcpDiscoveryKubernetesIpFinder();
		tcpDiscoveryKubernetesIpFinder.setNamespace("default");
		tcpDiscoveryKubernetesIpFinder.setServiceName("job-cache");

		TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
		tcpDiscoverySpi.setIpFinder(tcpDiscoveryKubernetesIpFinder);

		IgniteConfiguration igniteConfiguration = new IgniteConfiguration();

		igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);
		igniteConfiguration.setClientMode(false);

		return Ignition.start(igniteConfiguration);
	}

	@Bean
	public SpringCacheManager cacheManager(Ignite ignite) {
		SpringCacheManager springCacheManager =new SpringCacheManager();
		springCacheManager.setIgniteInstanceName(ignite.name());
		return springCacheManager;
	}

}
