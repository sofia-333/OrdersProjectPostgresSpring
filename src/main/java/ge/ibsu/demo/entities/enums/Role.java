package ge.ibsu.demo.entities.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ge.ibsu.demo.entities.enums.Permission.ORDER_ADD;
import static ge.ibsu.demo.entities.enums.Permission.ORDER_READ;

public enum Role {
    ADMIN(Set.of(ORDER_READ, ORDER_ADD));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(i -> new SimpleGrantedAuthority(i.getPermission()))
                .collect(Collectors.toList());
        //authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        authorities.add(new SimpleGrantedAuthority(name()));
        return authorities;
    }
}
