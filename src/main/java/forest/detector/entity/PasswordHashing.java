package forest.detector.entity;


public class PasswordHashing {

    private String hash = "";

    private int receivingExistCodes(int x) {
         x += 256;

        while (!(((x <= 57) && (x >= 48)) || ((x <= 90) && (x >= 65)) || ((x <= 122) && (x >= 97)))) {
            if (x < 48)
                x += 24;
            else
                x -= 47;
        }
        return x;
    }

    private int getControlSum(String str) {
        int sault = 0;

        for (int i = 0; i < str.length(); i++)
            sault += (int)str.charAt(i);

        return sault;
    }

    public String getHash(String userString ) {

        int lengthHash = 60;
        String result = "";

        if (lengthHash > 3)
        {
             int unrealLenHashstr = 1;
             int realHashLength = 0;

             int originalSoult = this.getControlSum(userString);
             int originalLengthStr = userString.length();

            // Getting the length of the string closest to the specified hash length
            while (unrealLenHashstr <= lengthHash)
                realHashLength = (unrealLenHashstr *= 2);

            // 	This loop is needed if we enter a string for an example of 20 characters and want to get a hash of 5 characters
            while (unrealLenHashstr < originalLengthStr)
                unrealLenHashstr *= 2;

            // Make the hash length at least twice as long as the original one
            if ((unrealLenHashstr - originalLengthStr) < unrealLenHashstr)
                unrealLenHashstr *= 2;

            int addCharCount = unrealLenHashstr - originalLengthStr;

            for (int i = 0; i < addCharCount; i++)
                userString += (char)this.receivingExistCodes(userString.charAt(i) + userString.charAt(i+1));

            int maxSault = this.getControlSum(userString);
            int maxLengthStr = userString.length();

            while (userString.length() != realHashLength)
            {
                for (int i = 0, center = userString.length() / 2; i < center; i++)
                    this.hash += (char)this.receivingExistCodes(userString.charAt(center - i) + userString.charAt(center + i));

                userString = this.hash;
                this.hash = "";
            }

            for (int i = 0; this.hash.length() < (lengthHash - 4); i++)
            {
                if (i % 2 == 0)
                    this.hash += (char)this.receivingExistCodes(userString.charAt(i) + userString.charAt(i+1));
			else
                this.hash += userString.charAt(i);
            }

            this.hash += (char)this.receivingExistCodes(originalSoult);
            this.hash += (char)this.receivingExistCodes(originalLengthStr);

            this.hash += (char)this.receivingExistCodes(maxSault);
            this.hash += (char)this.receivingExistCodes(maxLengthStr);
            result = hash;
            hash="";

            return result;
        }
        return "";
    }




}
