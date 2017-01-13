package event;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;


public class EventConfig {
    private FirefoxDriver driver;
    private Map<String, String> eventsMap = new HashMap<String, String>();

    @Before
    public void FFRun(){
        driver=new FirefoxDriver();
    }
    public EventConfig(FirefoxDriver driver){
        this.driver=driver;
        this.initEventsMap();
    }

    public void WaitF(String eventkey) throws Exception {
        String rez= "";
        String eventName = eventsMap.get(eventkey);

        JavascriptExecutor js=null;
        if (driver instanceof JavascriptExecutor){
            js=(JavascriptExecutor) driver;

        }
        js.executeScript("window.TEST_EVENT_FIRED = false;" +
                "window.TEST_EVENT = new Sys.Observable();" +
                "window.TEST_EVENT.addListener('" + eventName + "', " +
                "function(){" +
                "window.TEST_EVENT.removeListener('" + eventName + "');" +
                "window.TEST_EVENT_FIRED = true; " +
                "});");

        while (!(rez == "true")) {
            rez = js.executeScript("return window.TEST_EVENT_FIRED").toString();
            Thread.sleep(100);
        }
    }

    public void waitForfinspin() throws Exception{
        this.WaitF("gamefin");
    }

    private void initEventsMap(){
        eventsMap.put("gamefin", "notify:stateHandler.enteringStoppedState");

    }
}
