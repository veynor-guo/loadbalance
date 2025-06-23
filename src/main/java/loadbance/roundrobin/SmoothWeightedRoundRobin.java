package loadbance.roundrobin;

import loadbance.ServiceConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * smooth weighted round robin
 * <p>
 * 平滑加权轮询
 *
 * @author GuoJiangtao
 * @date 2025/06/14
 */
public class SmoothWeightedRoundRobin {
    private static List<IpWeight> ipWeights = new ArrayList<>((int) (ServiceConfig.WEIGHT_IP_LIST.size() / 0.75 + 1));

    static {
        for (Map.Entry<String, Integer> entry : ServiceConfig.WEIGHT_IP_LIST.entrySet()) {
            IpWeight ipWeight = new IpWeight(entry.getKey(), entry.getValue());
            ipWeights.add(ipWeight);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(smoothWeightedRoundRobin());
        }
    }

    public static String smoothWeightedRoundRobin() {
        // dynamicWeight += weight
        for (IpWeight ipWeight : ipWeights) {
            ipWeight.setDynamicWeight(ipWeight.getDynamicWeight() + ipWeight.getWeight());
        }

        System.out.println(ipWeights);

        // MaxIpWeight(dynamicWeight -= weight)
        IpWeight ipWeight = ipWeights.stream().max(Comparator.comparingDouble(IpWeight::getDynamicWeight)).get();
        Integer totalDynamicWeight = ipWeights.stream().map(IpWeight::getDynamicWeight).reduce(0, Integer::sum);
        ipWeight.setDynamicWeight(ipWeight.getDynamicWeight() - totalDynamicWeight);

        System.out.println(ipWeights);

        return ipWeight.getIp();
    }

    @Getter
    @Setter
    @ToString
    public static class IpWeight {
        private String ip;
        private Integer weight;
        private Integer dynamicWeight;

        public IpWeight(String ip, int weight) {
            this.ip = ip;
            this.weight = weight;
            this.dynamicWeight = 0;
        }

    }
}
