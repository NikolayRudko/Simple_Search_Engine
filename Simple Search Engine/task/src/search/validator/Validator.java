package search.validator;

public class Validator {
    public boolean isCorrectInput(String[] args) {
        return args != null &&
                args.length == 2 &&
                args[0].equals("--data");
    }
}
