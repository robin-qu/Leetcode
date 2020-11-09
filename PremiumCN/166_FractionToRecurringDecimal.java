class Solution {
    public String fractionToDecimal(int num, int den) {
        StringBuilder sb = new StringBuilder();
        if ((num > 0 && den < 0) || (num < 0 && den > 0)) {
            sb.append('-');
        }
        long numerator = Math.abs((long) num);
        long denominator = Math.abs((long) den);
        
        sb.append(numerator / denominator);
        long remainder = numerator % denominator;
        if (remainder == 0) {
            return sb.toString();
        }
        sb.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0 && !map.containsKey(remainder * 10)) {
            long quotient = remainder * 10 / denominator;
            map.put(remainder * 10, sb.length());
            sb.append(quotient);
            remainder = remainder * 10 % denominator;
        }
        
        if (remainder == 0) {
            return sb.toString();
        }
        
        int insertIdx = map.get(remainder * 10);
        sb.insert(insertIdx, '(');
        sb.append(')');
        return sb.toString();
    }
}