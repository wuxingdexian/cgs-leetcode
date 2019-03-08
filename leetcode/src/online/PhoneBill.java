package online;

import java.util.*;


public class PhoneBill {


    private final static int THREAD_HOLD_SECONDS = 5 * 60;

    public int solution(String S) {
        // write your code in Java SE 8

        String[] lines = S.split("\n");

        Map<String/*phone number*/, Integer/*total seconds*/> phoneToSecondMap = new HashMap<>();
        Map<String/*phone number*/, Integer/*total fee*/> phoneToFeeMap = new HashMap<>();

        int allPhoneTotalFee = 0;
        for (String line : lines) {
            String[] parts = line.split(",");
            Integer totalSecond = phoneToSecondMap.getOrDefault(parts[1], 0);
            Integer totalFee = phoneToFeeMap.getOrDefault(parts[1], 0);
            int seconds = getSeconds(parts[0]);
            int fee = calculateFee(seconds);

            allPhoneTotalFee = allPhoneTotalFee + fee;

            phoneToSecondMap.put(parts[1], totalSecond + seconds);
            phoneToFeeMap.put(parts[1], totalFee + fee);
        }

        String freePhone = getFreePhone(phoneToSecondMap);

        Integer freeFee = phoneToFeeMap.getOrDefault(freePhone, 0);

        return allPhoneTotalFee - freeFee;
    }

    private String getFreePhone(Map<String/*phone number*/, Integer/*total seconds*/> phoneToSecondMap) {
        Map<Integer/*total seconds*/, List<String/*phone number*/>> secondToPhoneMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : phoneToSecondMap.entrySet()) {
            List<String> phoneList = secondToPhoneMap.getOrDefault(entry.getValue(), new ArrayList<>());
            phoneList.add(entry.getKey());
            secondToPhoneMap.put(entry.getValue(), phoneList);
        }

        /* get max duration */
        int maxDuration = 0;
        List<String/*phone number*/> maximumDurationPhoneList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry: secondToPhoneMap.entrySet()) {
            if (entry.getKey() > maxDuration) {
                maximumDurationPhoneList = entry.getValue();
            }
        }
        // text order
        maximumDurationPhoneList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        return maximumDurationPhoneList.get(0);
    }

    private int calculateFee(int seconds) {
        return seconds < THREAD_HOLD_SECONDS ? 3 * seconds : 150 * Double.valueOf(Math.ceil(seconds / 60.0)).intValue();

    }

    private int getSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.valueOf(parts[0]) * 60 * 60 + Integer.valueOf(parts[1]) * 60 + Integer.valueOf(parts[2]);
    }



    public static void main(String[] args) {
        System.out.println('\n');
        System.out.println(Integer.valueOf("02"));
        System.out.println(Math.round(2.4));
        System.out.println(Math.ceil(2.4));

        String S = "00:01:07,400-234-090\n" +
                "00:05:01,701-080-080\n" +
                "00:05:00,400-234-090";

//        String S = "00:01:07,400-234-090";

        PhoneBill phoneBill = new PhoneBill();
        int solution = phoneBill.solution(S);
        System.out.println(solution);
    }



}
