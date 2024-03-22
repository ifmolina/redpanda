package com.example.redpanda.context.user.primaryadapter.rest.update;

import com.example.redpanda.context.user.interaction.find.FindUserQueryHandler;
import com.example.redpanda.context.user.interaction.find.UserResult;
import com.example.redpanda.context.user.interaction.update.UpdateUserCommandHandler;
import com.example.redpanda.context.user.interaction.update.UpdateUserCommandHandler.UpdateUserCommand;
import com.example.redpanda.context.user.primaryadapter.rest.serialization.UserResponse;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateUserController {

    private final UpdateUserCommandHandler updateUserCommandHandler;
    private final FindUserQueryHandler findUserQueryHandler;

    public UpdateUserController(
            UpdateUserCommandHandler updateUserCommandHandler,
            FindUserQueryHandler findUserQueryHandler
    ) {
        this.updateUserCommandHandler = updateUserCommandHandler;
        this.findUserQueryHandler = findUserQueryHandler;
    }

    @PatchMapping("/api/users/{id}")
    public UserResponse update(
            @PathVariable String id,
            @RequestBody UpdateUserRequest request
    ) {
        updateUserCommandHandler.handle(
                new UpdateUserCommand(
                        id, request.name()
                )
        );

        return find(id);
    }

    private UserResponse find(String userId) {
        final UserResult userResult = findUserQueryHandler.find(new FindUserQueryHandler.FindUserQuery(userId));
        return new UserResponse(userResult.id(), userResult.name());
    }
}
