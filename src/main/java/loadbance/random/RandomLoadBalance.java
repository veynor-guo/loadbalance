package loadbance.random;

import java.util.Map;
import java.util.Random;

import static loadbance.random.WeightedRandomLoadBalance.IPS;

/**
 * random
 *
 * 随机负载均衡
 *
 * @author GuoJiangtao
 * @date 2025/06/12
 */
public class RandomLoadBalance {


    /**
     * 随机负载均衡
     * <p>
     * 请求少时，随机数可能比较集中
     * 当请求多时，才会均匀分布
     * 负载均衡算法是为了应对请求量大的情况
     */
    public static void randomLoadBalancing() {

        Random rand = new Random();

        for (; ; ) {
            int index = rand.nextInt(IPS.size());
            System.out.println(IPS.get(index));
        }
    }

    /**
     * 加权随机负载均衡
     * <p>
     * 某台机器配置好，某台机器配置差
     */
    public static void weightedRandomLoadBalancing() {
        final Map<String, Integer> IPS = Map.of("192.168.0.1:9001", 1, "192.168.0.2:9001", 9, "192.168.0.3:9001", 5, "192.168.0.4:9001", 2, "192.168.0.5:9001", 7);

    }

    public static void main(String[] args) {
        randomLoadBalancing();
    }
}
