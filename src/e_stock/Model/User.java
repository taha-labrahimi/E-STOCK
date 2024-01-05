package e_stock.Model;

public class User {
    private int userID;
    private String username;
    private String password; 
    private String userType;
    private byte[] image; 
    private String email; 
    private String firstName;
    private String lastName;
    
    // Constructor
    public User(int userID,String username, String password, String userType,byte[] image, String email, String firstName, String lastName) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.image=image;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    // Constructor 2
    public User(String username, String password, String userType,byte[] image, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.image=image;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // Set password should include logic to hash the password
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

     public Object[] getUserData() {
        return new Object[]{userID,username,userType,email,firstName,lastName 
        };
    }
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }    
}

