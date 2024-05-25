//package com.buymore.transaction.api.core.security;
//
//import jakarta.enterprise.context.RequestScoped;
//import org.eclipse.microprofile.jwt.JsonWebToken;
//
//@RequestScoped
//public class AuthSecurityContext {
//
//    private static ThreadLocal<SecurityData> instance = new ThreadLocal<>();
//
//    public void initialize(){
//        instance.set(new SecurityData());
//    }
//
//    public ThreadLocal<SecurityData> getInstance(){
//        return instance;
//    }
//
//    public class SecurityData {
//
//        public String clientId;
//
//        public String ipAdress;
//
//        public JsonWebToken jsonWebToken;
//
//    }
//
//}
