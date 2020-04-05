import org.testng.annotations.DataProvider;

public class InvalidDataSetDataProvider {
    @DataProvider(name = "Invalid Data Sets Provider")
    public Object[][] invalidData() {
        return new Object[][]{
                {101, "qui est esse"},
                {3, "qui"},
                {-1, "qui est esse"},
                {"qwerty", "qui est esse"},
                {1.5, "qui est esse"},
                {"", "qui est esse"},
                {5, ""}
        };
    }
}
