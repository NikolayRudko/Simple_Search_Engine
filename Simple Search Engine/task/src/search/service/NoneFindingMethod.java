package search.service;

import search.entity.Contact;

import java.util.*;

public class NoneFindingMethod implements FindingMethod {

    @Override
    public List<Contact> find(Map<String, Set<Integer>> peopleInvertedIndex, ArrayList<Contact> people, String query) {
        String[] queries = query.trim().toLowerCase().split("\\s+");
        List<Contact> resultList = new ArrayList<>();
        //add
        Set<Integer> tempSet = new HashSet<>();
        Set<Integer> matches;

        for (int i = 0; i < queries.length; i++) {
            if (i == 0) {
                tempSet = peopleInvertedIndex.get(queries[i]);
            } else {
                matches = peopleInvertedIndex.get(queries[i]);
                tempSet.addAll(matches);
            }

            if (tempSet == null) {
                return null;
            }
        }

        for (int i = 0; i < people.size(); i++) {
            if(!tempSet.contains(i)){
                resultList.add(people.get(i));
            }
        }

        return resultList;
    }
}
