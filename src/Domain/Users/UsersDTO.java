package Domain.Users;


import lib.ERDataResources;

public class UsersDTO extends ERDataResources {

    public int id;
    public String name;
    public String email;

    public UsersDTO(){}

    public UsersDTO(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void showDTO() {
      IO.println("======= Dados do Usuário =======");
      IO.println("ID: " + this.id);
      IO.println("Nome: " + this.name);
      IO.println("Email: " + this.email);
      IO.println("================================");
    }
}