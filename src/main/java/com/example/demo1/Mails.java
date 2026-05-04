package com.example.demo1;
import jakarta.persistence.*;

@Entity
@Table(name = "mails")
public class Mails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String receiver_user;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "userToMails", referencedColumnName = "\"user\"")
    private User userToMails;

    public Mails(String receiver_user,String message, User userToMails)
    {
        this.message = message;
        this.userToMails = userToMails;
        this.receiver_user = receiver_user;
    }

    public Mails()
    {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserToMails() {
        return userToMails;
    }

    public void setUserToMails(User userToMails) {
        this.userToMails = userToMails;
    }

    public String getReceiver_user() {
        return receiver_user;
    }

    public void setReceiver_user(String receiver_user) {
        this.receiver_user = receiver_user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
