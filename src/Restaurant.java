import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    List<Food> foods = new ArrayList<>();
    Waiter waiter = new Waiter(this);

    public void makeAnOrder() {
        waiter.acceptTheOrder();
    }

    public void getAnOrder() {
         waiter.giveAnOrder();
    }

    List<Food> getFood() {
        return foods;
    }
}
