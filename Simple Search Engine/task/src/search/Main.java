package search;

import search.exception.CustomException;
import search.service.Service;

public class Main {
    public static void main(String[] args) throws CustomException {
        Service service = new Service();
        service.start(args);
    }
}

