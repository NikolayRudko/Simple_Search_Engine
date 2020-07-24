package search.parser;

import search.entity.Contact;
import search.exception.CustomException;

import java.util.*;

public class ContactParser {
    public ArrayList<Contact> parseData(List<String> strList) throws CustomException {

        ArrayList<Contact> contactList = new ArrayList<>();

        if (strList == null) {
            throw new CustomException("Whole file");
        } else {
            for (String str : strList) {
                if (str == null) {
                    throw new CustomException("Broken contact");
                } else {
                    String[] tempStr = str.trim().split("\\s+");
                    if (tempStr.length < 2 || tempStr.length > 3) {
                        throw new CustomException("Incorrect format of contact");
                    } else {
                        if (tempStr.length == 2) {
                            contactList.add(new Contact(tempStr[0], tempStr[1]));
                        }
                        if (tempStr.length == 3) {
                            contactList.add(new Contact(tempStr[0], tempStr[1], tempStr[2]));
                        }
                    }
                }
            }
        }
        return contactList;
    }

    public Map<String, Set<Integer>> parseInvertedIndex(ArrayList<String> listPeople) {

        Map<String, Set<Integer>> peopleInvertedIndex = new HashMap<>();

        for (int i = 0; i < listPeople.size(); i++) {
            String[] tempStr = listPeople.get(i).trim().toLowerCase().split("\\s+");
            for (String str : tempStr) {
                if (peopleInvertedIndex.containsKey(str)) {
                    peopleInvertedIndex.get(str).add(i);
                } else {
                    Set<Integer> set = new HashSet<>();
                    set.add(i);
                    peopleInvertedIndex.put(str, set);
                }
            }
        }
        return peopleInvertedIndex;
    }
}
