package com.example.webbansach_backend.config;

import com.example.webbansach_backend.entity.BookCategory;
import com.example.webbansach_backend.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Autowired
    private EntityManager entityManager;
    private String url = "http://localhost:8081";

    private String frontendUrl = "http://localhost:3000";

    /*  Add Id to Endpoints which crested by @RepositoryRestResource */

//    ___   Solution 1  ___
//    @Bean
//    public RepositoryRestConfigurer repositoryRestConfigurer() {
//        return RepositoryRestConfigurer.withConfig(config -> config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new)));
//    }

    //    ___   Solution 2  ___
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        /*  ---
        *   exposeIdsFor(Class<?>... domainTypes) : Set the list of domain types for which we will expose the ID value as a normal property.
        *   entityManager.getMetamodel().getEntities() : Get all Entity from Application
        *   .stream().map(Type::getJavaType).toArray(Class[]::new) :    Convert Entity to Array of Class object.
        * ---*/
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));

        /*  CORS configuration ==> allow front-end call to backendAPI through frontend URL and Method name   */
        cors.addMapping("/**")
                .allowedOrigins(frontendUrl)
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        HttpMethod[] chanCacPhuongThuc ={
                HttpMethod.POST,
                HttpMethod.PUT,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
        };
        disableHttpMethods(BookCategory.class, config, chanCacPhuongThuc);

        // diable DELETE Method
        HttpMethod[] phuongThucDelete = {
                HttpMethod.DELETE
        };
//        disableHttpMethods(User.class, config,phuongThucDelete );
        disableHttpMethods(User.class, config, new HttpMethod[]{HttpMethod.DELETE} );
    }

    private void disableHttpMethods(Class c,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] methods){
        config.getExposureConfiguration()
                .forDomainType(c)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(methods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methods));
    }
}
