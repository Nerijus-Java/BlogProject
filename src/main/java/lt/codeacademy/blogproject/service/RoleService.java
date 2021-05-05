package lt.codeacademy.blogproject.service;

import lt.codeacademy.blogproject.model.Role;
import lt.codeacademy.blogproject.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getRoleByName(String name){
        return repository.findByRole(name);
    }
}
