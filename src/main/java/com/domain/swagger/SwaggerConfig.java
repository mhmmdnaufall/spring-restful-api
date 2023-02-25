// package com.domain.swagger;

// import java.util.Collections;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.Contact;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
// @EnableSwagger2
// public class SwaggerConfig {
    
//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2)
//             .select()
//             // .any() => scan semua package, kemudian yang bisa dia buatkan documentation akan dibuatkan
//             // kalo mau specified, pake '.basePackage("lokasi.package.yang.mau.di-scan")'
//             .apis(RequestHandlerSelectors.any()/*.basePackage("com.domain.controller")*/) 
//             .paths(PathSelectors.any())
//             .build()
//             .apiInfo(apiInfo());
//     }

//     private ApiInfo apiInfo() {
//         return new ApiInfo(
//             "My Demo API",
//             "Ini adalah demo API dengan SpringBoot oleh KelasKoding",
//             "v1.0",
//             "Terms of Service",
//             new Contact("Muhammad Naufal", "www.kelaskoding.com", "muhammadnaufaall@gmail.com"),
//             "Apache License",
//             "www.apache.com",
//             Collections.emptyList()
//         );
//     }

// }
