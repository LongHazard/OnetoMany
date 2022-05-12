package com.example.spring2.payload;

import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.List;
@Data
public class RolePayload {
    private String roleName;
    @ElementCollection
    private List<Integer> userlist;
}
