package com.example.redpanda.context.user.primaryadapter.rest.create;


import com.example.redpanda.context.user.interaction.create.CreateUserCommandHandler;
import com.example.redpanda.context.user.interaction.create.CreateUserCommandHandler.CreateUserCommand;
import com.example.redpanda.context.user.interaction.find.FindUserQueryHandler;
import com.example.redpanda.context.user.interaction.find.FindUserQueryHandler.FindUserQuery;
import com.example.redpanda.context.user.interaction.find.UserResult;
import com.example.redpanda.context.user.primaryadapter.rest.serialization.UserResponse;
import com.example.redpanda.library.idgenerator.IdGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {

    private final CreateUserCommandHandler createUserCommandHandler;
    private final FindUserQueryHandler findUserQueryHandler;
    private final IdGenerator idGenerator;

    public CreateUserController(
            CreateUserCommandHandler createUserCommandHandler,
            FindUserQueryHandler findUserQueryHandler,
            IdGenerator idGenerator
    ) {
        this.createUserCommandHandler = createUserCommandHandler;
        this.findUserQueryHandler = findUserQueryHandler;
        this.idGenerator = idGenerator;
    }

    @PostMapping("/api/users")
    public UserResponse create(
            @RequestBody CreateUserRequest request
    ) {
        final String userId = idGenerator.generate().toString();
        createUser(request, userId);
        return find(userId);
    }

    private void createUser(CreateUserRequest request, String userId) {
        createUserCommandHandler.create(
                new CreateUserCommand(
                        userId,
                        request.name()
                )
        );
    }

    private UserResponse find(String userId) {
        final UserResult userResult = findUserQueryHandler.find(new FindUserQuery(userId));
        return new UserResponse(userResult.id(), userResult.name());
    }
}
