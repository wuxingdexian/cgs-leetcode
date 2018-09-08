package others.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：函数映射
 * 1. 建模：主要是如何解决冲突。从长串到短串，哈希值可能会存在冲突
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：Map、Set
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link String#indexOf)} 要去"/"后面一个，否则包含了"/"，
 * <code>String[] parts = shortUrl.substring(shortUrl.lastIndexOf("/") + 1).split("_");</code>
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 26/08/2018
 * @see
 * @since cgs-leetcode on  26/08/2018
 */
public class EncodeAndDecodeTinyURL {

//    Set<String/*shortUrl + id*/> shortUrlIdSet = new HashSet<>(); // 这个其实可以不用要了
    Map<String/*shortUrl*/, List<String/*longUrl*/>> short2longUrlMap = new HashMap<>();

    private final String shortUrlPrefix = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String hashCode = String.valueOf(String.valueOf(longUrl).hashCode());
        List<String> longUrlList = short2longUrlMap.getOrDefault(hashCode, new ArrayList<>());
        short2longUrlMap.put(hashCode, longUrlList);
        String code2id = hashCode + "_" + longUrlList.size();
//        shortUrlIdSet.add(code2id);
        longUrlList.add(longUrl);
        return shortUrlPrefix + "/" + code2id;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (null == shortUrl || !shortUrl.contains("_")) {
            return null;
        }
        String[] parts = shortUrl.substring(shortUrl.lastIndexOf("/") + 1).split("_");
        return short2longUrlMap.get(parts[0]).get(Integer.valueOf(parts[1]));
    }
}
