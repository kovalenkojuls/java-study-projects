package ru.julia.aop.instrumentation.proxyASM;

/*
    java -javaagent:proxyASMDemo.jar -jar proxyASMDemo.jar
*/
public final class ProxyASMDemo {

    public static void main(String[] args) {
        var myClass = new MyClass();
        myClass.secureAccess("Security Param");
    }
}