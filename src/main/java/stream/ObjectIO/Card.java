package stream.ObjectIO;

import java.io.Serializable;

// Serializable giúp đọc và ghi đối tượng Card vào file
// Khi đối tượng được ghi vào file, nó sẽ được chuyển đổi thành một chuỗi byte
public class Card implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String email;

    public Card() {
    }

    public Card(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
