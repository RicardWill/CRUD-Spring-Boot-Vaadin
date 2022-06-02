package com.example.application;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@Theme(value = "flowcrmtutorial")
@PWA(name = "Flow CRM Tutorial", shortName = "Flow CRM Tutorial", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Main extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }

}
