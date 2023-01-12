package Unit;

public class CountDownThread implements Runnable {

    @Override
    public void run() {
        int count = 3;
        System.out.println("-----LOADING-----");
        System.out.print("       ");
        for (int i = count; i > 0; i--) {
            System.out.print("*");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
