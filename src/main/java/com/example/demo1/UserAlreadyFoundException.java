package com.example.demo1;
public class UserAlreadyFoundException extends RuntimeException
{
    public UserAlreadyFoundException(String message)
    {
        super(message);
    }
}
