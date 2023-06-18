package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.Role;
import Api.AppDatDoAn.services.AccountService;
import Api.AppDatDoAn.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String getAllRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "role/list-roles";
    }

    @GetMapping("/add-role")
    public String addRoleForm(Model model) {
        model.addAttribute("newRole", new Role());
        return "role/add-role";
    }
    @PostMapping("/add-role")
    public String addRole(@Valid @ModelAttribute("newRole") Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "role/add-role";
        }
        roleService.saveRole(role);
        return "redirect:/role";
    }

    @GetMapping("/edit-role/{roleId}")
    public String editRoleForm(@PathVariable("roleId") UUID roleId, Model model) {
        Role role = roleService.getRoleById(roleId);
        model.addAttribute("editRole", role);
        return "role/edit-role";
    }
    @PostMapping("/edit-role")
    public String editRole(@Valid @ModelAttribute("editRole") Role role,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "role/edit-role";
        }
        roleService.saveRole(role);
        return "redirect:/role";
    }

    @GetMapping("/delete-role/{roleId}")
    public String deleteRole(@PathVariable("roleId") UUID roleId) {
        roleService.removeRole(roleId);
        return "redirect:/role";
    }

    @GetMapping("/assign-role/{id}")
    public String addRoleToUserForm(@PathVariable("id") UUID id, Model model) {
        Account account = accountService.getAccountById(id);
        List<Role> roles = roleService.getAllRoles();
        String[] rolesOfAccount = accountService.getRolesOfAccount(id);

        model.addAttribute("account", account);
        model.addAttribute("roles", roles);
        model.addAttribute("rolesOfAccount", rolesOfAccount);
        return "role/assign-role";
    }
    @PostMapping("/assign-role")
    public String addRoleToUser(@RequestParam UUID accountId, @RequestParam UUID roleId,
                                RedirectAttributes redirect) {
        String[] roles = accountService.getRolesOfAccount(accountId);
        String roleName = roleService.getRoleById(roleId).getRoleName();

        if (Arrays.asList(roles).contains(roleName)) {
            redirect.addFlashAttribute("exists", "Quyền đã tồn tại cho tài khoản này.");
            return "redirect:/role/assign-role/" + accountId;
        } else {
            accountService.addRoleToAccount(accountId, roleId);
            redirect.addFlashAttribute("success", "Đã thêm quyền cho tài khoản này.");
            return "redirect:/role/assign-role/" + accountId;
        }
    }

    @PostMapping("/remove-role-from-account")
    public String removeRoleFromUser(@RequestParam UUID accountId, @RequestParam UUID roleIdDel,
                                     RedirectAttributes redirect) {
        String[] roles = accountService.getRolesOfAccount(accountId);
        String roleName = roleService.getRoleById(roleIdDel).getRoleName();

        if (Arrays.asList(roles).contains(roleName)) {
            accountService.removeRoleFromAccount(accountId, roleIdDel);
            redirect.addFlashAttribute("success", "Đã xóa quyền cho người dùng này");
        } else {
            redirect.addFlashAttribute("notExist", "Người dùng không có quyền này");
        }

        return "redirect:/role/assign-role/" + accountId;
    }
}
