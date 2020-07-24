package search.service;

import search.entity.Contact;
import search.exception.CustomException;
import search.parser.ContactParser;
import search.reader.UserReader;
import search.validator.Validator;

import java.util.*;

public class Service {
    public void start(String[] args) throws CustomException {
        Validator validator = new Validator();
        if (!validator.isCorrectInput(args)) {
            System.out.println("Incorrect command line arguments");
        } else {
            String filePatch = args[1];
            UserReader reader = new UserReader();
            ContactParser parser = new ContactParser();
            //Read the file ans made the strings list
            ArrayList<String> result = reader.read(filePatch);
            //Convert the list of strings to the contact list
            ArrayList<Contact> contactList = parser.parseData(result);
            Map<String, Set<Integer>> invertedIndex = parser.parseInvertedIndex(result);
            menu(contactList, invertedIndex);
        }
    }

    private void menu(ArrayList<Contact> people, Map<String, Set<Integer>> invertedIndex) {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        do {
            System.out.println("\n=== Menu ===" +
                    "\n1. Find a person" +
                    "\n2. Print all people" +
                    "\n0. Exit");
            int itemOfMenu = Integer.parseInt(sc.nextLine());

            switch (itemOfMenu) {
                case (0):
                    exit = true;
                    System.out.println("\nBye!");
                    break;
                case (1):
                    System.out.println("\nEnter a name or email to search all suitable people.");
                    //findOfPeopleByIndex(invertedIndex, people, sc);
                    findMenu(sc, invertedIndex, people);
                    break;
                case (2):
                    System.out.println("\n=== List of people ===");
                    printPeople(people);
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.");
                    break;
            }
        } while (!exit);
    }

    private void findMenu(Scanner sc, Map<String, Set<Integer>> invertedIndex, ArrayList<Contact> people) {
        boolean exit;
        FindService findService = new FindService();
        String query;
        List<Contact> printList;
        do {
            exit = true;
            System.out.println("Select a matching strategy: ALL, ANY, NONE");
            String itemMenu = sc.nextLine();
            switch (itemMenu) {
                case ("ALL"):
                    findService.setMethod(new AllFindingMethod());
                    break;
                case ("ANY"):
                    findService.setMethod(new AnyFindingMethod());
                    break;
                case ("NONE"):
                    findService.setMethod(new NoneFindingMethod());
                    break;
                default:
                    exit = false;
                    break;
            }
            query = inputQuery(sc);
            printList = findService.find(invertedIndex, people, query);
            printPeople(printList);
        } while (!exit);
    }

    private String inputQuery(Scanner scanner) {
        String query;

        do {
            System.out.println("Enter a name or email to search all suitable people.");
            query = scanner.nextLine();
        } while (query == null || query.trim().isEmpty());

        return query;
    }

    private void printPeople(List<Contact> people) {
        if (people != null) {
            for (Contact contact : people) {
                System.out.println(contact);
            }
        }
    }
}
