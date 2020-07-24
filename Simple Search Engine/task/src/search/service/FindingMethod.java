package search.service;

import search.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FindingMethod {
    List<Contact> find(Map<String, Set<Integer>> peopleInvertedIndex, ArrayList<Contact> people, String query);
}
