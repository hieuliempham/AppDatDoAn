package Api.AppDatDoAn.utils;

import Api.AppDatDoAn.entity.Role;
import Api.AppDatDoAn.reponsitory.IRoleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SampleDataInitializer {
    private final IRoleRepository roleRepository;

    public SampleDataInitializer(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initialize() {
        Role roleUser = roleRepository.findByRoleName("USER");
        Role roleAdmin = roleRepository.findByRoleName("ADMIN");

        Role newRoleUser = new Role();
        Role newRoleAdmin = new Role();

        if (roleUser == null && roleAdmin == null) {
            newRoleUser.setRoleName("USER");
            newRoleAdmin.setRoleName("ADMIN");
            roleRepository.save(newRoleUser);
            roleRepository.save(newRoleAdmin);
        } else if (roleUser == null) {
            newRoleUser.setRoleName("USER");
            roleRepository.save(newRoleUser);
        } else if (roleAdmin == null) {
            newRoleAdmin.setRoleName("ADMIN");
            roleRepository.save(newRoleAdmin);
        }
    }
}
