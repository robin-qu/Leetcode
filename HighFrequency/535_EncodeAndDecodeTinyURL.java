// O(1)time O(n)space
public class Codec {
    private char[] dict = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private int id = 1;
    private Map<String, String> map = new HashMap<>();  // code to longUrl

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String code = getShortUrl();
        map.put(code, longUrl);
        id++;
        return "http://tinyurl.com/" + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String code = shortUrl.replace("http://tinyurl.com/", "");
        return map.get(code);
    }
    
    private String getShortUrl() {
        StringBuilder sb = new StringBuilder();
        int n = dict.length;
        
        int currId = id;
        while (currId > 0) {
            sb.append(dict[currId % n]);
            currId /= n;
        }
        
        return sb.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));