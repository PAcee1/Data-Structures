import com.enbuys.queue.MyLoopQueue;

/**
 * @Author: Pace
 * @Data: 2018/12/13 15:35
 * @Version: v1.0
 * 数据结构——数组
 */
public class Main {
    public static void main(String[] args) {
        MyLoopQueue<Integer> queue = new MyLoopQueue<>();
        for(int i=0;i<10;i++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3==2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
