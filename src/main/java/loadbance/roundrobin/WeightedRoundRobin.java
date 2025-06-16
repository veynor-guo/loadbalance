package loadbance.roundrobin;

import static loadbance.ServiceConfig.WEIGHT_IP_LIST;

/**
 * 加权轮询
 *
 * @author GuoJiangtao
 * @date 2025/06/13
 */
public class WeightedRoundRobin {

    private static int num = -1;

    /**
     * weighted round robin
     *
     * @return {@link String }
     */
    public static String WeightedRoundRobin(){

        // 一维坐标长度
        int weightTotal = 0;
        // 所有IP权重相同标记
        boolean flag = true;
        Integer[] weights = WEIGHT_IP_LIST.values().toArray(new Integer[0]);
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            weightTotal += weight;

            if (flag && i > 0 && weight != weights[i-1]){
                flag = false;
            }
        }

        // 下一个坐标点
        int num = getAndIncrement();
        int pos = num % weightTotal;

        if (!flag) {
            String[] ipArray = WEIGHT_IP_LIST.keySet().toArray(new String[0]);
            for (String ip : ipArray) {
                // IP坐标范围
                Integer weight = WEIGHT_IP_LIST.get(ip);
                if (pos < weight) {
                    return ip;
                }
                pos -= weight;
            }
        }

        // 轮询
        String[] ipArray = WEIGHT_IP_LIST.keySet().toArray(new String[0]);
        return ipArray[pos];
    }

    public static int getAndIncrement(){
        return ++num;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(WeightedRoundRobin());
        }

    }
}
