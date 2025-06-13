package loadbance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServiceConfig {
    public static final List<String> IP_LIST = Arrays.asList("192.168.0.1:9001", "192.168.0.2:9001", "192.168.0.3:9001", "192.168.0.4:9001", "192.168.0.5:9001");

    public static final Map<String, Integer> WEIGHT_IP_LIST =
            Map.of("192.168.0.1:9001", 1, "192.168.0.2:9001", 9, "192.168.0.3:9001", 5, "192.168.0.4:9001", 2, "192.168.0.5:9001", 7);
}
