package search.service;

import search.entity.Contact;

import java.util.*;

/**
 * If the strategy is ALL, the program should find lines containing all words from the query
 *
 * @link https://stackoverflow.com/questions/56488685/what-is-the-best-way-to-find-common-elements-from-2-sets/56492200
 */
public class AllFindingMethod implements FindingMethod {

    @Override
    public List<Contact> find(Map<String, Set<Integer>> peopleInvertedIndex, ArrayList<Contact> people, String query) {
        String[] queries = query.trim().toLowerCase().split("\\s+");
        List<Contact> resultList = new ArrayList<>();
        //edit
        Set<Integer> tempSet = new HashSet<>();
        Set<Integer> matches;

        for (int i = 0; i < queries.length; i++) {
            if (i == 0) {
                tempSet = peopleInvertedIndex.get(queries[i]);
            } else {
                matches = peopleInvertedIndex.get(queries[i]);
                tempSet.retainAll(matches);
            }

            if (tempSet == null) {
                return null;
            }
        }

        for (Integer person : tempSet) {
            resultList.add(people.get(person));
        }

        return resultList;
    }
}
