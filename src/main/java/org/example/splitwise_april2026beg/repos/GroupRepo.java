package org.example.splitwise_april2026beg.repos;

import org.example.splitwise_april2026beg.models.Group;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GroupRepo {
    private Map<Long, Group> groupMap = new TreeMap<>();

    public GroupRepo() {
    }

    public Optional<Group> findGroupById(Long groupId) {
        if (groupMap.containsKey(groupId)) {
            Group group = groupMap.get(groupId);
            return Optional.of(group);
        } else {
            return Optional.empty();
        }
    }


}
