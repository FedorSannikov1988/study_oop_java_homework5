package views;

import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

        private int UserId = 0;
        private Pattern namePattern = Pattern.compile("^\\S+$");
        private Pattern phonePattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");

        public void validateUserId(String userId) throws Exception {

            try {
                UserId = Integer.parseInt(userId);
            }

            catch(Exception ex) {
                throw new Exception("You did not enter a number in integer format");
            }

            if (UserId <= 0) {
                throw new Exception("Number cannot be less than or equal to zero");
            }
        }

        public void validateUser(User inputUser) throws Exception {

            Matcher nameMatcher = namePattern.matcher(inputUser.getFirstName());
            Matcher lastnameMatcher = namePattern.matcher(inputUser.getLastName());
            Matcher phoneMatcher = phonePattern.matcher(inputUser.getPhone());
            if(!nameMatcher.find()) {
                throw new Exception("Такое имя недопустимо!");
            }
            if(!lastnameMatcher.find()) {
                throw new Exception("Такая фамилия недопустима!");
            }
            if(!phoneMatcher.find()) {
                throw new Exception("Такой номер телефона не допустим!");
            }
        }
}
