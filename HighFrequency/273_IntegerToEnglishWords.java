class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        
        String res = "";
        int count = 0;
        while (num > 0) {
            String comma = "";
            if (count == 1) {
                comma = " Thousand";
            } else if (count == 2) {
                comma = " Million";
            } else if (count == 3) {
                comma = " Billion";
            }
            int chunk = num % 1000;
            res = (chunk == 0 ? res : readThree(chunk) + comma + res);
            num /= 1000;
            count++;
        }
        
        return res.trim();
    }
    
    private String readThree(int num) {
        if (num / 100 < 1) {
            return getTens(num);
        }
        
        return getOnes(num / 100) + " Hundred" + getTens(num % 100);
    }
    
    private String getOnes(int num) {
        switch (num) {
            case 1:
                return " One";
            case 2:
                return " Two";
            case 3:
                return " Three";
            case 4:
                return " Four";
            case 5:
                return " Five";
            case 6:
                return " Six";
            case 7:
                return " Seven";
            case 8:
                return " Eight";
            case 9:
                return " Nine";
            default:
                return "";
        }
    }
    
    private String getTens(int num) {
        switch (num / 10) {
            case 0:
                return getOnes(num % 10);
            case 1:
                switch (num % 10) {
                    case 0:
                        return " Ten";
                    case 1:
                        return " Eleven";
                    case 2:
                        return " Twelve";
                    case 3:
                        return " Thirteen";
                    case 4:
                        return " Fourteen";
                    case 5:
                        return " Fifteen";
                    case 6:
                        return " Sixteen";
                    case 7:
                        return " Seventeen";
                    case 8:
                        return " Eighteen";
                    case 9:
                        return " Nineteen";
                }
            case 2:
                return " Twenty" + getOnes(num % 10);
            case 3:
                return " Thirty" + getOnes(num % 10);
            case 4:
                return " Forty" + getOnes(num % 10);
            case 5:
                return " Fifty" + getOnes(num % 10);
            case 6:
                return " Sixty" + getOnes(num % 10);
            case 7:
                return " Seventy" + getOnes(num % 10);
            case 8:
                return " Eighty" + getOnes(num % 10);
            case 9:
                return " Ninety" + getOnes(num % 10);
        }
        return "";
    }
}