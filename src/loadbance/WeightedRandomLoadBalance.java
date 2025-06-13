package loadbance;

import java.util.*;

/**
 * 加权随机负载均衡
 * <p>
 * 针对某台机器配置好，某台机器配置差的情况
 *
 * @author GuoJiangtao
 * @date 2025/06/12
 */
public class WeightedRandomLoadBalance {

    public static final Map<String, Integer> IPS =
            Map.of("192.168.0.1:9001", 1, "192.168.0.2:9001", 9, "192.168.0.3:9001", 5, "192.168.0.4:9001", 2, "192.168.0.5:9001", 7);


    /**
     * 加权随机负载均衡算法
     *
     * ——————————5————————3————2>
     *
     * @return
     */
    public static String weightedRandomLoadBalancingV2() {
        int weightTotal = 0;
        boolean flag = true;

        Integer[] weights = IPS.values().toArray(new Integer[0]);
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            // 求权重之和
            weightTotal += weight;

            // 判断所有权重是否都相等
            if (flag && 1 > 0 && weight != weights[i - 1]) {
                flag = false;
            }
        }

        Random random = new Random();

        // 权重不同，找随机数落到哪个IP的权重区间
        if (!flag) {
            int randomNum = random.nextInt(weightTotal);
            Set<Map.Entry<String, Integer>> entries = IPS.entrySet();
            for (Map.Entry<String, Integer> entry : entries) {
                Integer weight = entry.getValue();
                if (randomNum < weight) {
                    return entry.getKey();
                }
                randomNum -= weight;
            }
        }

        // 所有全权重相等，使用随机负载均衡
        int index = random.nextInt(IPS.size());
        String[] ipArray = IPS.keySet().toArray(new String[0]);
        return ipArray[index];
    }

    /**
     * 加权随机负载均衡（复制法）
     * <p>
     * 将ip按照权重生成到一个集合中
     * <p>
     * 这种方法遇到权重比较大的情况，会比较耗内存
     */
    public static void weightedRandomLoadBalancing() {
        List<Object> ipList = new ArrayList<>();
        Set<Map.Entry<String, Integer>> entries = IPS.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String ip = entry.getKey();
            int weight = entry.getValue();
            for (int i = 0; i < weight; i++) {
                ipList.add(ip);
            }
        }

        Random rand = new Random();

        for (; ; ) {
            int index = rand.nextInt(ipList.size());
            System.out.println(ipList.get(index));
        }
    }

    public static void main(String[] args) {
        weightedRandomLoadBalancing();
    }
}
