package com.example.redpanda.context.user.interaction.update;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.update.UserUpdater;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandHandler {

    private final UserUpdater updater;

    public UpdateUserCommandHandler(UserUpdater updater) {
        this.updater = updater;
    }

    public void handle(UpdateUserCommand command) {
        updater.update(
                User.Id.from(command.id),
                new User.Name(command.name)
        );
    }

    public record UpdateUserCommand(
            String id,
            String name
    ){}
}
