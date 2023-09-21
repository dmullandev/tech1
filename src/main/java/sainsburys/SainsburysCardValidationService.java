package sainsburys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SainsburysCardValidationService {

    private static final Logger LOG = LoggerFactory.getLogger(SainsburysValidateCardController.class);

    public boolean isValid(String cardNumber) {
        LOG.info("Received cardNumber: " + cardNumber);
        LOG.info("Cardnumber length: " + cardNumber.length());

        int total = 0;
        // iterate card number from end
        // double digit
        // if digit > 9, then multiply digit one * digit two
        // the sum of every calculation
        // return true if sum ends with a 0

        int index = cardNumber.length() - 1;

        while (index >= 0) {
            int digit = Integer.parseInt(String.valueOf(cardNumber.charAt(index)));

            int doubledDigit = digit * 2;

            if (doubledDigit > 9) {
                String doubledDigitStr = Integer.toString(doubledDigit);
                int digitOne = Integer.parseInt(String.valueOf(doubledDigitStr.charAt(0)));
                int digitTwo = Integer.parseInt(String.valueOf(doubledDigitStr.charAt(1)));
                int sum = digitOne * digitTwo;
                total += sum;

                LOG.info("Cardnumber Index {}: Value: {} Doubled: {} MultipliedDigits: {} Total: {}", index, digit, doubledDigit, sum, total);
            } else {
                total += doubledDigit;
                LOG.info("Cardnumber Index {}: Value: {} Doubled: {} Total: {}", index, digit, doubledDigit, total);
            }

            index--;
        }

        LOG.info("Endswith 0: {}", Integer.toString(total).endsWith("0"));
        return Integer.toString(total).endsWith("0");
    }

}
