package com.example.redpanda.context.user.primaryadapter.rest.find;

import com.example.redpanda.context.user.interaction.find.FindUserQueryHandler;
import com.example.redpanda.context.user.interaction.find.UserResult;
import com.example.redpanda.context.user.primaryadapter.rest.serialization.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindUserController {

    private final FindUserQueryHandler handler;

    public FindUserController(FindUserQueryHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/api/users/{id}")
    public UserResponse find(
            @PathVariable String id
    ) {
        UserResult userResult = handler.find(new FindUserQueryHandler.FindUserQuery(id));
        return  new UserResponse(userResult.id(), userResult.name());
    }
}
