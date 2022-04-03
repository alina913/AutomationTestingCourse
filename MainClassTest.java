import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{
    MainClass object = new MainClass();

    @Test
    public void testGetLocalNumber(){
        System.out.println("testGetLocalNumber");
        Assert.assertTrue("number is not 14", object.getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber(){
        System.out.println("testGetClassNumber");
        Assert.assertTrue("number is not more than 45", object.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString(){
        System.out.println("testGetClassString");
        String stringForTest = object.getClassString();
        Assert.assertTrue("String does not contain 'hello' or 'Hello'", object.stringAnalys(stringForTest));
    }
}
