package com.example.redpanda.context.user.primaryadapter.rest.delete;

import com.example.redpanda.context.user.interaction.delete.DeleteUserCommandHandler;
import com.example.redpanda.context.user.interaction.delete.DeleteUserCommandHandler.CreateUserCommand;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteUserController {

    private final DeleteUserCommandHandler deleteHandler;

    public DeleteUserController(DeleteUserCommandHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }

    @DeleteMapping("/api/users/{id}")
    void delete(
            @PathVariable String id
    ) {
        deleteHandler.handle(new CreateUserCommand(id));
    }
}
