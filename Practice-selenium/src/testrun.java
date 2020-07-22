import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class testrun {

        private WebDriver driver;

    @Test(priority = 0)
    public  void getGamesInfo(){
        testcheck TestCheck = new testcheck(driver);
        TestCheck.open().checkScroll();

        gamePage Game = TestCheck.selectGame();
        System.out.println(Game);
    }
}
