import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testcheck {

    private WebDriver driver;
    private WebDriverWait wait;

    public testcheck(WebDriver driver){
        this.driver = driver;
    }

    public testcheck open(){
        System.out.println("constructor opened");
        return this;
    }

    public void checkScroll(){
        System.out.println("ScrollCheck completed");
    }

//    public gamePage selectGame(){
//        checkScroll();
//        return new gamePage(driver);
//    }

}
