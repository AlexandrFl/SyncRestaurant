import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private final int ACCEPT_TIME = 1000;
    private final int COOK_TIME = 2000;
    private Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void acceptTheOrder() {
        try {
            lock.lock();
            System.out.println("Посетитель " + Thread.currentThread().getName() + " пришел в ресторан");
            Thread.sleep(ACCEPT_TIME);
            System.out.println("Посетитель " + Thread.currentThread().getName() + " делает заказ");
            restaurant.getFood().add(new Food());
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Food giveAnOrder() {
        try {
            lock.lock();
            while (restaurant.getFood().size() == 0) {
                System.out.println("Официант " + Thread.currentThread().getName() + " свободен");
                condition.await();
            }
            System.out.println("Официант " + Thread.currentThread().getName() + " принял заказ");
            Thread.sleep(ACCEPT_TIME);
            System.out.println("Повар готовит заказ");
            Thread.sleep(COOK_TIME);
            System.out.println("Повар приготовил заказ");
            Thread.sleep(ACCEPT_TIME);
            System.out.println("Официант " + Thread.currentThread().getName() + " несет заказ клиенту");
            System.out.println("✅ Заказ отдан клиенту ✅\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return restaurant.getFood().remove(0);
    }
}
