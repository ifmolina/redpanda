package com.example.redpanda.context.user.interaction.delete;

import com.example.redpanda.context.user.domain.User.Id;
import com.example.redpanda.context.user.domain.delete.UserDeleter;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserCommandHandler {

    private final UserDeleter deleter;

    public DeleteUserCommandHandler(UserDeleter deleter) {
        this.deleter = deleter;
    }

    public void handle(CreateUserCommand command) {
        deleter.delete(Id.from(command.id));
    }

    public record CreateUserCommand(
            String id
    ) {
    }
}
