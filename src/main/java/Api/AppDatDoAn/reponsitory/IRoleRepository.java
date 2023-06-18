package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    @Query("SELECT r FROM Role r WHERE r.roleName = ?1")
    Role findByRoleName(String roleName);
    @Query("SELECT r.roleId FROM Role r WHERE r.roleName = ?1")
    UUID getRoleIdByRoleName(String roleName);
}
