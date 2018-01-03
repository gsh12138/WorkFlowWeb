package helpers;

import org.springframework.web.method.HandlerMethod;

/**
 * Created by tech on 2018/1/2.
 */
public class ActiveNav {

    private HandlerMethod method;
    private String controllerName;

    public ActiveNav(HandlerMethod method) {
        this.method = method;
        this.controllerName=getControllerName();
    }

    public String getName() {
        return method.getMethod().getName();
    }

    public String getMainName() {
        return controllerName;
    }

    private String getControllerName(){
        String className = method.getMethod().getName();
        String[] packageNames = method.getMethod().toString().split("\\.");
        String controllerName = null;
        for (String name:packageNames
             ) {

            if(name.endsWith("Controller")){
                controllerName=name.split("Controller")[0];
                break;
            }

        }
        return controllerName;
    }


}
