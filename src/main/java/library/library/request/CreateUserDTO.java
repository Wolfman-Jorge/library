package library.library.request;

import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
    //private Set<String> roles;

    @NotBlank
    private String email;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /*
    public CreateUserDTO(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    */


    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreateUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //    public Set<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<String> roles) {
//        this.roles = roles;
//    }

}
