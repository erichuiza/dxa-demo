package com.alliancedata;

import com.sdl.dxa.DxaSpringInitialization;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Initializes Spring context for DXA web application.
 */
@Import(DxaSpringInitialization.class)
@Configuration
@ComponentScan( basePackages = { "com.sdl.webapp", "com.sdl.dxa", "com.alliancedata"})
public class SpringInitializer {
}