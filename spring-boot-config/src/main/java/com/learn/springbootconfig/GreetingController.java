package com.learn.springbootconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("${app.desc}")
    private String appDesc;

    @Value("Some Static Message")
    private String staticMessage;

    @Value("${my.list.values}")
    private List<String> myList;

    @Value("#{${db.connection}}")
    private Map<String, String> dbValues;

    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private Environment env;

    @GetMapping("/greeting")
    public String greeting(){
        return greetingMessage;
    }

    @GetMapping("/app")
    public String getAppDec(){
        return appDesc;
    }

    @GetMapping("/msg")
    public String getStaticMessage(){
        return staticMessage;
    }

    @GetMapping("/list")
    public List<String> getMyList(){
        return myList;
    }

    @GetMapping("/db")
    public Map<String, String> getDbValues(){
        return dbValues;
    }

    @GetMapping("/dbConn")
    public String getDbConnection(){
        String firstKeyName = dbValues.keySet().stream().findFirst().get(); //Java 8
        String firstKeyValue = dbValues.get(dbValues.keySet().toArray()[0]);
        return firstKeyName + ": " + firstKeyValue;
    }

    @GetMapping("/dbconfig")
    public String getDbConfigProperties() {
        return dbSettings.getConnection() + " : " + dbSettings.getHost();
    }

    @GetMapping("/env")
    public String getEnvDetails(){
        return env.toString();
    }
}
