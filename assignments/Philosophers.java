import java.util.concurrent.locks.ReentrantLock;

public class Philosophers extends Thread{
    private int id;
    private static long start;
    private ReentrantLock leftFork;
    private ReentrantLock rightFork;
    public static void main(String[] args) {

        int n = 5;
        ReentrantLock[] forks = new ReentrantLock[n];

        
        for (int i = 0; i < n; i++) {
            forks[i] = new ReentrantLock();
        }
    
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            ReentrantLock left = forks[i];
            ReentrantLock right = forks[(i + 1) % n];

            Philosophers p = new Philosophers(i + 1, left, right);
            p.start();
        }


    }    
    Philosophers(int id, ReentrantLock left, ReentrantLock right){
        this.id = id;
        this.leftFork = left;
        this.rightFork = right;

    }

    private void think() throws InterruptedException{
        System.out.println(getTimestamp()+ " Philosopher " + id + " is currently thinking");
        Thread.sleep(1000 + (int)(Math.random() * 1000));
    }

    public void eat() throws InterruptedException{
        System.out.println(getTimestamp() + " Philosopher " + id + " is currently eating");
        Thread.sleep(2000 + (int)(Math.random() * 1000));
    }
    public static String getTimestamp(){
        long elapsedNanos = System.nanoTime() - start;
        long elapsedSeconds = elapsedNanos / 1000000000;

        long minutes = elapsedSeconds / 60;
        long seconds = elapsedSeconds % 60;

        return "[" + minutes + ":" + seconds + "]";

    }


    @Override
    public void run(){
        for(int i = 0; i < 10; i++){    
            try {
                think();
                ReentrantLock first = leftFork;
                ReentrantLock second = rightFork;

                if (System.identityHashCode(leftFork) > System.identityHashCode(rightFork)) {
                    first = rightFork;
                    second = leftFork;
                }

                first.lock();
                second.lock();
                eat();
            }

             catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                leftFork.unlock();
                rightFork.unlock();
                
            }
        }
    }
}


