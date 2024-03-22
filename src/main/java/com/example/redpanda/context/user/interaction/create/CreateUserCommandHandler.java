package com.example.redpanda.context.user.interaction.create;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.create.UserCreator;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler {

    private final UserCreator userCreator;

    public CreateUserCommandHandler(UserCreator userCreator) {
        this.userCreator = userCreator;
    }

    public void create(CreateUserCommand command) {
        userCreator.create(
                User.Id.from(command.id),
                new User.Name(command.name)
        );
    }

    public record CreateUserCommand(
            String id,
            String name
    ) {
    }
}


