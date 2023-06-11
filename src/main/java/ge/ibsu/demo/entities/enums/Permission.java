package ge.ibsu.demo.entities.enums;

public enum Permission {
    ORDER_READ("order:read"), ORDER_ADD("order:add");

    Permission(String permission) {
        this.permission = permission;
    }

    private final String permission;

    public String getPermission() {
        return permission;
    }
}
