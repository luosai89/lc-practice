package LC_Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 8/23 Y
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
 * and retrieve the key's value at a certain timestamp.
 *
 * Methods
 * 1. Hashmap with defined new class Value for timestamp and value.
 */
public class M_0981_Time_Based_Key_Value_Store {

    class TimeMap {
        HashMap<String, List<Value>> map;
        class Value {
            String value;
            int timeStamp;

            public Value(String value, int timeStamp){
                this.value = value;
                this.timeStamp = timeStamp;
            }
        }

        /** Initialize your data structure here. */
        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            List<Value> list;
            if (map.get(key) != null) {
                list = map.get(key);
            } else {
                list = new ArrayList<>();
            }
            list.add(new Value(value, timestamp));
            map.put(key, list);
        }

        public String get(String key, int timestamp) {
            List<Value> list = map.get(key);
            if (list == null || list.size() == 0) return "";
            for (int i = list.size() - 1; i >= 0; i--){
                if (list.get(i).timeStamp <= timestamp) {
                    return list.get(i).value;
                }
            }
            return "";
        }
    }

/**
 * Your TimeMap1 object will be instantiated and called as such:
 * TimeMap1 obj = new TimeMap1();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
}
