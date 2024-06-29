package com.example.prmproject.repository;

import com.example.prmproject.api.APIClient;
import com.example.prmproject.service.UserService;

public class UserRepository {
    public static UserService getUserService() {
        return APIClient.getClient().create(UserService.class);
    }
}
