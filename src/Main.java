public class Main {

    private static final int PERIOD = 3000;

    public static void main(String[] args) throws InterruptedException {
        final Restaurant restaurant = new Restaurant();
        System.out.println("OPEN");

        new Thread(null, restaurant::getAnOrder, "Вася").start();
        new Thread(null, restaurant::getAnOrder, "Оля").start();
        new Thread(null, restaurant::getAnOrder, "Коля").start();
        new Thread(null, restaurant::getAnOrder, "Женя").start();
        new Thread(null, restaurant::getAnOrder, "Стас").start();

        new Thread(null, restaurant::makeAnOrder, "Посетитель 1").start();
        Thread.sleep(PERIOD);
        new Thread(null, restaurant::makeAnOrder, "Посетитель 2").start();
        Thread.sleep(PERIOD);
        new Thread(null, restaurant::makeAnOrder, "Посетитель 3").start();
        new Thread(null, restaurant::makeAnOrder, "Посетитель 4").start();
        Thread.sleep(PERIOD);
        new Thread(null, restaurant::makeAnOrder, "Посетитель 5").start();
    }
}
