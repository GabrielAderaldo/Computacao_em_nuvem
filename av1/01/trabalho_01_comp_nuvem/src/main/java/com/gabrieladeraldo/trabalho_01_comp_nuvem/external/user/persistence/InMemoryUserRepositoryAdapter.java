package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.user.persistence;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.User;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepositoryAdapter implements UserRepository {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public <S extends User> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.incrementAndGet());
        }
        users.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }
}
