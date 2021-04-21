//package eu.glutfree.glutfree.config;
//
//import eu.glutfree.glutfree.interceptors.HomePageInterceptor;
//import eu.glutfree.glutfree.interceptors.LoginPageInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class LoadInterceptorConfig implements WebMvcConfigurer {
//    private final HomePageInterceptor homePageInterceptor;
//    private final LoginPageInterceptor loginPageInterceptor;
//
//    public LoadInterceptorConfig(HomePageInterceptor homePageInterceptor, LoginPageInterceptor loginPageInterceptor) {
//        this.homePageInterceptor = homePageInterceptor;
//        this.loginPageInterceptor = loginPageInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.
//                addInterceptor(homePageInterceptor).
//                addPathPatterns("/home");
//        registry.
//                addInterceptor(loginPageInterceptor).
//                addPathPatterns("/users/login");
//    }
//}
