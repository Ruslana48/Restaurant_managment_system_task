package entity;

public class Client {
    private int id;
    private String surname;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String status;
    public Client(){

    }

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Client(String surname, String name, String email, String phone, String password, String status) {
        //this.id = id;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }



    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder{
        Client client=new Client();

        public Builder setId(int id){
            client.id=id;
            return this;
        }

        public Builder setSurname(String surname){
            client.surname=surname;
            return this;
        }

        public Builder setName(String name){
            client.name=name;
            return this;
        }

        public Builder setEmail(String email){
            client.email=email;
            return this;
        }

        public Builder setPhone(String phone){
            client.phone=phone;
            return this;
        }

        public Builder setPassword(String password){
            client.password=password;
            return this;
        }

        public Builder setStatus(String status){
            client.status=status;
            return this;
        }
        public Client getClient(){
            return client;
        }

    }
}
