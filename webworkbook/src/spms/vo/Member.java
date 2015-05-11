package spms.vo;

import java.util.Date;

import lombok.Data;

public @Data class Member {
    protected int no;
    protected String name;
    protected String email;
    protected String password;
    protected Date createdDate;
    protected Date modifiedDate;
    
    public Member setNo(int no) {
        this.no = no;
        return this;
    }
    
    public Member setName(String name) {
        this.name = name;
        return this;
    }
    
    public Member setEmail(String email) {
        this.email = email;
        return this;
    }
    
    public Member setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public Member setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }
    
    public Member setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }
}
