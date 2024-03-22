package com.example.redpanda.context.user.interaction.find;

import com.example.redpanda.context.user.domain.User;
import com.example.redpanda.context.user.domain.find.UserFinder;
import org.springframework.stereotype.Component;

@Component
public class FindUserQueryHandler {

    private final UserFinder finder;

    public FindUserQueryHandler(UserFinder finder) {
        this.finder = finder;
    }

    public UserResult find(FindUserQuery query) {
        final User user = finder.find(User.Id.from(query.id));
        return toUserResult(user);
    }

    private UserResult toUserResult(User user) {
        return new UserResult(user.getId().toString(), user.getName().value());
    }

    public record FindUserQuery(
            String id
    ){}
}
