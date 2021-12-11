package org.example;

import java.time.LocalDate;

public class Email {

    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");
    private int emailId;
    private String to;
    private String from;
    private LocalDate emailDate;
    private String subject;
    private String content;

    public Email(int emailId, String to, String from, LocalDate emailDate, String subject, String content) {
        this.emailId = emailId;
        this.to = to;
        this.from = from;
        this.emailDate = emailDate;
        this.subject = subject;
        this.content = content;
    }

    public Email(String to, String from, LocalDate emailDate, String subject, String content) {
        this.emailId = idGenerator.getNextId();
        this.to = to;
        this.from = from;
        this.emailDate = emailDate;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email{" +
                "idGenerator=" + idGenerator +
                ", emailId=" + emailId +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", emailDate=" + emailDate +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
