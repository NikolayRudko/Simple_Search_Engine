package search.service;

import search.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindService {
    private FindingMethod method;

    public void setMethod(FindingMethod method) {
        this.method = method;
    }

    public List<Contact> find(Map<String, Set<Integer>> peopleInvertedIndex,
                              ArrayList<Contact> people,
                              String query) {
        return this.method.find(peopleInvertedIndex, people, query);
    }
}
