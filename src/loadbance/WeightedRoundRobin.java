package loadbance;

import static loadbance.ServiceConfig.WEIGHT_IP_LIST;

/**
 * 加权轮询
 *
 * @author GuoJiangtao
 * @date 2025/06/13
 */
public class WeightedRoundRobin {

    private int num = -1;

    public String WeightedRoundRobin(){

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

        int idx = getAndIncrement(weightTotal);

        if (!flag) {
            String[] ipArray = WEIGHT_IP_LIST.keySet().toArray(new String[0]);
            for (String ip : ipArray) {
                Integer weight = WEIGHT_IP_LIST.get(ip);
                weight %
            }
        }

        // 轮询
        String[] ipArray = WEIGHT_IP_LIST.keySet().toArray(new String[0]);
        return ipArray[idx];
    }

    public int getAndIncrement(int weightTotal){
        return ++num % weightTotal;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(WEIGHT_IP_LIST.keySet());
        }

    }
}
