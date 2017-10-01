package fi.jyu.tietokonekauppa.listeners;

import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeAdmin();
    }

    private void initializeAdmin(){
        String login = "admin";
        String password = "admin";
        String firstName = "first";
        String lastName = "last";
        String email = "admin@admin.net";
        User admin = userService.getUser(login);
        if(admin == null){
            admin = userService.createAdmin(login, password, firstName, lastName, email);
            if(admin != null){
                System.out.println("Admin account successfully created. Please change password using admin panel.");
            }
        }
        else{
            System.out.println("Admin account login: "+admin.getLogin());
        }
    }
}
